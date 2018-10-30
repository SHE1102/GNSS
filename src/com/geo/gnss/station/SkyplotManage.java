package com.geo.gnss.station;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class SkyplotManage {
    private String skyplotPath;
    private List<SkyplotInfo> skyplotList = new ArrayList<>();
    
	public SkyplotManage(String rawPath, String stationName) {
		skyplotPath = rawPath + File.separator + "StationState" + File.separator + stationName + ".xml";
	}
	
	protected void read() throws Exception{
		SAXReader reader = new SAXReader();
    	Document document = reader.read(new File(skyplotPath));
    	
    	Element root = document.getRootElement();
    	Element stationTag = root.element("Station");
		Element stationListTag = stationTag.element("SatelliteList");
		List<Element> satelliteTagList = stationListTag.elements("Satellite");
		
		for(int i=0; i<satelliteTagList.size(); i++){
			SkyplotInfo skyplotInfo = new SkyplotInfo();
			Element node = satelliteTagList.get(i);
			
			skyplotInfo.parseSkyplot(node.getText());
			skyplotList.add(skyplotInfo);
		}
	}

	public String getJson(){
		
		try {
			read();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		String json = null,strTem = null;
        Iterator<SkyplotInfo> it = skyplotList.iterator();
		
        json = "{\"satellite\":[";
		while(it.hasNext()){
			SkyplotInfo obj = it.next();
			
			strTem = "{\"type\":\"" + obj.getType()
			+"\",\"id\":\"" + obj.getId()
			+"\",\"azimuth\":\"" + obj.getAzimuth()
			+"\",\"elevation\":\"" + obj.getElevation()
			+"\"}";
			
			if(obj != skyplotList.get(0)){
				json += ",";
			}
			
			json += strTem;
		}
		json += "]}";
		
		return json;
	}

}
