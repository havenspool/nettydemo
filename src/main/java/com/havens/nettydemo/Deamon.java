package com.havens.nettydemo;

import com.havens.nettydemo.job.LoginJob;
import com.havens.nettydemo.server.Server;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by havens on 15-8-7.
 */
public class Deamon{

    public static void main(String[] args) throws Exception {
        new Server().run();
//        ExecutorService exec = Executors.newCachedThreadPool();
//        exec.execute(LoginJob.getInstance());
    }
}
