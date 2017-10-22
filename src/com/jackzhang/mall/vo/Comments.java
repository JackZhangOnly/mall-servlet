package com.jackzhang.mall.vo;
/**
 * 评论实体类
 * @author jackzhang
 *
 */
public class Comments {
	
	private String comments_id;
	//评论
	private String comment;
	//评论的商品ID
	private Goods goods;
	//创建时间
	private String createtime;
	//评论人ID
	private User user;
	
	//get and set
	public String getCreatetime() {
		return createtime;
	}
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	public String getComments_id() {
		return comments_id;
	}
	public void setComments_id(String comments_id) {
		this.comments_id = comments_id;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public Goods getGoods() {
		return goods;
	}
	public void setGoods(Goods goods) {
		this.goods = goods;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
}
