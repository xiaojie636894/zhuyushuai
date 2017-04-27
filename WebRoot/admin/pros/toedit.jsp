<%@page import="com.java115.entity.Product"%>
<%@page import="com.java115.service.ProService"%>
<%@page import="com.java115.entity.Ptype"%>
<%@page import="com.java115.service.PtypeService"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<!DOCTYPE HTML >
<html>
  <head>
    
    <title>产品修改</title>
    
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
		$("#btnedit").click(function(){
			var pname = $("#pname").val();
		if(pname == ""){
			bootbox.alert("产品的名称不能为空！");
			return ;
		}
		$.ajax({
							type:"GET",
							url:"${pageContext.request.contextPath}/admin/pros?op=doedit",
							dataType:"text",
							data:$("#frmedit").serialize(),
								success:function(result){
									if(result == "yes"){
										datatable.draw(false);
										$(".bootbox-close-button").click();
									}else{
										bootbox.alert("修改失败！");
									}
								}
						});
		});
	});
	function checkedit(){
		
	}
	
	</script>
  </head>
  
  <body>
    <form id="frmedit">
    	<table border="2" cellspacing="0" bgcolor="#66FF66" bordercolor="#003333" align="center">
			<tr>
				<td colspan="2" align="center">产品修改
				<input id="proid" name ="proid" value="${pro.pid}" type="hidden"/>
				</td>
			</tr>
			<tr>
				<td align="center">产品名称</td>
				<td><input id="pname" name ="pname" value="${pro.pname}" type="text"/></td>
			</tr>
			<tr>
				<td align="center">产品类别</td>
				<td><select name="ptypes">
    	<option value="0">请选择</option>
    	
    	<c:forEach items="${requestScope.types}" var="type">
    	<option value="${type.ptypeid}" 
    		<c:if test="${type.ptypeid==requestScope.pro.ptype.ptypeid}">
    		selected="selected"
    		</c:if>
    		 >${type.ptypename}</option>
    	</c:forEach>
    	
    		  </select></td>
			</tr>
			<tr>
				<td align="center">进货价格</td>
				<td><input id="inprice" name ="inprice" value="${requestScope.pro.inprice}" type="text"/></td>
			</tr>
			<tr>
				<td align="center">出售价格</td>
				<td><input id="salprice" name ="salprice" value="${requestScope.pro.salprice}" type="text"/></td>
			</tr>
			<tr>
				<td align="center">进货时间</td>
				<td><input id="date" name ="date" value="${requestScope.pro.date}" type="text" onclick="WdatePicker()" readonly="readonly"/></td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<input id="btnedit" type="button" onclick = "checkedit();" value = "修改"/>
    		 		<input name="btnreset" type="reset" value = "重置"/>
				</td>
			</tr>
			
		</table>
    	</form>
  </body>
</html>
