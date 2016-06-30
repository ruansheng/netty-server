package com.entry;

public class Message {
	
	/**
	 * 消息id
	 */
	private String id;
	
	/**
	 * 消息action
	 */
	private String action;
	
	/**
	 * 消息内容
	 */
	private String text;
	
	/**
	 * 消息接收方
	 */
	private String to;
	
	/**
	 * 消息类型
	 */
	private int type;
	
	/**
	 * 消息接受方
	 */
	private String fr;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getFr() {
		return fr;
	}

	public void setFr(String fr) {
		this.fr = fr;
	}
	
}
