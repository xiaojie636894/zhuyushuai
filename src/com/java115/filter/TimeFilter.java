package com.java115.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class TimeFilter implements Filter{

	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
			long starttime = System.currentTimeMillis();
			chain.doFilter(request, response);
			long endtime = System.currentTimeMillis();
			HttpServletRequest req = (HttpServletRequest) request;
			String url = req.getRequestURL()+"?"+req.getQueryString();
			System.out.println(url+"÷¥––¡À"+(endtime-starttime));
	}

	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
