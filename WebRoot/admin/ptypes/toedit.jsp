<%@page import="com.java115.entity.Ptype"%>
<%@page import="com.java115.service.PtypeService"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<!DOCTYPE HTML >
<html>
  <head>
    
    <title>修改产品类别</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript">
	function checkAdd(){
		var typename = document.getElementById("typename").value;
		if(typenmae == ""){
			alert("产品的名称不能为空！");
			return false;
		}
		return true;
	}
	
	</script>
  </head>
  
  <body>
    
    <form name="frmadd" method="post" action="${pageContext.request.contextPath}/admin/ptypes?op=doedit">
    	<table border="2" cellspacing="0" bgcolor="#66FF66" bgcolor="#FFFFFF" align="center" width="300px" height="100px">
			<tr>
    		<td align="center" colspan="2">产品类别修改
			<input type="hidden" name ="id" value = "${requestScope.ptypes.ptypeid}"/>
			</td>
			</tr>
			<tr>
				<td align="center">类别名</td>
				<td><input id="typename" name ="typename" value = "${requestScope.ptypes.ptypename}" type="text"/></td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<input name="btnadd" type="submit" onclick = "return checkAdd();" value = "修改"/>
    		 		<input name="btnreset" type="reset" value = "重置"/>
				</td>
			</tr>
 
			
		</table>
    	</form>
  </body>
</html>
