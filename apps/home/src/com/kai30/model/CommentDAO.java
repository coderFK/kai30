package com.kai30.model;


import java.util.TreeSet;

import com.kai30.javabean.Comment;

public interface CommentDAO {
	void saveComment(Comment comment);
	
	TreeSet<Comment> getComments();
}
