package com.jackzhang.mall.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import com.jackzhang.mall.dao.CommentsDAO;
import com.jackzhang.mall.dao.GoodsDAO;
import com.jackzhang.mall.dao.TypeDAO;
import com.jackzhang.mall.util.Page;
import com.jackzhang.mall.util.PageHelp;
import com.jackzhang.mall.util.PinyinUtil;
import com.jackzhang.mall.vo.Comments;
import com.jackzhang.mall.vo.Goods;
import com.jackzhang.mall.vo.GoodsAdmin;
import com.jackzhang.mall.vo.Type;

/**
 * 商品SERVICE
 * @author jackzhang
 *
 */
public class GoodsService 
{
	private GoodsDAO goodsDAO = new GoodsDAO();
	
	private TypeDAO typeDAO = new TypeDAO();

	private CommentsDAO commentsDAO = new CommentsDAO();
	
	public int add(Goods goods)
	{
		return goodsDAO.add(goods);
	}
	
	public int del(String id)
	{
		return goodsDAO.del(id);
	}
	
	public int upd(Goods goods)
	{
		return goodsDAO.upd(goods);
	}
	/**
	 * 后台分页
	 * @param page
	 * @return
	 */
	public List<Goods> pageAdmin(Page page)
	{
		//开始行数
		int startRow = page.getStartRow();
		//每页多少条
		int pageSize = page.getPageSize();
		List<Goods> list = goodsDAO.selectAll(startRow, pageSize);
		for (Goods goods : list) {
			Type type = typeDAO.select(goods.getType().getType_id());
			goods.setType(type);
		}
		return list;
	}
	
	/**
	 * 用户名模糊查询
	 * @param name
	 * @return
	 */
	public List<Goods> selectByFuzzy(String name)
	{
		return goodsDAO.selectByFuzzy(name);
	}
	
	
	/**
	 * 获得查看商品分页集合
	 * @param page
	 * @param type_id
	 * @param sort_id
	 * @return
	 */
	public List<Goods> pageList(Page page,String type_id,String sort_id)
	{
		//开始行数
		int startRow = page.getStartRow();
		//每页多少条
		int pageSize = page.getPageSize();
		
		List<Goods> list = null;
		Type type = typeDAO.select(type_id);
		//是否是顶级类别（FID=0）
		if(type.getFid().equals("0"))
		{
			if(sort_id.equals("2"))//按照价格排序(低到高)
			{
				 list =goodsDAO.pageTopGoods(type_id,"price", startRow, pageSize, false);
			}
			else if(sort_id.equals("3"))//按照价格排序(高到底)
			{
				list =goodsDAO.pageTopGoods(type_id,"price", startRow, pageSize, true);
			}
			else
			{
				list = goodsDAO.pageTopGoods(type_id,"createtime", startRow, pageSize, true);
			}
		}
		else//按类别排序
		{
			if(sort_id.equals("2"))//按照价格排序(低到高)
			{
				list = goodsDAO.pageByType_id(type_id, "price", startRow, pageSize, false);
			}
			else if(sort_id.equals("3"))//按照价格排序(高到底)
			{
				list = goodsDAO.pageByType_id(type_id, "price", startRow, pageSize, true);
			}
			else
			{
				list = goodsDAO.pageByType_id(type_id, "createtime", startRow, pageSize, true);
			}
		}
		return list;
	}
	/**
	 * 商品的全查或根据商品名称查询
	 * @param page
	 * @param goodsName
	 * @return
	 */
	public List<Goods> pageListByName(Page page,String goodsName,String sort_id)
	{
		//开始行数
		int startRow = page.getStartRow();
		//每页多少条
		int pageSize = page.getPageSize();
		
		List<Goods> list = null;
		
		if(goodsName==null)
		{
			if(sort_id.equals("2"))//按照价格排序(低到高)
			{
				list = goodsDAO.selectAll( "price",startRow, pageSize,false);
			}
			else if(sort_id.equals("3"))//按照价格排序(高到底)
			{
				list = goodsDAO.selectAll( "price",startRow, pageSize,true);
			}
			else
			{
				list = goodsDAO.selectAll("createtime", startRow, pageSize, true);
			}
		}
		else
		{
			if(sort_id.equals("2"))//按照价格排序(低到高)
			{
				list = goodsDAO.selectByName("price",goodsName,startRow, pageSize,false);
			}
			else if(sort_id.equals("3"))//按照价格排序(高到底)
			{
				list = goodsDAO.selectByName("price",goodsName,startRow, pageSize,true);
			}
			else
			{
				list = goodsDAO.selectByName("createtime",goodsName, startRow, pageSize, true);
			}
		}
		return list;
	}
	/**
	 * page对象
	 * @param request
	 * @param count
	 * @return
	 */
	public Page getPage(HttpServletRequest request,int count)
	{
		//获得PAGE对象
		return PageHelp.getPage(request, count, 10); 
	}
	/**
	 * 顶级类别的总条数
	 * @return
	 */
	public int countByTop(String type_id)
	{
		return goodsDAO.countByTop(type_id);
	}
	
