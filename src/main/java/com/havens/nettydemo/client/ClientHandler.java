package com.havens.nettydemo.client;

import com.havens.nettydemo.entity.Hero;
import com.havens.nettydemo.message.Message;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Map;

/**
 * Created by havens on 15-8-7.
 */
public class ClientHandler extends SimpleChannelInboundHandler<Object> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object s) throws Exception {
        try {
            Message msg=(Message)s;
            if(msg.data!=null)
                System.out.println("data from server:"+msg.data);
        }catch (Exception e){

        }
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