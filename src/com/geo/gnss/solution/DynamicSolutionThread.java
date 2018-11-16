package com.geo.gnss.solution;

import java.io.File;
import java.io.FileReader;
import java.io.LineNumberReader;
import java.util.List;

import com.geo.gnss.dao.AppConfig;
import com.geo.gnss.dao.EmailDao;
import com.geo.gnss.jna.DllInterface.PPKDLL64;
import com.geo.gnss.jna.DllInterface.ToRinexDLL64;
import com.geo.gnss.util.SendEmail;

public class DynamicSolutionThread extends Thread {
    private EmailDao emailDao;
    private AppConfig appConfig;
    private SolutionParameter solutionParameter;
	
	public DynamicSolutionThread(AppConfig appConfig, SolutionParameter solutionParameter, EmailDao emailDao){
		this.appConfig = appConfig;
		this.solutionParameter = solutionParameter;
		this.emailDao = emailDao;
	}
	
	@Override
	public void run(){
		super.run();
		
        SolutionManage solutionManage = new SolutionManage(appConfig, solutionParameter);
		
		if(solutionManage.parseDynamic()){
			List<String> baseFileList = solutionManage.getBaseFileList();
			List<String> roverFileList = solutionManage.getRoverFileList();
			
			try {
				startSolution(baseFileList.get(0), roverFileList.get(0));
			} catch (Exception e) {
				//e.printStackTrace();
			}
		}
	}

	private void startSolution(String baseFilePath, String roverFilePath) throws Exception{
		//convert base file
		//如果是上传的O文件就不用转换
		if(baseFilePath.endsWith("dat")){
			File baseFile = new File(baseFilePath);
			ToRinexDLL64.instance.ParseDataToRinexFormat_HTML(baseFile.getAbsolutePath(), baseFile.getParent(), 16782887, null);
		}

		//convert rover file
		File roverFile = new File(roverFilePath);
		ToRinexDLL64.instance.ParseDataToRinexFormat_HTML(roverFile.getAbsolutePath(), roverFile.getParent(), 16782887, null);
		
		//solution
		solution(baseFilePath, roverFilePath);
		
		//send email
		SendEmail sendEmail = new SendEmail(appConfig.getAppPath(), solutionParameter.getFolderName(), emailDao, 1);
		sendEmail.send();
	}
	
	private  void solution(String baseFilePath, String roverFilePath) throws Exception{
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
		
		gridName = appConfig.getAppPath() + File.separator + "config" + File.separator + "gpt2_1wA.grd";
		gridName = "";
		antFilename = appConfig.getAppPath()  + File.separator + "config" + File.separator + "ANTINFO_NGS.txt";
		reportFilename = baseFile.getParent() + File.separator  + baseName + "_"+roverName + ".txt";
		
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
				//System.out.println("Dynamic:x="+coorX + " y="+coorY+" z="+coorZ);
				break;
			}
		}
		lr.close();
		
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
