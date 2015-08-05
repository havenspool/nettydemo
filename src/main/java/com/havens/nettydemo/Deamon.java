package com.havens.nettydemo;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * Created by havens on 15-8-5.
 */
public class Deamon {

    public void init(){
        EventLoopGroup conn=new NioEventLoopGroup();
        EventLoopGroup work=new NioEventLoopGroup();
        ServerBootstrap start=new ServerBootstrap();
//        start.group(conn,work).option(NioServerSocketChannel.class)


    }


    public static void main(String[] args){
        new Deamon();
    }
}