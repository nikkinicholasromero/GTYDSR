package com.gty.dsr.service;

import java.util.List;

import com.gty.dsr.beanfactory.BeanFactory;
import com.gty.dsr.dao.DiscrepancyDAO;
import com.gty.dsr.dao.RecordDAO;
import com.gty.dsr.domain.Discrepancy;

public final class DiscrepancyService {
	private static final DiscrepancyDAO discrepancyDao = BeanFactory.getDiscrepancyDAO();
	private static final RecordDAO recordDao = BeanFactory.getRecordDAO();
	
	private DiscrepancyService() {
	}

	public static Discrepancy getDiscrepancyById(int id) {
		return discrepancyDao.getDiscrepancyById(id);
	}

	public static Discrepancy getDiscrepancyByDiscrepancyType(String type) {
		return discrepancyDao.getDiscrepancyByDiscrepancyType(type);
	}

	public static List<Discrepancy> getAllDiscrepancies() {
		return discrepancyDao.getAllDiscrepancies();
	}

	public static String addDiscrepancy(Discrepancy discrepancy) {
		String validationResult = validateNewDiscrepancy(discrepancy);

		if (validationResult.equalsIgnoreCase("success")) {
			discrepancyDao.addDiscrepancy(discrepancy);
		}

		return validationResult;
	}

	public static String updateDiscrepancy(Discrepancy discrepancy) {
		String validationResult = validateDiscrepancyUpdate(discrepancy);

		if (validationResult.equalsIgnoreCase("success")) {
			Discrepancy currentDiscrepancy = discrepancyDao.getDiscrepancyById(discrepancy.getId());
			String currentDiscrepancyName = currentDiscrepancy.getType();
			String newDiscrepancyName = discrepancy.getType();
			recordDao.updateDiscrepancyOfRecords(currentDiscrepancyName, newDiscrepancyName);
			discrepancyDao.udpateDiscrepancy(discrepancy);
		}

		return validationResult;
	}

	private static String validateNewDiscrepancy(Discrepancy discrepancy) {
		String validationResult = "success";

		Discrepancy existingDiscrepancy = discrepancyDao.getDiscrepancyByDiscrepancyType(discrepancy.getType());
		if (existingDiscrepancy != null) {
			validationResult = "Discrepancy type already exists. ";
		} else if ("".equalsIgnoreCase(discrepancy.getType())) {
			validationResult = "Discrepancy type is mandatory. ";
		}

		return validationResult;
	}

	private static String validateDiscrepancyUpdate(Discrepancy discrepancy) {
		String validationResult = "success";

		Discrepancy existingDiscrepancy = discrepancyDao.getDiscrepancyByDiscrepancyType(discrepancy.getType());
		if ((existingDiscrepancy != null) && (existingDiscrepancy.getId() != discrepancy.getId())) {
			validationResult = "Discrepancy type already exists. ";
		} else if ("".equalsIgnoreCase(discrepancy.getType())) {
			validationResult = "Discrepancy type is mandatory. ";
		}

		return validationResult;
	}
}
