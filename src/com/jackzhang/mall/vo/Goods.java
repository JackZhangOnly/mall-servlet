package com.jackzhang.mall.vo;

/**
 * ��Ʒʵ����
 * @author jackzhang
 *
 */
public class Goods {
	private String goods_id;
	//��Ʒ����
	private String name;
	private double price;
	//ͼƬ
	private String img;
	//����
	private String desc;
	//��������
	private Type type;
	private boolean state;
	//����ʱ��
	private String createtime;
	
	//get and set
	public String getGoods_id() {
		return goods_id;
	}
	public void setGoods_id(String goods_id) {
		this.goods_id = goods_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public Type getType() {
		return type;
	}
	public void setType(Type type) {
		this.type = type;
	}
	public boolean isState() {
		return state;
	}
	public void setState(boolean state) {
		this.state = state;
	}
	public String getCreatetime() {
		return createtime;
	}
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	
}
