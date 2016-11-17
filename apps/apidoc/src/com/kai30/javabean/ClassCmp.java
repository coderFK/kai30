package com.kai30.javabean;

import java.util.Comparator;

public class ClassCmp implements Comparator<ClassBean>{
	@Override
	public int compare(ClassBean o1, ClassBean o2) {
		// TODO Auto-generated method stub
		int cmp = o1.getName().length() - o2.getName().length();
		if(cmp == 0){
			cmp = o1.getPackageName().length() - o2.getPackageName().length();
		}
		return cmp;
	}

}
