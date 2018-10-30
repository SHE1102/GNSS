package com.geo.gnss.manage;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.geo.gnss.dao.ConfigDao;

/**
 * Servlet implementation class ServletConfigSet
 */
@WebServlet("/Config")
public class ServletConfig extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		String method = request.getParameter("method");
		
		if("read".equals(method)){
			readConfig(request, response);
		}else if("save".equals(method)){
			saveConfig(request, response);
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	private void readConfig(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String configPath = getServletContext().getRealPath("config/config.xml");
		ConfigManage configManage = new ConfigManage(configPath);
		ConfigDao configDao = configManage.readConfig();
		String path = configDao.getRawPath();
		path = path.replace("\\", "\\\\");
		
		String json = "{\"rawPath\":\"" + path +
				"\",\"hostEmail\":\"" + configDao.getHostEmail() +
				"\",\"hostEmailPassword\":\"" + configDao.getHostEmailPassword() +
				"\",\"hostEmailProtocol\":\"" + configDao.getHostEmailProtocol() +
				"\",\"centerLat\":" + configDao.getCenterLat() +
				",\"centerLon\":" + configDao.getCenterLon() +  
				",\"mapZoom\":" + configDao.getZoom() + "}";
		
		response.getWriter().print(json);
	}
	
	private void saveConfig(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String configPath = getServletContext().getRealPath("config/config.xml");
		
		String rawPath = request.getParameter("rawPath");
		String centerLat = request.getParameter("centerLat");
		String centerLon = request.getParameter("centerLon");
		String mapZoom = request.getParameter("zoom");
		String hostEmail = request.getParameter("hostEmail");
		String hostEmailPassword = request.getParameter("hostEmailPassword");
		String hostEmailProtocol = request.getParameter("hostEmailProtocol");
		rawPath.replace("\\", "\\\\");
		
		ConfigDao configDao = new ConfigDao();
		configDao.setRawPath(rawPath);
		configDao.setHostEmail(hostEmail);
		configDao.setHostEmailPassword(hostEmailPassword);
		configDao.setHostEmailProtocol(hostEmailProtocol);
		configDao.setCenterLat(Double.parseDouble(centerLat));
		configDao.setCenterLon(Double.parseDouble(centerLon));
		configDao.setZoom(Integer.parseInt(mapZoom));
		
		ConfigManage configManage = new ConfigManage(configPath);
		boolean res = configManage.saveConfig(configDao);
		
		ServletContext context = getServletContext();
		context.setAttribute("rawPath", configDao.getRawPath());
		context.setAttribute("centerLat", configDao.getCenterLat());
		context.setAttribute("centerLon", configDao.getCenterLon());
		context.setAttribute("mapZoom", configDao.getZoom());
		context.setAttribute("hostEmail", configDao.getHostEmail());
		context.setAttribute("hostEmailPassword", configDao.getHostEmailPassword());
		context.setAttribute("hostEmailProtocol", configDao.getHostEmailProtocol());
		
		response.getWriter().print(res);
		//response.sendRedirect(getServletContext().getContextPath() + "/stationMap.jsp");
		
	}
}
