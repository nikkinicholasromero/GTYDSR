package com.gty.dsr.utility;

import java.util.Comparator;

import com.gty.dsr.domain.Discrepancy;

public class DiscrepancyComparator implements Comparator<Discrepancy> {
	@Override
	public int compare(Discrepancy o1, Discrepancy o2) {
		return o1.getType().compareTo(o2.getType());
	}
}