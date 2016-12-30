package com.kai30.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.sql.DataSource;

import com.kai30.javabean.Bookmark;

public class SaveBookmarkInJDBC implements BookmarkDAO{
	DataSource dataSource;
	public SaveBookmarkInJDBC(DataSource dataSource) {
		this.dataSource = dataSource; 
	}

	@Override
	public LinkedList<Bookmark> getBookmarks(Bookmark bookmark) {
		
		Connection conn = null;
		PreparedStatement state = null;
		LinkedList<Bookmark> bookmarks = new LinkedList<Bookmark>();
		Exception err = null;
		String username = bookmark.getUsername();
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
				bookmarks.add(new Bookmark(username, date, url, title, imgUrl));
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
	public void saveBookmark(LinkedList<Bookmark> bookmarks) {
		Connection conn = null;
		PreparedStatement state = null;
		Exception err = null;
		try {
			conn = dataSource.getConnection();
			state = conn.prepareStatement(
					"insert into user_bookmark(username, date, url, title, imgUrl) values(?, ?, ?, ?, ?)");
			for (Bookmark bookmark : bookmarks) {
				state.setString(1, bookmark.getUsername());
				state.setTimestamp(2, new Timestamp(bookmark.getDate().getTime()));
				state.setString(3, bookmark.getUrl());
				state.setString(4, bookmark.getTitle());
				state.setString(5, bookmark.getImgUrl());
				state.addBatch();
			}
			state.executeBatch();
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

	@Override
	public void deleteBookmark(Bookmark bookmark) {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement state = null;
		SQLException err = null;
		try {
			conn = dataSource.getConnection();
			state = conn.prepareStatement(
					"delete from user_bookmark where date=? and username=?");
			state.setTimestamp(1, new Timestamp( bookmark.getDate().getTime()));
			state.setString(2, bookmark.getUsername());
			state.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			err = e;
		}
		finally{
			try {
				if(state!=null){
					state.close();
				}
				if(conn!=null){
					conn.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				if(err != null){
					err = e;
				}
			}
		}
		if(err!=null){
			throw new RuntimeException(err);
		}
	}

	@Override
	public List<Bookmark> getSearchResult(Bookmark bookmark, String searchBookmarkKey) {
		Connection conn = null;
		PreparedStatement state = null;
		SQLException err = null;
		LinkedList<Bookmark> bookmarks = new LinkedList<Bookmark>();
		try {
			conn = dataSource.getConnection();
			state = conn.prepareStatement(
					"select date, url, title, imgUrl from user_bookmark where username=?");
			state.setString(1, bookmark.getUsername());
			ResultSet result = state.executeQuery();
			while(result.next()){
				String title = result.getString(3);
				if(title.contains(searchBookmarkKey)){
					Date date = new Date(result.getTimestamp(1).getTime());
					String url = result.getString(2);
					String imgUrl = result.getString(4);
					bookmarks.addFirst(new Bookmark(bookmark.getUsername(), date, url, title, imgUrl));
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			err = e;
		}
		finally{
			try {
				if(state!=null){
					state.close();
				}
				if(conn!=null){
					conn.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				if(err != null){
					err = e;
				}
			}
		}
		if(err!=null){
			throw new RuntimeException(err);
		}
		
		return bookmarks;
	}

	@Override
	public void deleteAllBookmark(Bookmark bookmark) {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement state = null;
		SQLException err = null;
		try {
			conn = dataSource.getConnection();
			state = conn.prepareStatement(
					"delete from user_bookmark where username=?");
			state.setString(1, bookmark.getUsername());
			state.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			err = e;
		}
		finally{
			try {
				if(state!=null){
					state.close();
				}
				if(conn!=null){
					conn.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				if(err != null){
					err = e;
				}
			}
		}
		if(err!=null){
			throw new RuntimeException(err);
		}
	}

}
