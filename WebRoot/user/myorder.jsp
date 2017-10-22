<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/userhead.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
							<table border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td height="30" align="center" width="270">
										<span class="fontbb">订单编号</span>
									</td>
									<td width="200" align="center">
										<span class="fontbb">是否付款</span>
									</td>
									<td width="200" align="center">
										<span class="fontbb">是否发货</span>
									</td>
								</tr>
								<tr><td colspan="3">
								<div class="dvsolid"></div>
								</td></tr>
								<c:forEach items="${orderList}" var="order">
										<tr>
									<td height="30" align="center">
										<a href="${ctx }/order.do?method=selectOrderUser&order_id=${order.order_id }">${order.order_id }</a>
									</td>
									<td align="center">
										<c:choose>
											<c:when test="${order.ispay}">
												<span>已付款</span>
											</c:when>
											<c:when test="${order.payment=='货到付款'}">
												<span>货到付款</span>
											</c:when>
											<c:otherwise>
													<span>未付款</span>
											</c:otherwise>
										</c:choose>
									</td>
									<td align="center">
											<c:choose>
												<c:when test="${order.state}">
													<span>已发货</span>
												</c:when>
												<c:otherwise>
													<span>未发货</span>
											</c:otherwise>
											</c:choose>
									</td>
								</tr>
								<tr><td colspan="3">
								<div class="dvdashed"></div>
								</td></tr>
								</c:forEach>						
							</table>
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
