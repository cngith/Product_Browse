package cn.wzr.entity;

public class Commodity {

	private Long id;
	/**
	 *  展号
	 */
	private String showNo="";
	
	/**
	 * 编号
	 */
	private String cmdtSN="";
	
	private String styleNo="";
	
	
	private String name="";
	
	private String imagePath="";
	
	public String getStyleNo() {
		return styleNo;
	}
	public void setStyleNo(String styleNo) {
		this.styleNo = styleNo;
	}
	
	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取展号
	 * @return
	 */
	public String getShowNo() {
		return showNo;
	}
	
	public void setShowNo(String showNo) {
		this.showNo = showNo;
	}
	
	/**
	 * 获取编号
	 * @return
	 */
	public String getCmdtSN() {
		return cmdtSN;
	}
	public void setCmdtSN(String cmdtSN) {
		this.cmdtSN = cmdtSN;
	}
	
}
