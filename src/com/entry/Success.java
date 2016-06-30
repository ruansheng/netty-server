package com.entry;

public class Success {
	
	/**
	 * 消息action
	 */
	private String action;
		
	/**
	 * 状态
	 */
	private int ec;
	
	/**
	 * 消息
	 */
	private String em;

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getEm() {
		return em;
	}

	public void setEm(String em) {
		this.em = em;
	}

	public int getEc() {
		return ec;
	}

	public void setEc(int ec) {
		this.ec = ec;
	}
}
