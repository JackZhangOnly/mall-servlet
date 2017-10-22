package com.jackzhang.mall.service;

import java.util.List;

import com.jackzhang.mall.dao.TypeDAO;
import com.jackzhang.mall.vo.Type;

/**
 * ��Ʒ����SERVICE
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
	 * ����FID��ѯ��ID
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
	 * ��ѯ���и���𣬼����в���Ҫת�ظ�����µ������
	 * @param fid
	 * @return
	 */
	public List<Type> selectByFidNo(String fid)
	{
		return typeDAO.selectByFid(fid);
	}
	/**
	 * �������ID��ѯ������
	 * @param type_id
	 * @return
	 */
	public Type select (String type_id)
	{
		return typeDAO.select(type_id);
	}
	
	/**
	 * �������2�����
	 * @return
	 */
	public List<Type> selectByson()
	{
		return typeDAO.selectByson();
	}
}
