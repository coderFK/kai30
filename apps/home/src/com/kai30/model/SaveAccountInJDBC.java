package com.kai30.model;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.kai30.javabean.Account;

public class SaveAccountInJDBC implements AccountDAO{

	private DataSource dataSource;
	
	public SaveAccountInJDBC(DataSource dataSource) {
		super();
		this.dataSource = dataSource;
	}

	@Override
	public void createUserData(String email, String name, String password) {
		Connection conn = null;
		PreparedStatement state = null;
		PreparedStatement state2 = null;
		SQLException err = null;
		try {
			conn = dataSource.getConnection();
			state = conn.prepareStatement("insert into account(username, password, email) values(?, ?, ?)");
			//分组用
			state2 = conn.prepareStatement("insert into account_role(username, role) values(?, 'member')");
			state.setString(1, name);
			state.setString(2, password);
			state.setString(3, email);
			state2.setString(1, name);
			conn.setAutoCommit(false);
			state.executeUpdate();
			state2.executeUpdate();
			conn.commit();
		} catch (SQLException e) {
			err = e;
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		finally{
			try {
				if(state!=null){
					state.close();
				}
				if(state2!=null){
					state.close();
				}
				if(conn!=null){
					conn.setAutoCommit(true);
					conn.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				if(err != null){
					err = e;
				}
			}
		}
		if(err!=null){
			throw new RuntimeException(err);
		}
	}

	@Override
	public boolean isUserExisted(String name) {
		boolean isExisted = false;
		Connection conn = null;
		PreparedStatement state = null;
		SQLException err = null;
		try {
			conn = dataSource.getConnection();
			state = conn.prepareStatement("select * from account where username=?");
			state.setString(1, name);
			ResultSet result = state.executeQuery();
			if(result.next()){
				isExisted = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			err = e;
		}
		finally{
			try {
				if(state!=null){
					state.close();
				}
				if(conn!=null){
					conn.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				if(err != null){
					err = e;
				}
			}
		}
		if(err!=null){
			throw new RuntimeException(err);
		}
		return isExisted;
	}

	@Override
	public boolean checkLoginIsOk(String name, String password) {
		boolean isOk = false;
		Connection conn = null;
		PreparedStatement state = null;
		SQLException err = null;
		try {
			conn = dataSource.getConnection();
			state = conn.prepareStatement("select password from account where username=?");
			state.setString(1, name);
			ResultSet result = state.executeQuery();
			if(result.next()){
				isOk = (password.equals(result.getString(1)));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			err = e;
		}
		finally{
			try {
				if(state!=null){
					state.close();
				}
				if(conn!=null){
					conn.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				if(err != null){
					err = e;
				}
			}
		}
		if(err!=null){
			throw new RuntimeException(err);
		}
		return isOk;
	}

	@Override
	public Account getAccount(String name) {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement state = null;
		SQLException err = null;
		Account account = null;
		try {
			conn = dataSource.getConnection();
			state = conn.prepareStatement("select password, email from account where username=?");
			state.setString(1, name);
			ResultSet result = state.executeQuery();
			if(result.next()){
				account = new Account( name, result.getString(1), result.getString(2));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			err = e;
		}
		finally{
			try {
				if(state!=null){
					state.close();
				}
				if(conn!=null){
					conn.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				if(err != null){
					err = e;
				}
			}
		}
		if(err!=null){
			throw new RuntimeException(err);
		}
		return account;
	}
	
}
