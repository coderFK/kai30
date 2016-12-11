<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>管理用户</title>
</head>
<body>

<h3 >用户</h3>
<ul>
<li>用户名 邮箱</li>	
<c:forEach var="account" items="${requestScope.accounts }">
	<li><a href="/daily/user/${account.username }" target="_blank">${account.username }</a> ${account.email }</li>	
</c:forEach>
</ul>

</body>
</html>