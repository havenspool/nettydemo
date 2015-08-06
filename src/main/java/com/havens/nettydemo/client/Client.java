package com.havens.nettydemo.client;

import com.havens.nettydemo.message.Message;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by havens on 15-8-6.
 */
public class Client {
    private ScheduledExecutorService executor= Executors.newScheduledThreadPool(1);
    EventLoopGroup group=new NioEventLoopGroup();

    private Channel sendchannel = null;
    private static final String host="127.0.0.1";
    private static final int port=8090;

    public void connect() throws Exception{
        try {
            Bootstrap client = new Bootstrap();
            client.group(group).channel(NioSocketChannel.class)
                    .option(ChannelOption.TCP_NODELAY, true)
                    .handler(new ClientChannelInitializer(this));
            ChannelFuture future = client.connect(new InetSocketAddress(host, port)).sync();
            sendchannel=future.channel();
        }finally {
            executor.execute(new Runnable() {
                public void run() {
                    try {
                        TimeUnit.SECONDS.sleep(15);
                        System.out.println("client connect again...");
                        connect();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                }
            });
        }
    }

    public void sendHello(String content) {
        Message helloMsg = new Message();
        HashMap msgObj = new HashMap();
        msgObj.put("cmd", "say_hello");
        msgObj.put("result", true);
        msgObj.put("comment", content);
        helloMsg.setSendMap(msgObj);
        sendMsg(helloMsg);
    }

    public void sendMsg(Message msg) {
        sendchannel.writeAndFlush(msg);
        System.out.println("发送数据成功,命令码:\t" + msg.getSendMap().toString());
    }

    public static void main(String[] args) throws Exception{
        Client client = new Client();
        client.connect();// 开启客户端netty

        // 发送名字检查
        client.sendHello("fuck the GFW");
    }

}
