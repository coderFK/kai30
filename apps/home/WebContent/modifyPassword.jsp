<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="css/index.css" type="text/css">
<link href="images/headIcon.png" rel="Shortcut Icon">
<title>修改密码</title>
</head>
<body>
<div class="right_form" style='background-color: #CC9909; width: 450px;'>
	<h3>${requestScope.msg }</h3>
    <a href="/home" target="_top">返回</a>
    <form method='post' action='modifyPassword.do'>
      	<div class="form_title">修改密码</div>
      	<br />
      	<div class="form_normal">
      		设置新密码：<input type='password' name='password'>（最少6个字符）<br />
      		确认新密码：<input type='password' name='confirmedPasswd'>
      	</div>
      	<br />
      	<div class="form_normal">
      		<input type='submit' value='  修改  ' class="form_submit">
      	</div>
    </form>
</div>
</body>
</html>