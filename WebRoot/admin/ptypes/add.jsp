<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

  <title>添加类别</title>
  <meta http-equiv="pragma" content="no-cache">
  <meta http-equiv="cache-control" content="no-cache">
  <meta http-equiv="expires" content="0">
  <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
  <meta http-equiv="description" content="This is my page">
  
  <script type="text/javascript">
	function checkAdd() {
		var name = $("#categoryname").val();
		var desc = $("#categorydesc").val();
		if (name == "") {
			$("#spname").html(" 名称不得为空");
			return false;
		}
		if (desc == "") {
			$("#spdesc").html(" 描述不得为空");
			return false;
		}
		$("#spdesc").html("");
		if($("#spname").html() != ""){
		    return false;
		}
		
		$.ajax({
		        type : "get",
				url : "${pageContext.request.contextPath}/admin/ptypes?op=doadd",
				data : $("#formadd").serialize(),
				dataType : "text",
				success : function(result) {
					if (result == 1) {
					        datatable.draw(false);
					        $(".bootbox-close-button").click();
				        } else if (result == -1){
					        bootbox.alert("该类别已存在，无法添加！");
				        } else{
					        bootbox.alert("添加失败！");
				        }
				}
		
		});
	}

	function checkName() {
	    var catename = $("#categoryname").val();
		$.ajax({
				type : "get",
				url : "${pageContext.request.contextPath}/admin/ptypes",
				data : "op=checkname&tname=" + catename,
				dataType : "text",
				success : function(result) {
					if (result == "true") {
						$("#spname").html(" 该类别已存在");
					} else{
					    $("#spname").html("");
					}
				}
			});
	}

  </script>
</head>

<body>
	<form id="formadd">
		<table align="center" width="80%">
			<tr height="50">
				<td width="35%" align="right">类别名称：</td>
				<td width="65%"><input type="text" name="categoryname"
					id="categoryname" onBlur="checkName();" /><span id="spname"></span>
				</td>
			</tr>
			
			<tr height="50" align="center">
				<td colspan="2"><input type="button" value="添加" class="bt"
					onClick="return checkAdd();">&nbsp; <input type="reset"
					value="重置" class="bt"></td>
			</tr>
		</table>
	</form>
</body>
</html>