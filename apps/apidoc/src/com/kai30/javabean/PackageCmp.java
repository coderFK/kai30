package com.kai30.javabean;

import java.util.Comparator;

public class PackageCmp implements Comparator<PackageBean>{
	@Override
	public int compare(PackageBean o1, PackageBean o2) {
		// TODO Auto-generated method stub
		int cmp = o1.getName().length() - o2.getName().length();
		return cmp;
	}

}
