package com.geo.gnss.ftpServer;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.geo.gnss.dao.FtpDao;

/**
 * Servlet implementation class ServletFtpSteup
 */
@WebServlet("/FtpSteup")
public class ServletFtpSteup extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		
		FtpDao ftpDao = new FtpDao();
		ftpDao.setPort(Integer.parseInt(request.getParameter("FtpPort")));
		ftpDao.setHomeDirectory(request.getParameter("HomeDirectory"));
		ftpDao.setUserName(request.getParameter("FtpName"));
		ftpDao.setPassword(request.getParameter("FtpPassword"));
		
		String steup = request.getParameter("Steup");
		
		if("StartFtp".equals(steup)){
			MyFtpUtils.createFtpServer(ftpDao);
			
			String configPath = getServletContext().getRealPath("/config/ftpConfig.xml");
			FtpConfig ftpConfig = new FtpConfig(configPath);
			ftpConfig.saveConfig(ftpDao);
			
		} else if("StopFtp".equals(steup)){
			MyFtpUtils.closeFtpServer();
		}
		
		response.getWriter().print(MyFtpUtils.isFtpStart());
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
