package com.kai30.servlet;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.LinkedList;

import javax.imageio.stream.FileCacheImageInputStream;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.kai30.javabean.Bookmark;
import com.kai30.model.UserService;
import com.kai30.util.StringUtil;

/**
 * Servlet implementation class BookmarksServlet
 */
@MultipartConfig
@WebServlet("/bookmark.do")
public class BookmarkServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String PAGE = "bookmark.jsp";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BookmarkServlet() {
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


	private void process(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		UserService us = (UserService) request.getServletContext().getAttribute("us");
		final String username = (String) session.getAttribute("login");
		
		String content = getContent(request);
		
		if(!StringUtil.isInvalidKey(content)){
			final String urls [] = content.split(" ");
			Bookmark.getBookmarks().clear();
			for (int i = 0; i < urls.length; i++) {
				final Date date = new Date();
				final String url = urls[i];
				new Thread(new Runnable(){
					public void run() {
						Bookmark.createBookmark(username, date, url);		
					}
				}).start();
			}
			
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			if(Bookmark.getBookmarks()!= null && !Bookmark.getBookmarks().isEmpty()){
				us.saveBookmark(Bookmark.getBookmarks());	
			}
				
		}
		
		
		LinkedList<Bookmark> bookmarks = us.getBookmarks(username);
		request.setAttribute("bookmarks", bookmarks);
		request.getRequestDispatcher(PAGE).forward(request, response);
		
	}

	private String getContent(HttpServletRequest request) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String content = request.getParameter("content");
		try{
			Part part = request.getPart("bookmark_html");
			if(part!=null){
				String header = part.getHeader("Content-Disposition");
				String filename = header.substring(header.indexOf("filename=\"") + 10,
						header.lastIndexOf("\""));
				
				InputStream is = part.getInputStream();
				BufferedReader br = new BufferedReader(new InputStreamReader(is));
				
				StringBuilder sb_html = new StringBuilder("");
				String line = null;
				while((line=br.readLine())!=null){
					sb_html.append(line);
				}
				Document doc = Jsoup.parse(sb_html.toString());
				StringBuilder sb_content = new StringBuilder("");
				Elements eles = doc.getElementsByTag("a");
				for (Element element : eles) {
					sb_content.append(element.attr("href") + " ");
				}
				content = sb_content.toString();
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return content;
	}

}
