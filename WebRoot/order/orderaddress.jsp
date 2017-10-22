<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/head.jsp"%>

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
								<a
									href="${ctx}/goods.do?method=page&type_id=${type.type_id }"><span>${type.name
										}</span> </a>
							</c:forEach>
						</div>
					</td>
					<td valign="top">
						<div class="carhead">
							<div>
								1. 我的购物车　　
								<span>2. 确认收货地址　　</span> 3. 付费
							</div>
						</div>
						<div class="listright" id="listright">
							<table border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td width="75" height="30" style="font-weight: bold">
										收货人信息
									</td>
									<td width="70">
										&nbsp;
									</td>
									<td width="566">
										&nbsp;
									</td>
								</tr>
								<tr>
									<td height="30">
										&nbsp;
									</td>
									<td align="right">
										收货地址：
									</td>
									<td>
										<input name="address" type="text" class="loginkuang"
											id="textfield" value="${user.address }"/>
									</td>
								</tr>
								<tr>
									<td height="30">
										&nbsp;
									</td>
									<td align="right">
										收货人：
									</td>
									<td>
										<input name="username" type="text" class="loginkuang"
											id="textfield2" value="${user.name }" readonly="readonly"/>
									</td>
								</tr>
								<tr>
									<td height="30">
										&nbsp;
									</td>
									<td align="right">
										邮政编码：
									</td>
									<td>
										<input name="zip" type="text" class="loginkuang"
											id="textfield3" value="${user.zip }"/>
									</td>
								</tr>
								<tr>
									<td height="30">
										&nbsp;
									</td>
									<td align="right">
										电话号码：
									</td>
									<td>
										<input name="phone" type="text" class="loginkuang"
											id="textfield4" value="${user.phone }"/>
									</td>
								</tr>
								<tr>
									<td colspan="3">
										<div class="dvsolid"></div>
									</td>
								</tr>
								<tr>
									<td colspan="3" align="right">
										<input name="sub" id="sub" class="redbutton" type="submit" value="下一步">
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