package com.jackzhang.mall.vo;

/**
 * 管理员实体类
 * @author jackzhang
 *
 */
public class Admin {
	//管理员ID
	private String admin_id;
	//管理员姓名
	private String name;
	//管理员密码
	private String password;
	//是否冻结
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
