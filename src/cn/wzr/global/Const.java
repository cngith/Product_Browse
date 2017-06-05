package cn.wzr.global;

import java.io.File;

/*
 * 保存常量的类
 */
public final class Const {
	

	/**
	 * 用来分隔网址的字符
	 */
	public static final char WEBURL_SEPARATOR = '/';
	
	/**
	 * 本系统常量：时间格式
	 */
	public final static String SYS_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss"; 
	
	
	/**
	 * folder save .jsp files
	 */
	public final static String JSP_FOLDER_NAME = "jsp" + WEBURL_SEPARATOR; 
	
	/**
	 * folder save user's .jsp files
	 */
	public final static String USER_FOLDER_NAME = "user" + WEBURL_SEPARATOR; 
	
	/**
	 * folder save user's .jsp files
	 */
	public final static String CUS_FOLDER_NAME = "customer" + WEBURL_SEPARATOR; 
	
	/**
	 * folder save commodity's .jsp files
	 */
	public final static String CMDT_FOLDER_NAME = "commodity" + WEBURL_SEPARATOR; 
	
	/**
	 * folder save user's .jsp files
	 */
	public final static String DEVICE_FOLDER_NAME = "device" + WEBURL_SEPARATOR; 
	
	public final static String WEBINF="WEB-INF";
	
	public final static String ENCODING="UTF-8";

	
	/**
	 * 配置文件的文件夹名(conf/)
	 */
	public final static String CONFIG_FOLDER = "conf" + WEBURL_SEPARATOR;
	
	/*********************
	 * 过滤器FLT
	 *********************/
	
	/**
	 * 初始化参数WebInitParam(WIP)名:例外页面
	 */
	public final static String FLT_WIP_EXCLUDED_PAGES = "excludedPages";
	
	
	/*********************
	 * Session(SS)
	 *********************/
	
	/**
	 * Session参数名:设备授权
	 */
	public final static String SS_N_DEVICE_AUTH = "DeviceAuthorization";
	
	
	/*********************
	 * jsp页面名称
	 *********************/
	
	public final static String JSP_DEVICE_REGISTER="device_register.jsp";
	
	/**
	 * jsp页面名称，设备注册，输入注册码
	 */
	public final static String JSP_DEVICE_REGCODE="device_register_code.jsp";

	public final static String JSP_INDEX="index.jsp";
	
	public final static String JSP_USERLOGIN="userlogin.jsp";
	
	public final static String JSP_CUSLOGIN="cuslogin.jsp";

	public final static String JSP_USER_FRAME="user_frame.jsp";

	public final static String JSP_CUS_FRAME="cus_frame.jsp";

	public final static String JSP_MESSAGE="message.jsp";
	
	public final static String JSP_CMDT_BROWSE_LIST = "commodity_browse_list.jsp";
	
	public final static String JSP_CMDT_UPDATE="commodity_update.jsp";

	public final static String JSP_IMGSHOW="commodity_image_show.jsp";
	

	/**
	 * 大图浏览
	 */
	public final static String JSP_COMMODITY_BROWSE_BIGIMG="commodity_browse_bigimg.jsp";
	
	
	/**
	 * Cookie名：设备授权码
	 */
	public final static String CK_N_DEV_AUTHCODE = "DeviceAuthCode";
	
	/**
	 * Cookie值：设备授权码Cookie寿命(秒)
	 */
	public final static int CK_V_DEVAC_MAXAGE = 60*60*24*40;
	
	
	/**
	 * jsp页面上的参数名：标识在Url上显示的信息
	 */
	public final static String JSP_P_URL_MESSAGE = "urlMessage";
	
	/**
	 * jsp页面上的参数名：表示Url地址
	 */
	public final static String JSP_P_FORWARD_URL = "forwarUrl";
	
	/**
	 * jsp页面上的参数名：图片链接
	 */
	public final static String JSP_P_IMGURL="imgUrl";
	
	/**
	 * jsp页面上的参数名：标识将调用的action名字
	 */
	public static final String JSP_P_ACTIONNAME = "actionName";
	

	/**
	 * jsp页面上的参数名：授权码后缀 
	 */
	public final static String JSP_P_AUTHCODE_POSTFIX="authCodePostfix";
	
	/**
	 * jsp页面上的参数名：返回Jsp页面 
	 */
	public final static String JSP_P_RETURN_JSP="returnJsp";
	
