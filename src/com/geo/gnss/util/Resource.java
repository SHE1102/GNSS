package com.geo.gnss.util;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Properties;

public class Resource {

	private String src = "C:\\Users\\geo\\Desktop\\test.txt";
	private Properties p = null;
	
	public Resource(String path){
		//this.src = path;
		load();
	}
	
	protected void load(){
		try {
			Reader reader = new InputStreamReader(new FileInputStream(src),"UTF-8");
			p = new Properties();
			p.load(reader);
		} catch (Exception e) {
		}
	}
	
	public String getValue(String key){
		if(p == null){
			//return null;
		}
		return p.getProperty(key);
	}
}
