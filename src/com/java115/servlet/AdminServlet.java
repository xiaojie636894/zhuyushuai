package com.java115.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.java115.entity.Userinfo;
import com.java115.service.UserinfoService;

public class AdminServlet extends HttpServlet{
	private static final long serialVersionUID = -3915917701415341108L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
  		String username = request.getParameter("username");
  		byte[] bytes = username.getBytes("iso-8859-1");
  		username = new String(bytes,"utf-8");
  		String password = request.getParameter("password");
  		UserinfoService service = new UserinfoService();
  		Userinfo user = new Userinfo();
  		user.setUsername(username);
  		user.setPassword(password);
  		boolean result = service.login(user);
  		
  		
  		
  		if(result){
  			Userinfo userinfo = service.getUserByUsername(username);
  			Cookie cookie = new Cookie("username",URLEncoder.encode(userinfo.getUsername()));
  			cookie.setMaxAge(7*24*60*60);
  			cookie.setPath("/");
  			response.addCookie(cookie);
  			
  			Cookie namecookie = new Cookie("realname",URLEncoder.encode(userinfo.getRealname()));
  			namecookie.setMaxAge(7*24*60*60);
  			namecookie.setPath("/");
  			response.addCookie(namecookie);
 			HttpSession session = request.getSession();
 			session.setAttribute("loginuser",userinfo);
  			response.sendRedirect(request.getContextPath()+"/admin/index.jsp");
  		}else{
  			PrintWriter out = response.getWriter();
  			out.print("<script>alert('��½ʧ�ܣ�');history.go(-1);</script>");
  		}
	}
}
