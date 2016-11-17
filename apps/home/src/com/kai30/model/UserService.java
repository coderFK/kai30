package com.kai30.model;

import java.util.List;

import com.kai30.javabean.Account;
import com.kai30.javabean.Comment;

public class UserService {
	AccountDAO accountDAO;
	CommentDAO commentDAO;
	public UserService(AccountDAO accountDAO, CommentDAO commentDAO) {
		super();
		this.accountDAO = accountDAO;
		this.commentDAO = commentDAO;
	}
	
	public Account getAccount(String name){
		return accountDAO.getAccount(name);
	}
	public boolean checkLoginIsOk(String name, String password){
		return accountDAO.checkLoginIsOk(name, password);
	}
	public boolean isUserExisted(String name) {
		return accountDAO.isUserExisted(name);
	}
	public void createUserData(String email, String name, String password){
		accountDAO.createUserData(email, name, password);
	}
	
	public boolean isInvalidEmail(String email) {
		return email == null || !email.matches("^[a-zA-Z0-9_-]+@[a-zA-Z0-9-]+[.][a-zA-Z0-9-]+$");
	}
	public boolean isInvalidePassword(String password, String confirmedPasswd) {
		// TODO Auto-generated method stub
		return password == null||password.length()<6 || password.length()>16 || !password.equals(confirmedPasswd);
	}
	
	public void saveComment(Comment comment){
		commentDAO.saveComment(comment);
	}
	public List<Comment> getComments(){
		return commentDAO.getComments();
	}
	
}
