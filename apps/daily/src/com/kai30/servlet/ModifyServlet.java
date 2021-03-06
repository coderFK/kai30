package com.kai30.servlet;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kai30.javabean.Content;
import com.kai30.javabean.Daily;
import com.kai30.model.DailyService;
import com.kai30.util.MyStringUtil;

/**
 * Servlet implementation class ModifyServlet
 */
@WebServlet(urlPatterns={"/modify.do"},
		initParams={@WebInitParam(name="MEMBER_VIEW", value="member.jsp"),
				@WebInitParam(name="MESSAGE_VIEW", value="message.do")})
public class ModifyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String MEMBER_VIEW;
	private static String MESSAGE_VIEW;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModifyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		MEMBER_VIEW = getServletConfig().getInitParameter("MEMBER_VIEW");
		MESSAGE_VIEW = getServletConfig().getInitParameter("MESSAGE_VIEW");
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(request, response);
	}

	private void processRequest(HttpServletRequest request, HttpServletResponse response) 
			throws IOException, ServletException {
		request.setCharacterEncoding("UTF-8");
		DailyService dailyService = (DailyService) request.getServletContext().getAttribute("dailyService");
		HttpSession session = request.getSession();
		String name = (String) session.getAttribute("login");
		
		String date_str = request.getParameter("date");
		String time = request.getParameter("time");
		
		try{
			if(!MyStringUtil.isInvalidKey(date_str)){
				Date date = new Date(Long.parseLong(date_str));
				Daily daily = new Daily();
				daily.setUsername(name);
				daily.setDate(date);
				daily = dailyService.getDaily(daily);
				
				String content = daily.getContent().getText();
				String title = daily.getTitle();
				String subject = daily.getSubject();
				
				request.setAttribute("msg", "修改日志");
				request.setAttribute("subject", subject);
				request.setAttribute("title", title);
				request.setAttribute("content", content);
				request.setAttribute("time", date);
				request.setAttribute("modify", true);
				
				List<Daily> dailys = dailyService.getDailys(daily);
				Set<String> subjects = dailyService.getSubjects(daily);
				request.setAttribute("dailys", dailys);
				request.setAttribute("subjects", subjects);
				request.getRequestDispatcher(MEMBER_VIEW).forward(request, response);
			}
			else if(!MyStringUtil.isInvalidKey(time)){
				
				String content = request.getParameter("content"); 
				String title = request.getParameter("title");
				String subject = request.getParameter("subject");
				Date date = new Date(Long.parseLong(time));
				
				content = content.trim();
				title = title.trim();
				subject = subject.trim();
				
				Daily daily = new Daily();
				daily.setUsername(name);
				daily.setDate(date);
				daily.setContent(new Content(content));
				daily.setTitle(title);
				daily.setSubject(subject);
				dailyService.modifyDaily(daily);
				response.sendRedirect(MESSAGE_VIEW);
			}
			else{
				response.sendRedirect(MESSAGE_VIEW);
			}
		}
		catch(Exception e){
			response.sendRedirect(MESSAGE_VIEW);
		}
		
	}

}
