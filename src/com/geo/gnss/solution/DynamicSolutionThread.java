package com.geo.gnss.solution;

import java.io.File;
import java.io.FileReader;
import java.io.LineNumberReader;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import com.geo.gnss.dao.EmailDao;
import com.geo.gnss.jna.DllInterface.PPKDLL64;
import com.geo.gnss.jna.DllInterface.ToRinexDLL64;
import com.geo.gnss.util.SendEmail;

public class DynamicSolutionThread extends Thread {
	private String appPath = "";
	private String sessionId = "";
    private String[] baseFileArray = null;
    private String[] roverFileArray = null;
    private EmailDao emailDao;
	
	public DynamicSolutionThread(String appPath, String sessionId, String[] baseArray, String[] roverArray,EmailDao emailDao){
		this.appPath = appPath;
		this.sessionId = sessionId;
		baseFileArray = baseArray;
		roverFileArray = roverArray;
		this.emailDao = emailDao;
	}
	
	@Override
	public void run() {
		super.run();
		
		try {
			Start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void Start() throws Exception {
		//copy dat files to temp dir
		String destPath = appPath + File.separator + "DynamicSolution" + File.separator + sessionId;
		File destDirFile = new File(destPath);
		if(!destDirFile.exists()){
			destDirFile.mkdirs();
		}else{
			for(File f : destDirFile.listFiles()){
				f.delete();
			}
		}
		
		//copy base file
        List<String> baseFileList = new ArrayList<String>();
		for(String srcFilePath : baseFileArray){
			File srcFile = new File(srcFilePath);
			String destFilePath = destPath + File.separator + srcFile.getName();
			File destFile = new File(destFilePath);
			Files.copy(srcFile.toPath(), destFile.toPath());
			
			baseFileList.add(destFilePath);
		}
		
		//copy rover file
		List<String> roverFileList = new ArrayList<String>();
		for(String srcFilePath : roverFileArray){
			File srcFile = new File(srcFilePath);
			String destFilePath = destPath + File.separator + srcFile.getName();
			File destFile = new File(destFilePath);
			Files.copy(srcFile.toPath(), destFile.toPath());
			
			roverFileList.add(destFilePath);
		}
		
		//convert base file
		for(String convertFilePath : baseFileList){
			File convertFile = new File(convertFilePath);
			ToRinexDLL64.instance.ParseDataToRinexFormat_HTML(convertFile.getAbsolutePath(), convertFile.getParent(), 16782887, null);
		}
		
		//convert rover file
		for(String convertFilePath : roverFileList){
			File convertFile = new File(convertFilePath);
			ToRinexDLL64.instance.ParseDataToRinexFormat_HTML(convertFile.getAbsolutePath(), convertFile.getParent(), 16782887, null);
		}
		
		//solution
		for(int i=0; i<baseFileList.size(); i++){
			for(int j=0; j<roverFileList.size(); j++){
				Solution(baseFileList.get(i), roverFileList.get(j));
			}
		}
		
		//send email
		SendEmail sendEmail = new SendEmail(appPath, sessionId, emailDao, "dynamic");
		sendEmail.send();
		
	}
	
	private  void Solution(String baseFilePath, String roverFilePath) throws Exception{
		File baseFile = new File(baseFilePath);
		File roverFile = new File(roverFilePath);
		
		File[] allFileList = baseFile.getParentFile().listFiles();
		
		String baseName = "",roverName = "", gridName = "";
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
		
		gridName = appPath + File.separator + "config" + File.separator + "gpt2_1wA.grd";
		gridName = "";
		antFilename = appPath  + File.separator + "config" + File.separator + "ANTINFO_NGS.txt";
		reportFilename = appPath + File.separator +"DynamicSolution" + File.separator + sessionId + 
				File.separator  + baseName + "_"+roverName + ".txt";
		
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
		
		//read base o file, get x y z
		if(baseOFilename.isEmpty()){
			return;
		}
		
		double coorX = 0.0, coorY = 0.0, coorZ = 0.0;
		LineNumberReader lr = new LineNumberReader(new FileReader(baseOFilename));
		String line = "";
		while((line=lr.readLine()) != null){
			if(line.contains(" APPROX POSITION XYZ")){
				line = line.substring(0, line.indexOf(" APPROX POSITION XYZ"));
				line = line.trim();
				
				String[] coorArray = line.split("  ");
				coorX = Double.parseDouble(coorArray[0]);
				coorY = Double.parseDouble(coorArray[1]);
				coorZ = Double.parseDouble(coorArray[2]);
				System.out.println("Dynamic:x="+coorX + " y="+coorY+" z="+coorZ);
				break;
			}
		}
		lr.close();
		
		System.out.println(baseOFilename);
		System.out.println(baseNFilename);
		System.out.println(baseGFilename);
		System.out.println(baseCFilename);
		System.out.println(baseLFilename);
		System.out.println(roveOFilename);
		System.out.println(roveNFilename);
		System.out.println(roveGFilename);
		System.out.println(roveCFilename);
		System.out.println(roveLFilename);
		System.out.println(antFilename);
		System.out.println(gridName);
		System.out.println(reportFilename);
		
		System.out.println("Dynamic solution......");
		boolean result = false;
		result = PPKDLL64.instance.PPKProcess(baseOFilename, roveOFilename,
				baseNFilename, roveNFilename,
				baseGFilename, roveGFilename,
				baseCFilename, roveCFilename,
				baseLFilename, roveLFilename,
				antFilename, gridName, reportFilename,
				2, 20*Math.PI/180, (0x1)|(0x1<<2), 2, 2.0,
				coorX, coorY, coorZ,
				5, 4, 0.0, 50);
		System.out.println("Dynamic solution result:" + result);
	}



	
}
