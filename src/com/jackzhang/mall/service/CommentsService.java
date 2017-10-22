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
 * 评论SERVICE
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
	 * 通过某一商品，对其评论分页
	 * @param page
	 * @param goods_id
	 * @return
	 */
	public List<Comments> getGoodsCommentsPageList(Page page,String goods_id)
	{
		//开始行数
		int startRow = page.getStartRow();
		//每页多少条
		int pageSize = page.getPageSize();
		//分页集合
		return commentsDAO.getGoodsCommentsPageList(goods_id, startRow, pageSize);
	}
	
	/**
	 * 后台page用到的评论总条数
	 * @return
	 */
	public int countAdmin()
	{
		return commentsDAO.countAdmin();
	}
	
	/**
	 * 用户中心page用到的评论总条数
	 * @return
	 */
	public int countUser(String user_id)
	{
		return commentsDAO.countUser(user_id);
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
	 * 后台分页集合
	 * @return
	 */
	public List<Comments> pageListAdmin(Page page)
	{
		//开始行数
		int startRow = page.getStartRow();
		//每页多少条
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
	 * 用户中心分页集合
	 * @param page
	 * @return
	 */
	public List<Comments> pageListUser(Page page,String user_id)
	{
		//开始行数
		int startRow = page.getStartRow();
		//每页多少条
		int pageSize = page.getPageSize();
		List<Comments> list = commentsDAO.selectByUser(user_id, startRow, pageSize);
		for (Comments comments : list) {
			Goods goods = goodsDAO.select(comments.getGoods().getGoods_id());
			comments.setGoods(goods);
		}
		
		return list;
	}
}
