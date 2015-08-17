package com.havens.nettydemo.server;

import com.havens.nettydemo.Service;
import com.havens.nettydemo.message.Message;
import com.havens.nettydemo.message.UDPMessage;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.DatagramPacket;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.util.Map;

/**
 * Created by havens on 15-8-17.
 */
public class UDPSeverHandler extends SimpleChannelInboundHandler<DatagramPacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, DatagramPacket packet) throws Exception {
        Message msg= UDPMessage.receive(packet);
        System.out.println("UDPSeverHandler channelRead0:" + msg);
        if (msg == null||msg.data==null) {
            return;
        }

        if(msg.data instanceof Map){
            msg.channel=ctx.channel();
            Service service=Server.service(msg.cmd);
            service.setChannel(msg.channel);
            service.filter(msg.data);
        }
        System.out.println("UDPSeverHandler channelRead0:" + msg);
    }
}
