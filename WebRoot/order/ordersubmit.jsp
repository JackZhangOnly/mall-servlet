<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/head.jsp"%>

<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>U-bai - 购物车</title>
		<link href="${ctx}/css/list.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript">
			function show(id)
			{
				window.location.href="${ctx}/goods.do?method=goodsShow&goods_id="+id;
			}
			function paymentSum(flag,sum)
			{
				if(flag=='true')
				{
					document.getElementById('paymentSum').innerHTML="￥"+(sum+5.0);
					document.getElementById('sum').value=sum+5.0;
				}
				else
				{
					document.getElementById('paymentSum').innerHTML="￥"+(sum+0.0);
					document.getElementById('sum').value=sum+0.0;
				}
			}
		</script>
	</head>
		<body onload="$('#gwcar').click()">
		<div class="dvbody">
			<form action="${ctx}/order.do?method=add" method="post" id="myform">
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
							<div class="carhead">
								<div>
									1. 我的购物车 2. 确认收货地址
									<span>3. 付费</span>
								</div>
							</div>
							<div class="listright" id="listright">
								<div class="dvnavigation">
									<span class="showtitle">订单发往卖家：<span>U-bai</span> </span>
								</div>
								<div class="dvshow">
									<table width="100%" border="0" cellspacing="0" cellpadding="0">
										<tr>
											<td width="64"></td>
											<td width="270">
												产品名称
											</td>
											<td width="100">
												单价（￥）
											</td>
											<td width="77">
												总价（￥）
											</td>
											<td width="100" align="center">
												数量
											</td>
											<td width="100" align="center">
												&nbsp;
											</td>
										</tr>
										<!--  -->
										<c:forEach items="${gwc}" var="gwc">
											<c:forEach items="${gwc.value}" var="g">
												<tr>
													<td colspan="6">
														<div class="dvsolid"></div>
													</td>
												</tr>
												<tr valign="middle">
													<td>
														<div class="carimg">
															<img width="50" height="50" src="${ctx}/${g.key.img}"
																onclick="javascript:show(${g.key.goods_id })" />
														</div>
													</td>
													<td class="cargood">
														<a href="javascript:show(${g.key.goods_id })">${g.key.name}</a>
													</td>
													<td>
														￥${g.key.price}
													</td>
													<td>
														￥${g.key.price*g.value}
														<c:set var="sum" value="${g.key.price*g.value+sum}"
															scope="page" />
													</td>
													<td align="center">
														${ g.value}
													</td>
													<td align="center">
														&nbsp;
													</td>
												</tr>
												<tr>
													<td colspan="6">
														<div class="dvdashed"></div>
													</td>
												</tr>
											</c:forEach>
										</c:forEach>
										<!--  -->
										<tr>
											<td colspan="6" align="right">
												<span class="sptprice">总价：<span class="fontred">￥<fmt:formatNumber
															value="${sum }" /> </span> </span>
											</td>
										</tr>
										<tr>
											<td colspan="6">
												<div class="dvsolid"></div>
											</td>
										</tr>
									</table>
								</div>
								<table width="711" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td height="30" style="font-weight: bold">
											送货方式
										</td>
										<td>
											&nbsp;
										</td>
										<td>
											&nbsp;
										</td>
									</tr>
									<tr>
										<td height="30">
											&nbsp;
										</td>
										<td colspan="2">
											<input type="radio" name="payment" checked="checked"
												value="5" onclick="paymentSum('true',${sum })" />
											普通快递上门（支持货到付款） 运费：
											<span class="fontred">5元</span>
										</td>
									</tr>
									<tr>
										<td height="30">
											&nbsp;
										</td>
										<td colspan="2">
											<input type="radio" name="payment" value="0"
												onclick="paymentSum('fales',${sum })" />
											普通邮递（不支持货到付款）运费：
											<span class="fontred">免运费</span>
										</td>
									</tr>
									<tr>
										<td colspan="3">
											<div class="dvdashed"></div>
										</td>
									</tr>
									<tr>
										<td height="30" style="font-weight: bold">
											付款方式
										</td>
										<td>
											&nbsp;
										</td>
										<td>
											&nbsp;
										</td>
									</tr>
									<tr>
										<td height="30">
											&nbsp;
										</td>
										<td colspan="2">
											<input type="radio" name="pay" checked="checked" value="ubai" />
											U-bai账户付款 （当前余额 :
											<span class="fontred"><fmt:formatNumber
													value="${user.balance }" />元</span>）
										</td>
									</tr>
									<tr>
										<td height="30">
											&nbsp;
										</td>
										<td colspan="2">
											<input type="radio" name="pay" value="cod" />
											货到付款（您需要在收货时用现金向送货员支付订单款项。）
										</td>
									</tr>
									<tr>
										<td colspan="3">
											<div class="dvsolid"></div>
										</td>
									</tr>
									<tr>
										<td colspan="3" align="right" height="30">
											<span class="sptprice">您总共需要支付 : <span id="paymentSum"
												class="fontred">￥<fmt:formatNumber value="${sum+5 }" />
											</span> </span>
											<input type="hidden" id="sum" name="sum" value="${sum+5 }">
										</td>
									</tr>
									<tr>
										<td colspan="3" align="right">
											<a href="javascript:myform.submit()"> <img
													src="${ctx}/images/buynowB_zh.gif"></img>
											</a>
										</td>
									</tr>
								</table>
							</div>
						</td>
					</tr>
				</table>
			</form>
		</div>
	</body>
</html>

<%@ include file="/common/bottom.jsp"%>