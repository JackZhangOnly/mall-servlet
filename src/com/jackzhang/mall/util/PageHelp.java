package com.jackzhang.mall.util;

import javax.servlet.http.HttpServletRequest;

/**
 * 分页帮助类
 * @author jackzhang
 *
 */
public class PageHelp {
	
	public static Page getPage(HttpServletRequest request, int totalRows,int pageSize) {
		Page page = new Page(totalRows,pageSize);
		// 获得当前页号
		String pagecount = request.getParameter("pageCount");

		// 如果当前页号为空，表示为首次查询该页
		// 如果不为空，则刷新page对象，输入当前页号等信息
		if (pagecount != null) {
			page.refresh(Integer.parseInt(pagecount));
		}

		// 获取当前执行的方法，首页，前一页，后一页，尾页。
		String pagerMethod = request.getParameter("pageMethod");

		if (pagerMethod != null) {
			if (pagerMethod.equals("first")) {
				page.first();
			} else if (pagerMethod.equals("previous")) {
				page.previous();
			} else if (pagerMethod.equals("next")) {
				page.next();
			} else if (pagerMethod.equals("last")) {
				page.last();
			}
		}

		return page;
	}
}
