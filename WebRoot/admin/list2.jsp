<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%
String path = request.getContextPath();
%>

<!DOCTYPE HTML PUBLIC "-//W3C//Dth HTML 4.01 Transitional//EN">
<html>
  <head>
    <title></title>
    <script type="text/javascript" src="<%=path%>/js/jquery-1.8.3.js"></script>
    <link type="text/css" href="<%=path %>/css/css.css" rel="stylesheet"/>
    <link rel="stylesheet" href="<%=path%>/css/pintuer.css">
	<link rel="stylesheet" href="<%=path%>/css/admin.css">
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
  <h2 style="color:#0ae;">用户列表</h2>
  </center>
  <hr/>
  <form name="com" style="padding-bottom:10px">
  	<input type="text"  placeholder="请输入用户姓名" name="name" class="input" style="width:250px; line-height:17px;display:inline-block">
  	<input type="button" value="搜索" onclick="query(0)" class="button border-main icon-search">
  	<div id="permission"></div>
  </form>

  <div id="showTable"></div>
		<div align="right">
		<!-- 	<a href="" id="first" onclick="query(1)" style="color:#0ae;">首页</a>
			<a href="" id="up" onclick="query(2)" style="color:#0ae;">上一页</a>
			<a href="" id="next" onclick="query(3)" style="color:#0ae;">下一页</a>
			<a href="" id="end" onclick="query(4)" style="color:#0ae;">尾页</a> -->
			<input type="button" id="first" value="|<" onclick="query(1)"/>
			<input type="button" id="up" value="<" onclick="query(2)"/>
			<input type="button" id="next" value=">" onclick="query(3)"/>
			<input type="button" id="end" value=">|" onclick="query(4)"/>
			<span id="showPageMessage"></span>
		</div>
		<div id="footer" class="row-fluid">
	             Copyright © 2012-2017 www.mydzfp.cn All Rights Reserved. 网站备案编号：京ICP备13008266号 北京栋邦达科技有限公司
		</div>
		
		</body>
		<script type="text/javascript">
		var pageSize =10;//一页显示的数据笔数
		var pageCurrent = 1;//显示的页数
		var allCount = 0;//总共的数据笔数
		var allPage = 0;//总共数据页数
		queryStatus();
		query(0);
		function update() {
			$(document).ready(function() {
				if($('#status').is(':checked')) {
					var status=0;
				}else{
					var status=1;
				}
				$.post("<%=path%>/updateStatus.do", {
					"status" : status
				},
					function(data) {
					if(data=="true"){
						alert('设置成功')
					}else{
						alert('设置失败')
					}
						
					}); 
		
			});
		}
		
		function queryStatus() {
			
			$(document).ready(function() {
				//设置提交的路径，和参数
				$.post("<%=path%>/queryStatus.do", {
					"bid" : ""
				},
					function(data) {
						//Servlet执行完之后执行方法，data表示的servlet返回数据内容
						$("#permission").html(data);//显示Servlet返回的内容
		 				controlButton();
					});
			});
		}
		function query(num){
			var name = com.name.value;
			if(num == 1){//第一页
				pageCurrent = 1;
			}else if(num == 2){//上一页
				pageCurrent = pageCurrent -1;
			}else if(num == 3){//下一页
				pageCurrent = pageCurrent + 1;
			}else if(num == 4){//最后一页
				pageCurrent = allPage;
			}
			 $(document).ready(function(){
			 	//设置提交的路径，和参数
				$.post("<%=path%>/userlist.do",{"name":name,"pageSize":pageSize,"pageCurrent":pageCurrent},
				function(data){//Servlet执行完之后执行方法，data表示的servlet返回数据内容
	 				$("#showTable").html(data);//显示Servlet返回的内容
	 				controlButton();
	 			});
			});
		}
		function controlButton(){//设置按钮可见与否，停在第一页时不能点击上一页。停在最后一页时，不能点击下一页
			allCount = $("#count").val();
			if(allCount%pageSize == 0){
				allPage = allCount/pageSize
			}else{
				allPage = Math.floor(allCount/pageSize) +1;
			}
			document.getElementById("first").disabled = false;
			document.getElementById("up").disabled = false;
			document.getElementById("next").disabled = false;
			document.getElementById("end").disabled = false;
			if(allPage == 1){
				document.getElementById("first").disabled = true;
				document.getElementById("up").disabled = true;
				document.getElementById("next").disabled = true;
				document.getElementById("end").disabled = true;
			}else if(pageCurrent == 1){
				document.getElementById("first").disabled = true;
				document.getElementById("up").disabled = true;
			}else if(pageCurrent == allPage){
				document.getElementById("next").disabled = true;
				document.getElementById("end").disabled = true;
			}
			$("#showPageMessage").html("总共"+allCount+"笔数据，当前显示"+pageCurrent+"页，共"+ allPage+"页");
			
		}
			<%-- function goUpdate(uid){
				var width = window.screen.width ;
				var height = window.screen.height ;
				window.open("<%=path%>/upd.do?uuid="+uid,"修改",'height=400,width=300,top='+(height-450)/2+',left='+(width-300)/2+',toolbar=no,menubar=no,scrollbars=no, resizable=no,location=no, status=no');
			} --%>
			function goDel(did){
				if(confirm("确认删除？")){
					 $(document).ready(function(){
				 	//设置提交的路径，和参数
					$.post("<%=path%>/del.do",{"did":did},
					function(data){
						//Servlet执行完之后执行方法，data表示的servlet返回数据内容
		 				if(data == "true"){
		 					alert("删除成功");
		 					query(0);
		 				}else{
		 					alert("删除失败");
		 				}
		 			});
				});
				}
				}
				 function goAllow(bid) {
						$(document).ready(function() {
							//设置提交的路径，和参数
							$.post("<%=path%>/allow.do", {
								"bid" : bid
							},
								function(data) {
									//Servlet执行完之后执行方法，data表示的servlet返回数据内容
									if (data == "true") {
										alert("授权成功");
										query(0);
									} else {
										alert("授权失败");
									}
								});
						});
					}
				 function goStop(bid) {
						
						$(document).ready(function() {
							//设置提交的路径，和参数
							$.post("<%=path%>/stop.do", {
								"bid" : bid
							},
								function(data) {
									//Servlet执行完之后执行方法，data表示的servlet返回数据内容
									if (data == "true") {
										alert("授权成功");
										query(0);
									} else {
										alert("授权失败");
									}
								});
						});
					}
				
				function reflush(){
						opener.location.reload(); 
					}
				<%--
				function goAdd(bid){
				if(confirm("确认添加为财务？")){
					 $(document).ready(function(){
				 	//设置提交的路径，和参数
					$.post("<%=path%>/add.do",{"bid":bid},
					function(data){
						//Servlet执行完之后执行方法，data表示的servlet返回数据内容
		 				if(data == "true"){
		 					alert("添加成功");
		 					query(0);
		 				}else{
		 					alert("添加失败");
		 				}
		 			});
				});
				}
			}
			function addUser(){
				var width = window.screen.width ;
				var height = window.screen.height ;
				window.open("<%=path%>/admin/add.jsp","新增财务",'height=400,width=400,top='+(height-450)/2+',left='+(width-300)/2+',toolbar=no,menubar=no,scrollbars=no, resizable=no,location=no, status=no');
			} --%>
		</script>
</html>
