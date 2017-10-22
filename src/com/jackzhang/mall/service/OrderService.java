package com.jackzhang.mall.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.jackzhang.mall.dao.OrderDAO;
import com.jackzhang.mall.util.Page;
import com.jackzhang.mall.util.PageHelp;
import com.jackzhang.mall.vo.Order;
import com.jackzhang.mall.vo.User;

/**
 * ����SERVICE
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
	 * ��ѯ��������
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
	 * ��õȴ����ѵĶ�������
	 * @return
	 */
	public int toWaitForPayment(String user_id)
	{	
		return orderDAO.toWaitForPayment(user_id);
	}
	
	/**
	 * ��õȴ�ȷ�ϵĶ�������
	 * @return
	 */
	public int awaitingConfirmation(String user_id)
	{
		return orderDAO.awaitingConfirmation(user_id);
	}
	
	/**
	 * ��ӹ�ϵ��
	 * @param order_id
	 * @param goods_id
	 * @param no
	 */
	public void addgxb(String order_id,String goods_id)
	{
		orderDAO.addgxb(order_id, goods_id);
	}
	
	/**
	 * ��̨��ҳ��ѯδ�����Ķ���
	 * @return
	 */
	public List<Order> selectunOrderAdmin(Page page)
	{
		//��ʼ����
		int startRow = page.getStartRow();
		//ÿҳ������
		int pageSize = page.getPageSize();
		//��ҳ����
		return orderDAO.selectunOrderAdmin(startRow, pageSize);
	}
	
	/**
	 * ��̨δ������������
	 * @return
	 */
	public int unOrderCountAdmin()
	{
		return orderDAO.unOrderCountAdmin();
	}
	
	/**
	 * ��̨��ҳ��ѯ�ѷ����Ķ���
	 * @return
	 */
	public List<Order> selectOrderAdmin(Page page)
	{
		//��ʼ����
		int startRow = page.getStartRow();
		//ÿҳ������
		int pageSize = page.getPageSize();
		//��ҳ����
		return orderDAO.selectOrderAdmin(startRow, pageSize);
	}
	
	/**
	 * ��̨�ѷ�����������
	 * @return
	 */
	public int OrderCountAdmin()
	{
		return orderDAO.OrderCountAdmin();
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
	 * ��̨������ϸ��ѯ
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
	 * �û����Ļ��δ����Ķ�������
	 * @param user_id
	 * @return
	 */
	public List<Order> selectToWaitForPaymentUser(String user_id)
	{	
		return orderDAO.selectToWaitForPaymentUser(user_id);
	}
}
