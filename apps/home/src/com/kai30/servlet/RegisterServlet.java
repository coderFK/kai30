package com.kai30.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kai30.model.LogService;
import com.kai30.model.UserService;
import com.kai30.util.MyStringUtil;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/register.do")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    private static final String PAGE = "register.jsp";
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		req.setCharacterEncoding("UTF-8");
		String email =req.getParameter("email");
		String username =req.getParameter("username");
		String password =req.getParameter("password");
		String confirmedPasswd =req.getParameter("confirmedPasswd");
		UserService us = (UserService) req.getServletContext().getAttribute("us");
		LogService logService = (LogService) req.getServletContext().getAttribute("logService");
		List<String> errors = new ArrayList<String>();
		if(us.isInvalidEmail(email)){
			errors.add("邮件填写错误");
		}
		if(us.isUserExisted(username)){
			errors.add("用户名已经存在");
		}
		if(us.isInvalidePassword(password, confirmedPasswd)){
			errors.add("密码格式错误或不一致");
		}
		
		//加密密码
		password = MyStringUtil.encryptPassword(password);
		
		if(errors.isEmpty()){
			logService.accountRegister(username);
			us.createUserData(email, username, password);
		}
		else{
			req.setAttribute("errors", errors);
		}
		req.getRequestDispatcher(PAGE).forward(req, res);
	}

	
}
