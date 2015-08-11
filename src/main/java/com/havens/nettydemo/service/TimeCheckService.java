package com.havens.nettydemo.service;

import com.havens.nettydemo.Service;
import com.havens.nettydemo.message.Message;
import com.havens.nettydemo.message.MessageHelper;

/**
 * Created by havens on 15-8-11.
 */
public class TimeCheckService extends Service {

    @Override
    public void filter(Message msg) throws Exception {
        channel.writeAndFlush(MessageHelper.time_check());
    }
}
