<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>修改密码</title>
    <script type="text/javascript" src="<%=path%>/js/jquery-1.8.3.js"></script>
     <link rel="stylesheet" href="../css/pintuer.css">
	<link rel="stylesheet" href="../css/admin.css">
	<style type="text/css">
		#main {padding:0;margin:0;} #main .container-fluid{padding:0 4px 0 6px;}
		#header {margin:0 0 8px;position:static;} #header li {font-size:14px;_font-size:12px;}
		#header .brand {font-family:Helvetica, Georgia, Arial, sans-serif, 黑体;font-size:26px;padding-left:33px;}
		#footer {margin:8px 0 0 0;padding:3px 3px 1200px 0;font-size:11px;text-align:center;border-bottom:2px solid #0663A2;}
		#footer, #footer a {color:#999;} #left{overflow-x:hidden;overflow-y:auto;} #left .collapse{position:static;}
		#userControl>li>a{/*color:#fff;*/text-shadow:none;} #userControl>li>a:hover, #user #userControl>li.open>a{background:transparent;}
	</style>
  </head>
  <body>
  <center>
		<h2 style="color:#0ae;">修改密码</h2>
		<hr/>
		<form name = "pro">
			<br/>
			&nbsp;&nbsp;&nbsp;&nbsp;旧密码：<input type="password" name="oldpassword" onblur ="checkOldPassword()" class="input" style="width:250px; line-height:17px;display:inline-block; margin:20px;"/>
			<br/>
			&nbsp;&nbsp;&nbsp;&nbsp;新密码：<input type="password" name="password" class="input" style="width:250px; line-height:17px;display:inline-block; margin:20px;"/>
			<br/>
			确认密码：<input type="password" name="passwordagin" onblur ="checkPassword()" class="input" style="width:250px; line-height:17px;display:inline-block;  margin:20px;"/>
			<br/>
			<input type="button" value="确认" onclick="upd()" class="button border-main icon-search"/>
			<input type="button" value="返回" onclick="back()" class="button border-main icon-search"/>
		</form>
	</center>
	<div id="footer" class="row-fluid">
	             Copyright © 2012-2017 www.mydzfp.cn All Rights Reserved. 网站备案编号：京ICP备13008266号 北京栋邦达科技有限公司
		</div>	
  </body>
  <script type="text/javascript">
	var flag0 = false;
	var flag1 = false;
		function upd(){
			var password=pro.password.value;
		
			if(flag0&&flag1){
			 $(document).ready(function(){
			
			 	//设置提交的路径，和参数
				$.post("<%=path%>/my.do",
				{"password":password},
				function(data){//Servlet执行完之后执行方法，data表示的servlet返回数据内容
	 				
	 				if(data == "true"){
	 					alert("密码修改成功");
	 					location.href= "my.jsp"
	 				}else{
	 					alert("密码修改失败，请联系系统管理员");
	 				}
	 			});
			});}
		}
		function checkOldPassword()	{
			var oldpassword=pro.oldpassword.value;
			 $(document).ready(function(){
					
				 	//设置提交的路径，和参数
					$.post("<%=path%>/mycheck.do",
					{"oldpassword":oldpassword},
					function(data){//Servlet执行完之后执行方法，data表示的servlet返回数据内容
		 				if(data == "true"){
		 					flag1=true;
		 				}else{
		 					alert("旧密码错误");
		 				}
		 			});
				});
		}
		function back(){
			opener.location.reload(); 
			//window.dialogArguments.query(0);//刷新之前页面 
			window.close();//关闭当前页面
		}
		function checkPassword(){
			
			var password=pro.password.value;
			var passwordagin=pro.passwordagin.value;
				if(password!=passwordagin){
					alert("两次密码输入不一样,请重新输入");
					pro.password.value = "";
					pro.passwordagin.value = "";
					
				}else{
					flag0=true;
				}
		}
	</script>
</html>
