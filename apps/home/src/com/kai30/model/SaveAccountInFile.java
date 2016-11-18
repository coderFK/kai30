
package com.kai30.model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.kai30.javabean.Account;

public class SaveAccountInFile implements AccountDAO{
	
	String userPath = null;

	public SaveAccountInFile(String userPath) {
		super();
		this.userPath = userPath;
	}

	@Override
	public boolean isUserExisted(String name) {
		// TODO Auto-generated method stub
		File file = new File(userPath + "/" + name);
		if(file.exists()){
			return true;
		}
		return false;
	}

	@Override
	public void createUserData(String email, String name, String password) {
		// TODO Auto-generated method stub
		BufferedWriter bw = null;
		try {
			File file = new File(userPath + "/" + name);
			file.mkdir();
			bw = new BufferedWriter(new FileWriter(file + "/profile"));
			bw.write(email + "\t" + password);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			try {
				if(bw!=null){
					bw.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}


	@Override
	public boolean checkLoginIsOk(String name, String password) {
		if(name !=null && password!=null){
			//记住try要在for循环里面， 否则流的关闭会无效！！！！！！！！！
			for(String f : new File(userPath).list()){
				if(f.equals(name)){
					BufferedReader br = null;
					try {
						br = new BufferedReader(new FileReader(userPath + "/" + f + "/profile" ));
						String pw = br.readLine().split("\t")[1];
						if(pw.equals(password)){
							if(br!=null){
								br.close();
							}
							return true;
						}
					} 
					catch(IOException e){
						e.printStackTrace();
					}
					finally{
						if(br!=null){
							try {
								br.close();
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					}
					
				}
			}
		}
		return false;
	}

	@Override
	public Account getAccount(String name) {
		// TODO Auto-generated method stub
		if(name !=null){
			//记住try要在for循环里面， 否则流的关闭会无效！！！！！！！！！
			for(String f : new File(userPath).list()){
				if(f.equals(name)){
					BufferedReader br = null;
					try {
						br = new BufferedReader(new FileReader(userPath + "/" + f + "/profile" ));
						String[] txts = br.readLine().split("\t");
						if(br!=null){
							br.close();
						}
						return new Account( name, txts[1], txts[0]);
					} 
					catch(IOException e){
						e.printStackTrace();
					}
					finally{
						if(br!=null){
							try {
								br.close();
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					}
					
				}
			}
		}
		return null;
	}

	@Override
	public void modifyPassword(String name, String password) {
		// TODO Auto-generated method stub
		
	}
	
}
