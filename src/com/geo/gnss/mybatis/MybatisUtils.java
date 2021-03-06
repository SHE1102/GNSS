package com.geo.gnss.mybatis;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MybatisUtils {
    private static SqlSessionFactory sqlSessionFactory = null;
    private static SqlSessionFactory sqlSessionFactoryExtern = null;
    
    static{
    	String source = "com/geo/gnss/mybatis/mybatis-config.xml";
    	String sourceExtern = "com/geo/gnss/mybatis/mybatisExternConfig.xml";
    	
		try {
			InputStream inputStream;
			inputStream = Resources.getResourceAsStream(source);
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream,"mysql");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			InputStream inputStream;
			inputStream = Resources.getResourceAsStream(sourceExtern);
			sqlSessionFactoryExtern = new SqlSessionFactoryBuilder().build(inputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    public static SqlSession getSession(){
    	return sqlSessionFactory.openSession();
    }
    
    public static SqlSession getExternSession(){
    	return sqlSessionFactoryExtern.openSession();
    }
    
}
