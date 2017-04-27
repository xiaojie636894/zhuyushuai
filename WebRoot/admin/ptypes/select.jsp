<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>

<!DOCTYPE HTML >
<html>
  <head>
    
    
    <title>My JSP 'select.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/js/jquery-3.2.1.min.js"></script>
	<script type="text/javascript">
		var httprequest = null;
	function changeType(typeid){
		var selproducts = document.getElementById("selproducts");
		selproducts.length=1;
		httprequest = getXMLHttpRequest();
		httprequest.open("get","${pageContext.request.contextPath}/admin/pros?op=getprosbytype&typeid="+typeid+"&"+"time="+new Date().getTime());
		httprequest.onreadystatechange=function(){
		if (httprequest.readyState == 4 && httprequest.status == 200) {
				var re = httprequest.responseText;
				var jsonarray = JSON.parse(re);
				
				
				for(var i=0;i<jsonarray.length;i++){
					var json = jsonarray[i];
					selproducts.add(new Option(json.pname,json.pid));
				}
			}
		};
		httprequest.send(null);
	}
	
		
		function getXMLHttpRequest() {
			if (window.XMLHttpRequest) {
				return new XMLHttpRequest();
			} else {
				var names = [ "msxml", "msxml2", "msxml3", "Microsoft" ];
				for ( var i = 0; i < names.length; i++) {
					try {
						var name = names[i] + ".XMLHTTP";
						return new ActiveXObject(name);
					} catch (e) {
					}
				}
			}
			return null;
		}
		
		function doajax(typeid){
		var selproducts = document.getElementById("selproducts");
		selproducts.length=1;
			$.ajax({
					type:"get",
					url:"${pageContext.request.contextPath}/admin/pros",
					data:"op=getprosbytype&typeid="+typeid,
					dataType:"json",
					success:function(result){
						
						for(var i=0;i<result.length;i++){
						var json = result[i];
						selproducts.add(new Option(json.pname,json.pid));
				}
					}
			});
		}
		
		function doajax2(typeid){
		$("#selproducts").empty();
		$("#selproducts").append("<option value='0'>---请选择---</option>");
			$.ajax({
					type:"get",
					url:"${pageContext.request.contextPath}/admin/pros",
					data:"op=getprosbytype&typeid="+typeid,
					dataType:"json",
					success:function(result){
						
						for(var i=0;i<result.length;i++){
						var json = result[i];
						$("#selproducts").append("<option value='"+json.pid+"'>"+json.pname+"</option>");
				}
					}
			});
		}
	</script>
  </head>
  
  <body>
    <select id="seltypes" onchange="doajax2(this.value)">
    	<option value="0">---请选择---</option>
    	<c:forEach items="${ptypes}" var="ptype">
    	<option value="${ptype.ptypeid}">${ptype.ptypename}</option>
    	</c:forEach>
    </select>
    <select id="selproducts">
    	<option value="0">---请选择---</option>
    </select>
  </body>
</html>
