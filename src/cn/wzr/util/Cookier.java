package cn.wzr.util;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Cookier {

	/**
	 * 设置cookie
	 * @param response
	 * @param name  cookie名字
	 * @param value cookie值
	 * @param maxAge cookie生命周期  以秒为单位
	 */
	public static void addCookie(HttpServletResponse response, String name, String value, int maxAge){
	    Cookie cookie = new Cookie(name,value);
	    cookie.setPath("/");
	    if(maxAge>0){
	    	cookie.setMaxAge(maxAge);
	    }else {
			cookie.setMaxAge(0);
		}
	    response.addCookie(cookie);
	}
	
	/**
	 * 根据名字获取cookie
	 * @param request
	 * @param cookieName cookie名字
	 * @return
	 */
	public static Cookie getCookieByName(HttpServletRequest request,String cookieName){
	    Map<String,Cookie> cookieMap = ReadCookieMap(request);
	    if(cookieMap.containsKey(cookieName)){
	        Cookie cookie = (Cookie)cookieMap.get(cookieName);
	        return cookie;
	    }else{
	        return null;
	    } 
	}

	/**
	 * 将cookie封装到Map里面
	 * @param request
	 * @return
	 * Java:http://www.javaweb.cc
	 */
	private static Map<String,Cookie> ReadCookieMap(HttpServletRequest request){
	    Map<String,Cookie> cookieMap = new HashMap<String,Cookie>();
	    Cookie[] cookies = request.getCookies();
	    if(null!=cookies){
	        for(Cookie cookie : cookies){
	            cookieMap.put(cookie.getName(), cookie);
	        }
	    }
	    return cookieMap;
	}
}
