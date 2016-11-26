package com.kai30.javabean;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.kai30.util.MyStringUtil;

public class Bookmark {
	
	String username;
	Date date;
	String url;
	String title;
	String imgUrl;
	
	private static String defaultImg = "images/default_icon.png";
	private static HashMap<String, LinkedList<Bookmark>> userBookmarks =
			new HashMap<String, LinkedList<Bookmark>>();
	
	public Bookmark(){
		
	}
	
	public Bookmark(String username, Date date, String url, String title, String imgUrl) {
		super();
		this.username = username;
		this.date = date;
		this.url = url;
		this.title = title;
		this.imgUrl = imgUrl;
	}

	public Bookmark(String username, Date date) {
		super();
		this.username = username;
		this.date = date;
	}
	
	public static void addWithText(String username, Date date, String url){
		
		Bookmark bookmark = new Bookmark(username, date);
		try {
			Document doc = Jsoup.connect(url).timeout(3000).get();
			Elements ele_title = doc.select("head title");
//			Elements ele_imgUrl = doc.getElementsByAttributeValue("rel", "Shortcut Icon");
			
			bookmark.url = url;
			
			//处理标题
			bookmark.title = ele_title.text();
			if(MyStringUtil.isInvalidKey(bookmark.title)){
				bookmark.title = url;
			}
			//处理图片URL
			bookmark.imgUrl = defaultImg;
//			bookmark.imgUrl = ele_imgUrl.attr("href");
//			if(!StringUtil.isInvalidKey(bookmark.imgUrl) && !bookmark.imgUrl.startsWith("http")){
//				bookmark.imgUrl = url + "/" + bookmark.imgUrl;
//			}
//			else{
//				bookmark.imgUrl = defaultImg;
//			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			bookmark.url = url;
			bookmark.title = url;
			bookmark.imgUrl = defaultImg;
		}
		finally{
			setUserBookmarks(username, bookmark);
		}
	}
	
	public static LinkedList<Bookmark> addWithFile(InputStream is, String filename, String username) {
		LinkedList<Bookmark> bookmarks = new LinkedList<Bookmark>();
		try {
			
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			StringBuilder sb_html = new StringBuilder("");
			String line = null;
			while((line=br.readLine())!=null){
				sb_html.append(line);
			}
			Document doc = Jsoup.parse(sb_html.toString());
			Elements eles = doc.getElementsByTag("a");
			for (Element element : eles) {
				String url = element.attr("href");
				String title = element.text();
				String imgUrl = element.attr("icon");
				
				if(!MyStringUtil.isInvalidKey(url)){
					if(MyStringUtil.isInvalidKey(imgUrl)){
						imgUrl = "images/default_icon.png";
					}
					Bookmark bookmark = new Bookmark(username, new Date(), url, title, imgUrl);
					bookmarks.add(bookmark);
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bookmarks;
	}
	
	public static void initUserBookmarks(String username) {
		// TODO Auto-generated method stub
		userBookmarks.put(username, new LinkedList<Bookmark>());
	}
	public static LinkedList<Bookmark> getUserBookmarks(String username) {
		// TODO Auto-generated method stub
		return userBookmarks.get(username);
	}
	public static void setUserBookmarks(String username, Bookmark bookmark) {
		getUserBookmarks(username).add(bookmark);
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
