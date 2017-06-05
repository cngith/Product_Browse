<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="cn.wzr.global.Const"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>TJ用户登录</title>
<script language=”JavaScript”>
	if (window != top)
		top.location.href = location.href;
</script>
</head>
<body>
	
	<form action="<%=request.getContextPath()%>/ActionServlet" method="post">
		<h2>用户登录</h2>
		用户名:<input type="text" name="<%=Const.JSP_P_USERNAME%>"><br><br>
		密&nbsp&nbsp码:<input type="password" name="<%=Const.JSP_P_PASSWORD%>"><br><br>
		<input type="hidden" name="<%=Const.JSP_P_ACTIONNAME%>" value="<%=Const.ACT_USER_CHECK%>" /> 
		<input type="submit" value="登录"><br><br>
	</form>
<font color="red"><%=request.getAttribute("message") == null ? "":request.getAttribute("message") %></font><br>
</body>
</html>