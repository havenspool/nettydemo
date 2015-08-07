package com.havens.nettydemo.server;

import com.havens.nettydemo.codec.MessageDecoder;
import com.havens.nettydemo.codec.MessageEncoder;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

/**
 * Created by havens on 15-8-7.
 */
public class ServerChannelHandler extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
//        ch.pipeline().addLast("framer", new DelimiterBasedFrameDecoder(8192, Delimiters.lineDelimiter()));
        ch.pipeline().addLast("decoder", new MessageDecoder());
        ch.pipeline().addLast("encoder", new MessageEncoder());
        ch.pipeline().addLast(new ServerHandler());
        System.out.println("Client:" + ch.remoteAddress() + "连接上");
    }
}
