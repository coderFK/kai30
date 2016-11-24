package com.kai30.model;

import java.util.LinkedList;
import java.util.Set;

import com.kai30.javabean.Account;
import com.kai30.javabean.Bookmark;
import com.kai30.javabean.Comment;

public class UserService {
	AccountDAO accountDAO;
	CommentDAO commentDAO;
	BookmarkDAO bookmarkDAO;
	

	public UserService(AccountDAO accountDAO, CommentDAO commentDAO, BookmarkDAO bookmarkDAO) {
		super();
		this.accountDAO = accountDAO;
		this.commentDAO = commentDAO;
		this.bookmarkDAO = bookmarkDAO;
	}
	
	public Account getAccount(String username){
		return accountDAO.getAccount(username);
	}
	public boolean checkLoginIsOk(String username, String password){
		return accountDAO.checkLoginIsOk(username, password);
	}
	public boolean isUserExisted(String username) {
		return accountDAO.isUserExisted(username);
	}
	public void createUserData(String email, String username, String password){
		accountDAO.createUserData(email, username, password);
	}
	public void modifyPassword(String username, String password){
		accountDAO.modifyPassword(username, password);
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
	public Set<Comment> getComments(){
		return commentDAO.getComments();
	}
	
	public LinkedList<Bookmark> getBookmarks(String username) {
		return bookmarkDAO.getBookmarks(username);
	}
	public void saveBookmark(LinkedList<Bookmark> bookmarks) {
		bookmarkDAO.saveBookmark(bookmarks);
	}
	public void deleteBookmark(Bookmark bookmark){
		bookmarkDAO.deleteBookmark(bookmark);
	}
}
