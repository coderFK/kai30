<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="com.kai30.model.UserService, java.util.*, com.kai30.javabean.Daily" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="daily" tagdir="/WEB-INF/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="../css/index.css" type="text/css">
<link href="images/headIcon.png" rel="Shortcut Icon">
<title>用户日志</title>
</head>
<body>
	<div>
		<a href="/home" target="_top">主页</a>
		<a href="/daily" target="_top">写日志</a>
		<a href="/apidoc" target="_top">API帮助文档</a>
	</div>
	
	<c:choose>
		<c:when test="${requestScope.user_err!=null}">
			<h2>${requestScope.user_err}</h2>
		</c:when>
		<c:otherwise>
			<h2>${requestScope.user_name}</h2>
		</c:otherwise>
	</c:choose>
	
	<hr/>
	<daily:Dailys/>
		
</body>
</html>