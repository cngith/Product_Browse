package cn.wzr.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.wzr.global.Const;
import cn.wzr.util.Cookier;

/**
 * 授权码过滤器
 * Servlet Filter implementation class AuthorizationCodeFilter
 */
@WebFilter(filterName="/AuthorizationCodeFilter",urlPatterns="/none"
	,initParams={ @WebInitParam(name = Const.FLT_WIP_EXCLUDED_PAGES
	, value = Const.JSP_DEVICE_REGCODE + Const.RECORD_SEPARATOR + Const.ACT_DEVICE_REGISTER
	+ Const.RECORD_SEPARATOR + Const.JSP_DEVICE_REGISTER) })
//	, @WebInitParam(name = "redirectURL", value = Const.ACT_DEVICE_REGISTER)
//	}
public class AuthorizationCodeFilter implements Filter {

	/**
	 * ip地址白名单
	 */
//	private ACWhiteListor acWhiteListor=null;
	
	private String excludedPages;       
	private String[] excludedPageArray; 
	
    /**
     * Default constructor. 
     */
    public AuthorizationCodeFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		this.excludedPages = null;
		this.excludedPageArray = null;
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletRequest req = (HttpServletRequest) request;
		boolean isExcludedPage = false;  
		for (String page : excludedPageArray) {// 遍历例外url数组     
			if(req.getServletPath().substring(1).equals(page)){ // 从第2个字符开始取（把前面的/去掉）
				System.out.println(page + ", you're excluded.");
				isExcludedPage = true;     
				break;     
			}     
		}   
		if (isExcludedPage) {//在过滤url之外     
			chain.doFilter(request, response);     
		}
		else {// 不在过滤url之外，判断session是否存在     
			HttpServletResponse resp = (HttpServletResponse) response;
			if(req.getSession().getAttribute(Const.SS_N_DEVICE_AUTH)!= null){ // 已授权设备认证通过
//				System.out.println("SS_N_DEVICE_AUTH:" + req.getSession().getAttribute(Const.SS_N_DEVICE_AUTH));			
				chain.doFilter(request, response);
			}
			else { // 未生成授权session，查询cookie
				Cookie cookie = Cookier.getCookieByName(req, Const.CK_N_DEV_AUTHCODE);
				if(null != cookie){ // 没有相应的cookie文件
					String devAC = Cookier.getCookieByName(req, Const.CK_N_DEV_AUTHCODE).getValue();
					if(null != devAC && 0 != devAC.length()){ // 已授权设备
						System.out.println("Cookier:" + devAC);
						req.getSession().setAttribute(Const.SS_N_DEVICE_AUTH,devAC);
						chain.doFilter(req, resp);
						return;
					}
				}
				// 未授权设备
				req.getRequestDispatcher(Const.WEBURL_SEPARATOR + Const.JSP_FOLDER_NAME 
							+ Const.DEVICE_FOLDER_NAME + Const.JSP_DEVICE_REGCODE).forward(req, resp);
			}
		}
	}
		

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		excludedPages = fConfig.getInitParameter(Const.FLT_WIP_EXCLUDED_PAGES);     
		if (null!=excludedPages && excludedPages.length()!=0) { // 例外页面不为空    
			excludedPageArray = excludedPages.split(String.valueOf(Const.RECORD_SEPARATOR));     
		}     
	}

}
