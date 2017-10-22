package com.jackzhang.mall.web;

import java.io.File;
import java.io.IOException;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jackzhang.mall.service.CommentsService;
import com.jackzhang.mall.service.GoodsService;
import com.jackzhang.mall.service.TypeService;
import com.jackzhang.mall.service.UserService;
import com.jackzhang.mall.util.AjaxOut;
import com.jackzhang.mall.util.DateUtil;
import com.jackzhang.mall.util.KeyUtil;
import com.jackzhang.mall.util.Page;
import com.jackzhang.mall.util.RandomFileRenamePolicy;
import com.jackzhang.mall.util.SessionUtil;
import com.jackzhang.mall.vo.Comments;
import com.jackzhang.mall.vo.Goods;
import com.jackzhang.mall.vo.GoodsAdmin;
import com.jackzhang.mall.vo.Type;
import com.jackzhang.mall.vo.User;
import com.oreilly.servlet.MultipartRequest;

/**
 * 商品SERVLET
 * 
 * @author jackzhang
 * 
 */
public class GoodsServlet extends HttpServlet {

	private static final long serialVersionUID = -3858469519454809267L;

	private GoodsService goodsService = new GoodsService();

	private TypeService typeService = new TypeService();
	
	private UserService userService = new UserService();
	
	private CommentsService commentsService = new CommentsService();

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String method = request.getParameter("method");
		if ("ajax".equals(method)) {
			ajax(request, response);
		} else if ("page".equals(method)) {
			page(request, response);
		}else if("goodsShow".equals(method)){
			goodsShow(request, response);
		}else if("addComments".equals(method)){
			addComments(request, response);
		}else if("pageAdmin".equals(method))
		{
			pageAdmin(request, response);
		}else if("del".equals(method))
		{
			del(request, response);
		}else if("goodsShowAdmin".equals(method))
		{
			goodsShowAdmin(request, response);
		}else if("selectType".equals(method))
		{
			selectType(request, response);
		}else if("addAdmin".equals(method))
		{
			addAdmin(request, response);
		}else if("getupdDetails".equals(method))
		{
			getupdDetails(request, response);
		}else if("updAdmin".equals(method))
		{
			updAdmin(request, response);
		}else if("selectByUser_idAdmin".equals(method))
		{
			selectByUser_idAdmin(request, response);
		}
	}

	/**
	 * 主页suggest效果
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public String ajax(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String name=new String(request.getParameter("name").getBytes("ISO8859-1"),"UTF-8");
		//获得输出字符
		String output = goodsService.suggestString(name);
		AjaxOut.out(response, output);
		return null;
	}

	

	/**
	 * 查询类别下的商品集合
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void page(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		String type_id = request.getParameter("type_id");
		int count = 0;
		Page page = null;
		List<Goods> list =null;
		// 分页是否用到
		String sort_id = request.getParameter("sort_id");
		// 第一次进入商品分页页面给出排序初始值
		if (sort_id == null) {
			sort_id = "1";
			request.setAttribute("sort_id", 1);
		} else {
			request.setAttribute("sort_id", sort_id);
		}
		
		//通过模糊查询
		if(type_id==null||type_id.equals(""))
		{
			//获得商品名称
			String goodsName=request.getParameter("goodsName");	
			if(goodsName==null)
			{
				count = goodsService.count();
			}
			else
			{
				goodsName = new String(goodsName.getBytes("ISO8859-1"),"UTF-8");
				request.setAttribute("goodsName", goodsName);
				count = goodsService.singleCount(goodsName);
			}
			page = goodsService.getPage(request, count);
			list = goodsService. pageListByName(page, goodsName,sort_id);
			//模糊查询分页页面用到显示类别名称
			String typeName = null;
			if(list.size()==1)
			{
				typeName = list.get(0).getType().getType_id();
				typeName = typeService.select(typeName).getName();
			}
			else
			{
				typeName = "综合";
			}
			request.setAttribute("typeName", typeName);
		}
		else//类别查询
		{
			// 分页页面用到显示类别对象
			Type type = typeService.select(type_id);
			request.setAttribute("type", type);
			// 是否是顶级类别（FID=0）
			String fid = type.getFid();
			if (fid.equals("0")) {
				count = goodsService.countByTop(type_id);
			} else {
				count = goodsService.countByType(type_id);
			}
			page = goodsService.getPage(request, count);
			// 分页商品集合
			list = goodsService.pageList(page, type_id, sort_id);		
		}
		request.setAttribute("goodsAll", list);
		request.setAttribute("count", count);
		request.setAttribute("page", page);
		// 查询顶级类型，页面用到
		getType(request);
		request.getRequestDispatcher("goods/goodslist.jsp").forward(request,
				response);
	}
	/**
	 * 查询顶级类型，页面用到
	 * @param request
	 */
	private void getType(HttpServletRequest request) {
		List<Type> typeList = typeService.selectByFid("0");
		request.setAttribute("typeList", typeList);
	}
	/**
	 * 查询详细
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void goodsShow(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//获取商品ID
		String goods_id=request.getParameter("goods_id");
		//查询单个商品
		Goods goods = goodsService.select(goods_id);
		//通过商品对象获得类型对象
		Type type = goodsService.getType(goods);
		//获得该商品评论总条数
		int count = goodsService.getGoodsCommentsCount(goods_id);
		//获得分页对象
		Page page = goodsService.getPage(request, count);
		//获得分页集合
		List<Comments> list = goodsService.getCommentsPageList(page, goods_id);
		for (Comments comments : list) {
			User user = comments.getUser();
			user = userService.select(user.getUser_id());
			comments.setUser(user);
		}
		getType(request);
		request.setAttribute("commentsAll",list);
		request.setAttribute("count", count);
		request.setAttribute("goods", goods);
		request.setAttribute("type", type);
		request.setAttribute("page", page);
		request.getRequestDispatcher("goods/goodsshow.jsp").forward(request, response);
	}
	
	/**
	 * 在商品详细中添加一条评论
	 * @param request
	 */
	public void addComments(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException 
	{
		Comments comments = new Comments();
		comments.setComments_id(KeyUtil.GetKey());
		
		comments.setComment(new String(request.getParameter("comment").getBytes("ISO8859-1"),"UTF-8"));
		Goods goods1 = new Goods();
		goods1.setGoods_id(request.getParameter("goods_id"));
		comments.setGoods(goods1);
		comments.setCreatetime(DateUtil.getDate());
		comments.setUser((User) request.getSession().getAttribute("user"));
		commentsService.add(comments);
		goodsShow(request, response);
	}
	/**
	 * 后台商品管理分页
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void pageAdmin(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException 
	{
		String msg = request.getParameter("msg");
		if(msg!=null)
		{
			if(msg.equals("addyes"))
			{
				request.setAttribute("msg", "添加商品成功！");
			}else if(msg.equals("updyes"))
			{
				request.setAttribute("msg", "修改商品成功！");
			}else if(msg.equals("delyes"))
			{
				request.setAttribute("msg", "删除商品成功！");
			}
		}
		int count = goodsService.count(); 
		Page page = goodsService.getPage(request, count);
		List<Goods> list = goodsService.pageAdmin(page);
		request.setAttribute("goodsList", list);
		request.setAttribute("page", page);
		request.getRequestDispatcher("admin/goodslist.jsp").forward(request, response);
	}
	
	/**
	 * 后台删除商品
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void del(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException 
	{
		String goods_id = request.getParameter("goods_id");
		Goods goods = goodsService.select(goods_id);
		goods.setState(false);
		goodsService.upd(goods);
		response.sendRedirect("goods.do?method=pageAdmin&msg=delyes");
	}
	/**
	 * 后台查看商品详细
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void goodsShowAdmin(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String goods_id = request.getParameter("goods_id");
		Goods goods = goodsService.select(goods_id);
		request.setAttribute("goods", goods);
		request.getRequestDispatcher("admin/goodsshow.jsp").forward(request, response);
	}
	
	/**
	 * 后台添加商品前获得所有类别
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void selectType(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		List<Type> list = typeService.selectByson();
		request.setAttribute("typeList", list);
		request.getRequestDispatcher("admin/goodsadd.jsp").forward(request, response);
	}
	
	/**
	 * 后台添加商品
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void addAdmin(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		ServletContext   application=(ServletContext)session.getServletContext();  
		System.out.println("ABC=" + application.getRealPath("/"));
		String filePath = application.getRealPath("/")+"upload";
		System.out.println("filePath=" + filePath);
		File uploadPath = new File(filePath);
		//检查文件夹是否存在 不存在 创建一个
		if(!uploadPath.exists())
		{
			uploadPath.mkdir();
		}
		//文件最大容量 5M
		int fileMaxSize = 5*1024*1024;
		//文件名
		String fileName = null;
		String path=null;
		//上传文件数
		int fileCount = 0;
		//重命名策略
		RandomFileRenamePolicy rfrp=new RandomFileRenamePolicy();
		//上传文件
		MultipartRequest mulit = new MultipartRequest(request,filePath,fileMaxSize,"UTF-8",rfrp);	
			Enumeration<?> filesname = mulit.getFileNames();
		      while(filesname.hasMoreElements())
		      {
		           String name = (String)filesname.nextElement();
		           fileName = mulit.getFilesystemName(name);
		           String contentType = mulit.getContentType(name);
		           
		           if(fileName!=null)
		           {
		        	   fileCount++;
		        	   path = "upload/"+fileName;
		           }
		           else
		           {
		        	   fileName = "wu.jpg";
		        	   path = "images/"+fileName;
		           }
		           System.out.println("文件名：" + fileName);
		           System.out.println("文件类型： " + contentType);
		           
		      }
		      System.out.println("共上传" + fileCount + "个文件！");
		      Goods goods = new Goods();
		      goods.setGoods_id(KeyUtil.GetKey());
		      goods.setName(mulit.getParameter("goodsName"));
		      goods.setPrice(Double.valueOf(mulit.getParameter("price")));
		      goods.setImg(path);
		      goods.setDesc(mulit.getParameter("desc"));		 
		      goods.setType(typeService.select(mulit.getParameter("type")));
		      goods.setState(true);
		      goods.setCreatetime(DateUtil.getDate());
		      goodsService.add(goods);
		      response.sendRedirect("goods.do?method=pageAdmin&msg=addyes");
	}
	/**
	 * 获得修改前商品的详细
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void getupdDetails(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String goods_id = request.getParameter("goods_id");
		Goods goods = goodsService.select(goods_id);
		//获得类别
		List<Type> list = typeService.selectByson();
		request.setAttribute("typeList", list);
		request.setAttribute("goods", goods);
		request.getRequestDispatcher("admin/goodsupdate.jsp").forward(request, response);
	}
	/***
	 * 后台修改商品
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void updAdmin(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		ServletContext   application=(ServletContext)session.getServletContext();  
		System.out.println("ABC=" + application.getRealPath("/"));
		String filePath = application.getRealPath("/")+"upload";
		System.out.println("filePath=" + filePath);
		File uploadPath = new File(filePath);
		//检查文件夹是否存在 不存在 创建一个
		if(!uploadPath.exists())
		{
			uploadPath.mkdir();
		}
		//文件最大容量 5M
		int fileMaxSize = 5*1024*1024;
		//文件名
		String fileName = null;
		String path=null;
		//上传文件数
		int fileCount = 0;
		//重命名策略
		RandomFileRenamePolicy rfrp=new RandomFileRenamePolicy();
		//上传文件
		MultipartRequest mulit = new MultipartRequest(request,filePath,fileMaxSize,"UTF-8",rfrp);	
			Enumeration<?> filesname = mulit.getFileNames();
		      while(filesname.hasMoreElements())
		      {
		           String name = (String)filesname.nextElement();
		           fileName = mulit.getFilesystemName(name);
		           String contentType = mulit.getContentType(name);
		           
		           if(fileName!=null)
		           {
		        	   fileCount++;
		        	   path = "upload/"+fileName;
		           }
		           else
		           {
		        	   fileName = "wu.jpg";
		        	   path = "images/"+fileName;
		           }
		           System.out.println("文件名：" + fileName);
		           System.out.println("文件类型： " + contentType);
		           
		      }
		      System.out.println("共上传" + fileCount + "个文件！");
		      Goods goods = new Goods();
		      goods.setGoods_id(mulit.getParameter("goods_id"));
		      goods.setName(mulit.getParameter("goodsName"));
		      goods.setPrice(Double.valueOf(mulit.getParameter("price")));
		      goods.setImg(path);
		      goods.setDesc(mulit.getParameter("desc"));		 
		      goods.setType(typeService.select(mulit.getParameter("type")));
		      goods.setState(true);
		      goods.setCreatetime(DateUtil.getDate());
		      goodsService.upd(goods);
		      response.sendRedirect("goods.do?method=pageAdmin&msg=updyes");
	}
	
	/**
	 * 后台根据某一用户查看买了多少商品
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void selectByUser_idAdmin(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String user_id =((User) SessionUtil.getObjSession(request, "user")).getUser_id();
		List<GoodsAdmin> list = goodsService.selectByUser_id(user_id);
		request.setAttribute("goodsAdminList", list);
		request.getRequestDispatcher("user/myproduct.jsp").forward(request, response);
	}
}
