package com.kai30.model;

import java.util.LinkedList;

import com.kai30.javabean.AccountLog;

public interface LogDAO {
	public void addAccountLog(AccountLog accountLog);
	
	public LinkedList<AccountLog> getAccountLogs();
}
