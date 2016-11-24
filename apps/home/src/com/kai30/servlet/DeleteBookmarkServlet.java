package com.kai30.servlet;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kai30.javabean.Bookmark;
import com.kai30.model.UserService;

/**
 * Servlet implementation class DeleteBookmarkServlet
 */
@WebServlet("/deleteBookmark.do")
public class DeleteBookmarkServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String BOOKMARK_VIEW_OK = "bookmark.do";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteBookmarkServlet() {
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

	private void process(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		UserService us = (UserService) request.getServletContext().getAttribute("us");
		String username = (String) session.getAttribute("login");
		Long date = Long.parseLong(request.getParameter("date"));
		Bookmark bookmark = new Bookmark(); 
		
		bookmark.setDate(new Date(date));
		bookmark.setUsername(username);
		us.deleteBookmark(bookmark);
		
		response.sendRedirect(BOOKMARK_VIEW_OK);
	}

}
