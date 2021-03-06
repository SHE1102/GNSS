package com.geo.gnss.customer;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ServletCustomerManage
 */
@WebServlet("/CustomerManage")
public class ServletCustomerManage extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		//String type = request.getParameter("type");
		
		int authority = (int)request.getSession().getAttribute("authority");
		String type = authority == 1 ? "admin" : "superAdmin";
		
		CustomerManage customerManage = new CustomerManage();
		String json = customerManage.getAllCustomerJson(type);
		
		response.getWriter().print(json);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
