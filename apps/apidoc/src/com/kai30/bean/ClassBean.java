package com.kai30.bean;

import java.io.Serializable;

public class ClassBean implements Serializable, Comparable<ClassBean>{
	int id;
	String name;
	String packageName;
	String src;
	
	ClassBean(){
		super();
	}
	
	public ClassBean(int id, String name, String packageName, String src) {
		super();
		this.id = id;
		this.name = name;
		this.packageName = packageName;
		this.src = src;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPackageName() {
		return packageName;
	}
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}
	public String getSrc() {
		return src;
	}
	public void setSrc(String src) {
		this.src = src;
	}

	@Override
	public int compareTo(ClassBean cb) {
		// TODO Auto-generated method stub
		int cmp = this.getName().length() - cb.getName().length();
		if(cmp == 0){
			cmp = this.getPackageName().length() - cb.getPackageName().length();
		}
		return cmp;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((packageName == null) ? 0 : packageName.hashCode());
		result = prime * result + ((src == null) ? 0 : src.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ClassBean other = (ClassBean) obj;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (packageName == null) {
			if (other.packageName != null)
				return false;
		} else if (!packageName.equals(other.packageName))
			return false;
		if (src == null) {
			if (other.src != null)
				return false;
		} else if (!src.equals(other.src))
			return false;
		return true;
	}
	
	
	
}
