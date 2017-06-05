package cn.wzr.model;


import java.io.File;
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

@WebServlet(description = "编辑商品信息", urlPatterns = { "/" + Const.ACT_COMMODITY_DELETE })
public class CommodityDeleteAction extends HttpServlet implements IModel{

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * @return 成功返回Url
	 */
	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) {
		String url = null;
			
		url = Const.WEBURL_SEPARATOR + Const.JSP_FOLDER_NAME + Const.JSP_MESSAGE ;
		String cmdtId=req.getParameter(Const.JSP_P_CMDT_ID);
		
		// 获取web项目的全路径
		String contextPath = req.getServletContext().getRealPath(String.valueOf(Const.WEBURL_SEPARATOR));
		// 获取数据库连接配置
		String dbPropertiesPath = contextPath + Const.PROPERTIES_DBCONFIG;
		DbConfig dbConfig = new DbConfig(dbPropertiesPath);
		String dburl = dbConfig.getUrl();
		String dbuser = dbConfig.getUserName();
		String dbpassword = dbConfig.getPassword();
		String dbDriver = dbConfig.getDriver();
		CommodityDao commodityDao = new CommodityDao(dburl, dbDriver, dbuser, dbpassword);
		// 删除商品对应图片
		Commodity cmdt = commodityDao.getById(Integer.valueOf(cmdtId));
		String imgsPath = cmdt.getImagePath();
		// 得到文件的保存目录(相对路径) upload/image/2015/05/03
		String[] imgAry = imgsPath.split(String.valueOf(Const.IMAGE_SEPARATOR));
		System.out.println("url:" + url);
		if(commodityDao.delete(Integer.valueOf(cmdtId))){
			// 得到文件的保存目录，并删除文件
			for(String img:imgAry){
				String realImgUploadPath = contextPath + img;
				deleteFile(realImgUploadPath);
			}
			req.setAttribute(Const.JSP_P_FORWARD_URL, "#");
			req.setAttribute(Const.JSP_P_URL_MESSAGE, "");
			req.setAttribute(Const.JSP_P_MESSAGE, Const.MSG_SUCESS_DELETE);
		}
		else{
			req.setAttribute(Const.JSP_P_FORWARD_URL, "#");
			req.setAttribute(Const.JSP_P_URL_MESSAGE, "");
			req.setAttribute(Const.JSP_P_MESSAGE, Const.MSG_FAIL_DELETE);
		}
				
		
		return url;
	}
	/** 
	 * 删除单个文件 
	 * @param   sPath    被删除文件的文件名 
	 * @return 单个文件删除成功返回true，否则返回false 
	 */  
	public boolean deleteFile(String sPath) {  
	    boolean flag = false;  
	    File file = new File(sPath);  
	    // 路径为文件且不为空则进行删除  
	    if (file.isFile() && file.exists()) {  
	        file.delete();  
	        flag = true;  
	    }  
	    return flag;  
	}
}
