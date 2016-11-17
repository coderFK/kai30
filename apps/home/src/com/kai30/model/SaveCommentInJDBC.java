package com.kai30.model;

import java.util.LinkedList;

import javax.sql.DataSource;

import com.kai30.javabean.Comment;

public class SaveCommentInJDBC implements CommentDAO{

	DataSource dataSource;
	
	public SaveCommentInJDBC(DataSource dataSource) {
		super();
		this.dataSource = dataSource;
	}

	@Override
	public void saveComment(Comment comment) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public LinkedList<Comment> getComments() {
		// TODO Auto-generated method stub
		return null;
	}

}
