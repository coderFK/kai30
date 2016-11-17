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
		    <button type='submit'>确定</button>
	    </form>
	</div>
	
    
	<hr />   	
   	
   	<h3>所有日志：</h3>
   	
    <c:forEach var="comment" items="${requestScope.comments }">
    	<b>${comment.getUsername() }</b> <br />
    	<b>${comment.getDate() }</b> 
   		<p>${comment.getContent()}</p>
   		<hr />
   	</c:forEach>
		
</body>
</html>