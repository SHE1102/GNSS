package com.geo.gnss.additional;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ServletDownloadQCReport
 */
@WebServlet("/downloadQCReport")
public class ServletDownloadQCReport extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		String filePath = request.getParameter("reportPath");
		
		String fileName = filePath.substring(filePath.lastIndexOf(File.separator)+1);
		response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
		
		OutputStream os = response.getOutputStream();
			
		File file = new File(filePath);
		InputStream inputStream = new FileInputStream(file);
			
		int read = 0;
		byte[] bytes = new byte[1024];
			
		while((read = inputStream.read(bytes)) != -1){
			os.write(bytes,0,read);
		}
			
		inputStream.close();
		
		os.flush();
		os.close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
