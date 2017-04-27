<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML >
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'upload.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript" charset="utf8"
	src="http://code.jquery.com/jquery-1.10.2.min.js"></script>
	<script type="text/javascript">
		$(document).ready(function(){
			$("#btnupload").click(function(){
				$.ajax({
					url : '${pageContext.request.contextPath}/admin/dofile?op=upload',
					type : 'POST',
					cache : false,
					data :new FormData($("#frmupload")[0]),
					processData : false,
					contentType : false,
					success:function(data){
						alert("添加成功!");
					},
					error:function(data){
						alert("添加失败!");
					}
				});
			});
		});
	</script>
  </head>
  
  <body>
  	<form id="frmupload" >
  	<img src="http://localhost:8080/javaweb/image/${requestScope.product.image}">
  	文件名:<input id="filename" name="filename" type="text"/><br/>
  	附件1: <input id="myfile1" name="myfile1" type="file"/><br/>
  	附件2: <input id="myfile2" name="myfile2" type="file"/><br/>
  	文件描述:<textarea id="filedesc" name="filedesc" rows="5" cols="40"></textarea><br/>
  		  <input id="btnupload" name="btnupload" type="button" value="添加"/>
  		  </form>
  </body>
</html>
