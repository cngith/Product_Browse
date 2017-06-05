<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="cn.wzr.global.Const"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	
	
<title>User left</title>
</head>
<body>
	<label>商品管理</label><br>
	<%
		String cmdtAddJsp=request.getContextPath() + Const.WEBURL_SEPARATOR + Const.JSP_FOLDER_NAME + Const.CMDT_FOLDER_NAME + "commodity_add.jsp";
		String act = request.getContextPath() + Const.WEBURL_SEPARATOR + Const.ACT_ACT_SERVLET;
	%>
	&nbsp<a href = "<%=cmdtAddJsp%>" target="body">添加商品</a><br>
	&nbsp<a href = "<%=act%>?<%=Const.JSP_P_ACTIONNAME%>=<%=Const.ACT_COMMODITY_BROWSE%>
	&<%=Const.JSP_P_RETURN_JSP%>=<%=Const.JSP_CMDT_BROWSE_LIST%>
	&<%=Const.JSP_P_EDITABLE%>=true
	&<%=Const.JSP_P_DELETABLE%>=true
	&<%=Const.JSP_P_PAGE_NUM%>=1" target="body">浏览商品</a><br>
</body>
</html>