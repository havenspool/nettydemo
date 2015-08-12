package com.havens.nettydemo.message;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by havens on 15-8-11.
 */
public class MessageHelper {

    public static Message time_check(){
        Message TIME_CHECK=new Message();
        TIME_CHECK.cmd="time_check";
        TIME_CHECK.data=new HashMap();
        TIME_CHECK.data.put("ctime",System.currentTimeMillis()/1000);
        return TIME_CHECK;
    }

    public static Message login(Message msg){
        Message msg2 = new Message();
        msg2.cmd = msg.cmd;
        Map data2 = new HashMap();
        if(msg.data!=null){
            data2.put("id",msg.data.get("id"));
            data2.put("name",msg.data.get("name"));
            data2.put("pwd",msg.data.get("pwd"));
        }
        data2.put("result",true);
        msg2.data=data2;
        return msg2;
    }
}
