package com.gty.dsr.domain;

public class Bank {
	private int id;
	private String name;
	private String status;
	
	public Bank() {
	}

	public int getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public String getStatus() {
		return status;
	}

	public void setId(int id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}
