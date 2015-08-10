package com.gty.dsr.utility;

import java.util.Comparator;

import com.gty.dsr.domain.Bank;

public class BankComparator implements Comparator<Bank> {
	@Override
	public int compare(Bank o1, Bank o2) {
		return o1.getName().compareTo(o2.getName());
	}
}