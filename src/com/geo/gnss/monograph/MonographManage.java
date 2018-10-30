package com.geo.gnss.monograph;

import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

public class MonographManage {
    protected String path;
	public MonographManage(String appPath) {
		path = appPath + File.separator + "monograph" + File.separator + "StationsMap.xml";
	}
	
	public Map<String,String> read() throws Exception{
		File file = new File(path);
		if(!file.exists()){
			return null;
			//file.createNewFile();
		}
		
		SAXReader reader = new SAXReader();
    	Document document = reader.read(new File(path));
    	
    	Element root = document.getRootElement();
		Element stationsTag = root.element("Stations");
		List<Element> stationTagList = stationsTag.elements("STATION");
		
		Map<String,String> map = new HashMap<String,String>();
		String name="",pdf="";
		for(Element stationTag : stationTagList){
			name = stationTag.elementTextTrim("Name");
			pdf = stationTag.elementTextTrim("PDF");
			
			map.put(name, pdf);
		}
		
		return map;
	}
	
	public void addLink(String name, String link){
		Map<String, String> map=null;
		try {
			map = read();
			
			if(map == null){
				map = new HashMap<String,String>();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		map.put(name, link);
		
		Set<String> set = map.keySet();
		Iterator<String> it = set.iterator();
		
		Document document = DocumentHelper.createDocument();
		Element root = DocumentHelper.createElement("HEAD");
		document.setRootElement(root);
		
		Element StationsTag = root.addElement("Stations");
		
		while(it.hasNext()){
			String key = (String)it.next();
			String value = map.get(key);
			Element StationTag = StationsTag.addElement("STATION");
			Element NameTag = StationTag.addElement("Name");
			NameTag.setText(key);
			Element PdfTag = StationTag.addElement("PDF");
			PdfTag.setText(value);
		}
		
		OutputFormat format = new OutputFormat("    ", true);
		try {
			XMLWriter xmlWriter = new XMLWriter(new FileOutputStream(path), format);
			xmlWriter.write(document);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
