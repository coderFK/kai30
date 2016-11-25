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
			手动添加书签：
			<br />
			<textarea cols='80' rows='6' name='content' ></textarea>
			<br />
			<button type='submit' class="form_submit">添加</button>
			多个书签可用空格分开，如<b style="height: 5px;">http://www.kai30.com http://www.baidu.com</b>
	    </form>
	    <hr>
	    <form action='bookmark.do' method='post' enctype="multipart/form-data">
	    	通过上传包含书签链接的Html文件添加书签：
	    	<br />
	    	<br />
	    	<input type="file" name="bookmark_html" value="上传Html文件"/>
	    	<br />
	    	<br />
	    	<button type='submit' class="form_submit" name="upload">添加</button>
	    </form>
	</div>
    
	<hr />   	
   	
   	<h2>所有书签：</h2>
   	
    <c:forEach var="bookmark" items="${requestScope.bookmarks }">
    	<img alt="书签" src=${bookmark.getImgUrl() } style="height: 15px; width: 15px;">
    	<a href=${bookmark.getUrl() } title="${bookmark.getUrl() }" style="font-size:small;">${bookmark.getTitle() }</a>
    	<a href='/home/deleteBookmark.do?date=${bookmark.getDate().getTime()}' id="daily_link">删除</a>	
    	<br />
   	</c:forEach>
</body>
</html>