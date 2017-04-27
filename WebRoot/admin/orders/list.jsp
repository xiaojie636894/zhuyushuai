<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE HTML>
<html>
  <head>
    
    <title></title>
    
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
		    						url:'${pageContext.request.contextPath}/admin/orders?op=list',
		    						type:'POST',
		    						dataSrc : 'data',
		    						data:{ordID:$("#ordID").val()}
    						},
    					columns:[
		    						{data:'orderID'},
		    						{data:'customer',render:function(data,type,row){
		    						/*console.log("customer");
		    						console.dir(data);*/
		    							return data.customer_name;
		    						}},
		    						{data:'emp',render:function(data,type,row){
		    						/*console.log("employees");
		    						console.dir(data);*/
		    							return data.emp_name;
		    						}},
		    						{data:'order_date',render:function(data,type,row){
        	var datetime = new Date();
        	 datetime.setTime(data);
        	 var year = datetime.getFullYear();
        		 var month = datetime.getMonth() + 1;
         	var date = datetime.getDate();
        	 var hour = datetime.getHours();
        	 var minute = datetime.getMinutes();
        	 return year + "-" + month + "-" + date+" "+hour+":"+minute;

		    						}},
		    						{data:'orderID',render:function(data,type,row){
		    						
		    							return "<a href='javascript:editorder(\""+data+"\")'>修改</a><a href='javascript:delorder(\""+data+"\")'> 删除</a> <a href='javascript:orderdetail(\""+data+"\")'> 详情</a>";
		    						}},
    						]
					});
				}
				
				
				
				
				
	
	function delorder(orderid) {

		bootbox.confirm({

			title : "系统提示",
			message : "是否删除该订单信息？",
			buttons : {
				confirm : {

					label : 'Yes',
					className : 'btn-success'
				},
				cancel : {
					label : 'No',
					className : 'btn-danger'
				}
			},
			callback : function(result) {
				if(result == true){
					$.ajax({
							type:"GET",
							url:"${pageContext.request.contextPath}/admin/orders?op=delOrder",
							dataType:"text",
							data:{
									orderid:orderid
								},
								success:function(result){
									if(result == "yes"){
										datatable.draw(false);
									}else{
										bootbox.alert("删除失败！");
									}
								}
						});
				}
			}
		});
	}
	
	
	
	
	
	function editorder(orderid) {
		$.ajax({
							type:"GET",
							url:"${pageContext.request.contextPath}/admin/orders?op=editorder",
							dataType:"text",
							data:{
									
									orderid:orderid
								},
								success:function(result){
									  bootbox.dialog({
									title : '订单修改',
									message : result
									});
								}
						});
		
		
	}
	
	function orderdetail(orderid){
		$.ajax({
			type:"get",
			url:"${pageContext.request.contextPath}/admin/orders?op=orderDetail",
			data:{
					orderid:orderid
			},
			dataType:"text",
			success:function(result){
				bootbox.dialog({
				title:'订单详情',
				message:result
				});
			}
		});
	}
	
	
</script>
</head>

<body>
	
	<br />
	<br />
	<table id="table_id_example" class="display" >
		<thead>
			<tr>
				<th>订单号</th>
				<th>客户名</th>
				<th>员工名</th>
				<th>订单日期</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>

		</tbody>
	</table>
	
</body>
</html>