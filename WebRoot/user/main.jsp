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
						<div
							style="background-color: FFFFCC; border: 1px solid #FF6600; padding: 10px; margin-bottom: 10px;">
							<table  border="0" cellpadding="0" cellspacing="0">
								<tr>
									<td height="35" colspan="5">
										<span
											style="font-size: 16px; font-weight: bold; color: #E45800">U-Bai
											账号中心</span>
									</td>
								</tr>
								<tr>
									<td width="110" rowspan="3">
										<img src="${ctx }/images/buyerimg.gif" width="100" height="100" />
									</td>
									<td width="50" height="35" align="right">
										账号：
									</td>
									<td width="150" align="left">
										${user.name }
									</td>
									<td width="65" align="right">
										性别：
									</td>
									<td align="left">
										<c:choose>
											<c:when test="${user.sex }">
												男
											</c:when>
											<c:otherwise>
												女
											</c:otherwise>
										</c:choose>
									</td>
								</tr>
								<tr>
									<td height="35" align="right">
										电话：
									</td>
									<td align="left">
										${user.phone }
									</td>
									<td align="right">
										电子邮件：
									</td>
									<td align="left">
										${user.email }
									</td>
								</tr>
								<tr>
									<td align="right" height="35">
										地址：
									</td>
									<td colspan="3" align="left">
										${user.address }
									</td>
								</tr>
							</table>
						</div>
						<div style="border: 1px solid #FFCD06">
							<table width="100%" border="0" cellpadding="0" cellspacing="0">
								<tr>
									<td width="12" height="26" bgcolor="#FFE580">
										<img src="${ctx }/images/img9dots_12x12.gif" />
									</td>
									<td width="70" bgcolor="#FFE580">
										<span class="fontbb">您的订单</span>
									</td>
									<td bgcolor="#FFE580">
										&nbsp;
									</td>
								</tr>
								<tr>
									<td rowspan="2" align="center">
										&nbsp;
									</td>
									<td rowspan="2">
										<img src="${ctx }/images/icontotal.gif" />
									</td>
									<td height="40">
										<span class="fontbb">等待付费：${toWaitForPayment }</span>
									</td>
								</tr>
								<tr>
									<td height="40">
										<span class="fontbb">等待确认：${awaitingConfirmation }</span>
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
