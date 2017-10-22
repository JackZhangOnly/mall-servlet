package com.jackzhang.mall.vo;

/**
 * 订单实体类
 * @author jackzhang
 *
 */
public class Order {
	private String order_id;
	//收货人
	private String consignee;
	//订单总价
	private double total;
	//付款方式
	private String payment;
	//订单状态
	private boolean state;
	//下单时间
	private String time;
	//属于用户
	private User user;
	//收货地址
	private String address;
	//收货人电话
	private String phone;
	//收货人邮编
	private String postcard;
	//送货方式
	private String delivery;
	//运费
	private String freight;
	//是否付款
	private boolean ispay;
	
	//get and set
	public boolean isIspay() {
		return ispay;
	}
	public void setIspay(boolean ispay) {
		this.ispay = ispay;
	}
	public String getOrder_id() {
		return order_id;
	}
	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}
	public String getConsignee() {
		return consignee;
	}
	public void setConsignee(String consignee) {
		this.consignee = consignee;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public String getPayment() {
		return payment;
	}
	public void setPayment(String payment) {
		this.payment = payment;
	}
	public boolean isState() {
		return state;
	}
	public void setState(boolean state) {
		this.state = state;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
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
	public String getPostcard() {
		return postcard;
	}
	public void setPostcard(String postcard) {
		this.postcard = postcard;
	}
	public String getDelivery() {
		return delivery;
	}
	public void setDelivery(String delivery) {
		this.delivery = delivery;
	}
	public String getFreight() {
		return freight;
	}
	public void setFreight(String freight) {
		this.freight = freight;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
}
