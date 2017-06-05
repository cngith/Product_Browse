package cn.wzr.dao.iface;


import java.util.List;

import cn.wzr.entity.Commodity;

public interface ICommodityDao extends IBaseDao<Commodity> {

	/** 
	 * 根据SQL语句查找记录
	 * @param sql SQL语句
	 * @param parameters SQL语句的参数
	 * @return 
	 */  
	public List<Commodity> findBySQL(String sql, Object[] parameters);

	/**
	 * 根据SQL语句取得记录数
	 * @param sql SQL语句
	 * @param parameters SQL语句的参数 
	 * @return
	 */
	public int getCountBySQL(String sql, Object[] parameters);
}
