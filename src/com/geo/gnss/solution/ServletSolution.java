package com.geo.gnss.solution;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.geo.gnss.dao.AppConfig;
import com.geo.gnss.dao.EmailDao;
import com.geo.gnss.util.MD5Utils;

/**
 * Servlet implementation class ServletSolution
 */
@WebServlet("/solution")
public class ServletSolution extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		int type = Integer.parseInt(request.getParameter("type"));
		int method = Integer.parseInt(request.getParameter("method"));
		
		String rawPath = (String) getServletContext().getAttribute("rawPath");
		String appPath = getServletContext().getRealPath("");
		
		AppConfig appConfig = new AppConfig();
		appConfig.setRawPath(rawPath);
		appConfig.setAppPath(appPath);
		
		String hostEmail = (String)getServletContext().getAttribute("hostEmail");
		String hostEmailPassword = (String)getServletContext().getAttribute("hostEmailPassword");
		String hostEmailProtocol = (String)getServletContext().getAttribute("hostEmailProtocol");
		String userEmail = (String)request.getSession().getAttribute("email");
		EmailDao emailDao = new EmailDao(hostEmail, hostEmailPassword, hostEmailProtocol, userEmail);
		
		SolutionParameter solutionParameter = new SolutionParameter();
		if(method == 0){
			solutionParameter.setSolutionType(type);
			solutionParameter.setDate(request.getParameter("date"));
			solutionParameter.setStartTime(request.getParameter("startTime"));
			solutionParameter.setEndTime(request.getParameter("endTime"));
			solutionParameter.setFolderName(MD5Utils.getFolderName());
			solutionParameter.setStationString(request.getParameter("stationList"));
			
			SolutionServerFile server = new SolutionServerFile(appConfig, solutionParameter, emailDao);
			server.start();
		} else {
			solutionParameter.setSolutionType(type);
			solutionParameter.setFolderName(request.getParameter("folderName"));
			
			SolutionUploadFile upload = new SolutionUploadFile(appConfig, solutionParameter, emailDao);
			upload.start();
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
