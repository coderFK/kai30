package com.kai30.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.TreeSet;

import com.kai30.javabean.ClassBean;
import com.kai30.javabean.PackageBean;

import java.util.TreeSet;

public class SelectFromWEB implements Select{
	private TreeSet<PackageBean> pbList = new TreeSet<PackageBean>();
	private TreeSet<ClassBean> cbList = new TreeSet<ClassBean>();
	private String packageUrl = "http://tool.oschina.net/uploads/apidocs/jdk-zh/overview-frame.html";
	private String classUrl = "http://tool.oschina.net/uploads/apidocs/jdk-zh/allclasses-frame.html";
	
	public SelectFromWEB(String key, boolean isNoCareUpCase) {
		super();
		
		if(!isInvalidKey(key)){
			searchKey(key, isNoCareUpCase);
		}
	}

	private void searchKey(String key, boolean isNoCareUpCase) {
		
	}
	@Override
	public TreeSet<PackageBean> getPbList() {
		// TODO Auto-generated method stub
		return pbList;
	}

	@Override
	public TreeSet<ClassBean> getCbList() {
		// TODO Auto-generated method stub
		return cbList;
	}

	private TreeSet<PackageBean> getAllPackages(){
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(
					new FileInputStream(new File(packageUrl))));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	private TreeSet<ClassBean> getAllClasses(){
		
		return null;
	}
	
	private boolean isInvalidKey(String key){
		if(key==null){
			return true;
		}
		else{
			//将字符串首尾的空格去除
			key = key.trim();
		}
		//如果之前的关键字是空字符串或者全为空格，则搜索无效
		if(key.length()==0){
			return true;
		}
		return false;
	}
}
