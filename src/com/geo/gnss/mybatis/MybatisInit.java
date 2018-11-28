package com.geo.gnss.mybatis;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.geo.gnss.dao.UserAuthority;
import com.geo.gnss.dao.UserDao;

public class MybatisInit {
	public final String resource = "com/geo/gnss/mybatis/mybatis-config.xml";
	
	public void initDatabase() throws IOException{
		SqlSession session = null;
		SqlSessionFactory sessionFactory = null;
		InputStream inputStream = null;
		
		inputStream = Resources.getResourceAsStream(resource);
		sessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		session = sessionFactory.openSession();
		session.update("geo.createDatabase");
		session.commit();
		session.close();
		inputStream.close();
		
		inputStream = Resources.getResourceAsStream(resource);
		sessionFactory = new SqlSessionFactoryBuilder().build(inputStream,"mysql");
		session = sessionFactory.openSession();
		session.update("geo.createTableCustomer");
		session.update("geo.createTableAuthority");
		
		UserDao userDao = new UserDao();
		userDao.setName("geo");
		userDao.setPassword("1234");
		userDao.setAuthority(9);
		userDao.setEnable(true);
		userDao.setLimitdate("2080-1-1");
		
		UserAuthority userAuthority = new UserAuthority();
		userAuthority.setName("geo");
		userAuthority.setDownloadRinex(true);
		userAuthority.setDownloadVirtual(true);
		userAuthority.setSolution(true);
		userAuthority.setAdditionalFeature(true);
		
		session.update("geo.insertRootUser", userDao);
		session.update("geo.insertRootAuthority", userAuthority);
		
		session.commit();
		session.close();
	}
	
	public void run2() throws IOException{
		InputStream inputStream = Resources.getResourceAsStream(resource);
		SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(inputStream,"mysql");
		SqlSession session = sessionFactory.openSession();
		session.update("geo.createTableCustomer");
		session.update("geo.createTableAuthority");
		
		UserDao userDao = new UserDao();
		userDao.setName("geo");
		userDao.setPassword("1234");
		userDao.setAuthority(9);
		userDao.setEnable(true);
		userDao.setLimitdate("2080-1-1");
		
		UserAuthority userAuthority = new UserAuthority();
		userAuthority.setName("geo");
		userAuthority.setDownloadRinex(true);
		userAuthority.setDownloadVirtual(true);
		userAuthority.setSolution(true);
		userAuthority.setAdditionalFeature(true);
		
		session.update("geo.insertRootUser", userDao);
		session.update("geo.insertRootAuthority", userAuthority);
		
		session.commit();
		session.close();
		
	}
}
