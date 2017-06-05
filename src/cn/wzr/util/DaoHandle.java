package cn.wzr.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO数据操作的辅助类
 * 
 * @author Steven
 * 
 */
public class DaoHandle {
	private static Connection con;

	private String url;
	private String dbDriver;
	private String dbUsername = "";
	private String dbPassword = "";

	public DaoHandle(String url, String dbdriver, String dbUsername, String dbPassword) {
		this.url = url;
		this.dbUsername = dbUsername;
		this.dbPassword = dbPassword;
		this.dbDriver = dbdriver;

	}

	/**
	 * 执行所有的DML操作
	 * 
	 * @param sql
	 * @param parameters
	 * @param primaryKey 用于返回自增主键
	 * @return 成功返回更新记录数，失败返回-1
	 */
	public int executeDML(String sql, Object[] parameters, DBPrimaryKey primaryKey) {
		int count = -1;
		// 获取连接
		DBConSingleton dbc = DBConSingleton.getInstance(url, dbDriver, dbUsername, dbPassword);
		ResultSet rs = null;
		try {
			con = dbc.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		PreparedStatement pstmt = null;

		if (con != null) {
			try {
				// 获取处理器对象,可获取新增记录的主键
				pstmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				// 注入参数
				for (int i = 0; i < parameters.length; i++) {
					// 根据参数的类型判断调用注入方法
					// 参数类型不安全
					pstmt.setObject(i + 1, parameters[i]);
				}
				// 执行SQL语句
				count = pstmt.executeUpdate();
				rs = pstmt.getGeneratedKeys();
				while (null != rs && rs.next()) {
					if(null != primaryKey)
						primaryKey.setPrimaryKey(rs.getLong(1));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {

				dbc.closeAll(con, pstmt, rs);
			}
		}
		return count;
	}

	

	/**
	 * 查询获取单个对象
	 * 
	 * @param <T>
	 * @param sql
	 * @param paramters
	 * @param objClass
	 * @return
	 */
	public <T> T executeQueryForSingle(String sql, Object[] parameters, Class<T> objClass) {
		T t = null;
		
		// 获取连接
		DBConSingleton dbc = DBConSingleton.getInstance(url, dbDriver, dbUsername, dbPassword);
		try {
			con = dbc.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		if (con != null) {
			try {
				// 获取处理器
				pstmt = con.prepareStatement(sql);
				// 注入参数
				if (parameters != null) {
					for (int i = 0; i < parameters.length; i++) {
						pstmt.setObject(i + 1, parameters[i]);
					}
				}
				// 执行查询
				rs = pstmt.executeQuery();
				// 获取结果集的元数据
				ResultSetMetaData metaData = rs.getMetaData();
				// 获取所有列名
				String[] colNames = getColNames(metaData);
				// 获取所有列的数据类型
				// String[] colTypes = getColTypeNames(metaData);
				// 使用反射获取当前类的方法
				Method[] methods = objClass.getDeclaredMethods();
				// 获取结果集的数据(只有一条记录)
				if (rs.next()) {
					// 获取类的实例
					t = objClass.newInstance();
					// 循环判断每列的数据类型
					for (int i = 0; i < colNames.length; i++) {
						// 获取每列的结果
						// 进行所有参数类型的赋值，不能保证类型安全
						Object value = rs.getObject(i + 1);
						// 遍历每个方法
						for (Method m : methods) {
							if (value != null) {
								// 如果是和该列同名的set方法，则调用该方法
								// 此处要求类的字段的set方法，必须遵循命名规范
								if (m.getName().equalsIgnoreCase("set" + colNames[i])) {
									// 进行对set方法的调用，向其中置值
									m.invoke(t, value);
									// System.out.println("---------field dataType:"
								}
							}
						}
					}
				}

			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				dbc.closeAll(con, pstmt, rs);
			}
		}
		// 返回对象
		System.out.println("----executeQueryForSingle returned");
		return t;
	}

	/**
	 * 通过元数据获取列名数组
	 * 
	 * @param metaData
	 * @return
	 */
	private static String[] getColNames(ResultSetMetaData metaData) {
		String[] colNames = null;

		try {
			// 获取结果集的列数
			int colCount = metaData.getColumnCount();
			// 创建数组
			colNames = new String[metaData.getColumnCount()];
			// 遍历每列
			for (int i = 1; i <= colCount; i++) {
				// 获取列名
				colNames[i - 1] = metaData.getColumnLabel(i);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return colNames;
	}

	/**
	 * 根据元数据获取所有列的数据类型
	 * 
	 * @param metaData
	 * @return
	 */
	private static String[] getColTypeNames(ResultSetMetaData metaData) {
		String[] colTypes = null;

		try {
			// 获取列数
			int colCount = metaData.getColumnCount();
			// 创建数组
			colTypes = new String[colCount];
			for (int i = 1; i <= colCount; i++) {
				colTypes[i - 1] = metaData.getColumnTypeName(i);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return colTypes;
	}

	/**
	 * 查询多行对象
	 * 
	 * @param <T>
	 * @param sql
	 * @param paramters
	 * @param objClass
	 * @return
	 */
	public <T> List<T> executeQueryForMultiple(String sql, Object[] parameters, Class<T> objClass) {
		List<T> list = new ArrayList<T>();
		DBConSingleton dbc = DBConSingleton.getInstance(url, dbDriver, dbUsername, dbPassword);
		try {
			con = dbc.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		if (con != null) {
			try {
				// 获取处理器
				pstmt = con.prepareStatement(sql);
				if (parameters != null) {
					// 注入参数
					for (int i = 0; i < parameters.length; i++) {
						pstmt.setObject(i + 1, parameters[i]);
					}
				}
				// 执行查询
				rs = pstmt.executeQuery();
				// 获取结果集的元数据
				ResultSetMetaData metaData = rs.getMetaData();
				// 获取所有列名
				String[] colNames = getColNames(metaData);
				// 获取所有列的数据类型
				// int[] colTypes = getColTypes(metaData);
				// 使用反射获取当前类的方法
				Method[] methods = objClass.getDeclaredMethods();
				// 获取结果集的数据
				while (rs.next()) {
					// 获取类的实例
					T t = objClass.newInstance();
					// 循环判断每列的数据类型
					for (int i = 0; i < colNames.length; i++) {
						// 获取每列的结果
						// 进行所有参数类型的赋值，不能保证类型安全
						Object value = rs.getObject(i + 1);
						// 遍历每个方法
						for (Method m : methods) {
							if (value != null) {
								// 如果是和该列同名的set方法，则调用该方法
								if (m.getName().equalsIgnoreCase("set" + colNames[i])) {
									// 进行对set方法的调用，向其中置值
									m.invoke(t, value);
								}
							}
						}
					}
					// 向集合中添加数据
					list.add(t);
				}

			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			} finally {
				dbc.closeAll(con, pstmt, rs);
			}
		}
		// 返回集合
		return list;
	}

	/**
	 * 返回查询结果集中数据行数
	 * 
	 * @param sql
	 * @param paramters
	 * @return
	 */
	public int executeQueryForCount(String sql, Object[] parameters) {
		int count = 0;
		// 获取连接
		DBConSingleton dbc = DBConSingleton.getInstance(url, dbDriver, dbUsername, dbPassword);
		try {
			con = dbc.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			// 获取处理器
			pstmt = con.prepareStatement(sql);
			if (parameters != null) {
				// 注入参数
				for (int i = 0; i < parameters.length; i++) {
					pstmt.setObject(i + 1, parameters[i]);
				}
			}
			// 执行查询
			rs = pstmt.executeQuery();
			// 取出结果集
			if (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbc.closeAll(con, pstmt, rs);
		}
		return count;
	}
}
