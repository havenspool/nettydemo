package com.havens.nettydemo.client;

import com.havens.nettydemo.entity.User;
import com.havens.nettydemo.message.Message;
import com.havens.nettydemo.message.UDPMessage;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.DatagramPacket;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by havens on 15-8-17.
 */
public class UDPClientHandler extends SimpleChannelInboundHandler<DatagramPacket> {
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Channel incoming = ctx.channel();
        Message req = new Message();
        req.cmd = "time_check";
        req.data= new HashMap();
        incoming.writeAndFlush(UDPMessage.send(req));
        System.out.println("UDPClientHandler channelActive:" + req);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, DatagramPacket packet) throws Exception {
        Message msg= UDPMessage.receive(packet);
        if (msg == null||msg.data==null) {
            return;
        }
        System.out.println("UDPClientHandler channelRead0:" + msg);
    }
}