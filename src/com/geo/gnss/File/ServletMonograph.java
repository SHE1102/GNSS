package com.geo.gnss.File;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.geo.gnss.monograph.MonographManage;

/**
 * Servlet implementation class ServletMonograph
 */
@WebServlet("/Monograph")
public class ServletMonograph extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String name = request.getParameter("name");
		String appPath = getServletContext().getRealPath("");
		
		MonographManage monographManage = new MonographManage(appPath);
    	Map<String, String> map;
    	String pdfFile = "";
		try {
			map = monographManage.read();
			if(map.containsKey(name)){
				pdfFile = map.get(name);
			}
		} catch (Exception e) {
			//e.printStackTrace();
		}
		
		if(pdfFile.isEmpty()){
			response.getWriter().print("No File");
			return;
		}
    	
		String path=getServletContext().getRealPath("") + File.separator + "monograph" + File.separator + pdfFile;
		
		response.setContentType("application/pdf;charset=utf-8");
		//response.setHeader("content-Disposition", "attachment; filename=test.pdf"); 
		//ServletContext context = getServletContext();
		//InputStream is = context.getResourceAsStream(path);
		
		@SuppressWarnings("resource")
		InputStream is = new FileInputStream(path);
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
