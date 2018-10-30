package com.geo.gnss.listener;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import com.geo.gnss.util.Resource;


@WebListener
public class SessionListener implements HttpSessionListener {

    public void sessionCreated(HttpSessionEvent event)  { 
    	HttpSession session = event.getSession();
    	
    	Resource source = new Resource("");
    	session.setAttribute("LangResource", source);
    }

    public void sessionDestroyed(HttpSessionEvent event)  { 
    }
	
}
