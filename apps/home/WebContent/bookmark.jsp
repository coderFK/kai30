<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="images/headIcon.png" rel="Shortcut Icon">
<link rel="stylesheet" href="css/index.css" type="text/css">
<title>我的书签</title>
</head>
<body>
	<div>
		<a href="/home" target="_top">返回</a>
		<br />
	</div>
	<hr />
	<div>
		<form action='bookmark.do' method='post'>
			添加书签：
			<br />
			<textarea cols='80' rows='6' name='content' ></textarea>
			<br />
			<button type='submit'>添加</button>
	    </form>
	</div>
    
	<hr />   	
   	
   	<h2>所有书签：</h2>
   	
    <c:forEach var="bookmark" items="${requestScope.bookmarks }">
    	<img alt="书签" src=${bookmark.getImgUrl() } style="height: 20px; width: 20px;">
    	<b style="height: 20px;">${bookmark.getTitle() }</b>
    	<a style="height: 20px;" href=${bookmark.getUrl() }>${bookmark.getUrl() }</a>
    	<br />
   	</c:forEach>
</body>
</html>