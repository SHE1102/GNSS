package com.geo.gnss.solution;

import java.io.File;

import com.geo.gnss.dao.AppConfig;
import com.geo.gnss.dao.EmailDao;

public class SolutionUploadFile {
	private AppConfig appConfig;
	private EmailDao emailDao;
	private SolutionParameter solutionParameter;
	
    public SolutionUploadFile(AppConfig appConfig,SolutionParameter solutionParameter, EmailDao emailDao){
    	this.appConfig = appConfig;
    	this.solutionParameter = solutionParameter;
    	this.emailDao = emailDao;
    }
    
    public boolean start(){
    	if(!getSolutionParatemer()){
    		return false;
    	}
    	
    	if(solutionParameter.getSolutionType() == 0){
			StaticSolutionThread thread = new StaticSolutionThread(appConfig, solutionParameter, emailDao);
			thread.start();
		} else {
			DynamicSolutionThread thread = new DynamicSolutionThread(appConfig, solutionParameter, emailDao);
			thread.start();
		}
    	
    	return true;
    }
    
    private boolean getSolutionParatemer(){
    	//get upload file
    	String folderPath = appConfig.getAppPath() + File.separator + "Solution" + File.separator + solutionParameter.getFolderName();
    	File folderFile = new File(folderPath);
    	
    	if(!folderFile.exists()){
    		return false;
    	}
    	
    	File[] files = folderFile.listFiles();
    	if(files == null || files.length != 1){
    		return false;
    	}
    	
    	//judge upload file ext
    	File uploadFile = files[0];
    	String uploadFilePath = uploadFile.getAbsolutePath();
    	String uploadFilename = uploadFile.getName();
    	String ext = uploadFilename.substring(uploadFilename.lastIndexOf(".")+1).toUpperCase();
    	if(!ext.endsWith("O") && !"DAT".equals(ext)){
    		return false;
    	}
    	
    	UploadManage uploadManage = new UploadManage(solutionParameter, appConfig);
    	
    	if("DAT".equals(ext)){
    		uploadManage.parseDatFile(uploadFilePath);
    	} else {
    		uploadManage.parseOFile(uploadFilePath);
    	}
    	
    	solutionParameter = uploadManage.getSolutionParameter();
    	solutionParameter.setUploadFilePath(uploadFilePath);
    	
    	return true;
    }
    
}
