package com.gty.dsr.service;

import java.util.List;

import com.gty.dsr.beanfactory.BeanFactory;
import com.gty.dsr.dao.BranchDAO;
import com.gty.dsr.dao.RecordDAO;
import com.gty.dsr.domain.Branch;

public final class BranchService {
	private BranchService() {
	}

	public static Branch getBranchById(int id) {
		BranchDAO dao = BeanFactory.getBranchDAO();
		return dao.getBranchById(id);
	}

	public static Branch getBranchByBranchName(String name) {
		BranchDAO dao = BeanFactory.getBranchDAO();
		return dao.getBranchByBranchName(name);
	}

	public static List<Branch> getAllBranches() {
		BranchDAO dao = BeanFactory.getBranchDAO();
		return dao.getAllBranches();
	}

	public static String addBranch(Branch branch) {
		String validationResult = validateNewBranch(branch);

		if (validationResult.equalsIgnoreCase("success")) {
			BranchDAO branchDao = BeanFactory.getBranchDAO();
			branchDao.addBranch(branch);
		}

		return validationResult;
	}

	public static String updateBranch(Branch branch) {
		String validationResult = validateBranchUpdate(branch);

		if (validationResult.equalsIgnoreCase("success")) {
			BranchDAO branchDao = BeanFactory.getBranchDAO();
			
			Branch currentBranch = branchDao.getBranchById(branch.getId());
			String currentBranchName = currentBranch.getName();
			String newBranchName = branch.getName();
			
			RecordDAO recordDao = BeanFactory.getRecordDAO();
			recordDao.updateBranchesOfRecords(currentBranchName, newBranchName);
			
			branchDao.updateBranch(branch);
		}

		return validationResult;
	}

	private static String validateNewBranch(Branch branch) {
		String validationResult = "success";

		BranchDAO dao = BeanFactory.getBranchDAO();
		Branch existingBranch = dao.getBranchByBranchName(branch.getName());
		if (existingBranch != null) {
			validationResult = "Branch name already exists. ";
		} else if ("".equalsIgnoreCase(branch.getName())) {
			validationResult = "Branch name is mandatory. ";
		}

		return validationResult;
	}

	private static String validateBranchUpdate(Branch branch) {
		String validationResult = "success";

		BranchDAO dao = BeanFactory.getBranchDAO();
		Branch existingBranch = dao.getBranchByBranchName(branch.getName());
		if ((existingBranch != null) && (existingBranch.getId() != branch.getId())) {
			validationResult = "Branch name already exists. ";
		} else if ("".equalsIgnoreCase(branch.getName())) {
			validationResult = "Branch name is mandatory. ";
		}

		return validationResult;
	}
}
