package cn.wzr.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 
 * 2008-12-6
 * 
 * @author <a href="">wzr</a>
 * 
 */
public final class DBConSingleton {
	private String url = "";
	//private String driver = "";
	private String username = "";
	private String password = "";

	// private static JdbcUtilsSing instance = new JdbcUtilsSing();
	private static DBConSingleton instance = null;

	/**
	 * 
	 * @param dbPropertiesPath 数据库配置文件（全）路径
	 */
	private DBConSingleton(String url, String driver, String username, String password) {
//		System.out.println("数据库配置文件（全）路径:" + dbPropertiesPath);
		
		this.url = url;
		this.username = username;
		this.password = password;
		//this.driver = driver;
		try {
			String clname=driver;
			Class.forName(clname);
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @param dbPropertiesPath 数据库配置文件（全）路径
	 * @return
	 */
	public static DBConSingleton getInstance(String url, String driver, String username, String password) {
		if (instance == null) {
			synchronized (DBConSingleton.class) {
				if (instance == null) {
					instance = new DBConSingleton(url, driver, username, password);
				}
			}
		}
		return instance;
	}


	public Connection getConnection() throws SQLException {
		return DriverManager.getConnection(url,username, password);
	}

	public void closeAll(Connection conn, Statement st, ResultSet rs ) {
		if (rs != null){
			try {
					rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			rs=null;
		} 
		if (st != null){
			try {
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			st=null;
		}
		if (conn != null){
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			conn=null;
		}
	}
}
