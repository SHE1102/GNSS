package com.geo.gnss.listener;

import java.io.IOException;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebFilter(filterName="loginFilter", urlPatterns={
		"/chs/accountInfo.jsp","/chs/stationMapUser.jsp","/chs/stationListUser.jsp","/chs/stationSkyplot.jsp",
		"/chs/downloadRinex.jsp","/chs/virtualRinex.jsp","/chs/solutionStatic.jsp","/chs/solutionDynamic.jsp",
		"/chs/coordinateConvert.jsp","/chs/configSet.jsp","/chs/customerManage.jsp","/chs/monograph.jsp",
		"/chs/customerAdmin.jsp",
		
		"/en/accountInfo.jsp","/en/stationMapUser.jsp","/en/stationListUser.jsp","/en/stationSkyplot.jsp",
		"/en/downloadRinex.jsp","/en/virtualRinex.jsp","/en/solutionStatic.jsp","/en/solutionDynamic.jsp",
		"/en/coordinateConvert.jsp","/en/configSet.jsp","/en/customerManage.jsp","/en/monograph.jsp",
		"/en/customerAdmin.jsp",
		},
        dispatcherTypes={DispatcherType.REQUEST, DispatcherType.FORWARD, DispatcherType.INCLUDE})
public class LoginFilter implements Filter{

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		
	}
	
	@Override
	public void destroy() {
		
	}

	@Override
	public void doFilter(ServletRequest servlerRequest, ServletResponse servletResponse, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpServletRequest = (HttpServletRequest) servlerRequest;
		HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
		
		String lang = httpServletRequest.getLocale().getLanguage();
		
        Object userNameObj = null;
        userNameObj = httpServletRequest.getSession().getAttribute("name");
         
        if(userNameObj == null){
        	if (lang.equals("zh")) {
        		httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/chs/login.jsp");
        	} else{
        		httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/en/login.jsp");
        	}
        	return;
        }
        
		chain.doFilter(servlerRequest, servletResponse);
	}

}
