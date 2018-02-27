<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
	String path = request.getContextPath();
 %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<title>登录</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" href="<%=path%>/css/bootstrap.min.css">
<link rel="stylesheet" href="<%=path%>/css/animate.css">
<link rel="stylesheet" href="<%=path%>/css/style1.css">

</head>
<body class="style-2">

		<div class="container">
			<div class="row">
				<div class="col-md-12 text-center">
					<ul class="menu">
					<li><a>${message }</a></li>
					</ul>
				</div>
			</div>
			<div class="row">
				<div class="col-md-4">
					

					<!-- Start Sign In Form -->
					<form action="<%=path %>/login.do" method="post" name="f" onsubmit="return run()"class="fh5co-form animate-box" data-animate-effect="fadeInLeft">
						<h4>明阅电子发票助手（管理员系统）</h4>
						<div class="form-group">
							<input type="text" name="idCode" class="form-control" id="idCode" placeholder="企业id" value= "${sessionScope.idCode }" >
						</div>
						<div class="form-group">
							<input type="text" name="loginName" class="form-control" id="loginName" placeholder="用户名" value= "${sessionScope.loginName }" >
						</div>
						<div class="form-group">
							<input type="password" name="password" class="form-control" id="password" placeholder="密码" >
						</div>
						<div class="form-group">
							<input type="submit" value="登  录" class="btn btn-primary">
						</div>
					</form>
					<!-- END Sign In Form -->
		Copyright © 2012-2017 www.mydzfp.cn All Rights Reserved. 网站备案编号：京ICP备13008266号 北京栋邦达科技有限公司
				</div>
			</div>
			
		</div>
	
	<!-- jQuery -->
	<script src="<%=path%>/js/jquery.min.js"></script>
	<!-- Bootstrap -->
	<script src="<%=path%>/js/bootstrap.min.js"></script>
	<!-- Placeholder -->
	<script src="<%=path%>/js/jquery.placeholder.min.js"></script>
	<!-- Waypoints -->
	<script src="<%=path%>/js/jquery.waypoints.min.js"></script>
	<!-- Main JS -->
	<script src="<%=path%>/js/main.js"></script>

	</body>
</body>
<script type="text/javascript">
function run(){
	var idCode = f.idCode.value;
	var loginName = f.loginName.value;
	var password = f.password.value;
	if(idCode==""){alert("企业id不能为空");return false;}
	if(loginName==""){alert("登录名不能为空");return false;}
	if(password==""){alert("密码不能为空"); return false;}
}
</script>
</html>