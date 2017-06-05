package cn.wzr.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.RepaintManager;

import org.omg.CORBA.Environment;

import cn.wzr.global.Const;
import cn.wzr.util.ACWhiteListor;
import cn.wzr.util.Cookier;

/**
 * Servlet implementation class UserCtrl
 */
@WebServlet("/DeviceRegister")
public class DeviceRegister extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String actName=req.getParameter(Const.JSP_P_ACTIONNAME);
		StringBuffer msg = new StringBuffer();
		String nr=System.getProperty("line.separator");
		if(null == actName || 0==actName.trim().length()){
			msg.append(Const.ERRMSG_UNDEFINED_ACTION + nr);
		}
		
		if(null != actName && actName.equals(Const.ACT_GET_DEVICE_REGCODE)){ // 调用取得注册码的Action
			String devCode=req.getParameter(Const.JSP_P_DEVICE_CODE);
			if(null == devCode || 0 == devCode.trim().length()){
				msg.append(Const.ERRMSG_ILLEGAL_DEVCODE + nr);
			}
			else{
				Date date = new Date(); 
				long mil = date.getTime();
				System.out.println("注册时间：" + new SimpleDateFormat(Const.SYS_DATE_FORMAT).format(date));
				System.out.println(devCode + Const.FIELD_SEPARATOR +String.valueOf(mil));
				req.setAttribute(Const.JSP_P_DEVICE_CODE, devCode);
				req.setAttribute(Const.JSP_P_AUTHCODE_POSTFIX, String.valueOf(mil));
				req.getRequestDispatcher(Const.WEBURL_SEPARATOR + Const.JSP_FOLDER_NAME 
						+ Const.DEVICE_FOLDER_NAME + Const.JSP_DEVICE_REGISTER).forward(req, resp);
				return;
			}
		}
		
		if(null != actName && actName.equals(Const.ACT_DEVICE_REGISTER_SUBMIT)){ // 调用提交注册码的Action(目前都在DeviceRegister中处理)
			String devCode=req.getParameter(Const.JSP_P_DEVICE_CODE);	// 设备码
			String postfix=req.getParameter(Const.JSP_P_AUTHCODE_POSTFIX);	// 注册码后缀
			String authCode = devCode + Const.FIELD_SEPARATOR + postfix; // 注册码
			// acWLfp:注册码白名单的全路径名
			String acWLfp = getServletContext().getRealPath(String.valueOf(Const.WEBURL_SEPARATOR)) + Const.PROPERTIES_AUTHCODE_WHITE_LIST;
			ACWhiteListor acwl = new ACWhiteListor(acWLfp);
			System.out.println("authCode:" + authCode);
			if(acwl.isLegal(authCode)){ // 注册成功
				System.out.println("注册成功");
				Cookier.addCookie(resp, Const.CK_N_DEV_AUTHCODE, authCode, Const.CK_V_DEVAC_MAXAGE);
				req.getSession().setAttribute(Const.SS_N_DEVICE_AUTH, authCode);
				req.setAttribute(Const.JSP_P_MESSAGE, Const.MSG_SUCESS_DEVREG);
				String fwUrl = req.getContextPath() + Const.WEBURL_SEPARATOR ;
				req.setAttribute(Const.JSP_P_FORWARD_URL, fwUrl);
				req.setAttribute(Const.JSP_P_URL_MESSAGE, Const.JSP_PV_URLMSG_GOINDEX);
				req.getRequestDispatcher(Const.WEBURL_SEPARATOR + Const.JSP_FOLDER_NAME 
						+ Const.JSP_MESSAGE).forward(req, resp);
			}
			else{ // 注册失败
				msg.append(Const.ERRMSG_FAIL_ACCODE + nr);
			}
			//	出现错误
			if(null != msg && 0 != msg.toString().trim().length()){
				req.setAttribute(Const.JSP_P_MESSAGE, msg.toString().trim());
				String fwUrl = req.getContextPath() + Const.WEBURL_SEPARATOR + Const.JSP_FOLDER_NAME 
						+ Const.DEVICE_FOLDER_NAME + Const.JSP_DEVICE_REGCODE;
				req.setAttribute(Const.JSP_P_FORWARD_URL, fwUrl);
				req.setAttribute(Const.JSP_P_URL_MESSAGE, "返回注册");
				req.getRequestDispatcher(Const.WEBURL_SEPARATOR + Const.JSP_FOLDER_NAME 
						+ Const.JSP_MESSAGE).forward(req, resp);
			}
		}
		req.setAttribute(Const.JSP_P_MESSAGE, Const.ERRMSG_UNDEFINED_ACTION);
	}

	
}
