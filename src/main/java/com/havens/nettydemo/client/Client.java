package com.havens.nettydemo.client;

import com.havens.nettydemo.entity.Hero;
import com.havens.nettydemo.entity.User;
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
           // BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            User user=new User();
            user.id=10001;
            user.name="havens";
            user.pwd="123456";

            Message msg = new Message();
            msg.cmd = "time_check";
            msg.data= new HashMap();

            Message msg2 = new Message();
            msg2.cmd = "login";
            Map data2 = new HashMap();
            data2.put("id",user.id);
            data2.put("name",user.name);
            data2.put("pwd",user.pwd);
            msg2.data=data2;
//            channel.writeAndFlush(msg2);
//            channel.writeAndFlush(msg);
            while(true){
//              channel.writeAndFlush((Object)(in.readLine() +"\r\n"));
//                channel.writeAndFlush(msg);
//                channel.writeAndFlush(msg2);
//                channel.writeAndFlush(msg);
                //channel.writeAndFlush(msg2);
                Thread.sleep(15000);
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
        //max 2500
        for(int i=0;i<1000;i++){
            new Thread(new Runnable() {
                public void run() {
                    try {
                        new Client().connect(8090, "127.0.0.1");
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }
}