	/**
	 * jsp页面上的参数名：标识返回的message
	 */
	public static final String JSP_P_MESSAGE = "message";
	
	/**
	 * jsp页面上的参数名：用户名
	 */
	public final static String JSP_P_USERNAME="userName";
	
	/**
	 * jsp页面上的参数名：用户名
	 */
	public final static String JSP_P_CUSNAME="cusName";

	/**
	 * jsp页面上的参数名：标识密码
	 */
	public final static String JSP_P_PASSWORD="password";
	
	/**
	 * jsp页面上的参数名：标识分页对象
	 */
	public final static String JSP_P_PAGE="page";
	
	/**
	 * jsp页面上的参数名：当前页码 
	 */
	public final static String JSP_P_PAGE_NUM="pageNum";
	
	
	/**
	 * jsp页面上的参数名：每页显示行数 
	 */
	public final static String JSP_P_ROWS_PERPAGE="rowsPerpage";
	
	
	/**
	 * jsp页面上的参数名：设备注册码 
	 */
	public final static String JSP_P_DEVICE_CODE="deviceCode";
	
	/**
	 * jsp页面上的参数名：对commodity表进行查询，取得数据的方式 
	 */
	public final static String JSP_P_CMDT_SEARCH_KIND="commoditySearchKind";

	/**
	 * jsp页面上的参数名：标识commodity记录对象
	 */
	public final static String JSP_P_CMDT_OBJ="cmdtObj";
	
	/**
	 * jsp页面上的参数名：commodity表Id字段
	 */
	public final static String JSP_P_CMDT_ID="Id";
	
	/**
	 * jsp页面上的参数名：commodity表StyleNo字段
	 */
	public final static String JSP_P_CMDT_STYLENO="styleNo";
	
	/**
	 * jsp页面上的参数名：commodity表Name字段
	 */
	public final static String JSP_P_CMDT_CMDTNAME="cmdtName";
	
	/**
	 * jsp页面上的参数名：commodity表ShowNo字段
	 */
	public final static String JSP_P_CMDT_SHOWNO="showNo";
	
	/**
	 * jsp页面上的参数名：commodity表CmdtSN字段
	 */
	public final static String JSP_P_CMDT_CMDTSN="cmdtSN";
	
	/**
	 * jsp页面上的参数名：commodity表ImagePath字段
	 */
	public final static String JSP_P_CMDT_IMGPATH="cmdtImgPath";
	
	/**
	 * jsp页面上的参数名：标识记录能否编辑
	 */
	public final static String JSP_P_EDITABLE="editable";
	
	/**
	 * jsp页面上的参数名：标识记录编辑状态（初始化，准备完成）
	 */
	public final static String JSP_P_EDIT_STATUS="editStatus";
	
	
	/**
	 * jsp页面上的参数名：标识记录能否删除
	 */
	public final static String JSP_P_DELETABLE="deletable";
	
	
	/*********************
	 * jsp页面上的参数值
	 ********************/

	/**
	 * jsp页面上的参数值：在Url上显示的信息
	 */
	public final static String JSP_PV_URLMSG_GOINDEX = "返回首页";

	/**
	 * jsp页面上的参数值：JSP页面上回退链接值
	 */
	public final static String JSP_PV_FORWARD_URL_BACK = "javascript:history.go(-1)";
	
	/**
	 * jsp页面上的参数值：对commodity表进行查询，取得所有数据的方式进行查询 
	 */
	public final static String JSP_PV_CMDT_SK_ALL="SKAll";
	
	/**
	 * jsp页面上的参数值：标识通过StyleNo字段，对commodity表进行查询
	 */
	public final static String JSP_PV_CMDT_SK_BYSTYLENO="SKStyleNo";
	
	/**
	 * jsp页面上的参数值：标识未找到商品图片时，显示什么图片
	 */
	public final static String JSP_PV_CMDT_BIGIMG_INF="img_not_found.jpg";
	

	/**
	 * jsp页面上的参数值：list模式浏览时图片宽度
	 */
	public final static String JSP_PV_CMDT_LISTIMG_WIDTH="200";
	
	/**
	 * jsp页面上的参数值：JSP页面上回退链接显示信息
	 */
	public final static String JSP_PV_URLMSG_BACK = "返回上一步";

	/**
	 * jsp页面上的参数值：初始化编辑信息
	 */
	public final static String JSP_PV_EDITSTA_INIT="editInit";
	
