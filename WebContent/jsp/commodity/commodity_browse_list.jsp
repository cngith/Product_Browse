<%@page import="cn.wzr.dao.impl.APage"%>
<%@page import="java.io.File"%>
<%@page import="cn.wzr.util.ActionAssistant"%>
<%@page import="cn.wzr.entity.Commodity"%>
<%@page import="cn.wzr.dao.impl.CommodityDao"%>
<%@page import="cn.wzr.global.Const"%>
<%@page import="java.util.List"%>
<%@page import="java.lang.String"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<HTML>
<HEAD>
<TITLE>查看商品</TITLE>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<script>
	function resetForm(){
		document.getElementById("<%=Const.JSP_P_CMDT_SHOWNO%>").value="";
		document.getElementById("<%=Const.JSP_P_CMDT_CMDTSN%>").value="";
		document.getElementById("<%=Const.JSP_P_CMDT_STYLENO%>").value="";
		document.getElementById("<%=Const.JSP_P_CMDT_CMDTNAME%>").value="";
	}
	function del() { 
	  var msg = "您确定要删除此记录吗？删除后将不能恢复。"; 
	  if (confirm(msg)==true){ 
	    return true; 
	  }
	  else{ 
	    return false; 
	  } 
	} 
</script>
</HEAD>
<BODY oncontextmenu="return false">
<%
	APage<Commodity> pageObj = (APage<Commodity>)request.getAttribute(Const.JSP_P_PAGE); 
	String act = request.getContextPath() + Const.WEBURL_SEPARATOR + Const.ACT_ACT_SERVLET;
%>
<form action="<%=act%>" >
	<input type="hidden" name="<%=Const.JSP_P_ACTIONNAME%>" value="<%=Const.ACT_COMMODITY_BROWSE%>" />
	<input type="hidden" name="<%=Const.JSP_P_ROWS_PERPAGE%>" value="<%=pageObj.getPageSize()%>" />
	<input type="hidden" name="<%=Const.JSP_P_RETURN_JSP%>" value="<%=Const.JSP_CMDT_BROWSE_LIST%>" />
	
	<% 
		String editable = request.getAttribute(Const.JSP_P_EDITABLE)==null?"":(String)request.getAttribute(Const.JSP_P_EDITABLE);
		String deletable = request.getAttribute(Const.JSP_P_DELETABLE)==null?"":(String)request.getAttribute(Const.JSP_P_DELETABLE);
		String styleNo = request.getAttribute(Const.JSP_P_CMDT_STYLENO)==null?"":(String)request.getAttribute(Const.JSP_P_CMDT_STYLENO);;
		String cmdtName = request.getAttribute(Const.JSP_P_CMDT_CMDTNAME)==null?"":(String)request.getAttribute(Const.JSP_P_CMDT_CMDTNAME); 
		String showNo = request.getAttribute(Const.JSP_P_CMDT_SHOWNO)==null?"":(String)request.getAttribute(Const.JSP_P_CMDT_SHOWNO); 
		String cmdtSN = request.getAttribute(Const.JSP_P_CMDT_CMDTSN)==null?"":(String)request.getAttribute(Const.JSP_P_CMDT_CMDTSN); 
		
		String bigImgUrl = act + "?"
			+ Const.JSP_P_ACTIONNAME + "=" + Const.ACT_COMMODITY_BROWSE 		// action
			+ "&" + Const.JSP_P_RETURN_JSP + "=" + Const.JSP_COMMODITY_BROWSE_BIGIMG		// jsp page
			+ "&" + Const.JSP_P_EDITABLE + "=" + editable
			+ "&" + Const.JSP_P_DELETABLE + "=" + deletable
			+ "&" + Const.JSP_P_CMDT_SHOWNO + "=" + showNo 
			+ "&" + Const.JSP_P_CMDT_CMDTSN + "=" + cmdtSN
			+ "&" + Const.JSP_P_CMDT_STYLENO + "=" + styleNo 
			+ "&" + Const.JSP_P_CMDT_CMDTNAME + "=" + cmdtName
			+ "&" + Const.JSP_P_PAGE_NUM + "=" + pageObj.getPageNum();
	%>
	<a href="<%=bigImgUrl%>" >大图浏览模式</a>
	
	<br>
	展号：<input type="text" name="<%=Const.JSP_P_CMDT_SHOWNO%>" id="<%=Const.JSP_P_CMDT_SHOWNO%>" value="<%=showNo%>" size="15"/>
	编号：<input type="text" name="<%=Const.JSP_P_CMDT_CMDTSN%>" id="<%=Const.JSP_P_CMDT_CMDTSN%>" value="<%=cmdtSN%>" size="15"/>
	<input type="button" value="清空文本" onclick="resetForm()"/> <br>
	款号：<input type="text" name="<%=Const.JSP_P_CMDT_STYLENO%>" id="<%=Const.JSP_P_CMDT_STYLENO%>" value="<%=styleNo%>" size="15"/>
	品名：<input type="text" name="<%=Const.JSP_P_CMDT_CMDTNAME%>" id="<%=Const.JSP_P_CMDT_CMDTNAME%>" value="<%=cmdtName%>" size="15"/>
	<input type="submit" value="提交查询" />&nbsp
	
