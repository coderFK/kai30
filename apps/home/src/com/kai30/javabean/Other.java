package com.kai30.javabean;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.kai30.util.MyStringUtil;


public class Other {

	String msg;
	
	public Other() throws  IOException{
		StringBuilder sb = new StringBuilder("");
		String url = "http://ac.qq.com/VIP";
		Document doc = Jsoup.connect(url).
				userAgent("Mozilla/5.0 (Windows NT 6.1; rv:22.0) Gecko/20100101 Firefox/22.0").
				ignoreContentType(true).timeout(30000).get();
		Elements ele = doc.getElementsByClass("in-works-name");
		for (Element element : ele) {
			sb.append(element.text() + " ");
		}
		
		msg = sb.toString();
		if(MyStringUtil.isInvalidKey(msg)){
			msg = "今日没有";
		}
	}

	public String getMsg() {
		return msg;
	}

	
}
