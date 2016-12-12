package com.kai30.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.LinkedList;

import javax.sql.DataSource;

import com.kai30.javabean.AccountLog;
import com.kai30.model.LogDAO;

public class SaveLogInJDBC implements LogDAO {
	DataSource dataSource;
	
	public SaveLogInJDBC(DataSource dataSource) {
		super();
		this.dataSource = dataSource;
	}

	@Override
	public void addAccountLog(AccountLog accountLog) {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement state = null;
		try {
			conn = dataSource.getConnection();
			state = conn.prepareStatement(
					"insert into account_log(username, message, date) values(?, ?, ?)");
			state.setString(1, accountLog.getUsername());
			state.setString(2, accountLog.getMessage());
			state.setTimestamp(3, new Timestamp(accountLog.getDate().getTime()));
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			if(state != null){
				try {
					state.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(conn != null){
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public LinkedList<AccountLog> getAccountLogs() {
		// TODO Auto-generated method stub
		LinkedList<AccountLog> accountLogs = new LinkedList<AccountLog>();
		
		Connection conn = null;
		PreparedStatement state = null;
		try {
			conn = dataSource.getConnection();
			state = conn.prepareStatement(
					"select username, message, date from account_log");
			ResultSet result = state.executeQuery();
			while(result.next()){
				AccountLog accountLog = new AccountLog(
						result.getString(1), result.getString(2), new Date(result.getDate(1).getTime()));
				accountLogs.add(accountLog);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			if(state != null){
				try {
					state.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(conn != null){
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return accountLogs;
	}
	
	
	
}
