package cn.wzr.dao.impl;

import java.util.List;

import cn.wzr.dao.iface.ICommodityDao;
import cn.wzr.entity.Commodity;
import cn.wzr.global.Const;
import cn.wzr.util.DBPrimaryKey;
import cn.wzr.util.DaoHandle;

public class CommodityDao implements ICommodityDao {

	private String dbUrl;
	private String dbDriver;
	private String dbUsername = "";
	private String dbPassword = "";
    
	public CommodityDao(String dbUrl,String dbDriver, String dbUsername, String dbPassword) {
		this.dbUrl = dbUrl;
		this.dbUsername = dbUsername;
		this.dbPassword = dbPassword;
		this.dbDriver = dbDriver;
		
	}
	
	@Override
	public boolean add(Commodity t) {
		// 创建标志位  
		boolean flag;
		// 创建sql语句  
		String sql = Const.SQL_IN_COMMODITY;  
		// 填充参数(主键不需要填写，自增)  
		Object[] parameters = new Object[] { null, t.getStyleNo(),t.getName(),t.getImagePath(),t.getShowNo(),t.getCmdtSN()};  
		
		DaoHandle dh = new DaoHandle(dbUrl,dbDriver, dbUsername, dbPassword);
		DBPrimaryKey dbpk = new DBPrimaryKey(-1L);
		flag = dh.executeDML(sql, parameters,dbpk) > 0 ? true : false;  
		t.setId(dbpk.getPrimaryKey());
		return flag;
		
	}

	@Override
	public boolean delete(int id) {
		// 创建标志位  
		boolean flag = false;
		// 创建sql语句  
		String sql = Const.SQL_DEL_COMMODITY;  
		// 填充参数(根据Id更新数据)  
		Object[] parameters = new Object[] {id};  
		
		DaoHandle dh = new DaoHandle(dbUrl,dbDriver, dbUsername, dbPassword);
		flag = dh.executeDML(sql, parameters,null) == 1 ? true : false;  
		return flag;
	}

	@Override
	public boolean update(Commodity t) {
		// 创建标志位  
		boolean flag = false;
		// 创建sql语句  
		String sql = Const.SQL_UPD_COMMODITY;  
		// 填充参数(根据Id更新数据)  
		Object[] parameters = new Object[] { t.getShowNo(),t.getCmdtSN(), t.getStyleNo(),t.getName(),t.getImagePath(),t.getId()};  
		
		DaoHandle dh=new DaoHandle(dbUrl,dbDriver, dbUsername, dbPassword);
		flag = dh.executeDML(sql, parameters,null) == 1 ? true : false;  
		return flag;
	}

	@Override
	public Commodity getById(int id) {
		// 创建sql语句  
        String sql = Const.SQL_TB_COMMODITY_ALL + Const.SQL_SQL_WHERE + Const.SQL_FD_COMMODITYID + "=?";  
        // 填充参数  
        Object[] parameters = new Object[] { id };  
        // 查找单个记录  
        DaoHandle dh=new DaoHandle(dbUrl,dbDriver, dbUsername, dbPassword);
        Commodity commodity = dh.executeQueryForSingle(sql, parameters, Commodity.class);  
        return commodity;
	}

	/**
	 * 从所有记录中取出指定范围的记录
	 * @param startLine 起始记录位置（从第几行开始返回），最小为0
	 * @param count 返回记录数
	 */
//	@Override
//	public List<Commodity> findScopeInAll(int startLine,int count) {
//		// 查询所有的记录  
//       String sql = Const.SQL_TB_COMMODITY_ALL_LIM;  
//        // 填充参数()  
//		Object[] parameters = new Object[] {startLine, count};
//		DaoHandle dh = new DaoHandle(dbUrl,dbDriver, dbUsername, dbPassword);
//		List<Commodity> list = (List<Commodity>) dh.executeQueryForMultiple(sql, parameters, Commodity.class);  
//		return list;  
//	}


	@Override
	public List<Commodity> findBySQL(String sql, Object[] parameters) {
		DaoHandle dh =new DaoHandle(dbUrl,dbDriver, dbUsername, dbPassword);
		List<Commodity> list = (List<Commodity>) dh.executeQueryForMultiple(sql, parameters, Commodity.class);  
		return list;  
	}

	@Override
	public List<Commodity> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getCount() {
		// 
		String sql=Const.SQL_GET_ROWCOUNT + Const.SQL_TBN_COMMODITY;
		DaoHandle dh = new DaoHandle(dbUrl,dbDriver, dbUsername, dbPassword);
		
		return dh.executeQueryForCount(sql, null);
	}
	
	@Override
	public int getCountBySQL(String sql,Object[] parameters) {
		// 
		DaoHandle dh = new DaoHandle(dbUrl, dbDriver, dbUsername, dbPassword);
		return dh.executeQueryForCount(sql, parameters);
	}


}
