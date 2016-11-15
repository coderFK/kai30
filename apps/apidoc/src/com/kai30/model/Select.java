package com.kai30.model;

import java.util.TreeSet;

import com.kai30.bean.ClassBean;
import com.kai30.bean.PackageBean;

public interface Select {
	TreeSet<PackageBean> getPbList();
	TreeSet<ClassBean> getCbList();
}
