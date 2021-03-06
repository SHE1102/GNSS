package com.geo.gnss.coordinateSystem;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.geo.gnss.dao.EmailDao;
import com.geo.gnss.jna.DllInterface.CoordConvertLib64;
import com.geo.gnss.util.SendEmail;
import com.sun.jna.ptr.DoubleByReference;

/**
 * Servlet implementation class ServletCoordinateConvert
 */
@WebServlet("/CoordinateConvert")
public class ServletCoordinateConvert extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		
		int convertType = Integer.parseInt(request.getParameter("ConvertType"));
		double sourceBx = Double.parseDouble(request.getParameter("Bx"));
		double sourceLy = Double.parseDouble(request.getParameter("Ly"));
		double sourceHh = Double.parseDouble(request.getParameter("Hh"));
		
		//System.out.println("type:" + convertType + " sourceBx:" + sourceBx + " sourceLy:" + sourceLy + " sourceHh:" + sourceHh);
		
		DoubleByReference destinationx = new DoubleByReference(0);
		DoubleByReference destinationy = new DoubleByReference(0);
		DoubleByReference destinationh = new DoubleByReference(0);
		
		String appPath = getServletContext().getRealPath("");
		String fileDir = appPath + File.separator + "coordinateSystem" + File.separator + request.getRequestedSessionId();
		String filePath = fileDir + File.separator + "coordinateSystem.sp";
		
		if(convertType == 0){
			CoordConvertLib64.instance.BLHToxyh(filePath, sourceBx, sourceLy, sourceHh, 
					destinationx, destinationy, destinationh);
		}else{
			CoordConvertLib64.instance.xyhToBLH(filePath, sourceBx, sourceLy, sourceHh, 
					destinationx, destinationy, destinationh);
		}
		
		StringBuilder contentSb = new StringBuilder();
		contentSb.append("source:<br/>");
		contentSb.append("B:");
		contentSb.append(sourceBx);
		contentSb.append("<br/>L:");
		contentSb.append(sourceLy);
		contentSb.append("<br/>H:");
		contentSb.append(sourceHh);
		contentSb.append("<br/>destination:<br/>");
		contentSb.append("B:");
		contentSb.append(destinationx.getValue());
		contentSb.append("<br/>L:");
		contentSb.append(destinationy.getValue());
		contentSb.append("<br/>H:");
		contentSb.append(destinationh.getValue());
		sendEmail(contentSb.toString(),request);
		
		StringBuilder sb = new StringBuilder();
		sb.append("{");
		sb.append("\"destinationBx\":");
		sb.append(destinationx.getValue());
		sb.append(",\"destinationLy\":");
		sb.append(destinationy.getValue());
		sb.append(",\"destinationHh\":");
		sb.append(destinationh.getValue());
		sb.append("}");
		
		//System.out.println(sb.toString());
		response.getWriter().print(sb.toString());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	protected void sendEmail(String content, HttpServletRequest request){
		String hostEmail = (String)getServletContext().getAttribute("hostEmail");
		String hostEmailPassword = (String)getServletContext().getAttribute("hostEmailPassword");
		String hostEmailProtocol = (String)getServletContext().getAttribute("hostEmailProtocol");
		String userEmail = (String)request.getSession().getAttribute("email");
		EmailDao emailDao = new EmailDao(hostEmail, hostEmailPassword, hostEmailProtocol, userEmail);
		
		SendEmail sendEmail = new SendEmail(content, emailDao);
		
		try {
			sendEmail.sendCoordinate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
