package com.geo.gnss.atest;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.dom4j.Element;

public class Xml {

	public static void main(String[] args) {

		 SAXReader reader = new SAXReader();
		 try {
			Document document = reader.read(new File("C:\\Users\\geo\\Desktop\\Stations.xml"));
			
			 Element root = document.getRootElement();
			 Element e = root.element("Stations");
			 List<Element> list = e.elements("STATION");
			 
			 String text;
			 for(Element station : list){
				 Iterator<Element> it = station.elementIterator();
				 while(it.hasNext()){
					 Element node = it.next();
					 
					 System.out.println(node.getName());
					 if("NAME".equals(node.getName())){
						 text = node.getText();
						 System.out.println(text);
					 }else if("ID".equals(node.getName())){
						 text = node.getText();
						 System.out.println(text);
					 }else if("REMARK".equals(node.getName())){
						 text = node.getText();
						 System.out.println(text);
					 }else if("ANTENNA".equals(node.getName())){
						 text = node.elementText("ANTENNAPAR");
						 System.out.println(text);
					 }else if("ANTENNAPOINT_CTS".equals(node.getName())){
						 text = node.elementText("X");
						 System.out.println(text);
						 text = node.elementText("Y");
						 System.out.println(text);
						 text = node.elementText("Z");
						 System.out.println(text);
					 }else if("ANTENNAPOINT_NEU_L1".equals(node.getName())){
						 text = node.elementText("N");
						 System.out.println(text);
						 text = node.elementText("E");
						 System.out.println(text);
						 text = node.elementText("U");
						 System.out.println(text);
					 }else if("ANTENNAPOINT_NEU_L2".equals(node.getName())){
						 text = node.elementText("N");
						 System.out.println(text);
						 text = node.elementText("E");
						 System.out.println(text);
						 text = node.elementText("U");
						 System.out.println(text);
					 }
					 
					 
				 }
			 }
			 
		} catch (Exception e) {
			e.printStackTrace();
		}
		saveXml();
	}
	
	public static void saveXml(){
		Document document = DocumentHelper.createDocument();
		document.addDocType("xml","","");
		Element root = DocumentHelper.createElement("HEAD");
		document.setRootElement(root);
		
		Element elementRawPath = root.addElement("RawPath");
		elementRawPath.setText("dfdfs");
		
		OutputFormat format = new OutputFormat("    ", true);
		try {
			XMLWriter xmlWriter = new XMLWriter(new FileOutputStream("C:\\Users\\geo\\Desktop\\f.xml"), format);
			xmlWriter.write(document);
			xmlWriter.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
