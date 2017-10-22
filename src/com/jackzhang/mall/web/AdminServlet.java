package com.jackzhang.mall.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jackzhang.mall.service.AdminService;
import com.jackzhang.mall.util.AjaxOut;
import com.jackzhang.mall.util.KeyUtil;
import com.jackzhang.mall.util.Page;
import com.jackzhang.mall.util.SessionUtil;
import com.jackzhang.mall.vo.Admin;

public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 3941548145331853L;

	private AdminService adminService = new AdminService();
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String method = request.getParameter("method");
		if(method!=null)
		{
			if("selectPageAdmin".equals(method))
			{
				selectPageAdmin(request, response);
			}else if("loginByNameAndPasswordAdmin".equals(method))
			{
				loginByNameAndPasswordAdmin(request, response);
			}else if("updateStateAdmin".equals(method))
			{
				updateStateAdmin(request, response);
			}else if("updetePassword".equals(method))
			{
				updetePassword(request, response);
			}else if("addAdmin".equals(method))
			{
				addAdmin(request, response);
			}else if("outAdmin".equals(method))
			{
				outAdmin(request, response);
			}else if("authenticationAdminName".equals(method))
			{
				authenticationAdminName(request,response);
			}
		}
	}
	/**
	 * ��̨��ҳ
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void selectPageAdmin(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		String msg = request.getParameter("msg");
		if(msg!=null)
		{
			if(msg.equals("yes"))
			{
				request.setAttribute("msg", "��ӳɹ�");
			}
		}
		int count = adminService.getCountAdmin();
		Page page = adminService.getPage(request, count);
		List<Admin> list = adminService.selectByPageAdmin(page);
		request.setAttribute("page", page);
		request.setAttribute("adminList", list);
		request.getRequestDispatcher("admin/adminlist.jsp").forward(request, response);
	}
	/**
	 * ��̨����Ա��¼��֤
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void loginByNameAndPasswordAdmin(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		String name = request.getParameter("adminName");
		String password = request.getParameter("password");
		Admin admin = adminService.loginByNameAndPasswordAdmin(name, password);
		if(admin.getAdmin_id()==null || admin.getAdmin_id().equals(""))
		{
			request.setAttribute("msg", "�û�����������������µ�¼");
			request.getRequestDispatcher("admin/adminlogin.jsp").forward(request, response);
		}
		else
		{
			if(admin.isIsfreeze())
			{
				request.setAttribute("msg", "�˻��ѱ����ᣬ�������Ա��ϵ");
				request.getRequestDispatcher("admin/adminlogin.jsp").forward(request, response);
			}
			else
			{
				SessionUtil.setObjSession(request, "admin", admin);
				response.sendRedirect("admin/main.jsp");
			}
		}
	}
	/**
	 * ����״̬
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void updateStateAdmin(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		String admin_id=request.getParameter("admin_id");
		Admin admin =  adminService.select(admin_id);
		if(admin.isIsfreeze())
		{
			admin.setIsfreeze(false);
		}
		else
		{
			admin.setIsfreeze(true);
		}
		adminService.upd(admin);
		selectPageAdmin(request, response);
	}
	/**
	 * �޸Ĺ���Ա����
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void updetePassword(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		String oldpwd = request.getParameter("oldpwd");
		String newpwd = request.getParameter("newpwd");
		Admin admin = (Admin) SessionUtil.getObjSession(request, "admin");
		if(admin!=null)
		{
			if(oldpwd.equals(admin.getPassword()))
			{
				admin.setPassword(newpwd);
				adminService.upd(admin);
				SessionUtil.setObjSession(request, "admin", admin);
				request.setAttribute("msg", "�޸ĳɹ���");
				selectPageAdmin(request, response);
			}
			else
			{
				request.setAttribute("msg", "�����벻��ȷ����������д��");
				request.getRequestDispatcher("admin/adminuppwd.jsp").forward(request, response);
			}
		}
		else
		{
			request.setAttribute("msg", "����û�е�¼�����ȵ�¼");
			request.getRequestDispatcher("admin/adminlogin.jsp").forward(request, response);
		}
	}
	/**
	 * ��̨��ӹ���Ա
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void addAdmin(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		String adminName = request.getParameter("adminName");
		String password = request.getParameter("pwd");
		Admin admin = new Admin();
		admin.setAdmin_id(KeyUtil.GetKey());
		admin.setIsfreeze(false);
		admin.setName(adminName);
		admin.setPassword(password);
		adminService.add(admin);
		response.sendRedirect("admin.do?method=selectPageAdmin&msg=yes");
	}
	
	public void outAdmin(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		request.getSession().removeAttribute("admin");
		response.sendRedirect("admin/adminlogin.jsp");
	}
	public void authenticationAdminName(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		String name = request.getParameter("userName");
		boolean falg = adminService.authenticationAdminName(name);
		if(falg)
		{
			AjaxOut.out(response, "ok");
		}
		else
		{
			AjaxOut.out(response, "no");
		}
	}
	
}
