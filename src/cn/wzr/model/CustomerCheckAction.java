package cn.wzr.model;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.wzr.dao.iface.IModel;
import cn.wzr.global.Const;

/**
 * 实现统一规定的模型
 * 
 * @author Steven
 * 
 */
public class CustomerCheckAction implements IModel {


	/**
	 * 客商暂不做判断，以后实现
	 */
	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) {
		// 获取前台表单提交后的用户名
		String cusname = req.getParameter(Const.JSP_P_CUSNAME);
		System.out.println("cusname" + cusname);
		req.getSession().setAttribute(Const.JSP_P_CUSNAME, cusname);
		return Const.WEBURL_SEPARATOR + Const.JSP_FOLDER_NAME + Const.CUS_FOLDER_NAME + Const.JSP_CUS_FRAME;
		/*
		// 获取前台表单提交后的用户名
		String cusname = req.getParameter(Const.JSP_P_USERNAME);
		// 密码
		String password = req.getParameter(Const.JSP_P_PASSWORD);
		String dbPropertiesPath = req.getServletContext().getRealPath(File.separator)
				+ Const.PROPERTIES_DBCONFIG;
		DbConfig dbConfig = new DbConfig(dbPropertiesPath);
		String dburl = dbConfig.getUrl();
		String dbuser = dbConfig.getUserName();
		String dbpassword = dbConfig.getPassword();
		String dbDriver = dbConfig.getDriver();
		// 获得数据库操作的DAO
		
		UserDao userDao = new UserDao(dburl, dbDriver, dbuser, dbpassword);
		UserCheckResult ucr = userDao.userCheck(cusname, password);
		if (UserCheckResult.LEGAL == ucr) { // 合法用户
		
		if (true) { // 合法用户 
			req.getSession().setAttribute(Const.JSP_P_CUSNAME, cusname);
			return Const.WEBURL_SEPARATOR + Const.JSP_FOLDER_NAME + Const.CUS_FOLDER_NAME + Const.JSP_CUS_FRAME;
		}
		
		if (UserCheckResult.USERNAME_ERROR == ucr) { // 用户名错误
			req.setAttribute("message", ucr.toString());
		}
		if (UserCheckResult.PASSWORD_ERROR == ucr) { // 密码错误
			req.setAttribute("message", ucr.toString());
		}
		return Const.JSP_USERLOGIN;
		*/
	}

}
