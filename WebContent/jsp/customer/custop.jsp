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
	if(session.getAttribute(Const.JSP_P_CUSNAME)==null){
		response.sendRedirect(request.getContextPath() + File.separator + Const.JSP_CUSLOGIN);
	}

	String act = request.getContextPath() + Const.WEBURL_SEPARATOR + Const.ACT_ACT_SERVLET;
%>
欢迎你<%=(String)session.getAttribute(Const.JSP_P_CUSNAME) %>,登录成功 &nbsp&nbsp&nbsp
<a href = "<%=act%>?<%=Const.JSP_P_ACTIONNAME%>=<%=Const.ACT_COMMODITY_BROWSE%>
	&<%=Const.JSP_P_RETURN_JSP%>=<%=Const.JSP_CMDT_BROWSE_LIST%>
<%-- 	&<%=Const.JSP_P_EDITABLE%>=true --%>
<%-- 	&<%=Const.JSP_P_DELETABLE%>=true --%>
	&<%=Const.JSP_P_PAGE_NUM%>=1" target="body">浏览商品</a>&nbsp&nbsp&nbsp
<a href="<%=request.getContextPath() + Const.WEBURL_SEPARATOR + Const.ACT_CUS_LOGOUT%>" target="_top">退出登录</a>
</body>
</html>