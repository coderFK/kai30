package com.kai30.javabean;

import java.util.List;

public interface DailyDAO {

	List<Daily> getDailys(Daily blah);

	void addDaily(Daily blah);

	void deleteDaily(Daily blah);

}
