package cn.wzr.entity;

/** 
 * 实体类 
 *  
 * @author Steven 
 *  
 */  
public class User {  
    // 用户Id  
    private Long userId;  
    // 用户名  
    private String userName;  
    // 用户年龄  
    private String password; 
    // 
    private Long depId;  
    
  
    // 有参构造
//	public User(String user_id, String username, String password, String dep_id) {  
//    }  
  
      
    public User() {  
        
    }  
  
    public Long getDepId() {
		return depId;
	}

	public void setDepId(Long depId) {
		this.depId = depId;
	}

	public Long getUserId() {  
        return userId;  
    }  
  
    public void setUserId(Long userId) {  
        this.userId = userId;  
    }  
  
    public String getUserName() {  
        return userName;  
    }  
  
    public void setUserName(String userName) {  
        this.userName = userName;  
    }  

    public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
     
  
    @Override  
    public String toString() {  
        return "用户编号是 " + this.getUserId() + ", 用户名是 " + userName;  
    }  
  
  
}
