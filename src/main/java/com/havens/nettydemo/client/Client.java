package com.havens.nettydemo.client;

import com.havens.nettydemo.message.Message;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.MessageToByteEncoder;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by havens on 15-8-7.
 */
public class Client {
    public void connect(int port, String host) throws Exception {
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap  = new Bootstrap()
                    .group(group)
                    .channel(NioSocketChannel.class)
                    .handler(new ClientChannelHaindler());
            Channel channel = bootstrap.connect(host, port).sync().channel();
            Message msg=new Message();
           // BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            while(true){
//                channel.writeAndFlush((Object)(in.readLine() +"\r\n"));
                msg=new Message();
                msg.cmd="time_check";
                msg.data=new HashMap();
                msg.put(msg.cmd,msg.data);
                System.out.println(msg.toString());
                channel.writeAndFlush(msg);
                Thread.sleep(5000);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            group.shutdownGracefully();
        }
    }

    /**
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        new Client().connect(8090, "127.0.0.1");
    }
}
