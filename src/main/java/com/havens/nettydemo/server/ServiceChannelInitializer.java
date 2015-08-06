package com.havens.nettydemo.server;

import com.havens.nettydemo.Deamon;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;

/**
 * Created by havens on 15-8-6.
 */
public class ServiceChannelInitializer extends ChannelInitializer<SocketChannel> {
    private Deamon deamon;

    public ServiceChannelInitializer(Deamon deamon) {
        this.deamon = deamon;
    }
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        System.out.println("initChannel");
//        ch.pipeline().addLast(new StringEncoder(CharsetUtil.UTF_8),
//                new LineBasedFrameDecoder(1024),
//                new StringDecoder(CharsetUtil.UTF_8));
        ch.pipeline().addLast(new ServerHandler());
    }


}
