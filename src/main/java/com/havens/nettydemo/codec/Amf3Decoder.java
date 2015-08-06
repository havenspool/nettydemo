package com.havens.nettydemo.codec;

import flex.messaging.io.SerializationContext;
import flex.messaging.io.amf.Amf3Input;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;

import java.io.ByteArrayInputStream;

/**
 * Created by havens on 15-8-6.
 */
public class Amf3Decoder extends ActionScriptObject{

    protected Object decode(ChannelHandlerContext ctx, Channel channel,
                            ByteBuf buffer) throws Exception {
        if(buffer.readableBytes()<4){
            return null;
        }
        buffer.markReaderIndex();
        int needBytes = buffer.readInt();
        if(buffer.readableBytes()<needBytes){
            buffer.resetReaderIndex();
            return null;
        }
        byte[] content = new byte[buffer.readableBytes()];
        buffer.readBytes(content);
        Amf3Input amf3Input = new Amf3Input(SerializationContext.getSerializationContext());
        amf3Input.setInputStream(new ByteArrayInputStream(content));
        return amf3Input.readObject();
    }

}
