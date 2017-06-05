<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="cn.wzr.global.Const"%>
<%@page import="cn.wzr.dao.impl.APage"%>
<%@page import="cn.wzr.entity.Commodity"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>显示</title>
<style type="text/css"> 
	.box{ margin-left:auto; margin-right:auto; background:url("<%=request.getContextPath()%>/image/<%=Const.JSP_PV_CMDT_BIGIMG_INF%>");background-attachment:fixed;
	background-size:80% ; background-repeat:no-repeat; background-position:center; text-align:center; overflow:hidden; position:relative;}
	.box img{vertical-align:middle;}
	.vline{display:inline-block; height:100%; width:0; vertical-align:middle;}
	.prev, .next{width:50%; _height:2000px; background-image:url(about:blank); position:absolute; top:0; bottom:0; outline:none;}
	.prev{cursor:url("<%=request.getContextPath()%>/image/arrow_left.png"), auto; left:0;}
	.next{cursor:url("<%=request.getContextPath()%>/image/arrow_right.png"), auto; right:0;}

</style>
<script type="text/javascript">
	function goRowNum(){ // 定位到指定行商品
		var rowNum = document.getElementById("<%=Const.JSP_HI_CMDT_ROWNUM%>").value;
		if(isNaN(rowNum)){
			alert("'" + rowNum + "' 不是合法的数字");
			return;
		}
		if(rowNum<0 || isNaN(rowNum)){ // 数字非法，置为1
			rowNum=1;
		}
		var totalRecord=document.getElementById("<%=Const.JSP_HI_CMDT_TOTAL_RECORD%>").value;
		if(rowNum > totalRecord){
			rowNum = totalRecord;
		}
		var cmdtAll=document.getElementById("<%=Const.JSP_HI_CMDTLIST%>").value;
		var imgColNum=5;	
		if(cmdtAll.length>0){ // 存在商品记录
			var cmdtList = new Array();
			// 取记录列表
			cmdtList = cmdtAll.split("<%=Const.RECORD_SEPARATOR%>");
			// 取记录
			var cmdt = new Array();
			cmdt = cmdtList[rowNum-1].split("<%=Const.FIELD_SEPARATOR%>");
			
			// 取图片列表
		   // arrImgUrl是图片地址数组,保存单件商品图片
			var arrImgUrl = new Array();
			arrImgUrl=cmdt[imgColNum].split("<%=Const.IMAGE_SEPARATOR%>");
		   
			// 取图片
			if (arrImgUrl[0]) {
               	// 图片地址更换
             document.getElementById("img").src = arrImgUrl[0];
			}
			else{
				document.getElementById("img").src = "<%=request.getContextPath()%>/image/<%=Const.JSP_PV_CMDT_BIGIMG_INF%>";
			}
			
			document.getElementById("<%=Const.JSP_HI_CMDT_ROWNUM%>").value = rowNum;
			document.getElementById("<%=Const.JSP_P_CMDT_ID%>").value = cmdt[0];
			document.getElementById("<%=Const.JSP_P_CMDT_SHOWNO%>").value = cmdt[1];
			document.getElementById("<%=Const.JSP_P_CMDT_CMDTSN%>").value = cmdt[2];
			document.getElementById("<%=Const.JSP_P_CMDT_STYLENO%>").value = cmdt[3];
			document.getElementById("<%=Const.JSP_P_CMDT_CMDTNAME%>").value = cmdt[4];
			document.getElementById("<%=Const.JSP_HI_CMDT_IMGNUM%>").value = 1;
			document.getElementById("<%=Const.JSP_HI_CMDT_CURIMGCOUNT%>").value = arrImgUrl.length;
			
			//document.getElementById("imgIndex").value = indexImage;
		}
	}
