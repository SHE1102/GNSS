package com.geo.gnss.customer;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ServletCustomerDelete
 */
@WebServlet("/CustomerDelete")
public class ServletCustomerDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		
		String name = request.getParameter("UserName");
		//String password = request.getParameter("Password");
		//String email = request.getParameter("Email");
		//String authority = request.getParameter("Authority");
		//String enable = request.getParameter("Enable");
		
		CustomerManage customerManage = new CustomerManage();
		boolean res = customerManage.deleteCustomer(name);
		response.getWriter().print(res);
		//System.out.println("delete:" + name + ":" + password + ":" + email + ":" +authority + ":" +enable);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
