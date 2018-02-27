<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%
	String path = request.getContextPath();
 %>
<html>

	<head>
		<title>明悦电子发票助手</title>
		<link rel="stylesheet" href="<%=path%>/css/pintuer.css">
		<link rel="stylesheet" href="<%=path%>/css/admin.css">
		<script type="text/javascript" src="<%=path%>/js/jquery-1.8.3.js"></script>
		
	</head>
	<body style="background-color: #5DA1FF;">
		<div class="header bg-main">
			<div class="logo margin-big-left fadein-top">
					<h2 style="color:#FFFFFF;font-size:17px;">
					<%-- <img src="<%=path%>/images/icon-search.png" class="radius-circle rotate-hover"
						height="50" alt="" /> --%>
					明阅助手管理系统
					</h2>
				
			</div>
		</div>
		<div class="leftnav" id="showFirstMenu"  >
				
				
				<h2>
					<a><span style="padding-left:10px;color: #FFFFFF;">欢迎您：${sessionScope.user.name}</span></a>
				</h2>
				<ul>
						
						<li>
							<a href="list.jsp" target='right'><span></span>财务管理</a>
						</li>
						<li>
							<a href="list2.jsp" target='right'><span></span>用户列表</a>
						</li>
						<li>
							<a href="my.jsp" target='right'><span></span>修改密码</a>
						</li>
<!-- 						<li>
							<a href="bursar.jsp" target='right'><span></span>财务管理</a>
						</li> -->
						<li>
							<a href="<%=path%>/logout.do"><span></span>退出登录</a>
						</li>
				</ul>
				

		</div>
		
		<div class="admin" >
			<iframe  src="list.jsp" name="right"
				width="100%" height="90%"></iframe>
				
		</div>
		 
		
	</body>
	<script type="text/javascript">

$(function() {
		$(".leftnav h2").next().slideToggle(200);
		$(".leftnav h2").toggleClass("on");
});
$(function() {
	$(".leftnav ul li a").click(function() {
		$("#a_leader_txt").text($(this).text());
		$(".leftnav ul li a").removeClass("on");
		$(this).addClass("on");
	})
});
</script>
</html>