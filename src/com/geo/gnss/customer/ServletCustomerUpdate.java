package com.geo.gnss.customer;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.geo.gnss.dao.EmailDao;
import com.geo.gnss.dao.UserAuthority;
import com.geo.gnss.dao.UserDao;

/**
 * Servlet implementation class ServletCustomerAdd
 */
@WebServlet("/CustomerUpdate")
public class ServletCustomerUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;chatset=utf-8");
		
		String name = request.getParameter("UserName");
		//String authority = "";
		String enable = request.getParameter("Enable");
		String limitdate = request.getParameter("LimitDate");
		
		boolean isSuperAdmin = false;
		Map<String, String[]> parameterMap = request.getParameterMap();
		if(parameterMap.containsKey("Authority")){
			isSuperAdmin = true;
			//authority = request.getParameter("Authority");
		}
		
		UserDao userDao = new UserDao();
		userDao.setName(name);
		if(isSuperAdmin){
			userDao.setAuthority(Integer.parseInt(request.getParameter("Authority")));
		}
		userDao.setEnable(Boolean.parseBoolean(enable));
		userDao.setLimitdate(limitdate);
		
		UserAuthority userAuthority = new UserAuthority();
		userAuthority.setName(name);
		userAuthority.setDownloadRinex(Boolean.parseBoolean(request.getParameter("downloadRinex")));
		userAuthority.setDownloadVirtual(Boolean.parseBoolean(request.getParameter("virtualRinex")));
		userAuthority.setSolution(Boolean.parseBoolean(request.getParameter("solution")));
		//userAuthority.setSolutionDynamic(Boolean.parseBoolean(request.getParameter("solutionDynamic")));
		userAuthority.setAdditionalFeature(Boolean.parseBoolean(request.getParameter("additionalFeature")));
		
		String hostEmail = (String)getServletContext().getAttribute("hostEmail");
		String hostEmailPassword = (String)getServletContext().getAttribute("hostEmailPassword");
		String hostEmailProtocol = (String)getServletContext().getAttribute("hostEmailProtocol");
		String userEmail = (String)request.getSession().getAttribute("email");
		EmailDao emailDao = new EmailDao(hostEmail, hostEmailPassword, hostEmailProtocol, userEmail);
		
		CustomerManage customerManage = new CustomerManage();
		boolean res = customerManage.updateCustomer(userDao, userAuthority, emailDao, isSuperAdmin);
		response.getWriter().print(res);
		//response.sendRedirect(getServletContext().getContextPath() + "/customerManage.jsp");
		
		//System.out.println("update:" + name + ":" + password + ":" + email + ":" +authority + ":" +enable);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
