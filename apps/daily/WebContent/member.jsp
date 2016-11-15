<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.kai30.javabean.UserService, java.util.*, com.kai30.javabean.Daily" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>	
<%@ taglib prefix="daily" tagdir="/WEB-INF/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="css/index.css" type="text/css">
<title>日志</title>
</head>
<body>
	<!-- 
	<div class="icon_img">
		<img src='images/icon.jpg' alt='${sessionScope.login }' />
	</div>
	 -->
	<div>
		<a href="/home" target="_top">主页</a>
		<a href="/apidoc" target="_top">API帮助文档</a>
		<a href="logout.do" target="_top">退出当前账号</a>
	</div>
	
	<h3>${sessionScope.login }登陆成功!</h3>
		
	<form action='message.do' method='post'>
	主题：
	<br />
	<input type="text" name="subject" />（可根据主题分类日志，可填“Java”，“Linux”，“其他”之类）
	<br />
	标题：
	<br />
	<textarea cols='80' rows='1' name="title" ></textarea>
	<br />
	内容：
	<br />
	<textarea cols='80' rows='6' name='content' ></textarea>
	<br>
    <button type='submit'>送出</button>
    </form><br />
    
    <daily:Dailys/>
    
		
</body>
</html>