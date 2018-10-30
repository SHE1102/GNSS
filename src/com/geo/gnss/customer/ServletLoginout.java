package com.geo.gnss.customer;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ServletLoginout
 */
@WebServlet("/Loginout")
public class ServletLoginout extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String lang = request.getLocale().getLanguage();
		HttpSession session = request.getSession();
		//String status = (String)session.getAttribute("status");
		Object nameObj = session.getAttribute("name");
		
		if(nameObj != null){
			session.invalidate();
			//request.getRequestDispatcher("/stationMap.jsp").forward(request, response);
			if("zh".equals(lang)){
				response.sendRedirect(getServletContext().getContextPath() + "/chs/stationMap.jsp");
			} else {
				response.sendRedirect(getServletContext().getContextPath() + "/en/stationMap.jsp");
			}
		} else {
			if("zh".equals(lang)){
				response.sendRedirect(getServletContext().getContextPath() + "/chs/login.jsp");
			} else {
				response.sendRedirect(getServletContext().getContextPath() + "/en/login.jsp");
			}
			
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
