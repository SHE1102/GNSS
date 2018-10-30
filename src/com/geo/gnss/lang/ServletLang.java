package com.geo.gnss.lang;

import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ServletLang
 */
@WebServlet("/lang")
public class ServletLang extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		String appPath = getServletContext().getContextPath();
		String lang = request.getParameter("Lang");
		String resquestRefer = request.getHeader("Referer");
		resquestRefer.substring(resquestRefer.lastIndexOf('\\')+1);
		
		String redirectPath = "";
		if("zh".equalsIgnoreCase(lang)){
			redirectPath = appPath + File.separator + "chs" + File.separator + resquestRefer;
		}else{
			redirectPath = appPath + File.separator + "en" + File.separator + resquestRefer;
		}
		
		response.sendRedirect(redirectPath);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
