package com.kai30.util;

public class StringUtil {
	public static boolean isInvalidKey(String key){
		if(key==null){
			return true;
		}
		else{
			//将字符串首尾的空格去除
			key = key.trim();
		}
		//如果之前的关键字是空字符串或者全为空格，则搜索无效
		if(key.length()==0){
			return true;
		}
		return false;
	}
}
