<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>供应商目录</title>
    
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
	var datatable = null;
	$(document).ready(function() {
		createTables();
		$("#searchprov").click(function() {
			if (datatable != null) {
				datatable.destroy();
			}
			createTables();
		});
		
		$("#addprov").click(function() {
		    $.ajax({
			type : "get",
			url : "${pageContext.request.contextPath}/admin/providers/doprovider",
			data : "op=toadd",
			success : function(result) {
				bootbox.dialog({
                    title: '添加供应商',
                    message: result
                });
			  }
		    });
		});
	});
	function createTables() {
		datatable = $('#providerTab')
			.DataTable(
				{
					language : {
						url : '${pageContext.request.contextPath}/JS/Chinese.json'
					},
					searching : false,
					ordering : false,
					paging : true,
					aLengthMenu:[5,4,3,2,1],
					serverSide : true,
					ajax : {
						type : 'post',
						url : '${pageContext.request.contextPath}/admin/providers/doprovider?op=dolist',
						dataSrc : 'data',
						data : {
						    searchname:$("#searchname").val()						
						}
					},
					columns : [ {
						data : 'provider_name'
					}, {
						data : 'provider_add',					
					}, {
						data : 'provider_tel'
					}, {
						data : 'account',					
					}, {
						data : 'email',					
					}, {
						data : 'providerID',
						render : function(data, type, row) {
							return "<a href='javascript:editProv("+data+")'>修改</a>"+" "+"<a href='javascript:delProv("+data+")'>删除</a>";
						}
					} ]
				});
	}
	function delProv(id){
	    bootbox.confirm({
            title: "系统提示",
            message: "确定要删除该供应商信息吗？",
            buttons: {
                confirm: {
                label: '是',
                className: 'btn-success'
              },
            cancel: {
                label: '否',
                className: 'btn-danger'
              }
            },
            callback: function (result) {
                if(result == true){
                    $.ajax({
			        type : "get",
			        url : "${pageContext.request.contextPath}/admin/providers/doprovider",
			        data : "op=dodel&id="+id,
			        dateType : "text",
			        success : function(result) {
				        if (result == 1) {
					        datatable.draw(false);
				        } else if (result == -1){
					        bootbox.alert("该供应商下存在相关产品，无法删除！");
				        } else{
					        bootbox.alert("删除失败！");
				        }
			          }
		            });
                }
            }
        });      
    } 
    function editProv(id){
        $.ajax({
			    type : "get",
			    url : "${pageContext.request.contextPath}/admin/providers/doprovider",
			    data : "op=toedit&id="+id,
			    dataType : "text",
			    success : function(result) {
                    bootbox.dialog({
                        title: '供应商修改',
                        message: result
                    });
			    }
        });      
    } 
	</script>
  </head>
  
  <body>
    <br/>
          类别名称：<input type="text" id="searchname" name="searchname" value="${requestScope.shname}"/>&nbsp;
            <input type="button" id="searchprov" name="searchprov" value="搜索" /><br/><br/>
          类别管理：<input type="button" id="addprov" name="addprov" value="添加供应商" />
    <br/><br/>
    <table id="providerTab" class="display">
    <thead>
        <tr align="center" style="font-weight:bold;">
          <td>名称</td>
          <td>所在地</td>
          <td>联系方式</td>
          <td>账户</td>
          <td>Email</td>
          <td>操作</td>
        </tr>
    </thead>
    <tbody align="center" >

    </tbody>
    </table>
  </body>
</html>
