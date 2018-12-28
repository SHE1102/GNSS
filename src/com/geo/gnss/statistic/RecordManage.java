package com.geo.gnss.statistic;

import java.text.SimpleDateFormat;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.geo.gnss.dao.RecordCountDao;
import com.geo.gnss.mybatis.MybatisUtils;

public class RecordManage {

	public String getRecordJson(){
		SqlSession session = MybatisUtils.getSession();
		List<RecordCountDao> selectRecordList = session.selectList("selectRecord");
		session.commit();
		session.close();
		
		
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		StringBuilder sb = new StringBuilder();
		sb.append("{\"array\":[");
		if(selectRecordList != null && selectRecordList.size() > 0){
			for(int index=selectRecordList.size()-1; index>=0; index--){
				RecordCountDao recordCountDao = selectRecordList.get(index);
				
				sb.append("{\"date\":\"");
				sb.append(sdf.format(recordCountDao.getDate()));
				sb.append("\",\"register\":");
				sb.append(recordCountDao.getRegisterCount());
				sb.append(",\"rinex\":");
				sb.append(recordCountDao.getRinexCount());
				sb.append(",\"virtual\":");
				sb.append(recordCountDao.getVirtualCount());
				sb.append("}");
				
				if(index != 0){
					sb.append(",");
				}
			}
		}
		sb.append("]}");
		
		return sb.toString();
	}
	
	public String getRoverJson(){
		SqlSession session = MybatisUtils.getExternSession();
		List<RoverDao> selectRoverList = session.selectList("selectRoverList");
		List<CorsDao> selectCorsList = session.selectList("selectCorsList");
		session.commit();
		session.close();
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		StringBuilder sb = new StringBuilder();
		sb.append("{");
		
		sb.append("\"rover\":[");
		if(selectRoverList != null && selectRoverList.size() != 0){
			for(int index = selectRoverList.size()-1; index>=0; index--){
				RoverDao roverDao = selectRoverList.get(index);
				sb.append("{\"date\":\"");
				sb.append(sdf.format(roverDao.getDate()));
				sb.append("\",\"count\":");
				sb.append(roverDao.getCount());
				sb.append(",\"onlineTime\":");
				sb.append(roverDao.getOnlineTime());
				sb.append("}");
				
				if(index != 0){
					sb.append(",");
				}
			}
		}
		sb.append("],");
		
		sb.append("\"position\":[");
		if(selectCorsList != null && selectCorsList.size() != 0){
			for(int index = selectCorsList.size()-1; index>=0; index --){
				CorsDao corsDao = selectCorsList.get(index);
				sb.append("{\"latitude\":");
				sb.append(corsDao.getLatitude());
				sb.append(",\"longitude\":");
				sb.append(corsDao.getLongitude());
				sb.append(",\"altitude\":");
				sb.append(corsDao.getAltitude());
				sb.append("}");
				
				if(index != 0){
					sb.append(",");
				}
			}
		}
		sb.append("]");
		sb.append("}");
		
		return sb.toString();
	}
}
