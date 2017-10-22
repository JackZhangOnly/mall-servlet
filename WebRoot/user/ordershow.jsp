<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/userhead.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title></title>
		<script type="text/javascript">
			function back()
			{
				window.history.go("-1");
			}
			function buy(id)
			{
				if(confirm("你确信要付款吗？"))
   				{
        			window.location.href="${ctx}/order.do?method=buyUser&order_id="+id;
    			}			
			}
		</script>
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
									<td height="30" colspan="2" class="fontbb">
										${order.order_id }
									</td>
								</tr>
								<tr>
									<td colspan="2">
										<div class="dvsolid"></div>
									</td>
								</tr>
								<tr>
									<td width="100" height="30" align="right">
										收货人：
									</td>
									<td width="570">
										${order.consignee }
									</td>
								</tr>
								<tr>
									<td colspan="2">
										<div class="dvdashed"></div>
									</td>
								</tr>
								<tr>
									<td height="30" align="right">
										收货地址：
									</td>
									<td>
										${order.address }
									</td>
								</tr>
								<tr>
									<td colspan="2">
										<div class="dvdashed"></div>
									</td>
								</tr>
								<tr>
									<td height="30" align="right">
										邮政编码：
									</td>
									<td>
										${order.postcard }
									</td>
								</tr>
								<tr>
									<td colspan="2">
										<div class="dvdashed"></div>
									</td>
								</tr>
								<tr>
									<td height="30" align="right">
										联系电话：
									</td>
									<td>
										${order.phone }
									</td>
								</tr>
								<tr>
									<td colspan="2">
										<div class="dvdashed"></div>
									</td>
								</tr>
								<tr>
									<td height="30" align="right">
										送货方式：
									</td>
									<td>
										${order.delivery }
					<c:choose>
						<c:when test="${order.freight==5}">
								<c:set var="freight" value="5" scope="page"/>	(5元)					
						</c:when>
						<c:otherwise>
								<c:set var="freight" value="0" scope="page"/>	
						</c:otherwise>
					</c:choose>
									</td>
								</tr>
								<tr>
									<td colspan="2">
										<div class="dvdashed"></div>
									</td>
								</tr>
								<tr>
									<td height="30" align="right">
										付款方式：
									</td>
									<td>
										${order.payment }
									</td>
								</tr>
								<tr>
									<td colspan="2">
										<div class="dvdashed"></div>
									</td>
								</tr>
								<tr>
									<td height="30" align="right">
										是否发货：
									</td>
									<td>
										<c:choose>
						<c:when test="${order.state}">
							已发货
						</c:when>
						<c:otherwise>
							未发货
						</c:otherwise>
					</c:choose>
									</td>
								</tr>
								<tr>
									<td colspan="2">
										<div class="dvdashed"></div>
									</td>
								</tr>
								<tr>
									<td colspan="2">
								<tr>
									<td height="30" align="right">
										是否付款：
									</td>
									<td>
										<c:choose>
						<c:when test="${order.ispay}">
							已付款
						</c:when>
						<c:otherwise>
							<span style="color: red;">未付款</span>
						</c:otherwise>
					</c:choose>
									</td>
								</tr>
								<tr>
									<td colspan="2">
										<div class="dvdashed"></div>
									</td>
								</tr>
								<tr>
									<td align="right" valign="top">
										商品清单：
									</td>
									<td>
										<table width="549" border="0" cellpadding="0" cellspacing="0"
											style="margin: 10px;">
											<tr class="fontbb">
												<td width="249" height="28">
													商品名称
												</td>
												<td width="100">
													单价
												</td>
												<td width="100">
													总价
												</td>
												<td width="100">
													数量
												</td>
											</tr>
											<tr>
												<td colspan="4">
													<div class="dvsolid"></div>
												</td>
											</tr>
											
											
											
											
											<c:forEach items="${goodsAdminList}" var="goodsAdmin">
						
									<tr>
							<td>
							 	${goodsAdmin.goodsName }
							</td>
							<td>
								${goodsAdmin.goodsPrice }
							</td>
							<td>
								${goodsAdmin.goodsPrice*goodsAdmin.goodsCount }
								<c:set var="sum" value="${goodsAdmin.goodsPrice*goodsAdmin.goodsCount+sum}" scope="page"/>
							</td>
							<td>
								${goodsAdmin.goodsCount }
							</td>
						</tr>
									<tr>
												<td colspan="4">
													<div class="dvdashed"></div>
												</td>
											</tr>
						</c:forEach>
										
											
											
											
											
											<tr>
												<td colspan="4">
													<div class="dvsolid"></div>
												</td>
											</tr>
											<tr>
												<td height="28" colspan="4" align="right">
													总价：${sum+freight }
												</td>
											</tr>
										</table>
									</td>
								</tr>
								<tr>
									<td colspan="2">
										<div class="dvsolid"></div>
									</td>
								</tr>
								<tr>
									<td height="30">
										&nbsp;

									</td>
									<td>
									<c:if test="${order.ispay==false&&order.payment !='货到付款'}">
											<input type="button" name="button2" id="button2" value="付款"
											class="bluebutton" onclick="javascript:buy('${order.order_id }')" />
									</c:if>
									
										<input type="button" name="button2" id="button2" value="后退"
											class="bluebutton" onclick="javascript:back()" />
									</td>
								</tr>
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
