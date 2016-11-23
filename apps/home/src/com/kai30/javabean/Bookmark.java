package com.kai30.javabean;

import java.util.Date;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.kai30.util.StringUtil;

public class Bookmark {
	
	String username;
	Date date;
	String url;
	String title;
	String imgUrl;
	
	
	
	public Bookmark(String username, Date date, String url, String title, String imgUrl) {
		super();
		this.username = username;
		this.date = date;
		this.url = url;
		this.title = title;
		this.imgUrl = imgUrl;
	}

	private Bookmark(){
		
	}
	
	private Bookmark(String username, Date date) {
		super();
		this.username = username;
		this.date = date;
	}
	
	public static Bookmark createBookmark(String username, Date date, String url){
		
		Bookmark bookmark = new Bookmark(username, date);
		
		try {
			Document doc = Jsoup.connect(url).get();
			Elements ele_title = doc.select("head title");
			Elements ele_imgUrl = doc.getElementsByAttributeValue("rel", "Shortcut Icon");
			
			bookmark.url = url;
			
			//处理标题
			bookmark.title = ele_title.text();
			if(!StringUtil.isInvalidKey(bookmark.title)){
				bookmark.title = "标题";
			}
			//处理图片URL
			bookmark.imgUrl = ele_imgUrl.attr("href");
			if(!StringUtil.isInvalidKey(bookmark.imgUrl) && !bookmark.imgUrl.startsWith("http")){
				bookmark.imgUrl = url + "/" + bookmark.imgUrl;
			}
			else{
				bookmark.imgUrl = "http://www.kai30.com/images/default.png";
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return null;
		}
		
		return bookmark;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Bookmark other = (Bookmark) obj;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}
	
}
