package com.havens.nettydemo.codec;

import com.havens.nettydemo.message.Message;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

/**
 * Created by havens on 15-8-6.
 */
public class Amf3Encoder extends ActionScriptObject{

    protected ByteBuf encode(Message msg) throws Exception {
        if(msg==null||msg.getSendMap()==null){
            return null;
        }
        ByteBuf sendbuf= Unpooled.buffer();
        sendbuf.writeBytes(msg.mapToBytes(msg.getSendMap()));
        return sendbuf;
    }
}
