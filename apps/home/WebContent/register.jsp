<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="css/index.css" type="text/css">
<link href="images/headIcon.png" rel="Shortcut Icon">
<title>注册</title>
</head>
<body>
<!-- 
<div class="left_img" > 
	<img src='images/back_register.jpg' alt='注册' style='width: 800px; height: 450px;' />
</div>
 -->

	
	
<div class="right_form" style='background-color: #CC9909; width: 400px;'>
	<c:choose>
		<c:when test="${requestScope.errors!=null}">
			<c:forEach var="err" items="${requestScope.errors}">
				<h3>${err} </h3>
			</c:forEach>
		</c:when>
		<c:otherwise>
			<h2>${param.username}注册成功</h2>
		</c:otherwise>
	</c:choose>
	<a href="/" >返回</a>
	<form method='post' action='/home/register.do'>
      	<div class="form_title">会员注册</div>
      	<br />
      	
      	<div class="form_normal">
      		邮件：<input type='text' name='email'>
      	</div>
      	
      	<div class="form_normal">
      		名称：<input type='text' name='username'>（最大16字符）
      	</div>
      	<div class="form_normal">
      		密码：<input type='password' name='password'>（6到16字符）
      	</div>
      	<div class="form_normal">
      		确认：<input type='password' name='confirmedPasswd'>
      	</div>
      	
      	<br />
      	<div class="form_normal">
      		<input type='submit' value='  注册  ' class="form_submit">
      	</div>
    </form>

</div>	
	
</body>
</html>