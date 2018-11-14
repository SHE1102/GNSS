package com.geo.gnss.solution;

import com.geo.gnss.dao.AppConfig;
import com.geo.gnss.dao.EmailDao;

public class SolutionServerFile {

	private int solutionType;
	private AppConfig appConfig;
	private SolutionParameter solutionParameter;
	private EmailDao emailDao;
	
	public SolutionServerFile(int solutionType, AppConfig appConfig, SolutionParameter solutionParameter, EmailDao emailDao){
		this.solutionType = solutionType;
		this.appConfig = appConfig;
		this.solutionParameter = solutionParameter;
		this.emailDao = emailDao;
	}
	
	public void start(){
		
		if(solutionType == 0){
			StaticSolutionThread thread = new StaticSolutionThread(appConfig, solutionParameter, emailDao);
			thread.start();
		} else {
			DynamicSolutionThread thread = new DynamicSolutionThread(appConfig, solutionParameter, emailDao);
			thread.start();
		}
	}
	
}
