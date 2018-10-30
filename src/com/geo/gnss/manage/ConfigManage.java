package com.geo.gnss.manage;

import java.io.File;
import java.io.FileOutputStream;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import com.geo.gnss.dao.ConfigDao;

public class ConfigManage {
    private String configPath;
	
    //考虑是否存储到数据库
	public ConfigManage(String configPath) {
		this.configPath = configPath;
	}
	
	public ConfigDao readConfig(){
        ConfigDao configDao = new ConfigDao();
        
        File configXml = new File(configPath);
        if(!configXml.exists()){
        	return configDao;
        }
		
		try {
			SAXReader reader = new SAXReader();
			Document document = reader.read(new File(configPath));
			
			String content = "" ;
	    	Element root = document.getRootElement();
	    	content = root.elementTextTrim("RawPath");
	    	configDao.setRawPath(content);
	    	content = root.elementTextTrim("MapCenter_Latitude");
	    	configDao.setCenterLat(Double.parseDouble(content));
	    	content = root.elementTextTrim("MapCenter_Longitude");
	    	configDao.setCenterLon(Double.parseDouble(content));
	    	content = root.elementTextTrim("Map_Zoom");
	    	configDao.setZoom(Integer.parseInt(content));
	    	content = root.elementTextTrim("Host_Email");
	    	configDao.setHostEmail(content);
	    	content = root.elementTextTrim("Host_EmailPassword");
	    	configDao.setHostEmailPassword(content);
	    	content = root.elementTextTrim("Host_EmailProtocol");
	    	configDao.setHostEmailProtocol(content);
		} catch (DocumentException e) {
			e.printStackTrace();
		}
    	
		return configDao;
	}
	
	public boolean saveConfig(ConfigDao configDao){
		Document document = DocumentHelper.createDocument();
		document.addDocType("xml","","");
		Element root = DocumentHelper.createElement("HEAD");
		document.setRootElement(root);
		
		Element elementRawPath = root.addElement("RawPath");
		elementRawPath.setText(configDao.getRawPath());
		Element elementHostEmail = root.addElement("Host_Email");
		elementHostEmail.setText(configDao.getHostEmail());
		Element elementHostEmailPassword = root.addElement("Host_EmailPassword");
		elementHostEmailPassword.setText(configDao.getHostEmailPassword());
		Element elementHostEmailProtocol = root.addElement("Host_EmailProtocol");
		elementHostEmailProtocol.setText(configDao.getHostEmailProtocol());
		Element elementCenterLat = root.addElement("MapCenter_Latitude");
		elementCenterLat.setText(String.valueOf(configDao.getCenterLat()));
		Element elementCenterLon = root.addElement("MapCenter_Longitude");
		elementCenterLon.setText(String.valueOf(configDao.getCenterLon()));
		Element elementMapZoom = root.addElement("Map_Zoom");
		elementMapZoom.setText(String.valueOf(configDao.getZoom()));
		
		boolean res = true;
		OutputFormat format = new OutputFormat("    ", true);
		try {
			XMLWriter xmlWriter = new XMLWriter(new FileOutputStream(configPath), format);
			xmlWriter.write(document);
			xmlWriter.close();
		} catch (Exception e) {
			e.printStackTrace();
			res = false;
		}
		return res;
	}

}
