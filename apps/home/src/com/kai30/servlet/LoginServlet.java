package com.kai30.servlet;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kai30.javabean.UserService;

/**
 * Servlet implementation class ControllerServlet
 */
@WebServlet("/login.do")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static final String ERROR_PAGE = "index.jsp";
    private static final String SUCCESS_PAGE = "/daily/message.do";
     
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
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
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		req.setCharacterEncoding("UTF-8");
		String name =req.getParameter("username");
		String password =req.getParameter("password");
		UserService us = (UserService) req.getServletContext().getAttribute("us");
		
		if(us.checkLoginIsOk(name, password)){
//			req.login(name, password);
			//将session放入当前应用的ServletContext中， 以便传递
			HttpSession sessionHome =req.getSession();
			sessionHome.setAttribute("login", name);	
			ServletContext context = req.getServletContext();
			context.setAttribute("sessionHome", sessionHome);
			
			res.sendRedirect(SUCCESS_PAGE);
//			req.getRequestDispatcher(SUCCESS_PAGE).forward(req, res);
		}
		else{
			req.setAttribute("loginErr", "用户名不存在或者密码错误");
			req.getRequestDispatcher(ERROR_PAGE).forward(req, res);
		}
		//res.sendRedirect(page);
		
	}

	

}
