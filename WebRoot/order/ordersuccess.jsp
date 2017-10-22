<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/head.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>U-bai - 购物车</title>
		<link href="${ctx}/css/list.css" rel="stylesheet" type="text/css" />
	</head>
		<body onload="$('#gwcar').click()">
		<form action="${ctx }/order.do?method=verifyAddresses" method="post">
			<div class="dvbody">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td width="210" valign="top">
							<div class="listleft">
								<div class="dvtypename">
									<span>商品目录</span>
								</div>
								<c:forEach items="${typeList}" var="type">
									<a href="${ctx}/goods.do?method=page&type_id=${type.type_id }"><span>${type.name
											}</span> </a>
								</c:forEach>
							</div>
						</td>
						<td valign="top">
							<div class="listright" id="listright">
								<table border="0" cellspacing="0" cellpadding="0" width="711">
									<tr>
										<td>
										<c:choose>
											<c:when test="${order.payment=='货到付款'}">
												<div
												style="line-height: 24px; font-weight: bold; font-size: 16px;">
												订单${order.order_id }已提交，预计1-2天从北京发货，
												<a href="${ctx }/order.do?method=selectOrderUser&order_id=${order.order_id }">查看订单状态</a>
												<br />
												您需要支付
												<span class="fontred">￥${order.total }</span>，请在收货时向送货员支付订单款项。
											</div>
											</c:when>
											<c:when test="${order.ispay}">
													<div style="font-weight: bold; font-size: 16px;">
												<div style="font-size: 14px; padding-bottom: 10px;">
													已从您的账户扣除订单商品金额
													<span class="fontred">￥${order.total }</span>
												</div>
												订单${order.order_id }已提交，预计1-2天从北京发货，
												<a href="${ctx }/order.do?method=selectOrderUser&order_id=${order.order_id }">查看订单状态</a>
												<br />
											</div>
											</c:when>
											<c:otherwise>
												<div style="font-weight: bold; font-size: 16px;">
												<div style="font-size: 14px; padding-bottom: 10px;">
													您的账户余额为￥
													<span class="fontred">￥${user.balance }</span>，此订单订单商品金额为
													<span class="fontred">￥${order.total }</span>，余额不足，
													<a href="${ctx }/user/balance.jsp">充值</a>
												</div>
												订单${order.order_id }已提交，预计1-2天从北京发货，
												<a href="${ctx }/order.do?method=selectOrderUser&order_id=${order.order_id }">查看订单状态</a>
												<br />
											</div>
											</c:otherwise>
										</c:choose>
										</td>
									</tr>
									<tr>
										<td>
											<div class="dvdashed"></div>
										</td>
									</tr>
									<tr>
										<td align="center">
											* 您可以在“我的订单”中查看或取消您的订单，由于系统需进行订单预处理，您可能不会立刻查询到刚提交的订单。
										</td>
									</tr>
								</table>
							</div>
						</td>
					</tr>
				</table>
			</div>
		</form>
	</body>
</html>

<%@ include file="/common/bottom.jsp"%>