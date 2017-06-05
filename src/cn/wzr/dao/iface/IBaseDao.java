package cn.wzr.dao.iface;

import java.util.List;

/**
	 * 通过泛型机制为所有的实现类提供一下方法 
	 *  
	 * @author Steven 
	 *  
	 */  
	public interface IBaseDao<T> {  
    
		/** 
     * 新增一条数据 
     *  
     * @param t 
     * @return 
     */  
    public boolean add(T t);  
  
    /** 
     * 根据编号(主键)进行删除一条数据 
     *  
     * @param id 
     * @return 
     */  
    public boolean delete(int id);  
  
    /** 
     * 修改数据 
     *  
     * @param t 
     * @return 
     */  
    public boolean update(T t);  
  
    /** 
     * 根据ID查找到该信息 
    * @param id 
    * @return 
    */  
	public T getById(int id);  

	
	/** 
	 * 返回查到的记录数 
	 * @return 成功返回记录数
	 */  
	public int getCount(); 

	/** 
	 * 返回所有的信息 
	 * @return 
	 */  
	public List<T> findAll(); 

	/** 
	 * 返回指定范围信息 
	 * @param startLine 起始行（按索引1为第一条记录）
	 * @param count 显示条数 
	 * @return 
	 */  
//	public List<T> findScopeInAll(int startLine,int count);	 

	/** 
	 * 返回指定范围信息 
	 * @param startLine 起始行（按索引1为第一条记录）
	 * @param count 显示条数 
	 * @return 
	 */  
//	public List<T> findScope(String sql, Object[] param, int startLine,int count);	 
	
 
    
}
