package com.geo.gnss.station;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ServletStation
 */
@WebServlet("/Station")
public class ServletStation extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json;charset=utf-8");
		
		ServletContext context = request.getServletContext();
		String appPath = getServletContext().getRealPath("");
		String rawPath = (String)context.getAttribute("rawPath");
		
		String userType = request.getParameter("userType");
		
		StationManage stationManage = new StationManage(rawPath, appPath);
		String json = "";
		if("Tourist".equals(userType)){
			json = stationManage.getJson(false);
		} else if("User".equals(userType)){
			json = stationManage.getJson(true);
		}
		//System.out.println(json);
		response.getWriter().print(json);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
