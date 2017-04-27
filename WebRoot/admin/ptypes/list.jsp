<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>产品类别管理</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
    <style type="text/css">
      a{text-decoration: none;}
      .bt {
	    background-image:
		url('${pageContext.request.contextPath}/images/bgbt.jpg');
	    width: 82px;
	    height: 23px;
	    border: 0px;
      }
      span {
	    color: red;
	    font-size: 15px;
      }
    </style>
    
	<script type="text/javascript">
	var datatable = null;
	$(document).ready(function() {
		createTables();
		$("#searchcate").click(function() {
			if (datatable != null) {
				datatable.destroy();
			}
			createTables();
		});
		
		$("#addcate").click(function() {
		    $.ajax({
			type : "get",
			url : "${pageContext.request.contextPath}/admin/categorys/docategory",
			data : "op=toadd",
			success : function(result) {
				bootbox.dialog({
                    title: '添加类别',
                    message: result
                });
			  }
		    });
		});
	});
	function createTables() {
		datatable = $('#categoryTab')
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
						url : '${pageContext.request.contextPath}/admin/categorys/docategory?op=dolist',
						dataSrc : 'data',
						data : {
						    searchname:$("#searchname").val()						
						}
					},
					columns : [ {
						data : 'category_name'
					}, {
						data : 'category_desc',					
					}, {
						data : 'categoryID',
						render : function(data, type, row) {
							return "<a href='javascript:editCate("+data+")'>修改</a>"+" "+"<a href='javascript:delCate("+data+")'>删除</a>";
						}
					} ]
				});
	}
	function delCate(id){
	    bootbox.confirm({
            title: "系统提示",
            message: "确定要删除该类别信息吗？",
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
			        url : "${pageContext.request.contextPath}/admin/categorys/docategory",
			        data : "op=dodel&id="+id,
			        dateType : "text",
			        success : function(result) {
				        if (result == 1) {
					        datatable.draw(false);
				        } else if (result == -1){
					        bootbox.alert("该类别下存在相关产品，无法删除！");
				        } else{
					        bootbox.alert("删除失败！");
				        }
			          }
		            });
                }
            }
        });      
    } 
    function editCate(id){
        $.ajax({
			    type : "get",
			    url : "${pageContext.request.contextPath}/admin/categorys/docategory",
			    data : "op=toedit&id="+id,
			    dataType : "text",
			    success : function(result) {
                    bootbox.dialog({
                        title: '类别修改',
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
            <input type="button" id="searchcate" name="searchcate" value="搜索" /><br/><br/>
          类别管理：<input type="button" id="addcate" name="addcate" value="添加类别" />
    <br/><br/>
    <table id="categoryTab" class="display">
    <thead>
        <tr align="center" style="font-weight:bold;">
            <td>类别名称</td>
            
            <td>操作</td>
        </tr>
    </thead>
    <tbody align="center" >

    </tbody>
    </table>
  </body>
</html>