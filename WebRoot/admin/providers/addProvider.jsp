<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

  <title>添加供应商</title>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">

  <script type="text/javascript">
  function checkProv() {
	var name = $("#providername").val();
	var add = $("#provideradd").val();
	var tel = $("#providertel").val();
	var account = $("#account").val();
	var email1 = $("#email1").val();
	var email2 = $("#email2").val();

	if (name == "") {
		$("#spname").html("名称不得为空");
		return false;
	}	
	if (add == "") {
		$("#spadd").html("地址不得为空");
		return false;
	}
	$("#spadd").html("");
	if (tel == "") {
		$("#sptel").html("联系方式不为空");
		return false;
	}
	$("#sptel").html("");
	if (tel != tel.match(/^[0-9]{1,20}$/)) {
		$("#sptel").html("格式错误");
		return false;
	}
	$("#sptel").html("");
	if (account == "") {
        $("#spaccount").html("账户不得为空");
		return false;
	}
	$("#spaccount").html("");
	if (email1 == "") {
		$("#spemail").html("邮箱不为空");
		return false;
	}
	$("#spemail").html("");
	if (email2 == "0") {
		$("#spemail").html("格式错误");
		return false;
	}
	$("#spemail").html("");
	
	if($("#spname").html() != ""){
		return false;
	}
	
    	$.ajax({
		        type : "get",
				url : "${pageContext.request.contextPath}/admin/providers/doprovider?op=doadd",
				data : $("#formadd").serialize(),
				dataType : "text",
				success : function(result) {
					if (result == 1) {
					        datatable.draw(false);
					        $(".bootbox-close-button").click();
				        } else if (result == -1){
					        bootbox.alert("该供应商已存在，无法添加！");
				        } else{
					        bootbox.alert("添加失败！");
				        }
				}
		
		});
  }

  function checkName() {
	var provname = $("#providername").val();
	$.ajax({
			type : "get",
			url : "${pageContext.request.contextPath}/admin/providers/doprovider",
			data : "op=checkname&name=" + provname,
			dateType : "json",
			success : function(result) {
				if (result == "true") {
					$("#spname").html(" 供应商已存在");
				} else {
					$("#spname").html("");
				}
			}
		});
  }
  </script>
</head>

<body bgcolor="white">
	<form id="formadd">
		<table align="center" width="80%">
			<tr height="50">
				<td width="30%" align="right">供应商名称：</td>
				<td width="70%"><input type="text" name="providername"
					id="providername" onBlur="checkName();" /><span id="spname"></span>
				</td>
			</tr>
			<tr height="50">
				<td align="right">所在地：</td>
				<td><input type="text" name="provideradd" id="provideradd" /><span
					id="spadd"></span>
				</td>
			</tr>
			<tr height="50">
				<td align="right">联系方式：</td>
				<td><input type="text" name="providertel" id="providertel" /><span
					id="sptel"></span>
				</td>
			</tr>
			<tr height="50">
				<td align="right">账户：</td>
				<td><input type="text" name="account" id="account" /><span
					id="spaccount"></span>
				</td>
			</tr>
			<tr height="50">
				<td align="right">Email：</td>
				<td><input type="text" name="email1" id="email1"
					style="width:100px" />@ <select name="email2" id="email2">
						<option value="0">请选择</option>
						<c:forEach items="${requestScope.emailType}" var="et">
							<option value="${et}">${et}</option>
						</c:forEach>
				</select> <span id="spemail"></span></td>
			</tr>
			<tr height="50" align="center">
		      <td colspan="2">
				<input type="button" value="添加" class="bt" onClick="return checkProv();">&nbsp;
				<input type="reset" value="重置" class="bt">
			  </td>
			</tr>
		</table>
	</form>
</body>
</html>
