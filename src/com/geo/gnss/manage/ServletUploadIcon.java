package com.geo.gnss.manage;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 * Servlet implementation class ServletUploadIcon
 */
@WebServlet("/UploadIcon")
@MultipartConfig
public class ServletUploadIcon extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;chatset=utf-8");
		
		Part filePart = request.getPart("LogoIcon");
		String filePath = getServletContext().getRealPath("img") + File.separator + "logo-customer.png";
		
		InputStream fileContent = null;
		OutputStream out  = null;
		
		//System.out.println(filePath);
		out = new FileOutputStream(new File(filePath));
		fileContent = filePart.getInputStream();
		
		int read = 0;
		byte[] bt = new byte[1024];
		while((read = fileContent.read(bt)) != -1){
			out.write(bt, 0, read);
		}
		
		if(out != null){
			out.close();
		}
		
		if(fileContent != null){
			fileContent.close();
		}
		
		//response.getWriter().print("true");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
