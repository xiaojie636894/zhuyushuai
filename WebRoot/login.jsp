<%@page import="java.net.URLDecoder"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>


<!DOCTYPE HTML >
<html>
  <head>
    
    <title>登录</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript">
	if(self!=top){
		top.location = "${pageContext.request.contextPath}/admin/login.jsp";
	}
		function checklogin(){
			var username = document.getElementById("username").value;
			if(username == ""){
				alert("用户名不能为空！");
				return false;
			}
			var password = document.getElementById("password").value;
			if(password == ""){
				alert("密码不能为空！");
				return false;
				}
				return true;
		}
		
	
	</script>
  </head>
  
  <body>
  <% String username="";
   Cookie[] cookies = request.getCookies();  
  		if(cookies != null){
  			for(Cookie cookie:cookies){
  				
  				if("username".equals(cookie.getName())){
  					username = URLDecoder.decode(cookie.getValue());
  				}
  				out.print(cookie.getName()+":"+URLDecoder.decode(cookie.getValue())+"<br/>");
  			}
  		}
  
  %>
    	<form name="frmlogin" method="post" action="<%=request.getContextPath()%>/dologin">
    	<table align="center" cellspacing="0" border="2" bgcolor="#66FFFF" bordercolor="#6600CC" width="230px" >
			<tr>
				<td colspan="2" align="center">用户登录</td>
			</tr>
			<tr>
				<td align="center">用户名</td>
				<td><input id="username" name ="username" value="<%=username%>" type="text"/></td>
			</tr>
			<tr>
				<td align="center">密码</td>
				<td><input id="password" name="password" type = "password"/></td>
			</tr>
			<tr>
				<td colspan="2" align="center">
				<input name="btnlogin" type="submit" onclick = "return checklogin();" value = "登录"/>
				<input name="btnreset" type="reset" value = "重置"/>
				</td>
			</tr>
		</table>
		</form>
		
		<h2>${applicationScope.usercount}</h2>
  </body>
</html>
