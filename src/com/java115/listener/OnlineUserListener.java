package com.java115.listener;

import java.util.Date;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class OnlineUserListener implements HttpSessionListener {

	public void sessionCreated(HttpSessionEvent se) {
		HttpSession session = se.getSession();
		System.out.println(new Date()+":"+session.getId()+"进入系统!");
		ServletContext application = se.getSession().getServletContext();
		Object count_result = application.getAttribute("usercount"); 
		if(count_result != null){
			application.setAttribute("usercount",Integer.valueOf(count_result.toString())+1);
		}else{
			application.setAttribute("usercount",1);
		}
	}

	public void sessionDestroyed(HttpSessionEvent se) {
		HttpSession session = se.getSession();
		System.out.println(new Date()+":"+session.getId()+"退出系统!");
		ServletContext application = se.getSession().getServletContext();
		Object count_result = application.getAttribute("usercount"); 
		if(count_result != null){
			application.setAttribute("usercount",Integer.valueOf(count_result.toString())-1);
		}else{
			application.setAttribute("usercount",0);
		}
	}

}
