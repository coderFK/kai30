package com.kai30.servlet;

import java.io.IOException;
import java.util.Date;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import com.kai30.javabean.Bookmark;
import com.kai30.model.UserService;
import com.kai30.util.StringUtil;
import com.mysql.jdbc.StringUtils;

/**
 * Servlet implementation class BookmarksServlet
 */
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
		process(request, response);
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		process(request, response);
	}


	private void process(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		UserService us = (UserService) request.getServletContext().getAttribute("us");
		String username = (String) session.getAttribute("login");
		String url = request.getParameter("content");
		
		if(!StringUtil.isInvalidKey(url)){
			Date date = new Date();
			Bookmark bookmark = Bookmark.createBookmark(username, date, url);
			if(bookmark!=null){
				us.saveBookmark(bookmark);	
			}
		}
		
		
		LinkedList<Bookmark> bookmarks = us.getBookmarks(username);
		request.setAttribute("bookmarks", bookmarks);
		request.getRequestDispatcher(PAGE).forward(request, response);
		
	}

}
