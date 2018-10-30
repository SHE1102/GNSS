package com.geo.gnss.atest;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;

public class Test {

	public static void main(String[] args) {
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder bulider  = factory.newDocumentBuilder();
			Document doc = bulider.parse(new File("C:\\Users\\geo\\Desktop\\Stations.xml"));
			
			NodeList list = doc.getElementsByTagName("STATION");
			
			String text;
			for(int i=0; i<list.getLength(); i++){
				Node node = list.item(i);
				NodeList child = node.getChildNodes();
				for(int j=0; j<child.getLength(); j++){
					Node childNode = child.item(j);
					text = childNode.getNodeName();
					if("NAME".equals(text)){
						text = childNode.getTextContent();
					} else if("ID".equals(text)){
						text = childNode.getTextContent();
					}else if("REMARK".equals(text)){
						text = childNode.getTextContent();
					}else if("ANTENNA".equals(text)){
						NodeList subList = childNode.getChildNodes();
						
						for(int k=0; k<subList.getLength(); k++){
							if("ANTENNAPAR".equals(subList.item(k).getNodeName())){
								text = subList.item(k).getTextContent();
							}
						}
						
					}else if("ANTENNAPOINT_CTS".equals(text)){
						
					}else if("ANTENNAPOINT_NEU_L1".equals(text)){
						
					}else if("ANTENNAPOINT_NEU_L2".equals(text)){
						
					}
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		

	}

}
