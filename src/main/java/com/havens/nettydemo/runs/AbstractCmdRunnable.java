package com.havens.nettydemo.runs;

import com.havens.nettydemo.message.Message;
import com.havens.nettydemo.queue.BaseQueue;

import java.util.concurrent.TimeUnit;

public abstract class AbstractCmdRunnable implements Runnable {
	private BaseQueue<Message> INSTANCE;

	public AbstractCmdRunnable(BaseQueue<Message> INSTANCE) {
		this.INSTANCE = INSTANCE;
	}

	public void run() {
		for (;;) {
			try {
				Message msg = null;
				while (INSTANCE.getQueueSize() > 0) {
					msg = INSTANCE.take();
					if (msg != null) {
						service(msg);
					}
				}
				TimeUnit.MILLISECONDS.sleep(50);// 如果已经取完则让给其他线程一些时间片
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public abstract void service(Message msg);

}
