package com.geo.gnss.station;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ServletSkyplot
 */
@WebServlet("/Skyplot")
public class ServletSkyplot extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;chartset=utf-8");
		
		String rawPath = (String)getServletContext().getAttribute("rawPath");
		String stationName = request.getParameter("stationName");
		
		SkyplotManage skyplot = new SkyplotManage(rawPath, stationName);
		String json = skyplot.getJson();
		response.getWriter().print(json);
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
