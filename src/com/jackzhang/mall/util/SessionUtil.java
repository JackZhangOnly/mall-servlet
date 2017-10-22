package com.jackzhang.mall.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * session帮助类
 * @author jackzhang
 *
 */
public class SessionUtil 
{
	/**
	 * 获得session
	 * @param request
	 * @return
	 */
	private static HttpSession getSession(HttpServletRequest  request)
	{
		return request.getSession();
	}
	/**
	 * 把对象装入session中
	 * @param request
	 * @param key
	 * @param obj
	 */
	public static void setObjSession(HttpServletRequest request,String key, Object obj)
	{
		getSession(request).setAttribute(key, obj);
	}
	
	/**
	 * 获得session中的值
	 * @param request
	 * @param key
	 * @return
	 */
	public static Object getObjSession(HttpServletRequest request,String key)
	{
		return getSession(request).getAttribute(key);
	}
	
	
}
