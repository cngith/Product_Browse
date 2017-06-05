package cn.wzr.model;

import cn.wzr.dao.iface.IModel;
import cn.wzr.dao.impl.CommodityDao;
import cn.wzr.entity.Commodity;
import cn.wzr.global.Const;
import cn.wzr.util.ActionAssistant;
import cn.wzr.util.DbConfig;
import cn.wzr.util.PropertiesMan;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@WebServlet("/CommodityAddAction")
public class CommodityAddAction extends HttpServlet implements IModel{

	/**
	 *
	 */
	private static final long serialVersionUID = -5062946478986801334L;

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

		String resultString = execute(req, resp);
		PrintWriter out = resp.getWriter();
		out.print(resultString);
	}

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub
		// 获取前台表单提交后的

		Commodity commodity = getCommodityInfo(req, resp);
		System.out.println("styleNo:" + commodity.getStyleNo());
		System.out.println("getName:" + commodity.getName());
		System.out.println("getShowNo:" + commodity.getShowNo());
		System.out.println("CmdtSN:" + commodity.getCmdtSN());
		String dbPropertiesPath = req.getServletContext().getRealPath(File.separator) + File.separator
				+ Const.PROPERTIES_DBCONFIG;
		DbConfig dbConfig = new DbConfig(dbPropertiesPath);
		String url = dbConfig.getUrl();
		String dbuser = dbConfig.getUserName();
		String dbpassword = dbConfig.getPassword();
		String dbDriver = dbConfig.getDriver();
		// 获得数据库操作的DAO
		CommodityDao commodityDao = new CommodityDao(url, dbDriver, dbuser, dbpassword);
		boolean flag = commodityDao.add(commodity);

		if (flag) { // 添加成功
			return "添加成功";
		} else { // 添加失败
			return "添加失败";
		}

	}

	/**
	 * 上传（图片）文件，根据提交的信息生成商品对象(commodity)
	 * @param request
	 * @param response
	 * @return 返回生成的商品对象(commodity)
	 */
	private Commodity getCommodityInfo(HttpServletRequest request, HttpServletResponse response) {
		// 得到上传文件的保存目录，
		String contextPath = request.getSession().getServletContext().getRealPath(String.valueOf(Const.WEBURL_SEPARATOR));
		String imgUploadRootPath = getImageUploadRootPath(contextPath); // upload/image/

		// 上传时生成的临时文件保存目录
		String tempPath = makeTempFullPath(contextPath);
		File tmpFile = new File(tempPath);
		if (!tmpFile.exists()) {
			// 创建临时目录
			tmpFile.mkdir();
		}
		// 3、判断提交上来的数据是否是上传表单的数据
		if (!ServletFileUpload.isMultipartContent(request)) {
			// 按照传统方式获取数据
			return null;
		}
		Commodity commodity = new Commodity();
		// 消息提示
		String message = "";
		try {
			// 使用Apache文件上传组件处理文件上传步骤：
			// 1、创建一个DiskFileItemFactory工厂
			DiskFileItemFactory factory = new DiskFileItemFactory();

			String sizeThreshold = getThreshold(contextPath);
			// 设置工厂的缓冲区的大小，当上传的文件大小超过缓冲区的大小时，就会生成一个临时文件存放到指定的临时目录当中。
			// 设置缓冲区为指定大小KB，如果不指定，那么缓冲区的大小默认是10KB
			factory.setSizeThreshold(1024 * Integer.parseInt(sizeThreshold));

			// 设置上传时生成的临时文件的保存目录
			factory.setRepository(tmpFile);

			// //2、创建一个文件上传解析器
			ServletFileUpload upload = new ServletFileUpload(factory);

			// 监听文件上传进度
			// upload.setProgressListener(new ProgressListener(){
			// public void update(long pBytesRead, long pContentLength, int
			// arg2) {
			// System.out.println("文件大小为：" + pContentLength + ",当前已处理：" +
			// pBytesRead);
			// /**
			// * 文件大小为：14608,当前已处理：4096
			// 文件大小为：14608,当前已处理：7367
			// 文件大小为：14608,当前已处理：11419
			// 文件大小为：14608,当前已处理：14608
			// */
			// }
			// });

			// 解决上传文件名的中文乱码
			upload.setHeaderEncoding(Const.ENCODING);

			String fileSizeMax = getUploadFileSizeMax(contextPath);
			// 设置上传单个文件大小的最大值，目前是设置为1024*1024字节，也就是1MB
			upload.setFileSizeMax(1024 * Integer.parseInt(fileSizeMax));
			// System.out.println("单个文件大小的最大值是："+fileSizeMax);

			// 设置上传所有文件大小的最大值，目前是设置为1024*10240字节，也就是10MB
			String allFileSizeMax = getUploadAllFileSizeMax(contextPath);
			upload.setSizeMax(1024 * Integer.parseInt(allFileSizeMax));
			// System.out.println("所有文件大小的最大值是："+allFileSizeMax);
			// 4、使用ServletFileUpload解析器解析上传数据，解析结果返回的是一个List<FileItem>集合，每一个FileItem对应一个Form表单的输入项
			List<FileItem> list = upload.parseRequest(request);

			// 防止图片文件重名,引入计数器
			short imgCount=1;
			for (FileItem item : list) {
				if (item.isFormField()) {
					// 如果fileitem中封装的是普通输入项的数据

					String name = item.getFieldName().trim();
					// 解决普通输入项的数据的中文乱码问题
					String value = item.getString(Const.ENCODING).trim();
					// 每取到一对值就对commodity对象赋值
					setCommmodityValue(name, value, commodity);

				} else {// 如果fileitem中封装的是上传文件
					// 得到上传的文件名称
					String filename = item.getName().trim();

					if (filename == null || filename.trim().equals("")) {
						continue;
					}
					// 注意：不同的浏览器提交的文件名是不一样的，有些浏览器提交上来的文件名是带有路径的，如：
					// c:\a\b\1.txt，而有些只是单纯的文件名，如：1.txt
					// 处理获取到的上传文件的文件名的路径部分，只保留文件名部分
					filename = filename.substring(filename.lastIndexOf(File.separator) + 1);
					// 得到上传文件的扩展名(含“.”)
					String fileExtName = filename.substring(filename.lastIndexOf("."));
					// 如果需要限制上传的文件类型，那么可以通过文件的扩展名来判断上传的文件类型是否合法
					System.out.println("上传文件：" + filename);

					// 获取item中的上传文件的输入流
					InputStream in = item.getInputStream();
					// 得到文件保存的名称
					String imgFilename = makeUploadFileName(fileExtName,imgCount++);
					System.out.println("实际上传文件名：" + imgFilename);

					// 得到文件的保存目录(相对路径) upload/image/2015/05/03
					String imgUploadRelativePath = imgUploadRootPath + makeDatePath();
					System.out.println("上传相对路径名：" + imgUploadRelativePath);

					// 得到文件的保存目录
					String realImgUploadPath = makeUploadFullPath(contextPath, imgUploadRelativePath);
					System.out.println("实际上传路径：" + realImgUploadPath);
					String realFileUploadFullPath = realImgUploadPath + imgFilename;
					System.out.println("全路径文件名：" + realFileUploadFullPath);
					// 创建一个文件输出流
					FileOutputStream out = new FileOutputStream(realFileUploadFullPath);
					// 创建一个缓冲区
					byte buffer[] = new byte[1024];
					// 判断输入流中的数据是否已经读完的标识
					int len = 0;
					// 循环将输入流读入到缓冲区当中，(len=in.read(buffer))>0就表示in里面还有数据
					while ((len = in.read(buffer)) > 0) {
						// 使用FileOutputStream输出流将缓冲区的数据写入到指定的目录(realImgUploadPath
						// + imgFilename)当中
						out.write(buffer, 0, len);
					}
					// 关闭输入流
					in.close();
					// 关闭输出流
					out.close();
					// 删除处理文件上传时生成的临时文件
					// item.delete();
					String oldPath = commodity.getImagePath();
//					String imgPath = oldPath + imgUploadRelativePath + imgFilename + imgCount;
					commodity.setImagePath(oldPath + imgUploadRelativePath + imgFilename + Const.IMAGE_SEPARATOR );
				}
			}
		} catch (FileUploadBase.FileSizeLimitExceededException e) {
			e.printStackTrace();
			message = Const.ERRMSG_FILESIZE_OVERFLOW;

		} catch (FileUploadBase.SizeLimitExceededException e) {
			e.printStackTrace();
			message = Const.ERRMSG_ALLFILESIZE_OVERFLOW;
			System.out.println(message);
		} catch (Exception e) {
			e.printStackTrace();
			message = Const.ERRMSG_FILEUPLOAD_FAIL;
			System.out.println(message);
		}

		System.out.println("---message:" + message);
		if(0 < message.length()){
			String msgPath = File.separator + Const.JSP_FOLDER_NAME + Const.JSP_MESSAGE;
			try {
				request.setAttribute(Const.JSP_P_MESSAGE, message);
				request.setAttribute(Const.JSP_P_FORWARD_URL, Const.JSP_PV_FORWARD_URL_BACK);
				request.setAttribute(Const.JSP_P_URL_MESSAGE, Const.JSP_PV_URLMSG_BACK);
				request.getRequestDispatcher(msgPath).forward(request, response);
				return null;
			} catch (ServletException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		String oldPath = commodity.getImagePath();
		if(null != oldPath && !"".equals(oldPath)){
			if(oldPath.charAt(oldPath.length()-1) == Const.IMAGE_SEPARATOR){
				commodity.setImagePath(oldPath.substring(0,oldPath.length()-1));
			}
		}
		else{
			commodity.setImagePath("");
		}
		return commodity;
	}

	/**
	 * 通过字段名对commodity属性赋值
	 * @param fieldName 字段名
	 * @param fieldValue
	 * @param commodity
	 */
	private void setCommmodityValue(String fieldName, String fieldValue, Commodity commodity) {
		if(Const.JSP_P_CMDT_ID.equalsIgnoreCase(fieldName)){
			commodity.setId(Long.parseLong(fieldValue));
		}
		if(Const.JSP_P_CMDT_STYLENO.equalsIgnoreCase(fieldName)){
			commodity.setStyleNo(fieldValue);
		}
		if(Const.JSP_P_CMDT_SHOWNO.equalsIgnoreCase(fieldName)){
			commodity.setShowNo(fieldValue);
		}
		if(Const.JSP_P_CMDT_CMDTSN.equals(fieldName)){
			commodity.setCmdtSN(fieldValue);
		}
		if(Const.JSP_P_CMDT_CMDTNAME.equals(fieldName)){
			commodity.setName(fieldValue);
		}
//		if(Const.SQL_FD_CMDT_IMAGEPATH.equals(fieldName)){
//			commodity.setImagePath(fieldValue + Const.IMAGE_SEPARATOR);
//		}
	}

	/**
	 * 获取上传所有文件大小的最大值
	 * @param contextPath
	 * @return
	 */
	private String getUploadAllFileSizeMax(String contextPath) {
		PropertiesMan pMan = new PropertiesMan(contextPath + Const.PROPERTIES_UPLOAD);
		String allFileSizeMax = pMan.getValue(Const.UPLOAD_ALLFILESIZEMAX);
		return allFileSizeMax;
	}

	private String getUploadFileSizeMax(String contextPath) {
		PropertiesMan pMan = new PropertiesMan(contextPath + Const.PROPERTIES_UPLOAD);
		String fileSizeMax = pMan.getValue(Const.UPLOAD_FILESIZEMAX);
		return fileSizeMax;
	}

	private String getThreshold(String contextPath) {
		PropertiesMan pMan = new PropertiesMan(contextPath + Const.PROPERTIES_UPLOAD);
		String sizeThreshold = pMan.getValue(Const.UPLOAD_THRESHOLD);
		return sizeThreshold;
	}

	/**
	 * 根据配置文件生成上传文件时所用临时文件目录(全)路径
	 *
	 * @param contextPath
	 * @return 成功则返回临时文件目录(全)路径
	 */
	private String makeTempFullPath(String contextPath) {
		PropertiesMan pMan = new PropertiesMan(contextPath + Const.PROPERTIES_UPLOAD);
		String tmpFolder = pMan.getValue(Const.UPLOAD_TEMP_FOLDER);
		String temFullPath = tmpFolder + File.separator;
		return temFullPath;
	}

	/**
	 * 根据配置文件生成图片上传总目录路径
	 * （类似：upload/image/)
	 * @return: 成功返回图片上传总目录路径
	 */
	private String getImageUploadRootPath(String contextPath) {
		return ActionAssistant.makeImageUploadPath(contextPath);
	}

	/**
	 * @Description 生成上传文件的文件名：小时+分+秒+毫秒+imgIndex
	 * @Anthor wzr
	 * @param fileExtName
	 *            文件的扩展名
	 * @param imgIndex 图片的索引
	 * @return 文件名+扩展名
	 */
	private String makeUploadFileName(String fileExtName, short imgIndex) {
		// 为防止文件覆盖的现象发生，要为上传文件产生一个唯一的文件名.
		SimpleDateFormat sdf = new SimpleDateFormat("HHmmssSSS");

		return sdf.format(new Date()) + imgIndex + fileExtName;
	}

	/**
	 * 把工程目录路径和相对路径合并成文件上传绝对路径
	 * <p> 如果目录不存在则创建
	 * @Anthor:wzr
	 * ＠param contextPath 工程目录绝对路径
	 * @param uploadRelativePath 文件上传相对路径(形如：/.../upload)
	 * @return 新的上传目录(全路径,形如：/mydata/myPrj/upload/image/.../)
	 */
	private String makeUploadFullPath(String contextPath,String uploadRelativePath) {
		// 新的保存目录(绝对路径）
		String dir = contextPath + uploadRelativePath;

		makeDir(dir);
		return dir;
	}

	/**
	 * 用日期构造目录路径(相对路径）
	 * <p>为防止一个目录下面出现太多文件，要使用年月日分散生成目录存储
	 * <p>类似2015/05/03/
	 * @return
	 */
	private String makeDatePath() {
		String dir = (new SimpleDateFormat("yyyy")).format(new Date()) + File.separator
				+ (new SimpleDateFormat("MM")).format(new Date()) + File.separator
				+ (new SimpleDateFormat("dd")).format(new Date()) + File.separator;
		return dir;
	}

	private void makeDir(String dir) {
		// File既可以代表文件也可以代表目录
		File file = new File(dir);

		// 如果目录不存在
		if (!file.exists()) {
			// 创建目录
			file.mkdirs();
		}
	}
}
