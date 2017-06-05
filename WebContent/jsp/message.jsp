<%@page import="cn.wzr.global.Const"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%= request.getAttribute(Const.JSP_P_MESSAGE) %><br>
<a href="<%=request.getAttribute(Const.JSP_P_FORWARD_URL) %>" ><%=request.getAttribute(Const.JSP_P_URL_MESSAGE)%></a>
</body>
</html>