package com.geo.gnss.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import com.geo.gnss.dao.EmailDao;
import com.geo.gnss.dao.UserDao;

public class SendEmail {
	//private String username = "yanxiao.she@aliyun.com";
	//private String host = "smtp.aliyun.com";
	//private String srcEmail = "yanxiao.she@aliyun.com";
	//private String password = "sheyanxiao1102*";
	//private String destEmail = "yanxiao.she@geoelectron.com";
	
	private String appPath;
	private String sessionId;
	private String filter;
	private EmailDao emailDao;
	private UserDao userDao;
	
	public SendEmail(UserDao userDao, EmailDao emailDao){
        this.userDao = userDao;
        this.emailDao = emailDao;
	}
	
     public SendEmail(String appPath, String sessionId, EmailDao emailDao, String filter){
    	 this.appPath = appPath; 
    	 this.sessionId = sessionId;
    	 this.filter = filter;
    	 this.emailDao = emailDao;
     }
     
     public void send() throws Exception{
    	 Properties prop = new Properties();
 		 prop.setProperty("mail.host", emailDao.getHostEmailProtocol());
 		 prop.setProperty("mail.transport.protocol", "smtp");
 		 prop.setProperty("mail.smtp.port", "25");
 		 prop.setProperty("mail.smtp.auth", "true");
 		 Session session = Session.getInstance(prop);
 		 //session.setDebug(true);
 		 Transport ts = session.getTransport();
 		 //ts.connect(host, srcEmail, password);
 		 ts.connect(emailDao.getHostEmailProtocol(), emailDao.getHostEmail(), emailDao.getHostEmailPassword());
 		 Message message = createEmail(session);
 		 ts.sendMessage(message, message.getAllRecipients());
 		 ts.close();
     }

	private Message createEmail(Session session) throws Exception {
		MimeMessage message = new MimeMessage(session);
		message.setFrom(new InternetAddress(emailDao.getHostEmail()));
		message.setRecipient(Message.RecipientType.TO, new InternetAddress(emailDao.getUserEmail()));
		
		//add email title
		message.setSubject("数据解算邮件");
		    
		//email body
		MimeMultipart bodyparts = new MimeMultipart();
		message.setContent(bodyparts);
		
		//get attachments
		List<File> fileList = getFilterFileList();
		
		//add attachments
		for(File f : fileList){
			MimeBodyPart attch = new MimeBodyPart();
			bodyparts.addBodyPart(attch);
			
			DataHandler dh = new DataHandler(new FileDataSource(f.getAbsoluteFile()));
			attch.setDataHandler(dh);
			attch.setFileName(f.getName());
		}
		
		//add content
		MimeBodyPart content = new MimeBodyPart();
		bodyparts.addBodyPart(content);
		String info = "数据解算成功,请查看附件!";//set email content
		content.setContent(info, "text/html;charset=UTF-8");
		
		message.saveChanges();
		
		return message;
	}
	
	protected List<File> getFilterFileList(){
		List<File> filterList = new ArrayList<File>();
		
		String reportPath="", filterExt="";
		if("static".equals(filter)){
			reportPath = appPath + File.separator +"StaticSolution" + File.separator + sessionId;
			filterExt = "HTML";
		} else if("dynamic".equals(filter)){
			reportPath = appPath + File.separator +"DynamicSolution" + File.separator + sessionId;
			filterExt = "TXT";
		}
		
		File reportDir = new File(reportPath);
		File[] fileArray = reportDir.listFiles();
		
		for(File f : fileArray){
			String name = f.getName();
			String ext = name.substring(name.lastIndexOf(".")+1).toUpperCase();
			
			if(filterExt.equals(ext)){
				filterList.add(f);
			}
		}
		
		return filterList;
	}
	
