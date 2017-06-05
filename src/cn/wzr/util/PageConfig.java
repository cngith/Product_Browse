package cn.wzr.util;

import cn.wzr.global.Const;

public class PageConfig {

	/**
	 * 每页显示几条记录
	 */
	private String linesPerPage;
	
	/**
	 * 每行显示几张图片
	 */
	private String imgPerLine;
	
	/**
	 * 页码栏共显示多少页码
	 */
	private String showPageCount;

	public String getLinesPerPage() {
		return linesPerPage;
	}

	public void setLinesPerPage(String linesPerPage) {
		this.linesPerPage = linesPerPage;
	}

	public String getImgPerLine() {
		return imgPerLine;
	}

	public void setImgPerLine(String imgPerLine) {
		this.imgPerLine = imgPerLine;
	}

	public String getShowPageCount() {
		return showPageCount;
	}

	public void setShowPageCount(String showPageCount) {
		this.showPageCount = showPageCount;
	}

	/**
	 * 
	 * @param pagePropertiesPath 数据库配置文件（全）路径
	 */
	public PageConfig(String pagePropertiesPath) {
		PropertiesMan pMan=new PropertiesMan(pagePropertiesPath);
		
		this.linesPerPage = pMan.getValue(Const.PAGE_LINES_PERPAGE);
		this.imgPerLine = pMan.getValue(Const.PAGE_IMG_PERLINE);
		this.showPageCount = pMan.getValue(Const.PAGECONFIG_SHOW_PAGECOUNT);

	}

}
