package com.geo.gnss.station;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.geo.gnss.monograph.MonographManage;


public class StationManage {
	private String rawPath;
	private String appPath;
	//private String stationsPath;
	
	List<Station> stationList = new ArrayList<Station>();
	
    public StationManage(String rawPath, String appPath){
    	this.rawPath = rawPath;
    	this.appPath = appPath;
    	//stationsPath = rawPath + File.separator + "Stations.xml";
    }
    
    public void read() throws Exception{
    	File file = new File(rawPath);
    	File[] listFiles = file.listFiles();
    	
    	if(listFiles == null){
    		return;
    	}
    	
    	for(File temFile : listFiles){
    		if(temFile.isFile()){
    			String temFileName = temFile.getName();
    			if(temFileName.contains("Stations") && temFileName.contains(".xml")){
					read(temFile.getAbsolutePath());
    			}
    		}
    	}
    }
    public void read( String xmlPath) throws Exception{
    	SAXReader reader = new SAXReader();
    	//Document document = reader.read(new File(stationsPath));
    	Document document = reader.read(new File(xmlPath));
    	
    	Element root = document.getRootElement();
		Element stationsTag = root.element("Stations");
		List<Element> stationTagList = stationsTag.elements("STATION");
		
		String tagName="", content="";
		for(Element stationTag : stationTagList){
			Station st = new Station();
			Iterator<Element> it = stationTag.elementIterator();
			
			while(it.hasNext()){
				Element node = it.next();
				tagName = node.getName();
				 
				if("NAME".equals(tagName)){
					content = node.getText();
					st.setName(content);
				}else if("ID".equals(tagName)){
					content = node.getText();
					st.setId(content);
				}else if("REMARK".equals(tagName)){
					content = node.getText();
					st.setRemark(content);
				}else if("ANTENNA".equals(tagName)){
					content = node.elementText("ANTENNAPAR");
				}else if("ANTENNAPOINT_CTS".equals(tagName)){
					content = node.elementText("X");
					st.setX(Double.parseDouble(content));
					content = node.elementText("Y");
					st.setY(Double.parseDouble(content));
					content = node.elementText("Z");
					st.setZ(Double.parseDouble(content));
				}else if("ANTENNAPOINT_NEU_L1".equals(tagName)){
					content = node.elementText("N");
					st.setN1(Double.parseDouble(content));
					content = node.elementText("E");
					st.setE1(Double.parseDouble(content));
					content = node.elementText("U");
					st.setU1(Double.parseDouble(content));
				}else if("ANTENNAPOINT_NEU_L2".equals(tagName)){
					content = node.elementText("N");
					st.setN2(Double.parseDouble(content));
					content = node.elementText("E");
					st.setE2(Double.parseDouble(content));
					content = node.elementText("U");
					st.setU2(Double.parseDouble(content));
				}
			}//while end
			stationList.add(st);
		}
    }
    
