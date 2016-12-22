package com.kai30.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kai30.javabean.Daily;
import com.kai30.model.DailyService;
import com.kai30.util.MyStringUtil;

/**
 * Servlet implementation class DailyServlet
 */
@WebServlet("/daily.do")
public class DailyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String INDEX_VIEW = "index.jsp";
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DailyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		DailyService dailyService = (DailyService) request.getServletContext().getAttribute("dailyService");
		List<Daily> allDailys = dailyService.getAllDailys();
		request.setAttribute("dailys", allDailys);
		request.getRequestDispatcher(INDEX_VIEW).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		DailyService dailyService = (DailyService) request.getServletContext().getAttribute("dailyService");
		String searchKey = request.getParameter("searchKey");
		List<Daily> allDailys = dailyService.getAllDailys();
		request.setAttribute("dailys", allDailys);
		if(!MyStringUtil.isInvalidKey(searchKey)){
			List<Daily> searchResult = dailyService.getAllSearchResult(searchKey);
			request.setAttribute("searchResult", searchResult);
		}
		request.getRequestDispatcher(INDEX_VIEW).forward(request, response);
	}

}
