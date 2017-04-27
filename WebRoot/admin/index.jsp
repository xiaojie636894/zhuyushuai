<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<!DOCTYPE HTML >
<html>
  <head>
    
    
    <title>My JSP 'index.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  <frameset rows = "20%,*">
  	<frame src = "top.jsp"/>
  	<frameset cols = "20%,*">
  		<frame src = "left.jsp"/>
  		<frame name = "right" src = "right.jsp"/>
  	</frameset>
  
  </frameset>
</html>
