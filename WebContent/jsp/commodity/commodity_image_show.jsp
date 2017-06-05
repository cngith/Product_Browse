<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="cn.wzr.global.Const"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>显示</title>

</head>
<body>
<input type="hidden" id="ht" /> 

<table oncontextmenu=return(false) top="0" left="0" width="100%" style="border-collapse: collapse;">
	<tr>
	<input type="hidden" id="txt" />
		
	</tr>
	<tr>
	<td>
	<img id="image" style="pointer-events:none;" galleryimg="no" height="300" src="<%=request.getContextPath()%>/<%=request.getParameter(Const.JSP_P_IMGURL)%>" />
	</td>
</table>
<script type="text/javascript">
	document.getElementById("image").height=document.documentElement.clientHeight;
</script>
<script type="text/javascript">
			var txt=document.getElementById("txt");
			txt.value=document.body.scrollHeight;
</script>
</body>
</html>