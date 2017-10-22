package com.jackzhang.mall.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * session������
 * @author jackzhang
 *
 */
public class SessionUtil 
{
	/**
	 * ���session
	 * @param request
	 * @return
	 */
	private static HttpSession getSession(HttpServletRequest  request)
	{
		return request.getSession();
	}
	/**
	 * �Ѷ���װ��session��
	 * @param request
	 * @param key
	 * @param obj
	 */
	public static void setObjSession(HttpServletRequest request,String key, Object obj)
	{
		getSession(request).setAttribute(key, obj);
	}
	
	/**
	 * ���session�е�ֵ
	 * @param request
	 * @param key
	 * @return
	 */
	public static Object getObjSession(HttpServletRequest request,String key)
	{
		return getSession(request).getAttribute(key);
	}
	
	
}
