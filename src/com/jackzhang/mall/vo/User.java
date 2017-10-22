package com.jackzhang.mall.vo;

/**
 * 用户实体类
 * @author jackzhang
 *
 */
public class User {

	private String user_id;
	private String name;
	private String password;
	//充值时间
	private String time;
	//账户余额
	private double balance;
	//性别
	private boolean sex;
	//地址
	private String address;
	//电话
	private String phone;
	private String email;
	//是否冻结
	private boolean isfreeze;
	//删除状态
	private boolean state;
	//邮编
	private String zip;
	
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
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
	public boolean isSex() {
		return sex;
	}
	public void setSex(boolean sex) {
		this.sex = sex;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public boolean isIsfreeze() {
		return isfreeze;
	}
	public void setIsfreeze(boolean isfreeze) {
		this.isfreeze = isfreeze;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public boolean isState() {
		return state;
	}
	public void setState(boolean state) {
		this.state = state;
	}
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}
}
