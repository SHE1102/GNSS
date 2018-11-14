package com.geo.gnss.solution;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.LineNumberReader;

public class UploadManage {

	//private String oFilePath="C:\\Users\\geo\\Desktop\\33KM3130305.18O";
	//private String datFilePath = "C:\\Users\\geo\\Desktop\\33KM313e.dat";
	private SolutionParameter solutionParameter = new SolutionParameter();

	public SolutionParameter getSolutionParameter() {
		return solutionParameter;
	}
	
	public void parseOFile(String filePath){
		
		double coorX = 0.0, coorY = 0.0, coorZ = 0.0;
		try {
			LineNumberReader lr = new LineNumberReader(new FileReader(filePath));
	
			String line = "";
			while((line=lr.readLine()) != null){
				if(line.contains(" APPROX POSITION XYZ")){
					line = line.substring(0, line.indexOf(" APPROX POSITION XYZ"));
					line = line.trim();
					String[] coorArray = line.split("  ");
					coorX = Double.parseDouble(coorArray[0]);
					coorY = Double.parseDouble(coorArray[1]);
					coorZ = Double.parseDouble(coorArray[2]);
					System.out.println("Dynamic:x="+coorX + " y="+coorY+" z="+coorZ);
				} else if(line.contains("TIME OF FIRST OBS")){
					line = line.replaceAll("\\s+", " ");
					line = line.substring(0, line.indexOf("GPS TIME OF FIRST OBS"));
					line = line.trim();
					String[] startTimeArray = line.split(" ");
					String startTime = String.format("%s-%s-%s %s:%s:%s", startTimeArray[0],startTimeArray[1],startTimeArray[2],startTimeArray[3],startTimeArray[4],startTimeArray[5]);
					System.out.println("startTime:" + startTime);
					String[] timeArray = startTime.split(" ");
					solutionParameter.setDate(timeArray[0]);
					solutionParameter.setStartTime(timeArray[1]);
				} else if(line.contains("TIME OF LAST OBS")){
					line = line.replaceAll("\\s+", " ");
					line = line.substring(0, line.indexOf("GPS TIME OF LAST OBS"));
					line = line.trim();
					String[] endTimeArray = line.split(" ");
					String endTime = String.format("%s-%s-%s %s:%s:%s", endTimeArray[0],endTimeArray[1],endTimeArray[2],endTimeArray[3],endTimeArray[4],endTimeArray[5]);
					System.out.println("endTime:" + endTime);
					String[] timeArray = endTime.split(" ");
					solutionParameter.setEndTime(timeArray[1]);
					break;
				}
			}
			lr.close();
		} catch (Exception e) {
			//e.printStackTrace();
		}
	}
	
	public void parseDatFile(String filePath){
		File datFile = new File(filePath);
		getStaticHead(datFile);
	}
	
	private void getStaticHead(File file) {
		byte[] data = new byte[1000];
		
		try {
			FileInputStream inputStream = new FileInputStream(file);
			inputStream.read(data, 0, 0x200);
			inputStream.close();
		} catch (Exception e) {
		}
		
		if(data[0]==0x4E && data[1]==0x47 && data[2]==0x53 && data[3]==0x2D
				&& data[4]==0x47 && data[5]==0x50 && data[6]==0x53 && data[7]==0x20){
			readHead(data);
		}
	}
	
	private void readHead(byte[] bt) {
		String ss = "";
		double dd= 0.0;
		
		//byte[] destHead = new byte[53];
		//System.arraycopy(bt, 0, destHead, 0, 53);
		//ss = new String(destHead);
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
		String date = String.format("%d-%02d-%02d", year, month, day);
        solutionParameter.setDate(date);
		
		byte[] destCoor = new byte[8];
		byte[] destCoor3 = new byte[4];
		System.arraycopy(bt, 80, destCoor, 0, 8);
		dd = byteToDouble(destCoor);
		System.out.println("Coor1:" + dd);
		System.arraycopy(bt, 88, destCoor, 0, 8);
		dd = byteToDouble(destCoor);
		System.out.println("Coor2:" + dd);
		System.arraycopy(bt, 96, destCoor3, 0, 4);
		dd = byteTofloat(destCoor3);
		System.out.println("Coor3:" + dd);
		
		//byte[] destAnHeight = new byte[2];
		//System.arraycopy(bt, 105, destAnHeight, 0, 2);
		//dd = byteToInt(destAnHeight);
		//System.out.println("AntennaHeight:" + dd/1000);
		
		byte[] destTime = new byte[8];
		System.arraycopy(bt, 107, destTime, 0, 8);
		ss = new String(destTime);
		System.out.println("StartTime:" + ss);
		solutionParameter.setStartTime(ss);
		
		System.arraycopy(bt, 115, destTime, 0, 8);
		ss = new String(destTime);
		System.out.println("EndTime:" + ss);
		solutionParameter.setEndTime(ss);
		
		//ii = new Integer(bt[123]);
		//System.out.println("AntennaType:" + ii);
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
