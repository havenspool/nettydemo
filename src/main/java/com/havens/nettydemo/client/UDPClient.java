package com.havens.nettydemo.client;


import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.DatagramChannel;
import io.netty.channel.socket.DatagramPacket;
import io.netty.channel.socket.nio.NioDatagramChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.io.IOException;

import java.net.InetAddress;
import java.net.InetSocketAddress;


/**
 * Created by havens on 15-8-17.
 */
public class UDPClient {

    public void init(String host,int port){
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap  = new Bootstrap()
                    .group(group)
                    .channel(NioDatagramChannel.class)
                    .handler(new UDPClientHandler());
            Channel channel = bootstrap.connect(host, port).sync().channel();

            while (true){
                Thread.sleep(500);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            group.shutdownGracefully();
        }
    }

    public static void main(String[] args) throws IOException {
        new UDPClient().init("127.0.0.1",9000);
    }
}
