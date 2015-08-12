package com.havens.nettydemo.client;

import com.havens.nettydemo.entity.Hero;
import com.havens.nettydemo.entity.User;
import com.havens.nettydemo.message.Message;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by havens on 15-8-7.
 */
public class ClientHandler extends SimpleChannelInboundHandler<Object> {
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception { // (5)
        Channel incoming = ctx.channel();
        User user=new User();
        user.id=10001;
        user.name="havens";
        user.pwd="123456";

        Message msg2 = new Message();
        msg2.cmd = "login";
        Map data2 = new HashMap();
        data2.put("id",user.id);
        data2.put("name",user.name);
        data2.put("pwd",user.pwd);
        msg2.data=data2;
        incoming.writeAndFlush(msg2);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object s) throws Exception {
        try {
            Message msg=(Message)s;
            if(msg.data!=null) {
                System.out.println("data from server:" + msg.data);
                Thread.sleep(10000);
                Message result = new Message();
                result.cmd = "time_check";
                result.data= new HashMap();
                if("login".equals(msg.cmd)||"time_check".equals(msg.cmd)){
                    ctx.channel().writeAndFlush(result);
                }
            }
        }catch (Exception e){
           // e.printStackTrace();
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