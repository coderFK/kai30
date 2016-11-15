package com.kai30.servlet;

import java.io.IOException;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kai30.javabean.Account;
import com.kai30.javabean.UserService;



/**
 * Servlet implementation class SendServlet
 * 
 * request.getHeader("User-Agent");    //就是取得客户端的系统版本     
request.getRemoteAddr();    //取得客户端的IP     
request.getRemoteHost()     //取得客户端的主机名     
request.getRemotePort();    //取得客户端的端口     
request.getRemoteUser();    //取得客户端的用户     
request.getLocalAddr();    //取得服务器IP     
request.getLocalPort();    //取得服务器端口
 */
@WebServlet("/findPassword.do")
public class FindPasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	String from = "smtp.qq.com";
	String adminUsername = "3311705190@qq.com";
	String adminPassword = "quxkovbqpxtocjfd";
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FindPasswordServlet() {
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		String name = request.getParameter("username"); 
		UserService us = (UserService) request.getServletContext().getAttribute("us");
		Account account = null;
		if(name!=null&&us.isUserExisted(name)){
			account = us.getAccount(name);
		}
		else{
			request.setAttribute("msg", "用户名不存在");
			request.getRequestDispatcher("findPassword.jsp").forward(request, response);
			return;
		}
		
		Properties pro = new Properties();
		
		pro.put("mail.smtp.host", from);
		pro.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		pro.setProperty("mail.smtp.socketFactory.fallback", "false");
		pro.setProperty("mail.smtp.port", "465");
		pro.setProperty("mail.smtp.socketFactory.port", "465");
		pro.setProperty("mail.smtp.auth", "true");
		try {
			Session ses = Session.getDefaultInstance(pro, new Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					// TODO Auto-generated method stub
					return new PasswordAuthentication(adminUsername,adminPassword);
				}
			});
			Message message = createEmail(ses, account, request);
			Transport.send(message);
			request.setAttribute("name", account.getName());
			request.setAttribute("msg", "发送邮件成功");
			request.setAttribute("loginErr", "发送邮件成功");
			request.getRequestDispatcher("findPassword.jsp").forward(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			request.setAttribute("name", account.getName());
			request.setAttribute("msg", "发送邮件失败");
			request.getRequestDispatcher("findPassword.jsp").forward(request, response);
			e.printStackTrace();
			
		}
	}

	private Message createEmail(Session session, Account account, HttpServletRequest request) {
		// TODO Auto-generated method stub
		MimeMessage msg = new MimeMessage(session);
	    MimeBodyPart body = new MimeBodyPart();
	    Multipart part = new MimeMultipart();
	    String path = "http://123.206.213.249/home";
	    String text = "<html>"
	    		+ "<body>"
	    		+ "<h5>"+ account.getName() +"，您好！您的密码为:" + account.getPassword()
	    		+ "<br />您可以 <a href='"+path+"'> 马上登陆</a>"
	    		+ "</body>"
	    		+ "</html>";
	    
		try {
			body.setContent(text, "text/html;charset=UTF-8");
			part.addBodyPart(body);
			msg.setContent(part);
			msg.setFrom(new InternetAddress(adminUsername));
			msg.setRecipient(Message.RecipientType.TO, new InternetAddress(account.getEmail()));
			msg.setSubject("找回密码");
			msg.saveChanges();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return msg;
	}

}
