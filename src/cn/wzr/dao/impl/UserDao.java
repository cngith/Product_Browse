package cn.wzr.dao.impl;

import java.util.List;

import cn.wzr.dao.iface.IUserDao;
import cn.wzr.entity.User;
import cn.wzr.global.Const;
import cn.wzr.global.Const.UserCheckResult;
import cn.wzr.util.DBPrimaryKey;
import cn.wzr.util.DaoHandle;

public class UserDao implements IUserDao{

	private String dbUrl;
	private String dbDriver;
	private String dbUsername = "";
	private String dbPassword = "";
    
	public UserDao(String dbUrl,String dbDriver, String dbUsername, String dbPassword) {
		this.dbUrl = dbUrl;
		this.dbUsername = dbUsername;
		this.dbPassword = dbPassword;
		this.dbDriver = dbDriver;
	}
		
	
	@Override  
	public boolean add(User user) {  
		// 创建标志位  
		boolean flag = false;
		// 创建sql语句  
		String sql = "insert into tb_user values(?,?,?,?)";  
		// 填充参数(主键不需要填写，自增)  
		Object[] parameters = new Object[] { null, user.getUserName(), user.getPassword(),user.getDepId() };  
		DaoHandle dh=new DaoHandle(dbUrl,dbDriver, dbUsername, dbPassword);
		DBPrimaryKey dbpk = new DBPrimaryKey(-1L);
		flag = dh.executeDML(sql, parameters,dbpk) > 0 ? true : false;  
		user.setUserId(dbpk.getPrimaryKey());
		return flag;  
	}
	
	@Override  
    public boolean delete(int id) {  
        // 创建标志位  
        boolean flag = false;  
        // 创建sql语句  
        String sql = "delete from tb_user where userId=?";  
        // 填充参数  
        Object[] parameters = new Object[] { id };  
        // 执行数据库数据删除操作  
        DaoHandle dh=new DaoHandle(dbUrl,dbDriver, dbUsername, dbPassword);
        flag = dh.executeDML(sql, parameters,null) > 0 ? true : false;  
        return flag;  
    }  
  
    @Override  
    public List<User> findAll() {  
        // 创建sql语句  
        String sql = "select * from tb_user";  
        // 查询所有的记录  
        DaoHandle dh =new DaoHandle(dbUrl,dbDriver, dbUsername, dbPassword);
        List<User> list = (List<User>) dh.executeQueryForMultiple(sql, null, User.class);  
        return list;  
    }  
  
    @Override  
    public User getById(int id) {  
        // 创建sql语句  
        String sql = "select * from tb_user where UserId = ?";  
        // 填充参数  
        Object[] parameters = new Object[] { id };  
        // 查找单个记录  
        DaoHandle dh=new DaoHandle(dbUrl,dbDriver, dbUsername, dbPassword);
        User user = dh.executeQueryForSingle(sql, parameters, User.class);  
        return user;
    }  
  
    @Override  
    public boolean update(User user) {  
        // 创建标志位  
        boolean flag = false;  
        // 创建sql语句  
        String sql = "update tb_user set UserName=?,Password=? where UserId=?";  
        // 注入参数  
        Object[] parameters = new Object[] { user.getUserName(), user.getPassword(), user.getUserId() };  
        DaoHandle dh=new DaoHandle(dbUrl,dbDriver, dbUsername, dbPassword);
        DBPrimaryKey primaryKey=new DBPrimaryKey(-1L);
        flag = dh.executeDML(sql, parameters,primaryKey) > 0 ? true : false;  
        return flag;  
    }

	@Override
	public User findByUserName(String username) {
		// 创建sql语句  
        String sql = Const.SQL_TB_USER_BY_USERNAME;  
        // 填充参数  
        Object[] parameters = new Object[] { username };  
        // 查找单个记录  
//        System.out.println("---findByUserName:" + username);
        DaoHandle dh = new DaoHandle(dbUrl,dbDriver, dbUsername, dbPassword);
        User user = dh.executeQueryForSingle(sql, parameters, User.class);  
        return user;  
	}
	
	/**
	 * 用户验证
	 */
	@Override
	public UserCheckResult userCheck(String username,String password){
	System.out.println("--UserCheckResult:" + username);
	User user = findByUserName(username);
	if (null == user){
		return UserCheckResult.USERNAME_ERROR;
	}
	if(null == user.getPassword() || !password.equals(user.getPassword())){
		return UserCheckResult.PASSWORD_ERROR;
	}
	return UserCheckResult.LEGAL;
}





	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	
    
}
