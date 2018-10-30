package com.geo.gnss.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 
 * Dir: .../log/2018-09-18.txt
 *
 */
public class DownloadLog {

	private String appPath;
	
	public DownloadLog(String appPath) {
		this.appPath = appPath;
	}
	
	public void write(String user, String content){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = sdf.format(new Date());
        
        StringBuilder sb = new StringBuilder();
        sb.append(appPath);
        sb.append(File.separator);
        sb.append("log");
		String logDir = sb.toString();
		
		sb.append(File.separator);
		sb.append(dateString);
		sb.append(".txt");
		String logPath = sb.toString();
		
		System.out.println(logPath);
		File logDirFile = new File(logDir);
		if(!logDirFile.exists()){
			logDirFile.mkdirs();
		}
		
		try {
			File logFile = new File(logPath);
			if(!logFile.exists()){
				logFile.createNewFile();
			}
			
			SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	        String logDateString = dateformat.format(new Date());
	        
			FileWriter fw = new FileWriter(logFile, true);
			String writeLine = String.format("[%s]\t%s\t%s\r\n", logDateString, user, content);
			fw.write(writeLine);
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
