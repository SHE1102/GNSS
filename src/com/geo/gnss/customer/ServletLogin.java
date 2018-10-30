package com.geo.gnss.customer;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.geo.gnss.dao.UserDao;

/**
 * Servlet implementation class ServletLogin
 */
@WebServlet("/Login")
public class ServletLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		HttpSession session = request.getSession();
		String lang = request.getLocale().getLanguage();
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		
		CustomerManage customerManage = new CustomerManage();
		boolean res = customerManage.login(name, password);
		
		if(res){
			UserDao user = customerManage.getLoginCustomer();
			session.setAttribute("name", user.getName());
			session.setAttribute("password", user.getPassword());
			session.setAttribute("authority", user.getAuthority());
			session.setAttribute("email", user.getEmail());
			session.setAttribute("status", "login");
			
			//request.getRequestDispatcher("/stationMap.jsp").forward(request, response);
			if("zh".equals(lang)){
				response.sendRedirect(getServletContext().getContextPath() + "/chs/stationMap.jsp");
			} else {
				response.sendRedirect(getServletContext().getContextPath() + "/en/stationMap.jsp");
			}
		} else {
			if("zh".equals(lang)){
				String path = getServletContext().getContextPath() + "/chs/login.jsp";
				response.getWriter().print("<script>alert('用户名或密码不正确,请重试!');window.location.href='"+path+"';</script>");
				//response.sendRedirect(getServletContext().getContextPath() + "/chs/login.jsp");
			} else {
				String path = getServletContext().getContextPath() + "/en/login.jsp";
				response.getWriter().print("<script>alert('The username and/or password inserted are not correct: please try again!');window.location.href='"+path+"';</script>");
				//response.sendRedirect(getServletContext().getContextPath() + "/en/login.jsp");
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
