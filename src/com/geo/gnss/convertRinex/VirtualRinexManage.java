/**
 * 
 */
package com.geo.gnss.convertRinex;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.geo.gnss.dao.ProgressDao;
import com.geo.gnss.dao.VirtualRinexDao;
import com.geo.gnss.jna.DllInterface.VirtualRinexMakerDLL64;
import com.geo.gnss.progress.ProgressManage;

public class VirtualRinexManage /*implements Runnable*/ {
	private String rawPath;
	private String appPath;
	private String sessionId;
	private VirtualRinexDao virtualRinexDao;
	
	private String destDirPath;
	private String progressXmlPath;
	
	public VirtualRinexManage( String rawPath, String appPath, String sessionId,VirtualRinexDao virtualRinexDao) {
		this.rawPath = rawPath;
		this.appPath = appPath;
		this.sessionId = sessionId;
		this.virtualRinexDao = virtualRinexDao;
	}
	
	public boolean convert(){
		destDirPath = getDesDirPath();
		progressXmlPath = getProgressPath();
		
		Thread thread = new Thread(new Runnable() {
			
			@Override
			public void run() {
				System.out.println("Virtual convert start!");
				/*System.out.println("virtual destpath:" + destDirPath);
				System.out.println("virtual progress:" + progressXmlPath);
				System.out.println("latitude:" + virtualRinexDao.getLatitude() + 
						" longitude:" + virtualRinexDao.getLongitude() + 
						" altitude:" + virtualRinexDao.getAltitude() +
						" coordinateFormat:" + virtualRinexDao.getCoordinateFormat() +
						" date:" + virtualRinexDao.getDate() +
						" startTime:" + virtualRinexDao.getStartTime() +
						" endTime:" + virtualRinexDao.getEndTime() +
						" zone:" + virtualRinexDao.getZone() +
						" mixture:" + virtualRinexDao.isMixture() +
						" timeInterval:" + virtualRinexDao.getTimeInterval() +
						" rinexVersion:" + virtualRinexDao.getRinexVersion() +
						" frequencyPoint:" + virtualRinexDao.getFrenquencyPoint() +
						" gps:" + virtualRinexDao.isSatelliteSystem_GPS() + 
						" beidou:" + virtualRinexDao.isSatelliteSystem_BeiDou() +
						" galieo:" + virtualRinexDao.isSatelliteSystem_Galileo() +
						" glo:" + virtualRinexDao.isSatelliteSystem_GLO() +
						" qzss:" + virtualRinexDao.isSatelliteSystem_QZSS() +
						" abas:" + virtualRinexDao.isSatelliteSystem_SBAS()
						);*/
				
				int mixture = virtualRinexDao.isMixture() ? 1 : 0;
				int navsys = getNavSys();
				double timeInterval=0.0;
				int timeinter = virtualRinexDao.getTimeInterval();
				switch(timeinter){
				case 4:
					timeInterval = 1.0;
					break;
				case 5:
					timeInterval = 2.0;
					break;
				case 6:
					timeInterval = 3.0;
					break;
				case 7:
					timeInterval = 10.0;
					break;
				case 8:
					timeInterval = 15.0;
					break;
				case 9:
					timeInterval = 20.0;
					break;
				case 10:
					timeInterval = 30.0;
					break;
				case 11:
					timeInterval = 60.0;
					break;
				}
				
				int res = VirtualRinexMakerDLL64.instance.ToVirtualrinex(virtualRinexDao.getLatitude(),virtualRinexDao.getLongitude(),
						virtualRinexDao.getAltitude(),virtualRinexDao.getCoordinateFormat(),
						virtualRinexDao.getDate(),virtualRinexDao.getStartTime(),
						virtualRinexDao.getEndTime(),virtualRinexDao.getZone(),
						virtualRinexDao.getRinexVersion(),mixture,timeInterval,
						virtualRinexDao.getFrenquencyPoint()^1,navsys,
						rawPath, destDirPath,progressXmlPath);
				
				int n = virtualRinexDao.getFrenquencyPoint()^1;
				System.out.println("singleFren!" + " result:" + n);
				System.out.println("nav:" + navsys);
				System.out.println("Virtual convert finish!" + " result:" + res);
				if(res == 1) {
					ProgressDao progress = new ProgressDao();
					progress.stepName = "Finish!";
					progress.pos = 100;
					progress.total = 100;
					progress.success = 2;
					
					ProgressManage progressManage = new ProgressManage(progressXmlPath);
					progressManage.saveConfig(progress);
				}
			}
		});
		
		thread.start();
		return true;
	}
	
	private int getNavSys(){
		
		int nNavSys = 0;
		if (virtualRinexDao.isSatelliteSystem_GPS())
		{
			nNavSys |= 0x1;
		}
		if (virtualRinexDao.isSatelliteSystem_GLO())
		{
			nNavSys |= 0x2;
		}
		if (virtualRinexDao.isSatelliteSystem_BeiDou())
		{
			nNavSys |= 0x4;
		}
		
		if (virtualRinexDao.isSatelliteSystem_SBAS())
		{
			nNavSys |= 0x8;
		}
		
		if (virtualRinexDao.isSatelliteSystem_QZSS())
		{
			nNavSys |= 0x10;
		}
		
		if (virtualRinexDao.isSatelliteSystem_Galileo())
		{
			nNavSys |= 0x20;
		}
		
		return nNavSys;
	}
	
    private String getDesDirPath() {
    	String tempPath =  appPath + File.separator + "Virtual" + File.separator + sessionId;//dirName;
    	File tempDir = new File(tempPath);
    	
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
    	
    	return tempPath;
    }
    
    private String getProgressPath() {
    	String dirPath = appPath + File.separator + "Progress" + File.separator + sessionId;
    	String xmlPath = dirPath + File.separator + "progress.xml";
    	
    	File dir = new File(dirPath);
    	
    	if(!dir.exists()){
    		dir.mkdirs();
    	}
    	
    	File file = new File(xmlPath);
    	if(!file.exists()){
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
    
    public String getDownloadFileName(){
    	String startTime = virtualRinexDao.getStartTime();
		String endTime = virtualRinexDao.getEndTime();
		
		SimpleDateFormat sDateFormat=new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		try {
			Date currentDate = sDateFormat.parse(virtualRinexDao.getDate());
			calendar.setTime(currentDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		int dayOfYear = calendar.get(Calendar.DAY_OF_YEAR);
		
		StringBuilder sb = new StringBuilder();
		sb.append("Virtual");
		sb.append(dayOfYear);
		sb.append(startTime.substring(0, startTime.indexOf(":")));
		sb.append(endTime.substring(0, endTime.indexOf(":")));
		sb.append("rinex");
		
		return sb.toString();
    }

}
