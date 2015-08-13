package com.gty.dsr.service;

import java.util.List;

import com.gty.dsr.beanfactory.BeanFactory;
import com.gty.dsr.dao.BankDAO;
import com.gty.dsr.dao.BranchDAO;
import com.gty.dsr.dao.RecordDAO;
import com.gty.dsr.domain.Bank;

public final class BankService {
	private static final BankDAO bankDao = BeanFactory.getBankDAO();
	private static final BranchDAO branchDao = BeanFactory.getBranchDAO();
	private static final RecordDAO recordDao = BeanFactory.getRecordDAO();
	
	private BankService() {
	}

	public static Bank getBankById(int id) {
		return bankDao.getBankById(id);
	}

	public static Bank getBankByBankName(String name) {
		return bankDao.getBankByBankName(name);
	}

	public static List<Bank> getAllBanks() {
		return bankDao.getAllBanks();
	}

	public static List<Bank> getAllActiveBanks() {
		return bankDao.getAllActiveBanks();
	}

	public static String addBank(Bank bank) {
		String validationResult = validateNewBank(bank);

		if (validationResult.equalsIgnoreCase("success")) {
			bankDao.addBank(bank);
		}

		return validationResult;
	}

	public static String updateBank(Bank bank) {
		String validationResult = validateBankUpdate(bank);

		if (validationResult.equalsIgnoreCase("success")) {
			Bank currentBank = bankDao.getBankById(bank.getId());
			String currentBankName = currentBank.getName();
			String newBankName= bank.getName();
			branchDao.updateBankOfBranches(currentBankName, newBankName);
			recordDao.updateBankOfRecords(currentBankName, newBankName);
			bankDao.updateBank(bank);
		}

		return validationResult;
	}

	private static String validateNewBank(Bank bank) {
		String validationResult = "success";

		Bank existingBank = bankDao.getBankByBankName(bank.getName());
		if (existingBank != null) {
			validationResult = "Bank name already exists. ";
		} else if ("".equalsIgnoreCase(bank.getName())) {
			validationResult = "Bank name is mandatory. ";
		}

		return validationResult;
	}

	private static String validateBankUpdate(Bank bank) {
		String validationResult = "success";

		Bank existingBank = bankDao.getBankByBankName(bank.getName());
		if ((existingBank != null) && (existingBank.getId() != bank.getId())) {
			validationResult = "Bank name already exists. ";
		} else if ("".equalsIgnoreCase(bank.getName())) {
			validationResult = "Bank name is mandatory. ";
		}

		return validationResult;
	}
}
