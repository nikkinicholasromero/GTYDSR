package com.gty.dsr.beanfactory;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.gty.dsr.dao.BankDAO;
import com.gty.dsr.dao.BranchDAO;
import com.gty.dsr.dao.DiscrepancyDAO;
import com.gty.dsr.dao.RecordDAO;

public final class BeanFactory {
	private static ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");

	private BeanFactory() {
	}

	public static BankDAO getBankDAO() {
		return context.getBean(BankDAO.class, "bankDAO");
	}
	public static BranchDAO getBranchDAO() {
		return context.getBean(BranchDAO.class, "branchDAO");
	}
	public static DiscrepancyDAO getDiscrepancyDAO() {
		return context.getBean(DiscrepancyDAO.class, "discrepancyDAO");
	}
	public static RecordDAO getRecordDAO() {
		return context.getBean(RecordDAO.class, "recordDAO");
	}
}
