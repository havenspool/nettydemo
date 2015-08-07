package com.havens.nettydemo.message;

import org.json.JSONObject;

import java.util.Map;

/**
 * Created by havens on 15-8-7.
 */
public class Message extends JSONObject {
    public String cmd;
    public Map data;
}
