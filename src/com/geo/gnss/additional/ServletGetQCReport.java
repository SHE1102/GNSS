package com.geo.gnss.additional;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ServletGetQCReport
 */
@WebServlet("/getQCReport")
public class ServletGetQCReport extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		String rawDate = (String)getServletContext().getAttribute("rawPath");
			
		String date = request.getParameter("date");
		String stationName = request.getParameter("stationName");
		
		SimpleDateFormat sDateFormat=new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		try {
			Date currentDate = sDateFormat.parse(date);
			calendar.setTime(currentDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		int year = calendar.get(Calendar.YEAR);
		int dayOfYear = calendar.get(Calendar.DAY_OF_YEAR);
		
		StringBuilder sb = new StringBuilder();
		sb.append(rawDate);
		sb.append("\\Daily\\");
		sb.append(year);
		sb.append("\\");
		sb.append(dayOfYear);
		sb.append("\\");
		sb.append(stationName);
		
		String skinDir = sb.toString();
		String qcReportFileName = stationName + dayOfYear + "Whole_QC.txt";
		String reportFilePath = "";
		
		File fileDir = new File(skinDir);
		File[] files = fileDir.listFiles();
		
		if(files == null){
			response.getWriter().print("false");
			return;
		}
		
		for(File fileTem : files){
			String name = fileTem.getName();
			if(name.equals(qcReportFileName)){
				reportFilePath = fileTem.getAbsolutePath();
				break;
			}
		}
		
		String out = reportFilePath.isEmpty() ? "false" : reportFilePath;
		response.getWriter().print(out);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
