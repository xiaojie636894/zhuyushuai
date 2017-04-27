<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE HTML >
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'order.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css"
	href="http://cdn.datatables.net/1.10.13/css/jquery.dataTables.css">
	<!-- 最新版本的 Bootstrap 核心 CSS 文件 -->
<link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
<!-- 可选的 Bootstrap 主题文件（一般不用引入） -->
<link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
<script type="text/javascript" charset="utf8"
	src="http://code.jquery.com/jquery-1.10.2.min.js"></script>
	<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>

<script type="text/javascript" charset="utf8"   
	src="http://cdn.datatables.net/1.10.13/js/jquery.dataTables.js"></script>
	<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/bootbox.js/4.4.0/bootbox.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript">

	function add() {
		$.ajax({
							type:"GET",
							url:"${pageContext.request.contextPath}/admin/orders/pro_order.jsp",
							
								success:function(result){
									 var adddialog =  bootbox.dialog({
									title : '选择产品',
									message : result
									});
								}
						});
		
		
	}
	
	$(document).ready(function() {
		$("#btnadd").click(function() {		
			if ($("#cust").val() == "0") {
				$("#custError").html("---请选择客户！---");
				return false;
			}
			if ($("#emp").val() == "0") {
				$("#empError").html("---请选择员工！---");
				return false;
			}
			
		});

	});
	
</script>
  </head>
  
  <body>
  
    <form name="frmadd" id="frmadd" method="post" action="${pageContext.request.contextPath}/admin/orders?op=dolist">
    	订单号：<input type="text" name="orderID" id="orderID" value="${requestScope.orderID}" readonly="readonly"/><br/><br/>
    	客户：<select name="cust" id="cust">
    		<option value="0" >请选择</option>
				<c:forEach items="${requestScope.customer}" var="cu">
				<option value="${cu.customerID}">${cu.customer_name}</option>
			</c:forEach>
    	</select><br/><br/>
    	员工：<select name="emp" id="emp">
    		<option value="0" >请选择</option>
				<c:forEach items="${requestScope.emps}" var="em">
				<option value="${em.empID}">${em.emp_name}</option>
			</c:forEach>
    	</select><br/><br/>
    	订购日期：<input id="orderdate" name="orderdate" type="text" onclick="WdatePicker()" readonly="readonly" /><br/><br/>
    	<a href="javascript:add();">选择产品</a><br/><br/>
    	<table id="order_pros">
    		<tr>
    			<td>产品名称</td>
    			<td>产品价格</td>
    			<td>产品类型</td>
    			<td>产品数量</td>
    			<td>操作</td>
    			
    		</tr>
    	
    	</table><br/><br/>
    	<input id="btnadd" type="submit" value="保存订单"/>
    </form>
    
  </body>
</html>
