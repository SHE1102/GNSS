package com.geo.gnss.ftpServer;

import java.util.ArrayList;
import java.util.List;

import org.apache.ftpserver.FtpServer;
import org.apache.ftpserver.FtpServerFactory;
import org.apache.ftpserver.ftplet.Authority;
import org.apache.ftpserver.ftplet.FtpException;
import org.apache.ftpserver.listener.ListenerFactory;
import org.apache.ftpserver.usermanager.impl.BaseUser;
import org.apache.ftpserver.usermanager.impl.WritePermission;

import com.geo.gnss.dao.FtpDao;

public class MyFtpUtils {
    private static FtpServer ftpServer = null;
    
//    static{
//    	FtpServerFactory serverFactory = new FtpServerFactory();
//    	ftpServer = serverFactory.createServer();
//    }
    
	public static void createFtpServer(FtpDao ftpDao) {
		closeFtpServer();
		
		FtpServerFactory ftpServerFactory = new FtpServerFactory();
		
		ListenerFactory listenerFactory = new ListenerFactory();
	    //设置监听端口
		//listenerFactory.setPort(12121);
		listenerFactory.setPort(ftpDao.getPort());
		//替换默认监听
		ftpServerFactory.addListener("default", listenerFactory.createListener());

		BaseUser user = new BaseUser();
		//user.setName("test");
		//user.setPassword("123");
		//user.setHomeDirectory("D:/GNSS");
		user.setName(ftpDao.getUserName());
		user.setPassword(ftpDao.getPassword());
		user.setHomeDirectory(ftpDao.getHomeDirectory());
		
		List<Authority> authorities = new ArrayList<Authority>();
		authorities.add(new WritePermission());
		user.setAuthorities(authorities);
		
		try {
			ftpServerFactory.getUserManager().save(user);
			ftpServer = ftpServerFactory.createServer();
			ftpServer.start();
		} catch (FtpException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void closeFtpServer(){
		if(ftpServer != null && !ftpServer.isStopped()){
			ftpServer.stop();
		}
	}
	public static boolean isFtpStart(){
		if(ftpServer == null){
			return false;
		}
		
		return !ftpServer.isStopped();
	}
	public static FtpServer getFtpServer(){
		return ftpServer;
	}

}
