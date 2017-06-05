<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="cn.wzr.global.Const" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>设备注册</title>
</head>
<body>
您的设备尚未注册，或注册码已过期，请注册后使用：<br/><br/>
<form action="<%=request.getContextPath() %>/<%=Const.ACT_DEVICE_REGISTER %>">
	注册码:<input type="text" name="<%=Const.JSP_P_DEVICE_CODE %>"/>&nbsp
	<input type="hidden" name="<%=Const.JSP_P_ACTIONNAME%>" value="<%=Const.ACT_GET_DEVICE_REGCODE%>" />
	<input type="submit" value="确定" /> 
</form>
</body>
</html>