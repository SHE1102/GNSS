package com.geo.gnss.File;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.geo.gnss.util.DownloadLog;

/**
 * Servlet implementation class DownloadServlet
 */
@WebServlet("/Download")
public class ServletDownload extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	doGet(request, response);
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		String appPath = getServletContext().getRealPath("");
		String user = (String)request.getSession().getAttribute("name");
		
		String rawPath = (String)getServletContext().getAttribute("rawPath");
		String fileString = request.getParameter("fileString");
		String[] filePathArray = fileString.split(":");
		
		if(filePathArray.length <= 0){
			return;
		}
		
		for(int i=0; i<filePathArray.length; i++){
			String strTemp = filePathArray[i];
			filePathArray[i] = rawPath + File.separator + strTemp;
		}
		
		DownloadLog log = new DownloadLog(appPath);
		log.write(user, "Download Rinex 30s");
		
		downloadFile( filePathArray,  response);
	}

	private void downloadFile(String[] fileList,  HttpServletResponse response) throws IOException{
		if(fileList.length == 1){
			downloadSingleFile(fileList[0], response);
		}
		else {
			downloadZipFile(fileList, response);
		}
	}
	
	protected void downloadSingleFile(String filePath,  HttpServletResponse response) throws IOException{
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
	
	protected void downloadZipFile(String[] fileArray,  HttpServletResponse response) throws IOException{
		String zipName = "download.rar";
		response.setHeader("Content-Disposition", "attachment;filename=" + zipName);
			
		ZipOutputStream zos = new ZipOutputStream(response.getOutputStream());
		
		for(String absoultPath : fileArray){
				
			String fileName = absoultPath.substring(absoultPath.lastIndexOf(File.separator)+1);
			zos.putNextEntry(new ZipEntry(fileName));  
				
	        FileInputStream fis = new FileInputStream(new File(absoultPath));
	            
	        try  
	        {  
	            int read = 0;  
	            byte[] bytes = new byte[1024];
	            while((read = fis.read(bytes)) != -1){  
	                zos.write(bytes, 0, read);  
	            }  
	        }finally  
	        {  
	            zos.flush();  
	            fis.close();  
	        }  
	    }
		zos.flush();  
	    zos.close();  
	}
}
