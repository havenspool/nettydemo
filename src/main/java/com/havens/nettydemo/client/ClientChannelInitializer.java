package com.havens.nettydemo.client;

import com.havens.nettydemo.message.Message;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;

/**
 * Created by havens on 15-8-6.
 */
public class ClientChannelInitializer extends ChannelInitializer<SocketChannel> {
    private Client client;

    public ClientChannelInitializer(Client client) {
        this.client = client;
    }
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ch.pipeline().addLast(new StringEncoder(CharsetUtil.UTF_8),
                new LineBasedFrameDecoder(1024),
                new StringDecoder(CharsetUtil.UTF_8));
        ch.pipeline().addLast(new ClientService(client));
    }
}

class ClientService  extends SimpleChannelInboundHandler<Message>{
    private Client client;

    public ClientService(Client client) {
        this.client = client;
    }

    @Override
    protected void messageReceived(ChannelHandlerContext channelHandlerContext, Message message) throws Exception {
        client.sendHello("fuck the GFW");
    }
}
