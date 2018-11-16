package com.geo.gnss.solution;

import com.geo.gnss.dao.AppConfig;
import com.geo.gnss.dao.EmailDao;

public class SolutionServerFile {

	private AppConfig appConfig;
	private SolutionParameter solutionParameter;
	private EmailDao emailDao;
	
	public SolutionServerFile(AppConfig appConfig, SolutionParameter solutionParameter, EmailDao emailDao){
		this.appConfig = appConfig;
		this.solutionParameter = solutionParameter;
		this.emailDao = emailDao;
	}
	
	public boolean start(){
		
		if(solutionParameter.getSolutionType() == 0){
			StaticSolutionThread thread = new StaticSolutionThread(appConfig, solutionParameter, emailDao);
			thread.start();
		} else {
			DynamicSolutionThread thread = new DynamicSolutionThread(appConfig, solutionParameter, emailDao);
			thread.start();
		}
		
		return true;
	}
	
}
