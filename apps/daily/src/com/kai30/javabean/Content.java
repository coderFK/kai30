package com.kai30.javabean;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Serializable;
import java.io.StringReader;
import java.util.ArrayList;

public class Content implements Serializable{
	String text;
	ArrayList<String> textList = new ArrayList<String>();
	
	
	public Content(String text) {
		super();
		this.text = text;
		
		String line = null;
		BufferedReader reader = new BufferedReader(new StringReader(text));
		try {
			while((line=reader.readLine())!=null){
				textList.add(line + "<br />");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public ArrayList<String> getTextList() {
		return textList;
	}

	public void setTextList(ArrayList<String> textList) {
		this.textList = textList;
	}
	
	@Override
	public String toString() {
		return text;
	}
	
}
