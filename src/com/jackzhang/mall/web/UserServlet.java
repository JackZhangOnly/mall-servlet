package com.jackzhang.mall.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jackzhang.mall.service.OrderService;
import com.jackzhang.mall.service.UserService;
import com.jackzhang.mall.util.AjaxOut;
import com.jackzhang.mall.util.DateUtil;
import com.jackzhang.mall.util.KeyUtil;
import com.jackzhang.mall.util.Page;
import com.jackzhang.mall.util.SessionUtil;
import com.jackzhang.mall.vo.User;

/**
 * �û�SERVLET
 * @author jackzhang
 *
 */
public class UserServlet extends HttpServlet {

	private static final long serialVersionUID = -5812164269763769099L;
	
	private UserService userService = new UserService(); 
	
	private OrderService orderService = new OrderService();

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
			if("add".equals(method))
			{
				add(request, response);
			}
			else if("register".equals(method))
			{
				register(request, response);
			}
			else if("login".equals(method))
			{
				login(request, response);
			}else if("out".equals(method))
			{
				out(request, response);
			}else if("result".equals(method))
			{
				result(request, response);
			}else if("main".equals(method))
			{
				main(request, response);
			}else if("upd".equals(method))
			{
				upd(request, response);
			}else if("updpwd".equals(method))
			{
				updpwd(request, response);
			}else if("page".equals(method))
			{
				page(request, response);
			}else if("updState".equals(method))
			{
				updState(request, response);
			}else if("authenticationName".equals(method))
			{
				authenticationName(request, response);
			}
		}
	}
	public void out(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException 
	{
		request.getSession().removeAttribute("user");
		response.sendRedirect("type.do?method=selectIndexType");
	}
	
	/**
	 * ����û�
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void add(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException 
	{
		User user = new User();
		user.setUser_id(KeyUtil.GetKey());
		user.setName(request.getParameter("name"));
		user.setPassword(request.getParameter("password"));
		user.setTime(request.getParameter("time"));
		user.setBalance(0);
		user.setSex(request.getParameter("sex").equals("��")?true:false);
		user.setAddress(request.getParameter("address"));
		user.setPhone(request.getParameter("phone"));
		user.setEmail(request.getParameter("email"));
		user.setIsfreeze(false);
		user.setState(false);
		user.setZip(request.getParameter("zip"));
		userService.add(user);
	}
	/**
	 * �û�ע��
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void register(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException 
	{
		String yzm = request.getParameter("yzm");
		if(yzm.equals(request.getSession().getAttribute("SystemCode")))
		{
			User user = new User();
			user.setUser_id(KeyUtil.GetKey());
			user.setName(request.getParameter("name"));
			user.setPassword(request.getParameter("password"));
			user.setTime(DateUtil.getDate());
			user.setBalance(0);
			user.setSex(request.getParameter("sex").equals("��")?true:false);
			user.setAddress(new String(request.getParameter("address").getBytes("ISO8859-1"),"UTF-8"));
			user.setPhone(request.getParameter("phone"));
			user.setEmail(request.getParameter("email"));
			user.setIsfreeze(false);
			user.setState(false);
			user.setZip(request.getParameter("zip"));
			userService.add(user);
			response.sendRedirect("user/login.jsp");
		}
		else
		{
			response.sendRedirect("user/register.jsp");
		}
	}
	
	/**
	 * �û���¼
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void login(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException 
	{
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		User user = userService.login(name, password);
		if(user.getUser_id()!=null)
		{
			SessionUtil.setObjSession(request, "user", user);
			
			response.sendRedirect("type.do?method=selectIndexType");
		}
		else
		{
			//�û������������
			response.sendRedirect("user/login.jsp");
		}
	}
	/**
	 * �˻���ֵ
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void result(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException 
	{
			double money = Double.valueOf(request.getParameter("money"));
			User user = (User) SessionUtil.getObjSession(request, "user");
			user.setBalance(user.getBalance()+money); 
			userService.upd(user);
			SessionUtil.setObjSession(request, "user", user);
			request.setAttribute("falg", "1");
			response.sendRedirect("user/balance.jsp");
	}
	
	/**
	 * �����û�������ҳ��Ϣ
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void main(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException 
	{
		String user_id =((User) SessionUtil.getObjSession(request, "user")).getUser_id();
		//��õȴ�ȷ�ϵĶ�������
		int awaitingConfirmation = orderService.awaitingConfirmation(user_id);
		//��õȴ����ѵĶ�������
		int toWaitForPayment = orderService.toWaitForPayment(user_id);
		
		request.setAttribute("awaitingConfirmation", awaitingConfirmation);
		request.setAttribute("toWaitForPayment", toWaitForPayment);
		request.getRequestDispatcher("user/main.jsp").forward(request, response);
	}
	/**
	 * �༭�û�
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void upd(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException 
	{
		User user = (User) SessionUtil.getObjSession(request, "user");
		String sex = request.getParameter("sex");
		sex=new String(sex.getBytes("ISO8859-1"),"UTF-8");
		if(sex.equals("��"))
		{
			user.setSex(true);
		}
		else
		{
			user.setSex(false);
		}
		user.setAddress(new String(request.getParameter("address").getBytes("ISO8859-1"),"UTF-8"));
		user.setPhone(request.getParameter("phone"));
		user.setEmail(request.getParameter("email"));
		user.setZip(request.getParameter("zip"));
		userService.upd(user);
		SessionUtil.setObjSession(request, "user", user);
		response.sendRedirect("user.do?method=main");
	}
	/**
	 * �޸�����
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public String updpwd(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException 
	{
		String oldpwd = request.getParameter("oldpwd");
		String newpwd = request.getParameter("newpwd");
		User user = (User) SessionUtil.getObjSession(request, "user");
		if(oldpwd.equals(user.getPassword()))
		{
			if(!oldpwd.equals(newpwd)){
				user.setPassword(newpwd);
				userService.upd(user);
				SessionUtil.setObjSession(request, "user", user);
			}
			AjaxOut.out(response, "ok");
		}
		else
		{
			AjaxOut.out(response, "no");
		}
		return null;
	}
	/**
	 * ��̨��ҳ
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void page(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		String msg = request.getParameter("msg");
		if(msg!=null)
		{
			if(msg.equals("updyes"))
			{
				request.setAttribute("msg", "�����ɹ���");
			}
		}
		Page page = userService.getPage(request);
		List<User> list = userService.pageList(page);
		request.setAttribute("userList", list);
		request.setAttribute("page", page);
		request.getRequestDispatcher("admin/userlist.jsp").forward(request, response);
	}
	
	/**
	 * ��̨�޸��û�״̬
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void updState(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		String id = request.getParameter("id");
		User user = userService.select(id);
		if(user.isIsfreeze())
		{
			user.setIsfreeze(false);
		}
		else
		{
			user.setIsfreeze(true);
		}
		userService.upd(user);
		response.sendRedirect("user.do?method=page&msg=updyes");
	}
	/**
	 * ע��ʱ�û�����֤
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void authenticationName(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		String name = request.getParameter("userName");
		boolean falg = userService.authenticationName(name);
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
