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
 * Servlet implementation class StaticSolutionServlet
 */
@WebServlet("/SolutionStatic")
public class ServletStaticSolution extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		String rawPath = (String)getServletContext().getAttribute("rawPath");
		String appPath = (String)getServletContext().getRealPath("");
		String sessionId = request.getSession().getId();
		
		String hostEmail = (String)getServletContext().getAttribute("hostEmail");
		String hostEmailPassword = (String)getServletContext().getAttribute("hostEmailPassword");
		String hostEmailProtocol = (String)getServletContext().getAttribute("hostEmailProtocol");
		String userEmail = (String)request.getSession().getAttribute("email");
		EmailDao emailDao = new EmailDao(hostEmail, hostEmailPassword, hostEmailProtocol, userEmail);
		
		String fileArray = request.getParameter("file");

		String[] stringArray = fileArray.split(":");
		
		for(int i=0; i<stringArray.length; i++){
			String strTem = rawPath + File.separator + stringArray[i];
			stringArray[i] = strTem;
		}
		
		StaticSolutionThread solutionThread = new StaticSolutionThread(appPath,sessionId,stringArray,emailDao);
        solutionThread.start();		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
