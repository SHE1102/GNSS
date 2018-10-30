package com.geo.gnss.convertRinex;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.geo.gnss.util.DownloadLog;

/**
 * Servlet implementation class VirtualDownloadServlet
 */
@WebServlet("/VirtualDownload")
public class ServletVirtualDownload extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		
		String appPath = getServletContext().getRealPath("");
		String user = (String)request.getSession().getAttribute("name");
		DownloadLog log = new DownloadLog(appPath);
		log.write(user, "Download virtual");
		
		String rarName = request.getParameter("fileName");
		
		StringBuilder sb = new StringBuilder();
		sb.append(appPath);
		sb.append(File.separator);
		sb.append("Virtual");
		sb.append(File.separator);
		sb.append(request.getSession().getId());
		String downloadPath = sb.toString();
		
		File file = new File(downloadPath);
		File[] downFileList = file.listFiles();
		
		List<String> downLoadFilePathList = new ArrayList<String>();
		for(File tempFile : downFileList) {
			String fileName = tempFile.getName();
			String ext = fileName.substring(fileName.lastIndexOf(".")+1);
			ext = ext.toUpperCase();
			
			if (ext.equals("SBLN") || ext.equals("SEPH") ||ext.equals("SOBS") 
					||ext.equals("SSTN") ||ext.equals("STRG")) {
				continue;
			}
			downLoadFilePathList.add(tempFile.getAbsolutePath());
		}
		
		downloadFile(response, rarName, downLoadFilePathList);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	protected void downloadFile(HttpServletResponse response, String rarName, List<String> downloadFileList) throws IOException {
		rarName += ".rar";
		response.setHeader("Content-Disposition", "attachment;filename=" + rarName);
			
		ZipOutputStream zos = new ZipOutputStream(response.getOutputStream());
		Iterator<String> it = downloadFileList.iterator();
		
		while (it.hasNext()) {
			String absoultPath = it.next();
			
			String tempFileName = absoultPath.substring(absoultPath.lastIndexOf("\\")+1);
			zos.putNextEntry(new ZipEntry(tempFileName));  
				
			File file = new File(absoultPath);
	        FileInputStream fis = new FileInputStream(file);
	            
            int read = 0;  
            byte[] bytes = new byte[1024];
            while((read = fis.read(bytes)) != -1){
                zos.write(bytes, 0, read);  
            }
            zos.flush();  
            fis.close();  
		}
		zos.flush();  
	    zos.close();  
	}

}
