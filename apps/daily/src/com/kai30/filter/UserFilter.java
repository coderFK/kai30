package com.kai30.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter(urlPatterns={"/delete.do", "/logout.do", "/member.jsp", "/message.do"},
			initParams={@WebInitParam(name="LOGIN_VIEW", value="/home")})
public class UserFilter implements Filter{
	private String loginView = null;
	private HttpServletRequest req;
	private HttpSession sessionDaily;
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		loginView = filterConfig.getInitParameter("LOGIN_VIEW");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, 
			FilterChain chain)
			throws IOException, ServletException {
		req = (HttpServletRequest) request;
		sessionDaily = req.getSession();
		
		//其中contextDaily.getContext("/")表示取得"home"的上下文（context）
		if(sessionDaily.getAttribute("login")!=null){
			chain.doFilter(request, response);
		}
		else{
			ServletContext contextDaily = req.getServletContext();
			ServletContext contextHome = contextDaily.getContext("/home");
			
			if(contextHome!=null){
				HttpSession sessionHome = (HttpSession) contextHome.getAttribute("sessionHome");
				if(sessionHome!=null){
					String name = (String) sessionHome.getAttribute("login");
					if(name!=null){
						sessionDaily.setAttribute("login", name);
						chain.doFilter(request, response);
						return;
					}
				}
			}
			HttpServletResponse res = (HttpServletResponse) response;
			res.sendRedirect(loginView);
		}
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	

}
