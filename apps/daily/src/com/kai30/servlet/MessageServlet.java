package com.kai30.servlet;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kai30.javabean.Daily;
import com.kai30.javabean.UserService;

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
		if(title == null){
			title = "";
		}
		UserService us = (UserService) request.getServletContext().getAttribute("us");
		Daily daily = new Daily();
		daily.setUsername(name);
		if(content!=null && content.length()!=0){
			System.out.println("content.length()="+ content.length() +"content="+content);
			
			daily.setDate(new Date());
			daily.setContent(content);
			daily.setTitle(title);
			daily.setSubject(subject);
			us.addDaily(daily);
		}
		List<Daily> dailys = us.getDailys(daily);
		request.setAttribute("dailys", dailys);
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
