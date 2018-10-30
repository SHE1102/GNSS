package com.geo.gnss.ftpServer;

import java.io.File;
import java.io.FileOutputStream;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import com.geo.gnss.dao.FtpDao;

public class FtpConfig {
    private String configPath;
	
	public FtpConfig(String configPath) {
		this.configPath = configPath;
	}
	
	public String getFtpJson(){
		FtpDao ftpDao = readConfig();
		
		StringBuilder sb = new StringBuilder();
		sb.append("{");
		sb.append("\"port\":");
		sb.append(ftpDao.getPort());
		sb.append(",\"homeDirectory\":\"");
		sb.append(ftpDao.getHomeDirectory());
		sb.append("\",\"userName\":\"");
		sb.append(ftpDao.getUserName());
		sb.append("\",\"password\":\"");
		sb.append(ftpDao.getPassword());
		sb.append("\"");
		sb.append("}");
		
		return sb.toString();
	}
	public FtpDao readConfig(){
		FtpDao ftpDao = new FtpDao();
        
        File configXml = new File(configPath);
        if(!configXml.exists()){
        	return ftpDao;
        }
		
		try {
			SAXReader reader = new SAXReader();
			Document document = reader.read(new File(configPath));
			
			String content = "" ;
	    	Element root = document.getRootElement();
	    	content = root.elementTextTrim("Port");
	    	ftpDao.setPort(Integer.parseInt(content));
	    	content = root.elementTextTrim("HomeDirectory");
	    	ftpDao.setHomeDirectory(content);
	    	content = root.elementTextTrim("UserName");
	    	ftpDao.setUserName(content);
	    	content = root.elementTextTrim("Password");
	    	ftpDao.setPassword(content);
		} catch (DocumentException e) {
			e.printStackTrace();
		}
    	
		return ftpDao;
	}
	
	public boolean saveConfig(FtpDao ftpDao){
		Document document = DocumentHelper.createDocument();
		document.addDocType("xml","","");
		Element root = DocumentHelper.createElement("HEAD");
		document.setRootElement(root);
		
		Element elementRawPath = root.addElement("Port");
		elementRawPath.setText(String.valueOf(ftpDao.getPort()));
		Element elementHostEmail = root.addElement("HomeDirectory");
		elementHostEmail.setText(ftpDao.getHomeDirectory());
		Element elementHostEmailPassword = root.addElement("UserName");
		elementHostEmailPassword.setText(ftpDao.getUserName());
		Element elementHostEmailProtocol = root.addElement("Password");
		elementHostEmailProtocol.setText(ftpDao.getPassword());
		
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
