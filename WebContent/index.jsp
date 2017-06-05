<%@page import="cn.wzr.global.Const"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>欢迎登录</title>
</head>
<body>
		<h3>v1.2</h3>
<a href="<%=request.getContextPath()%>/<%=Const.JSP_CUSLOGIN %>">浏览界面</a>
<a href="<%=request.getContextPath()%>/<%=Const.JSP_USERLOGIN %>">管理界面</a>
</body>
</html>