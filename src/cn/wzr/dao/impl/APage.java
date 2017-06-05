package cn.wzr.dao.impl;

import java.util.List;

public class  APage <E> {
	
	private List<E> list;
	
	private int startPage=1;
	private int endPage=10;
	
	/**
	 * 总记录数，必须传入
	 */
	private int totalRecord;
	
	/**
	 * 每页记录数
	 */
	private int pageSize=5;

	/**
	 * 当前页码(从1开始)
	 */
	private int pageNum=1;
	
	/**
	 * 总页数，由'总记录数'和'每页记录数'算出
	 */
	private int totalPage;
	
	/**
	 * 显示的页数
	 */
	private int showPageCount = 10;
	
	
	/**
	 * 对应action的名字
	 */
	private String actionName = "";
	
	public String getActionName() {
		return actionName;
	}

	public void setActionName(String actionName) {
		this.actionName = actionName;
	}

	/**
	 * 显示页数最小值（页码栏最少显示多少个页码）
	 */
//	private static final int SHOW_PAGECOUNT_MIN=3;
	

	/**
	 * 
	 * @param tatolRecord 总记录数（一共多少条记录）
	 */
	public APage (int tatolRecord, int pageSize){
		this.totalRecord = tatolRecord;
		this.pageSize = pageSize;
		totalPage=((totalRecord - 1)/pageSize) + 1;
		startPage = 1;
		endPage = totalPage;
	}
	
	public int getShowPageCount() {
		return showPageCount;
	}
	
	/**
	 * 设置页码栏显示多少个页码
	 * @param showPageCount
	 */
	public void setShowPageCount(int showPageCount) {
		if(totalPage <= 1){
			this.showPageCount = totalPage;
			startPage = totalPage;
			endPage = totalPage;
		}
		else{
			this.showPageCount = showPageCount <= 0 ? 1 : showPageCount;
			startPage = (pageNum-(this.showPageCount/2)) <= 1 ? 1 : pageNum-(this.showPageCount/2);
			endPage = (startPage + this.showPageCount - 1) > totalPage ? totalPage : (startPage + this.showPageCount - 1);
			if(endPage == totalPage){
				if(this.showPageCount < totalPage){
					startPage = endPage - this.showPageCount + 1;
				}else {
					startPage = 1;
				}
			}
		}
	}

	public int getTotalPage() {
		return totalPage;
	}
	
	
	public List<E> getList() {
		return list;
	}
	public void setList(List<E> list) {
		this.list = list;
	}
	public int getStartPage() {
		return startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public int getTotalRecord() {
		return totalRecord;
	}
	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
	}
	public int getPageSize() {
		return pageSize;
	}
	
	/**
	 * 设置每页记录数
	 * @param pageSize
	 */
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
	public int getPageNum() {
		return pageNum;
	}
	
	/**
	 * 设置当前页码
	 * @param pageNum
	 */
	public void setPageNum(int pageNum) {
		if(pageNum <= 1)
			this.pageNum = 1;
		else if (pageNum > this.totalPage) {
			this.pageNum = this.totalPage;
		}else {
			this.pageNum = pageNum;
		}
	}
	
	
}
