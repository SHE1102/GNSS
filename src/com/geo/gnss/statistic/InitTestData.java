package com.geo.gnss.statistic;

import java.util.Date;
import java.util.Random;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import com.geo.gnss.dao.RecordCountDao;
import com.geo.gnss.mybatis.MybatisUtils;

public class InitTestData {

	@Test
    public void run(){
		SqlSession session = MybatisUtils.getSession();
		
		RecordCountDao dao = new RecordCountDao();
		Random ra =new Random();
		for(int i=0; i<15; i++){
			dao.setDate(new Date(new Date().getTime() - (15-i)*24*60*60*1000));
			dao.setRegisterCount(ra.nextInt(50));
			dao.setRinexCount(ra.nextInt(50));
			dao.setVirtualCount(ra.nextInt(50));
			session.insert("insertRecordTest", dao);
			session.commit();
		}
		session.close();
    }
}
