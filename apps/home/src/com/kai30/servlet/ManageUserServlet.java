package com.kai30.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kai30.javabean.Account;
import com.kai30.model.UserService;

/**
 * Servlet implementation class ManageUserServlet
 */
@WebServlet("/manageUser.do")
public class ManageUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String MANAGE_VAGE = "manage.jsp";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ManageUserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		UserService us = (UserService) request.getServletContext().getAttribute("us");
		List<Account> accounts = us.getAccounts();
		request.setAttribute("accounts", accounts);
		request.getRequestDispatcher(MANAGE_VAGE).forward(request, response);;
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
