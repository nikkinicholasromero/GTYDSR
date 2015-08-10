package com.gty.dsr.utility;

import java.util.Comparator;

import com.gty.dsr.domain.Branch;

public class BranchComparator implements Comparator<Branch> {
	@Override
	public int compare(Branch o1, Branch o2) {
		return o1.getName().compareTo(o2.getName());
	}
}