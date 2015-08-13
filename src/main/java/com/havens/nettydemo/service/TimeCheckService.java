package com.havens.nettydemo.service;

import com.havens.nettydemo.Service;
import com.havens.nettydemo.message.Message;
import com.havens.nettydemo.message.MessageHelper;

import java.util.Map;

/**
 * Created by havens on 15-8-11.
 */
public class TimeCheckService extends Service {

    @Override
    public void filter(Map map) throws Exception {
        write(MessageHelper.time_check());
    }
}
