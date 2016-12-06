package com.kai30.util;

import java.security.MessageDigest;

import sun.misc.BASE64Encoder;

public class MyStringUtil {
	//验证字符串是否是无效字符
	public static boolean isInvalidKey(String key){
		if(key==null){
			return true;
		}
		else{
			//将字符串首尾的空格去除，不能改变传进来的值
			key = key.trim();
		}
		//如果之前的关键字是空字符串或者全为空格，则搜索无效
		if(key.length()==0){
			return true;
		}
		return false;
	}
	
	//单向加密密码
	public static String encryptPassword(String password){
		return encryptWithMD5(password);
	}
	//利用MD5技术
	private static String encryptWithMD5(String password) {
		try {
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			BASE64Encoder encoder = new BASE64Encoder();
			String newPassword = encoder.encode(md5.digest(password.getBytes("utf-8")));
			return newPassword;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
