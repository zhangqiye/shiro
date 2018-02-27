<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
	String path = request.getContextPath();
 %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登录</title>  
    <link rel="stylesheet" href="<%=path%>/css/pintuer.css">
    <link rel="stylesheet" href="<%=path%>/css/admin.css">
  
</head>
<body style="background:#D5FFFD">
<div class="bg"></div>
<div class="container">
    <div class="line bouncein">
        <div class="xs6 xm4 xs3-move xm4-move">
            <div style="height:150px;"></div>
            <div class="media media-y margin-big-bottom">           
            </div>         
            <form action="<%=path %>/login.do" method="post" name="f" onsubmit="return run()">
            <div class="panel loginbox">
            	<div class="text-center margin-big padding-big-top"> ${message }</div>
                <div class="text-center margin-big padding-big-top"><h1>明悦电子发票助手</h1></div>
                <div class="panel-body" style="padding:30px; padding-bottom:10px; padding-top:10px;">
                	<div class="form-group">
                        <div class="field field-icon-right" >
                            <input type="text" class="input input-big" name="idCode" placeholder="企业ID"/>
                            <span class="icon icon-user margin-small" style="padding-top:9px;"></span>
                        </div>
                    </div> 
                    <div class="form-group">
                        <div class="field field-icon-right" >
                            <input type="text" class="input input-big" name="loginName" placeholder="登录名"/>
                            <span class="icon icon-user margin-small" style="padding-top:9px;"></span>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="field field-icon-right" >
                            <input type="password" class="input input-big" name="password" placeholder="登录密码"/>
                            <span class="icon icon-key margin-small" style="padding-top:9px;"></span>
                        </div>
                    </div>

                </div>
                <div style="padding:30px;"><input type="submit" class="button button-block bg-main text-big input-big" value="登录"></div>
            </div>
            </form>          
        </div>
    </div>
</div>

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