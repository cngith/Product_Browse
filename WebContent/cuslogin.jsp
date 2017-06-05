<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
	<%@ page import="cn.wzr.global.Const"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	String act = request.getContextPath() + Const.WEBURL_SEPARATOR + Const.ACT_ACT_SERVLET;
%>
	<form action="<%=act%>" method="post">
		<h2>客商登录</h2>
		用户名:<input type="text" name="<%=Const.JSP_P_CUSNAME%>" value="guest"><br><br>
		密&nbsp&nbsp码:<input type="password" name="<%=Const.JSP_P_PASSWORD%>" value="123"><br><br>
<%-- 		<input type="hidden" name="<%=Const.JSP_P_ACTIONNAME%>" value="<%=Const.ACT_COMMODITY_BROWSE%>" />  --%>
<%-- 		<input type="hidden" name="<%=Const.JSP_P_RETURN_JSP%>" value="<%=Const.JSP_CMDT_BROWSE_LIST%>" />  --%>
<%-- 		<input type="hidden" name="<%=Const.JSP_P_PAGE_NUM%>" value="1" />  --%>
		<input type="hidden" name="<%=Const.JSP_P_ACTIONNAME%>" value="<%=Const.ACT_CUS_CHECK%>" /> 
		<input type="submit" value="登录">
	</form>

</body>
</html>