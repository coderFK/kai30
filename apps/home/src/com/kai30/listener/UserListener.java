package com.kai30.listener;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.sql.DataSource;

import com.kai30.javabean.AccountDAO;
import com.kai30.javabean.SaveAccountInJDBC;
import com.kai30.javabean.UserService;

@WebListener
public class UserListener implements ServletContextListener{

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		
//		可随时改变储存方式
//		文件存储
//		saveInFile(sce);
//		数据库存储
		saveInJDBC(sce);
	}

	private void saveInJDBC(ServletContextEvent sce) {
		Context context;
		try {
			context = new InitialContext();
			Context envContext = (Context) context.lookup("java:/comp/env");
			DataSource dataSource = (DataSource) envContext.lookup("jdbc/daily");
			AccountDAO accountDAO = new SaveAccountInJDBC(dataSource);
			sce.getServletContext().setAttribute("us", new UserService(accountDAO));
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

//	private void saveInFile(ServletContextEvent sce) {
//		String userPath = sce.getServletContext().getInitParameter("userPath");
//		AccountDAO accountDAO = new SaveAccountInFile(userPath);
//		DailyDAO dailyDAO = new SaveDailyInFile(userPath);
//		sce.getServletContext().setAttribute("us", new UserService(accountDAO, dailyDAO));
//		
//	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		
	}

}
