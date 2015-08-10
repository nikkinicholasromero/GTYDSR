package com.gty.dsr.service;

import java.util.List;

import com.gty.dsr.beanfactory.BeanFactory;
import com.gty.dsr.dao.BankDAO;
import com.gty.dsr.dao.BranchDAO;
import com.gty.dsr.dao.RecordDAO;
import com.gty.dsr.domain.Bank;

public final class BankService {
	private BankService() {
	}

	public static Bank getBankById(int id) {
		BankDAO dao = BeanFactory.getBankDAO();
		return dao.getBankById(id);
	}

	public static Bank getBankByBankName(String name) {
		BankDAO dao = BeanFactory.getBankDAO();
		return dao.getBankByBankName(name);
	}

	public static List<Bank> getAllBanks() {
		BankDAO dao = BeanFactory.getBankDAO();
		return dao.getAllBanks();
	}

	public static String addBank(Bank bank) {
		String validationResult = validateNewBank(bank);

		if (validationResult.equalsIgnoreCase("success")) {
			BankDAO dao = BeanFactory.getBankDAO();
			dao.addBank(bank);
		}

		return validationResult;
	}

	public static String updateBank(Bank bank) {
		String validationResult = validateBankUpdate(bank);

		if (validationResult.equalsIgnoreCase("success")) {
			BankDAO bankDao = BeanFactory.getBankDAO();
			Bank currentBank = bankDao.getBankById(bank.getId());
			String currentBankName = currentBank.getName();
			String newBankName= bank.getName();
			
			BranchDAO branchDao = BeanFactory.getBranchDAO();
			branchDao.updateBankOfBranches(currentBankName, newBankName);
			
			RecordDAO recordDao = BeanFactory.getRecordDAO();
			recordDao.updateBankOfRecords(currentBankName, newBankName);
			
			bankDao.updateBank(bank);
		}

		return validationResult;
	}

	private static String validateNewBank(Bank bank) {
		String validationResult = "success";

		BankDAO dao = BeanFactory.getBankDAO();
		Bank existingBank = dao.getBankByBankName(bank.getName());
		if (existingBank != null) {
			validationResult = "Bank name already exists. ";
		} else if ("".equalsIgnoreCase(bank.getName())) {
			validationResult = "Bank name is mandatory. ";
		}

		return validationResult;
	}

	private static String validateBankUpdate(Bank bank) {
		String validationResult = "success";

		BankDAO dao = BeanFactory.getBankDAO();
		Bank existingBank = dao.getBankByBankName(bank.getName());
		if ((existingBank != null) && (existingBank.getId() != bank.getId())) {
			validationResult = "Bank name already exists. ";
		} else if ("".equalsIgnoreCase(bank.getName())) {
			validationResult = "Bank name is mandatory. ";
		}

		return validationResult;
	}
}
