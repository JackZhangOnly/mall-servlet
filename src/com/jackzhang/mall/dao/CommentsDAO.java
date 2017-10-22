package com.jackzhang.mall.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.jackzhang.mall.util.DBManager;
import com.jackzhang.mall.vo.Comments;
import com.jackzhang.mall.vo.Goods;
import com.jackzhang.mall.vo.User;

/**
 * 评论DAO
 * @author jackzhang
 *
 */
public class CommentsDAO extends DBManager {
	
	
	private String sql="";
	private Connection conn =null;
	private PreparedStatement ps =null;
	private ResultSet rs = null;
	
	public int add(Comments comments)
	{
		int no = 0;
		sql = "insert into t_comments (comments_id, `comment`, goods_id, createtime, user_id)values( ?, ?, ?, ?, ?)";
		try {
			conn = getConnection();
			ps = getPreparedStatement(conn, sql);
			ps.setObject(1, comments.getComments_id());
			ps.setObject(2, comments.getComment());
			ps.setObject(3, comments.getGoods().getGoods_id());
			ps.setObject(4, comments.getCreatetime());
			ps.setObject(5, comments.getUser().getUser_id());
			no = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			close(conn, ps, null);
		}
		return no;
	}
	
	public int del(String id)
	{
		int no = 0;
		sql ="delete from t_comments where comments_id=?";
		try {
			conn = getConnection();
			ps = getPreparedStatement(conn, sql);
			ps.setObject(1, id);
			no = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			close(conn, ps, null);
		}
		return no;
	}
	
	public int upd(Comments comments)
	{
		int no = 0;
		sql ="update t_comments set `comment` = ? , goods_id = ? , createtime = ? , user_id = ? where comments_id = ? ";
		try {
			conn = getConnection();
			ps = getPreparedStatement(conn, sql);
			ps.setObject(1, comments.getComment());
			ps.setObject(2, comments.getGoods().getGoods_id());
			ps.setObject(3, comments.getCreatetime());
			ps.setObject(4, comments.getUser().getUser_id());
			ps.setObject(5, comments.getComments_id());
			no = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			close(conn, ps, null);
		}
		return no;
	}
	
	public List<Comments> selectAll(int startRow,int pageSize)
	{
		sql ="select * from t_comments limit ?,?";
		List<Comments> commentsList = new ArrayList<Comments>();
		try {
			conn = getConnection();
			ps = getPreparedStatement(conn, sql);
			ps.setObject(1, startRow);
			ps.setObject(2, pageSize);
			rs = ps.executeQuery();
			rsUtil(commentsList);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			close(conn, ps, rs);
		}
		return commentsList;
	}
	
	/**
	 * 通过商品ID查询评论条数
	 * @return
	 */
	 
	public int getCountByGoods_id(String goods_id)
	{
		int no = 0 ;
		sql = "select count(*) from t_comments where goods_id=?";
		try {
			conn = getConnection();
			ps = getPreparedStatement(conn, sql);
			ps.setObject(1, goods_id);
			rs = ps.executeQuery();
			if(rs.next())
			{
				no = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			close(conn, ps, rs);
		}
		return no;
	}
	
	/**
	 * 通过某一商品，对其评论分页
	 * @param goods_id
	 * @param startRow
	 * @param pageSize
	 * @return
	 */
	public List<Comments> getGoodsCommentsPageList(String goods_id,int startRow,int pageSize)
	{
		sql = "select * from t_comments where goods_id = ? ORDER BY createtime desc limit ?,?";
		List<Comments> list = new ArrayList<Comments>();
		try {
			conn = getConnection();
			ps = getPreparedStatement(conn, sql);
			ps.setObject(1, goods_id);
			ps.setObject(2, startRow);
			ps.setObject(3, pageSize);
			rs = ps.executeQuery();
			rsUtil(list);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			close(conn, ps, rs);
		}
		return list;
	}
	/**
	 * RS帮助类
	 * @param list
	 * @throws SQLException
	 */
	private void rsUtil(List<Comments> list) throws SQLException {
		while(rs.next())
		{
			Comments comments = new Comments();
			comments.setComments_id(rs.getString("comments_id"));
			comments.setComment(rs.getString("comment"));
			comments.setCreatetime(rs.getString("createtime"));
			User user = new User();
			user.setUser_id(rs.getString("user_id"));
			comments.setUser(user);
			Goods goods = new Goods();
			goods.setGoods_id(rs.getString("goods_id"));
			comments.setGoods(goods);
			list.add(comments);
		}
	}
	
	/**
	 * 后台page用到的评论总条数
	 * @return
	 */
	public int countAdmin()
	{
		sql="select count(*) from t_comments";
		int no = 0;
		try {
			conn = getConnection();
			ps = getPreparedStatement(conn, sql);
			rs = ps.executeQuery();
			if(rs.next())
			{
				no = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			close(conn, ps, rs);
		}
		return no;
	}
	
	/**
	 * 用户中心page用到的评论总条数
	 * @return
	 */
	public int countUser(String user_id)
	{
		sql="select count(*) from t_comments where user_id=?";
		int no = 0;
		try {
			conn = getConnection();
			ps = getPreparedStatement(conn, sql);
			ps.setObject(1, user_id);
			rs = ps.executeQuery();
			if(rs.next())
			{
				no = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			close(conn, ps, rs);
		}
		return no;
	}
	/**
	 * 查询某一用户的评论
	 * @param startRow
	 * @param pageSize
	 * @return
	 */
	public List<Comments> selectByUser(String user_id,int startRow,int pageSize)
	{
		sql ="select * from t_comments where user_id= ?  limit ?,?";
		List<Comments> commentsList = new ArrayList<Comments>();
		try {
			conn = getConnection();
			ps = getPreparedStatement(conn, sql);
			ps.setObject(1, user_id);
			ps.setObject(2, startRow);
			ps.setObject(3, pageSize);
			rs = ps.executeQuery();
			rsUtil(commentsList);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			close(conn, ps, rs);
		}
		return commentsList;
	}
}
