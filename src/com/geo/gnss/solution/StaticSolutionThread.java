package com.geo.gnss.solution;

import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import com.geo.gnss.dao.EmailDao;
import com.geo.gnss.jna.DllInterface.StaticBaseLineSolution64;
import com.geo.gnss.jna.DllInterface.ToRinexDLL64;
import com.geo.gnss.util.SendEmail;

public class StaticSolutionThread extends Thread {
	private String appPath = "";
	private String sessionId = "";
    private String[] datFileArray = null;
    private EmailDao emailDao;
    
	public StaticSolutionThread(String appPath, String sessionId, String[] array, EmailDao emailDao){
		this.appPath = appPath;
		this.sessionId = sessionId;
		this.emailDao = emailDao;
		datFileArray = array;
	}

	@Override
	public void run() {
		super.run();
		
		try {
			Start();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//SolutionDemo();
	}

	private void Start() throws Exception{
		//copy dat files to temp dir
		String destPath = appPath + File.separator + "StaticSolution" + File.separator + sessionId;
		File destDirFile = new File(destPath);
		if(!destDirFile.exists()){
			destDirFile.mkdirs();
		}else{
			for(File f : destDirFile.listFiles()){
				f.delete();
			}
		}
		List<String> fileList = new ArrayList<String>();
		
		for(String srcFilePath : datFileArray){
			File srcFile = new File(srcFilePath);
			String destFilePath = destPath + File.separator + srcFile.getName();
			File destFile = new File(destFilePath);
			Files.copy(srcFile.toPath(), destFile.toPath());
			
			fileList.add(destFilePath);
		}
		
		//convert all file to rinex
		for(String convertFilePath : fileList){
			File convertFile = new File(convertFilePath);
			ToRinexDLL64.instance.ParseDataToRinexFormat_HTML(convertFile.getAbsolutePath(), convertFile.getParent(), 17241639, null);
		}
		
		int nCount = fileList.size();
		for(int i=0; i<nCount-1; i++){
			for(int j=i+1; j<nCount; j++){
				Solution(fileList.get(i), fileList.get(j));
			}
		}
		
		//send email
		SendEmail sendEmail = new SendEmail(appPath, sessionId, emailDao, "static");
		sendEmail.send();
	}
	
	private  void Solution(String baseFilePath, String roverFilePath){
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
		
		antFilename = appPath + File.separator + "config" + File.separator + "ANTINFO_NGS.txt";
		reportFilename = appPath + File.separator +"StaticSolution" + File.separator + sessionId + 
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
	
	/*private void SolutionDemo(){
		
		String baseOfilename="", roveOfilename="";
		String baseNFilename="", roveNFilename="";
		String baseGFilename="", roveGFilename=""; 
		String baseCFilename="", roveCFilename="";
		String baseLFilename="", roveLFilename="";
		String antFilename="", reportFilename = "";
		
		baseOfilename = "C:\\Users\\geo\\Desktop\\data2\\23KM240l.18O";
		baseNFilename = "C:\\Users\\geo\\Desktop\\data2\\23KM240l.18N";
		baseGFilename = "C:\\Users\\geo\\Desktop\\data2\\23KM240l.18G";
		baseCFilename = "C:\\Users\\geo\\Desktop\\data2\\23KM240l.18C";
		baseLFilename = "C:\\Users\\geo\\Desktop\\data2\\23KM240l.18L";
		
		roveOfilename = "C:\\Users\\geo\\Desktop\\data2\\55KM240l.18O";
		roveNFilename = "C:\\Users\\geo\\Desktop\\data2\\55KM240l.18N";
		roveGFilename = "C:\\Users\\geo\\Desktop\\data2\\55KM240l.18N";
		roveCFilename = "C:\\Users\\geo\\Desktop\\data2\\55KM240l.18N";
		roveLFilename = "C:\\Users\\geo\\Desktop\\data2\\55KM240l.18N";
		
		antFilename="C:\\Users\\geo\\Desktop\\data2\\ANTINFO_NGS.txt";
		reportFilename="C:\\Users\\geo\\Desktop\\data2\\23KM240l_55KM240l.html";
		
		StaticBaseLineSolution64.instance.GetStaticResult( baseOfilename,  roveOfilename, 
				 baseNFilename,  roveNFilename,
				 baseGFilename,  roveGFilename, 
				 baseCFilename,  roveCFilename,
				 baseLFilename,  roveLFilename,
				 antFilename,  reportFilename);
	}*/
}
