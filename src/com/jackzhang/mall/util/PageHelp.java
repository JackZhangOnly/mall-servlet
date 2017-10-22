package com.jackzhang.mall.util;

import javax.servlet.http.HttpServletRequest;

/**
 * ��ҳ������
 * @author jackzhang
 *
 */
public class PageHelp {
	
	public static Page getPage(HttpServletRequest request, int totalRows,int pageSize) {
		Page page = new Page(totalRows,pageSize);
		// ��õ�ǰҳ��
		String pagecount = request.getParameter("pageCount");

		// �����ǰҳ��Ϊ�գ���ʾΪ�״β�ѯ��ҳ
		// �����Ϊ�գ���ˢ��page�������뵱ǰҳ�ŵ���Ϣ
		if (pagecount != null) {
			page.refresh(Integer.parseInt(pagecount));
		}

		// ��ȡ��ǰִ�еķ�������ҳ��ǰһҳ����һҳ��βҳ��
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
