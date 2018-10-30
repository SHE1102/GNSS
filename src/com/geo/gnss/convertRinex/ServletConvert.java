package com.geo.gnss.convertRinex;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.geo.gnss.dao.ConvertParDao;

/**
 * Servlet implementation class ConvertServlet
 */
@WebServlet("/ConvertRinex")
public class ServletConvert extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		String rawPath = (String)getServletContext().getAttribute("rawPath");
		String appPath = getServletContext().getRealPath("");
		
		ConvertParDao requestParam = new ConvertParDao();
		requestParam.setRinexVersion(Integer.parseInt(request.getParameter("RinexVersion")));
		requestParam.setTimeInterval(Integer.parseInt(request.getParameter("TimeInterval")));
		requestParam.setMixTure(Boolean.parseBoolean(request.getParameter("Mixture")));
		requestParam.setOutPutPar1(Boolean.parseBoolean(request.getParameter("CheckFlag0")));
		requestParam.setOutPutPar2(Boolean.parseBoolean(request.getParameter("CheckFlag1")));
		requestParam.setOutPutPar3(Boolean.parseBoolean(request.getParameter("CheckFlag2")));
		requestParam.setOutPutPar4(Boolean.parseBoolean(request.getParameter("CheckFlag3")));
		requestParam.setSatelliteSystem_GPS(Boolean.parseBoolean(request.getParameter("SystemFlag0")));
		requestParam.setSatelliteSystem_GLO(Boolean.parseBoolean(request.getParameter("SystemFlag1")));
		requestParam.setSatelliteSystem_BeiDou(Boolean.parseBoolean(request.getParameter("SystemFlag2")));
		requestParam.setSatelliteSystem_Galileo(Boolean.parseBoolean(request.getParameter("SystemFlag3")));
		requestParam.setSatelliteSystem_QZSS(Boolean.parseBoolean(request.getParameter("SystemFlag4")));
		requestParam.setSatelliteSystem_SBAS(Boolean.parseBoolean(request.getParameter("SystemFlag5")));
		requestParam.setFrequencyPoint(Integer.parseInt(request.getParameter("FrePoint")));
		requestParam.setDate(request.getParameter("Date"));
		requestParam.setStartTime(request.getParameter("StartTime"));
		requestParam.setEndTime(request.getParameter("EndTime"));
		requestParam.setStationName(request.getParameter("StationName"));
		requestParam.setZone(Integer.parseInt(request.getParameter("Zone")));
		
		ConvertManage rinexConvertManger = new ConvertManage(rawPath, appPath, request.getSession().getId(), requestParam);
		boolean res = rinexConvertManger.Convert();
		String saveFileName = rinexConvertManger.getSaveFileName();
		
		String backString = res ? saveFileName : "false";
		PrintWriter out = response.getWriter();
		out.print(backString);
		//out.print(saveFileName);
		
		/*System.out.println("Rinex:"+requestParam.getRinexVersion() + "\r\nMixTure:" + requestParam.isMixTure() +
				"\r\nInterval:" + requestParam.getTimeInterval() + "\r\nPar1:" + requestParam.isOutPutPar1() +
				"\r\nPar2:" + requestParam.isOutPutPar2() + "\r\nPar3:" + requestParam.isOutPutPar3() +
				"\r\nPar3:" + requestParam.isOutPutPar4() + "\r\nGPS:" + requestParam.isSatelliteSystem_GPS() +
				"\r\nGLO:" + requestParam.isSatelliteSystem_GLO() + "\r\nBeiDou:" + requestParam.isSatelliteSystem_BeiDou() +
				"\r\nGalileo:" + requestParam.isSatelliteSystem_Galileo() + "\r\nQZSS:" + requestParam.isSatelliteSystem_QZSS() +
				"\r\nSBAS:" + requestParam.isSatelliteSystem_SBAS() + "\r\nFre:" + requestParam.getFrequencyPoint() + 
				"\r\nstartTime:" + request.getParameter("StartTime") + "\r\nendTime:" + request.getParameter("EndTime") +
				"\r\nDate:" + request.getParameter("Date") + "\r\nstationName:" + request.getParameter("StationName"));*/
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
