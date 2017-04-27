<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>添加产品</title>  
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<style type="text/css">
      a{text-decoration: none;}
      .bt{background-image: url('../../images/bgbt.jpg');width:82px;height:23px;border: 0px;}
      span{color:red;font-size: 15px;}
    </style>
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
    function editOrder(){
        var orderdate = $("#orderdate").val();
		var customerid = $("#customerid").val();
		var empid = $("#empid").val();
		var rows = $("#orderdetails").find("tr").length;
		
		if (customerid == "0") {
			$("#spcustomerid").html("请选择某客户");
			return false;
		}
			$("#spcustomerid").html("");

	    if (empid == "0") {
			$("#spempid").html("请选择某员工");
			return false;
		}
			$("#spempid").html("");
			
	    if (orderdate == "") {
			$("#sporderdate").html("日期不得为空");
			return false;
		}
		    $("#sporderdate").html("");
		    
		if (rows == 1) {
			$("#spchooseprod").html("请选择产品");
			return false;
		}
			$("#spchooseprod").html("");	
		
		$.ajax({
				type : "get",
				url : "${pageContext.request.contextPath}/admin/orders?op=doedit",
				data : $("#editform").serialize(),
				dataType : "text",
				success : function(result) {
						if (result == "yes") {
							    history.go(-1);
						} else {
								bootbox.alert("修改失败！");
					    }
				}
			 });			 
    }
    
    function chooseProd(){
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
    
    function goBack(){
        history.go(-1);
        
    }
    
    function delRow(rowid){
        $("#"+rowid).remove();
    }
    </script>
</head> 
  
  <body bgcolor="white">
    <form id="editform">
    <table align="center" width="80%">
      <tr>
		<td>&nbsp;</td>
	  </tr>
	  <tr>
		<td colspan="2" bgcolor="#F3FBFE" style="font-size: 20px;font-weight: bold;">修改订单</td>
	  </tr>      
      <tr height="50">
        <td width="40%" align="right">订单编号：</td>
        <td width="60%">
        <input type="hidden" name="id" value="${requestScope.order.id }">
        <input type="text" name="orderid" id="orderid" value="${requestScope.order.orderID }" readonly="readonly"/></td>
      </tr>
      <tr height="50">
        <td align="right">客户：</td>
        <td>
          <select name="customerid" id="customerid">
             <option value="0">请选择</option>
             <c:forEach items="${requestScope.customer}" var="customer">
             <option value="${customer.customerID}" 
               <c:if test="${requestScope.order.customer.customerID== customer.customerID}">
                  selected = "selected"
               </c:if>
             >${customer.customer_name}</option>
             </c:forEach>             
          </select>
          <span id="spcustomerid"></span>      
        </td>
      </tr>
      <tr height="50">
        <td align="right">业务员：</td>
        <td>
          <select name="empid" id="empid">
             <option value="0">请选择</option>
             <c:forEach items="${requestScope.employees}" var="emp">
             <option value="${emp.empID}"
               <c:if test="${requestScope.order.emp.empID == emp.empID}">
                  selected = "selected"
               </c:if>          
             >${emp.emp_name}</option>
             </c:forEach>             
          </select>
          <span id="spempid"></span>   
        </td>
      </tr>
      <tr height="50">
        <td align="right">订购日期：</td>
        <td>
          <input type="text" name="orderdate" id="orderdate" value="${requestScope.order.order_date }" onFocus="WdatePicker()" readonly="readonly"/>
          <span id="sporderdate"></span>
        </td>
      </tr>
      <tr height="50">
        <td align="right">订单详情：</td>
        <td>
        
          <input type="button" name="chooseprod" id="chooseprod" onClick="javascript:chooseProd()" value="选择产品"/>
          <span id="spchooseprod"></span>
        </td>
      </tr>
      <tr height="50">
        <td colspan="2">
          <table id="orderdetails"width="100%" border="1">
            <tr align="center">
              <td width="20%">产品名称</td>
              <td width="20%">产品单价</td>
              <td width="20%">产品类别</td>
              <td width="20%">产品数量</td>
              <td width="20%">操作</td>
            </tr>
            <c:forEach items="${requestScope.orderdetails}" var="orderdetail">
            <tr align="center" id="row_${orderdetail.product.pid}">
              <td>${orderdetail.product.pname}</td>
              <td>${orderdetail.product.salprice}</td>
              <td>${orderdetail.product.ptype.ptypename}</td>
              <td>
                <input type="hidden" name="productid" value="${orderdetail.product.pid}"/>
                <input type="text" name="quantity" value="${orderdetail.quantity}" style="border:0px;text-align:center"/>
              </td>
              <td><a href="javascript:delRow('row_${orderdetail.product.pid}')">删除</a></td>
            </tr>  
            </c:forEach>
          </table>
        </td>
      </tr>
      <tr height="50" align="center">
        <td colspan="2">
          <input type="button"  value="保存" class="bt" id="addorder" onClick="return editOrder();">&nbsp;
          <input type="button"  value="返回" class="bt" id="goback" onClick="goBack();">
        </td>
      </tr>
    </table>
    </form>
  </body> 
</html>
