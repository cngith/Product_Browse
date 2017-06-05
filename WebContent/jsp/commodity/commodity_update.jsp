<%@page import="cn.wzr.entity.Commodity"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="cn.wzr.global.Const"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>编辑商品信息</title>
</head>
<body>
<%
	Commodity cmdtobj = (Commodity)request.getAttribute(Const.JSP_P_CMDT_OBJ);
	String cmdtId= String.valueOf(cmdtobj.getId());
	String showNo= cmdtobj.getShowNo();
	String cmdtSN= cmdtobj.getCmdtSN();
	String styleNo= cmdtobj.getStyleNo();
	String cmdtName=cmdtobj.getName();
	String cmdtImg =cmdtobj.getImagePath();
	String act = request.getContextPath() + Const.WEBURL_SEPARATOR + Const.ACT_ACT_SERVLET;
%>

<form action="<%=act%>">
	<table>
      <tr>
           <td>商品id:</td> 
           <td><input type="text" name="<%=Const.JSP_P_CMDT_ID%>" id="<%=Const.JSP_P_CMDT_ID%>" readonly="readonly"/></td>
      </tr>
      <tr>
	      <td>展号</td>
	      <td><input type="text" name="<%=Const.JSP_P_CMDT_SHOWNO%>" id="<%=Const.JSP_P_CMDT_SHOWNO%>" /></td>
      </tr>
      <tr>
	      <td>编号</td>
	      <td><input type="text" name="<%=Const.JSP_P_CMDT_CMDTSN%>" id="<%=Const.JSP_P_CMDT_CMDTSN%>"/></td>
      </tr>
      <tr>
	      <td>款号</td>
	      <td><input type="text" name="<%=Const.JSP_P_CMDT_STYLENO%>" id="<%=Const.JSP_P_CMDT_STYLENO%>"/></td>
      </tr>
      <tr>
	      <td>品名</td>
	      <td><input type="text" name="<%=Const.JSP_P_CMDT_CMDTNAME%>" id="<%=Const.JSP_P_CMDT_CMDTNAME%>"/></td>
      </tr>
	</table>
	<input type="hidden" name="<%=Const.JSP_P_CMDT_IMGPATH%>" id="<%=Const.JSP_P_CMDT_IMGPATH%>" value="<%=cmdtImg%>"/>
	<input type="hidden" name="<%=Const.JSP_P_ACTIONNAME%>" value="<%=Const.ACT_COMMODITY_UPDATE%>"/>
	<input type="hidden" name="<%=Const.JSP_P_RETURN_JSP%>" value="<%=Const.JSP_P_RETURN_JSP%>"/>
	<input type="hidden" name="<%=Const.JSP_P_EDIT_STATUS%>" value="<%=Const.JSP_PV_EDITSTA_READY%>"/>
	<input type="submit" value="提交更新" />
</form>

<script type="text/javascript">
	document.getElementById("<%=Const.JSP_P_CMDT_ID%>").value = "<%=cmdtId%>";
	document.getElementById("<%=Const.JSP_P_CMDT_SHOWNO%>").value = "<%=showNo%>";
	document.getElementById("<%=Const.JSP_P_CMDT_CMDTSN%>").value = "<%=cmdtSN%>";
	document.getElementById("<%=Const.JSP_P_CMDT_STYLENO%>").value = "<%=styleNo%>";
	document.getElementById("<%=Const.JSP_P_CMDT_CMDTNAME%>").value = "<%=cmdtName%>";
	document.getElementById("<%=Const.JSP_P_CMDT_IMGPATH%>").value = "<%=cmdtImg%>";
</script>
</body>
</html>