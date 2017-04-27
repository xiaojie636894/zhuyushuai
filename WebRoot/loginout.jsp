<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	session.removeAttribute("loginuser");
	response.sendRedirect(request.getContextPath()+"/admin/login.jsp");
%>
