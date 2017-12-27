<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>恭喜${ user.username }认证成功！</h2>
<shiro:hasPermission name="删除">
		<span>我有"删除"权限了</span>
</shiro:hasPermission>
<shiro:hasPermission name="增加">
		<span>我有"增加"权限了</span>
</shiro:hasPermission>
</body>
</html>