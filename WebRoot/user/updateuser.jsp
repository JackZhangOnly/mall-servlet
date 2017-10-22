<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/common/userhead.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title></title>
		<script type="text/javascript" src="${ctx}/js/jquery.js"></script>
		<script type="text/javascript" src="${ctx}/js/register.js"></script>
		<script type="text/javascript">
			function back()
			{
				window.location.href="${ctx}/user.do?method=main";
			}
		</script>
	</head>
	<body>
	<form action="${ctx }/user.do?method=upd" method="post">
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
							<table width="670" border="0" cellpadding="6" cellspacing="0">
								<tr>
									<td width="100" align="right">
										用户名：
									</td>
									<td width="245">
										${user.name }
									</td>
									<td width="325">
									</td>
								</tr>
								<tr>
									<td align="right">
										性别：
									</td>
									<td>
										<c:choose>
											<c:when test="${user.sex}">
										<input type="radio" name="sex" id="ranan" checked="checked"
											value="男" />
										男
										<input type="radio" name="sex" id="ranv" value="女" />
										女
												
											</c:when>
											<c:otherwise>
												<input type="radio" name="sex" id="ranan" 
											value="男" />
										男
										<input type="radio" name="sex" id="ranv" value="女" checked="checked" />
										女
											</c:otherwise>
										</c:choose>
										<span class="fontred">*</span>
									</td>
									<td>
										&nbsp;
									</td>
								</tr>
								<tr>
									<td align="right">
										地址：
									</td>
									<td>
										<input name="address" type="text" class="kuang" id="address" value="${user.address }"/>
										<span class="fontred">*</span>
									</td>
									<td>
										<div id="dvaddress"></div>
									</td>
								</tr>
								<tr>
									<td align="right">
										联系电话：
									</td>
									<td>
										<input name="phone" type="text" class="kuang" id="phone" value="${user.phone }"/>
										<span class="fontred">*</span>
									</td>
									<td>
										<div id="dvphone"></div>
									</td>
								</tr>
								<tr>
									<td align="right">
										电子邮件：
									</td>
									<td>
										<input name="email" type="text" class="kuang" id="email" value="${user.email }"/>
										<span class="fontred">*</span>
									</td>
									<td>
										<div id="dvemail"></div>
									</td>
								</tr>
								<tr>
									<td align="right">
										邮编：
									</td>
									<td>
										<input name="zip" type="text" class="kuang" id="zip" value="${user.zip }"/>
										<span class="fontred">*</span>
									</td>
									<td>
										<div id="dvzip"></div>
									</td>
								</tr>
																<tr>
									<td align="right">
									</td>
									<td>
									<input type="submit" value="修改" class="bluebutton">&nbsp;
									<input type="button" value="取消" class="bluebutton" onclick="back()">
									</td>
									<td>
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
		</form>
	</body>
</html>
