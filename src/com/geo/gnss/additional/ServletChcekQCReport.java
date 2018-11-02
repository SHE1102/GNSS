package com.geo.gnss.additional;

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
 * Servlet implementation class ServletChcekQCReport
 */
@WebServlet("/chcekQCReport")
public class ServletChcekQCReport extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		String reportFilePath = request.getParameter("reportPath");
		
		@SuppressWarnings("resource")
		InputStream is = new FileInputStream(reportFilePath);
		ServletOutputStream os = response.getOutputStream();
		
		int read = 0;
		byte[] bytes = new byte[1024];
		while((read=is.read(bytes))!=-1){
			os.write(bytes, 0, read);
		}
		
		os.flush();
		os.close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
