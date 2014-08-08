package com.maple.scs.util;

import java.io.Serializable;

public class MessageBean implements Serializable{
	
	private String msg;
	
	private String msgType;

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getMsgType() {
		return msgType;
	}

	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}

}
