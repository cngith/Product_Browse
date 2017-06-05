<%@page import="java.io.File"%>
<%@page import="java.lang.String"%>
<%@page import="cn.wzr.global.Const"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>User top</title>
</head>
<body>
<%
	if(session.getAttribute(Const.JSP_P_USERNAME)==null){
		response.sendRedirect(request.getContextPath() + File.separator + Const.JSP_USERLOGIN);
	}
%>
欢迎你<%=(String)session.getAttribute(Const.JSP_P_USERNAME) %>,登录成功 &nbsp&nbsp&nbsp
<a href="<%=request.getContextPath() + Const.WEBURL_SEPARATOR + Const.ACT_USER_LOGOUT%>" target="_top">退出登录</a>
</body>
</html>