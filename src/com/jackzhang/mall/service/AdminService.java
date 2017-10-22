package com.jackzhang.mall.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.jackzhang.mall.dao.AdminDAO;
import com.jackzhang.mall.util.Page;
import com.jackzhang.mall.util.PageHelp;
import com.jackzhang.mall.vo.Admin;

/**
 * ����ԱService
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
	 * ����ID��ѯ��������Ա
	 * @param admin_id
	 * @return
	 */
	public Admin select(String admin_id)
	{
		return adminDAO.select(admin_id);
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
	
	/***
	 * ��̨��ҳ����
	 * @param page
	 * @return
	 */
	public List<Admin> selectByPageAdmin(Page page)
	{
		//��ʼ����
		int startRow = page.getStartRow();
		//ÿҳ������
		int pageSize = page.getPageSize();
		return adminDAO.selectByPageAdmin(startRow, pageSize);
	}
	
	/**
	 * ��̨��ѯ����Ա������
	 * @return
	 */
	public int getCountAdmin()
	{
		return adminDAO.getCountAdmin();
	}
	
	/**
	 * ��̨����Ա��¼��֤
	 * @param name
	 * @param password
	 * @return
	 */
	public Admin loginByNameAndPasswordAdmin(String name,String password)
	{
		return adminDAO.loginByNameAndPasswordAdmin(name, password);
	}
	
	/**
	 * ��ӹ���Ա���û�����֤
	 * @param name
	 * @return
	 */
	public boolean authenticationAdminName(String name)
	{
		return  adminDAO.authenticationAdminName(name);
	}
}
