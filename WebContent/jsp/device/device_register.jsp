<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.sql.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="cn.wzr.global.Const" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>设备注册</title>
<%
	Date d = new Date(Long.valueOf((String)request.getAttribute(Const.JSP_P_AUTHCODE_POSTFIX)));
	String date = new SimpleDateFormat(Const.SYS_DATE_FORMAT).format(d);
%>
</head>
<body>
<form action="<%=request.getContextPath() %>/<%=Const.ACT_DEVICE_REGISTER %>">
	<input type="hidden" name="<%=Const.JSP_P_ACTIONNAME%>" value="<%=Const.ACT_DEVICE_REGISTER_SUBMIT%>" />
	注册码为：<input type="text" name="<%=Const.JSP_P_DEVICE_CODE %>" 
	value="<%=request.getAttribute(Const.JSP_P_DEVICE_CODE)%>" readonly="readonly"/> <br>
	提交时间：<%=date %> <br>
	注册信息已上传到服务器，请联系管理员完成注册。<br>
	完成注册前不要关闭本窗口否则注册信息将丢失。<br>
	<input type="hidden" name="<%=Const.JSP_P_AUTHCODE_POSTFIX %>" 
	value="<%=request.getAttribute(Const.JSP_P_AUTHCODE_POSTFIX)%>" />
	<input type="submit" value="注册" />	
</form>
</body>
</html>