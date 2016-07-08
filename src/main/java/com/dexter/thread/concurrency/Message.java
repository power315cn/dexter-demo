package com.dexter.thread.concurrency;

/** 
 * @ClassName:	Message 
 * @Description:
 * 一个java bean类，线程将会使用它并调用wait和notify方法。
 * @author:	dexter
 * @date:	2016年7月8日 下午4:19:22 
 *  
 */
public class Message {
	private String msg;

	public Message(String str) {
		this.msg = str;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String str) {
		this.msg = str;
	}

}