</script>
</head>
	<body>
	<%
		APage<Commodity> pageObj = (APage<Commodity>)request.getAttribute(Const.JSP_P_PAGE);
		int rowNum = pageObj.getPageSize() * (pageObj.getPageNum()-1); // 0 开始
		// 商品信息连接成字符串
		String cmtdString = "";
		List<Commodity> list=null;
		try{
			if(pageObj!=null){
			 list = (List<Commodity>)pageObj.getList();
			}
		}catch(NullPointerException npe){
			npe.printStackTrace();
		}
		int imgCount=0;
		String showNo=""; 
		String cmdtSN="";
		String styleNo=""; 
		String cmdtName="";
		long id=1L;
		if(null != list){
	       for(Commodity cmdt:list)
		    {
				cmtdString += String.valueOf(cmdt.getId()) + Const.FIELD_SEPARATOR;
				cmtdString += cmdt.getShowNo() + Const.FIELD_SEPARATOR;
				cmtdString += cmdt.getCmdtSN() + Const.FIELD_SEPARATOR;
				cmtdString += cmdt.getStyleNo() + Const.FIELD_SEPARATOR;
				cmtdString += cmdt.getName() + Const.FIELD_SEPARATOR;
				cmtdString += cmdt.getImagePath() + Const.RECORD_SEPARATOR;
		    }
          if("".equals(cmtdString)){ // 如果不存在商品信息
        	  	id=0;
        	  	showNo="";
        	  	cmdtSN="";
        	  	styleNo="";
        	  	cmdtName="";
        	  	imgCount=0;
          	}
          else{// 如果存在商品信息
	          cmtdString = cmtdString.substring(0, cmtdString.length()-1);
	           	// 取当前Id
				id=((Commodity)list.get(rowNum)).getId();         
	           	// 取当前款号
				showNo=((Commodity)list.get(rowNum)).getShowNo();         
	           	// 取当前品名
				cmdtSN=((Commodity)list.get(rowNum)).getCmdtSN();           
	           	// 取当前款号
				styleNo=((Commodity)list.get(rowNum)).getStyleNo();         
	           	// 取当前品名
				cmdtName=((Commodity)list.get(rowNum)).getName();           
	           	// 取当前记录的图片数量
	          String rImgString=((Commodity)list.get(rowNum)).getImagePath();
	          imgCount = rImgString.split(String.valueOf(Const.IMAGE_SEPARATOR)).length;
            }
         }
		
	String listBrowseUrl = request.getContextPath() + Const.WEBURL_SEPARATOR + Const.ACT_ACT_SERVLET +"?" 
			+ Const.JSP_P_ACTIONNAME + "=" + Const.ACT_COMMODITY_BROWSE 		// action
			+ "&" + Const.JSP_P_RETURN_JSP + "=" + Const.JSP_CMDT_BROWSE_LIST		// jsp page
			+ "&" + Const.JSP_P_EDITABLE + "=" + request.getAttribute(Const.JSP_P_EDITABLE)
			+ "&" + Const.JSP_P_DELETABLE + "=" + request.getAttribute(Const.JSP_P_DELETABLE)
			+ "&" + Const.JSP_P_CMDT_SHOWNO + "=" + request.getAttribute(Const.JSP_P_CMDT_SHOWNO) 
			+ "&" + Const.JSP_P_CMDT_CMDTSN + "=" + request.getAttribute(Const.JSP_P_CMDT_CMDTSN) 
			+ "&" + Const.JSP_P_CMDT_STYLENO + "=" + request.getAttribute(Const.JSP_P_CMDT_STYLENO) 
			+ "&" + Const.JSP_P_CMDT_CMDTNAME + "=" + request.getAttribute(Const.JSP_P_CMDT_CMDTNAME) 
			+ "&" + Const.JSP_P_PAGE_NUM + "=" + request.getParameter(Const.JSP_P_PAGE_NUM);
	%>
	<a href="<%=listBrowseUrl%>" >列表浏览模式</a>
	<table style="margin:auto;" id="table">
		<tr id="tr">
			<td id="td" height="30">
			<!-- 商品:<input type="number" min="0" max="<%=pageObj.getTotalRecord() %>" id="<%=Const.JSP_HI_CMDT_ROWNUM%>" value="<%=rowNum+1%>" onkeydown="if(event.keyCode==13) goRowNum();"/>/<%=pageObj.getTotalRecord() %> ； -->
			商品:<input type="text" id="<%=Const.JSP_HI_CMDT_ROWNUM%>" value="<%=rowNum+1%>" size="4" onkeydown="if(event.keyCode==13) goRowNum();"/>/<%=pageObj.getTotalRecord() %> ；
			图片:<input type="text" id="<%=Const.JSP_HI_CMDT_IMGNUM%>" value="1" readonly="readonly" size="2" />/<input type="text" id=<%=Const.JSP_HI_CMDT_CURIMGCOUNT %> value="<%=imgCount%>" readonly="readonly" size="2"/>
			
			</td>
		</tr>
		<tr>
			<td>
			
			商品ID:<input type="text" id="<%=Const.JSP_P_CMDT_ID%>" value="<%=id%>" readonly="readonly" size="4"/>&nbsp
			展号:<input type="text" id="<%=Const.JSP_P_CMDT_SHOWNO%>" value="<%=showNo%>" readonly="readonly" size="4"/>&nbsp
			编号:<input type="text" id="<%=Const.JSP_P_CMDT_CMDTSN%>" value="<%=cmdtSN%>" readonly="readonly" size="9"/><br>
			款号:<input type="text" id="<%=Const.JSP_P_CMDT_STYLENO%>" value="<%=styleNo%>" readonly="readonly" />&nbsp
			品名:<input type="text" id="<%=Const.JSP_P_CMDT_CMDTNAME%>" value="<%=cmdtName%>" readonly="readonly" />
			</td>
		</tr>
	</table>
	
	<input type="hidden" id="<%=Const.JSP_HI_CMDTLIST%>" value="<%=cmtdString%>"/> <!-- 保存查到的所有商品信息 -->
	<input type="hidden" id="<%=Const.JSP_HI_CMDT_TOTAL_RECORD%>" value="<%=pageObj.getTotalRecord()%>"/> <!-- 保存查到的所有商品信息 -->
	<input type="hidden" id="rowNum" value="<%=rowNum%>"/> <!-- 保存当前显示的商品行号，起始索引0 -->
	<!-- <input type="hidden" id="imgIndex" value="0"/> 保存当前显示的图片索引号，起始索引0 -->
	
	<input type="hidden" name="<%=Const.JSP_P_ACTIONNAME%>" value="<%=Const.ACT_COMMODITY_BROWSE%>" />
	<input type="hidden" name="<%=Const.JSP_P_RETURN_JSP%>" value="<%=Const.JSP_CMDT_BROWSE_LIST%>" />

