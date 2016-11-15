package com.kai30.other;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;



public class Main{
	
	private static FileWriter fw;

	public static void main(String[] args) throws IOException, ClassNotFoundException, SQLException{
		
		//fw = new FileWriter(new File("G:/Doc/html/java6_API/html/api/java6/class-path.txt"));
		//getClassPath();
		//fw.close();
		//getPackagePath();
		//addToSQL();
	}
	

	private static void addToSQL() throws ClassNotFoundException, SQLException, IOException {
		// TODO Auto-generated method stub
		Connection con = null ;
		String driver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/apidoc";
		String user = "root";
		String password = "3721";
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
			if(!con.isClosed()){
				System.out.println("连接成功");
//				PreparedStatement ps = con.prepareStatement("insert into java6_package values(id,?,?)");
//				getPackagePath(ps);
				
				PreparedStatement ps = con.prepareStatement("insert into java6_class values(id,?,?,?)");
				getClassPath(ps);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			con.close();
		}
		
	}


	private static void getPackagePath(PreparedStatement ps) throws SQLException {
		// TODO Auto-generated method stub
		File file = new File("G:/Doc/html/java6_API/html/api/java6/ppl.txt");
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
			String line  = null;
			while((line=br.readLine())!=null){
				if(line.length() >20){
					String path = line.substring(line.indexOf("HREF=") + 6, line.indexOf("html") + 4);
					String name = line.substring(line.indexOf("Frame\">") + 7, line.indexOf("</A>"));
					
					ps.setString(1, name);
					ps.setString(2, path);
					ps.executeUpdate();
					
					System.out.println(name + " " +path);	
				}
				
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	private static void getClassPath(PreparedStatement ps) throws IOException, SQLException {
		// TODO Auto-generated method stub
		
		File file = new File("G:/Doc/html/java6_API/html/api/java6/");
		for(File f:file.listFiles()){
			if(f.isDirectory()){
				String fileName = f.getAbsolutePath();
				if(fileName.endsWith("java")||
						fileName.endsWith("javax")||
						fileName.endsWith("org")){
					find(f, ps);	
				}
			}
		}
	}

	public static void find(File file,PreparedStatement ps) throws IOException, SQLException{
		for(File f:file.listFiles()){
			if(f.isDirectory()){
				//System.out.println("目录 "+f.getAbsolutePath());
				find(f, ps);
			}
			else{
				String name = f.getName();
				String path = f.getAbsolutePath();
				path = path.substring(path.lastIndexOf("java6") + 6);
				String class_Package = f.getParent(); 
				class_Package = class_Package.substring(class_Package.lastIndexOf("java6") + 6);
				class_Package = class_Package.replace("\\", ".");
				if(name.endsWith("html")||name.endsWith("htm")){
					if(Character.isUpperCase(name.charAt(0))||name.startsWith("_")){
						name = name.substring(0, name.indexOf(".htm"));
						System.out.println(name + " " + class_Package + " " +path);
						
						ps.setString(1, name);
						ps.setString(2, class_Package);
						ps.setString(3, path);
						ps.executeUpdate();
						
//						fw.write( name + " " + class_Package + " " +path + "\r\n");
//						fw.flush();
					} 
				}
				
			}
		}
	}
	
	
}