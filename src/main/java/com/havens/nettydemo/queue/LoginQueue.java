package com.havens.nettydemo.queue;
import com.havens.nettydemo.message.Message;
/**
 * Created by havens on 15-8-6.
 */
public class LoginQueue extends BaseQueue<Message> {
    private static final LoginQueue INSTANCE = new LoginQueue();

    private LoginQueue() {
    }

    public static LoginQueue getInstance() {
        return INSTANCE;
    }

}
