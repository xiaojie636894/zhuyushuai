<%@page import="com.java115.entity.Ptype"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE HTML>
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'MyJsp.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="content-type" content="text/html;charset=utf-8">
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

	var datatable = null;
				$(document).ready(function() {
					createTables();
					$("#btnsearch").click(function(){
						if(datatable!=null){
							datatable.destroy();
						}
						createTables();
					});
				});
				
				function createTables(){
					datatable = $('#table_id_example').DataTable({
						
				language : {
							url : '${pageContext.request.contextPath}/js/jquery/Chinese.json'
						},
						aLengthMenu:[[2,3,4,5,6,10],["2条","3条","4条","5条","6条","10条"]],
						searching: false,
    					ordering:  false,
    					paging:true,
    					serverSide:true,
    					ajax: {
		    						url:'${pageContext.request.contextPath}/admin/pros?op=manager',
		    						type:'POST',
		    						dataSrc : 'data',
		    						data:{proname:$("#proname").val()}
    						},
    					columns:[
		    						{data:'pname'},
		    						{data:'ptype',render:function(data,type,row){
		    							return data.ptypename;
		    						}},
		    						{data:'inprice',render:function(data,type,row){
		    							return "￥"+data;
		    						}},
		    						{data:'salprice',render:function(data,type,row){
		    							return "￥"+data;
		    						}},
		    						{data:'date'},
		    						{data:'pid',render:function(data,type,row){
		    							return "<input name='btnsel' type='button' value='选择' onclick='selpro("+data+");'/>";
		    						}},
    						]
					});
				}
				
	

function selpro(id){
	var rowid="row_"+id;
	
	if($("#"+rowid).length>0){
		var val = parseInt($("#"+rowid+" input[name='pronums']").val());
		$("#"+rowid+" input[name='pronums']").val(val+1);
		return;
	}
	$.ajax({
		url:"${pageContext.request.contextPath}/admin/pros",
		data:{
			op:"view",
			pid:id
		},
		type:"post",
		dataType:"json",
		success:function(result){
			if(result.tag){
				
				var str="";
				str=str+"<tr id=\""+rowid+"\">";
				str=str+"<td>"+result.data.pname+"</td>";
				str=str+"<td>"+result.data.salprice+"</td>";
				str=str+"<td>"+result.data.ptype.ptypename+"</td>";
				str=str+"<td><input name='proids' type='hidden' value='"+result.data.pid+"'/><input name='pronums' type='text' value='1'/></td>";
				str=str+"<td><input name='btndel' type='button' value='删除' onclick='delrow(\""+rowid+"\")'/></td>";
				str=str+"</tr>";
				$("#order_pros").append(str);
			}
		},
		error:function(){
		
		}
	});
}

	
function delrow(rowid){
	$("#"+rowid).remove();
}				
				
				
	
	
</script>
</head>

<body>

	<form id="frmsearch">
		
		产品名称：<input id="proname" value="${requestScope.pname}" type="text" />
		<input id="btnsearch" type="button" value="搜索" />
	</form>
	<br />
	<br />
	<table id="table_id_example" class="display" >
		<thead>
			<tr>
				<th>产品名称</th>
				<th>产品类别</th>
				<th>进货价格</th>
				<th>出售价格</th>
				<th>进货日期</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>

		</tbody>
	</table>
	
</body>
</html>