package com.geo.gnss.File;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.geo.gnss.dao.FileAttrDao;

/**
 * Servlet implementation class SkinFileServlet
 */
@WebServlet("/SkinFile")
public class ServletSkinFile extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String rawPath = "";
	private String type = "";
	
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	doGet(request, response);
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		rawPath = (String)getServletContext().getAttribute("rawPath");
		
		String absoluteParentPath = getDataPath(request);
		
		//preventing path length changes caused by escaped characters in different systems
		//File rawFile = new File(rawPath);
		//rawPath = rawFile.getAbsolutePath();
		
		//System.out.println(absoluteParentPath);
		String json = getFileListJson(absoluteParentPath);
		//System.out.println(json);
		response.getWriter().print(json);
	}
	
	private String getDataPath(HttpServletRequest request) {
		type = request.getParameter("type");
		String date = request.getParameter("date");
		String stationName = request.getParameter("stationName");
		
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
		if("RawData".equals(type)){
			sb.append(rawPath);
			sb.append(File.separator);
			sb.append(year);
			sb.append(File.separator);
			sb.append(dayOfYear);
			sb.append(File.separator);
			sb.append(stationName);
		} else if("DailyRinexData".equals(type)){
			sb.append(rawPath);
			sb.append(File.separator);
			sb.append("Daily");
			sb.append(File.separator);
			sb.append(year);
			sb.append(File.separator);
			sb.append(dayOfYear);
			sb.append(File.separator);
			sb.append(stationName);
		}
		
		return sb.toString();
	}
	
	protected String getFileListJson(String parentPath){
		List<FileAttrDao> attrList = getAttList(parentPath);
		
		if(attrList == null){
			return "{\"file\":[]}";
		}
		
        String json = null,strTem = null;
	    Iterator<FileAttrDao> it = attrList.iterator();
		
	    json = "{\"rootPath\":\"" + replaceEscapedChar(parentPath.substring(rawPath.length())) +"\",\"file\":[";
		while(it.hasNext()){
			FileAttrDao obj = it.next();
			strTem = "{\"name\":\"" + obj.getName() 
			+ "\",\"type\":\"" + obj.getType()
			+ "\",\"size\":\"" + obj.getSize()
			+ "\",\"lastModifyTime\":\"" + obj.getLastModifyTime()
			+ "\",\"relativePath\":\"" + obj.getRelativePath()
			+ "\",\"date\":\"" + obj.getDate()
			+ "\",\"startDateTime\":\"" + obj.getStartDateTime()
			+ "\",\"endDateTime\":\"" + obj.getEndDateTime()
			+ "\"}";
			
			if(obj != attrList.get(0)){
				json += ",";
			}
			json += strTem;
		}
		json += "]}";
		//System.out.println(json);
		return json;
	}
	
	protected List<FileAttrDao> getAttList(String parentPath){
		File parentDir = new File(parentPath);
		File[] fileList = parentDir.listFiles();
		
		if(fileList == null || fileList.length <= 0 ){
			return null;
		}
		
		List<FileAttrDao> attrList = new ArrayList<FileAttrDao>();
		List<FileAttrDao> fileAttrList = new ArrayList<FileAttrDao>();
		
		//first, add uplevel dir //Judge whether the path names are equal
		//delete uplevel dir
//		if(parentDir.compareTo(new File(rawPath)) != 0){
//			fileAttr = new FileAttrDao();
//			fileAttr.setName("...");
//			fileAttr.setType(0);
//			fileAttr.setSize("");
//			fileAttr.setLastModifyTime("");
//			fileAttr.setRelativePath(replaceEscapedChar(parentDir.getParent().substring(rawPath.length())));
//			attrList.add(fileAttr);
//		}
		
		for(File file : fileList){
			if(file.isFile() && isFilterFile(file)){
				continue;
			}
			
			//delete dir
			if(file.isDirectory()){
				continue;
			}
			
			FileAttrDao fileAttr = null;
			fileAttr = new FileAttrDao();
 
			fileAttr.setName(file.getName());
			fileAttr.setType(file.isDirectory() ? 0 : 1);
			
			DecimalFormat df = new DecimalFormat("0.00");
			String size = df.format((double)file.length()/1024);
			fileAttr.setSize(file.isDirectory() ? "folder" : size+"kb");
			
			Calendar cal = Calendar.getInstance();
			cal.setTimeInMillis(file.lastModified());
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String lastModifyTime = dateFormat.format(cal.getTime());
			fileAttr.setLastModifyTime(lastModifyTime);
			
			String absolutePath = file.getAbsolutePath();
			String relativePath = absolutePath.substring(rawPath.length()+1);
			fileAttr.setRelativePath(replaceEscapedChar(relativePath));
			
			//
			String name = file.getName();
			String ext = name.substring(name.lastIndexOf(".")+1);
			ext = ext.toUpperCase();
			
			if(ext.equals("DAT") || ext.equals("RAW") || ext.equals("RT17") ||
    				ext.equals("RT27") || ext.equals("STH") || ext.equals("ZHD") ||
    				ext.equals("BIN")){
				getStaticHead(file, fileAttr);
    		}
			
			//put the folder to the front 
			if(file.isDirectory() ){
				attrList.add(fileAttr);
			} else {
				fileAttrList.add(fileAttr);
			}
		}
		
		attrList.addAll(fileAttrList);
		
		return attrList;
	}
	
	private void getStaticHead(File file, FileAttrDao fileAttr) {
		byte[] data = new byte[1000];
		
		try {
			FileInputStream inputStream = new FileInputStream(file);
			inputStream.read(data, 0, 0x200);
			inputStream.close();
		} catch (Exception e) {
		}
		
		if(data[0]==0x4E && data[1]==0x47 && data[2]==0x53 && data[3]==0x2D
				&& data[4]==0x47 && data[5]==0x50 && data[6]==0x53 && data[7]==0x20){
			readHead(data, fileAttr);
		}
		else if (data[0]=='$' && data[1]=='>' && data[2]=='J' && data[3]=='S' && data[4]=='I'&&
				data[5]=='T' && data[6]=='E' && data[7]=='R' && data[8]=='E' && data[9]=='C' &&
						data[10]=='O' && data[11]=='R'&& data[12]=='D') {
			
		}
	}

	private void readHead(byte[] bt, FileAttrDao fileAttr) {
		String ss = "";
		//int ii = 0;
		double dd= 0.0;
		
		byte[] destHead = new byte[53];
		System.arraycopy(bt, 0, destHead, 0, 53);
		ss = new String(destHead);
		//System.out.println("Head:" + ss);
		
		int year, month, day;
		day = new Integer(bt[58]);
		//System.out.println("Day:" + day);
		
		month = new Integer(bt[59]);
		//System.out.println("Month:" + month);
		
		byte[] destYear = new byte[2];
		System.arraycopy(bt, 60, destYear, 0, 2);
		year = byteToInt(destYear);
		//System.out.println("Year:" + year);
		fileAttr.setDate(String.format("%d-%d-%d", year, month, day));
		
		byte[] destCoor = new byte[8];
		byte[] destCoor3 = new byte[4];
		System.arraycopy(bt, 80, destCoor, 0, 8);
		dd = byteToDouble(destCoor);
		//System.out.println("Coor1:" + dd);
		System.arraycopy(bt, 88, destCoor, 0, 8);
		dd = byteToDouble(destCoor);
		//System.out.println("Coor2:" + dd);
		System.arraycopy(bt, 96, destCoor3, 0, 4);
		dd = byteTofloat(destCoor3);
		//System.out.println("Coor3:" + dd);
		
		byte[] destAnHeight = new byte[2];
		System.arraycopy(bt, 105, destAnHeight, 0, 2);
		dd = byteToInt(destAnHeight);
		//System.out.println("AntennaHeight:" + dd/1000);
		fileAttr.setAntennaHeight(String.valueOf(dd/1000));
		
		byte[] destTime = new byte[8];
		System.arraycopy(bt, 107, destTime, 0, 8);
		ss = new String(destTime);
		//System.out.println("StartTime:" + ss);
		fileAttr.setStartDateTime(ss);
		
		//System.arraycopy(bt, 115, destTime, 0, 8);
		//ss = new String(destTime);
		//System.out.println("EndTime:" + ss);
		//fileAttr.setEndDateTime(ss);
		
		//ii = new Integer(bt[123]);
		//System.out.println("AntennaType:" + ii);
	}

	//filter file
	protected boolean isFilterFile(File file){
		String name = file.getName();
		String ext = name.substring(name.lastIndexOf(".")+1);
		ext = ext.toUpperCase();
		
		boolean isFilter = false;
//		if(!ext.equals("DAT")){
//			isFilter = true;
//		}
		
		if("RawData".equals(type)){
			if(!ext.equals("DAT")){
				isFilter = true;
			}
		} else if("DailyRinexData".equals(type)){
			ext = name.substring(name.length()-1);
			ext = ext.toUpperCase();
			if(!ext.equals("C") && !ext.equals("G") && !ext.equals("L") && !ext.equals("N") && !ext.equals("O")){
				isFilter = true;
			}
		}
			
		return isFilter;
	}
	
	protected String replaceEscapedChar(String path){
		//return path.replace(File.separator, "/");
		return path.replace("\\", "\\\\");
	}
	
	public int byteToInt(byte[] b){          
	    int value= 0;
	    for(int i=0;i<b.length;i++){                
	        int n=(b[i]<0?(int)b[i]+256:(int)b[i])<<(8*i);             
	        value+=n;
	    }         
	    return value;       
	}
	
	
	 public double byteToDouble(byte[] arr) {  
	        long value = 0;  
	        for (int i = 0; i < 8; i++) {  
	            value |= ((long) (arr[i] & 0xff)) << (8 * i);  
	        }  
	        return Double.longBitsToDouble(value);  
	    }  
	    
	    public float byteTofloat(byte[] b) {    
	        int l;                                             
	        l = b[0];                                  
	        l &= 0xff;                                         
	        l |= ((long) b[1] << 8);                   
	        l &= 0xffff;                                       
	        l |= ((long) b[2] << 16);                  
	        l &= 0xffffff;                                     
	        l |= ((long) b[3] << 24);                  
	        return Float.intBitsToFloat(l);                    
	    } 


}
