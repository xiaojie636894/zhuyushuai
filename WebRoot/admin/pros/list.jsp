<%@page import="com.java115.entity.Product"%>
<%@page import="com.java115.service.ProService"%>
<%@page import="com.java115.entity.Ptype"%>
<%@page import="com.java115.service.PtypeService"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>

<!DOCTYPE HTML >
<html>
<head>

<title></title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/js/jquery-3.2.1.min.js"></script>
<script type="text/javascript">
		function changesize(){
			var pagesize = $("#selpagesize").val();
			location.href = "${pageContext.request.contextPath}/admin/pros?op=search&pagesize="+pagesize+"&proname="+$("#proname").val();
		
		}
		
		function changepage(pageindex){
			var pagesize = $("#selpagesize").val();
			location.href = "${pageContext.request.contextPath}/admin/pros?op=search&page="+pageindex+"&pagesize="+pagesize+"&proname="+$("#proname").val();
		
		}
		
		$(document).ready(function(){
			$("#ckball").click(function(){
				$("input[name='ckbproid']").prop("checked",$(this).prop("checked"));
			});
			
			$("#btndel").click(function(){
				var len = $("input[name='ckbproid']:checked").length;
				if(len > 0){
					return confirm("是否要删除？");
					return true;
				}else{
					alert("至少选择一个产品！");
					return false;
				}
				
			});

		});
	
	</script>

</head>

<body>
	<form name="frmsearch" method="post"action="${pageContext.request.contextPath}/admin/pros?op=search" align="center">
		产品名称：<input id="proname" name="proname" value="${requestScope.pname}" type="text"/>
			   <input name="btnsearch" type="submit" value="搜索"/>
	</form><br/><br/>
	<form name="frm" method="post"
		action="${pageContext.request.contextPath}/admin/pros?op=manydel">
		<table border="2" cellspacing="0" bgcolor="#00FFFF"
			bordercolor="#003333" align="center" width="574">
			<tr>
				<td height="31" colspan="8" align="center">产品管理</td>
			</tr>
			<tr>
				<td align="center" width="50" height="20"><input id="ckball"
					name="ckball" type="checkbox" />
				</td>
				<td width="91" height="58" align="center">产品名称</td>
				<td width="90" align="center">产品类别</td>
				<td width="90" align="center">进货价格</td>
				<td width="92" align="center">出售价格</td>
				<td width="92" align="center">进货日期</td>
				<td width="91" colspan="2" align="center">操作</td>
			</tr>
			<c:forEach items="${requestScope.pros}" var="pro">
				<tr>
					<td align="center"><input name="ckbproid" value="${pro.pid}"
						type="checkbox" />
					</td>
					<td height="39" align="center">${pro.pname}</td>
					<td align="center">${pro.ptype.ptypename}</td>
					<td align="center">${pro.inprice}</td>
					<td align="center">${pro.salprice}</td>
					<td align="center">${pro.date}</td>

					<td align="center">
					<a href="${pageContext.request.contextPath}/admin/pros?op=del&id=${pro.pid}" onclick="return confirm('是否删除？');"> 删除</a> 
						<a href="${pageContext.request.contextPath}/admin/pros?op=toedit&id=${pro.pid}">修改</a>
						<a href="${pageContext.request.contextPath}/admin/pros?op=touplood&id=${pro.pid}">添加照片</a>
					</td>
				</tr>
			</c:forEach>




			<tr>
				<td colspan="8" align="center">Pagesize：<select
					name="selpagesize" id="selpagesize" onchange="changesize()">

						<c:forEach items="${requestScope.pagesizes}" var="num">
							<option value="${num}"
								<c:if test="${requestScope.pagesize==num}">
	  			selected = "selected"
	  			</c:if>>${num}条</option>
						</c:forEach>

				</select> <c:if test="${requestScope.pageindex > 1}">
						<a
							href="${pageContext.request.contextPath}/admin/pros?op=search&proname=${requestScope.pname}&page=1&pagesize=${requestScope.pagesize}">首页</a>
						<a
							href="${pageContext.request.contextPath}/admin/pros?op=search&proname=${requestScope.pname}&page=${requestScope.pageindex-1}&pagesize=${requestScope.pagesize}">上一页</a>
					</c:if> <c:if test="${requestScope.pageindex<=1}">
    	   	 首页 上一页
    	    </c:if> <c:choose>
						<c:when test="${requestScope.pageindex < requestScope.pagecount}">
							<a
								href="${pageContext.request.contextPath}/admin/pros?op=search&proname=${requestScope.pname}&page=${requestScope.pageindex+1}&pagesize=${requestScope.pagesize}">下一页</a>
							<a
								href="${pageContext.request.contextPath}/admin/pros?op=search&proname=${requestScope.pname}&page=${requestScope.pagecount}&pagesize=${requestScope.pagesize}">尾页</a>
						</c:when>
						<c:otherwise>
			下一页 尾页
			</c:otherwise>
					</c:choose> 跳至：<select name="selpages" id="selpages"
					onchange="changepage(this.value)">
						<%
							int pageindex = Integer.valueOf(request.getAttribute("pageindex")
									.toString());
							int pagecount = Integer.valueOf(request.getAttribute("pagecount")
									.toString());

							for (int i = 1; i <= pagecount; i++) {
						%>
						<option value="<%=i%>"
							<%if (pageindex == i) {%>
							selected="selected" <%}%>>
							第<%=i%>页
						</option>
						<%
							}
						%>
				</select>
				</td>
			</tr>
			<tr>
				<td align="center" colspan="8">
				<input id="btndel" name="btndel" type="submit" value="批量删除" />
				</td>
			</tr>
		</table>
	</form>
		
</body>
</html>
