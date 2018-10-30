package com.geo.gnss.convertRinex;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.geo.gnss.dao.VirtualRinexDao;

/**
 * Servlet implementation class VirtualRinexServlet
 */
@WebServlet("/VirtualRinex")
public class ServletVirtualRinex extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		
		String rawPath = (String)getServletContext().getAttribute("rawPath");
		String appPath = getServletContext().getRealPath("");
		String sessionId = request.getRequestedSessionId();
		
		VirtualRinexDao virtualRinexDao = new VirtualRinexDao();
		virtualRinexDao.setLatitude(request.getParameter("Latitude"));
		virtualRinexDao.setLongitude(request.getParameter("Longitude"));
		virtualRinexDao.setAltitude(request.getParameter("Altitude"));
		virtualRinexDao.setCoordinateFormat(Integer.parseInt(request.getParameter("CoordinateFormat")));
		virtualRinexDao.setDate(request.getParameter("Date"));
		virtualRinexDao.setZone(Integer.parseInt(request.getParameter("Zone")));
		virtualRinexDao.setStartTime(request.getParameter("StartTime"));
		virtualRinexDao.setEndTime(request.getParameter("EndTime"));
		virtualRinexDao.setRinexVersion(Integer.parseInt(request.getParameter("RinexVersion")));
		virtualRinexDao.setTimeInterval(Integer.parseInt(request.getParameter("TimeInterval")));
		virtualRinexDao.setFrenquencyPoint(Integer.parseInt(request.getParameter("FrequencyPoint")));
		Map<String, String[]> maps = request.getParameterMap();
		boolean mixture = maps.containsKey("Mixture");
		virtualRinexDao.setMixture(mixture);
		
		if(maps.containsKey("SystemFlag")){
			String[] sys = maps.get("SystemFlag");
			for(String ss : sys){
				int mode = Integer.parseInt(ss);
				switch(mode){
				case 0:
					virtualRinexDao.setSatelliteSystem_GPS(true);
					break;
				case 1:
					virtualRinexDao.setSatelliteSystem_GLO(true);
					break;
				case 2:
					virtualRinexDao.setSatelliteSystem_BeiDou(true);
					break;
				case 3:
					virtualRinexDao.setSatelliteSystem_Galileo(true);
					break;
				case 4:
					virtualRinexDao.setSatelliteSystem_QZSS(true);
					break;
				case 5:
					virtualRinexDao.setSatelliteSystem_SBAS(true);
					break;
				}
				//System.out.println("System:" + ss);
			}
		}
		
		VirtualRinexManage virtualRinexManage = new VirtualRinexManage(rawPath,appPath,sessionId,virtualRinexDao);
		virtualRinexManage.convert();
		
		PrintWriter out = response.getWriter();
		out.print(virtualRinexManage.getDownloadFileName());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
}
