package com.geo.gnss.manage;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ServletLangResource
 */
@WebServlet("/LangResource")
public class ServletLangResource extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/xml;charset=utf-8");
		
		String lang = request.getLocale().getLanguage();
		String langPath = getServletContext().getRealPath("/lang/Language_zh.xml");
		
		if(!"zh".equals(lang)){
			 langPath = getServletContext().getRealPath("/lang/Language_en.xml");
		}
		
		@SuppressWarnings("resource")
		InputStream is = new FileInputStream(langPath);
		ServletOutputStream os = response.getOutputStream();
		
		int read = 0;
		byte[] bytes = new byte[1024];
		
		while((read = is.read(bytes)) != -1){
			os.write(bytes, 0, read);
		}
		os.flush();
		os.close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
}
