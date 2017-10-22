package com.jackzhang.mall.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jackzhang.mall.service.GoodsService;
import com.jackzhang.mall.service.OrderService;
import com.jackzhang.mall.service.TypeService;
import com.jackzhang.mall.service.UserService;
import com.jackzhang.mall.util.DateUtil;
import com.jackzhang.mall.util.KeyUtil;
import com.jackzhang.mall.util.Page;
import com.jackzhang.mall.util.SessionUtil;
import com.jackzhang.mall.vo.Goods;
import com.jackzhang.mall.vo.GoodsAdmin;
import com.jackzhang.mall.vo.Order;
import com.jackzhang.mall.vo.Type;
import com.jackzhang.mall.vo.User;

public class OrderServlet extends HttpServlet {
	private static final long serialVersionUID = -2265205417919185275L;
	
	private TypeService typeService = new TypeService();
	
	private UserService userService = new  UserService();
	
	private OrderService orderService = new OrderService();
	
	private GoodsService goodsService = new GoodsService();
	
	
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
			if("verifyAddresses".equals(method))
			{
				verifyAddresses(request, response);
			}else if("details".equals(method)){
				details(request, response);
			}else if("add".equals(method))
			{
				add(request, response);
			}else if("select".equals(method))
			{
				select(request, response);
			}else if("selectunOrderPageAdmin".equals(method))
			{
				selectunOrderPageAdmin(request, response);
			}else if("selectOrderPageAdmin".equals(method))
			{
				selectOrderPageAdmin(request, response);
			}else if("selectOrderAdmin".equals(method))
			{
				selectOrderAdmin(request, response);
			}else if("updStateAdmin".equals(method))
			{
				updStateAdmin(request, response);
			}else if("selectOrderUser".equals(method))
			{
				selectOrderUser(request, response);
			}else if("buyUser".equals(method))
			{
				buyUser(request, response);
			}else if("temp".equals(method))
			{
				temp(request, response);
			}
		}
	}
	/**
	 * 查看订单中的收货人详细
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void details(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		addType(request);
		request.getRequestDispatcher("order/orderaddress.jsp").forward(request, response);
	}
	/**
	 * 验证收货地址
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void verifyAddresses(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		User user = (User) SessionUtil.getObjSession(request, "user");		
		user.setAddress(new String(request.getParameter("address").getBytes("ISO8859-1"),"UTF-8"));//地址
		user.setZip(request.getParameter("zip"));//邮政编码
		user.setPhone(request.getParameter("phone"));//电话
		userService.upd(user);
		addType(request);
		SessionUtil.setObjSession(request, "token", KeyUtil.GetKey());
		request.getRequestDispatcher("order/ordersubmit.jsp").forward(request, response);
	}
	/**
	 * 获取类别
	 * @param request
	 */
	public void addType(HttpServletRequest request)
	{
		List<Type> typeList =  typeService.selectByFid("0");
		request.setAttribute("typeList", typeList);
	}
	
	/**
	 * 添加订单
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void add(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		//送货方式
		double payment =Double.valueOf(request.getParameter("payment"));
		//支付方式
		String pay = request.getParameter("pay");
		double sum = Double.valueOf(request.getParameter("sum"));
		User user = (User) SessionUtil.getObjSession(request, "user");
		Order order = new Order();
		//订单ID
		String order_id =KeyUtil.GetKey();
		order.setOrder_id(order_id);
		order.setConsignee(user.getName());
		order.setTotal(sum);
		if(pay.equals("ubai"))
		{
			order.setPayment("ubai付款");
		}
		else
		{
			order.setPayment("货到付款");
		}
		order.setState(false);
		order.setTime(DateUtil.getDate());
		order.setUser(user);
		order.setAddress(new String(user.getAddress().getBytes("ISO8859-1"),"UTF-8"));
		order.setPhone(user.getPhone());
		order.setPostcard(user.getZip());
		if(payment==5)
		{
			order.setDelivery("普通快递上门");
			order.setFreight("5");
		}
		else
		{
			order.setDelivery("普通邮递");
			order.setFreight("0");
		}
		//是否付款
		if(pay.equals("ubai")&&user.getBalance()>sum)
		{
			//付款
			user.setBalance(user.getBalance()-sum);
			userService.upd(user);
			SessionUtil.setObjSession(request, "user", user);
			order.setIspay(true);
		}
		else
		{
			order.setIspay(false);
		}
		orderService.add(order);
		//----添加关系表------
		addgxb(request, order_id);
		//----销毁购物车----
		Map<String, Map<Goods, Integer>> gwc = new HashMap<String, Map<Goods,Integer>>();
		SessionUtil.setObjSession(request, "gwc", gwc);
		
		response.sendRedirect("order.do?method=temp&order_id="+order_id);
		//request.setAttribute("order", order);
		//request.getRequestDispatcher("order/ordersuccess.jsp").forward(request, response);
	}
	
	/**
	 * 防止重复提交的转发
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void temp(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		String order_id = request.getParameter("order_id");
		Order order = orderService.select(order_id);
		request.setAttribute("order", order);
		addType(request);
		request.getRequestDispatcher("order/ordersuccess.jsp").forward(request, response);
	}
	/**
	 * 添加订单和商品表关系
	 * @param request
	 * @param order_id
	 */
	@SuppressWarnings("unchecked")
	private void addgxb(HttpServletRequest request, String order_id) {
		String goods_id = null;
		int no = 0;
		 Iterator iter = ((Map<String, Map<Goods, Integer>>) SessionUtil.getObjSession(request, "gwc")).entrySet().iterator();
		 while(iter.hasNext()) 
		 {
			 Map.Entry entry = (Map.Entry) iter.next();
			 //获得商品ID
			 goods_id =  (String) entry.getKey();
			 //获得内层Map
			 Map<Goods, Integer> nmap= (Map<Goods, Integer>) entry.getValue();
			 //迭代器
			 Iterator niter = nmap.entrySet().iterator();
			 if(niter.hasNext())
			 {
				 Map.Entry nentry = (Map.Entry) niter.next();
				 //获得商品数量
				 no = Integer.valueOf(nentry.getValue().toString());				 
			 }
			 //添加订单和商品关系
			 for(int i =1;i<=no;i++)
			 {				 
				 orderService.addgxb(order_id, goods_id);
			 }
		 }
	}
	
	/**
	 * 用户中心中的查看订单
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void select(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		User user =  (User) SessionUtil.getObjSession(request, "user");
		List<Order> list = orderService.selectToWaitForPaymentUser(user.getUser_id());
		request.setAttribute("orderList", list);
		request.getRequestDispatcher("user/myorder.jsp").forward(request, response);
	}
	
	/**
	 * 后台查询未发货的分页订单
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void selectunOrderPageAdmin(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		int count = orderService.unOrderCountAdmin();
		Page page = orderService.getPage(request, count); 
		List<Order> list = orderService.selectunOrderAdmin(page);
		request.setAttribute("orderList", list);
		request.setAttribute("page", page);
		request.getRequestDispatcher("admin/unfilledorderlist.jsp").forward(request, response);
	}
	
	/**
	 * 后台查询已发货的分页订单
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void selectOrderPageAdmin(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		String msg = request.getParameter("msg");
		if(msg!=null)
		{
			if(msg.equals("yes"))
			{
				request.setAttribute("msg", "发货成功！");
			}
		}
		int count = orderService.OrderCountAdmin();
		Page page = orderService.getPage(request, count); 
		List<Order> list = orderService.selectOrderAdmin(page);
		request.setAttribute("orderList", list);
		request.setAttribute("page", page);
		request.getRequestDispatcher("admin/filledorderlist.jsp").forward(request, response);
	}
	/**
	 * 后台订单详细查询
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void selectOrderAdmin(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		String order_id = request.getParameter("order_id");
		Order order = orderService.selectOrderAdmin(order_id);
		//--------某一订单下的商品-------------
		List<GoodsAdmin> list = goodsService.selectByOrder_id(order_id);
		request.setAttribute("goodsAdminList", list);
		request.setAttribute("order", order);
		request.getRequestDispatcher("admin/ordershow.jsp").forward(request, response);
	}
	/**
	 * 用户中心订单详细查询
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void selectOrderUser(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		String order_id = request.getParameter("order_id");
		Order order = orderService.selectOrderAdmin(order_id);
		//--------某一订单下的商品-------------
		List<GoodsAdmin> list = goodsService.selectByOrder_id(order_id);
		request.setAttribute("goodsAdminList", list);
		request.setAttribute("order", order);
		request.getRequestDispatcher("user/ordershow.jsp").forward(request, response);
	}
	
	/**
	 * 后台订单发货
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void updStateAdmin(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		String order_id = request.getParameter("id");
		Order order = orderService.select(order_id);
		order.setState(true);
		orderService.upd(order);
		response.sendRedirect("order.do?method=selectOrderPageAdmin&msg=yes");
	}
	/**
	 * 用户中心付款
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void buyUser(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		String order_id = request.getParameter("order_id");
		User user = (User) SessionUtil.getObjSession(request, "user");
		Order order = orderService.select(order_id);
		//订单总金额
		double m = order.getTotal();
		//用户余额
		double userm =user.getBalance();
		if(userm<m)
		{
			request.getRequestDispatcher("user/balance.jsp").forward(request, response);
		}
		else
		{
			user.setBalance(userm-m);
			userService.upd(user);
			SessionUtil.setObjSession(request, "user", user);
			order.setIspay(true);
			orderService.upd(order);
			response.sendRedirect("order.do?method=select");
		}
	}
}
