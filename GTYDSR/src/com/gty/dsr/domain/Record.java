package com.gty.dsr.domain;

import java.math.BigDecimal;
import java.sql.Date;

public class Record {
	private int id;
	private String branch;
	private String bank;
	private Date date;
	private int consignment;
	private int overdue;
	private int advanced;
	private int openConsignment;
	private int dueConsignment;
	private int newConsignment;
	private BigDecimal sales;
	private BigDecimal expense;
	private BigDecimal deposit;
	private BigDecimal ocoh;
	private BigDecimal pcoh;
	private BigDecimal acoh;
	private BigDecimal diff;
	private String discrepancyType;
	private String discrepancyCategory;
	private BigDecimal discrepancyAmount;
	private String additionalInformation;
	
	public Record() {
	}

	public int getId() {
		return id;
	}
	public String getBranch() {
		return branch;
	}
	public String getBank() {
		return bank;
	}
	public Date getDate() {
		return date;
	}
	public int getConsignment() {
		return consignment;
	}
	public int getOverdue() {
		return overdue;
	}
	public int getAdvanced() {
		return advanced;
	}
	public int getOpenConsignment() {
		return openConsignment;
	}
	public int getDueConsignment() {
		return dueConsignment;
	}
	public int getNewConsignment() {
		return newConsignment;
	}
	public BigDecimal getSales() {
		return sales;
	}
	public BigDecimal getExpense() {
		return expense;
	}
	public BigDecimal getDeposit() {
		return deposit;
	}
	public BigDecimal getOcoh() {
		return ocoh;
	}
	public BigDecimal getPcoh() {
		return pcoh;
	}
	public BigDecimal getAcoh() {
		return acoh;
	}
	public BigDecimal getDiff() {
		return diff;
	}
	public String getDiscrepancyType() {
		return discrepancyType;
	}
	public String getDiscrepancyCategory() {
		return discrepancyCategory;
	}
	public BigDecimal getDiscrepancyAmount() {
		return discrepancyAmount;
	}
	public String getAdditionalInformation() {
		return additionalInformation;
	}

	public void setId(int id) {
		this.id = id;
	}
	public void setBranch(String branch) {
		this.branch = branch;
	}
	public void setBank(String bank) {
		this.bank = bank;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public void setConsignment(int consignment) {
		this.consignment = consignment;
	}
	public void setOverdue(int overdue) {
		this.overdue = overdue;
	}
	public void setAdvanced(int advanced) {
		this.advanced = advanced;
	}
	public void setOpenConsignment(int openConsignment) {
		this.openConsignment = openConsignment;
	}
	public void setDueConsignment(int dueConsignment) {
		this.dueConsignment = dueConsignment;
	}
	public void setNewConsignment(int newConsignment) {
		this.newConsignment = newConsignment;
	}
	public void setSales(BigDecimal sales) {
		this.sales = sales;
	}
	public void setExpense(BigDecimal expense) {
		this.expense = expense;
	}
	public void setDeposit(BigDecimal deposit) {
		this.deposit = deposit;
	}
	public void setOcoh(BigDecimal ocoh) {
		this.ocoh = ocoh;
	}
	public void setPcoh(BigDecimal pcoh) {
		this.pcoh = pcoh;
	}
	public void setAcoh(BigDecimal acoh) {
		this.acoh = acoh;
	}
	public void setDiff(BigDecimal diff) {
		this.diff = diff;
	}
	public void setDiscrepancyType(String discrepancyType) {
		this.discrepancyType = discrepancyType;
	}
	public void setDiscrepancyCategory(String discrepancyCategory) {
		this.discrepancyCategory = discrepancyCategory;
	}
	public void setDiscrepancyAmount(BigDecimal discrepancyAmount) {
		this.discrepancyAmount = discrepancyAmount;
	}
	public void setAdditionalInformation(String additionalInformation) {
		this.additionalInformation = additionalInformation;
	}
}
