package com.kai30.servlet;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kai30.javabean.Daily;
import com.kai30.model.UserService;
import com.kai30.util.MyStringUtil;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet(urlPatterns={ "/user/*"}, initParams={
	@WebInitParam(name="USER_VIEW", value="/user.jsp")
})
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static String USER_VIEW;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
		USER_VIEW = getServletConfig().getInitParameter("USER_VIEW");
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserService us = (UserService) request.getServletContext().getAttribute("us");
		String name = request.getPathInfo().substring(1);
		String subject = request.getParameter("subject");
		Daily daily = new Daily();
		daily.setUsername(name);
		List<Daily> dailys;
		Set<String> subjects;
		if(MyStringUtil.isInvalidKey(subject)){
			dailys =us.getDailys(daily);
			subjects = us.getSubjects(daily);
		}
		else{
			daily.setSubject(subject);
			dailys =us.getSubjectDailys(daily);
			subjects = us.getSubjects(daily);
		}
		
		if(dailys.isEmpty()){
			request.setAttribute("user_err", "抱歉，该用户不存在或者他还没有日志。");
		}
		
		request.setAttribute("name", name);
		request.setAttribute("dailys", dailys);
		request.setAttribute("subjects", subjects);
		request.getRequestDispatcher(USER_VIEW).forward(request, response);
	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserService us = (UserService) request.getServletContext().getAttribute("us");
		String name = request.getPathInfo().substring(1);
		
		String searchKey = request.getParameter("searchKey");
		if(!MyStringUtil.isInvalidKey(searchKey)){
			Daily daily = new Daily();
			daily.setUsername(name);
			List<Daily> searchResult = us.getSearchResult(daily, searchKey);
			request.setAttribute("searchKey", searchKey);
			request.setAttribute("searchResult", searchResult);
		}
		doGet(request, response);
	}

}
