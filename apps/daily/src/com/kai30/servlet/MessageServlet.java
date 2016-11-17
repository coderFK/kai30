package com.kai30.servlet;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kai30.javabean.Content;
import com.kai30.javabean.Daily;
import com.kai30.model.UserService;
import com.kai30.util.StringUtil;

/**
 * Servlet implementation class MessageServlet
 */
@WebServlet(urlPatterns="/message.do", 
		initParams={@WebInitParam(name="MEMBER_VIEW", value="member.jsp")})
public class MessageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String MEMBER_VIEW;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MessageServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    
	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
		MEMBER_VIEW = getServletConfig().getInitParameter("MEMBER_VIEW");
		
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(request, response);
		
	}

	private void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		String name = (String) session.getAttribute("login");
		String content = request.getParameter("content");
		String title = request.getParameter("title");
		String subject = request.getParameter("subject");
		
		UserService us = (UserService) request.getServletContext().getAttribute("us");
		Daily daily = new Daily();
		daily.setUsername(name);
		//主题为空则为设置为其他
		if(StringUtil.isInvalidKey(subject)){
			subject = "其他";
		}
		if(StringUtil.isInvalidKey(content)||StringUtil.isInvalidKey(title)){
			request.setAttribute("msg", "写日志");
		}
		else{
			daily.setDate(new Date());
			daily.setContent(new Content(content));
			daily.setTitle(title);
			daily.setSubject(subject);
			us.addDaily(daily);
		}
		List<Daily> dailys = us.getDailys(daily);
		Set<String> subjects = us.getSubjects(daily);
		request.setAttribute("dailys", dailys);
		request.setAttribute("subjects", subjects);
		request.getRequestDispatcher(MEMBER_VIEW).forward(request, response);
	}

	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(request, response);
	}

}
