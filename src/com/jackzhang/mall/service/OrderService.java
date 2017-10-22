package com.jackzhang.mall.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.jackzhang.mall.dao.OrderDAO;
import com.jackzhang.mall.util.Page;
import com.jackzhang.mall.util.PageHelp;
import com.jackzhang.mall.vo.Order;
import com.jackzhang.mall.vo.User;

/**
 * 订单SERVICE
 * @author jackzhang
 *
 */
public class OrderService 
{
	private OrderDAO orderDAO = new OrderDAO();
	private UserService userService = new UserService();
	
	public int add(Order order)
	{
		return orderDAO.add(order);
	}
	
	public int del(String id)
	{
		return orderDAO.del(id);
	}
	
	public int upd(Order order)
	{
		return orderDAO.upd(order);
	}
	
	public List<Order> selectAll()
	{
		return orderDAO.selectAll();
	}
	/**
	 * 查询单个订单
	 * @param order_id
	 * @return
	 */
	public Order select(String order_id)
	{
		Order order = orderDAO.select(order_id);
		User user = order.getUser();
		user = userService.select(user.getUser_id());
		order.setUser(user);
		return order;
	}
	
	/**
	 * 获得等待付费的订单个数
	 * @return
	 */
	public int toWaitForPayment(String user_id)
	{	
		return orderDAO.toWaitForPayment(user_id);
	}
	
	/**
	 * 获得等待确认的订单个数
	 * @return
	 */
	public int awaitingConfirmation(String user_id)
	{
		return orderDAO.awaitingConfirmation(user_id);
	}
	
	/**
	 * 添加关系表
	 * @param order_id
	 * @param goods_id
	 * @param no
	 */
	public void addgxb(String order_id,String goods_id)
	{
		orderDAO.addgxb(order_id, goods_id);
	}
	
	/**
	 * 后台分页查询未发货的订单
	 * @return
	 */
	public List<Order> selectunOrderAdmin(Page page)
	{
		//开始行数
		int startRow = page.getStartRow();
		//每页多少条
		int pageSize = page.getPageSize();
		//分页集合
		return orderDAO.selectunOrderAdmin(startRow, pageSize);
	}
	
	/**
	 * 后台未发货订单个数
	 * @return
	 */
	public int unOrderCountAdmin()
	{
		return orderDAO.unOrderCountAdmin();
	}
	
	/**
	 * 后台分页查询已发货的订单
	 * @return
	 */
	public List<Order> selectOrderAdmin(Page page)
	{
		//开始行数
		int startRow = page.getStartRow();
		//每页多少条
		int pageSize = page.getPageSize();
		//分页集合
		return orderDAO.selectOrderAdmin(startRow, pageSize);
	}
	
	/**
	 * 后台已发货订单个数
	 * @return
	 */
	public int OrderCountAdmin()
	{
		return orderDAO.OrderCountAdmin();
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
	 * 后台订单详细查询
	 * @param order_id
	 * @return
	 */
	public Order selectOrderAdmin(String order_id)
	{
		Order order = orderDAO.select(order_id);
		User user = userService.select(order.getUser().getUser_id());
		order.setUser(user);
		return order;
	}
	
	/**
	 * 用户中心获得未付款的订单集合
	 * @param user_id
	 * @return
	 */
	public List<Order> selectToWaitForPaymentUser(String user_id)
	{	
		return orderDAO.selectToWaitForPaymentUser(user_id);
	}
}
