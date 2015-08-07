package com.havens.nettydemo.client;

import com.havens.nettydemo.message.Message;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * Created by havens on 15-8-7.
 */
public class ClientHandler extends SimpleChannelInboundHandler<Message> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Message s) throws Exception {
        System.out.println("client receive from server:"+s);
    }
}