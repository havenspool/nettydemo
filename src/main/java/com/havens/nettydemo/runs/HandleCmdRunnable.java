package com.havens.nettydemo.runs;


import com.havens.nettydemo.message.Message;
import com.havens.nettydemo.queue.BaseQueue;
import com.havens.nettydemo.service.Service;

public class HandleCmdRunnable extends AbstractCmdRunnable {

	public HandleCmdRunnable(BaseQueue<Message> INSTANCE) {
		super(INSTANCE);
	}

	private static Service service = new Service();// 这里命令码较少.暂时放置到同一个handler中.如果较多最好按照cmdCode分别放置存入hashMap中

	/**
	 * 处理消息的核心方法
	 * */
	@Override
	public void service(Message msg) {
		service.service(msg);
	}
}