	/**
	 * jsp页面上的参数值：初始化编辑信息
	 */
	public final static String JSP_PV_EDITSTA_READY="editReady";
	
	/*****************
	 * Action名字
	 ****************/
	
	/**
	 * Action名字
	 */
	public final static String ACT_ACT_SERVLET = "ActionServlet";
	
	/**
	 * Action名字
	 */
	public final static String ACT_USER_CHECK = "UserCheckAction";
	
	/**
	 * Action名字
	 */
	public final static String ACT_CUS_CHECK = "CustomerCheckAction";
	
	/**
	 * Action名字
	 */
	public final static String ACT_DEVICE_REGISTER = "DeviceRegister";
	
	/**
	 * Action名字
	 */
	public final static String ACT_DEVICE_REGISTER_SUBMIT="DeviceRegisterSubmit";

	/**
	 * Action名字
	 */
	public final static String ACT_COMMODITY_BROWSE="CommodityBrowseAction";
	
	/**
	 * Action名字
	 */
	public final static String ACT_COMMODITY_UPDATE="CommodityUpdateAction";
	
	/**
	 * Action名字
	 */
	public final static String ACT_COMMODITY_DELETE="CommodityDeleteAction";
	
	/**
	 * Action名字
	 */
	public final static String ACT_GET_DEVICE_REGCODE="GetDeviceRegCodeAction";
	
	/**
	 * Action名字，标识用户退出
	 */
	public final static String ACT_USER_LOGOUT="UserLogoutAction";
	
	/**
	 * Action名字，标识客商退出
	 */
	public final static String ACT_CUS_LOGOUT="CusLogoutAction";

	
	
	
	/**********************
	 * HTML标签ID缩写：HI
	***********************/
	
	/**
	 * jsp页面上标签ID：商品列表 
	 */
	public final static String JSP_HI_CMDTLIST="cmdtList";
	
	/**
	 * jsp页面上标签ID：当前商品图片数 
	 */
	public final static String JSP_HI_CMDT_CURIMGCOUNT="curImgCount";
	
	/**
	 * jsp页面上标签ID：查到的所有商品数 
	 */
	public final static String JSP_HI_CMDT_TOTAL_RECORD="totalRecord";
	
	/**
	 * jsp页面上标签"rowNum"：查到的商品行号 
	 */
	public final static String JSP_HI_CMDT_ROWNUM="rowNum";
	
	/**
	 * jsp页面上标签"rowNum"：查到的商品行号 
	 */
	public final static String JSP_HI_CMDT_IMGNUM="imgNum";
	
	
	/**
	 * 用来分隔图片名称的字符
	 */
	public static final char IMAGE_SEPARATOR = ';';
	
	/**
	 * 用来分隔字段的字符
	 */
	public static final char FIELD_SEPARATOR = '~';
	
	/**
	 * 用来分隔记录的字符
	 */
	public static final char RECORD_SEPARATOR = '`';

	
	/**
	 * 配置文件的文件名（保存分页各选项）。
	 */
	public final static String PAGECONFIG_PROPERTIES = CONFIG_FOLDER + "page.properties";
	
	/**
	 * 配置文件中的键名（每页显示几条记录）
	 */
	public final static String PAGECONFIG_LINES_PERPAGE="linesPerPage";
	
	/**
	 * 配置文件中的键名（每行显示几张图片）
	 */
	public final static String PAGECONFIG_IMG_PERLINE="imgPerLine";
	
	/**
	 * 配置文件中的键名（页码栏共显示多少页码）
	 */
	public final static String PAGECONFIG_SHOW_PAGECOUNT="showPageCount";

	
	
	/**
	 * DBCONFIG_PROPERTIES:配置文件的文件名（保存数据库连接各选项）。
	 */
	public final static String PROPERTIES_DBCONFIG= CONFIG_FOLDER + "dbConfig.properties";
	
	/**
	 * 配置文件中的键名（driver）
	 */
	public final static String DBCONFIG_DRIVER="driver";
	
	/**
	 * 配置文件中的键名（url）
	 */
	public final static String DBCONFIG_URL="url";
	
	/**
	 * 配置文件中的键名（username）
	 */
	public final static String DBCONFIG_USERNAME="username";
	
	/**
	 * 配置文件中的键名（passwrod）
	 */
	public final static String DBCONFIG_PASSWROD="passwrod";
	
