package com.kai30.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;


import com.kai30.javabean.Bookmark;
import com.kai30.model.UserService;
import com.kai30.util.MyStringUtil;

/**
 * Servlet implementation class BookmarksServlet
 */
@MultipartConfig
@WebServlet("/bookmark.do")
public class BookmarkServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String PAGE = "bookmark.jsp";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BookmarkServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		UserService userService = (UserService) request.getServletContext().getAttribute("userService");
		final String username = (String) session.getAttribute("login");
		Bookmark bookmark = new Bookmark(username);
		LinkedList<Bookmark> bookmarks = userService.getBookmarks(bookmark);
		request.setAttribute("bookmarks", bookmarks);
		request.getRequestDispatcher(PAGE).forward(request, response);
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		UserService userService = (UserService) request.getServletContext().getAttribute("userService");
		final String username = (String) session.getAttribute("login");
		LinkedList<Bookmark> bookmarks = null;
		Bookmark bookmark = new Bookmark(username);
		String searchBookmarkKey = request.getParameter("searchBookmarkKey");
		if(!MyStringUtil.isInvalidKey(searchBookmarkKey)){
			List<Bookmark> searchResult = userService.getSearchResult(bookmark, searchBookmarkKey);
			request.setAttribute("searchBookmarkKey", searchBookmarkKey);
			request.setAttribute("searchBookmarkResult", searchResult);
		}
		else if(MyStringUtil.isInvalidKey(request.getParameter("content"))){
			bookmarks = addWithFile(request, userService);
		}
		else{
			bookmarks = addWithText(request, userService);
		}
		
		if(bookmarks!=null && !bookmarks.isEmpty()){
			userService.saveBookmark(bookmarks);
		}
		
		request.setAttribute("bookmarks", userService.getBookmarks(bookmark));
		request.getRequestDispatcher(PAGE).forward(request, response);
	}

	
	private LinkedList<Bookmark> addWithText(HttpServletRequest request, UserService us) {
		LinkedList<Bookmark> bookmarks = null;
		final String username = (String) request.getSession().getAttribute("login");
		String content = (String) request.getParameter("content");
		
		if(!MyStringUtil.isInvalidKey(content)){
			content = content.trim();
			String urls [] = content.split(" ");
			Bookmark.initUserBookmarks(username);
			for (int i = 0; i < urls.length; i++) {
				final Date date = new Date();
				final String url = urls[i];
				new Thread(new Runnable(){
					public void run() {
						Bookmark.addWithText(username, date, url);		
					}
				}).start();
			}
			
			bookmarks = Bookmark.getUserBookmarks(username);
			while(bookmarks.size() != urls.length){}
		}
		return bookmarks;
	}

	private LinkedList<Bookmark> addWithFile(HttpServletRequest request, UserService us) {
		LinkedList<Bookmark> bookmarks = null;
		try{
			Part part = request.getPart("bookmark_html");
			String username = (String) request.getSession().getAttribute("login");
			if(part!=null){
				String header = part.getHeader("Content-Disposition");
				String filename = header.substring(header.indexOf("filename=\"") + 10,
						header.lastIndexOf("\""));
				InputStream is = part.getInputStream();
				
				bookmarks = Bookmark.addWithFile(is, filename, username);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return bookmarks;		
	}


}
