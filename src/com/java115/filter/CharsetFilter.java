package com.java115.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class CharsetFilter implements Filter{

	public void destroy() {
		
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
			
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html;chasrset=utf-8");
			chain.doFilter(request, response);
	}


	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	

}
