package com.kai30.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kai30.javabean.Daily;
import com.kai30.javabean.UserService;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet(urlPatterns={ "/user/*"}, initParams={
	@WebInitParam(name="USER_VIEW", value="/user.jsp")
})
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static String userView;
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
		userView = getServletConfig().getInitParameter("USER_VIEW");
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		process(request, response);
	}

	private void process(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		UserService us = (UserService) request.getServletContext().getAttribute("us");
		String username = request.getPathInfo().substring(1);
		System.out.println(username);
		System.out.println(username);
		Daily daily = new Daily();
		daily.setUsername(username);
		List<Daily> dailys =us.getDailys(daily);
		if(dailys.isEmpty()){
			request.setAttribute("user_err", "抱歉，该用户不存在或者他还没有日志。");
		}
		request.setAttribute("dailys", dailys);
		System.out.println(userView);
		request.getRequestDispatcher(userView).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		process(request, response);
	}

}