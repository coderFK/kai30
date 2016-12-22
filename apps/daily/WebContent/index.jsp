<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="daily" tagdir="/WEB-INF/tags"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>日志</title>
</head>
<body>
	<div>
		<a href="/home" target="_top">主页</a>
		<a href="message.do" target="_top">我的日志</a>
	</div>
	
	
   	<h3>查找日志：</h3>
   	<form method="post" action="daily.do">
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
	
	<h3>所有日志：</h3>
    <daily:Dailys/>
</body>
</html>