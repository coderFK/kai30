package com.kai30.bean;

import java.io.Serializable;

public class PackageBean implements Serializable, Comparable<PackageBean>{
	private int id;
	private String name;
	private String src;
	
	PackageBean(){
		super();
	}
	
	
	public PackageBean(int id, String name, String src) {
		super();
		this.id = id;
		this.name = name;
		this.src = src;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSrc() {
		return src;
	}
	public void setSrc(String src) {
		this.src = src;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@Override
	public int compareTo(PackageBean pb) {
		// TODO Auto-generated method stub
		int cmp = this.getName().length() - pb.getName().length();
		return cmp;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		PackageBean other = (PackageBean) obj;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (src == null) {
			if (other.src != null)
				return false;
		} else if (!src.equals(other.src))
			return false;
		return true;
	}
	
	
	
}
