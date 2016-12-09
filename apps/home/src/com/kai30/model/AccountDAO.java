package com.kai30.model;

import java.util.List;

import com.kai30.javabean.Account;

public interface AccountDAO {

	void createUserData(String email, String name, String password);

	boolean isUserExisted(String name);

	boolean checkLoginIsOk(String name, String password);
	
	boolean checkUserIsMaster(String username);

	Account getAccount(String name);
	
	List<Account> getAccounts();

	void modifyPassword(String name, String password);
}
