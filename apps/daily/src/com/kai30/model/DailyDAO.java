package com.kai30.model;

import java.util.List;
import java.util.Set;

import com.kai30.javabean.Daily;

public interface DailyDAO {

	List<Daily> getDailys(Daily daily);
	
	Daily getDaily(Daily daily);
	
	List<Daily> getSubjectDailys(Daily daily);
	
	Set<String> getSubjects(Daily daily);

	void addDaily(Daily daily);

	void deleteDaily(Daily daily);

	void modifyDaily(Daily daily);

	List<Daily> getSearchResult(Daily daily, String searchKey);
	
}
