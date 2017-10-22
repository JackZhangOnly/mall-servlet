package com.jackzhang.mall.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.jackzhang.mall.util.DBManager;
import com.jackzhang.mall.vo.Goods;
import com.jackzhang.mall.vo.GoodsAdmin;
import com.jackzhang.mall.vo.Type;

/**
 * 商品DAO
 * @author jackzhang
 *
 */
public class GoodsDAO extends DBManager {
	private String sql="";
	private Connection conn =null;
	private PreparedStatement ps =null;
	private ResultSet rs = null;
	
	public int add(Goods goods)
	{
		int no = 0;
		sql = "insert into t_goods (goods_id, `name`, price, img, `desc`, type_id, state, createtime)values(?, ?, ?, ?, ?, ?, ?, ?)";
		try {
			conn = getConnection();
			ps = getPreparedStatement(conn, sql);
			ps.setObject(1, goods.getGoods_id());
			ps.setObject(2, goods.getName());
			ps.setObject(3, goods.getPrice());
			ps.setObject(4, goods.getImg());
			ps.setObject(5, goods.getDesc());
			ps.setObject(6, goods.getType().getType_id());
			ps.setObject(7, goods.isState());
			ps.setObject(8, goods.getCreatetime());
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
		int no = 0 ;
		sql ="delete from t_goods where goods_id=?";
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
	
	public int upd(Goods goods)
	{
		int no = 0;
		sql ="update t_goods set  `name` = ? , price = ? , img = ? , `desc` = ?  , type_id = ? , state = ? , createtime = ?  where goods_id = ? ";
		try {
			conn = getConnection();
			ps = getPreparedStatement(conn, sql);
			ps.setObject(1, goods.getName());
			ps.setObject(2, goods.getPrice());
			ps.setObject(3, goods.getImg());
			ps.setObject(4, goods.getDesc());
			ps.setObject(5, goods.getType().getType_id());
			ps.setObject(6, goods.isState());
			ps.setObject(7, goods.getCreatetime());
			ps.setObject(8, goods.getGoods_id());
			no = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			close(conn, ps, null);
		}
		return no;
	}
	
	public List<Goods> selectAll(String sort_id,int startRow,int pageSize,boolean isDesc)
	{
		if(isDesc)
		{
			sql ="select * from t_goods where `state`=true ORDER BY "+sort_id+" desc  limit ?,?";
		}
		else
		{
			sql ="select * from t_goods where `state`=true ORDER BY "+sort_id+"  limit ?,?";
		}
		List<Goods> list = new ArrayList<Goods>();
		try {
			conn = getConnection();
			ps = getPreparedStatement(conn, sql);
			ps.setObject(1, startRow);
			ps.setObject(2, pageSize);
			rs = ps.executeQuery();
			rsUtil(list);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			close(conn, ps, rs);
		}
		return list;
	}
	/***
	 * 后台分页
	 * @param startRow
	 * @param pageSize
	 * @return
	 */
	public List<Goods> selectAll(int startRow,int pageSize)
	{
		sql ="select * from t_goods where `state`=true limit ?,?";
		List<Goods> list = new ArrayList<Goods>();
		try {
			conn = getConnection();
			ps = getPreparedStatement(conn, sql);
			ps.setObject(1, startRow);
			ps.setObject(2, pageSize);
			rs = ps.executeQuery();
			rsUtil(list);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			close(conn, ps, rs);
		}
		return list;
	}
	
	public List<Goods> selectAll()
	{
		sql ="select * from t_goods where `state`=true";
		List<Goods> list = new ArrayList<Goods>();
		try {
			conn = getConnection();
			ps = getPreparedStatement(conn, sql);
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
	private void rsUtil(List<Goods> list) throws SQLException {
		while(rs.next())
		{
			Goods goods = new Goods();
			goods.setGoods_id(rs.getString("goods_id"));
			goods.setName(rs.getString("name"));
			goods.setPrice(rs.getDouble("price"));
			goods.setImg(rs.getString("img"));
			goods.setDesc(rs.getString("desc"));
			Type type = new Type();
			type.setType_id(rs.getString("type_id"));
			goods.setType(type);
			goods.setState(rs.getBoolean("state"));
			goods.setCreatetime(rs.getString("createtime"));
			list.add(goods);
		}
	}
	/**
	 * 根据商品名称模糊查询
	 * @return
	 */
	public List<Goods> selectByFuzzy(String name)
	{
		sql = "SELECT * FROM t_goods WHERE `state`=true and `name` LIKE  ?";
		List<Goods> list = new ArrayList<Goods>();
		try {
			conn = getConnection();
			ps = getPreparedStatement(conn, sql);
			ps.setObject(1, name);
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
	 * 分页查询顶级商品(FID为0的商品)
	 * @param type_id
	 * @return
	 */
	public List<Goods> pageTopGoods(String type_id,String sort_id,int startRow,int pageSize,boolean isDesc)
	{
		if(isDesc)
		{	
			sql = "SELECT * FROM t_goods WHERE state=true and type_id IN (SELECT type_id FROM t_type WHERE fid = ? ) ORDER BY "+sort_id+" desc limit ?,?";
		}
		else
		{
			sql = "SELECT * FROM t_goods WHERE state=true and type_id IN (SELECT type_id FROM t_type WHERE fid = ? ) ORDER BY "+sort_id+" limit ?,?";
		}
		List<Goods> list = new ArrayList<Goods>();
		try {
			conn = getConnection();
			ps = getPreparedStatement(conn, sql);
			ps.setObject(1, type_id);
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
	 * 分页查询某个类型的商品
	 * @param type_id
	 * @param sort_id
	 * @param startRow
	 * @param pageSize
	 * @param isDesc
	 * @return
	 */
	public List<Goods> pageByType_id(String type_id,String sort_id,int startRow,int pageSize,boolean isDesc)
	{
		if(isDesc)
		{
			sql = "select * from t_goods where type_id= ? and  state=true  ORDER BY "+sort_id+" desc limit ?,?";
		}
		else
		{			
			sql = "select * from t_goods where type_id= ? and state=true ORDER BY "+sort_id+" limit ?,?";
		}
		List<Goods> list = new ArrayList<Goods>();
		try {
			conn = getConnection();
			ps = getPreparedStatement(conn, sql);
			ps.setObject(1, type_id);
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
	 * 顶级类别的总条数
	 * @return
	 */
	public int countByTop(String type_id)
	{
		int no = 0 ;
		sql = "SELECT COUNT(*) FROM t_goods WHERE state=true and type_id IN (SELECT type_id FROM t_type WHERE fid =?)";
		try {
			conn = getConnection();
			ps = getPreparedStatement(conn, sql);
			ps.setObject(1, type_id);
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
	 * 查询某个类别的商品总条数
	 * @param type_id
	 * @return
	 */
	public int countByType(String type_id)
	{
		int no = 0;
		sql ="SELECT count(*) FROM t_goods WHERE `state`=true and type_id= ? ";
		try {
			conn = getConnection();
			ps = getPreparedStatement(conn, sql);
			ps.setObject(1, type_id);
			rs = ps.executeQuery();
			if(rs.next())
			{
				no = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			close(conn, ps, null);
		}
		return no;
	}
	
	/**
	 * 查询单个商品
	 * @param goods_id
	 * @return
	 */
	public Goods select(String goods_id)
	{
		sql="select * from t_goods where `state`=true and goods_id=?";
		Goods goods = new Goods();
		try {
			conn = getConnection();
			ps = getPreparedStatement(conn, sql);
			ps.setObject(1, goods_id);
			rs = ps.executeQuery();
			if(rs.next())
			{
				goods.setGoods_id(rs.getString("goods_id"));
				goods.setName(rs.getString("name"));
				goods.setPrice(rs.getDouble("price"));
				goods.setImg(rs.getString("img"));
				goods.setDesc(rs.getString("desc"));
				Type type = new Type();
				type.setType_id(rs.getString("type_id"));
				goods.setType(type);
				goods.setState(rs.getBoolean("state"));
				goods.setCreatetime(rs.getString("createtime"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			close(conn, ps, rs);
		}
		return goods;
	}
	
	/**
	 * 模糊查询单个商品的中条数
	 * @param name
	 * @return
	 */
	public int singleCount(String name)
	{
		int no = 0;
		sql = "SELECT COUNT(*) FROM t_goods WHERE `state`=true and NAME LIKE '%"+name+"%'";
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
			close(conn, ps, null);
		}
		return no;
	}
	
	/**
	 * 根据商品名称模糊查询
	 * @param goodsName
	 * @return
	 */
	public List<Goods> selectByName(String sort_id,String goodsName,int startRow,int pageSize,boolean isDesc)
	{
		if(isDesc)
		{
			sql = "SELECT * FROM t_goods WHERE `state`=true and NAME LIKE '%"+goodsName+"%' ORDER BY "+sort_id+" desc limit ?,?";
		}
		else
		{			
			sql = "SELECT * FROM t_goods WHERE `state`=true and NAME LIKE '%"+goodsName+"%' ORDER BY "+sort_id+" limit ?,?";
		}
		List<Goods> list = new ArrayList<Goods>();
		try {
			conn = getConnection();
			ps = getPreparedStatement(conn, sql);
			ps.setObject(1, startRow);
			ps.setObject(2, pageSize);
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
	 * 查询所有商品总条数
	 * @return
	 */
	public int count()
	{
		int no = 0;
		sql="SELECT COUNT(*) FROM t_goods WHERE `state`=true";
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
			close(conn, ps, null);
		}
		return no;
	}
	/**
	 * 后台根据某一用户查看买了多少商品
	 * @return
	 */
	public List<GoodsAdmin> selectByUser_id(String user_id)
	{
		sql ="SELECT g.name AS goodsName,(SELECT t.name FROM t_type AS t WHERE type_id=g.type_id) AS goodsTypeName ,g.price as goodsPrice,COUNT(g.goods_id) AS `goodsCount` FROM t_goods AS g LEFT JOIN t_order_goods AS og ON g.goods_id = og.goods_id LEFT JOIN t_order AS o ON og.order_id = o.order_id WHERE o.user_id=?  GROUP BY g.goods_id";
		List<GoodsAdmin> list = new ArrayList<GoodsAdmin>();
		try {
			conn = getConnection();
			ps = getPreparedStatement(conn, sql);
			ps.setObject(1, user_id);
			rs = ps.executeQuery();
			while(rs.next())
			{
				GoodsAdmin goodsAdmin = new GoodsAdmin();
				goodsAdmin.setGoodsName(rs.getString("goodsName"));
				goodsAdmin.setGoodsTypeName(rs.getString("goodsTypeName"));
				goodsAdmin.setGoodsPrice(rs.getString("goodsPrice"));
				goodsAdmin.setGoodsCount(rs.getString("goodsCount"));
				list.add(goodsAdmin);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			close(conn, ps, rs);
		}
		return list;
	}
	
	/**
	 * 后台根据某一订单查看买了多少商品
	 * @return
	 */
	public List<GoodsAdmin> selectByOrder_id(String order_id)
	{
		sql = "SELECT g.name AS goodsName,(SELECT t.name FROM t_type AS t WHERE type_id=g.type_id) AS goodsTypeName ,g.price AS goodsPrice,COUNT(g.goods_id) AS `goodsCount` FROM t_goods AS g LEFT JOIN t_order_goods AS og ON g.goods_id = og.goods_id LEFT JOIN t_order AS o ON og.order_id = o.order_id WHERE og.order_id=?  GROUP BY g.goods_id";
		List<GoodsAdmin> list = new ArrayList<GoodsAdmin>();
		try {
			conn = getConnection();
			ps = getPreparedStatement(conn, sql);
			ps.setObject(1, order_id);
			rs = ps.executeQuery();
			while(rs.next())
			{
				GoodsAdmin goodsAdmin = new GoodsAdmin();
				goodsAdmin.setGoodsName(rs.getString("goodsName"));
				goodsAdmin.setGoodsTypeName(rs.getString("goodsTypeName"));
				goodsAdmin.setGoodsPrice(rs.getString("goodsPrice"));
				goodsAdmin.setGoodsCount(rs.getString("goodsCount"));
				list.add(goodsAdmin);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			close(conn, ps, rs);
		}
		return list;
	}
}
