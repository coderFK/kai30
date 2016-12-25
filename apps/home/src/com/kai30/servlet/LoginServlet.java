package com.kai30.servlet;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kai30.model.LogService;
import com.kai30.model.UserService;
import com.kai30.util.MyStringUtil;

/**
 * Servlet implementation class ControllerServlet
 */
@WebServlet("/login.do")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static final String ERROR_PAGE = "index.jsp";
    private static final String SUCCESS_PAGE = "/daily/message.do";
    private static final String SUCCESS_PAGE_MASTER = "index.jsp";
     
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
		String username =req.getParameter("username");
		String password =req.getParameter("password");
		String rememberUser = req.getParameter("rememberUser");
		password = MyStringUtil.encryptPassword(password);
		UserService userService = (UserService) req.getServletContext().getAttribute("userService");
		LogService logService = (LogService) req.getServletContext().getAttribute("logService");
		
		if(userService.checkLoginIsOk(username, password)){
//			req.login(name, password);
			//将session放入当前应用的ServletContext中， 以便传递数据
			HttpSession sessionHome =req.getSession();
			sessionHome.setAttribute("login", username);	
			ServletContext context = req.getServletContext();
			context.setAttribute("sessionHome", sessionHome);
			
			if(!MyStringUtil.isInvalidKey(rememberUser)){
				Cookie cookie = new Cookie("username", username);
				cookie.setMaxAge(7* 24 * 60 * 60);
				res.addCookie(cookie);
			}
			
			//记录用户登陆
			logService.accountLogin(username);
			
			if(userService.checkUserIsMaster(username)){
				sessionHome.setAttribute("isManager", "true");
			}
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
