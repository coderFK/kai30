package com.kai30.model;

import java.util.List;
import java.util.Set;

import com.kai30.javabean.Daily;

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
	
	public List<Daily> getSubjectDailys(Daily daily){
		// TODO Auto-generated method stub
		return dailyDAO.getSubjectDailys(daily);
	}
	
	public Set<String> getSubjects(Daily daily){
		// TODO Auto-generated method stub
		return dailyDAO.getSubjects(daily);
	}
	
	public void addDaily(Daily daily){
		dailyDAO.addDaily(daily);
	}
	
	public void deleteDaily(Daily daily) {
		dailyDAO.deleteDaily(daily);
	}
	
	public void modifyDaily(Daily daily){
		dailyDAO.modifyDaily(daily);
	}
	
	public Daily getDaily(Daily daily) {
		return dailyDAO.getDaily(daily);
	}
	
	public boolean isDailyExisted(Daily daily){
		return getDaily(daily)!=null;
	}
}
