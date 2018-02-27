<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
%>

<!DOCTYPE HTML PUBLIC "-//W3C//Dth HTML 4.01 Transitional//EN">
<html>
<head>
<title></title>
<script type="text/javascript" src="<%=path%>/js/jquery-1.8.3.js"></script>
<link type="text/css" href="<%=path %>/css/css.css" rel="stylesheet" />
<link rel="stylesheet" href="../css/pintuer.css">
<link rel="stylesheet" href="../css/admin.css">
</head>
<center>
  <h2 style="color:#0ae;">用户列表</h2>
  </center>
  <hr/>
<body>
	<div id="showTable"></div>
</body>
<script type="text/javascript">
	$(document).ready(function() {
		$.post("<%=path%>/bursar.do", {
		
		},
			function(data) { //Servlet执行完之后执行方法，data表示的servlet返回数据内容
				$("#showTable").html(data); //显示Servlet返回的内容
				controlButton();
			});
	});
	function goDel(bid) {
		if (confirm("确定删除该用户吗？")) {
			$(document).ready(function() {
				//设置提交的路径，和参数
				$.post("<%=path%>/del.do", {
					"bid" : bid
				},
					function(data) {
						//Servlet执行完之后执行方法，data表示的servlet返回数据内容
						if (data == "true") {
							alert("删除成功");
							reflush(); 
						} else {
							alert("删除失败");
						}
					});
			});
		}
	}
	function reflush(){
			opener.location.reload(); 
		}
</script>
</html>
