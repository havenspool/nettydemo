package com.havens.nettydemo.message;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by havens on 15-8-7.
 */
public class Message extends JSONObject {
    public String cmd;
    public Map data;


    public static void main(String[] args){
        Message msg=new Message();
        msg.cmd="time_check";
        msg.data=new HashMap();
        msg.put(msg.cmd,msg.data);
        System.out.println(msg.toString());
    }
}
