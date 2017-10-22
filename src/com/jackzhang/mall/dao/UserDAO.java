package com.jackzhang.mall.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.jackzhang.mall.util.DBManager;
import com.jackzhang.mall.vo.User;

/**
 * 用户DAO
 * @author jackzhang
 *
 */
public class UserDAO extends DBManager
{
	private String sql="";
	private Connection conn =null;
	private PreparedStatement ps =null;
	private ResultSet rs = null;
	public int add(User user)
	{
		int no = 0;
		sql ="insert into t_user (user_id, `name`, `password`, sex, address, phone, email, isfreeze, `time`, balance, state,zip)values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?,?)";
		try {
			conn = getConnection();
			ps = getPreparedStatement(conn, sql);
			ps.setObject(1, user.getUser_id());
			ps.setObject(2, user.getName());
			ps.setObject(3, user.getPassword());
			ps.setObject(4, user.isSex());
			ps.setObject(5, user.getAddress());
			ps.setObject(6, user.getPhone());
			ps.setObject(7, user.getEmail());
			ps.setObject(8, user.isIsfreeze());
			ps.setObject(9, user.getTime());
			ps.setObject(10, user.getBalance());
			ps.setObject(11, user.isState());
			ps.setObject(12, user.getZip());
			no = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			close(conn, ps,null);
		}
		return no;
	}
	
	public int del(String id)
	{
		int no = 0;
		sql = "delete from t_user where user_id =?";
		try {
			conn = getConnection();
			ps = getPreparedStatement(conn, sql);
			ps.setObject(1, id);
			no = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally
		{
			close(conn, ps,null);
		}
		return no;
	}
	
	public int upd(User user)
	{
		int no = 0;
		sql = "update t_user set `name` = ? , `password` = ? , sex = ? , address = ? , phone = ? , email = ? , isfreeze = ? , `time` = ? , balance = ? , state = ?,zip = ?  where  user_id = ? ";
		try {
			conn = getConnection();
			ps = getPreparedStatement(conn, sql);
			ps.setObject(1, user.getName());
			ps.setObject(2, user.getPassword());
			ps.setObject(3, user.isSex());
			ps.setObject(4, user.getAddress());
			ps.setObject(5, user.getPhone());
			ps.setObject(6, user.getEmail());
			ps.setObject(7, user.isIsfreeze());
			ps.setObject(8, user.getTime());
			ps.setObject(9, user.getBalance());
			ps.setObject(10, user.isState());
			ps.setObject(11, user.getZip());
			ps.setObject(12, user.getUser_id());
			no = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			close(conn, ps,null);
		}
		return no;
	}
	public List<User> selectAll(int startRow,int pageSize)
	{
		sql ="select * from t_user where state = FALSE  limit ?,?";
		List<User> userList = new ArrayList<User>();
		try {
			conn = getConnection();
			ps = getPreparedStatement(conn, sql);
			ps.setObject(1, startRow);
			ps.setObject(2, pageSize);
			rs = ps.executeQuery();
			rsUtil(userList);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally{
			close(conn, ps, rs);
		}
		return userList;
	}
	/**
	 * rs帮助类
	 * @param userList
	 * @throws SQLException
	 */
	private void rsUtil(List<User> userList) throws SQLException {
		while(rs.next())
		{
			User user = new User();
			user.setUser_id(rs.getString("user_id"));
			user.setName(rs.getString("name"));
			user.setPassword(rs.getString("password"));
			user.setTime(rs.getString("time"));
			user.setBalance(rs.getDouble("balance"));
			user.setSex(rs.getBoolean("sex"));
			user.setAddress(rs.getString("address"));
			user.setPhone(rs.getString("phone"));
			user.setEmail(rs.getString("email"));
			user.setIsfreeze(rs.getBoolean("isfreeze"));
			user.setState(rs.getBoolean("state"));
			user.setZip(rs.getString("zip"));
			userList.add(user);
		}
	}
	
	/**
	 * 用户登录
	 * @param name
	 * @param password
	 * @return
	 */
	public User login(String name,String password)
	{
		sql = "select * from t_user where state=false and isfreeze=false and name=? and password = ?";
		User user = new User();
		try {
			conn = getConnection();
			ps = getPreparedStatement(conn, sql);
			ps.setObject(1, name);
			ps.setObject(2, password);
			rs = ps.executeQuery();
			if(rs.next())
			{
				user.setUser_id(rs.getString("user_id"));
				user.setName(rs.getString("name"));
				user.setPassword(rs.getString("password"));
				user.setTime(rs.getString("time"));
				user.setBalance(rs.getDouble("balance"));
				user.setSex(rs.getBoolean("sex"));
				user.setAddress(rs.getString("address"));
				user.setPhone(rs.getString("phone"));
				user.setEmail(rs.getString("email"));
				user.setIsfreeze(rs.getBoolean("isfreeze"));
				user.setState(rs.getBoolean("state"));
				user.setZip(rs.getString("zip"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			close(conn, ps, rs);
		}
		return user;
	}
	/**
	 * 注册时验证用户名是否存在
	 * @param name
	 * @param password
	 * @return
	 */
	public User authenticationName(String name)
	{
		sql = "SELECT * FROM t_user WHERE state=FALSE AND isfreeze=FALSE AND NAME= ?";
		User user = new User();
		try {
			conn = getConnection();
			ps = getPreparedStatement(conn, sql);
			ps.setObject(1, name);
			rs = ps.executeQuery();
			if(rs.next())
			{
				user.setUser_id(rs.getString("user_id"));
				user.setName(rs.getString("name"));
				user.setPassword(rs.getString("password"));
				user.setTime(rs.getString("time"));
				user.setBalance(rs.getDouble("balance"));
				user.setSex(rs.getBoolean("sex"));
				user.setAddress(rs.getString("address"));
				user.setPhone(rs.getString("phone"));
				user.setEmail(rs.getString("email"));
				user.setIsfreeze(rs.getBoolean("isfreeze"));
				user.setState(rs.getBoolean("state"));
				user.setZip(rs.getString("zip"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			close(conn, ps, rs);
		}
		return user;
	}
	/**
	 * 查询单个用户
	 * @param user_id
	 * @return
	 */
	public User select(String user_id)
	{
		sql = "select * from t_user where state = FALSE  and user_id = ?";
		User user = new User();
		try {
			conn = getConnection();
			ps = getPreparedStatement(conn, sql);
			ps.setObject(1, user_id);
			rs = ps.executeQuery();
			if(rs.next())
			{
				user.setUser_id(rs.getString("user_id"));
				user.setName(rs.getString("name"));
				user.setPassword(rs.getString("password"));
				user.setTime(rs.getString("time"));
				user.setBalance(rs.getDouble("balance"));
				user.setSex(rs.getBoolean("sex"));
				user.setAddress(rs.getString("address"));
				user.setPhone(rs.getString("phone"));
				user.setEmail(rs.getString("email"));
				user.setIsfreeze(rs.getBoolean("isfreeze"));
				user.setState(rs.getBoolean("state"));
				user.setZip(rs.getString("zip"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			close(conn, ps, rs);
		}
		return user;
	}
	
	/**
	 * 总条数
	 * @return
	 */
	public int count ()
	{
		sql = "select count(*) from t_user where state = FALSE";
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
