package com.kai30.javabean;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.sql.DataSource;

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
					"select date, content, title, subject from t_daily where name=?");
			state.setString(1, daily.getUsername());
			ResultSet result = state.executeQuery();
			while(result.next()){
				dailys.addFirst(new Daily(daily.getUsername(), 
						result.getTimestamp(1), result.getString(2), 
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
					"insert into t_daily(name, date, content, title, subject) values(?, ?, ?, ?, ?)");
			state.setString(1, daily.getUsername());
			state.setTimestamp(2, new Timestamp( daily.getDate().getTime()));
			state.setString(3, daily.getContent());
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
					"delete from t_daily where date=?");
			state.setTimestamp(1, new Timestamp( daily.getDate().getTime()));
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
	
//	public class Cmp implements Comparator<Date>{
//		@Override
//		public int compare(Date o1, Date o2) {
//			// TODO Auto-generated method stub
//			return -o1.compareTo(o2);
//		}
//	}

}
