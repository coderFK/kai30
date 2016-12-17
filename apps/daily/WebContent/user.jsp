<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
			<h2>${requestScope.name}的日志</h2>
		</c:otherwise>
	</c:choose>
	<hr/>
	
	<hr />
   	<h3>搜索查找日志：</h3>
   	<form method="post" action="user/${requestScope.name}">
   		<input type="text" name="searchKey" value="${requestScope.searchKey }"> <input type="submit" value="在标题中搜索">
   	</form>
   	<c:if test="${requestScope.searchResult!=null && requestScope.searchResult.isEmpty()}">
		抱歉，未在标题中查找到您要的内容
	</c:if>
	
	<c:forEach var="daily" items="${requestScope.searchResult}">
   		<a href='&#35;${daily.getDate().getTime() }' class="daily_link2"><c:out value="${daily.getTitle()}"/></a>
   		<br />
    </c:forEach>
	
	<hr />
   	<h3>分类查找日志：</h3>
   	<c:forEach var="sub" items="${requestScope.subjects }">
   		<a href="/daily/user/${requestScope.name}?subject=${sub}" class="daily_link2"><c:out value="${sub}"/></a>
   	</c:forEach>
   	
   	<hr />
   	
   	<h3>按标题查找日志：</h3>
   	<ul>
   	
   	</ul>
   	<c:forEach var="daily" items="${requestScope.dailys }">
   		<a href='&#35;${daily.getDate().getTime() }' class="daily_link2"><c:out value="${daily.getTitle()}"/></a>
   		<br />
   	</c:forEach>
   	<hr />
	
	<hr/>
	<daily:Dailys/>
		
</body>
</html>