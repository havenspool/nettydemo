package com.havens.nettydemo.entity;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by havens on 15-8-10.
 */
public class User extends DBObject{
    public int id;
    public String name;
    public String pwd;

    public static void main(String[] args) {
        User user=new User();
        user.id=10001;
        user.name="havens";
        user.pwd="123456";
        System.out.println(user.toString());
    }

}
