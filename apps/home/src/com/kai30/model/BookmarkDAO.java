package com.kai30.model;

import java.util.LinkedList;
import java.util.List;

import com.kai30.javabean.Bookmark;

public interface BookmarkDAO {
	LinkedList<Bookmark> getBookmarks(Bookmark bookmark);
	
	void saveBookmark(LinkedList<Bookmark> bookmarks);
	
	void deleteBookmark(Bookmark bookmark);

	void deleteAllBookmark(Bookmark bookmark);
	
	List<Bookmark> getSearchResult(Bookmark bookmark, String searchBookmarkKey);
}
