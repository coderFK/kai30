package com.kai30.servlet;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kai30.javabean.Bookmark;
import com.kai30.model.UserService;
import com.kai30.util.MyStringUtil;

/**
 * Servlet implementation class SearchDaily
 */
@WebServlet("/searchBookmark.do")
public class SearchBookmarkServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String PAGE="bookmark.jsp";   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchBookmarkServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		String searchBookmarkKey = request.getParameter("searchBookmarkKey");
		String username = (String) request.getSession().getAttribute("login");
		UserService us = (UserService) request.getServletContext().getAttribute("us");
		Bookmark bookmark = new Bookmark(username);
		if(!MyStringUtil.isInvalidKey(searchBookmarkKey)){
			List<Bookmark> searchResult = us.getSearchResult(bookmark, searchBookmarkKey);
			request.setAttribute("searchBookmarkKey", searchBookmarkKey);
			request.setAttribute("searchBookmarkResult", searchResult);
		}
		
		LinkedList<Bookmark> bookmarks = us.getBookmarks(bookmark);
		request.setAttribute("bookmarks", bookmarks);
		request.getRequestDispatcher(PAGE).forward(request, response);
	}

}
