package com.havens.nettydemo.message;

import io.netty.channel.Channel;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by havens on 15-8-6.
 */
public class Message {
    public Channel channel;// 当前玩家的channel

    public static Message TIME_OUT = new Message();

    static {
        HashMap msgObj = new HashMap();
        msgObj.put("cmd", "time_out");
        msgObj.put("result", false);
        msgObj.put("comment", "服务器繁忙.");
        TIME_OUT.setSendMap(msgObj);
    }

    public static Message HEART_CHECK = new Message();
    static {
        HashMap msgObj = new HashMap();
        msgObj.put("cmd", "heart_check");
        msgObj.put("result", true);
        msgObj.put("time", System.currentTimeMillis()/1000);
        TIME_OUT.setSendMap(msgObj);
    }

    private Map sendMap;

    public Map getSendMap() {
        return this.sendMap;
    }

    public void setSendMap(Map sendMap) {
        this.sendMap = sendMap;
    }

    public static byte[] mapToBytes(Map sendMap) {
        if (sendMap==null || sendMap.size() < 0)
            return null;
        try {
            ByteArrayOutputStream bo = new ByteArrayOutputStream();
            ObjectOutputStream os = new ObjectOutputStream(bo);
            os.writeObject(sendMap);
            return bo.toByteArray();
        } catch (IOException e) {
            return null;
        }
    }

}
