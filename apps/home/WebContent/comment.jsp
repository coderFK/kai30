<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.kai30.model.UserService, java.util.*, com.kai30.javabean.Comment" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="css/index.css" type="text/css">
<link href="images/headIcon.png" rel="Shortcut Icon">
<title>留言</title>
</head>
<body>
	<div>
		<a href="/home" target="_top">返回</a>
		<br />
	</div>
	<hr />
	<div>
		<form action='comment.do' method='post'>
			留言：
			<br />
			<textarea cols='80' rows='6' name='content' ></textarea>
			<br />
			<c:choose>
				<c:when test="${sessionScope.login!=null }">
					<button type='submit'>确定</button>
				</c:when>
				<c:otherwise>
					<a href="/home"  >请先登陆</a>
				</c:otherwise>
			</c:choose>
		    
	    </form>
	</div>
	
    
	<hr />   	
   	
   	<h2>所有留言：</h2>
   	
    <c:forEach var="comment" items="${requestScope.comments }">
    	用户：<b>${comment.getUsername() }</b>
    	<em><fmt:formatDate value="${comment.getDate() }" timeStyle="full" dateStyle="full" /> </em>
   		<p>${comment.getContent()}</p>
   		<hr />
   	</c:forEach>
		
</body>
</html>