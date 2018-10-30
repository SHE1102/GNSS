package com.geo.gnss.convertRinex;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.geo.gnss.util.DownloadLog;

/**
 * Servlet implementation class ConvertDownloadServlet
 */
@WebServlet("/DownloadConvertRinex")
public class ServletDownloadConvert extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		String appPath = getServletContext().getRealPath("");
		String user = (String)request.getSession().getAttribute("name");
		DownloadLog log = new DownloadLog(appPath);
		log.write(user, "Download Rinex");
		
		String fileName = request.getParameter("file");
		
		StringBuilder sb = new StringBuilder();
		sb.append(appPath);
		sb.append(File.separator);
		sb.append("Convert");
		sb.append(File.separator);
		sb.append(request.getSession().getId());
		String sessionPath = sb.toString();
		
		downloadFile(response, fileName, sessionPath);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	protected void downloadFile(HttpServletResponse response, String fileName, String sessionPath) throws ServletException, IOException{
		ArrayList<String> fileList = getFileList(fileName, sessionPath);
		
		fileName += ".rar";
		response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
			
		ZipOutputStream zos = new ZipOutputStream(response.getOutputStream());
		
		Iterator<String> it = fileList.iterator();
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
	
	private ArrayList<String> getFileList (String fileName, String sessionPath){
		ArrayList<String> downFileList = new ArrayList<String>();
		
		File sessionDir = new File(sessionPath);
		String[] fileList = sessionDir.list();
		
		for(String pathName : fileList){
			if(pathName.contains(fileName)){
				String ext = pathName.substring(pathName.length()-4);
				ext = ext.toUpperCase();
				if (ext.equals(".DAT")) {
					continue;
				}
				
				String absoultPath = sessionPath + File.separator + pathName;
				downFileList.add(absoultPath);
			}
		}
		
		return downFileList;
	}

}
