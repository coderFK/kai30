package com.kai30.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.TreeSet;

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
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = dataSource.getConnection();
			ps = conn.prepareStatement("insert into user_comment(username, date, content) values(?, ?, ?)");
			ps.setString(1, comment.getUsername());
			ps.setTimestamp(2, new Timestamp(comment.getDate().getTime()));
			ps.setString(3, comment.getContent());
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			if(ps !=null){
				try {
					ps.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(conn !=null){
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
	public TreeSet<Comment> getComments() {
		Connection conn = null;
		PreparedStatement ps = null;
		TreeSet<Comment> comments = new TreeSet<Comment>();
		try {
			conn = dataSource.getConnection();
			ps = conn.prepareStatement("select username, date, content from user_comment");
			ResultSet result = ps.executeQuery();
			while(result.next()){
				String username = result.getString(1);
				Date date = new Date(result.getTimestamp(2).getTime());
				String content = result.getString(3);	
				comments.add(new Comment(username, date, content));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			if(ps !=null){
				try {
					ps.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(conn !=null){
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return comments;
	}

}
