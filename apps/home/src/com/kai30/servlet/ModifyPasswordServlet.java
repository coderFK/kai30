package com.kai30.servlet;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kai30.model.UserService;
import com.kai30.util.MyStringUtil;

/**
 * Servlet implementation class ModifyPasswordServlet
 */
@WebServlet("/modifyPassword.do")
public class ModifyPasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String PAGE = "modifyPassword.jsp";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModifyPasswordServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		process(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		process(request, response);
	}

	private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		String username = (String) request.getSession().getAttribute("login");
		UserService us = (UserService) request.getServletContext().getAttribute("us");
		String password = request.getParameter("password");
		String confirmedPasswd = request.getParameter("confirmedPasswd");
		if(us.isInvalidePassword(password, confirmedPasswd)){
			request.setAttribute("msg", "密码格式错误或者两次密码输入不一致！");
		}
		else{
			password = MyStringUtil.encryptPassword(password);
			us.modifyPassword(username, password);
			request.setAttribute("msg", "修改成功！");
		}
		
		request.getRequestDispatcher(PAGE).forward(request, response);
		
		
		
	}

}
