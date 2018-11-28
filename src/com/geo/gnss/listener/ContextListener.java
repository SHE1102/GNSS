package com.geo.gnss.listener;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.apache.ftpserver.FtpServer;

import com.geo.gnss.dao.ConfigDao;
import com.geo.gnss.ftpServer.MyFtpUtils;
import com.geo.gnss.manage.ConfigManage;
import com.geo.gnss.mybatis.MybatisInit;
import com.geo.gnss.thread.TimerClearFileThread;

@WebListener
public class ContextListener implements ServletContextListener {

	TimerClearFileThread clearThread = null;
	FtpServer ftpServer = null;
	
	@Override
	public void contextInitialized(ServletContextEvent event) {
        ServletContext context = event.getServletContext();
        
        ConfigDao configDao = readConfig(context);
        context.setAttribute("rawPath", configDao.getRawPath());
        context.setAttribute("centerLat", configDao.getCenterLat());
        context.setAttribute("centerLon", configDao.getCenterLon());
        context.setAttribute("mapZoom", configDao.getZoom());
        context.setAttribute("hostEmail", configDao.getHostEmail());
        context.setAttribute("hostEmailPassword", configDao.getHostEmailPassword());
        context.setAttribute("hostEmailProtocol", configDao.getHostEmailProtocol());
		
        startClearThread(context.getRealPath(""));
        
        MybatisInit mybatisInit = new MybatisInit();
        try {
			mybatisInit.initDatabase();
		} catch (IOException e) {
			e.printStackTrace();
		}
		//createFtpServer();
	
	}

//	private void createFtpServer(){
//		FtpDao ftpDao = new FtpDao();
//		ftpDao.setPort(12323);
//		ftpDao.setUserName("SHE");
//		ftpDao.setPassword("1234");
//		ftpDao.setHomeDirectory("D:/GNSS");
//		
//		MyFtpUtils.createFtpServer(ftpDao);
//	}

	@Override
	public void contextDestroyed(ServletContextEvent event) {
		ServletContext context = event.getServletContext();
		
		saveConfig(context);
		
		exitClearThread();
		
		destoryFtpServer();
	}

	private void destoryFtpServer() {
		MyFtpUtils.closeFtpServer();
	}

	protected ConfigDao readConfig(ServletContext context){
		String configPath = context.getRealPath("/config/config.xml");
		//System.out.println(configPath);
		ConfigManage configManage = new ConfigManage(configPath);
		
		return configManage.readConfig();
	}
	
	private void saveConfig(ServletContext context) {
		String configPath = context.getRealPath("/config/config.xml");
		//System.out.println(configPath);
		ConfigDao configDao = new ConfigDao();
		configDao.setRawPath((String)context.getAttribute("rawPath"));
		configDao.setHostEmail((String)context.getAttribute("hostEmail"));
		configDao.setHostEmailPassword((String)context.getAttribute("hostEmailPassword"));
		configDao.setHostEmailProtocol((String)context.getAttribute("hostEmailProtocol"));
		configDao.setCenterLat((double)context.getAttribute("centerLat"));
		configDao.setCenterLon((double)context.getAttribute("centerLon"));
		configDao.setZoom((int)context.getAttribute("mapZoom"));
		
		ConfigManage configManage = new ConfigManage(configPath);
		configManage.saveConfig(configDao);
	}
	
	private void startClearThread(String appPath) {
		clearThread = new TimerClearFileThread(appPath);
		clearThread.start();
	}
	
	private void exitClearThread() {
		if (clearThread != null) {
			clearThread.interrupt();
		}
	}

}
