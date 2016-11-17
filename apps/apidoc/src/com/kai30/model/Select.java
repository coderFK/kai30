package com.kai30.model;

import java.util.TreeSet;

import com.kai30.javabean.ClassBean;
import com.kai30.javabean.PackageBean;

public interface Select {
	TreeSet<PackageBean> getPbList();
	TreeSet<ClassBean> getCbList();
}
