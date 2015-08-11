package com.havens.nettydemo.codec;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import flex.messaging.io.amf.Amf3Output;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.Unpooled;
import io.netty.buffer.UnpooledByteBufAllocator;
import io.netty.channel.ChannelHandlerContext;


import flex.messaging.io.SerializationContext;
import flex.messaging.io.amf.Amf3Input;
import com.havens.nettydemo.message.Message;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.handler.codec.MessageToMessageDecoder;


public class Amf3Decoder extends ByteToMessageDecoder{

	@Override
	protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
		//System.out.println("decode:" + in.readableBytes());
		if(in.readableBytes()>4){
			in.markReaderIndex();
			int needBytes = in.readInt();
			//System.out.println("needBytes:" + needBytes);
			if(in.readableBytes()>=needBytes){
				byte[] content = new byte[in.readableBytes()];
				in.readBytes(content);

				Amf3Input amf3Input = new Amf3Input(SerializationContext.getSerializationContext());
				InputStream bais = new ByteArrayInputStream(content);
				amf3Input.setInputStream(bais);
				Object decoded=amf3Input.readObject();
				amf3Input.close();

				if (decoded != null) {
					out.add(decoded);
					//System.out.println("decoded:" + decoded);
				}
			}else{
				in.resetReaderIndex();
			}
		}
	}
}
