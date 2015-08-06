package com.havens.nettydemo.server;

import com.havens.nettydemo.message.Message;
import com.havens.nettydemo.queue.LoginQueue;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * Created by havens on 15-8-6.
 */
public class ServerHandler extends SimpleChannelInboundHandler<Message> {

    @Override
    protected void messageReceived(ChannelHandlerContext ctx, Message msg) throws Exception {
        System.out.println("messageReceived");
        if (msg == null) {
            return;
        }
        LoginQueue.getInstance().put(msg);
    }

    public void channelRead(ChannelHandlerContext ctx, Message msg) throws Exception {
        System.out.println("channelRead");
        if (msg == null) {
            return;
        }
        LoginQueue.getInstance().put(msg);
    }
}