<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>订单详情</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<style type="text/css">
      a{text-decoration: none;}
    </style>    

</head>
  
  <body bgcolor="white">
      <table align="center" width="100%">    
      <tr height="50">
        <td width="40%" align="right">订单编号：</td>
        <td width="60%"><input type="text"  value="${requestScope.order.orderID}" readonly="readonly"/></td>
      </tr>
      <tr height="50">
        <td align="right">客户：</td>
        <td><input type="text"  value="${requestScope.order.customer.customer_name}" readonly="readonly"/></td>
      </tr>
      <tr height="50">
        <td align="right">业务员：</td>
        <td><input type="text"  value="${requestScope.order.emp.emp_name}" readonly="readonly"/></td>
      </tr>
      <tr height="50">
        <td align="right">订购日期：</td>
        <td>
          <input type="text" name="orderdate" id="orderdate" value="${requestScope.order.order_date}" readonly="readonly" />
        </td>
      </tr>
      <tr height="50">
        <td align="center" colspan="2" style="font-weight: bold;font-size: 20px;">订购产品详情</td>
      </tr>
      <tr height="50">
        <td colspan="2">
          <table border="1" align="center" width="80%">
            <tr align="center" style="font-weight:bold;">
              <td width="20%">产品名称</td>
              <td width="20%">产品单价</td>
              <td width="20%">产品类别</td>
              <td width="20%">产品数量</td>
            </tr>
            <c:forEach items="${requestScope.orderdetails}" var="orderdetail">
            <tr align="center">
              <td>${orderdetail.product.pname}</td>
              <td>${orderdetail.product.salprice}</td>
              <td>${orderdetail.product.ptype.ptypename}</td>
              <td>${orderdetail.quantity}</td>
            </tr>  
            </c:forEach>   
          </table>
      </td>
      </tr>
    </table>
  </body>
</html>

