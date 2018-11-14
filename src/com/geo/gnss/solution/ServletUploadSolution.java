package com.geo.gnss.solution;

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

import com.geo.gnss.util.MD5Utils;

/**
 * Servlet implementation class ServletUploadSolution
 */
@WebServlet("/uploadSolution")
@MultipartConfig
public class ServletUploadSolution extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;chatset=utf-8");
		
		String folderName = MD5Utils.getFolderName();
		
		Part filePart = request.getPart("solutionFile");
		
		String header = filePart.getHeader("content-disposition");
		String fileName = header.substring(header.lastIndexOf("filename")+10, header.length()-1);
		
		String appPath = getServletContext().getRealPath("");
		String fileDir = appPath + File.separator + "SolutionUpload" + File.separator + folderName;
		File folderFile = new File(fileDir);
		folderFile.mkdirs();
		
		String filePath = fileDir + File.separator + fileName;
		
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
		
		//
		response.getWriter().print(folderName);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
