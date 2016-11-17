package com.kai30.model;

import java.util.LinkedList;

import com.kai30.javabean.Comment;

public interface CommentDAO {
	void saveComment(Comment comment);
	
	LinkedList<Comment> getComments();
}
