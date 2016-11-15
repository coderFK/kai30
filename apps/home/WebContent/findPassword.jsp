<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="css/index.css" type="text/css">
<title>找回密码</title>
</head>
<body>
<div class="right_form" style="background: gray;">
	<h3>${requestScope.msg }</h3>
    <a href="/home" target="_top">返回</a>
    <form method='post' action='findPassword.do'>
      	<div class="form_title">通过邮箱找回密码</div>
      	<br />
      	<div class="form_normal">
      		用户名：<input type='text' name='username'>
      	</div>
      	<br />
      	<div class="form_normal">
      		<input type='submit' value='  找回  ' class="form_submit">
      	</div>
    </form>
</div>
</body>
</html>