package com.kai30.model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import com.kai30.javabean.Content;
import com.kai30.javabean.Daily;


public class SaveDailyInFile implements DailyDAO{

	String userPath = null;
	
	public SaveDailyInFile(String userPath) {
		super();
		this.userPath = userPath;
	}
	
	@Override
	public List<Daily> getDailys(Daily daily) {
		// TODO Auto-generated method stub
		File file = new File(userPath + "/" + daily.getUsername());
		BufferedReader br = null;
		LinkedList<Daily> dailys = new LinkedList<Daily>();
		//记住try要在for循环里面， 否则流的关闭会无效！！！！！！！！！
		for(String f: file.list(new TxtFilenameFilter())) {
			try{
				String time = f.substring(0, f.indexOf(".txt"));
				Date date = new Date(Long.parseLong(time));
				br = new BufferedReader(new InputStreamReader(new FileInputStream(file+"/"+f), "UTF-8"));
				StringBuilder text = new StringBuilder("");
				String line = null;
				while((line=br.readLine())!=null){
					text.append(line);
				}
				
				Daily b = new Daily(daily.getUsername(), date, new Content(text.toString()), "", "");
				dailys.addFirst(b);
			}
			catch(IOException e){
				e.printStackTrace();
			}
			finally {
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
		
		return dailys;
	}
	public void addDaily(Daily daily){
		File file = new File(userPath + "/" + daily.getUsername() + "/" +daily.getDate().getTime() + ".txt");
		BufferedWriter bw = null;
		try {
			bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "UTF-8"));
			bw.write(daily.getContent().toString());
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			if(bw!=null){
				try {
					bw.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	public void deleteDaily(Daily daily){
		File file = new File(userPath + "/" + daily.getUsername() + "/" + daily.getDate().getTime() + ".txt");
		if(file.exists()){
			while(!file.delete()){
				throw new RuntimeException("删除失败！");
			}
		}
		else{
			throw new RuntimeException("删除失败！");
		}
	}

	class TxtFilenameFilter implements FilenameFilter{
		@Override
		public boolean accept(File dir, String name) {
			// TODO Auto-generated method stub
			return name.endsWith(".txt");
		}
	}

	@Override
	public List<Daily> getSubjectDailys(Daily blah) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<String> getSubjects(Daily blah) {
		// TODO Auto-generated method stub
		return null;
	}

	
//	public class Cmp implements Comparator<Date>{
//		@Override
//		public int compare(Date o1, Date o2) {
//			// TODO Auto-generated method stub
//			return -o1.compareTo(o2);
//		}
//	}

}
