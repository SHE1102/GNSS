package com.geo.gnss.solution;

import java.io.File;
import java.util.List;

import com.geo.gnss.dao.AppConfig;
import com.geo.gnss.dao.EmailDao;
import com.geo.gnss.jna.DllInterface.AdjustmentDLL64;
import com.geo.gnss.jna.DllInterface.ToRinexDLL64;
import com.geo.gnss.util.SendEmail;

public class StaticSolutionThread extends Thread {

    private AppConfig appConfig; 
    private SolutionParameter solutionParameter;
    private EmailDao emailDao;
    
	public StaticSolutionThread(AppConfig appConfig, SolutionParameter solutionParameter, EmailDao emailDao){
		this.appConfig = appConfig;
		this.solutionParameter = solutionParameter;
		this.emailDao = emailDao;
	}

	@Override
	public void run() {
		super.run();
		
		SolutionManage solutionManage = new SolutionManage(appConfig, solutionParameter);
		if(solutionManage.parseStatic()){
			List<String> baseFileList = solutionManage.getBaseFileList();
			
			try {
				startSolution(baseFileList);
			} catch (Exception e) {
				//e.printStackTrace();
			}
		}
	}
	
	private void startSolution(List<String> baseFileList) throws Exception{
		String convertPath = appConfig.getAppPath() + File.separator + "Solution" + 
		           File.separator  + solutionParameter.getFolderName();
		
		//convert all file to rinex
		for(String convertFilePath : baseFileList){
			if(convertFilePath.endsWith("O")){
				continue;
			} 
			
			ToRinexDLL64.instance.ParseDataToRinexFormat_HTML(convertFilePath, convertPath, 17241639, null);
		}
				
		//solution:use convert folder
		solution(convertPath);
		
		//sendemail
		SendEmail sendEmail = new SendEmail(appConfig.getAppPath(), solutionParameter.getFolderName(), emailDao, 0);
		sendEmail.send();
	}
	
	private void solution(String convertPath){
		String antFilename = appConfig.getAppPath() + File.separator + "config" + File.separator + "ANTINFO_NGS.txt";
		System.out.println("Static solution......");
		boolean result = false;
		result = AdjustmentDLL64.instance.GetAdjustResult( convertPath,  antFilename);
		System.out.println("Static solution result:" + result);
	}
	
	/*
	private  void solution(String baseFilePath, String roverFilePath){
		File baseFile = new File(baseFilePath);
		File roverFile = new File(roverFilePath);
		
		File[] allFileList = baseFile.getParentFile().listFiles();
		
		String baseName = "",roverName = "";
		baseName = baseFile.getName();
		baseName = baseName.substring(0,baseName.indexOf("."));
		roverName = roverFile.getName();
		roverName = roverName.substring(0,roverName.indexOf("."));
		
		String baseOFilename="", roveOFilename="";
		String baseNFilename="", roveNFilename="";
		String baseGFilename="", roveGFilename=""; 
		String baseCFilename="", roveCFilename="";
		String baseLFilename="", roveLFilename="";
		String antFilename="", reportFilename = "";
		
		antFilename = appConfig.getAppPath() + File.separator + "config" + File.separator + "ANTINFO_NGS.txt";
		reportFilename = appConfig.getAppPath() + File.separator +"SolutionStatic" + File.separator + solutionParameter.getFolderName() + 
				File.separator  + baseName + "_"+roverName + ".html";
		
		for(File temFile : allFileList){
			String name = temFile.getName();
			String ext = name.substring(name.length()-1);
			ext = ext.toUpperCase();
			
			if(name.contains(baseName)){
				if(ext.equals("O")){
					baseOFilename = temFile.getAbsolutePath();
				}else if(ext.equals("N")){
					baseNFilename = temFile.getAbsolutePath();
				}else if(ext.equals("G")){
					baseGFilename = temFile.getAbsolutePath();
				}else if(ext.equals("C")){
					baseCFilename = temFile.getAbsolutePath();
				}else if(ext.equals("L")){
					baseLFilename = temFile.getAbsolutePath();
				}
			}else if(name.contains(roverName)){
                if(ext.equals("O")){
                	roveOFilename = temFile.getAbsolutePath();
				}else if(ext.equals("N")){
					roveNFilename = temFile.getAbsolutePath();
				}else if(ext.equals("G")){
					roveGFilename = temFile.getAbsolutePath();
				}else if(ext.equals("C")){
					roveCFilename = temFile.getAbsolutePath();
				}else if(ext.equals("L")){
					roveLFilename = temFile.getAbsolutePath();
				}
			}
		}
//		System.out.println(baseOFilename);
//		System.out.println(baseNFilename);
//		System.out.println(baseGFilename);
//		System.out.println(baseCFilename);
//		System.out.println(baseLFilename);
//		System.out.println(roveOFilename);
//		System.out.println(roveNFilename);
//		System.out.println(roveGFilename);
//		System.out.println(roveCFilename);
//		System.out.println(roveLFilename);
//		System.out.println(antFilename);
//	    System.out.println(reportFilename);
		System.out.println("Static solution......");
		boolean result = false;
		result = StaticBaseLineSolution64.instance.GetStaticResult( baseOFilename,  roveOFilename, 
				 baseNFilename,  roveNFilename,
				 baseGFilename,  roveGFilename, 
				 baseCFilename,  roveCFilename,
				 baseLFilename,  roveLFilename,
				 antFilename,  reportFilename);
		
		System.out.println("Static solution result:" + result);
	}
	*/
}
