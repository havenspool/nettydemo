package com.havens.nettydemo.service;

import com.havens.nettydemo.Service;
import com.havens.nettydemo.message.Message;
import com.havens.nettydemo.message.MessageHelper;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by havens on 15-8-11.
 */
public class LoginService extends Service {
    @Override
    public void filter(Message msg) throws Exception {
        channel.writeAndFlush(MessageHelper.login(msg));
    }
}