	/**
	 * 查询某个类别的商品总条数
	 * @param type_id
	 * @return
	 */
	public int countByType(String type_id)
	{
		return goodsDAO.countByType(type_id);
	}
	
	public String suggestString(String name) {
		List<Goods> list = goodsDAO.selectAll();
		Set<String> tempSet = new HashSet<String>();
		boolean flag = false;// 标示
		for (Goods g : list) {
			// 获得循环列表中的name
			String contactName = g.getName();
			// 1-------------全拼判断---------------
			// 获得集合name的全拼
			String contactNameASCII = PinyinUtil.getPinYin(contactName)
					.toLowerCase();
			String contactNamePINYIN = PinyinUtil.getPinYin(contactName);
			// 通过全拼获得集合name的ASCII码
			contactNameASCII = PinyinUtil.getCnASCII(contactNameASCII);
			// 获得用户输入的字符全拼
			String tempASCll = PinyinUtil.getPinYin(name);
			// 通过全拼获得用户输入字符的ASCII码
			tempASCll = PinyinUtil.getCnASCII(tempASCll);
			// 2-------------首字母判断-------------
			// 获得集合name的首字母
			String initialASCII = PinyinUtil.getPinYinHeadChar(contactName);
			// 通过获得的首字母获得集合name的ASCII码
			initialASCII = PinyinUtil.getCnASCII(initialASCII);
			// 如果集合name的ASCII长度大于用户输入字符的ASCII长度时
			if (contactNameASCII.length() > tempASCll.length()) {
				contactNameASCII = contactNameASCII.substring(0, tempASCll
						.length());// 截取和用户输入一样长度的ASCII码
			}
			// 如果集合name的首字母的ASCII大于用户输入字符的ASCII长度时
			if (initialASCII.length() > tempASCll.length()) {
				initialASCII = initialASCII.substring(0, tempASCll.length());// 截取和用户输入一样长度首字母的ASCII码
			}
			// 如果集合name的ASCII等于用户输入的ASCII或者集合name首字母的ASCII等于用户输入的ASCII
			flag = (contactNameASCII.equals(tempASCll) || initialASCII
					.equals(tempASCll)) ? true : false;
			if (flag) {
				tempSet.add(contactName + "　　" + contactNamePINYIN);
			}
		}
		String output = "";
		for (String s : tempSet) {
			output += s + "\n";
		}
		return output;
	}
	/**
	 * 查询单个商品
	 * @param goods_id
	 * @return
	 */
	public Goods select(String goods_id)
	{
		Goods goods = goodsDAO.select(goods_id);
		Type type = goods.getType();
		type = typeDAO.select(type.getType_id());
		goods.setType(type);
		return goods;
	}
	/**
	 * 通过商品对象获得类型对象
	 * @param goods
	 * @return
	 */
	public Type getType(Goods goods)
	{
		String type_id = goods.getType().getType_id();
		return typeDAO.select(type_id);
	}
	/**
	 * 获得该商品下评论总条数
	 * @param goods_id
	 * @return
	 */
	public int getGoodsCommentsCount(String goods_id)
	{
		return commentsDAO.getCountByGoods_id(goods_id);
	}
	/**
	 * 获得评论分页集合
	 * @param page
	 * @return
	 */
	public List<Comments> getCommentsPageList(Page page,String goods_id)
	{
		//开始行数
		int startRow = page.getStartRow();
		//每页多少条
		int pageSize = page.getPageSize();
		return commentsDAO.getGoodsCommentsPageList(goods_id, startRow, pageSize);
	}
	
	/**
	 * 查询单个商品的中条数
	 * @param name
	 * @return
	 */
	public int singleCount(String name)
	{
		return goodsDAO.singleCount(name);
	}
	
	/**
	 * 查询所有商品的总条数
	 * @return
	 */
	public int count()
	{
		return goodsDAO.count();
	}
	
	/**
	 * 后台根据某一用户查看买了多少商品
	 * @param user_id
	 * @return
	 */
	public List<GoodsAdmin> selectByUser_id (String user_id)
	{
		return goodsDAO.selectByUser_id(user_id);
	}
	
	/**
	 * 后台根据某一订单查看买了多少商品
	 * @return
	 */
	public List<GoodsAdmin> selectByOrder_id(String order_id)
	{
		return goodsDAO.selectByOrder_id(order_id);
	}
	
}
