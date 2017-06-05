package cn.wzr.util;

import cn.wzr.global.Const;

public class DbConfig {

	private String driver;
	private String url;
	private String userName;
	private String password;

	/**
	 * 
	 * @param dbPropertiesPath 数据库配置文件（全）路径
	 */
	public DbConfig(String dbPropertiesPath) {
		// 文件名前加了"/"，则表示从类路径下也就是从classes文件夹(编译前对应src)下查找资源
		PropertiesMan pMan= new PropertiesMan(dbPropertiesPath);
		
		this.driver = pMan.getValue(Const.DBCONFIG_DRIVER);
		this.url = pMan.getValue(Const.DBCONFIG_URL);
		this.userName = pMan.getValue(Const.DBCONFIG_USERNAME);
		this.password = pMan.getValue(Const.DBCONFIG_PASSWROD);

	}

	public String getDriver() {
		return driver;
	}

	public String getUrl() {
		return url;
	}

	public String getUserName() {
		return userName;
	}

	public String getPassword() {
		return password;
	}

}
