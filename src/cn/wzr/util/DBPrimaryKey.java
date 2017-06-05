package cn.wzr.util;

/**
 * 主键传递类
 * <p>当向数据表中添加记录时用于回传自增主键值
 * <p>本类对象作为参数传入添加记录的子方法，修改值后主方法取出其值
 * @author wzr
 *
 */
public class DBPrimaryKey {

	private Long primaryKey=-1L;
	
	public Long getPrimaryKey() {
		return primaryKey;
	}
	public void setPrimaryKey(Long primaryKey) {
		this.primaryKey = primaryKey;
	}
	
	public DBPrimaryKey(Long value){
		primaryKey=value;
	}
	
}
