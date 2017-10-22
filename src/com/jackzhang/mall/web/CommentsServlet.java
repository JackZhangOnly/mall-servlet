package com.jackzhang.mall.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jackzhang.mall.service.CommentsService;
import com.jackzhang.mall.util.Page;
import com.jackzhang.mall.vo.Comments;

public class CommentsServlet extends HttpServlet {

	private static final long serialVersionUID = 8196954972931582565L;
	
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
		if(method!=null)
		{
			if("pageAdmin".equals(method))
			{
				pageAdmin(request, response);
			}else if("del".equals(method))
			{
				del(request, response);
			}else if("pageUser".equals(method))
			{
				pageUser(request, response);
			}
		}
	}
	
	/**
	 * 后台评论管理
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void pageAdmin(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		String msg = request.getParameter("msg");
		if(msg!=null)
		{
			if(msg.equals("yes"))
			{
				request.setAttribute("msg", "删除评论成功！");
			}
		}
		int count  = commentsService.countAdmin();
		Page page = commentsService.getPage(request,count);
		List<Comments> list = commentsService.pageListAdmin(page);
		request.setAttribute("page", page);
		request.setAttribute("commentsList", list);
		request.getRequestDispatcher("admin/commentlist.jsp").forward(request, response);
	}
	/**
	 * 用户中心评论管理
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void pageUser(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		String user_id = request.getParameter("user_id");
		int count = commentsService.countUser(user_id);
		Page page = commentsService.getPage(request,count);
		List<Comments> list = commentsService.pageListUser(page, user_id);
		request.setAttribute("page", page);
		request.setAttribute("commentsList", list);
		request.getRequestDispatcher("user/mycomment.jsp").forward(request, response);
	}
	
	/**
	 * 删除
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void del(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		String comments_id = request.getParameter("comments_id");
		commentsService.del(comments_id);
		response.sendRedirect("comments.do?method=pageAdmin&msg=yes");
	}
	
	
	
}
