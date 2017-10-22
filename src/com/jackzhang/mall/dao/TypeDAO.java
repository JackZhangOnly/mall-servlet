package com.jackzhang.mall.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.jackzhang.mall.util.DBManager;
import com.jackzhang.mall.vo.Type;

/**
 * 商品类型DAO
 * @author jackzhang
 *
 */
public class TypeDAO extends DBManager
{
	private String sql="";
	private Connection conn =null;
	private PreparedStatement ps =null;
	private ResultSet rs = null;
	
	public int add(Type type)
	{
		int no = 0 ;
		sql = "insert into t_type (type_id, name, fid, state)values(?, ?, ?, ?)";
		try {
			conn = getConnection();
			ps = getPreparedStatement(conn, sql);
			ps.setObject(1, type.getType_id());
			ps.setObject(2, type.getName());
			ps.setObject(3, type.getFid());
			ps.setObject(4, type.isState());
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
		sql = "delete from t_type where type_id= ? ";
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
	
	public int upd(Type type)
	{
		int no = 0;
		sql = "update t_type  `name` = ? , fid = ? , state = ?  WHERE type_id = ? ";
		try {
			conn = getConnection();
			ps = getPreparedStatement(conn, sql);
			ps.setObject(1, type.getName());
			ps.setObject(2, type.getName());
			ps.setObject(3, type.getFid());
			ps.setObject(4, type.isState());
			ps.setObject(5, type.getType_id());
			no = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			close(conn, ps, null);
		}
		return no;
	}
	
	public List<Type> selectAll()
	{
		sql = "select * from t_type";
		List<Type> typeList = new ArrayList<Type>();
		try {
			conn = getConnection();
			ps = getPreparedStatement(conn, sql);
			rs = ps.executeQuery();
			while(rs.next())
			{
				Type type = new Type();
				type.setType_id(rs.getString("type_id"));
				type.setName(rs.getString("name"));
				type.setFid(rs.getString("fid"));
				type.setState(rs.getBoolean("state"));
				typeList.add(type);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			close(conn, ps, rs);
		}
		return typeList;
	}
	
	/**
	 * 根据FID查询子类型对象集合
	 * @return
	 */
	public List<Type> selectByFid(String fid)
	{
		sql = "select * from t_type where state=true and fid=?";
		List<Type> list = new ArrayList<Type>();
		try {
			conn = getConnection();
			ps = getPreparedStatement(conn, sql);
			ps.setObject(1, fid);
			rs = ps.executeQuery();
			while(rs.next())
			{
				Type type = new Type();
				type.setType_id(rs.getString("type_id"));
				type.setName(rs.getString("name"));
				type.setFid(rs.getString("fid"));
				type.setState(rs.getBoolean("state"));
				list.add(type);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally{
			close(conn, ps, rs);
		}
		return list;
	}
	
	/**
	 * 根据类别ID查询类别对象
	 * @param type_id
	 * @return
	 */
	public Type select(String type_id)
	{
		sql ="select * from t_type where type_id= ?";
		Type type = new Type();
		try {
			conn = getConnection();
			ps = getPreparedStatement(conn, sql);
			ps.setObject(1, type_id);
			rs = ps.executeQuery();
			if(rs.next())
			{
				type.setType_id(rs.getString("type_id"));
				type.setName(rs.getString("name"));
				type.setFid(rs.getString("fid"));
				type.setState(rs.getBoolean("state"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally{
			close(conn, ps, rs);
		}
		return type;
	}
	
	/**
	 * 获得所有2级类别
	 * @return
	 */
	public List<Type> selectByson()
	{
		sql = "SELECT * FROM t_type WHERE fid <> 0 ";
		List<Type> list = new ArrayList<Type>();
		try {
			conn = getConnection();
			ps = getPreparedStatement(conn, sql);
			rs = ps.executeQuery();
			while(rs.next())
			{
				Type type = new Type();
				type.setType_id(rs.getString("type_id"));
				type.setName(rs.getString("name"));
				type.setFid(rs.getString("fid"));
				type.setState(rs.getBoolean("state"));
				list.add(type);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			close(conn, ps, rs);
		}
		return list;
	}
}
