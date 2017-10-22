<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/userhead.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title></title>
	</head>
	<body>
		<div class="dvbody"
			style="padding-left: 1px; padding-right: 1px; text-align: left;">
			<table width="100%" border="0" cellspacing="0" cellpadding="6">
				<tr>
					<td width="260" align="center" style="padding: 3px" valign="top">
						<table width="100%" border="0" cellpadding="0" cellspacing="0"
							bgcolor="#FFFFFF" class="usertb">
							<tr>
								<th height="24" align="left">
									我的订单
								</th>
							</tr>
							<tr>
								<td height="24">
									<a href="${ctx }/order.do?method=select">订单管理</a>
								</td>
							</tr>
						</table>
						<table width="100%" border="0" cellpadding="0" cellspacing="0"
							bgcolor="#FFFFFF" class="usertb">
							<tr>
								<th height="24" align="left">
									我的账户
								</th>
							</tr>
							<tr>
								<td height="24">
									<a href="${ctx }/user/balance.jsp">账户管理</a>
								</td>
							</tr>
						</table>
						<table width="100%" border="0" cellpadding="0" cellspacing="0"
							bgcolor="#FFFFFF" class="usertb">
							<tr>
								<th height="24" align="left">
									我的商品
								</th>
							</tr>
							<tr>
								<td height="24">
									<a href="${ctx }/goods.do?method=selectByUser_idAdmin">已买到的商品</a> 
								</td>
							</tr>
						</table>
						<table width="100%" border="0" cellpadding="0" cellspacing="0"
							bgcolor="#FFFFFF" class="usertb">
							<tr>
								<th height="24" align="left">
									我的评论
								</th>
							</tr>
							<tr>
								<td height="24">
									<a href="${ctx }/comments.do?method=pageUser&user_id=${user.user_id }">评论管理</a>
								</td>
							</tr>
						</table>
						<table width="100%" border="0" cellpadding="0" cellspacing="0"
							bgcolor="#FFFFFF" class="usertb">
							<tr>
								<th height="24" align="left">
									我的个人信息
								</th>
							</tr>
							<tr>
								<td height="24">
									<a href="${ctx }/user/updateuser.jsp">编辑个人信息</a>
								</td>
							</tr>
							<tr>
								<td height="24">
									<a href="${ctx }/user/updatepwd.jsp">修改密码</a>
								</td>
							</tr>
						</table>
						<div style="padding-top: 24px; padding-bottom: 24px">
							<a href="${ctx }/user.do?method=out">退出登录</a>
						</div>
					</td>
					<td valign="top">
						<div style="border: 1px solid #BBCAEF; padding: 5px;">
							<c:forEach items="${commentsList}" var="comments">
							<div
								style="padding: 6px; border: 1px solid #FFCD06; margin-bottom: 6px;">
										${comments.comment }
								<div class="dvdashed"></div>
								<div style="text-align: right; line-height: 20px;">
									${comments.createtime } 评论商品:
									<a href="${ctx }/goods.do?method=goodsShow&goods_id=${comments.goods.goods_id }">${comments.goods.name }</a>
								</div>
							</div>
						</c:forEach>
							
							
							<div
								style="padding: 6px; border: 1px solid #FFCD06; text-align: center;">
								共${page.totalPages }页 当前第${page.currentPage}页　　<a
													href="${ctx }/comments.do?method=pageUser&user_id=${user.user_id }&pageMethod=first&pageCount=${page.currentPage }">首页</a>
												<a
													href="${ctx }/comments.do?method=pageUser&user_id=${user.user_id }&pageMethod=previous&pageCount=${page.currentPage }">上一页</a>
												<a
													href="${ctx }/comments.do?method=pageUser&user_id=${user.user_id }&pageMethod=next&pageCount=${page.currentPage }">下一页</a>
												<a
													href="${ctx }/comments.do?method=pageUser&user_id=${user.user_id }&pageMethod=last&pageCount=${page.currentPage }">末页</a>
							</div>
						</div>
					</td>
				</tr>
			</table>


			<div class="bottomline"></div>
			<div style="color: #4E4E4E; text-align: center">
				Copyright © 2009 U-Bai.com - ICP: 沪ICP备 09033184
			</div>
		</div>
	</body>
</html>
