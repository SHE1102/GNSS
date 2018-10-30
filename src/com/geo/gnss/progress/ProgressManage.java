package com.geo.gnss.progress;

import java.io.File;
import java.io.FileOutputStream;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import com.geo.gnss.dao.ProgressDao;

public class ProgressManage {
	
	private String dirPath;
	private String xmlPath;
	
	public ProgressManage(String xmlPath){
		this.xmlPath = xmlPath;
	}
	public ProgressManage(String appPath, String sessionDir) {
		
		dirPath = appPath + File.separator + "Progress" + File.separator + sessionDir;
		xmlPath = dirPath + File.separator + "progress.xml";
	}
	
	public String getProgressJson() {
		checkXmlPath();
		ProgressDao progress = readConfig();
		
		String json = "";
		
		if (progress != null) {
			json = "{" +
		           "\"stepName\":\"" + progress.stepName +
		           "\",\"pos\":" + progress.pos +
		           ",\"total\":" + progress.total +
		           ",\"success\":" + progress.success +
		           "}";
		}
		
		return json;
	}
	
	private void checkXmlPath() {
		//System.out.println("virtual progress file:" + xmlPath);
		/*StringBuilder builder = new StringBuilder();
		builder.append(dirPath);
		builder.append(File.separator);
		builder.append(xmlName);
		builder.append(".xml");
		xmlPath = builder.toString();*/
		
		File dir = new File(dirPath);
		if (!dir.exists()) {
			dir.mkdirs();
		}
		
		File xmlFile = new File(xmlPath);
		if (!xmlFile.exists()) {
			//System.out.println("progress File not exist!");
			ProgressDao progress = new ProgressDao();
			progress.stepName = "Start!";
			progress.pos = 1;
			progress.total = 100;
			progress.success = 1;
			
			saveConfig(progress);
		}
		
		//System.out.println("progress File2:" + xmlPath);
	}
	
	public ProgressDao readConfig(){
		ProgressDao progressDao = new ProgressDao();
		
		try {
			SAXReader reader = new SAXReader();
			Document document = reader.read(new File(xmlPath));
			
			String content = "" ;
	    	Element root = document.getRootElement();
	    	content = root.elementTextTrim("StepName");
	    	progressDao.stepName = content;
	    	content = root.elementTextTrim("Pos");
	    	progressDao.pos = Integer.parseInt(content);
	    	content = root.elementTextTrim("Total");
	    	progressDao.total = Integer.parseInt(content);
	    	content = root.elementTextTrim("SuccessFlag");
	    	progressDao.success = Integer.parseInt(content);
		} catch (DocumentException e) {
			//e.printStackTrace();
		}
    	
		return progressDao;
	}
	
	public void saveConfig(ProgressDao progress){
		Document document = DocumentHelper.createDocument();
		Element root = DocumentHelper.createElement("HEAD");
		document.setRootElement(root);
		
		Element elementStepName = root.addElement("StepName");
		elementStepName.setText(progress.stepName);
		Element elementPos = root.addElement("Pos");
		elementPos.setText(String.valueOf(progress.pos));
		Element elementTotal = root.addElement("Total");
		elementTotal.setText(String.valueOf(progress.total));
		Element elementSuccessFlag = root.addElement("SuccessFlag");
		elementSuccessFlag.setText(String.valueOf(progress.success));
		
		OutputFormat format = new OutputFormat("    ", true);
		try {
			XMLWriter xmlWriter = new XMLWriter(new FileOutputStream(xmlPath), format);
			xmlWriter.write(document);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
