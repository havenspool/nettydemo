package com.havens.nettydemo;

import com.havens.nettydemo.server.Server;
import io.netty.channel.Channel;

import java.io.IOException;
import com.havens.nettydemo.message.Message;

/**
 * Created by havens on 15-8-11.
 */
public abstract class Service {
    protected String cmd;

    protected Channel channel;

    public String getCmd() {
        return cmd;
    }

    public void setCmd(String cmd) {
        this.cmd = cmd;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }
    public Channel getChannel() {
        return this.channel;
    }

    protected void write(Message msg) throws IOException {
        channel.writeAndFlush(msg);
    }
    public final void service(final Message msg) throws Exception {
        if (beforeFilter(msg)) {
            filter(msg);
            afterFilter();
        }
    }

    public boolean beforeFilter(final Message msg) throws Exception {
        return true;
    }

    public void afterFilter() throws Exception {
    }

    public abstract void filter(Message msg) throws Exception;
}
