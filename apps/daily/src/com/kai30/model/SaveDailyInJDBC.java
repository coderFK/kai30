package com.kai30.model;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.sql.DataSource;

import com.kai30.javabean.Content;
import com.kai30.javabean.Daily;

public class SaveDailyInJDBC implements DailyDAO{

private DataSource dataSource;
	
	public SaveDailyInJDBC(DataSource dataSource) {
		super();
		this.dataSource = dataSource;
	}

	@Override
	public List<Daily> getDailys(Daily daily) {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement state = null;
		SQLException err = null;
		LinkedList<Daily> dailys = new LinkedList<Daily>();
		try {
			conn = dataSource.getConnection();
			state = conn.prepareStatement(
					"select date, content, title, subject from daily where username=?");
			state.setString(1, daily.getUsername());
			ResultSet result = state.executeQuery();
			while(result.next()){
				dailys.addFirst(new Daily(daily.getUsername(), 
						result.getTimestamp(1), new Content(result.getString(2)), 
						result.getString(3), result.getString(4)));
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
		
		return dailys;
	}

	@Override
	public void addDaily(Daily daily) {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement state = null;
		SQLException err = null;
		try {
			conn = dataSource.getConnection();
			state = conn.prepareStatement(
					"insert into daily(username, date, content, title, subject) values(?, ?, ?, ?, ?)");
			state.setString(1, daily.getUsername());
			state.setTimestamp(2, new Timestamp( daily.getDate().getTime()));
			state.setString(3, daily.getContent().toString());
			state.setString(4, daily.getTitle());
			state.setString(5, daily.getSubject());
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
	public void deleteDaily(Daily daily) {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement state = null;
		SQLException err = null;
		try {
			conn = dataSource.getConnection();
			state = conn.prepareStatement(
					"delete from daily where date=? and username=?");
			state.setTimestamp(1, new Timestamp( daily.getDate().getTime()));
			state.setString(2, daily.getUsername());
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
	public List<Daily> getSubjectDailys(Daily daily) {
		Connection conn = null;
		PreparedStatement state = null;
		SQLException err = null;
		LinkedList<Daily> dailys = new LinkedList<Daily>();
		try {
			conn = dataSource.getConnection();
			state = conn.prepareStatement(
					"select date, content, title, subject from daily where username=? and subject=?");
			state.setString(1, daily.getUsername());
			state.setString(2, daily.getSubject());
			ResultSet result = state.executeQuery();
			while(result.next()){
				dailys.addFirst(new Daily(daily.getUsername(), 
						result.getTimestamp(1), new Content(result.getString(2)), 
						result.getString(3), result.getString(4)));
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
		
		return dailys;
	}

	@Override
	public Set<String> getSubjects(Daily daily) {
		Connection conn = null;
		PreparedStatement state = null;
		SQLException err = null;
		Set<String> subjects = new TreeSet<String>();
		try {
			conn = dataSource.getConnection();
			state = conn.prepareStatement(
					"select subject from daily where username=?");
			state.setString(1, daily.getUsername());
			ResultSet result = state.executeQuery();
			while(result.next()){
				subjects.add(result.getString(1));
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
		
		return subjects;
	}

	@Override
	public void modifyDaily(Daily daily) {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement state = null;
		SQLException err = null;
		try {
			conn = dataSource.getConnection();
			state = conn.prepareStatement("update daily SET content=?, title=?, subject=? where date=? and username=?");
			state.setString(1, daily.getContent().getText());
			state.setString(2, daily.getTitle());
			state.setString(3, daily.getSubject());
			state.setTimestamp(4, new Timestamp(daily.getDate().getTime()));
			state.setString(5, daily.getUsername());
			state.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
	public Daily getDaily(Daily daily) {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement state = null;
		SQLException err = null;
		Daily targetDaily = null;
		try {
			conn = dataSource.getConnection();
			state = conn.prepareStatement(
					"select content, title, subject from daily where date=? and username=?");
			state.setTimestamp(1, new Timestamp(daily.getDate().getTime()));
			state.setString(2, daily.getUsername());
			ResultSet result = state.executeQuery();
			while(result.next()){
				targetDaily = new Daily(daily.getUsername(), 
						daily.getDate(), new Content(result.getString(1)), 
						result.getString(2), result.getString(3));
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
		
		return targetDaily;
	}

	@Override
	public List<Daily> getSearchResult(Daily daily, String searchKey) {
		Connection conn = null;
		PreparedStatement state = null;
		SQLException err = null;
		LinkedList<Daily> dailys = new LinkedList<Daily>();
		try {
			conn = dataSource.getConnection();
			state = conn.prepareStatement(
					"select date, content, title, subject from daily where username=?");
			state.setString(1, daily.getUsername());
			ResultSet result = state.executeQuery();
			while(result.next()){
				String title = result.getString(3);
				if(title.contains(searchKey)){
					dailys.addFirst(new Daily(daily.getUsername(), 
							result.getTimestamp(1), new Content(result.getString(2)), 
							result.getString(3), result.getString(4)));
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
		
		return dailys;
	}
	
	
//	public class Cmp implements Comparator<Date>{
//		@Override
//		public int compare(Date o1, Date o2) {
//			// TODO Auto-generated method stub
//			return -o1.compareTo(o2);
//		}
//	}

}
