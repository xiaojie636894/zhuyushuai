<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<!DOCTYPE HTML >
<html>
  <head>
   
    
    <title>My JSP 'top.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body bgcolor="#ccff00">
    <h1 align = "center">欢迎登陆淘宝管理系统</h1>
    <a href="${pageContext.request.contextPath}/loginout.jsp">安全退出</a>
  </body>
</html>
