package com.kai30.model;

import java.util.LinkedList;

import com.kai30.javabean.Bookmark;

public interface BookmarkDAO {
	LinkedList<Bookmark> getBookmarks(String username);
	
	void saveBookmark(LinkedList<Bookmark> bookmarks);
	
	void deleteBookmark(Bookmark bookmark);
}
