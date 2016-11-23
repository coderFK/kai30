package com.kai30.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.LinkedList;

import javax.sql.DataSource;

import com.kai30.javabean.Bookmark;

public class SaveBookmarkInJDBC implements BookmarkDAO{
	DataSource dataSource;
	public SaveBookmarkInJDBC(DataSource dataSource) {
		this.dataSource = dataSource; 
	}

	@Override
	public LinkedList<Bookmark> getBookmarks(String username) {
		
		Connection conn = null;
		PreparedStatement state = null;
		LinkedList<Bookmark> bookmarks = new LinkedList<Bookmark>();
		Exception err = null;
		try {
			conn = dataSource.getConnection();
			state = conn.prepareStatement(
					"select date, url, title, imgUrl from user_bookmark where username=?");
			state.setString(1, username);
			ResultSet result = state.executeQuery();
			while(result.next()){
				Date date = new Date(result.getTimestamp(1).getTime());
				String url = result.getString(2);
				String title = result.getString(3);
				String imgUrl = result.getString(4);
				Bookmark bookmark = new Bookmark(username, date, url, title, imgUrl); 
				bookmarks.add(bookmark);
			}
			
		} catch (SQLException e) {
			if(err==null){
				err = e;
			}
		}
		finally{
			try {
				if(state!=null){
					state.close();	
				}
			} catch (SQLException e) {
				if(err==null){
					err = e;
				}
			}
			try {
				if(conn!=null){
					conn.close();	
				}
			} catch (SQLException e) {
				if(err==null){
					err = e;
				}
			}
		}
		
		return bookmarks;
		
	}

	@Override
	public void saveBookmark(Bookmark bookmark) {
		Connection conn = null;
		PreparedStatement state = null;
		Exception err = null;
		try {
			conn = dataSource.getConnection();
			state = conn.prepareStatement(
					"insert into user_bookmark(username, date, url, title, imgUrl) values(?, ?, ?, ?, ?)");
			state.setString(1, bookmark.getUsername());
			state.setTimestamp(2, new Timestamp(bookmark.getDate().getTime()));
			state.setString(3, bookmark.getUrl());
			state.setString(4, bookmark.getTitle());
			state.setString(5, bookmark.getImgUrl());
			state.executeUpdate();
			
		} catch (SQLException e) {
			if(err==null){
				err = e;
			}
		}
		finally{
			try {
				if(state!=null){
					state.close();	
				}
			} catch (SQLException e) {
				if(err==null){
					err = e;
				}
			}
			try {
				if(conn!=null){
					conn.close();	
				}
			} catch (SQLException e) {
				if(err==null){
					err = e;
				}
			}
		}
		
	}

}
