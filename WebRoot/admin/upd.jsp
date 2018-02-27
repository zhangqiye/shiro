<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%
String path = request.getContextPath();
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title></title>
    <script type="text/javascript" src="<%=path%>/js/jquery-1.8.3.js"></script>
    <link rel="stylesheet" href="<%=path %>/css/pintuer.css">
	<link rel="stylesheet" href="<%=path %>/css/admin.css">
  </head>
  <body>
  		<form name="update_user">
  			<input type="hidden" name="userId" value="${upd_user.userId}" class="input">
  			<input type="text"  placeholder="登录名" name="loginName" value="${upd_user.loginName}"class="input">
  			<input type="text"  placeholder="工号" name="no" value="${upd_user.no}"class="input">
  			<input type="text"  placeholder="姓名" name="name" value="${upd_user.name}"class="input">
  			<input type="text"  placeholder="邮箱" name="email" value="${upd_user.email}"class="input">
  			<input type="text"  placeholder="电话" name="phone" value="${upd_user.phone}"class="input">
  			<input type="text"  placeholder="手机" name="mobile" value="${upd_user.mobile}"class="input">
  			<input type="button" value="确认" onclick="upd()" class="button border-main icon-search"/>
  		</form>
  </body>
  <script type="text/javascript">
		function upd(){
			var userId = update_user.userId.value;
			var loginName = update_user.loginName.value;
			var no = update_user.no.value;
			var name = update_user.name.value;
			var email = update_user.email.value;
			var phone = update_user.phone.value;
			var mobile = update_user.mobile.value;
			 $(document).ready(function(){
			 	//设置提交的路径，和参数
				$.post("<%=path%>/UpdSubmit.do",
				{"userId":userId,"loginName":loginName,"no":no,"name":name,"email":email,"phone":phone,"mobile":mobile},
				function(data){//Servlet执行完之后执行方法，data表示的servlet返回数据内容
	 				if(data == "true"){
	 					alert("修改成功");
	 					back();
	 				}else{
	 					alert("修改失败，请联系栋邦达");
	 				}
	 			});
			});
		}
		function back(){
			opener.location.reload(); 
			window.close();//关闭当前页面
		}
	</script>
</html>
