package com.gty.dsr.domain;

public class Discrepancy {
	private int id;
	private String type;
	private String description;
	
	public Discrepancy() {
	}

	public int getId() {
		return id;
	}
	public String getType() {
		return type;
	}
	public String getDescription() {
		return description;
	}

	public void setId(int id) {
		this.id = id;
	}
	public void setType(String type) {
		this.type = type;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}
