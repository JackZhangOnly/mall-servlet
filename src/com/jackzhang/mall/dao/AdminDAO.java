package com.jackzhang.mall.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.jackzhang.mall.util.DBManager;
import com.jackzhang.mall.vo.Admin;

/**
 * 管理员DAO
 * @author jackzhang
 *
 */
public class AdminDAO extends DBManager {

	private String sql="";
	private Connection conn =null;
	private PreparedStatement ps =null;
	private ResultSet rs = null;
	
	public int add(Admin admin)
	{
		int no = 0;
		sql = "INSERT INTO t_admin (admin_id,`name`,`password`,isfreeze)VALUES(?,?,?,?)";
		try {
			conn = getConnection();
			ps = getPreparedStatement(conn, sql);
			ps.setObject(1, admin.getAdmin_id());
			ps.setObject(2, admin.getName());
			ps.setObject(3, admin.getPassword());
			ps.setObject(4, admin.isIsfreeze());
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
		sql ="DELETE FROM t_admin WHERE admin_id =?";
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
	
	public int upd(Admin admin)
	{
		int no = 0;
		sql ="UPDATE t_admin SET `name`=?,`password`=?,isfreeze=? WHERE admin_id=?";
		try {
			conn = getConnection();
			ps = getPreparedStatement(conn, sql);
			ps.setObject(1, admin.getName());
			ps.setObject(2, admin.getPassword());
			ps.setObject(3, admin.isIsfreeze());
			ps.setObject(4, admin.getAdmin_id());
			no = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			close(conn, ps, null);
		}
		return no;
	}
	
	/**
	 * 分页全查
	 * @param startRow
	 * @param pageSize
	 * @return
	 */
	public List<Admin> selectByPageAdmin(int startRow,int pageSize)
	{
		sql ="SELECT * FROM t_admin  limit ?,?";
		List<Admin> adminList = new ArrayList<Admin>();
		try {
			conn = getConnection();
			ps = getPreparedStatement(conn, sql);
			ps.setObject(1, startRow);
			ps.setObject(2, pageSize);
			rs = ps.executeQuery();
			while(rs.next())
			{
				Admin admin = new Admin();
				admin.setAdmin_id(rs.getString("admin_id"));
				admin.setName(rs.getString("name"));
				admin.setPassword(rs.getString("password"));
				admin.setIsfreeze(rs.getBoolean("isfreeze"));
				adminList.add(admin);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			close(conn, ps, rs);
		}
		return adminList;
	}
	/**
	 * 查询单个管理员
	 * @param admin_id
	 * @return
	 */
	public Admin select(String admin_id)
	{
		sql ="SELECT * FROM t_admin where admin_id=?";
		Admin admin = new Admin();
		try {
			conn = getConnection();
			ps = getPreparedStatement(conn, sql);
			ps.setObject(1, admin_id);
			rs = ps.executeQuery();
			if(rs.next())
			{
				admin.setAdmin_id(rs.getString("admin_id"));
				admin.setName(rs.getString("name"));
				admin.setPassword(rs.getString("password"));
				admin.setIsfreeze(rs.getBoolean("isfreeze"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			close(conn, ps, rs);
		}
		return admin;
	}
	
	/**
	 * 后台查询管理员总条数
	 * @return
	 */
	public int getCountAdmin()
	{
		int no = 0 ;
		sql = "SELECT count(*) FROM t_admin";
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
	 * 后台管理员登录验证
	 * @param name
	 * @param password
	 * @return
	 */
	public Admin loginByNameAndPasswordAdmin(String name,String password)
	{
		sql="SELECT * FROM t_admin WHERE `name`=? AND `password`=?";
		Admin admin = new Admin();
		try {
			conn = getConnection();
			ps = getPreparedStatement(conn, sql);
			ps.setObject(1, name);
			ps.setObject(2, password);
			rs = ps.executeQuery();
			if(rs.next())
			{
				admin.setAdmin_id(rs.getString("admin_id"));
				admin.setName(rs.getString("name"));
				admin.setPassword(rs.getString("password"));
				admin.setIsfreeze(rs.getBoolean("isfreeze"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			close(conn, ps, rs);
		}
		return admin;
	}
	
	/**
	 * 验证管理员名是否重复
	 * @param name
	 * @return
	 */
	public boolean authenticationAdminName(String name)
	{
		sql = "SELECT COUNT(*) FROM t_admin WHERE NAME = ?";
		int no = 0;
		try {
			conn = getConnection();
			ps = getPreparedStatement(conn, sql);
			ps.setObject(1, name);
			rs = ps.executeQuery();
			if(rs.next())
			{
				 no = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return no==0?false:true;
	}
}
