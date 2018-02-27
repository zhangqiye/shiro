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
  		<form name="update_user" >
  			<!-- <div id="showTable"></div> -->
  			<input type="text"  placeholder="登录名" name="loginName" class="input"  >
  			<input type="password"  placeholder="密码" name="password" class="input"  >
  			<input type="text"  placeholder="工号" name="no" class="input"  >
  			<input type="text"  placeholder="姓名" name="name" class="input"  >
  			<input type="text"  placeholder="邮箱" name="email" class="input"  >
  			<input type="text"  placeholder="电话" name="phone" class="input"  >
  			<input type="text"  placeholder="手机" id = "mobile" name="mobile" style="width: 72%;height: 38px;">
  			<input type="button" value="获取验证码" onclick="return y()" class="button border-main icon-search" style="width: 25%;"/>
  			<input type="text"  placeholder="输入验证码" name="yzm"class="input">
  			<input type="button" value="提交" onclick="return run()" class="button border-main icon-search"/>
  		</form>
  </body>
  <script type="text/javascript">
  			function run(){
				var mobile = document.getElementById('mobile').value;
				var password = update_user.password.value;
				var loginName = update_user.loginName.value;
				var name = update_user.name.value;
				if(mobile==""){alert("手机不能为空");return false;}
				if(password==""){alert("密码不能为空"); return false;}
				if(password.length<6){alert("密码长度不符合"); return false;}
				if(loginName==""){alert("登录名不能为空");return false;}
				if(name==""){alert("姓名不能为空");return false;}
				add();
			}
  			function y(){
  				var mobile = document.getElementById('mobile').value;
  				$(document).ready(function(){
  				$.post("<%=path%>/Yzm.do",
  						{"mobile":mobile},
  						function(data){
  							if(data == "false"){
	 							alert("该手机号已被注册，请确认！");
	 						}else{
	 							alert("验证码已经发送，注意查收");
	 						}
  			 			});
  				});
			}
  			$(document).ready(function(){
			 	//设置提交的路径，和参数
				$.post("<%=path%>/office.do",{},
				function(data){//Servlet执行完之后执行方法，data表示的servlet返回数据内容
	 				$("#showTable").html(data);//显示Servlet返回的内容
	 			});
			});
		function add(){
			var loginName = update_user.loginName.value;
			/* var officeId = update_user.officeId.value; */
			var password = update_user.password.value;
			var no = update_user.no.value;
			var name = update_user.name.value;
			var email = update_user.email.value;
			var phone = update_user.phone.value;
			var mobile = update_user.mobile.value;
			var yzm = update_user.yzm.value;
			 $(document).ready(function(){
			 	//设置提交的路径，和参数
				$.post("<%=path%>/addUser.do",
				{"loginName":loginName,"password":password,"no":no,"name":name,"email":email,"phone":phone,"mobile":mobile,"yzm":yzm},
				function(data){
	 				if(data == "true"){
	 					alert("新建成功");
	 					back();
	 				}
	 				if(data == "error"){
	 					alert("验证码错误");
	 				}
	 				if(data == "false"){
	 					alert("新建失败，请检查该用户是否已经存在");
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
