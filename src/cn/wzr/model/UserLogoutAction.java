package cn.wzr.model;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.wzr.global.Const;

/**
 * Servlet implementation class UserLogoutAction
 */
@WebServlet(description = "用户退出登录", urlPatterns = { "/UserLogoutAction" })
public class UserLogoutAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(!request.getSession().isNew()){
			request.getSession().invalidate();
//			System.out.println("request.getSession().isNew():" + request.getSession().isNew());
			response.sendRedirect(request.getContextPath() + Const.WEBURL_SEPARATOR);
//			response.sendRedirect(request.getContextPath() + Const.WEBURL_SEPARATOR + Const.JSP_USERLOGIN);
		}
	}

}
