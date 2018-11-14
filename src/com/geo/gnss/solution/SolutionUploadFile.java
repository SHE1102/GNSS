package com.geo.gnss.solution;

import java.io.File;

import com.geo.gnss.dao.AppConfig;
import com.geo.gnss.dao.EmailDao;

public class SolutionUploadFile {
	private int solutionType;
	private String folderName;
	private AppConfig appConfig;
	private EmailDao emailDao;
	
    public SolutionUploadFile(int solutionType, String folderName, AppConfig appConfig, EmailDao emailDao){
    	this.solutionType = solutionType;
    	this.folderName = folderName;
    	this.appConfig = appConfig;
    	this.emailDao = emailDao;
    }
    
    public void start(){
    	//String uploadPath = appConfig.getAppPath() + File.separator + "SolutionUpload";
    	
    	
    	String solutionTypeString = solutionType == 0 ? "SolutionStatic" : "SolutionDynamic";
    	String solutionPath = appConfig.getAppPath() + File.separator + solutionTypeString + File.separator + folderName;
    	File folderFile = new File(solutionPath);
    	folderFile.mkdirs();
    	
    	File uploadFile = new File("");
    	String absolutePath = uploadFile.getAbsolutePath();
    	String name = uploadFile.getName();
    	String ext = name.substring(name.lastIndexOf(".")+1).toUpperCase();
    	
    	UploadManage uploadManage = new UploadManage();
    	
    	if("DAT".equals(ext)){
    		uploadManage.parseDatFile(absolutePath);
    	} else {
    		uploadManage.parseOFile(absolutePath);
    	}
    	
    	SolutionParameter solutionParameter = uploadManage.getSolutionParameter();
    	solutionParameter.setFolderName(folderName);
    	
    	if(solutionType == 0){
			StaticSolutionThread thread = new StaticSolutionThread(appConfig, solutionParameter, emailDao);
			thread.start();
		} else {
			DynamicSolutionThread thread = new DynamicSolutionThread(appConfig, solutionParameter, emailDao);
			thread.start();
		}
    	
    }
    
    
}