<div id="box" class="box">                	
    <s class="prev" title="这是第一张图片"></s>
    <s class="next" title="下一张"></s>
    <img src="" id="img"/><i class="vline"></i>
</div> 


<script>
	document.getElementById("img").height = window.screen.availHeight - document.getElementById("td").height * 4;
	document.getElementById("box").width = window.screen.availWidth;
	//document.getElementById("box").width = document.getElementById("img").width;
	document.getElementById("table").width = document.getElementById("img").width;
	
	var cmdtAll=document.getElementById("<%=Const.JSP_HI_CMDTLIST%>").value;
	var imgColNum=5;	
	if(cmdtAll.length>0){ // 存在商品记录
		var cmdtList = new Array();
		// 取记录列表
		cmdtList = cmdtAll.split("<%=Const.RECORD_SEPARATOR%>");
		
		// 取记录
		var cmdt = new Array();
		cmdt = cmdtList[<%=rowNum%>].split("<%=Const.FIELD_SEPARATOR%>");
		//alert("rowNum:" + <%=rowNum%>);
		// 取图片列表
	   // arrImgUrl是图片地址数组,保存单件商品图片
		var arrImgUrl = new Array();
		arrImgUrl=cmdt[imgColNum].split("<%=Const.IMAGE_SEPARATOR%>");
	   
		// 取图片
		document.getElementById("img").src = arrImgUrl[0];
		
		var eleBox = document.getElementById("box");
		var eleImg = null, eleLink = null;
		var indexLoop = 0;
		
		if (eleBox && (eleImg = eleBox.getElementsByTagName("img")[0]) &&
		    (eleLink = eleBox.getElementsByTagName("s")).length === 2){
		    for (; indexLoop < 2; indexLoop += 1) {
		        (function (index) {
		        	eleLink[index].onclick = function clickImg() {
		        		
		        	var indexImage = document.getElementById("<%=Const.JSP_HI_CMDT_IMGNUM%>").value - 1; 
		        	// index=0 代表点了左边;index=1 代表点了右边
					if(index==0){ // 点了向左
						if (indexImage <= 0) {
							if(document.getElementById("<%=Const.JSP_HI_CMDT_ROWNUM%>").value <= 1){
								// 到了第一条记录
								indexImage = 0;
								cmdt = cmdtList[document.getElementById("<%=Const.JSP_HI_CMDT_ROWNUM%>").value - 1].split("<%=Const.FIELD_SEPARATOR%>");
						    	eleLink[0].title = "这是第一张图片";
						    	alert("这已经是第一张图片");
							}
							else{ // 前面还有记录，向前移一条，图片索引指向当前记录末尾图片。
								document.getElementById("<%=Const.JSP_HI_CMDT_ROWNUM%>").value = document.getElementById("<%=Const.JSP_HI_CMDT_ROWNUM%>").value - 1;
								cmdt = cmdtList[document.getElementById("<%=Const.JSP_HI_CMDT_ROWNUM%>").value - 1].split("<%=Const.FIELD_SEPARATOR%>");
								arrImgUrl=cmdt[imgColNum].split("<%=Const.IMAGE_SEPARATOR%>");
								indexImage = (arrImgUrl.length - 1);
							}
						} else { // 不是本条记录的第一张图片，向前移动一张
							cmdt = cmdtList[document.getElementById("<%=Const.JSP_HI_CMDT_ROWNUM%>").value - 1].split("<%=Const.FIELD_SEPARATOR%>");
							indexImage--;
						   	eleLink[0].title = "上一张";
						}
					}
					else{// 点了向右
						if (indexImage >= arrImgUrl.length - 1) { // 本条商品记录的最后一张图片
							if(document.getElementById("<%=Const.JSP_HI_CMDT_ROWNUM%>").value >= <%=pageObj.getTotalRecord() %>){
								// 到了最后一条记录
								cmdt = cmdtList[document.getElementById("<%=Const.JSP_HI_CMDT_ROWNUM%>").value - 1].split("<%=Const.FIELD_SEPARATOR%>");
								arrImgUrl=cmdt[imgColNum].split("<%=Const.IMAGE_SEPARATOR%>");
								indexImage = arrImgUrl.length - 1;
								eleLink[1].title = "这是最后一张图片";   
								alert("这已经是最后一张图片");
							}
							else{ // 后面还有记录，向后移一条，图片索引指向当前记录（本商品）起始图片。
								document.getElementById("<%=Const.JSP_HI_CMDT_ROWNUM%>").value = parseInt(document.getElementById("<%=Const.JSP_HI_CMDT_ROWNUM%>").value) + 1;
								cmdt = cmdtList[document.getElementById("<%=Const.JSP_HI_CMDT_ROWNUM%>").value - 1].split("<%=Const.FIELD_SEPARATOR%>");
								arrImgUrl=cmdt[imgColNum].split("<%=Const.IMAGE_SEPARATOR%>");
								indexImage = 0;
							}
						} else { // 不是本条商品记录的最后一张图片，向后移动一张
							cmdt = cmdtList[document.getElementById("<%=Const.JSP_HI_CMDT_ROWNUM%>").value - 1].split("<%=Const.FIELD_SEPARATOR%>");
							indexImage++;
							eleLink[1].title = "下一张";
						}
					}
					
					//
					document.getElementById("<%=Const.JSP_P_CMDT_ID%>").value = cmdt[0];
					document.getElementById("<%=Const.JSP_P_CMDT_SHOWNO%>").value = cmdt[1];
					document.getElementById("<%=Const.JSP_P_CMDT_CMDTSN%>").value = cmdt[2];
					document.getElementById("<%=Const.JSP_P_CMDT_STYLENO%>").value = cmdt[3];
					document.getElementById("<%=Const.JSP_P_CMDT_CMDTNAME%>").value = cmdt[4];
					document.getElementById("<%=Const.JSP_HI_CMDT_CURIMGCOUNT%>").value = arrImgUrl.length;
					
					//document.getElementById("imgIndex").value = indexImage;
					document.getElementById("<%=Const.JSP_HI_CMDT_IMGNUM%>").value = indexImage + 1;
					
					var indexWill = indexImage;
					
					if (arrImgUrl[indexWill]) {
		               	// 图片地址更换
		             eleImg.src = arrImgUrl[indexWill];
					}
					else{
						eleImg.src = "<%=request.getContextPath()%>/image/<%=Const.JSP_PV_CMDT_BIGIMG_INF%>";
					}
					return false;
				};
		        
		       })(indexLoop);
		    }
		}
	}
	else{ // 不存在商品记录
		document.getElementById("<%=Const.JSP_P_CMDT_ID%>").value = "";
		document.getElementById("<%=Const.JSP_P_CMDT_SHOWNO%>").value = "";
		document.getElementById("<%=Const.JSP_P_CMDT_CMDTSN%>").value = "";
		document.getElementById("<%=Const.JSP_P_CMDT_STYLENO%>").value = "";
		document.getElementById("<%=Const.JSP_P_CMDT_CMDTNAME%>").value = "";
		document.getElementById("<%=Const.JSP_HI_CMDT_ROWNUM%>").value = 0;
		//document.getElementById("imgIndex").value = 0;
		document.getElementById("<%=Const.JSP_HI_CMDT_IMGNUM%>").value = 0;
		//document.getElementById("box").getElementsByTagName("img")[0]
	}
</script>
<br>

</body>
</html>