package com.havens.nettydemo.codec;

import java.io.ByteArrayOutputStream;
import java.util.List;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;


import flex.messaging.io.SerializationContext;
import flex.messaging.io.amf.Amf3Output;
import io.netty.handler.codec.MessageToMessageEncoder;

public class Amf3Encoder extends MessageToByteEncoder{

//	@Override
//	protected Object encode(ChannelHandlerContext ctx, Channel channel,
//			Object msg) throws Exception {
//		ByteArrayOutputStream out = new ByteArrayOutputStream();
//		Amf3Output amf3Output = new Amf3Output(SerializationContext.getSerializationContext());
//		amf3Output.setOutputStream(out);
//		amf3Output.writeObject(msg);
//
//		byte content[] = out.toByteArray();
//		ChannelBuffer buf = ChannelBuffers.buffer(content.length+4);
//		buf.writeInt(content.length);
//		buf.writeBytes(content);
//		return buf;
//	}
	@Override
	protected void encode(ChannelHandlerContext ctx, Object msg, ByteBuf buf) throws Exception {
		System.out.println("encode:"+msg);
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		Amf3Output amf3Output = new Amf3Output(SerializationContext.getSerializationContext());
		amf3Output.setOutputStream(out);
		amf3Output.writeObject(msg);

		byte[] content = out.toByteArray();
		buf.clear();
//		ByteBuf buf = ByteBuf.buffer(content.length+4);
		buf.writeInt(content.length);
		System.out.println("encode size:" + out.size());
		buf.writeBytes(content);
		amf3Output.flush();
		amf3Output.close();
		System.out.println("encode buf:" + buf.readableBytes());
	}
}
