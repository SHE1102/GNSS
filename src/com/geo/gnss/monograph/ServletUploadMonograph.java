package com.geo.gnss.monograph;

import java.io.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 * Servlet implementation class ServletUploadMonograph
 */
@WebServlet("/UploadMonograph")
@MultipartConfig
public class ServletUploadMonograph extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		
		String name = request.getParameter("Name");
		System.out.println(name);
		Part filePart = request.getPart("ChooseFile");
		
		String header = filePart.getHeader("content-disposition");
		String fileName = header.substring(header.lastIndexOf("filename")+10, header.length()-1);
		System.out.println(fileName);
		
		String filePath = getServletContext().getRealPath("monograph") + File.separator + fileName;
		
		InputStream fileContent = null;
		OutputStream out  = null;
		
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
		
		//save map.xml
		String appPath = getServletContext().getRealPath("");
		MonographManage monographManage = new MonographManage(appPath);
		monographManage.addLink(name,fileName);
		
		response.getWriter().print(fileName);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
