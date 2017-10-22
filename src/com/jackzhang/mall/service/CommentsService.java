package com.jackzhang.mall.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.jackzhang.mall.dao.CommentsDAO;
import com.jackzhang.mall.dao.GoodsDAO;
import com.jackzhang.mall.dao.UserDAO;
import com.jackzhang.mall.util.Page;
import com.jackzhang.mall.util.PageHelp;
import com.jackzhang.mall.vo.Comments;
import com.jackzhang.mall.vo.Goods;
import com.jackzhang.mall.vo.User;

/**
 * ����SERVICE
 * @author jackzhang
 *
 */
public class CommentsService 
{
	private CommentsDAO commentsDAO = new CommentsDAO();
	
	private UserDAO userDAO = new UserDAO();
	
	private GoodsDAO goodsDAO = new GoodsDAO();
	
	public int add(Comments comments)
	{
		return commentsDAO.add(comments);
	}
	
	public int del(String id)
	{
		return commentsDAO.del(id);
	}
	
	public int upd(Comments comments)
	{
		return commentsDAO.upd(comments);
	}
	
	
	/**
	 * ͨ��ĳһ��Ʒ���������۷�ҳ
	 * @param page
	 * @param goods_id
	 * @return
	 */
	public List<Comments> getGoodsCommentsPageList(Page page,String goods_id)
	{
		//��ʼ����
		int startRow = page.getStartRow();
		//ÿҳ������
		int pageSize = page.getPageSize();
		//��ҳ����
		return commentsDAO.getGoodsCommentsPageList(goods_id, startRow, pageSize);
	}
	
	/**
	 * ��̨page�õ�������������
	 * @return
	 */
	public int countAdmin()
	{
		return commentsDAO.countAdmin();
	}
	
	/**
	 * �û�����page�õ�������������
	 * @return
	 */
	public int countUser(String user_id)
	{
		return commentsDAO.countUser(user_id);
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
	 * ��̨��ҳ����
	 * @return
	 */
	public List<Comments> pageListAdmin(Page page)
	{
		//��ʼ����
		int startRow = page.getStartRow();
		//ÿҳ������
		int pageSize = page.getPageSize();
		List<Comments> list = commentsDAO.selectAll(startRow, pageSize);		
		for (Comments comments : list) {
			User user = userDAO.select(comments.getUser().getUser_id());
			Goods goods = goodsDAO.select(comments.getGoods().getGoods_id());
			comments.setUser(user);
			comments.setGoods(goods);
		}
		return list;
	}
	/**
	 * �û����ķ�ҳ����
	 * @param page
	 * @return
	 */
	public List<Comments> pageListUser(Page page,String user_id)
	{
		//��ʼ����
		int startRow = page.getStartRow();
		//ÿҳ������
		int pageSize = page.getPageSize();
		List<Comments> list = commentsDAO.selectByUser(user_id, startRow, pageSize);
		for (Comments comments : list) {
			Goods goods = goodsDAO.select(comments.getGoods().getGoods_id());
			comments.setGoods(goods);
		}
		
		return list;
	}
}
