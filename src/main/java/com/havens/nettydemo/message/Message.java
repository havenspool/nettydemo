package com.havens.nettydemo.message;

import com.havens.nettydemo.entity.DBObject;
import io.netty.channel.Channel;
import org.json.JSONObject;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by havens on 15-8-7.
 */
public class Message extends DBObject {
    public int code;
    public String cmd;
    public Channel channel;
    public Map data;

    public static void main(String[] args) throws Exception {
        Message msg = new Message();
        msg.cmd = "time_check";
        msg.data= new HashMap();
        msg.data.put("ctime",System.currentTimeMillis()/1000);
        System.out.println(msg.data);
    }

}
