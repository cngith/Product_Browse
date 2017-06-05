package cn.wzr.model;


import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.wzr.dao.iface.IModel;
import cn.wzr.dao.impl.CommodityDao;
import cn.wzr.entity.Commodity;
import cn.wzr.global.Const;
import cn.wzr.util.DbConfig;

@WebServlet(description = "编辑商品信息", urlPatterns = { "/" + Const.ACT_COMMODITY_UPDATE })
public class CommodityUpdateAction extends HttpServlet implements IModel{

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * @return 成功返回Url
	 */
	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) {
		String url = null;
		String upStatus = req.getParameter(Const.JSP_P_EDIT_STATUS);
		if(null != upStatus && upStatus.equals(Const.JSP_PV_EDITSTA_INIT)){ // 初始化信息
			url = Const.WEBURL_SEPARATOR + Const.JSP_FOLDER_NAME + Const.CMDT_FOLDER_NAME + Const.JSP_CMDT_UPDATE ;
			
			String cmdtId=req.getParameter(Const.JSP_P_CMDT_ID);
			
			// 获取数据库连接配置
			String dbPropertiesPath = req.getServletContext().getRealPath(String.valueOf(Const.WEBURL_SEPARATOR))
					+ Const.PROPERTIES_DBCONFIG;
			DbConfig dbConfig = new DbConfig(dbPropertiesPath);
			String dburl = dbConfig.getUrl();
			String dbuser = dbConfig.getUserName();
			String dbpassword = dbConfig.getPassword();
			String dbDriver = dbConfig.getDriver();
			CommodityDao commodityDao = new CommodityDao(dburl, dbDriver, dbuser, dbpassword);
			Commodity comobj = commodityDao.getById(Integer.valueOf(cmdtId));
			req.setAttribute(Const.JSP_P_CMDT_OBJ, comobj);			
		}
		if(null != upStatus && upStatus.equals(Const.JSP_PV_EDITSTA_READY)){ // 信息准备好
			url = Const.WEBURL_SEPARATOR + Const.JSP_FOLDER_NAME + Const.JSP_MESSAGE ;
//System.out.println("JSP_PV_EDITSTA_READY:" + Const.JSP_PV_EDITSTA_READY);		
			String cmdtId=req.getParameter(Const.JSP_P_CMDT_ID);
			
			// 获取数据库连接配置
			String dbPropertiesPath = req.getServletContext().getRealPath(String.valueOf(Const.WEBURL_SEPARATOR))
					+ Const.PROPERTIES_DBCONFIG;
			DbConfig dbConfig = new DbConfig(dbPropertiesPath);
			String dburl = dbConfig.getUrl();
			String dbuser = dbConfig.getUserName();
			String dbpassword = dbConfig.getPassword();
			String dbDriver = dbConfig.getDriver();
			CommodityDao commodityDao = new CommodityDao(dburl, dbDriver, dbuser, dbpassword);
			
			Commodity cmobj = new Commodity();
			cmobj.setId(Long.valueOf(cmdtId));
			cmobj.setShowNo(req.getParameter(Const.JSP_P_CMDT_SHOWNO));
			cmobj.setCmdtSN(req.getParameter(Const.JSP_P_CMDT_CMDTSN));
			cmobj.setStyleNo(req.getParameter(Const.JSP_P_CMDT_STYLENO));
			cmobj.setName(req.getParameter(Const.JSP_P_CMDT_CMDTNAME));
			cmobj.setImagePath(req.getParameter(Const.JSP_P_CMDT_IMGPATH));
//System.out.println("--data ready");			
			if(commodityDao.update(cmobj)){
				System.out.println("re:" + Const.MSG_SUCESS_UPDATE);
				req.setAttribute(Const.JSP_P_FORWARD_URL, "#");
				req.setAttribute(Const.JSP_P_URL_MESSAGE, "");
				req.setAttribute(Const.JSP_P_MESSAGE, Const.MSG_SUCESS_UPDATE);
			}
					
		}
		
		return url;
	}
}
