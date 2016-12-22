package com.kai30.servlet;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kai30.javabean.Comment;
import com.kai30.model.UserService;
import com.kai30.util.MyStringUtil;
import com.mysql.jdbc.StringUtils;
import com.mysql.jdbc.Util;

/**
 * Servlet implementation class CommentServlet
 */
@WebServlet("/comment.do")
public class CommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
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
		request.setCharacterEncoding("UTF-8");
		String username = (String) request.getSession().getAttribute("login");
		String content = request.getParameter("content");
		UserService userService = (UserService) request.getServletContext().getAttribute("userService");
		if(!MyStringUtil.isInvalidKey(content)){
			userService.saveComment(new Comment(username, new Date(), content));
		}
			
		Set<Comment> comments = userService.getComments();
		request.setAttribute("comments", comments);
		request.getRequestDispatcher(PAGE).forward(request, response);
	}

}
