package com.jackzhang.mall.vo;

/**
 * ����Աʵ����
 * @author jackzhang
 *
 */
public class Admin {
	//����ԱID
	private String admin_id;
	//����Ա����
	private String name;
	//����Ա����
	private String password;
	//�Ƿ񶳽�
	private boolean isfreeze;
	
	//get and set
	public String getAdmin_id() {
		return admin_id;
	}
	public void setAdmin_id(String admin_id) {
		this.admin_id = admin_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean isIsfreeze() {
		return isfreeze;
	}
	public void setIsfreeze(boolean isfreeze) {
		this.isfreeze = isfreeze;
	}
}
