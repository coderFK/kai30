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
 * Servlet implementation class LoginCookieServlet
 */
@WebServlet("/loginCookie.do")
public class LoginCookieServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String PAGE = "index.jsp";   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginCookieServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		UserService userService = (UserService) request.getServletContext().getAttribute("userService");
		LogService logService = (LogService) request.getServletContext().getAttribute("logService");
		Cookie[] cookies = request.getCookies();
		for (Cookie cookie : cookies) {
			if("username".equals(cookie.getName())){
				String username = cookie.getValue();
				if(!MyStringUtil.isInvalidKey(username) && userService.isUserExisted(username)){
					HttpSession sessionHome =request.getSession();
					sessionHome.setAttribute("login", username);	
					ServletContext context = request.getServletContext();
					context.setAttribute("sessionHome", sessionHome);
					
					//记录用户登陆
					logService.accountLogin(username);
					if(userService.checkUserIsMaster(username)){
						sessionHome.setAttribute("isManager", "true");
					}
					break;
				}
			}
		}
		
		response.sendRedirect(PAGE);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
