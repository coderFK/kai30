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
		String name = request.getPathInfo().substring(1);
		String subject = (String) request.getParameter("subject");
		Daily daily = new Daily();
		daily.setUsername(name);
		List<Daily> dailys;
		if(MyStringUtil.isInvalidKey(subject)){
			dailys =us.getDailys(daily);
		}
		else{
			daily.setSubject(subject);
			dailys =us.getSubjectDailys(daily);
		}
		
		if(dailys.isEmpty()){
			request.setAttribute("user_err", "抱歉，该用户不存在或者他还没有日志。");
		}
		else{
			request.setAttribute("user_name", name + "的日志");
		}
		request.setAttribute("dailys", dailys);
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