    public String getJson(boolean isUser){
    	try {
			read();
			getPDFLink();
			checkStationsWorkStatus();
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
    	String json = isUser ? getUserJson() : getTouristJson();
    	return json;
    }
    
    private void getPDFLink() throws Exception {
    	MonographManage monographManage = new MonographManage(appPath);
    	Map<String,String> map = monographManage.read();
    	if(map == null){
    		return;
    	}
    	
    	for(Station st:stationList){
    		String key = st.getName();
    		if(map.containsKey(key)){
    			String link = map.get(st.getName());
    			st.setPDFLink(link);
    		}
    	}
	}

	private String getTouristJson() {
    	String json = null,strTem = null;
		Iterator<Station>it = stationList.iterator();
		
		json = "{\"station\":[";
		while(it.hasNext()){
			Station obj = it.next();
			
			strTem = "{\"name\":\"" + obj.getName()
			+"\",\"id\":\"" + obj.getId()
			+"\",\"pdf\":\"" + obj.getPDFLink()
			+"\",\"latitude\":\"" + obj.getLatitude()
			+"\",\"longitude\":\"" + obj.getLongitude()
			+"\",\"latitudeFormat\":\"" + obj.getLatitudeText()
			+"\",\"longitudeFormat\":\"" + obj.getLongitudeText()
			+"\",\"altitude\":\"" + obj.getHeight()
			+"\"}";
			
			if(obj != stationList.get(0)){
				json += ",";
			}
			
			json += strTem;
		}
		json += "]}";
		
		return json;
	}

	private String getUserJson() {
    	try {
			getStationsSatellites();
		} catch (DocumentException e) {
			e.printStackTrace();
		}
    	
    	String json = null,strTem = null;
		Iterator<Station>it = stationList.iterator();
		
		json = "{\"station\":[";
		while(it.hasNext()){
			Station obj = it.next();
			String workStatus = obj.isWorkFlag() ? "active" : "inactive";
			
			strTem = "{\"name\":\"" + obj.getName()
			+"\",\"id\":\"" + obj.getId()
			+"\",\"pdf\":\"" + obj.getPDFLink()
			+"\",\"workFlag\":\"" + workStatus
			+"\",\"gps\":" + obj.getGpsCount()
			+",\"glonass\":" + obj.getGlonassCount()
			+",\"beidou\":" + obj.getBeidouCount()
			+",\"latitude\":\"" + obj.getLatitude()
			+"\",\"longitude\":\"" + obj.getLongitude()
			+"\",\"latitudeFormat\":\"" + obj.getLatitudeText()
			+"\",\"longitudeFormat\":\"" + obj.getLongitudeText()
			+"\",\"altitude\":" + obj.getHeight()
			+",\"distance\":\"" + obj.getTargetDistanceText()
			+"\"}";
			
			if(obj != stationList.get(0)){
				json += ",";
			}
			
			json += strTem;
		}
		json += "]}";
		
		return json;
	}
	
	private void checkStationsWorkStatus(){
    	if(stationList.size() <= 0){
    		return;
    	}
    	
		String stationStateDir = rawPath + File.separator + "StationState";
		File stationDirFile = new File(stationStateDir);
		File[] stationFileList = stationDirFile.listFiles();
		
		List<Station> stationListTem = new ArrayList<Station>();
		
		for(Station st : stationList){
			boolean findStation = false;
			
			File fileTem = null;
			for(File file : stationFileList){
				String name = file.getName();
				name = name.substring(0, name.indexOf("."));
				
				if(st.getName().equals(name)){
					findStation = true;
					fileTem = file;
					break;
				}
			}
			
			if(!findStation){
				continue;
			}
			
			if(!isStationEnable(fileTem.getAbsolutePath())){
				continue;
			}
			
			st.setWorkFlag(getStationXmlStatus(fileTem.getAbsolutePath()));
			stationListTem.add(st);
		}
		
		stationList.clear();
		stationList.addAll(stationListTem);
	}
	
	private void getStationsSatellites() throws DocumentException {
		for(Station st : stationList){
			if(st.isWorkFlag()){
				String stationXmlPath = rawPath + File.separator + "StationState" + File.separator + st.getName() + ".xml";
				
				SAXReader reader = new SAXReader();
				Document document = reader.read(new File(stationXmlPath));
				Element root = document.getRootElement();
		    	Element stationTag = root.element("Station");
				Element stationListTag = stationTag.element("SatelliteList");
				List<Element> satelliteTagList = stationListTag.elements("Satellite");
				
				int gps=0,glonass=0,beidou=0;
				for(int i=0; i<satelliteTagList.size(); i++){
					SkyplotInfo skyplotInfo = new SkyplotInfo();
					Element node = satelliteTagList.get(i);
					String type = skyplotInfo.parseSkyplot(node.getText());
					
					if("G".equals(type)){
						gps++;
					}else if("R".equals(type)){
						glonass++;
					} else if("C".equals(type)){
						beidou++;
					}
				}
				
				st.setGpsCount(gps);
				st.setGlonessCount(glonass);
				st.setBeidouCount(beidou);
			}
		}
	}
    
	/**
	 * 
	 * @param stationXmlPath
	 * @return return single station xml work status
	 */
  	private boolean  isStationEnable(String stationXmlPath){
  		boolean status = false;
  		try {
			SAXReader reader = new SAXReader();
			Document document = reader.read(new File(stationXmlPath));
			
			String currentTime="";
	    	Element root = document.getRootElement();
	    	Element stationTag = root.element("Station");
	    	currentTime = stationTag.elementTextTrim("CurrentTime");
	    	
	    	SimpleDateFormat sDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
			
			Date currentDate = sDateFormat.parse(currentTime);
			Date nowDate = getUTCTime();
			 
			if(Math.abs(currentDate.getTime() - nowDate.getTime()) <= 30*1000){
				status = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
  		
  		return status;
  	}
  	
  	private Date getUTCTime(){
        // 1、取得本地时间：  
        Calendar cal = Calendar.getInstance() ;  
        // 2、取得时间偏移量：  
        int zoneOffset = cal.get(java.util.Calendar.ZONE_OFFSET);  
        // 3、取得夏令时差：  
        int dstOffset = cal.get(java.util.Calendar.DST_OFFSET);  
        // 4、从本地时间里扣除这些差量，即可以取得UTC时间：  
        cal.add(java.util.Calendar.MILLISECOND, -(zoneOffset + dstOffset));  
        //int year = cal.get(Calendar.YEAR);  
        //int month = cal.get(Calendar.MONTH)+1;  
        //int day = cal.get(Calendar.DAY_OF_MONTH);  
        //int hour = cal.get(Calendar.HOUR_OF_DAY);  
        //int minute = cal.get(Calendar.MINUTE);   
        //int second = cal.get(Calendar.SECOND);
        //String utcTime = String.format("%d-%d-%d %02d:%d:%02d", year, month, day, hour, minute, second);
        return cal.getTime();
  	}
  	
  	private boolean  getStationXmlStatus(String stationXmlPath){
  		boolean status = false;
  		
  		try {
			SAXReader reader = new SAXReader();
			Document document = reader.read(new File(stationXmlPath));
			
			String currentTime="", stationTime="";
	    	Element root = document.getRootElement();
	    	Element stationTag = root.element("Station");
	    	currentTime = stationTag.elementTextTrim("CurrentTime");
	    	stationTime = stationTag.elementTextTrim("StationTime");
	    	
	    	SimpleDateFormat sDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); //加上时间
			
			Date currentDate = sDateFormat.parse(currentTime);
			Date stationDate=sDateFormat.parse(stationTime);
			
			if(Math.abs(currentDate.getTime() - stationDate.getTime()) <= 30*1000){
				status = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
  		
  		return status;
  	}
    
}
