<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<!DOCTYPE HTML >
<html>
  <head>
   
    
    <title>My JSP 'left.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body bgcolor="#33cc33">
    <a href="<%=request.getContextPath() %>/admin/ptypes/add.jsp" target = "right">产品类别添加</a><br/><br/>
    <a href="<%=request.getContextPath() %>/admin/ptypes/pty_manager.jsp" target = "right">产品类别管理</a><br/><br/>
  
    <a href="<%=request.getContextPath() %>/admin/pros/pro_manager.jsp" target = "right">产品管理</a><br/><br/>

    <a href="<%=request.getContextPath() %>/admin/orders?op=toorder" target = "right">订单添加</a><br/><br/>
    <a href="<%=request.getContextPath() %>/admin/orders/list.jsp" target = "right">订单管理</a><br/><br/>
    
  </body>
</html>
