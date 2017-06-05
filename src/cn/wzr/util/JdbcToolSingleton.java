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
 * @author <a href="">李勇</a>
 * 
 */
public final class JdbcToolSingleton {
	private String url = "";
	private String user = "";
	private String password = "";

	// private static JdbcUtilsSing instance = new JdbcUtilsSing();
	private static JdbcToolSingleton instance = null;

	/**
	 * 
	 * @param dbPropertiesPath 数据库配置文件（全）路径
	 */
	private JdbcToolSingleton(String dbPropertiesPath) {
//		System.out.println("数据库配置文件（全）路径:" + dbPropertiesPath);
		
		DbConfig dbConfig=new DbConfig(dbPropertiesPath);
		
		url=dbConfig.getUrl();
		user=dbConfig.getUserName();
		password=dbConfig.getPassword();
		
		try {
			String clname=dbConfig.getDriver();
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
	public static JdbcToolSingleton getInstance(String dbPropertiesPath) {
		if (instance == null) {
			synchronized (JdbcToolSingleton.class) {
				if (instance == null) {
					instance = new JdbcToolSingleton(dbPropertiesPath);
				}
			}
		}
		return instance;
	}


	public Connection getConnection() throws SQLException {
		return DriverManager.getConnection(url, user, password);
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