</form>
<%
	String hrefString= act + "?" + Const.JSP_P_ACTIONNAME + "=" + pageObj.getActionName() ;

	hrefString += "&" + Const.JSP_P_RETURN_JSP + "=" + Const.JSP_CMDT_BROWSE_LIST
	+ "&" + Const.JSP_P_EDITABLE + "=" + editable
	+ "&" + Const.JSP_P_DELETABLE + "=" + deletable
	+ "&" + Const.JSP_P_CMDT_STYLENO + "=" + styleNo
	+ "&" + Const.JSP_P_CMDT_CMDTSN + "=" + cmdtSN
	+ "&" + Const.JSP_P_CMDT_CMDTNAME + "=" + cmdtName
	+ "&" + Const.JSP_P_CMDT_SHOWNO + "=" + showNo
	+ "&" + Const.JSP_P_PAGE_NUM + "=";

	int prePage= pageObj.getPageNum()-1;
	int nextPage=pageObj.getPageNum()+1;
	String prePageStr = hrefString + prePage;
	String nextPageStr = hrefString + nextPage;
	String firstPage= hrefString + 1;
	String lastPage= hrefString + pageObj.getTotalPage();
%>	

	<form action="<%=act%>">
		<a href="<%=(prePage > 0)?prePageStr:'#'%>">[上一页]</a>&nbsp
		<a href="<%=(nextPage <= pageObj.getTotalPage())?nextPageStr:'#'%>">[下一页]</a>&nbsp
		<a href="<%=firstPage%>">[首页]</a>&nbsp
		<a href="<%=lastPage%>">[尾页]</a>&nbsp

		<input type="hidden" name="<%=Const.JSP_P_ACTIONNAME%>" value="<%=Const.ACT_COMMODITY_BROWSE%>" />
		<input type="hidden" name="<%=Const.JSP_P_RETURN_JSP%>" value="<%=Const.JSP_CMDT_BROWSE_LIST%>" />
		<input type="hidden" name="<%=Const.JSP_P_EDITABLE%>" value="<%=editable%>" />
		<input type="hidden" name="<%=Const.JSP_P_DELETABLE%>" value="<%=deletable%>" />
		<input type="hidden" name="<%=Const.JSP_P_CMDT_SHOWNO%>" value="<%=showNo%>" />
		<input type="hidden" name="<%=Const.JSP_P_CMDT_CMDTSN%>" value="<%=cmdtSN%>" />
		<input type="hidden" name="<%=Const.JSP_P_CMDT_STYLENO%>" value="<%=styleNo%>" />
		<input type="hidden" name="<%=Const.JSP_P_CMDT_CMDTNAME%>" value="<%=cmdtName%>" />
		转到第<input type="text" name="<%=Const.JSP_P_PAGE_NUM%>" size="3"/>页&nbsp
		<input type="submit" value="go"> 
	</form>

	<table border="1" >
       <tr>
           <td>id</td>
           <td>展号</td>
           <td>编号</td>
           <td>款号</td>
           <td>品名</td>
           <%
           if(Boolean.valueOf(editable)){
        	   out.print("<td>编辑</td>");
           		}
           System.out.println("deletable:" + deletable);
           if(Boolean.valueOf(deletable)){
        	   
        	   out.print("<td>删除</td>");
           		}
           %>
        	   
       </tr>
		<%
			List<Commodity> list=null;
			try{
				if(pageObj!=null){
				 list = (List<Commodity>)pageObj.getList();
				}
			}catch(NullPointerException npe){
				npe.printStackTrace();
			}
	         if(null != list)
	            for(Commodity cmdt:list)
		         {%>
	          <tr>
	              <td><%=cmdt.getId() %></td>
	              <td><%=cmdt.getShowNo()%></td>
	              <td><%=cmdt.getCmdtSN()%></td>
	              <td><%=cmdt.getStyleNo()%></td>
	              <td><%=cmdt.getName()%></td>
	              <%
	              String edturl = act +"?" + Const.JSP_P_ACTIONNAME + "=" + Const.ACT_COMMODITY_UPDATE
	            		  + "&" + Const.JSP_P_EDIT_STATUS + "=" + Const.JSP_PV_EDITSTA_INIT 
	            		  + "&" + Const.JSP_P_CMDT_ID + "=" + cmdt.getId();
	              if(Boolean.valueOf(editable)){
		              out.print("<td><a href ="+ edturl + " >编辑</a></td>");
	              		}
	              
	              String delurl = act +"?" + Const.JSP_P_ACTIONNAME + "=" + Const.ACT_COMMODITY_DELETE
	            		  + "&" + Const.JSP_P_CMDT_ID + "=" + cmdt.getId();
	              if(Boolean.valueOf(deletable)){
	           	   out.print("<td><a href =" + delurl +" onclick='javascript:return del()'>删除</a></td>");
	              		}
	              %>
	              
	          </tr>
           <tr>
           	<% 
           	String imgCompoundPath = cmdt.getImagePath(); 
          		String[] imgPathAry = imgCompoundPath.split(String.valueOf(Const.IMAGE_SEPARATOR));
          		for(int i=0;i<imgPathAry.length;++i){
          			String imgPath=imgPathAry[i];
           	%>
               <td><a href="<%=request.getContextPath()%>/<%=Const.JSP_FOLDER_NAME%><%=Const.CMDT_FOLDER_NAME%><%=Const.JSP_IMGSHOW%>?<%=Const.JSP_P_IMGURL%>=<%=imgPath%>">
               <img src="<%=imgPath%>" style="width:<%=Const.JSP_PV_CMDT_LISTIMG_WIDTH%>px"/></a></td>
               	<%} %>
           </tr>
             <%}
        %>
	</table>
	
	当前第<%=pageObj.getPageNum()%>页 / 共<%=pageObj.getTotalPage() %>页&nbsp&nbsp共<%=pageObj.getTotalRecord()%>条记录
	
	
	<% for(int i=pageObj.getStartPage();i<=pageObj.getEndPage();++i){
			String hrefX = hrefString + i;
	%>		
	
	<a href="<%=hrefX%>">[<%=i%>]</a> 
	<%} %><br>
	
	<a href="<%=(prePage > 0)?prePageStr:'#'%>">[上一页]</a>&nbsp
	<a href="<%=(nextPage <= pageObj.getTotalPage())?nextPageStr:'#'%>">[下一页]</a>&nbsp
	<a href="<%=firstPage%>">[首页]</a>&nbsp
	<a href="<%=lastPage%>">[尾页]</a>&nbsp

	<form action="<%=act%>">
		<input type="hidden" name="<%=Const.JSP_P_ACTIONNAME%>" value="<%=Const.ACT_COMMODITY_BROWSE%>" />
		<input type="hidden" name="<%=Const.JSP_P_RETURN_JSP%>" value="<%=Const.JSP_CMDT_BROWSE_LIST%>" />
		<input type="hidden" name="<%=Const.JSP_P_EDITABLE%>" value="<%=editable%>" />
		<input type="hidden" name="<%=Const.JSP_P_DELETABLE%>" value="<%=deletable%>" />
		<input type="hidden" name="<%=Const.JSP_P_CMDT_SHOWNO%>" value="<%=showNo%>" />
		<input type="hidden" name="<%=Const.JSP_P_CMDT_CMDTSN%>" value="<%=cmdtSN%>" />
		<input type="hidden" name="<%=Const.JSP_P_CMDT_STYLENO%>" value="<%=styleNo%>" />
		<input type="hidden" name="<%=Const.JSP_P_CMDT_CMDTNAME%>" value="<%=cmdtName%>" />
		转到第<input type="text" name="<%=Const.JSP_P_PAGE_NUM%>" size="3"/>页&nbsp
		<input type="submit" value="go"> 
	</form>
</BODY>
</HTML>
