package com.havens.nettydemo.server;

import com.havens.nettydemo.Service;
import com.havens.nettydemo.entity.Hero;
import com.havens.nettydemo.entity.User;
import com.havens.nettydemo.job.LoginJob;
import com.havens.nettydemo.message.Message;
import com.havens.nettydemo.message.MessageHelper;
import com.havens.nettydemo.service.TimeCheckService;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by havens on 15-8-7.
 */
public class ServerHandler extends SimpleChannelInboundHandler<Object> {

    public static ChannelGroup channels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {  // (2)
        Channel incoming = ctx.channel();
        for (Channel channel : channels) {
            channel.writeAndFlush("[SERVER] - " + incoming.remoteAddress() + " 加入");
        }
        channels.add(ctx.channel());
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {  // (3)
        Channel incoming = ctx.channel();
        for (Channel channel : channels) {
            channel.writeAndFlush("[SERVER] - " + incoming.remoteAddress() + " 离开");
        }
        channels.remove(ctx.channel());
    }
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object s) throws Exception { // (4)
        Message msg=(Message)s;
        if (msg == null) {
            return;
        }
        msg.channel=ctx.channel();
        Service service=Server.service(msg.cmd);
        service.setChannel(msg.channel);
        service.filter(msg);

//        msg.channel=ctx.channel();
//        LoginJob.getInstance().put(msg);
//        System.out.println("Client read:" + (Message) s + "channelsize:" + channels.size());

//        System.out.println("Client read:" + (Message)s + "channelsize:" + channels.size());
//        Channel incoming = ctx.channel();
//        for (Channel channel : channels) {
//            if (channel != incoming){
//                channel.writeAndFlush("[" + incoming.remoteAddress() + "]" + s);
//            } else {
//                channel.writeAndFlush(MessageHelper.time_check());
//                //channel.writeAndFlush((Object)("[you]" + s));
//            }
//        }
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception { // (5)
        Channel incoming = ctx.channel();
        System.out.println("Client:"+incoming.remoteAddress()+"在线");
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception { // (6)
        Channel incoming = ctx.channel();
        System.out.println("Client:"+incoming.remoteAddress()+"掉线");
    }
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        Channel incoming = ctx.channel();
        System.out.println("Client:"+incoming.remoteAddress()+"异常");
        // 当出现异常就关闭连接
        cause.printStackTrace();
        ctx.close();
    }
}
