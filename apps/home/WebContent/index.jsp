<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>kai30-我的个人开发之路</title>
<link rel="stylesheet" href="css/index.css" type="text/css">
<link href="images/headIcon.png" rel="Shortcut Icon">
</head>

<body>

<div class="right_form" >

	<c:choose>
		<c:when test="${sessionScope.login!=null }">
			<ul style="color: #003366">
				<li><a href="/daily/message.do">${sessionScope.login}的日志</a></li>
				<li><a href="/home/bookmark.do">${sessionScope.login}的书签</a></li>
				<li><a href="/daily/logout.do">退出当前账号</a></li>
				<li><a href="/home/modifyPassword.jsp">修改密码</a></li>
				<c:if test="${sessionScope.isManager != null}">
					<li><a href="/home/manageUser.do">管理用户</a></li>
					<li><a href="/home/others.do">其他</a></li>
				</c:if>
			</ul>
			
		</c:when>
		<c:otherwise>
			<div>
				<h5>${requestScope.loginErr }</h5>
			    <form method='post' action='/home/login.do'>
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
			      	<input type="checkbox" name="rememberUser" >记住密码一周
			      	
			    </form>
			    <br />
			    <br />
			    <a href='/home/register.jsp'>注册</a>
			   	<a href='/home/findPassword.jsp' >忘记密码</a>
			   	
			</div>
		</c:otherwise>
	</c:choose>
</div>
	  
<div class="class_other">
	<ul style="color: #003366">
		<li><a href="/daily">日志</a></li>
		<li><a href="/apidoc">API帮助文档</a></li>
		<li><a href="/home/comment.do">留言</a></li>
		<li><a href="/home/master.html">关于本站与站长</a></li>
	</ul>
</div>

<div class="footer">Copyright © 2016-2017 Kai30 赣ICP备16011233号-1</div>
</body>
</html>