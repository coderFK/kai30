package com.kai30.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.TreeSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.kai30.javabean.ClassBean;
import com.kai30.javabean.PackageBean;
import com.kai30.util.MyStringUtil;

public class SelectFromJDBC implements Select{
	private TreeSet<PackageBean> pbList = 
			new TreeSet<PackageBean>();
	private TreeSet<ClassBean> cbList = 
			new TreeSet<ClassBean>();
	
	public SelectFromJDBC(String key, boolean isCaseSensitive) {
		super();
		
		if(!MyStringUtil.isInvalidKey(key)){
			searchKey(key, isCaseSensitive);
		}
	}

	private void searchKey(String key, boolean isCaseSensitive) {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement packageState = null;
		PreparedStatement classState = null;
		try {
			conn = getDataSource().getConnection();
			packageState = conn.prepareStatement("select id, name, src from java6_package");
			ResultSet packageResult = packageState.executeQuery();
			while(packageResult.next()){
				int id = packageResult.getInt(1);
				String name = packageResult.getString(2);
				String src = packageResult.getString(3);
				if(name!=null){
					String key2 = key;
					String name2 = name;
					if(!isCaseSensitive){
						key2 = key.toLowerCase();
						name2 = name.toLowerCase();
					}
					
					if(name2.contains(key2) || key2.contains(name2)){
						pbList.add(new PackageBean(id, name, src));
					}
				}
			}
			
			classState = conn.prepareStatement("select id, name, packageName, src from java6_class");
			ResultSet classResult = classState.executeQuery();
			while(classResult.next()){
				int id = classResult.getInt(1);
				String name = classResult.getString(2);
				String packageName = classResult.getString(3);
				String src = classResult.getString(4);
				if(name!=null){
					String key2 = key;
					String name2 = name;
					if(!isCaseSensitive){
						key2 = key.toLowerCase();
						name2 = name.toLowerCase();
					}
					
					if(name2.contains(key2) || key2.contains(name2)){
						cbList.add(new ClassBean(id, name, packageName, src));
					}
				}
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			try {
				if(classState!=null){
					classState.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				if(packageState!=null){
					packageState.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				if(conn!=null){
					conn.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public TreeSet<PackageBean> getPbList() {
		return pbList;
	}

	public TreeSet<ClassBean> getCbList() {
		return cbList;
	}
	
	private DataSource getDataSource(){
		DataSource dataSource = null;
		try {
			Context context = new InitialContext();
			Context envContext = (Context) context.lookup("java:/comp/env");
			dataSource = (DataSource) envContext.lookup("jdbc/kai30");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return dataSource;
	}
}
