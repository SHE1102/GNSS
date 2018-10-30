package com.geo.gnss.convertRinex;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Files;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.geo.gnss.dao.ConvertParDao;
import com.geo.gnss.dao.ProgressDao;
import com.geo.gnss.jna.DllInterface.MergeDLL64;
import com.geo.gnss.jna.DllInterface.PostPositionDLL64;
import com.geo.gnss.jna.DllInterface.ToRinexDLL64;
import com.geo.gnss.progress.ProgressManage;
import com.sun.jna.ptr.DoubleByReference;

public class ConvertManage {
	private int nNavSys;
	
	private String rawPath;
	private String appPath;
	private String sessionId;
	private ConvertParDao convertParDao;
	
	 public String outDirPath,progressPath,saveFilePath;
	
	public ConvertManage(String rawPath, String appPath, String sessionId, ConvertParDao convertParDao){
		this.rawPath = rawPath;
		this.appPath = appPath;
		this.sessionId = sessionId;
		this.convertParDao = convertParDao;
	}
	
	public boolean Convert(){
		Initialize();
		
		List<String> sourceFileList = getTimeRangFiles(getParentPath());
		
		outDirPath = getOutDirPath();
		progressPath = getProgressFilePath();
		saveFilePath = getSaveFilePath();
		
		Thread thread = new Thread(new Runnable() {
			
			@Override
			public void run() {
				System.out.println("Convert start!");
				
				StringBuilder combineSb = new StringBuilder();
				for(String sourceFile : sourceFileList){
					File srcFile = new File(sourceFile);
					
					String tempSourceFile = outDirPath + File.separator + srcFile.getName();
					File destFile = new File(tempSourceFile);
					
					try {
						Files.copy(srcFile.toPath(), destFile.toPath());
					} catch (IOException e) {
						e.printStackTrace();
					}
					
					combineSb.append(tempSourceFile);
					combineSb.append("?");
				}
				String  combineString = combineSb.toString();
				
				//System.out.println("组合文件:" + combineString);
				//System.out.println("合并文件:" + saveFilePath);
				
				boolean res = MergeDLL64.instance.MergeRawFile(combineString, saveFilePath);
				System.out.println("Merge finish!Result:" + res);
				
				boolean bFlag = false;
				bFlag = Convert(saveFilePath, outDirPath);
				System.out.println("Convert finish!");
				
				ProgressDao progress = new ProgressDao();
				if (bFlag) {
					progress.stepName = "Finish!";
					progress.pos = 100;
					progress.total = 100;
					progress.success = 2;
					
					ProgressManage progressManage = new ProgressManage(progressPath);
					progressManage.saveConfig(progress);
				}
			}
		});
		
		if(sourceFileList == null || sourceFileList.size() == 0){
			ProgressDao progress = new ProgressDao();
			progress.stepName = "No files in the time period you set!";
			progress.pos = 0;
			progress.total = 100;
			progress.success = 0;
			
			ProgressManage progressManage = new ProgressManage(progressPath);
			progressManage.saveConfig(progress);
			return false;
		}
		
		thread.start();
		
		return true;
	}
	
