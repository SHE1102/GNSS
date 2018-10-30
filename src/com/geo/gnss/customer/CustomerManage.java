package com.geo.gnss.customer;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.geo.gnss.dao.EmailDao;
import com.geo.gnss.dao.UserDao;
import com.geo.gnss.mybatis.MybatisUtils;
import com.geo.gnss.util.SendEmail;

public class CustomerManage {
	UserDao loginCustomer = null;
	List<UserDao> customerList = new ArrayList<>();
	
	public CustomerManage() {
		
	}
	
	public boolean register(UserDao user,EmailDao emailDao){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());  
        calendar.add(Calendar.DATE,30);
        Date date=calendar.getTime();  
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String limitDate = sdf.format(date); 

        user.setLimitdate(limitDate);
		
    	SqlSession sqlSession = MybatisUtils.getSession();
    	int rows = sqlSession.insert("addCustomer",user);
    	sqlSession.commit();
    	sqlSession.close();
    	
    	boolean bRes = rows > 0 ? true : false;
    	
    	if(bRes){
    		//sendEmail
    		SendEmail sendEmail = new SendEmail(user, emailDao);
    		try {
				sendEmail.sendRegister();
			} catch (Exception e) {
				//e.printStackTrace();
			}
    	}
    	return bRes;
    }
	
	public boolean login(String name, String password){
		UserDao user = new UserDao();
		user.setName(name);
		user.setPassword(password);
		
		SqlSession sqlSession = MybatisUtils.getSession();
    	List<UserDao> userList = sqlSession.selectList("selectUser",user);
    	sqlSession.commit();
    	sqlSession.close();
    	
    	boolean res = false;
    	if(userList.size() > 0){
    		loginCustomer = userList.get(0);
    		
    		res = loginCustomer.isEnable();
    		
    		if(res){
	    		SimpleDateFormat sDateFormat=new SimpleDateFormat("yyyy-MM-dd"); 
	    		String limitDateString = loginCustomer.getLimitdate();
	    		
				try {
					Date limitDate = sDateFormat.parse(limitDateString);
					Date currentDate = new Date();
		    		
		    		res = (limitDate.getTime()+24*60*60*1000) >= currentDate.getTime();
				} catch (ParseException e) {
				}
    		}
    		
    	}
    	
    	return res;
	}
	
	public UserDao getLoginCustomer(){
		return loginCustomer;
	}
	
	private void getAllCustomer(){
        SqlSession sqlSession = MybatisUtils.getSession();
    	customerList = sqlSession.selectList("selectAll");
    	sqlSession.close();
	}
	
	public String getAllCustomerJson(String type){
		getAllCustomer();
		
        if(customerList.size() <= 0){
        	return null;
        }
        
        StringBuilder sb = new StringBuilder();
        sb.append("{\"userArray\":[");
        int start=0;
        for(int i=0; i<customerList.size(); i++){
        	UserDao obj = customerList.get(i);
        	
        	if("admin".equals(type)){
        		if(obj.getAuthority() != 0){
            		continue;
            	}
        	} 
        	
        	if(start != 0){
        		sb.append(",");
        	}
        	start = 1;
        	
        	sb.append("{\"name\":\"");
        	sb.append(obj.getName());
        	sb.append("\",\"password\":\"");
        	sb.append(obj.getPassword());
        	sb.append("\",\"email\":\"");
        	sb.append(obj.getEmail());
        	sb.append("\",\"authority\":\"");
        	sb.append(obj.getAuthority());
        	sb.append("\",\"enable\":\"");
        	sb.append(obj.isEnable());
        	sb.append("\",\"limitdate\":\"");
        	sb.append(obj.getLimitdate());
        	sb.append("\"}");
        	
        }
        
        sb.append("]}");
        
        return sb.toString();
	}
	
	public boolean updateCustomer(UserDao userDao){
		SqlSession sqlSession = MybatisUtils.getSession();
		
		int rows = 0;
		rows = sqlSession.update("updateUserByNameByAccount", userDao);
    	sqlSession.commit();
    	sqlSession.close();
    	
    	boolean res = false;
    	res = rows>0 ? true : false;
    	return res;
	}
	
	public boolean updateCustomer(UserDao userDao, EmailDao emailDao, boolean isSuperAdmin){
		SqlSession sqlSession = MybatisUtils.getSession();
		List<UserDao> userList = sqlSession.selectList("selectByName",userDao.getName());
    	
    	int rows = 0;
    	if(isSuperAdmin){
    		rows = sqlSession.update("updateUserByNameBySuperAdminster", userDao);
    	}else{
    		rows = sqlSession.update("updateUserByNameByAdminster", userDao);
    	}
    	sqlSession.commit();
    	sqlSession.close();
    	
    	boolean res = false;
    	res = rows>0 ? true : false;
    	
    	if(res && userList.size() > 0){
    		boolean oldEnable = userList.get(0).isEnable();
    		if(!oldEnable && userDao.isEnable()){
    			//sendEmail
    			SendEmail sendEmail = new SendEmail(userList.get(0), emailDao);
        		try {
    				sendEmail.sendBackRegister();
    			} catch (Exception e) {
    				//e.printStackTrace();
    			}
    		}
    	}
    	return res;
	}
	
	public boolean deleteCustomer(String name){
		SqlSession sqlSession = MybatisUtils.getSession();
    	
    	int rows = 0;
		rows = sqlSession.delete("deleteUserByName", name);
    	sqlSession.commit();
    	sqlSession.close();
    	
    	boolean res = false;
    	res = rows>0 ? true : false;
    	return res;
	}
	
	public boolean checkRegisterName(String name){
		SqlSession sqlSession = MybatisUtils.getSession();
		
		List<UserDao> userList = sqlSession.selectList("selectByName", name);
		sqlSession.commit();
		sqlSession.close();
		
		boolean res = userList.size() > 0 ? false : true;
		return res;
	}
	
	public String getCustomerDetail(String name){
        SqlSession sqlSession = MybatisUtils.getSession();
		
		List<UserDao> userList = sqlSession.selectList("selectByName", name);
		sqlSession.commit();
		sqlSession.close();
		
		StringBuilder sb = new StringBuilder();
		if(userList.size() > 0){
			UserDao obj = userList.get(0);
			
			sb.append("{\"name\":\"");
        	sb.append(obj.getName());
        	sb.append("\",\"password\":\"");
        	sb.append(obj.getPassword());
        	sb.append("\",\"email\":\"");
        	sb.append(obj.getEmail());
        	sb.append("\",\"authority\":\"");
        	sb.append(obj.getAuthority()==0 ? "User" : (obj.getAuthority()==1 ? "Administer" : "SuperAdminister"));
        	sb.append("\",\"enable\":\"");
        	sb.append(obj.isEnable() ? "enable" : "disable");
        	sb.append("\",\"limitdate\":\"");
        	sb.append(obj.getLimitdate());
        	sb.append("\",\"firstname\":\"");
        	sb.append(obj.getFirstName());
        	sb.append("\",\"lastname\":\"");
        	sb.append(obj.getLastName());
        	sb.append("\",\"company\":\"");
        	sb.append(obj.getCompany());
        	sb.append("\",\"telephone\":\"");
        	sb.append(obj.getTelephone());
        	sb.append("\"}");
		}
		
		return sb.toString();
	}

}
