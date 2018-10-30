package com.geo.gnss.customer;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.geo.gnss.dao.UserDao;

/**
 * Servlet implementation class ServletCustomerUpdateAccount
 */
@WebServlet("/CustomerUpdateAccount")
public class ServletCustomerUpdateAccount extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;chatset=utf-8");
		
		UserDao userDao = new UserDao();
		userDao.setName(request.getParameter("UserName"));
		userDao.setFirstName(request.getParameter("FirstName"));
		userDao.setLastName(request.getParameter("LastName"));
		userDao.setPassword(request.getParameter("Password"));
		userDao.setCompany(request.getParameter("Company"));
		userDao.setEmail(request.getParameter("Email"));
		userDao.setTelephone(request.getParameter("Telephone"));
	
		CustomerManage customerManage = new CustomerManage();
		boolean res = customerManage.updateCustomer(userDao);
		response.getWriter().print(res);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
