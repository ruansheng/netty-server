package com.entry;

public class Login {
	
	/**
	 * 消息action
	 */
	private String action;
		
	/**
	 * 用户id
	 */
	private String uid;

	public String get_() {
		return action;
	}

	public void set_(String action) {
		this.action = action;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}
}
