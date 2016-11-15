package com.kai30.javabean;

import java.util.List;

public class UserService {
	
	DailyDAO dailyDAO;
	
	
	public UserService( DailyDAO dailyDAO) {
		super();
		this.dailyDAO = dailyDAO;
	}
	
	public List<Daily> getDailys(Daily daily){
		// TODO Auto-generated method stub
		return dailyDAO.getDailys(daily);
	}
	
	public void addDaily(Daily daily){
		dailyDAO.addDaily(daily);
	}
	
	public void deleteDaily(Daily daily) {
		dailyDAO.deleteDaily(daily);
	}
	
}
