package com.geo.gnss.convertRinex;

import java.util.Date;

import org.apache.ibatis.session.SqlSession;

import com.geo.gnss.mybatis.MybatisUtils;

public class DownloadService {

	public void saveDownloadlog(DownloadInforDao inforDao){
		SqlSession session = MybatisUtils.getSession();
		session.insert("insertDownloadLog", inforDao);
		//session.insert("updateDownloadRinexRecord", new Date());
		session.commit();
		session.close();
	}
	
	public void updateRinexCount(){
		SqlSession session = MybatisUtils.getSession();
		session.insert("updateDownloadRinexRecord", new Date());
		session.commit();
		session.close();
	}
	
	public void updateVirtualCount(){
		SqlSession session = MybatisUtils.getSession();
		session.insert("updateDownloadVirtualRecord", new Date());
		session.commit();
		session.close();
	}
}
