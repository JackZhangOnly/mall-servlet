package com.jackzhang.mall.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.jackzhang.mall.util.DBManager;
import com.jackzhang.mall.vo.Order;
import com.jackzhang.mall.vo.User;

/**
 * 订单DAO
 * @author jackzhang
 *
 */
public class OrderDAO extends DBManager {
	
	private String sql="";
	private Connection conn =null;
	private PreparedStatement ps =null;
	private ResultSet rs = null;
	
	public int add(Order order)
	{
		int no = 0;
		sql = "insert into t_order (order_id, consignee, total, payment, state, `time`, user_id, address, phone, postcard, delivery, freight, ispay)values(?, ?,?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		try {
			conn = getConnection();
			ps = getPreparedStatement(conn, sql);
			ps.setObject(1, order.getOrder_id());
			ps.setObject(2, order.getConsignee());
			ps.setObject(3, order.getTotal());
			ps.setObject(4, order.getPayment());
			ps.setObject(5, order.isState());
			ps.setObject(6, order.getTime());
			ps.setObject(7, order.getUser().getUser_id());
			ps.setObject(8, order.getAddress());
			ps.setObject(9, order.getPhone());
			ps.setObject(10, order.getPostcard());
			ps.setObject(11, order.getDelivery());
			ps.setObject(12, order.getFreight());
			ps.setObject(13, order.isIspay());
			no = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			close(conn, ps, null);
		}
		return no ;
	}
	
	public int del (String id)
	{
		int no = 0 ;
		sql ="delete from t_order where order_id =?";
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
	
	public int upd(Order order)
	{
		int no = 0;
		sql ="update t_order set consignee = ? , total = ? , payment = ? , state = ? , `time` = ? , user_id = ? , address = ? , phone = ? , postcard = ? , delivery = ? , freight = ? , ispay = ?  where  order_id = ?";
		try {
			conn = getConnection();
			ps = getPreparedStatement(conn, sql);
			ps.setObject(1, order.getConsignee());
			ps.setObject(2, order.getTotal());
			ps.setObject(3, order.getPayment());
			ps.setObject(4, order.isState());
			ps.setObject(5, order.getTime());
			ps.setObject(6, order.getUser().getUser_id());
			ps.setObject(7, order.getAddress());
			ps.setObject(8, order.getPhone());
			ps.setObject(9, order.getPostcard());
			ps.setObject(10, order.getDelivery());
			ps.setObject(11, order.getFreight());
			ps.setObject(12, order.isIspay());
			ps.setObject(13, order.getOrder_id());
			no = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			close(conn, ps, null);
		}
		return no;
	}
	
	public List<Order> selectAll()
	{
		sql ="select * from t_order";
		List<Order> orderList = new ArrayList<Order>();
		try {
			conn = getConnection();
			ps = getPreparedStatement(conn, sql);
			rs = ps.executeQuery();
			rsUtil(orderList);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			close(conn, ps, rs);
		}
		return orderList;
	}
	
	public Order select(String order_id)
	{
		sql = "select * from t_order where order_id = ?";
		Order order = new Order();
		try {
			conn = getConnection();
			ps = getPreparedStatement(conn, sql);
			ps.setObject(1, order_id);
			rs = ps.executeQuery();
			if(rs.next())
			{
				order.setOrder_id(rs.getString("order_id"));
				order.setConsignee(rs.getString("consignee"));
				order.setTotal(rs.getDouble("total"));
				order.setPayment(rs.getString("payment"));
				order.setState(rs.getBoolean("state"));
				order.setTime(rs.getString("time"));
				User user = new User(); 
				user.setUser_id(rs.getString("user_id"));
				order.setUser(user);
				order.setAddress(rs.getString("address"));
				order.setPhone(rs.getString("phone"));
				order.setPostcard(rs.getString("postcard"));
				order.setDelivery(rs.getString("delivery"));
				order.setFreight(rs.getString("freight"));
				order.setIspay(rs.getBoolean("ispay"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			close(conn, ps, rs);
		}
		return order;
	}
	
	/**
	 * RS帮助类
	 * @param orderList
	 * @throws SQLException
	 */
	private void rsUtil(List<Order> orderList) throws SQLException {
		while(rs.next())
		{
			Order order = new Order();
			order.setOrder_id(rs.getString("order_id"));
			order.setConsignee(rs.getString("consignee"));
			order.setTotal(rs.getDouble("total"));
			order.setPayment(rs.getString("payment"));
			order.setState(rs.getBoolean("state"));
			order.setTime(rs.getString("time"));
			User user = new User(); 
			user.setUser_id(rs.getString("user_id"));
			order.setUser(user);
			order.setAddress(rs.getString("address"));
			order.setPhone(rs.getString("phone"));
			order.setPostcard(rs.getString("postcard"));
			order.setDelivery(rs.getString("delivery"));
			order.setFreight(rs.getString("freight"));
			order.setIspay(rs.getBoolean("ispay"));
			orderList.add(order);
		}
	}
	/**
	 * 用户中心获得未付款的订单集合
	 * @param user_id
	 * @return
	 */
	public List<Order> selectToWaitForPaymentUser(String user_id)
	{		
		sql = "SELECT * FROM t_order WHERE   user_id=? ORDER BY `time` DESC ";
		List<Order> orderList = new ArrayList<Order>();
		try {
			conn = getConnection();
			ps = getPreparedStatement(conn, sql);
			ps.setObject(1, user_id);
			rs = ps.executeQuery();
			rsUtil(orderList);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			close(conn, ps, rs);
		}
		return orderList;
	}
	
	/**
	 * 获得等待付费的订单个数
	 * @return
	 */
	public int toWaitForPayment(String user_id)
	{		
		int no = 0 ;
		sql = "SELECT COUNT(*) FROM t_order WHERE ispay=false and payment='ubai付款' and user_id=?";
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
	 * 获得等待确认的订单个数
	 * @return
	 */
	public int awaitingConfirmation(String user_id)
	{
		int no = 0 ;
		sql = "SELECT COUNT(*) FROM t_order WHERE state=false and user_id=?";
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
	 * 添加关系表
	 * @param order_id
	 * @param goods_id
	 * @param no
	 */
	public void addgxb(String order_id,String goods_id)
	{
		sql = "INSERT INTO t_order_goods (order_id,goods_id)VALUES(?,?)";
		try {
			conn = getConnection();
			ps = getPreparedStatement(conn, sql);
			ps.setObject(1, order_id);
			ps.setObject(2, goods_id);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			close(conn, ps, null);
		}
	}
	/**
	 * 后台分页查询未发货的订单
	 * @return
	 */
	public List<Order> selectunOrderAdmin(int startRow,int pageSize)
	{
		sql = "SELECT * FROM t_order WHERE state=FALSE limit ?,?";
		List<Order> orderList = new ArrayList<Order>();
		try {
			conn = getConnection();
			ps = getPreparedStatement(conn, sql);
			ps.setObject(1, startRow);
			ps.setObject(2, pageSize);
			rs = ps.executeQuery();
			rsUtil(orderList);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			close(conn, ps, rs);
		}
		return orderList;
	}
	
	/**
	 * 后台未发货订单个数
	 * @return
	 */
	public int unOrderCountAdmin()
	{
		sql = "SELECT count(*) FROM t_order WHERE state=FALSE";
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
	 * 后台分页查询已发货的订单
	 * @return
	 */
	public List<Order> selectOrderAdmin(int startRow,int pageSize)
	{
		sql = "SELECT * FROM t_order WHERE state=TRUE limit ?,?";
		List<Order> orderList = new ArrayList<Order>();
		try {
			conn = getConnection();
			ps = getPreparedStatement(conn, sql);
			ps.setObject(1, startRow);
			ps.setObject(2, pageSize);
			rs = ps.executeQuery();
			rsUtil(orderList);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			close(conn, ps, rs);
		}
		return orderList;
	}
	
	/**
	 * 后台已发货订单个数
	 * @return
	 */
	public int OrderCountAdmin()
	{
		sql = "SELECT count(*) FROM t_order WHERE state=true";
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
}
