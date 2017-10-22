package com.jackzhang.mall.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.jackzhang.mall.dao.UserDAO;
import com.jackzhang.mall.util.Page;
import com.jackzhang.mall.util.PageHelp;
import com.jackzhang.mall.vo.User;

/**
 * 用户SERVICE
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
	 * 用户登录
	 * @param name
	 * @param password
	 * @return
	 */
	public User login(String name,String password)
	{
		return userDAO.login(name, password);
	}
	
	/**
	 * 查询单个用户
	 * @param user_id
	 * @return
	 */
	public User select(String user_id)
	{
		return userDAO.select(user_id);
	}
	
	/**
	 * 查询总条数
	 * @return
	 */
	public int count()
	{
		return userDAO.count();
	}
	
	/**
	 * page对象
	 * @param request
	 * @param count
	 * @return
	 */
	public Page getPage(HttpServletRequest request)
	{
		//获得PAGE对象
		return PageHelp.getPage(request, count(), 10); 
	}
	
	/**
	 * 分页集合
	 * @param page
	 * @return
	 */
	public List<User> pageList (Page page)
	{
		//开始行数
		int startRow = page.getStartRow();
		//每页多少条
		int pageSize = page.getPageSize();
		return userDAO.selectAll(startRow, pageSize);		
	}
	
	/**
	 * 注册时验证用户名是否存在
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
