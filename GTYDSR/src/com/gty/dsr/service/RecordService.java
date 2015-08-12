package com.gty.dsr.service;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

import com.gty.dsr.beanfactory.BeanFactory;
import com.gty.dsr.dao.BranchDAO;
import com.gty.dsr.dao.RecordDAO;
import com.gty.dsr.domain.Branch;
import com.gty.dsr.domain.Record;

public final class RecordService {
	private RecordService() {
	}

	public static Record getRecordById(int id) {
		RecordDAO dao = BeanFactory.getRecordDAO();
		return dao.getRecordById(id);
	}

	public static BigDecimal getPreviousRecordAcoh(Record record) {
		RecordDAO dao = BeanFactory.getRecordDAO();
		Record previousRecord = dao.getPreviousRecord(record);
		if(previousRecord != null) {
			return previousRecord.getAcoh();
		} else {
			return new BigDecimal(0);
		}
	}

	public static Record getRecordByBranchAndDate(String branch, Date date) {
		RecordDAO dao = BeanFactory.getRecordDAO();
		return dao.getRecordByBranchAndDate(branch, date);
	}

	public static List<Record> getAllRecords() {
		RecordDAO dao = BeanFactory.getRecordDAO();
		return dao.getAllRecords();
	}
	
	public static String addRecord(Record record) {
		String validationResult = validateNewRecord(record);

		if (validationResult.equalsIgnoreCase("success")) {
			BranchDAO branchDao = BeanFactory.getBranchDAO();
			Branch branch = branchDao.getBranchByBranchName(record.getBranch());
			record.setBank(branch.getRemittanceBank());

			RecordDAO recordDao = BeanFactory.getRecordDAO();
			recordDao.addRecord(record);
		}

		return validationResult;
	}

	public static String updateRecord(Record record) {
		String validationResult = validateRecordUpdate(record);

		if (validationResult.equalsIgnoreCase("success")) {
			RecordDAO dao = BeanFactory.getRecordDAO();
			dao.updateRecord(record);
		}

		return validationResult;
	}

	private static String validateNewRecord(Record record) {
		String validationResult = validateRecord(record);

		RecordDAO dao = BeanFactory.getRecordDAO();
		Record existingRecord = dao.getRecordByBranchAndDate(record.getBranch(), record.getDate());
		if (existingRecord != null) {
			validationResult = "Record for this branch and date already exists. ";
		}

		return validationResult;
	}

	private static String validateRecordUpdate(Record record) {
		String validationResult = validateRecord(record);

		RecordDAO dao = BeanFactory.getRecordDAO();
		Record existingRecord = dao.getRecordByBranchAndDate(record.getBranch(), record.getDate());
		if ((existingRecord != null) && (existingRecord.getId() != record.getId())) {
			validationResult = "Record for this branch and date already exists. ";
		}

		return validationResult;
	}
	
	private static String validateRecord(Record record) {
		String validationResult = "success";
		
		if (record.getBranch() == "") {
			validationResult = "Branch cannot be empty. ";
		} else if (record.getDate() == null) {
			validationResult = "Date cannot be empty";
		} else  if (record.getConsignment() < 0) {
			validationResult = "Consignment value cannot be less than zero. ";
		} else if (record.getOverdue() < 0) {
			validationResult = "Overdue value cannot be less than zero. ";
		} else if (record.getAdvanced() < 0) {
			validationResult = "Advanced value cannot be less than zero. ";
		} else if (record.getOpenConsignment() < 0) {
			validationResult = "Open Consignment value cannot be less than zero. ";
		} else if (record.getDueConsignment() < 0) {
			validationResult = "Due Consignment value cannot be less than zero. ";
		} else if (record.getNewConsignment() < 0) {
			validationResult = "New Consignment value cannot be less than zero. ";
		} else if (record.getSales() == null) {
			validationResult = "Sales value cannot be empty or have an incorrect amount format. ";
		} else if (record.getSales().compareTo(BigDecimal.ZERO) < 0) {
			validationResult = "Sales value cannot be less than zero. ";
		} else if (record.getExpense() == null) {
			validationResult = "Expense value cannot be empty or have an incorrect amount format. ";
		} else if (record.getExpense().compareTo(BigDecimal.ZERO) < 0) {
			validationResult = "Expense value cannot be less than zero. ";
		} else if (record.getDeposit() == null) {
			validationResult = "Deposit value cannot be empty or have an incorrect amount format. ";
		} else if (record.getDeposit().compareTo(BigDecimal.ZERO) < 0) {
			validationResult = "Deposit value cannot be less than zero. ";
		} else if (record.getPcoh() == null) {
			validationResult = "PCOH value cannot be empty or have an incorrect amount format. ";
		} else if (record.getAcoh() == null) {
			validationResult = "ACOH value cannot be empty or have an incorrect amount format. ";
		} else if (record.getDiff() == null) {
			validationResult = "Diff value cannot be empty or have an incorrect amount format. ";
		} else if (record.getDiscrepancyAmount().compareTo(BigDecimal.ZERO) < 0) {
			validationResult = "Discrepancy Amount value cannot be less than zero. ";
		} else if (record.getDiscrepancyAmount() == null) {
			validationResult = "Discrepancy Amount value cannot be empty or have an incorrect amount format. ";
		} else if ((!record.getDiscrepancyType().equals("None")) || (!record.getDiscrepancyCategory().equals("None")) || (record.getDiscrepancyAmount().compareTo(BigDecimal.ZERO) > 0)) {
			if (record.getDiscrepancyType().equals("None")) {
				validationResult = "Discrepancy Type cannot be none if Category is not 'None' or Amount is not zero. ";
			} else if (record.getDiscrepancyCategory().equals("None")) {
				validationResult = "Discrepancy Category cannot be none if Type is not 'None' or Amount is not zero. ";
			} else if (record.getDiscrepancyAmount().compareTo(BigDecimal.ZERO) == 0) {
				validationResult = "Discrepancy Amount cannot be none if Category or Type is not 'None'. ";
			}
		}

		return validationResult;
	}
}
