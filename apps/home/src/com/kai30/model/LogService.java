package com.kai30.model;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import com.kai30.javabean.AccountLog;

public class LogService {

	LogDAO logDAO;
	
	public LogService(LogDAO logDAO) {
		super();
		this.logDAO = logDAO;
	}

	public void accountRegister(String username){
		logDAO.addAccountLog(new AccountLog(username, "用户注册", new Date()));
	}
	
	public void accountLogin(String username){
		logDAO.addAccountLog(new AccountLog(username, "用户登陆", new Date()));
	}
	
	public void accountLogout(String username){
		logDAO.addAccountLog(new AccountLog(username, "用户退出", new Date()));
	}
	
	public void accountModifyPassword(String username){
		logDAO.addAccountLog(new AccountLog(username, "修改密码", new Date()));
	}
	
	public void accountFindPassword(String username){
		logDAO.addAccountLog(new AccountLog(username, "找回密码", new Date()));
	}
	
	public LinkedList<AccountLog> getAccountLogs(){
		return logDAO.getAccountLogs();
	}
	
	
}