	/**
	 * 配置文件的文件名（保存上传文件各选项）。
	 */
	public final static String PROPERTIES_UPLOAD = CONFIG_FOLDER + "upload.properties";
	
	/**
	 * 配置文件的文件名（保存注册码白名单）。
	 * Authrization Code
	 */
	public final static String PROPERTIES_AUTHCODE_WHITE_LIST = CONFIG_FOLDER + "ACWhiteList.txt";

	
	
	/**
	 * UPLOAD_TEMP_FOLDER:配置文件中的键名（临时文件夹）
	 */
	public final static String UPLOAD_TEMP_FOLDER="uploadTempFolder";
	
	/**
	 * UPLOAD_FOLDER：配置文件中的键名（上传文件夹）
	 */
	public final static String UPLOAD_FOLDER="uploadFolder";
	
	/**
	 * UPLOAD_IMAGE_FOLDER：配置文件中的键名（上传图片文件夹）
	 */
	public final static String UPLOAD_IMAGE_FOLDER="uploadImageFolder";
	
	/**
	 * 配置文件中的键名（缓冲区的大小(KB)）
	 */
	public final static String UPLOAD_THRESHOLD="threshold";
	
	/**
	 * 配置文件中的键名（上传单个文件大小的最大值(KB)）
	 */
	public final static String UPLOAD_FILESIZEMAX="fileSizeMax";
	/**
	 * 配置文件中的键名（上传的所有文件大小的最大值(KB)）
	 */
	public final static String UPLOAD_ALLFILESIZEMAX="allFileSizeMax";
	
	
	/**
	 * 配置文件中的键名（每页显示记录数）
	 */
	public final static String PAGE_LINES_PERPAGE="linesPerPage";
	/**
	 * 配置文件中的键名（每行显示图片数）
	 */
	public final static String PAGE_IMG_PERLINE="imgPerLine";
	
	
	/**
	 * 用户表名
	 */
	public final static String SQL_TBN_USER="tb_user";
	
	/**
	 * 商品表名
	 */
	public final static String SQL_TBN_COMMODITY = "tb_commodity";
	
	
	
	/**
	 * 字段，款号
	 */
	public final static String SQL_FD_USERNAME="UserName";
	
	/**
	 * 字段，部门Id
	 */
	public final static String SQL_FD_DEPID="DepId";
	
	
	/**
	 * 字段，商品Id
	 */
	public final static String SQL_FD_COMMODITYID="Id";
	
	/**
	 * 字段，商品展号
	 */
	public final static String SQL_FD_SHOWNO="ShowNo";
	
	/**
	 * 字段，商品编号
	 */
	public final static String SQL_FD_CMDTSN="CmdtSN";
	
	/**
	 * 字段，商品品名
	 */
	public final static String SQL_FD_COMMODITY_NAME="Name";
	
	public final static String SQL_FD_STYLENO = "StyleNo";

	/**
	 * 字段，图片路径
	 */
	public final static String SQL_FD_CMDT_IMAGEPATH="ImagePath";
	
	/**
	 * SQL查询时返回记录数的字段名
	 */
	public final static String SQL_FD_ROWCOUNT="Row_count";
	
	
	
	/**
	 * SQL语句,取记录行数
	 */
	public final static String SQL_GET_ROWCOUNT = "SELECT COUNT(*) AS " + SQL_FD_ROWCOUNT + " FROM ";
	
	
	/**
	 * 用户验证时所用SQL语句
	 */
	public final static String SQL_CHECK_USER = SQL_GET_ROWCOUNT + SQL_TBN_USER + " WHERE User_name=? AND Password=?";
	
	/**
	 * SQL语句，通过USERNAME查询USER表
	 */
	public final static String SQL_TB_USER_BY_USERNAME = "SELECT * FROM " + SQL_TBN_USER + " WHERE " + SQL_FD_USERNAME + " = ?";
	
	/**
	 * SQL语句，查询COMMODITY表
	 */
	public final static String SQL_TB_COMMODITY_ALL = "SELECT * FROM " + SQL_TBN_COMMODITY;
	
	/**
	 * SQL语句，查询commodity表,取指定范围结果集
	 */
	public final static String SQL_TB_COMMODITY_ALL_LIM = "SELECT * FROM " + SQL_TBN_COMMODITY + " LIMIT ?,?";
	
