package com.kai30.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kai30.javabean.Daily;
import com.kai30.model.UserService;
import com.kai30.util.MyStringUtil;

/**
 * Servlet implementation class SearchDaily
 */
@WebServlet("/searchDaily.do")
public class SearchDailyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String MESSAGE_PAGE="message.do";   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchDailyServlet() {
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
		String searchKey = request.getParameter("searchKey");
		if(!MyStringUtil.isInvalidKey(searchKey)){
			String username = (String) request.getSession().getAttribute("login");
			UserService us = (UserService) request.getServletContext().getAttribute("us");
			Daily daily = new Daily();
			daily.setUsername(username);
			List<Daily> searchResult = us.getSearchResult(daily, searchKey);
			request.setAttribute("searchKey", searchKey);
			request.setAttribute("searchResult", searchResult);
		}
		
		request.getRequestDispatcher(MESSAGE_PAGE).forward(request, response);
	}

}
