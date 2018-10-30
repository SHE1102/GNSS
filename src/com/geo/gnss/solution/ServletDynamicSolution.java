package com.geo.gnss.solution;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.geo.gnss.dao.EmailDao;

/**
 * Dynamic Solution 
 */
@WebServlet("/SolutionDynamic")
public class ServletDynamicSolution extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String rawPath = (String)getServletContext().getAttribute("rawPath");
		String appPath = (String)getServletContext().getRealPath("/");
		String sessionId = request.getSession().getId();
		
		String hostEmail = (String)getServletContext().getAttribute("hostEmail");
		String hostEmailPassword = (String)getServletContext().getAttribute("hostEmailPassword");
		String hostEmailProtocol = (String)getServletContext().getAttribute("hostEmailProtocol");
		String userEmail = (String)request.getSession().getAttribute("email");
		EmailDao emailDao = new EmailDao(hostEmail, hostEmailPassword, hostEmailProtocol,userEmail);
		
		String baseFile = request.getParameter("baseFile");
		String roverFile = request.getParameter("roverFile");
		
		String[] baseArray = baseFile.split(":");
		String[] roverArray = roverFile.split(":");
		
		for(int i=0; i<baseArray.length; i++){
			String strTem = rawPath + File.separator + baseArray[i];
			baseArray[i] = strTem;
		}
		
		for(int i=0; i<roverArray.length; i++){
			String strTem = rawPath + File.separator + roverArray[i];
			roverArray[i] = strTem;
		}
		
		DynamicSolutionThread solutionThread = new DynamicSolutionThread(appPath, sessionId, baseArray, roverArray, emailDao);
        solutionThread.start();		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
