<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>主页</title>
<link rel="stylesheet" href="css/index.css" type="text/css">
</head>
<body>

<!-- 
<div class="left_img" > 
	<img src='images/back_img.jpg' alt='日志' style='width: 800px; height: 450px;' />
</div>
 -->
 
    
<div class="right_form" >
	<div>
			<a href="/apidoc">API帮助文档</a>
	</div>
		
	<br />
	<c:choose>
		
		<c:when test="${sessionScope.login!=null }">
			<a href="/daily/message.do">${sessionScope.login}的日志</a>
		</c:when>
		<c:otherwise>
			<div>
				<h5>${requestScope.loginErr }</h5>
			    <form method='post' action='login.do'>
			      	<div class="form_title">会员登录</div>
			      	<br />
			      	
			      	<div class="form_normal">
			      		名称：<input type='text' name='username'>
			      	</div>
			      	<div class="form_normal">
			      		密码：<input type='password' name='password'>
			      	</div>
			      	<br />
			      	<div class="form_normal">
			      		<input type='submit' value='  登入  ' class="form_submit">
			      	</div>
			    </form>
			    <br />
			    <br />
			    <a href='register.jsp'>注册</a>
			   	<a href='findPassword.jsp' >忘记密码</a>
			   	
			</div>
		</c:otherwise>
	</c:choose>
</div>
	    
</body>
</html>