package com.gty.dsr.domain;

public class Branch {
	private int id;
	private String name;
	private String location;
	private String remittanceBank;
	private String proprietor;
	private String contactNumber;
	private String status;
	
	public Branch() {
	}

	public int getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public String getLocation() {
		return location;
	}
	public String getRemittanceBank() {
		return remittanceBank;
	}
	public String getProprietor() {
		return proprietor;
	}
	public String getContactNumber() {
		return contactNumber;
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
	public void setLocation(String location) {
		this.location = location;
	}
	public void setRemittanceBank(String remittanceBank) {
		this.remittanceBank = remittanceBank;
	}
	public void setProprietor(String proprietor) {
		this.proprietor = proprietor;
	}
	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}