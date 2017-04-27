package com.java115.listener;

import java.util.Date;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionListener;

public class OnlineNumListener implements HttpSessionAttributeListener{

	public void attributeAdded(HttpSessionBindingEvent se) {
		
		String name = se.getName();
		if("loginuser".equals(name)){
		System.out.println(new Date()+":"+se.getValue()+"进入系统!");
		ServletContext application = se.getSession().getServletContext();
		Object count_result = application.getAttribute("usercount"); 
		if(count_result != null){
			application.setAttribute("usercount",Integer.valueOf(count_result.toString())+1);
		}else{
			application.setAttribute("usercount",1);
		}
	}
}
	
	public void attributeRemoved(HttpSessionBindingEvent se) {
		
		String name = se.getName();
		if("loginuser".equals(name)){
		System.out.println(new Date()+":"+se.getValue()+"退出系统!");
		ServletContext application = se.getSession().getServletContext();
		Object count_result = application.getAttribute("usercount"); 
		if(count_result != null){
			application.setAttribute("usercount",Integer.valueOf(count_result.toString())-1);
		}else{
			application.setAttribute("usercount",0);
		}
	}
}

	public void attributeReplaced(HttpSessionBindingEvent se) {
		
	}

}
