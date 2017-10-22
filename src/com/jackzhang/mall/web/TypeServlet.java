package com.jackzhang.mall.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jackzhang.mall.service.TypeService;
import com.jackzhang.mall.util.KeyUtil;
import com.jackzhang.mall.vo.Type;

/**
 * 商品类型SERVLET
 * @author jackzhang
 *
 */
public class TypeServlet extends HttpServlet {

	private static final long serialVersionUID = 8420521958198809085L;
	
	private TypeService typeService = new TypeService();

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String method = request.getParameter("method");
		if("selectIndexType".equals(method))
		{
			selectIndexType(request, response);
		}else if("selectAdminType".equals(method))
		{
			selectAdminType(request, response);
		}else if("addTypeList".equals(method))
		{
			addTypeList(request, response);
		}else if("addSunTypeAdmin".equals(method))
		{
			addSunTypeAdmin(request, response);
		}else if("selectTypes".equals(method)){
			selectTypes(request, response);
		}
	}
	/**
	 * 查询主页上的商品类型
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void selectIndexType(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		//查询顶级商品类型
		List<Type> topList = typeService.selectByFid("0");
		request.setAttribute("type1", topList.get(0));
		request.setAttribute("type2", topList.get(1));
		request.setAttribute("type3", topList.get(2));
		request.setAttribute("type4", topList.get(3));
		request.setAttribute("type5", topList.get(4));
		request.setAttribute("type6", topList.get(5));
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}
	/**
	 * 查询所有类别
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void selectTypes(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		//查询顶级商品类型
		List<Type> topList = typeService.selectByFid("0");
		request.setAttribute("type1", topList.get(0));
		request.setAttribute("type2", topList.get(1));
		request.setAttribute("type3", topList.get(2));
		request.setAttribute("type4", topList.get(3));
		request.setAttribute("type5", topList.get(4));
		request.setAttribute("type6", topList.get(5));
		request.getRequestDispatcher("typelist.jsp").forward(request, response);
	}
	/**
	 * 后台类别查询
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void selectAdminType(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		String msg = request.getParameter("msg");
		if(msg!=null)
		{
			if(msg.equals("addyes"))
			{
				request.setAttribute("msg", "添加类别成功！");
			}
		}
		//查询顶级商品类型
		List<Type> topList = typeService.selectByFid("0");
		request.setAttribute("type1", topList.get(0));
		request.setAttribute("type2", topList.get(1));
		request.setAttribute("type3", topList.get(2));
		request.setAttribute("type4", topList.get(3));
		request.setAttribute("type5", topList.get(4));
		request.setAttribute("type6", topList.get(5));
		request.getRequestDispatcher("admin/typelist.jsp").forward(request, response);
	}
	/**
	 * 后台添加页面用到的父类别集合
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void addTypeList(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		List<Type> topList =typeService.selectByFidNo("0");
		request.setAttribute("topList", topList);
		request.getRequestDispatcher("admin/typeadd.jsp").forward(request, response);
	}
	/**
	 * 后台添加子类别
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void addSunTypeAdmin(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		String fid = request.getParameter("fid");
		String typeName = request.getParameter("typeName");
		Type type = new Type();
		type.setType_id(KeyUtil.GetKey());
		type.setName(typeName);
		type.setState(true);
		type.setFid(fid);
		typeService.add(type);
		//selectAdminType(request, response);
		response.sendRedirect("type.do?method=selectAdminType&msg=addyes");
	}
}
