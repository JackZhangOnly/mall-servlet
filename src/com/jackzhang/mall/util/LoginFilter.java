package com.jackzhang.mall.util;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginFilter implements Filter {

	public void destroy() {
		// TODO Auto-generated method stub

	}

	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;

		// 请求的url
		String path = request.getServletPath();
		// 完全路径
		String basePath = request.getScheme() + "://" + request.getServerName()
				+ ":" + request.getServerPort() + request.getContextPath();
		// String temp = path.substring(path.lastIndexOf(".")+1);
		String method = request.getParameter("method");
		// 文件夹
		String folder = path.substring(0, path.lastIndexOf("/"));
		if (SessionUtil.getObjSession(request, "user") == null
				&& SessionUtil.getObjSession(request, "admin") != null) {
			if ((path.equals("/order.do") && method.equals("details"))
					|| (path.equals("/order.do") && method
							.equals("verifyAddresses"))
					|| (path.equals("/order.do") && method.equals("temp"))
					|| (path.equals("/order.do") && method
							.equals("selectOrderUser"))
					|| (path.equals("/user.do") && method.equals("main"))
					) {
				response.sendRedirect(basePath + "/user/login.jsp?error=user");
				return;
			} else {
				chain.doFilter(req, res);
				return;
			}
		} else if (SessionUtil.getObjSession(request, "user") != null
				&& SessionUtil.getObjSession(request, "admin") == null) {
			if (folder.equals("/admin")) {
				if (path.equals("/admin/adminlogin.jsp")) {
					chain.doFilter(req, res);
					return;
				}
				response.sendRedirect(basePath
						+ "/admin/adminlogin.jsp?error=admin");
				return;
			} else {
				chain.doFilter(req, res);
				return;
			}
		} else if (SessionUtil.getObjSession(request, "user") == null
				&& SessionUtil.getObjSession(request, "admin") == null) {
			if ((path.equals("/order.do") && method.equals("details"))
					|| (path.equals("/order.do") && method
							.equals("verifyAddresses"))
					|| (path.equals("/order.do") && method.equals("temp"))
					|| (path.equals("/order.do") && method
							.equals("selectOrderUser"))
					|| path.equals("/user/main.jsp") || folder.equals("/admin")
					|| (path.equals("/user.do") && method.equals("main"))
					||(path.equals("/goods.do") && method.equals("addComments"))) {
				if (path.equals("/admin/adminlogin.jsp")) {
					chain.doFilter(req, res);
					return;
				}
				boolean flag = false;
				flag = folder.equals("/admin") ? true : false;
				if (flag) {
					response.sendRedirect(basePath
							+ "/admin/adminlogin.jsp?error=admin");
					return;

				} else {
					response.sendRedirect(basePath
							+ "/user/login.jsp?error=user");
					return;
				}
			} else {
				chain.doFilter(req, res);
				return;
			}
		} else {
			chain.doFilter(req, res);
		}
		/*
		 * if(temp.equals("css")||temp.equals("js")||temp.equals("swf")||temp.equals
		 * ("png")||temp.equals("gif")||temp.equals("jpg")) {
		 * chain.doFilter(req, res); } else
		 * if(path.equals("/user/login.jsp")||path
		 * .equals("/admin/adminlogin.jsp"
		 * )||path.equals("/user/register.jsp"))//放行的jsp页面 { chain.doFilter(req,
		 * res); } else {
		 * if(path.equals("/user.do")||path.equals("/admin.do")||path
		 * .equals("/image.do"))//放行的方法 { if(method!=null) {
		 * if(method.equals("login"
		 * )||method.equals("loginByNameAndPasswordAdmin"
		 * )||method.equals("register")||method.equals("randletters")) {
		 * chain.doFilter(req, res); } else { perform(req, res, chain, request,
		 * response, basePath);
		 * 
		 * } }
		 * 
		 * } else { perform(req, res, chain, request, response, basePath); } }
		 */

	}

	/*
	 * private void perform(ServletRequest req, ServletResponse res, FilterChain
	 * chain, HttpServletRequest request, HttpServletResponse response, String
	 * basePath) throws IOException, ServletException {
	 * if(SessionUtil.getObjSession(request,
	 * "user")==null&&SessionUtil.getObjSession(request, "admin")==null) {
	 * response.sendRedirect(basePath +"/user/login.jsp?error"); } else {
	 * chain.doFilter(req, res); } }
	 */

	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub

	}

}