	public void sendRegister() throws Exception{
		Properties prop = new Properties();
		 prop.setProperty("mail.host", emailDao.getHostEmailProtocol());
		 prop.setProperty("mail.transport.protocol", "smtp");
		 prop.setProperty("mail.smtp.port", "25");
		 prop.setProperty("mail.smtp.auth", "true");
		 Session session = Session.getInstance(prop);
		 //session.setDebug(true);
		 Transport ts = session.getTransport();
		 //ts.connect(host, srcEmail, password);
		 ts.connect(emailDao.getHostEmailProtocol(), emailDao.getHostEmail(), emailDao.getHostEmailPassword());
		 Message message = createRegisterEmail(session);
		 ts.sendMessage(message, message.getAllRecipients());
		 ts.close();
	}
	
	private Message createRegisterEmail(Session session) throws Exception {
		MimeMessage message = new MimeMessage(session);
		message.setFrom(new InternetAddress(emailDao.getHostEmail()));
		message.setRecipient(Message.RecipientType.TO, new InternetAddress(emailDao.getHostEmail()));
		
		//add email title
		message.setSubject("注册邮件");
		    
		//email body
		MimeMultipart bodyparts = new MimeMultipart();
		message.setContent(bodyparts);
		
		//add content
		MimeBodyPart content = new MimeBodyPart();
		bodyparts.addBodyPart(content);
		
		StringBuilder sb = new StringBuilder();
		sb.append("New registration of an account!<br/>");
		sb.append("<b>UserName:");
		sb.append(userDao.getName());
		sb.append("</b><br/><b>FirstName:");
		sb.append(userDao.getFirstName());
		sb.append("</b><br/><b>LastName:");
		sb.append(userDao.getLastName());
		sb.append("</b><br/><b>Company:");
		sb.append(userDao.getCompany());
		sb.append("</b><br/><b>Email:");
		sb.append(userDao.getEmail());
		sb.append("</b><br/><b>telephone:");
		sb.append(userDao.getTelephone());
		sb.append("</b>");
		
		content.setContent(sb.toString(), "text/html;charset=UTF-8");
		message.saveChanges();
		
		return message;
	}
	public void sendBackRegister() throws Exception{
		Properties prop = new Properties();
		prop.setProperty("mail.host", emailDao.getHostEmailProtocol());
		prop.setProperty("mail.transport.protocol", "smtp");
		prop.setProperty("mail.smtp.port", "25");
		prop.setProperty("mail.smtp.auth", "true");
		Session session = Session.getInstance(prop);
		//session.setDebug(true);
		Transport ts = session.getTransport();
		//ts.connect(host, srcEmail, password);
		ts.connect(emailDao.getHostEmailProtocol(), emailDao.getHostEmail(), emailDao.getHostEmailPassword());
		Message message = createBackRegisterEmail(session);
		ts.sendMessage(message, message.getAllRecipients());
		ts.close();
	}
	
	private Message createBackRegisterEmail(Session session) throws Exception {
		MimeMessage message = new MimeMessage(session);
		message.setFrom(new InternetAddress(emailDao.getHostEmail()));
		message.setRecipient(Message.RecipientType.TO, new InternetAddress(userDao.getEmail()));
		
		//add email title
		message.setSubject("Back邮件");
		
		//email body
		MimeMultipart bodyparts = new MimeMultipart();
		message.setContent(bodyparts);
		
		//add content
		MimeBodyPart content = new MimeBodyPart();
		bodyparts.addBodyPart(content);
		
		StringBuilder sb = new StringBuilder();
		sb.append("<p>Dear user ");
		sb.append(userDao.getFirstName());
		sb.append(" ");
		sb.append(userDao.getLastName());
		sb.append(",Your request of registration has been confirmed. Now your account is enabled, you can access with the following credentials:</p><br/>");
		sb.append("<b>User name:");
		sb.append(userDao.getName());
		sb.append("</b><br/> <b>Password:");
		sb.append(userDao.getPassword());
		sb.append("</b>");
		
		content.setContent(sb.toString(), "text/html;charset=UTF-8");
		
		message.saveChanges();
		
		return message;
	}
}
