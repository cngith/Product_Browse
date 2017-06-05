package cn.wzr.controller;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.wzr.dao.iface.IModel;
import cn.wzr.global.Const;
import cn.wzr.model.CommodityAddAction;
import cn.wzr.model.CommodityBrowseAction;
import cn.wzr.model.CommodityDeleteAction;
import cn.wzr.model.CommodityUpdateAction;
import cn.wzr.model.CustomerCheckAction;
import cn.wzr.model.UserCheckAction;

/*
 * @WebServlet(name="ActionServlet",urlPatterns={"/ActionServlet"})
 */
@WebServlet("/ActionServlet")
public class ActionServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		boolean userEmpty=true;
		
		if(req.getSession().getAttribute(Const.JSP_P_USERNAME)==null && 
				(null == req.getParameter(Const.JSP_P_USERNAME) || req.getParameter(Const.JSP_P_USERNAME).isEmpty())){
			
		}
		else {
			userEmpty = false;
		}
		
		boolean cusEmpty=true;
		
		if(req.getSession().getAttribute(Const.JSP_P_CUSNAME)==null && 
				(null == req.getParameter(Const.JSP_P_CUSNAME) || req.getParameter(Const.JSP_P_CUSNAME).isEmpty())){
			
		}
		else {
			cusEmpty = false;
		}
		System.out.println("CUSNAME:" + (String)req.getSession().getAttribute(Const.JSP_P_CUSNAME));
		
		
		if(userEmpty && cusEmpty){
			// 没有登录直接通过地址访问
			req.setAttribute(Const.JSP_P_MESSAGE, Const.ERRMSG_UNLOGIN);
			String fwUrl = req.getContextPath() + Const.WEBURL_SEPARATOR ;
			req.setAttribute(Const.JSP_P_FORWARD_URL, fwUrl);
			req.setAttribute(Const.JSP_P_URL_MESSAGE, Const.JSP_PV_URLMSG_GOINDEX);
			req.getRequestDispatcher(Const.WEBURL_SEPARATOR + Const.JSP_FOLDER_NAME 
					+ Const.JSP_MESSAGE).forward(req, resp);
			return;
		}else { // 已登录
		
		}
		// 获取请求提交的参数，该参数用来标识执行的请求处理  
        String cmd = req.getParameter(Const.JSP_P_ACTIONNAME);  
        // 根据参数获取模型的实例 
//System.out.println("cmd:" + cmd);
		String url = null;
		if(null != cmd){
	        IModel model = (IModel) getServletContext().getAttribute(cmd); 
	        // 通过模型对象进行业务逻辑处理，返回视图路径  
	        url = model.execute(req, resp);  
	        // 根据视图路径进行页面转发  
		}

       if (cmd != null && url != null && 0 < url.length()) {  
            req.getRequestDispatcher(url).forward(req, resp);  
        } 
       else {  
            // 如果路径出错，跳转到错误页面
    	   req.setAttribute(Const.JSP_P_MESSAGE, Const.ERRMSG_ACTRE_ERRURL);
    	   req.setAttribute(Const.JSP_P_FORWARD_URL, Const.JSP_PV_FORWARD_URL_BACK);
    	   req.setAttribute(Const.JSP_P_URL_MESSAGE, Const.JSP_PV_URLMSG_BACK);
    	   req.getRequestDispatcher(Const.WEBURL_SEPARATOR + Const.JSP_FOLDER_NAME + Const.JSP_MESSAGE).forward(req, resp);  
        } 

	}
	@Override
	public void init() throws ServletException {
		// 获取application  
        ServletContext application = getServletContext();  
        // 将业务模型的实例写入application  
        application.setAttribute(Const.ACT_CUS_CHECK, new CustomerCheckAction());  
        application.setAttribute(Const.ACT_USER_CHECK, new UserCheckAction());  
//        application.setAttribute("userListAction", new UserCreate());  
        application.setAttribute(Const.ACT_COMMODITY_BROWSE, new CommodityBrowseAction());  
        application.setAttribute(Const.ACT_COMMODITY_UPDATE, new CommodityUpdateAction());  
        application.setAttribute(Const.ACT_COMMODITY_DELETE, new CommodityDeleteAction());  
//        application.setAttribute("userDeleteAction", new UserDeleteAction());  
//        application.setAttribute("userUpdateAction", new UserUpdateAction());
      application.setAttribute("commodityAddAction", new CommodityAddAction()); 
	}
	
}
