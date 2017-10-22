package com.jackzhang.mall.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.jackzhang.mall.dao.UserDAO;
import com.jackzhang.mall.util.Page;
import com.jackzhang.mall.util.PageHelp;
import com.jackzhang.mall.vo.User;

/**
 * �û�SERVICE
 * @author jackzhang
 *
 */
public class UserService {
	
	private UserDAO userDAO = new UserDAO();
	
	public int add(User user)
	{
		return userDAO.add(user);
	}
	
	public int del(String id)
	{
		return userDAO.del(id);
	}
	
	public int upd(User user)
	{
		return userDAO.upd(user);
	}

	/**
	 * �û���¼
	 * @param name
	 * @param password
	 * @return
	 */
	public User login(String name,String password)
	{
		return userDAO.login(name, password);
	}
	
	/**
	 * ��ѯ�����û�
	 * @param user_id
	 * @return
	 */
	public User select(String user_id)
	{
		return userDAO.select(user_id);
	}
	
	/**
	 * ��ѯ������
	 * @return
	 */
	public int count()
	{
		return userDAO.count();
	}
	
	/**
	 * page����
	 * @param request
	 * @param count
	 * @return
	 */
	public Page getPage(HttpServletRequest request)
	{
		//���PAGE����
		return PageHelp.getPage(request, count(), 10); 
	}
	
	/**
	 * ��ҳ����
	 * @param page
	 * @return
	 */
	public List<User> pageList (Page page)
	{
		//��ʼ����
		int startRow = page.getStartRow();
		//ÿҳ������
		int pageSize = page.getPageSize();
		return userDAO.selectAll(startRow, pageSize);		
	}
	
	/**
	 * ע��ʱ��֤�û����Ƿ����
	 * @param name
	 * @param password
	 * @return
	 */
	public boolean authenticationName(String name)
	{
		User user = userDAO.authenticationName(name);
		if(user.getUser_id()==null)
		{
			return false;
		}
		return true;
	}
}
