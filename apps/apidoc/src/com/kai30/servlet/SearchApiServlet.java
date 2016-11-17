package com.kai30.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.TreeSet;
import java.util.TreeSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kai30.javabean.ClassBean;
import com.kai30.javabean.PackageBean;
import com.kai30.model.Select;
import com.kai30.model.SelectFromJDBC;

/**
 * Servlet implementation class SearchApi
 */
@WebServlet("/searchApi.do")
public class SearchApiServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchApiServlet() {
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		String key = request.getParameter("key");
		String caseSensitive = request.getParameter("caseSensitive");
		boolean isCaseSensitive = false;
		if(caseSensitive!=null){
			isCaseSensitive = true; 
			request.getSession().setAttribute("isCaseSensitiveChecked", "checked='checked'");
		}
		else{
			request.getSession().setAttribute("isCaseSensitiveChecked", "");
		}
		
		Select select = new SelectFromJDBC(key, isCaseSensitive);
		TreeSet<PackageBean> pbList = select.getPbList();
		TreeSet<ClassBean> cbList = select.getCbList();
		
		request.getSession().setAttribute("pbList", pbList);
		request.getSession().setAttribute("cbList", cbList);
		request.getSession().setAttribute("keyValue", key);
		
		response.sendRedirect("/apidoc/search.jsp");
	}
}
