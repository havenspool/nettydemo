package com.havens.nettydemo.service;

import com.havens.nettydemo.message.Message;
import com.havens.nettydemo.runs.CmdHandler;
import flex.messaging.Server;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.socket.SocketChannel;

import java.util.HashMap;
import java.util.List;

/**
 * Created by havens on 15-8-6.
 */
public class Service extends CmdHandler {

    @Override
    public void service(Message msg) {
        sayHello(msg);
    }

    private void sayHello(Message msg) {
        System.err.println("message from client:"+msg);
        Message helloMsg = new Message();
        HashMap msgObj = new HashMap();
        msgObj.put("cmd", "say_hello");
        msgObj.put("result", true);
        msgObj.put("time", System.currentTimeMillis() / 1000);
        helloMsg.setSendMap(msgObj);
        msg.channel.writeAndFlush(helloMsg);// 发送消息
    }
}
