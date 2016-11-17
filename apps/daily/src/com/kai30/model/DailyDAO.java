package com.kai30.model;

import java.util.List;
import java.util.Set;

import com.kai30.javabean.Daily;

public interface DailyDAO {

	List<Daily> getDailys(Daily blah);
	
	List<Daily> getSubjectDailys(Daily blah);
	
	Set<String> getSubjects(Daily blah);

	void addDaily(Daily blah);

	void deleteDaily(Daily blah);

	
}
