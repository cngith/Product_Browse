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
import cn.wzr.dao.impl.APage;
import cn.wzr.entity.Commodity;
import cn.wzr.global.Const;
import cn.wzr.util.DbConfig;
import cn.wzr.util.PageConfig;

/**
 * Servlet implementation class CommodityBrowseAction
 */
@WebServlet("/CommodityBrowseAction")
public class CommodityBrowseAction extends HttpServlet implements IModel{
	private static final long serialVersionUID = 1L;
       

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub
		// 获取数据库连接配置
		String dbPropertiesPath = req.getServletContext().getRealPath(File.separator)
				+ Const.PROPERTIES_DBCONFIG;
		DbConfig dbConfig = new DbConfig(dbPropertiesPath);
		String url = dbConfig.getUrl();
		String dbuser = dbConfig.getUserName();
		String dbpassword = dbConfig.getPassword();
		String dbDriver = dbConfig.getDriver();

		// 取当前页码
		String pString = req.getParameter(Const.JSP_P_PAGE_NUM);
//		
		int pageNum=(pString == null ? 1 : Integer.parseInt(pString));
System.out.println("当前页码:" + pageNum);		
		
		String pagePropertiesPath = req.getServletContext().getRealPath(String.valueOf(Const.WEBURL_SEPARATOR))
				+ Const.PAGECONFIG_PROPERTIES;
		PageConfig pageConfig = new PageConfig(pagePropertiesPath);
		// 取每页显示记录数
		int linesPerPage = Integer.parseInt(pageConfig.getLinesPerPage());
System.out.println("每页显示记录数:" + linesPerPage);		
		// 计算从第几条记录开始显示
		int beginRecord = linesPerPage * (pageNum - 1);
System.out.println("开始显示:" + beginRecord);
		// 取共显示多少页
		int showPageCount = Integer.parseInt(pageConfig.getShowPageCount());
		
		// 获得数据库操作的DAO
		CommodityDao commodityDao = new CommodityDao(url, dbDriver, dbuser, dbpassword);
		List<Commodity> comList = null;
		int totalRecord = 0;
		
		String styleNo=(String)req.getParameter(Const.JSP_P_CMDT_STYLENO);
		styleNo = (styleNo==null) ? "" :styleNo;
		String cmdtName=(String)req.getParameter(Const.JSP_P_CMDT_CMDTNAME);
		cmdtName = (cmdtName==null) ? "" : cmdtName;
		String showNo=(String)req.getParameter(Const.JSP_P_CMDT_SHOWNO);
		showNo= (showNo==null) ? "" :showNo;
		String cmdtSN=(String)req.getParameter(Const.JSP_P_CMDT_CMDTSN);
		cmdtSN = (cmdtSN==null) ? "" : cmdtSN;
		
		String sqlRecord = "";
		Object[] parameters = null;
		if(Const.JSP_CMDT_BROWSE_LIST.equals(req.getParameter(Const.JSP_P_RETURN_JSP))){ // 列表浏览
			// 取记录语句  
			sqlRecord = Const.SQL_TB_COMMODITY_ALL + Const.SQL_SQL_WHERE + Const.SQL_FD_STYLENO + Const.SQL_SQL_LIKE + " AND " 
					+ Const.SQL_FD_COMMODITY_NAME + Const.SQL_SQL_LIKE + " AND "
					+ Const.SQL_FD_SHOWNO + Const.SQL_SQL_LIKE + " AND " + Const.SQL_FD_CMDTSN + Const.SQL_SQL_LIKE 
					+ Const.SQL_SQL_LIMIT; 
	
			// 填充参数  
		   parameters = new Object[] { "%" + styleNo + "%", "%" + cmdtName + "%", "%" + showNo + "%", "%" + cmdtSN + "%", beginRecord, linesPerPage }; 
		}
	   else if (Const.JSP_COMMODITY_BROWSE_BIGIMG.equals(req.getParameter(Const.JSP_P_RETURN_JSP))) { // 大图浏览
			// 取记录语句  
			sqlRecord = Const.SQL_TB_COMMODITY_ALL + Const.SQL_SQL_WHERE + Const.SQL_FD_STYLENO + Const.SQL_SQL_LIKE + " AND " 
					+ Const.SQL_FD_COMMODITY_NAME + Const.SQL_SQL_LIKE + " AND "
					+ Const.SQL_FD_SHOWNO + Const.SQL_SQL_LIKE + " AND " + Const.SQL_FD_CMDTSN + Const.SQL_SQL_LIKE ;
			
			// 填充参数  
			parameters = new Object[] { "%" + styleNo + "%", "%" + cmdtName + "%", "%" + showNo + "%", "%" + cmdtSN + "%" };
	   }
//System.out.println("--sqlRecord：" + sqlRecord);
		comList = commodityDao.findBySQL(sqlRecord, parameters);
		// 取记录数语句
		String sqlCount = Const.SQL_TBC_COMMODITY + Const.SQL_SQL_WHERE + Const.SQL_FD_STYLENO + Const.SQL_SQL_LIKE + " AND " 
				+ Const.SQL_FD_COMMODITY_NAME + Const.SQL_SQL_LIKE + " AND "
				+ Const.SQL_FD_SHOWNO + Const.SQL_SQL_LIKE + " AND " + Const.SQL_FD_CMDTSN + Const.SQL_SQL_LIKE; 
//		
		totalRecord = commodityDao.getCountBySQL(sqlCount, new Object[]{"%" + styleNo + "%", "%" + cmdtName + "%"
				, "%" + showNo + "%", "%" + cmdtSN + "%"});
		System.out.println("-styleNo:" + styleNo);
		req.setAttribute(Const.JSP_P_CMDT_STYLENO, styleNo);

		System.out.println("-cmdtName:" + cmdtName);
		req.setAttribute(Const.JSP_P_CMDT_CMDTNAME, cmdtName);

		System.out.println("-showNo:" + showNo);
		req.setAttribute(Const.JSP_P_CMDT_SHOWNO, showNo);
		
		System.out.println("-cmdtSN:" + cmdtSN);
		req.setAttribute(Const.JSP_P_CMDT_CMDTSN, cmdtSN);

System.out.println("totalRecord:" + totalRecord);
		APage<Commodity> page = new APage<Commodity>(totalRecord,linesPerPage);
		page.setPageNum(pageNum);
		page.setShowPageCount(showPageCount);

		page.setList(comList);
		page.setActionName(this.getClass().getSimpleName());
		req.setAttribute(Const.JSP_P_PAGE, page);
		// 设置编辑参数
		req.setAttribute(Const.JSP_P_EDITABLE, String.valueOf(req.getParameter(Const.JSP_P_EDITABLE)));
		req.setAttribute(Const.JSP_P_DELETABLE, String.valueOf(req.getParameter(Const.JSP_P_DELETABLE)));
//System.out.println("DELETABLE:" + String.valueOf(req.getParameter(Const.JSP_P_DELETABLE)));
		if(null==comList && 0 < page.getTotalRecord()){
			req.setAttribute(Const.JSP_P_MESSAGE, Const.ERRMSG_ACTRE_ERRDATA + "商品数量与生成数据对象不符。");
			return Const.WEBURL_SEPARATOR + Const.JSP_FOLDER_NAME + Const.JSP_MESSAGE;
		}else{
			return Const.WEBURL_SEPARATOR + Const.JSP_FOLDER_NAME + Const.CMDT_FOLDER_NAME + req.getParameter(Const.JSP_P_RETURN_JSP);
		
		}

	}
}
