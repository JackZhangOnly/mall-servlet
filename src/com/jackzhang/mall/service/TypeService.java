package com.jackzhang.mall.service;

import java.util.List;

import com.jackzhang.mall.dao.TypeDAO;
import com.jackzhang.mall.vo.Type;

/**
 * 商品类型SERVICE
 * @author jackzhang
 *
 */
public class TypeService 
{
	private TypeDAO typeDAO =new TypeDAO();
	
	public int add(Type type)
	{
		return typeDAO.add(type);
	}
	
	public int del(String id)
	{
		return typeDAO.del(id);
	}
	
	public int upd(Type type)
	{
		return typeDAO.upd(type);
	}
	
	public List<Type> selectAll()
	{
		return typeDAO.selectAll();
	}
	
	/**
	 * 根据FID查询子ID
	 * @param fid
	 * @return
	 */
	public List<Type> selectByFid(String fid)
	{
		List<Type> list = typeDAO.selectByFid(fid);
		for (Type type : list) {
			List<Type> subList = typeDAO.selectByFid(type.getType_id());
			type.setSubList(subList);
		}
		return list;
	}
	/**
	 * 查询所有父类别，集合中不需要转载父类别下的子类别
	 * @param fid
	 * @return
	 */
	public List<Type> selectByFidNo(String fid)
	{
		return typeDAO.selectByFid(fid);
	}
	/**
	 * 根据类别ID查询类别对象
	 * @param type_id
	 * @return
	 */
	public Type select (String type_id)
	{
		return typeDAO.select(type_id);
	}
	
	/**
	 * 获得所有2级类别
	 * @return
	 */
	public List<Type> selectByson()
	{
		return typeDAO.selectByson();
	}
}
