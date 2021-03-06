package com.kai30.model;

import java.util.List;
import java.util.Set;

import com.kai30.javabean.Daily;

public class DailyService {
	
	DailyDAO dailyDAO;
	
	
	public DailyService( DailyDAO dailyDAO) {
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

	public List<Daily> getSearchResult(Daily daily, String searchKey) {
		// TODO Auto-generated method stub
		return dailyDAO.getSearchResult(daily, searchKey);
	}
	
	public List<Daily> getAllDailys(){
		return dailyDAO.getAllDailys();
	}
	
	public List<Daily> getAllSearchResult(String searchKey) {
		return dailyDAO.getAllSearchResult(searchKey);
	}

}
