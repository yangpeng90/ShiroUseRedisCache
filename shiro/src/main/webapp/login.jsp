<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<h3>用户登录</h3>
<form action="/shiro/login" method="POST">

<p><input type="text" name="username" value=""></p>
<p><input type="password" name="password" value=""></p>
<p><input type="submit" value="提交"></p>

</form>
</body>
</html>