package com.geo.gnss.coordinateSystem;

import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ServletCoordinateDetail
 */
@WebServlet("/CoordinateDetail")
public class ServletCoordinateDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		
		String appPath = getServletContext().getRealPath("");
		String fileDir = appPath + File.separator + "coordinateSystem" + File.separator + request.getRequestedSessionId();
		String filePath = fileDir + File.separator + "coordinateSystem.sp";
		
		CoordinateSystemManage systemManage = new CoordinateSystemManage(filePath);
		String json = systemManage.getJson();
		
		response.getWriter().print(json);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
