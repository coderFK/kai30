package com.kai30.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kai30.model.UserService;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/register.do")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static final String ERROR_PAGE = "register.jsp";
    private static final String SUCCESS_PAGE = "register.success.jsp";
    
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
		String name =req.getParameter("username");
		String password =req.getParameter("password");
		String confirmedPasswd =req.getParameter("confirmedPasswd");
		UserService us = (UserService) req.getServletContext().getAttribute("us");
		List<String> errors = new ArrayList<String>();
		if(us.isInvalidEmail(email)){
			errors.add("邮件填写错误");
		}
		if(us.isUserExisted(name)){
			errors.add("用户名已经存在");
		}
		if(us.isInvalidePassword(password, confirmedPasswd)){
			errors.add("密码格式错误或不一致");
		}
		String resultPage = ERROR_PAGE;
		if(errors.isEmpty()){
			resultPage = SUCCESS_PAGE;
			us.createUserData(email, name, password);
		}
		else{
			req.setAttribute("errors", errors);
		}
		req.getRequestDispatcher(resultPage).forward(req, res);
	}

	
}
