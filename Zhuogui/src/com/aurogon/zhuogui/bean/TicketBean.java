package com.aurogon.zhuogui.bean;

import java.io.Serializable;

public class TicketBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5680324091996951264L;

	private String name;
	
	private String from;
	
	private int count;
	
	public TicketBean() {
	}
	
	
	
	public String getFrom() {
		return from;
	}



	public void setFrom(String from) {
		this.from = from;
	}



	public TicketBean(String from, String name) {
		super();
		this.name = name;
		this.from = from;
		this.count = 1;
	}



	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
	public void addCount() {
		this.count = this.count + 1;
	}
	
	

}
