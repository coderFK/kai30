<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="css/index.css" type="text/css">
<title>查找</title>
</head>
<body>

	<div>
	
		<div>
		<a href="/home" target="_top" style="font-size:x-large;">主页</a>
		</div>
		<br />
	
		<form action="/apidoc/searchApi.do" method="post">
			搜索类<br />
			<input type="text" name="key" value="${ sessionScope.keyValue }"/><br />
			<input type="checkbox" name="caseSensitive"  ${ sessionScope.isCaseSensitiveChecked }/>
			区分大小写 <br /> 
			若要找String类，可直接输入str<br /> 
			<input type="submit" name="search" value=" 搜索 " class="form_submit"
				style="text-align: center" />
		</form>
		
		
		<!-- 
		包
		<br />
		<c:if test="${sessionScope.pbList.isEmpty() && sessionScope.cbList.isEmpty()}">
			<h4>抱歉，搜索不到您要找的包，请查看您输入的包名是否有拼写错误</h4>
		</c:if>
		<ul>
			<c:forEach var="pb" items="${sessionScope.pbList}">
				<li>
					<A HREF="http://tool.oschina.net/uploads/apidocs/jdk-zh/${ pb.getSrc() }" target="packageFrame">${ pb.getName() }</A>
				</li>
			</c:forEach>
		</ul>
		 -->
		
		<hr />
		类
		<br />
		<c:if test="${sessionScope.pbList.isEmpty() && sessionScope.cbList.isEmpty()}">
			<h4>抱歉，搜索不到您要找的类，请查看您输入的类名是否有拼写错误</h4>
		</c:if>
		
		<ul>
			<c:forEach var="cb" items="${sessionScope.cbList}">
				<li>
					<em style="size: 10em;">${ cb.getPackageName()}.</em><A 
					HREF="http://tool.oschina.net/uploads/apidocs/jdk-zh/${ cb.getSrc() }" 
					style="size: 20em;" target="classFrame">${ cb.getName() }</A>  
					<br />
				</li>
			</c:forEach>
		</ul>
	
	</div>
	
</body>
</html>