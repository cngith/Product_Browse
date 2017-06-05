package cn.wzr.model;

import java.io.File;
import java.text.ParseException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.wzr.dao.iface.IModel;
import cn.wzr.dao.impl.UserDao;
import cn.wzr.entity.User;
import cn.wzr.global.Const;
import cn.wzr.util.DbConfig;

public class UserAddAction implements IModel {

	// 获得数据库操作的DAO
	private UserDao userDao;

	// 获取日期操作类
	// private Calendar calendar = Calendar.getInstance();

	public UserAddAction() {

	}

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		// 获取前台表单提交后的用户名
		String username = request.getParameter("username");
		//
		String password = request.getParameter("password");
		//
		String depId = request.getParameter("depId");

		String dbPropertiesPath = request.getServletContext().getRealPath(File.separator)
				+ Const.PROPERTIES_DBCONFIG;
		DbConfig dbConfig = new DbConfig(dbPropertiesPath);
		String url = dbConfig.getUrl();
		String dbuser = dbConfig.getUserName();
		String dbpassword = dbConfig.getPassword();
		String dbDriver = dbConfig.getDriver();
		userDao = new UserDao(url, dbuser, dbpassword, dbDriver);

		// 创建进行验证的标志信息
		boolean checkFlag = true;
		if ("".equals(username) || username == null) {
			request.setAttribute("userNameError", "用户名为空");
			checkFlag = false;
		} else {
			request.setAttribute("userName", username);
		}
		if (password == null || "".equals(password)) {
			request.setAttribute("birthdayError", "密码为空");
		}
		else{
			// 进行后台校验
			request.setAttribute("password", password);
		}
		// 如果有没有填的选项，则后台校验不成功，进行跳转
		if (!checkFlag) {
			return "index.jsp";
		}

		// 要创建的用户
		User user = new User();

		// 将用户添加到数据库中
		userDao.add(user);
		// 设置到request属性范围中
		request.setAttribute("user", user);

		// 设置跳转后的页面
		return "WEB-INF/jsp/show.jsp";
	}

}
