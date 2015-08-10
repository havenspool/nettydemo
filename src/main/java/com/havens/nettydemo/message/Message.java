package com.havens.nettydemo.message;

import com.havens.nettydemo.entity.DBObject;
import flex.messaging.io.SerializationContext;
import flex.messaging.io.amf.Amf3Input;
import flex.messaging.io.amf.Amf3Output;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import org.json.JSONObject;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by havens on 15-8-7.
 */
public class Message extends JSONObject implements Serializable{
    public String cmd;
    public Map data;

    public static void main(String[] args) throws Exception {
        Message msg = new Message();
        msg.cmd = "time_check";
        msg.data = new HashMap();
//        data.put("ctime",System.currentTimeMillis()/1000);
//        msg.data=data;
        msg.data.put("ctime",System.currentTimeMillis()/1000);
        msg.put(msg.cmd, msg.data);
//        System.out.println(data.toString());
        System.out.println(msg.toString());

        Message msg2 = new Message();
        msg2.doDecode();
    }

    public ByteBuf doEncode() throws Exception {
        Message msg = new Message();
        msg.cmd = "time_check";
        msg.data= new HashMap();
        msg.put(msg.cmd, msg.data);

        System.out.println("doEncode:"+msg);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        Amf3Output amf3Output = new Amf3Output(SerializationContext.getSerializationContext());
        amf3Output.setOutputStream(out);
        amf3Output.writeObject(msg);

        byte[] content = out.toByteArray();
        ByteBuf buf= Unpooled.copiedBuffer(content);
        buf.clear();
//		ByteBuf buf = ByteBuf.buffer(content.length+4);
        //buf.writeInt(content.length);
        System.out.println("doEncode size:" + out.size());
        System.out.println("doEncode content:" + new String(content, "UTF-8"));
        buf.writeBytes(content);
        amf3Output.flush();
        amf3Output.close();
        System.out.println("doEncode buf:" + buf.readableBytes());
        return buf;
    }

    public void doDecode() throws Exception {
        ByteBuf in=doEncode();
        List<Object> out=new ArrayList<Object>();
        System.out.println("doDecode:" + in.readableBytes());
        if(in.readableBytes()>0){
            byte[] content = new byte[in.readableBytes()];
            in.readBytes(content);
            System.out.println("doDecode content:" + new String(content, "UTF-8"));
            Amf3Input amf3Input = new Amf3Input(SerializationContext.getSerializationContext());
            amf3Input.setInputStream(new ByteArrayInputStream(content));
            Object decoded= amf3Input.readObject();
            amf3Input.close();
            if (decoded != null) {
                out.add(decoded);
                System.out.println("doDecode decoded:" + decoded);
            }
        }
    }
}
