package com.geo.gnss.customer;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.geo.gnss.dao.EmailDao;
import com.geo.gnss.dao.UserDao;

/**
 * Servlet implementation class ServletRegister
 */
@WebServlet("/Register")
public class ServletCustomerAdd extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		UserDao userDao = new UserDao();
		userDao.setName(request.getParameter("UserName"));
		userDao.setPassword(request.getParameter("Password"));
		userDao.setAuthority(0);
		userDao.setEmail(request.getParameter("Email"));
		userDao.setEnable(false);
		userDao.setFirstName(request.getParameter("FirstName"));
		userDao.setLastName(request.getParameter("LastName"));
		userDao.setCompany(request.getParameter("Company"));
		userDao.setTelephone(request.getParameter("Telephone"));
		
		String hostEmail = (String)getServletContext().getAttribute("hostEmail");
		String hostEmailPassword = (String)getServletContext().getAttribute("hostEmailPassword");
		String hostEmailProtocol = (String)getServletContext().getAttribute("hostEmailProtocol");
		String userEmail = (String)request.getSession().getAttribute("email");
		EmailDao emailDao = new EmailDao(hostEmail, hostEmailPassword, hostEmailProtocol, userEmail);
		
		CustomerManage customerManage = new CustomerManage();
		boolean res = customerManage.register(userDao, emailDao);
		//String text = res ? "success" : "failed";
		response.getWriter().print(res);
		//response.sendRedirect(getServletContext().getContextPath() + "/stationMap.jsp");
		//System.out.println(userName + ":" + password + ":" + email);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
