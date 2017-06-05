package cn.wzr.dao.iface;

import cn.wzr.entity.User;
import cn.wzr.global.Const.UserCheckResult;

public interface IUserDao extends IBaseDao<User> {

	/**
	 * 根据UserName查找到用户信息
	 * 
	 * @param username
	 *            用户名
	 * @return
	 */
	public User findByUserName(String username);

	/**
	 * 根据UserName查找到用户信息
	 * 
	 * @param username
	 *            用户名
	 * @param password
	 * @return
	 */
	
	
	public UserCheckResult userCheck(String username,String password);
	
}
