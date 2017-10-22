<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>U-bai - 用户注册</title>
		<link href="${ctx}/css/common.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="${ctx}/js/jquery.js"></script>
		<script type="text/javascript" src="${ctx}/js/hovering.js"></script>
		<script type="text/javascript" src="${ctx}/js/register.js"></script>
	</head>
	<body>
		<input type="hidden" id="ajaxurl"
			value="${ctx }/user.do?method=authenticationName&userName=">
		<form action="${ctx}/user.do?method=register" method="post">
			<!-- <input type="hidden" name="org.apache.struts.taglib.html.TOKEN" value="${sessionScope["org.apache.struts.action.TOKEN"]}" /> -->
			<div class="dvbody">
				<div class="dvlogo" style="margin-top: 12px;">
					<a href="${ctx}/type.do?method=selectIndexType"> <img
							src="${ctx}/images/logo-buyer.png" /> </a>
				</div>
				<div class="loginhead"></div>
				<div style="margin-top: 6px; text-align: center">
					<div
						style="width: 950px; margin-left: auto; margin-right: auto; margin-top: 0; text-align: left;">
						<!-- img仅靠div 否则IE下有缝隙 -->
						<div style="border: 1px solid #A1A1A1; border-bottom: 0;">
							<div
								style="background-color: #FF6600; height: 25px; padding-top: 7px">
								<span style="color: #fff; font-size: 14px; font-weight: bold;">
									用户注册</span>
							</div>
						</div>
						<div
							style="background-image: url(${ctx}/images/bg_cont.gif); background-repeat: repeat-x; padding-left: 1px; padding-right: 1px">
							<div style="background-color: #FFF;">
								<img src="${ctx}/images/bg_top_re.jpg" />
								<table width="95%" border="0" cellpadding="6" cellspacing="0">
									<tr>
										<td width="220" align="right">
											设置用户名：
										</td>
										<td width="245">
											<input name="name" type="text" class="kuang" id="name" />
											<span class="fontred">*</span>
										</td>
										<td>
											<div id="dvname"></div>
										</td>
									</tr>
									<tr>
										<td align="right">
											设置密码：
										</td>
										<td>
											<input name="password" type="password" class="kuang" id="pwd" />
											<span class="fontred">*</span>
										</td>
										<td>
											<div id="dvpwd"></div>
										</td>
									</tr>
									<tr>
										<td align="right">
											再输一次密码：
										</td>
										<td>
											<input name="rpwd" type="password" class="kuang" id="rpwd" />
											<span class="fontred">*</span>
										</td>
										<td>
											<div id="dvrpwd"></div>
										</td>
									</tr>
									<tr>
										<td align="right">
											性别：
										</td>
										<td>
											<input type="radio" name="sex" id="ranan" checked="checked"
												value="男" />
											男
											<input type="radio" name="sex" id="ranv" value="女" />
											女
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
											<input name="address" type="text" class="kuang" id="address" />
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
											<input name="phone" type="text" class="kuang" id="phone" />
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
											<input name="email" type="text" class="kuang" id="email" />
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
											<input name="zip" type="text" class="kuang" id="zip" />
											<span class="fontred">*</span>
										</td>
										<td>
											<div id="dvzip"></div>
										</td>
									</tr>
									<tr>
										<td align="right">
											验证码：
										</td>
										<td>
											<input name="yzm" type="text" id="yzm" style="width: 95px;" />
											<img src="${ctx}/image.do?method=randletters" width="130"
												height="30" align="absmiddle" style="margin-right: 3px"
												id="randimg" />
											<span class="fontred">*</span>
										</td>
										<td>
											<a href="javascript:void(0)"
												onclick="randimg.src='${ctx}/image.do?method=randletters&date='+new Date().value+Math.random()">看不清？换张图</a>
											<div id="dvyzm" style="display: inline"></div>
										</td>
									</tr>
									<tr>
										<td align="right">
											&nbsp;
										</td>
										<td>
											<input type="submit" value="" id="butadd" class="butadd" />
										</td>
										<td>
											&nbsp;
										</td>
									</tr>
								</table>
							</div>
						</div>
					</div>
				</div>
				<div class="bottomline"></div>
				<div style="color: #4E4E4E; text-align: center">
					Copyright © 2009 U-Bai.com - ICP: 沪ICP备 09033184
				</div>
			</div>
		</form>
	</body>
</html>
