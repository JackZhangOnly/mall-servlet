package com.jackzhang.mall.vo;

import java.util.List;

/**
 * ��Ʒ����ʵ����
 * @author jackzhang
 *
 */
public class Type {
	
	private String type_id;
	//��������
	private String name;
	//��ID
	private String fid;
	//�Ƿ����
	private boolean state;
	//�������µ�����𼯺�
	private List<Type> subList;
	
	//get and set
	public List<Type> getSubList() {
		return subList;
	}
	public void setSubList(List<Type> subList) {
		this.subList = subList;
	}
	public String getType_id() {
		return type_id;
	}
	public void setType_id(String type_id) {
		this.type_id = type_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getFid() {
		return fid;
	}
	public void setFid(String fid) {
		this.fid = fid;
	}
	public boolean isState() {
		return state;
	}
	public void setState(boolean state) {
		this.state = state;
	}
}
