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
		<br />
		<h3>${sessionScope.login }登陆成功!</h3>
	</div>
	<hr />
	<div>
		<h2>${requestScope.msg}</h2>	
		
		<c:choose>
			<c:when test="${requestScope.modify}">
				<form action='modify.do?time=${requestScope.time.getTime() }' method='post'>
				分类：
				<br />
				<input type="text" name="subject"  value="${requestScope.subject}"/>（可填“Java”，“Linux”或“数据库”字段，也可不填）
				<br />
				标题：
				<br />
				<textarea cols='80' rows='1' name="title" >${requestScope.title}</textarea>
				<br />
				内容：
				<br />
				<textarea cols='80' rows='6' name='content' >${requestScope.content}</textarea>
				<br>
			    <button type='submit'>修改</button>
			    </form>
			</c:when>
			
			<c:otherwise>
				<form action='message.do' method='post'>
				分类：
				<br />
				<input type="text" name="subject"  value="${requestScope.subject}"/>（可填“Java”，“Linux”或“数据库”字段，也可不填）
				<br />
				标题：
				<br />
				<textarea cols='80' rows='1' name="title" >${requestScope.title}</textarea>
				<br />
				内容：
				<br />
				<textarea cols='80' rows='6' name='content' >${requestScope.content}</textarea>
				<br>
			    <button type='submit'>确认</button>
			    </form>
			</c:otherwise>
		</c:choose>
	</div>
    
    <hr />
   	<h3>分类查找日志：</h3>
   	<c:forEach var="sub" items="${requestScope.subjects }">
   		<a href="/daily/user/${sessionScope.login}?subject=${sub}" id="daily_link2"><c:out value="${sub}"/></a>
   	</c:forEach>
   	<hr />
   	
   	<hr />
   	<h3>按标题查找日志：</h3>
   	<ul>
   	
   	</ul>
   	<c:forEach var="daily" items="${requestScope.dailys }">
   		<a href='&#35;${daily.getDate().getTime() }' id="daily_link2"><c:out value="${daily.getTitle()}"/></a>
   		<br />
   	</c:forEach>
   	<hr />
   	
   	
   	<h3>所有日志：</h3>
    <daily:Dailys/>
    
		
</body>
</html>