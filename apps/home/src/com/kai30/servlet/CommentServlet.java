package com.kai30.servlet;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kai30.javabean.Comment;
import com.kai30.model.UserService;
import com.kai30.util.StringUtil;
import com.mysql.jdbc.StringUtils;
import com.mysql.jdbc.Util;

/**
 * Servlet implementation class CommentServlet
 */
@WebServlet("/comment.do")
public class CommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String ERR_PAGE = "/home";
	private static final String PAGE = "comment.jsp";
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CommentServlet() {
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
	
	private void process(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		String username = (String) request.getSession().getAttribute("login");
		UserService us = (UserService) request.getServletContext().getAttribute("us");
		List<Comment> comments;
		if(StringUtil.isInvalidKey(username)){
			response.sendRedirect(ERR_PAGE);
		}
		else{
			String content = request.getParameter("content");
			if(!StringUtil.isInvalidKey(content)){
				us.saveComment(new Comment(username, new Date(), content));
			}
			comments = us.getComments();
			request.setAttribute("comments", comments);
		}
		request.getRequestDispatcher(PAGE).forward(request, response);
	}

}
