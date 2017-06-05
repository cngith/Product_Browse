package cn.wzr.util;

import java.io.File;
import java.lang.reflect.Method;

import cn.wzr.global.Const;

public final class ActionAssistant {


	/**
	 * 取图片上传路径上传相对路径（类似：upload/image/)
	 * @param contextPath 工程绝对路径
	 * @return
	 */
	public static String makeImageUploadPath(String contextPath) {
		PropertiesMan pMan = new PropertiesMan(contextPath + Const.PROPERTIES_UPLOAD);
		String upFolder = pMan.getValue(Const.UPLOAD_FOLDER);
		String upImgFolder = pMan.getValue(Const.UPLOAD_IMAGE_FOLDER);
		String upCompoundPath = upFolder + File.separator + upImgFolder;
		String imgUpRelativePath = upCompoundPath + File.separator;
		return imgUpRelativePath;
	}
//	public List<String> getImgPath(String imgCompoundPath){
//		
//	}
	/**
	 * 通过方法名，set其值
	 * @param <T>
	 * 
	 * @param <T>
	 * @param sql
	 * @param paramters
	 * @param objClass
	 */
//	public static <T> void setValue(String fieldName, String fieldValue, <T> objClass) {
//		Method[] methods = objClass.getDeclaredMethods();
//		// 遍历每个方法
//		for (Method m : methods) {
//			if (fieldValue != null) {
//				// 如果是和该列同名的set方法，则调用该方法
//				if (m.getName().equalsIgnoreCase("set" + fieldName)) {
//					// 进行对set方法的调用，向其中置值
//					Class[] params = m.getParameterTypes();
//					Class paramType;
//					try {
//						paramType = Class.forName(params[0].getName());
//						m.invoke(objClass, paramType.cast(fieldValue));
//					} catch (Exception e) {
//						e.printStackTrace();
//					}
//					System.out.println("-------method:" + m.getName() + "--dataType:"
//							+ params[0].getName());
//				}
//			}
//		}
//	}
	
}
