package cn.wzr.dao.iface;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface IModel {
	
	/** 
     * 模型接口 
     *  
     * @param request 
     * @return 
     */  
    public String execute(HttpServletRequest request,HttpServletResponse resp);  
} 