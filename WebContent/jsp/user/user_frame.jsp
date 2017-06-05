<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="cn.wzr.global.Const"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
 	 
	<frameset rows="30,*" name="frame">
		<frame name="banner" src="<%=Const.JSP_FOLDER_NAME + Const.USER_FOLDER_NAME %>top.jsp">
		<frameset cols="10%,*">
			<frame name="left" src="<%=Const.JSP_FOLDER_NAME + Const.USER_FOLDER_NAME %>left.jsp">
			<frame name="body" src="<%=Const.JSP_FOLDER_NAME + Const.USER_FOLDER_NAME %>body.jsp" marginHeight="0" marginWidth="0">
		</frameset>
	</frameset>

</html>