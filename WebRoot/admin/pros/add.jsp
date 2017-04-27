<%@page import="com.java115.entity.Ptype"%>
<%@page import="com.java115.service.PtypeService"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>

<!DOCTYPE HTML >
<html>
<head>

<title>产品添加</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">


<script type="text/javascript">

	$(document).ready(function() {
		$("#btnadd").click(function() {
			if ($("#pname").val() == "") {
				$("#pnameError").html("产品名称不能为空！");
				return false;
			}
			if ($("#ptypes").val() == "0") {
				$("#ptypesError").html("---请选择产品类别！---");
				return false;
			}
			$.ajax({
							type:"GET",
							url:"${pageContext.request.contextPath}/admin/pros?op=doadd&pid=${pro.pid}",
							dataType:"text",
							data:$("#frmadd").serialize(),
								success:function(result){
									if(result == "yes"){
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
	
	<form id="frmadd"  enctype="multipart/form-data">
		<table border="2" cellspacing="0" bgcolor="#66FF66"
			bordercolor="#003333" align="center">

			<tr>
				<td colspan="2" align="center">产品添加</td>
			</tr>
			<tr>
				<td align="center">产品名称</td>
				<td><input id="pname" name="pname" type="text" />
					<div id="pnameError" style="display:inline;color: red;"></div>
				</td>
			</tr>
			<tr>
				<td align="center">产品类别</td>
				<td><select name="ptypes" id="ptypes">
						<option value="0" selected="selected">请选择</option>
						<c:forEach items="${requestScope.types}" var="type">
							<option value="${type.ptypeid}">${type.ptypename}</option>
						</c:forEach>
				</select>
					<div id="ptypesError" style="display:inline;color: red;"></div>
				</td>
			</tr>
			<tr>
				<td align="center">进货价格</td>
				<td><input id="inprice" name="inprice" type="text" /></td>
			</tr>
			<tr>
				<td align="center">出售价格</td>
				<td><input id="salprice" name="salprice" type="text" /></td>
			</tr>
			<tr>
				<td align="center">进货时间</td>
				<td><input id="date" name="date" type="text"
					onclick="WdatePicker()" readonly="readonly" /></td>
			</tr>
			<tr>
				<td colspan="2" align="center">
				<input id="btnadd" name="btnadd" type="button" value="添加" /> 
					<input name="btnreset" type="reset" value="重置" />
				</td>
			</tr>

		</table>
	</form>
</body>
</html>
