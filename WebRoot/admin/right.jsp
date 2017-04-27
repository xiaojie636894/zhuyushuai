<%@page import="com.java115.entity.Userinfo"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML>
<html>
  <head>
    
    
    <title>My JSP 'right.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body bgcolor="#66ffff ">
  <%Userinfo user = (Userinfo)(session.getAttribute("loginuser"));%>
   <h2 align = "center">欢迎<%=user.getRealname() %>使用淘宝管理平台</h2>
  </body>
</html>
