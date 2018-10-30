package com.geo.gnss.thread;

import java.io.File;
import java.util.Calendar;
import java.util.Date;

import com.geo.gnss.jna.DllInterface.ToRinexDLL64;
import com.geo.gnss.util.CalConvertPar;

/**
 * 
 * Dir:Daily//2018/245//stationName//
 *
 */
public class AutoConvertRinexThread extends Thread {

	public boolean exitThread = false;
	private int nNavSys = 0;
	private String rawPath;
	private String dailyPath;
	private long currentConvertTime = 0;
	
	public AutoConvertRinexThread(String rawPath){
		this.rawPath = rawPath;
		nNavSys = new CalConvertPar().calDefineConvertPar();
	}
	
	@Override
	public void run() {
		System.out.println("Auto convert thread start.");
		
		while(true){
			
			if(exitThread){
				break;
			}
			if (isInterrupted()) {
				break;
			}
			
			try {
				Thread.sleep(1000*60*60);
			} catch (InterruptedException e) {
				e.printStackTrace();
				break;
			}
			
			StartConvert();
		}
	}
	
	private void StartConvert(){
		System.out.println("Converting...");
		currentConvertTime = System.currentTimeMillis();
		
		File file = new File(getConvertDir());
		if(!file.exists()){
			return;
		}
		
		createDailyDir();
		SkinDir(file);
	}
	
	private String getConvertDir() {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		
		StringBuilder sb = new StringBuilder();
		sb.append(rawPath);
		sb.append(File.separator);
		sb.append(calendar.get(Calendar.YEAR));
		sb.append(File.separator);
		sb.append(calendar.get(Calendar.DAY_OF_YEAR));
		
		return sb.toString();
	}

	private void createDailyDir() {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		
		StringBuilder sb = new StringBuilder();
		sb.append(rawPath);
		sb.append(File.separator);
		sb.append("Daily");
		sb.append(File.separator);
		sb.append(calendar.get(Calendar.YEAR));
		sb.append(File.separator);
		sb.append(calendar.get(Calendar.DAY_OF_YEAR));
		
		dailyPath = sb.toString();
		File dailyDir = new File(dailyPath);
		dailyDir.mkdirs();
		//System.out.println(sb.toString());
	}

	private void SkinDir(File dirFileParent){
		if(dirFileParent.isDirectory()){
			File[] dirList = dirFileParent.listFiles();
			
			for(File dirFile : dirList){
				SkinDir(dirFile);
			}
		}else if(dirFileParent.isFile()){
			String name = dirFileParent.getName();
			String ext = name.substring(name.indexOf('.'));
			System.out.println("ext:" + ext);
			if(ext.equals(".dat")){
				ConvertFile(dirFileParent);
			}
		}
	}
	
	private void ConvertFile(File file){
		long time = file.lastModified();
		//System.out.println("最后修改时间:" + time + " 当前时间:" + currentConvertTime);
		//System.out.println("文件路径:" + file.getAbsolutePath() + " 目录:" + file.getParent());
		if(currentConvertTime - time <= 60*60*1000){
			String destPath = dailyPath + File.separator + file.getParentFile().getName();
			File destDir = new File(destPath);
			destDir.mkdirs();
			
			ToRinexDLL64.instance.ParseDataToRinexFormat_HTML(file.getAbsolutePath(), destPath, nNavSys, null);
		}
	}

}
