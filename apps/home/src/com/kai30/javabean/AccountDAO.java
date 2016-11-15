package com.kai30.javabean;

public interface AccountDAO {

	void createUserData(String email, String name, String password);

	boolean isUserExisted(String name);

	boolean checkLoginIsOk(String name, String password);

	Account getAccount(String name);
}
