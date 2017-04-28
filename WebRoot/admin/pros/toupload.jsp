<%@page import="com.java115.entity.Product"%>
<%@page import="com.java115.service.ProService"%>
<%@page import="com.java115.entity.Ptype"%>
<%@page import="com.java115.service.PtypeService"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<!DOCTYPE HTML >
<html>
  <head>
    
    <title>上传产品照片</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	
	<script type="text/javascript">
	$(document).ready(function(){
		$("#addimage").click(function(){

			$.ajax({
				url : '${pageContext.request.contextPath}/admin/pros?op=doupload&proid=${pro.pid}',
				type : 'POST',
				cache : false,
				data :new FormData($("#frmadd")[0]),
				processData : false,
				contentType : false,
				dataType:"text",
				success:function(data){
					if(data == "yes"){
						datatable.draw(false);
						$(".bootbox-close-button").click();
					}else{
						bootbox.alert("添加失败！");
					}
				}
			});
		});
	});
	</script>
  </head>
  
  <body>
    <form  id="frmadd" enctype="multipart/form-data">
    	<table border="2" cellspacing="0" bgcolor="#66FF66" bordercolor="#003333" align="center">
			<tr>
				<td colspan="2" align="center">上传产品图片
				</td>
			</tr>
			<tr>
				<td colspan="2" align="center">产品图片
					<input id="myfile" name="myfile" type="file"/>
					<input id="addimage" name="addimage" type="button" value="添加" />
				</td>
			</tr>
		</table>
    	</form>
  </body>
</html>
