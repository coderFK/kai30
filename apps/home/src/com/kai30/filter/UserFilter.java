package com.kai30.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kai30.util.StringUtil;

/**
 * Servlet Filter implementation class UserFilter
 */
@WebFilter(urlPatterns={"/logout.do", "/modifyPassword.do", "/comment.do"},
		initParams=@WebInitParam(name="LOGIN_VIEW", value="/home"))
public class UserFilter implements Filter {
	private String login_view;
	
    /**
     * Default constructor. 
     */
    public UserFilter() {
        // TODO Auto-generated constructor stub
    }
    
    /**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		login_view = fConfig.getInitParameter("LOGIN_VIEW");
	}
	
	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		try{
			HttpSession session = req.getSession();
			String username = (String) session.getAttribute("login");
			if(!StringUtil.isInvalidKey(username)){
				chain.doFilter(request, response);
			}
			else{
				res.sendRedirect(login_view);
			}
		}
		catch(Exception e){
			res.sendRedirect(login_view);
		}
		
	}

}
