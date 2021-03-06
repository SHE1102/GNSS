package com.geo.gnss.customer;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.geo.gnss.dao.UserAuthority;

/**
 * Servlet implementation class ServletLoginStatus
 */
@WebServlet("/LoginStatus")
public class ServletLoginStatus extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Pragma", "no-cache");
		response.setDateHeader("expires", -1);
		
		HttpSession session = request.getSession();
		
		Object objName = session.getAttribute("name");
		Object objAuthority = session.getAttribute("authority");
		
		String name ="",authority="";
		UserAuthority userAuthority = null;
		if(objName != null){
			name = String.valueOf(objName);
			authority = String.valueOf(objAuthority);
			
			CustomerManage customerManage = new CustomerManage();
			userAuthority = customerManage.getCustomerModualAuthority(name);
		}
		
		StringBuilder sb = new StringBuilder();
		sb.append("{\"name\":\"");
		sb.append(name);
		sb.append("\",\"authority\":\"");
		sb.append(authority);
		
		if(objName != null ){
			sb.append("\",\"downloadRinex\":\"");
			sb.append(userAuthority.isDownloadRinex() ? "1" : "0");
			sb.append("\",\"downloadVirtual\":\"");
			sb.append(userAuthority.isDownloadVirtual() ? "1" : "0");
			sb.append("\",\"solution\":\"");
			sb.append(userAuthority.isSolution() ? "1" : "0");
			sb.append("\",\"additionalFeature\":\"");
			sb.append(userAuthority.isAdditionalFeature() ? "1" : "0");
		}
		
		sb.append("\"}");
		
		response.getWriter().print(sb.toString());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
