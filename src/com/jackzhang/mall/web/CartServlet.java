package com.jackzhang.mall.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jackzhang.mall.service.GoodsService;
import com.jackzhang.mall.service.TypeService;
import com.jackzhang.mall.util.AjaxOut;
import com.jackzhang.mall.util.SessionUtil;
import com.jackzhang.mall.vo.Goods;
import com.jackzhang.mall.vo.Type;

/**
 * ���ﳵServlet
 * @author jackzhang
 *
 */
public class CartServlet extends HttpServlet {

	private static final long serialVersionUID = -8748944755897978092L;
	
	private GoodsService goodsService = new GoodsService();
	
	private TypeService typeService = new TypeService();

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String method = request.getParameter("method");
		if(method!=null)
		{
			if("add".equals(method))
			{
				add(request, response);
			}else if("select".equals(method))
			{
				select(request, response);
			}else if("upd".equals(method))
			{
				upd(request, response);
			}else if("del".equals(method)){
				del(request, response);
			}
		}
	}
	/**
	 * ɾ�����ﳵ��ĳһ��Ʒ
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	public void del(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {		
		String goods_id = request.getParameter("id");
		//��ù��ﳵ
		Map<String, Map<Goods, Integer>> gwc = (Map<String, Map<Goods, Integer>>) SessionUtil.getObjSession(request, "gwc");
		Map<Goods, Integer> tempMap =gwc.get(goods_id);
		if(tempMap!=null)
		{
			gwc.remove(goods_id);
		}
		SessionUtil.setObjSession(request, "gwc", gwc);
		addType(request);
		request.getRequestDispatcher("cart/shoppingcar.jsp").forward(request, response);
	}
	/**
	 * �޸Ĺ��ﳵ
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	public void upd(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {		
		//���Ҫ�޸ĵ���ƷID
		String goods_id = request.getParameter("id");
		//���Ҫ�޸ĵ���Ʒ����
		int no = Integer.valueOf(request.getParameter("no"));
		//��ù��ﳵ
		Map<String, Map<Goods, Integer>> gwc =(Map<String, Map<Goods, Integer>>) SessionUtil.getObjSession(request, "gwc");
		//����ڲ�map
		Map<Goods, Integer> tempMap =gwc.get(goods_id);
		if(tempMap!=null)
		{	
			 	Iterator iter = tempMap.entrySet().iterator();
			 	if (iter.hasNext()) {
			     Map.Entry entry = (Map.Entry) iter.next();
			     Goods key = (Goods) entry.getKey();
			     tempMap.put(key, no);
			 	}
		}
		gwc.put(goods_id, tempMap);
		SessionUtil.setObjSession(request, "gwc", gwc);
		addType(request);
		request.getRequestDispatcher("cart/shoppingcar.jsp").forward(request, response);
		
	}
	/**
	 * �����ﳵ�����Ʒ
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	public String add(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		try {
			//��ù��ﳵ
			Map<String, Map<Goods, Integer>> gwc = (Map<String, Map<Goods, Integer>>) SessionUtil.getObjSession(request, "gwc");
			if(gwc==null)
			{
				//����һ���յĹ��ﳵ
				gwc = new HashMap<String, Map<Goods, Integer>>();
				SessionUtil.setObjSession(request, "gwc", gwc);
			}
			String goods_id = request.getParameter("goods_id");
			Goods goods = goodsService.select(goods_id);
			//��MAP����
			Map<Goods, Integer> tempMap = new HashMap<Goods, Integer>();
			if(gwc.size()<=0)
			{
				tempMap.put(goods, 1);
			}
			else
			{
				tempMap =gwc.get(goods_id);
				 if(tempMap!=null)
				 {		
					 Iterator iter = tempMap.entrySet().iterator();
					 if (iter.hasNext()) {
					     Map.Entry entry = (Map.Entry) iter.next();
					     Goods key = (Goods) entry.getKey();
					     int val = Integer.valueOf(entry.getValue().toString());
					     val++;
					     tempMap.put(key, val);
					 } 
				 }
				 else
				 {
					 tempMap = new HashMap<Goods, Integer>();
					 tempMap.put(goods, 1);
				 }
			}
			gwc.put(goods_id, tempMap);
			SessionUtil.setObjSession(request, "gwc", gwc);
			AjaxOut.out(response, "ok");
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void select(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {		
		addType(request);
		request.getRequestDispatcher("cart/shoppingcar.jsp").forward(request, response);
	}
	
	/**
	 * ��ȡ���
	 * @param request
	 */
	public void addType(HttpServletRequest request)
	{
		List<Type> typeList =  typeService.selectByFid("0");
		request.setAttribute("typeList", typeList);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