	private String getParentPath() {
		String date = convertParDao.getDate();
		String stationName = convertParDao.getStationName();
		
		SimpleDateFormat sDateFormat=new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		try {
			Date currentDate = sDateFormat.parse(date);
			calendar.setTime(currentDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		int year = calendar.get(Calendar.YEAR);
		int dayOfYear = calendar.get(Calendar.DAY_OF_YEAR);
		
		StringBuilder sb = new StringBuilder();
		sb.append(rawPath);
		sb.append(File.separator);
		sb.append(year);
		sb.append(File.separator);
		sb.append(dayOfYear);
		sb.append(File.separator);
		sb.append(stationName);
		
		return sb.toString();
	}
	
	private List<String> getTimeRangFiles(String parentPath) {
		File parentDir = new File(parentPath);
		File[] fileList = parentDir.listFiles();
		
		if(fileList == null || fileList.length <= 0){
			return null;
		}
		
		List<String> filterFileList = new ArrayList<String>();
		
		for(File file : fileList){
			if(file.isDirectory()){
				continue;
			}
			
			String name = file.getName();
			String ext = name.substring(name.lastIndexOf(".")+1);
			ext = ext.toUpperCase();
			
			if(ext.equals("DAT") || ext.equals("RAW") || ext.equals("RT17") ||
    				ext.equals("RT27") || ext.equals("STH") || ext.equals("ZHD") ||
    				ext.equals("BIN")){
				if(parseHead(file)){
					System.out.println(file.getAbsolutePath());
					filterFileList.add(file.getAbsolutePath());
				}
    		}
		}
		
		return filterFileList;
	}
	
	private boolean parseHead(File file){
       byte[] data = new byte[1000];
		
		try {
			FileInputStream inputStream = new FileInputStream(file);
			inputStream.read(data, 0, 0x200);
			inputStream.close();
		} catch (Exception e) {
		}
		
		boolean res = false;
		if(data[0]==0x4E && data[1]==0x47 && data[2]==0x53 && data[3]==0x2D
				&& data[4]==0x47 && data[5]==0x50 && data[6]==0x53 && data[7]==0x20){
			res = readHead(data);
		}
		else if (data[0]=='$' && data[1]=='>' && data[2]=='J' && data[3]=='S' && data[4]=='I'&&
				data[5]=='T' && data[6]=='E' && data[7]=='R' && data[8]=='E' && data[9]=='C' &&
						data[10]=='O' && data[11]=='R'&& data[12]=='D') {
			
		}
		
		return res;
	}
	
	private boolean readHead(byte[] bt) {
		int zone = convertParDao.getZone() - 12;
		String ss = "";
		
		byte[] destTime = new byte[8];
		System.arraycopy(bt, 107, destTime, 0, 8);
		ss = new String(destTime);
		int startHour = Integer.parseInt(ss.substring(0,ss.indexOf(":")));
		startHour += zone;
		//System.out.println("StartTime:" + startHour);
		
		System.arraycopy(bt, 115, destTime, 0, 8);
		ss = new String(destTime);
		if(ss.indexOf(":") == -1){
			return false;
		}
		
		int endHour = Integer.parseInt(ss.substring(0,ss.indexOf(":")));
		endHour += zone;
		//System.out.println("EndTime:" + endHour);
		
		boolean res = false;
		int setStartHour=0,setEndHour=0;
		String setStartHourString = convertParDao.getStartTime();
		String setEndHourString = convertParDao.getEndTime();
		setStartHour = Integer.parseInt(setStartHourString.substring(0, setStartHourString.indexOf(":")));
		setEndHour = Integer.parseInt(setEndHourString.substring(0, setEndHourString.indexOf(":")));
		
		int setEndHourMin = Integer.parseInt(setEndHourString.substring(setEndHourString.indexOf(":")+1));
		
		if(setEndHourMin > 0 && setEndHourMin <= 59){
			setEndHour += 1;
		}
		//System.out.println("SetEndTime:" + setEndHour);
		if(setEndHour == 0 && startHour >= setStartHour){
			res = true;
		} else if(endHour == 0){
			if(startHour >= setStartHour && endHour <= setEndHour && startHour < endHour){
				res = true;
			}
		} else {
			if(startHour >= setStartHour && endHour <= setEndHour){
				res = true;
			}
		}
		
		return res;
	}
	
	private void Initialize(){
		if (convertParDao.isSatelliteSystem_GPS())
		{
			nNavSys |= 0x1;
		}
		if (convertParDao.isSatelliteSystem_GLO())
		{
			nNavSys |= 0x2;
		}
		if (convertParDao.isSatelliteSystem_BeiDou())
		{
			nNavSys |= 0x4;
		}
		
		if (convertParDao.isSatelliteSystem_SBAS())
		{
			nNavSys |= 0x8;
		}
		
		if (convertParDao.isSatelliteSystem_QZSS())
		{
			nNavSys |= 0x10;
		}
		
		if (convertParDao.isSatelliteSystem_Galileo())
		{
			nNavSys |= 0x20;
		}
		
		if (convertParDao.getFrequencyPoint() == 0)
		{
			nNavSys |= (1 << 8);
		}

		if (convertParDao.isOutPutPar2())
		{
			nNavSys |= (1 << 9);
		}

		if (convertParDao.isOutPutPar1())
		{
			nNavSys |= (1 << 10);
		}

		if (convertParDao.isOutPutPar3())
		{
			nNavSys |= (1 << 12);
		}

		if (convertParDao.isOutPutPar4())
		{
			nNavSys |= (1 << 13);
		}
		
		int nSel =  convertParDao.getTimeInterval();
		nNavSys |= (nSel<<16);
		
		nSel =  convertParDao.getRinexVersion();
		nNavSys |= (nSel<<24);
		
		if (nSel==1 || nSel==2 || nSel==4)
		{
			if (convertParDao.isMixTure())
			{
				nNavSys |= (1 << 11); 
			}
		}
		
	}
	
	private String getOutDirPath() {
    	String outPath = appPath + File.separator + "Convert" + File.separator + sessionId;
    	
    	File tempDir = new File(outPath);
    	
    	//if dir not exist ,create
    	//if dir exist, delete all file
    	if (!tempDir.exists()) {
			tempDir.mkdirs();
		} else {
			File[] fileList = tempDir.listFiles();
			for(File tempFile : fileList) {
				tempFile.delete();
			}
		}
    	
    	//System.out.println("outpath:" + outPath);
    	return outPath;
    }
	
	private String getProgressFilePath() {
    	String dirPath = appPath + File.separator + "Progress" + File.separator + sessionId;
		String xmlPath = dirPath + File.separator + "progress.xml";

		File dir = new File(dirPath);
		if (!dir.exists()) {
			dir.mkdirs();
		}
		
		File file = new File(xmlPath);
		
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		ProgressDao progress = new ProgressDao();
		
		progress.stepName = "Start...";
		progress.pos = 0;
		progress.total = 100;
		progress.success = 1;
		
		ProgressManage progressManage = new ProgressManage(xmlPath);
		progressManage.saveConfig(progress);
		
		return xmlPath;
    }
    
    private String getSaveFilePath() {
		String startTime = convertParDao.getStartTime();
		String endTime = convertParDao.getEndTime();
		
		SimpleDateFormat sDateFormat=new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		try {
			Date currentDate = sDateFormat.parse(convertParDao.getDate());
			calendar.setTime(currentDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		int dayOfYear = calendar.get(Calendar.DAY_OF_YEAR);
		int endTimeInt = Integer.parseInt(endTime.substring(0, endTime.indexOf(":")));
		int endTimeMinInt = Integer.parseInt(endTime.substring(endTime.indexOf(":")+1));
		if(endTimeMinInt > 0 && endTimeMinInt < 59){
			endTimeInt += 1;
		}
		
		StringBuilder sb = new StringBuilder();
		sb.append(outDirPath);
		sb.append(File.separator);
		sb.append(convertParDao.getStationName());
		sb.append(dayOfYear);
		sb.append(startTime.substring(0, startTime.indexOf(":")));
		sb.append(endTimeInt);
		sb.append("rinex");
		sb.append(".dat");
		
		return sb.toString();
	}
    
    public String getSaveFileName(){
    	String startTime = convertParDao.getStartTime();
		String endTime = convertParDao.getEndTime();
		
		SimpleDateFormat sDateFormat=new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		try {
			Date currentDate = sDateFormat.parse(convertParDao.getDate());
			calendar.setTime(currentDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		int dayOfYear = calendar.get(Calendar.DAY_OF_YEAR);
		
		int endTimeInt = Integer.parseInt(endTime.substring(0, endTime.indexOf(":")));
		int endTimeMinInt = Integer.parseInt(endTime.substring(endTime.indexOf(":")+1));
		if(endTimeMinInt > 0 && endTimeMinInt < 59){
			endTimeInt += 1;
		}
		
		StringBuilder sb = new StringBuilder();
		sb.append(convertParDao.getStationName());
		sb.append(dayOfYear);
		sb.append(startTime.substring(0, startTime.indexOf(":")));
		sb.append(endTimeInt);
		sb.append("rinex");
		
		return sb.toString();
    }
	
	private boolean Convert(String sourceFile, String destPath){
		
		System.out.println("Source Path:" + sourceFile);
		System.out.println("Dest Path:" + destPath);
		
		boolean flag = false;
		flag = ToRinexDLL64.instance.ParseDataToRinexFormat_HTML(sourceFile,destPath,nNavSys,progressPath);
		System.out.println("ConvertFlag:" + flag);
		
		if (flag) {
			if ((nNavSys>>13 & 0x1) > 0) {
				
				String[] filePathArray = new String[2];
				filePathArray[0] = destPath;
				
				DoubleByReference coor1 = new DoubleByReference(0);
				DoubleByReference coor2 = new DoubleByReference(0);
				DoubleByReference coor3 = new DoubleByReference(0);
				
				if(SearchFile(filePathArray) && 
						PostPositionDLL64.instance.SinglePositionAndroid(filePathArray[1], coor1, coor2, coor3, null)){
					System.out.println("ModifyHead:" + true + ";Coor1:" + coor1.getValue() + 
							";Coor2:" + coor2.getValue() + ";Coor3:" +coor3.getValue() );
					
					ModifyStationCoord(filePathArray[1],coor1.getValue(),coor2.getValue(),coor3.getValue());
				}
			}
		}
		
		return flag;
	}
	
	private boolean SearchFile(String[] filePathArray){
		//int nPos = filePathArray[0].lastIndexOf("\\");
		//String filePath = filePathArray[0].substring(0,nPos);
		String filePath = filePathArray[0];
		System.out.println("Convert File:" + filePath);
		
		File fileDir = new File(filePath);
		String[] fileList = fileDir.list();
		
		for(String pathName : fileList){
			if(pathName.endsWith("O")){
				filePathArray[1] = filePath + "\\" + pathName;
				System.out.println("O File:" + filePathArray[1]);
				return true;
			}
		}
		
		return false;
	}
	
	private boolean ModifyStationCoord(String fileName, double coor1, double coor2, double coor3){
		int nLength = fileName.length();
		if (nLength < -1 || !fileName.endsWith("O")) {
			return false;
		}
		
		File file = new File(fileName);
		
		try {
			RandomAccessFile writeFile = new RandomAccessFile(file, "rw");
			   
			long nPosStart = 0;
			while (true) {
				nPosStart = writeFile.getFilePointer();
				
				String readLine = writeFile.readLine();
				//System.out.println(readLine);
				
				if(readLine.contains("APPROX POSITION XYZ")){
					String newLine = String.format("%14.4f%14.4f%14.4f%18s%-20s\r\n",coor1, coor2, coor3, " ", "APPROX POSITION XYZ");
				        
					System.out.println("Seek:" + nPosStart );
					System.out.println("newLine:" + newLine );
					writeFile.seek(nPosStart);
					writeFile.write(newLine.getBytes());
						
				}
				else if (readLine.contains("END OF HEADER")) {
					break;
				}
				
			}
			
			writeFile.close();
			
		} catch (Exception e) {
		}	
		
		return true;
	}
	
}
