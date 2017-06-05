<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="cn.wzr.global.Const"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
 	 
	<frameset rows="30,*">
		<frame name="banner" src="<%=Const.JSP_FOLDER_NAME + Const.CUS_FOLDER_NAME %>custop.jsp">
		<frame name="body" src="<%=Const.JSP_FOLDER_NAME + Const.CUS_FOLDER_NAME %>cusbody.jsp" marginHeight="0" marginWidth="0">
	</frameset>

</html>