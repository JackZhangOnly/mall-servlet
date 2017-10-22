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
 * ��ƷSERVICE
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
	 * ��̨��ҳ
	 * @param page
	 * @return
	 */
	public List<Goods> pageAdmin(Page page)
	{
		//��ʼ����
		int startRow = page.getStartRow();
		//ÿҳ������
		int pageSize = page.getPageSize();
		List<Goods> list = goodsDAO.selectAll(startRow, pageSize);
		for (Goods goods : list) {
			Type type = typeDAO.select(goods.getType().getType_id());
			goods.setType(type);
		}
		return list;
	}
	
	/**
	 * �û���ģ����ѯ
	 * @param name
	 * @return
	 */
	public List<Goods> selectByFuzzy(String name)
	{
		return goodsDAO.selectByFuzzy(name);
	}
	
	
	/**
	 * ��ò鿴��Ʒ��ҳ����
	 * @param page
	 * @param type_id
	 * @param sort_id
	 * @return
	 */
	public List<Goods> pageList(Page page,String type_id,String sort_id)
	{
		//��ʼ����
		int startRow = page.getStartRow();
		//ÿҳ������
		int pageSize = page.getPageSize();
		
		List<Goods> list = null;
		Type type = typeDAO.select(type_id);
		//�Ƿ��Ƕ������FID=0��
		if(type.getFid().equals("0"))
		{
			if(sort_id.equals("2"))//���ռ۸�����(�͵���)
			{
				 list =goodsDAO.pageTopGoods(type_id,"price", startRow, pageSize, false);
			}
			else if(sort_id.equals("3"))//���ռ۸�����(�ߵ���)
			{
				list =goodsDAO.pageTopGoods(type_id,"price", startRow, pageSize, true);
			}
			else
			{
				list = goodsDAO.pageTopGoods(type_id,"createtime", startRow, pageSize, true);
			}
		}
		else//���������
		{
			if(sort_id.equals("2"))//���ռ۸�����(�͵���)
			{
				list = goodsDAO.pageByType_id(type_id, "price", startRow, pageSize, false);
			}
			else if(sort_id.equals("3"))//���ռ۸�����(�ߵ���)
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
	 * ��Ʒ��ȫ��������Ʒ���Ʋ�ѯ
	 * @param page
	 * @param goodsName
	 * @return
	 */
	public List<Goods> pageListByName(Page page,String goodsName,String sort_id)
	{
		//��ʼ����
		int startRow = page.getStartRow();
		//ÿҳ������
		int pageSize = page.getPageSize();
		
		List<Goods> list = null;
		
		if(goodsName==null)
		{
			if(sort_id.equals("2"))//���ռ۸�����(�͵���)
			{
				list = goodsDAO.selectAll( "price",startRow, pageSize,false);
			}
			else if(sort_id.equals("3"))//���ռ۸�����(�ߵ���)
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
			if(sort_id.equals("2"))//���ռ۸�����(�͵���)
			{
				list = goodsDAO.selectByName("price",goodsName,startRow, pageSize,false);
			}
			else if(sort_id.equals("3"))//���ռ۸�����(�ߵ���)
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
	 * page����
	 * @param request
	 * @param count
	 * @return
	 */
	public Page getPage(HttpServletRequest request,int count)
	{
		//���PAGE����
		return PageHelp.getPage(request, count, 10); 
	}
	/**
	 * ��������������
	 * @return
	 */
	public int countByTop(String type_id)
	{
		return goodsDAO.countByTop(type_id);
	}
	
	/**
	 * ��ѯĳ��������Ʒ������
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
		boolean flag = false;// ��ʾ
		for (Goods g : list) {
			// ���ѭ���б��е�name
			String contactName = g.getName();
			// 1-------------ȫƴ�ж�---------------
			// ��ü���name��ȫƴ
			String contactNameASCII = PinyinUtil.getPinYin(contactName)
					.toLowerCase();
			String contactNamePINYIN = PinyinUtil.getPinYin(contactName);
			// ͨ��ȫƴ��ü���name��ASCII��
			contactNameASCII = PinyinUtil.getCnASCII(contactNameASCII);
			// ����û�������ַ�ȫƴ
			String tempASCll = PinyinUtil.getPinYin(name);
			// ͨ��ȫƴ����û������ַ���ASCII��
			tempASCll = PinyinUtil.getCnASCII(tempASCll);
			// 2-------------����ĸ�ж�-------------
			// ��ü���name������ĸ
			String initialASCII = PinyinUtil.getPinYinHeadChar(contactName);
			// ͨ����õ�����ĸ��ü���name��ASCII��
			initialASCII = PinyinUtil.getCnASCII(initialASCII);
			// �������name��ASCII���ȴ����û������ַ���ASCII����ʱ
			if (contactNameASCII.length() > tempASCll.length()) {
				contactNameASCII = contactNameASCII.substring(0, tempASCll
						.length());// ��ȡ���û�����һ�����ȵ�ASCII��
			}
			// �������name������ĸ��ASCII�����û������ַ���ASCII����ʱ
			if (initialASCII.length() > tempASCll.length()) {
				initialASCII = initialASCII.substring(0, tempASCll.length());// ��ȡ���û�����һ����������ĸ��ASCII��
			}
			// �������name��ASCII�����û������ASCII���߼���name����ĸ��ASCII�����û������ASCII
			flag = (contactNameASCII.equals(tempASCll) || initialASCII
					.equals(tempASCll)) ? true : false;
			if (flag) {
				tempSet.add(contactName + "����" + contactNamePINYIN);
			}
		}
		String output = "";
		for (String s : tempSet) {
			output += s + "\n";
		}
		return output;
	}
	/**
	 * ��ѯ������Ʒ
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
	 * ͨ����Ʒ���������Ͷ���
	 * @param goods
	 * @return
	 */
	public Type getType(Goods goods)
	{
		String type_id = goods.getType().getType_id();
		return typeDAO.select(type_id);
	}
	/**
	 * ��ø���Ʒ������������
	 * @param goods_id
	 * @return
	 */
	public int getGoodsCommentsCount(String goods_id)
	{
		return commentsDAO.getCountByGoods_id(goods_id);
	}
	/**
	 * ������۷�ҳ����
	 * @param page
	 * @return
	 */
	public List<Comments> getCommentsPageList(Page page,String goods_id)
	{
		//��ʼ����
		int startRow = page.getStartRow();
		//ÿҳ������
		int pageSize = page.getPageSize();
		return commentsDAO.getGoodsCommentsPageList(goods_id, startRow, pageSize);
	}
	
	/**
	 * ��ѯ������Ʒ��������
	 * @param name
	 * @return
	 */
	public int singleCount(String name)
	{
		return goodsDAO.singleCount(name);
	}
	
	/**
	 * ��ѯ������Ʒ��������
	 * @return
	 */
	public int count()
	{
		return goodsDAO.count();
	}
	
	/**
	 * ��̨����ĳһ�û��鿴���˶�����Ʒ
	 * @param user_id
	 * @return
	 */
	public List<GoodsAdmin> selectByUser_id (String user_id)
	{
		return goodsDAO.selectByUser_id(user_id);
	}
	
	/**
	 * ��̨����ĳһ�����鿴���˶�����Ʒ
	 * @return
	 */
	public List<GoodsAdmin> selectByOrder_id(String order_id)
	{
		return goodsDAO.selectByOrder_id(order_id);
	}
	
}