	/**
	 * SQL语句，通过Id查询commodity表，取（一条）记录
	 */
	public final static String SQL_TB_COMMODITY_BY_ID = SQL_TB_COMMODITY_ALL + " WHERE " + SQL_FD_COMMODITYID + " = ?";
	
	/**
	 * SQL语句的WHERE子句
	 */
	public final static String SQL_SQL_WHERE = " WHERE ";
	
	/**
	 * SQL语句的LIKE子句
	 */
	public final static String SQL_SQL_LIKE =  " LIKE ?";
	
	/**
	 * SQL语句的LIMIT子句
	 */
	public final static String SQL_SQL_LIMIT = " LIMIT ?,?";

	/**
	 * SQL语句，查询COMMODITY表，取记录数
	 */
	public final static String SQL_TBC_COMMODITY = Const.SQL_GET_ROWCOUNT + SQL_TBN_COMMODITY;
	
	
	/**********************
	 * SQL语句INSERT数据
	 **********************/

	/**
	 * SQL语句,取商品记录行数
	 */
	public final static String SQL_IN_COMMODITY = "INSERT INTO " + SQL_TBN_COMMODITY +"(" + SQL_FD_COMMODITYID + ","
			+ SQL_FD_STYLENO + "," + SQL_FD_COMMODITY_NAME + ","+ SQL_FD_CMDT_IMAGEPATH + ","
			+ SQL_FD_SHOWNO + "," + SQL_FD_CMDTSN + ")" + " VALUES(?,?,?,?,?,?)";
	
	/**
	 * SQL语句,更新商品记录
	 */
	public final static String SQL_UPD_COMMODITY = "UPDATE " + SQL_TBN_COMMODITY +" SET "
			+ SQL_FD_SHOWNO + "=?," + SQL_FD_CMDTSN + "=?," + SQL_FD_STYLENO + "=?,"  
			+ SQL_FD_COMMODITY_NAME + "=?," + SQL_FD_CMDT_IMAGEPATH + "=?"
			+ " WHERE " + SQL_FD_COMMODITYID + "=?";
	
	/**
	 * SQL语句,更新商品记录
	 */
	public final static String SQL_DEL_COMMODITY = "DELETE FROM " + SQL_TBN_COMMODITY + " WHERE " + SQL_FD_COMMODITYID + "=?";
	
	
	public enum UserCheckResult{
		
		LEGAL("合法用户"),
		USERNAME_ERROR("用户名错误"),
		PASSWORD_ERROR("密码错误");
		
		private String description;
		
		private UserCheckResult(String description) {
			this.description=description;
		}
		
		@Override
		public String toString(){
			return this.description;
		}
	}
	
	public enum CusCheckResult{
		
		LEGAL("合法客商"),
		CUSNAME_ERROR("客商名错误"),
		PASSWORD_ERROR("密码错误");
		
		private String description;
		
		private CusCheckResult(String description) {
			this.description=description;
		}
		
		@Override
		public String toString(){
			return this.description;
		}
	}
	
	public static final String MSG_FAIL_DELETE = "删除失败！";
	
	public static final String MSG_SUCESS_DELETE = "删除成功！";

	public static final String MSG_SUCESS_UPDATE = "更新成功！";
	
	public static final String MSG_SUCESS_DEVREG = "设备注册成功！";
	
	public static final String ERRMSG_FAIL_ACCODE = "注册码验证失败！";
	
	public static final String ERRMSG_ILLEGAL_DEVCODE = "非法的注册码！";
	
	public static final String ERRMSG_FILESIZE_OVERFLOW = "单个文件超出最大值！";
	
	public static final String ERRMSG_ALLFILESIZE_OVERFLOW = "文件总的大小超出限制的最大值！";
	
	public static final String ERRMSG_FILEUPLOAD_FAIL = "文件上传失败！";
	
	public static final String ERRMSG_UNLOGIN = "用户尚未登录！";
	
	public static final String ERRMSG_UNAUTHORIZED_DEVICE = "未授权设备！";
	
	public static final String ERRMSG_UNDEFINED_ACTION = "未定义的Action！";
	
	public static final String ERRMSG_ACTRE_ERRURL = "服务器开小差，他没有返回一个可访问的地址！";
	
	public static final String ERRMSG_ACTRE_ERRDATA = "服务器把自己搞晕了";
	
}
