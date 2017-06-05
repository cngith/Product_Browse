<%@page import="cn.wzr.global.Const"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="cn.wzr.global.EnumFileFrom" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<script type="text/javascript">
function addImage() {
    var div = document.createElement("div");
    var f = document.createElement("input");
    f.setAttribute("type", "file");
    f.setAttribute("name", "image");
    //f.setAttribute("size", "10");
    div.appendChild(f);
    
    document.getElementById("_container").appendChild(div);
}
</script>

<title>添加商品</title>

</head>
<body>

	<form action="<%=request.getContextPath()%>/CommodityAddAction" method="post" enctype="multipart/form-data">
		展号：<input type="text" name="<%=Const.JSP_P_CMDT_SHOWNO%>"><br>
		编号：<input type="text" name="<%=Const.JSP_P_CMDT_CMDTSN%>"><br>
		款号：<input type="text" name="<%=Const.JSP_P_CMDT_STYLENO%>"><br>
		品名：<input type="text" name="<%=Const.JSP_P_CMDT_CMDTNAME%>"><br>
		<table>
			<tr>
				<td>
					图片：
				</td>
				<td>
					<div id="_container">
						<input type="file" name="image"><br>
					</div>
				</td>
			</tr>
		</table>
		 
		<input type="button" value="添加图片" onclick="addImage()"><br>
		<br>
		<input type="hidden" name="filefrom" value="<%= EnumFileFrom.FileFrom.UPLOAD_FILE %>">
		<input type="hidden" name="actionName" value="commodityAddAction"> 
		<input type="submit" value="添加商品">&nbsp
		<input type="reset" name="button" id="button" value="重置" />
	</form>
</body>
</html>