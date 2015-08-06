package com.havens.nettydemo.runs;

import com.havens.nettydemo.message.Message;

public abstract class CmdHandler {
	public abstract void service(Message msg);
}
