package com.gty.dsr.service;

import java.util.List;

import com.gty.dsr.beanfactory.BeanFactory;
import com.gty.dsr.dao.BranchDAO;
import com.gty.dsr.dao.RecordDAO;
import com.gty.dsr.domain.Branch;

public final class BranchService {
	private static final BranchDAO branchDao = BeanFactory.getBranchDAO();
	private static final RecordDAO recordDao = BeanFactory.getRecordDAO();
	
	private BranchService() {
	}

	public static Branch getBranchById(int id) {
		return branchDao.getBranchById(id);
	}

	public static Branch getBranchByBranchName(String name) {
		return branchDao.getBranchByBranchName(name);
	}

	public static List<Branch> getAllBranches() {
		return branchDao.getAllBranches();
	}

	public static String addBranch(Branch branch) {
		String validationResult = validateNewBranch(branch);

		if (validationResult.equalsIgnoreCase("success")) {
			branchDao.addBranch(branch);
		}

		return validationResult;
	}

	public static String updateBranch(Branch branch) {
		String validationResult = validateBranchUpdate(branch);

		if (validationResult.equalsIgnoreCase("success")) {
			Branch currentBranch = branchDao.getBranchById(branch.getId());
			String currentBranchName = currentBranch.getName();
			String newBranchName = branch.getName();
			recordDao.updateBranchesOfRecords(currentBranchName, newBranchName);
			
			branchDao.updateBranch(branch);
		}

		return validationResult;
	}

	private static String validateNewBranch(Branch branch) {
		String validationResult = "success";

		Branch existingBranch = branchDao.getBranchByBranchName(branch.getName());
		if (existingBranch != null) {
			validationResult = "Branch name already exists. ";
		} else if ("".equalsIgnoreCase(branch.getName())) {
			validationResult = "Branch name is mandatory. ";
		}

		return validationResult;
	}

	private static String validateBranchUpdate(Branch branch) {
		String validationResult = "success";

		Branch existingBranch = branchDao.getBranchByBranchName(branch.getName());
		if ((existingBranch != null) && (existingBranch.getId() != branch.getId())) {
			validationResult = "Branch name already exists. ";
		} else if ("".equalsIgnoreCase(branch.getName())) {
			validationResult = "Branch name is mandatory. ";
		}

		return validationResult;
	}
}
