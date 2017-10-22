package com.jackzhang.mall.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.jackzhang.mall.dao.AdminDAO;
import com.jackzhang.mall.util.Page;
import com.jackzhang.mall.util.PageHelp;
import com.jackzhang.mall.vo.Admin;

/**
 * 管理员Service
 * @author jackzhang
 *
 */
public class AdminService {
	
	private AdminDAO adminDAO = new AdminDAO();
	
	public void add(Admin admin)
	{
		 adminDAO.add(admin);
	}
	
	public void del(String id)
	{
		adminDAO.del(id);
	}
	
	public void upd(Admin admin)
	{
		adminDAO.upd(admin);
	}
	
	/**
	 * 根据ID查询单个管理员
	 * @param admin_id
	 * @return
	 */
	public Admin select(String admin_id)
	{
		return adminDAO.select(admin_id);
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
	
	/***
	 * 后台分页集合
	 * @param page
	 * @return
	 */
	public List<Admin> selectByPageAdmin(Page page)
	{
		//开始行数
		int startRow = page.getStartRow();
		//每页多少条
		int pageSize = page.getPageSize();
		return adminDAO.selectByPageAdmin(startRow, pageSize);
	}
	
	/**
	 * 后台查询管理员总条数
	 * @return
	 */
	public int getCountAdmin()
	{
		return adminDAO.getCountAdmin();
	}
	
	/**
	 * 后台管理员登录验证
	 * @param name
	 * @param password
	 * @return
	 */
	public Admin loginByNameAndPasswordAdmin(String name,String password)
	{
		return adminDAO.loginByNameAndPasswordAdmin(name, password);
	}
	
	/**
	 * 添加管理员的用户名验证
	 * @param name
	 * @return
	 */
	public boolean authenticationAdminName(String name)
	{
		return  adminDAO.authenticationAdminName(name);
	}
}
