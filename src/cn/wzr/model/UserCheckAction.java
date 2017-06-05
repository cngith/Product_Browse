package cn.wzr.model;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.wzr.dao.iface.IModel;
import cn.wzr.dao.impl.UserDao;
import cn.wzr.global.Const;
import cn.wzr.global.Const.UserCheckResult;
import cn.wzr.util.DbConfig;

/**
 * 实现统一规定的模型
 * 
 * @author Steven
 * 
 */
public class UserCheckAction implements IModel {

	// 获取日期操作类
	// private Calendar calendar = Calendar.getInstance();

	@Override
	public String execute(HttpServletRequest req,
			HttpServletResponse resp) {
		// 获取前台表单提交后的用户名
		
		String username = req.getParameter(Const.JSP_P_USERNAME);
		// 密码
		String password = req.getParameter(Const.JSP_P_PASSWORD);
		String dbPropertiesPath = req.getServletContext().getRealPath(File.separator) + File.separator
				+ Const.PROPERTIES_DBCONFIG;
		System.out.println("dbPropertiesPath:" + dbPropertiesPath);
		DbConfig dbConfig = new DbConfig(dbPropertiesPath);
		String url = dbConfig.getUrl();
		String dbuser = dbConfig.getUserName();
		String dbpassword = dbConfig.getPassword();
		String dbDriver = dbConfig.getDriver();
		// 获得数据库操作的DAO
		UserDao userDao = new UserDao(url, dbDriver, dbuser, dbpassword);
		UserCheckResult ucr = userDao.userCheck(username, password);
		if (UserCheckResult.LEGAL == ucr) { // 合法用户
			req.getSession().setAttribute(Const.JSP_P_USERNAME, username);
			return Const.WEBURL_SEPARATOR + Const.JSP_FOLDER_NAME + Const.USER_FOLDER_NAME + Const.JSP_USER_FRAME;
		}
		if (UserCheckResult.USERNAME_ERROR == ucr) { // 用户名错误
			req.setAttribute("message", ucr.toString());
		}
		if (UserCheckResult.PASSWORD_ERROR == ucr) { // 密码错误
			req.setAttribute("message", ucr.toString());
		}
		return Const.JSP_USERLOGIN;
	}

}
