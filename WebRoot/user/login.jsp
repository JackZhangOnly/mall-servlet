<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>用户登录</title>
		<link href="${ctx}/css/common.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="${ctx}/js/jquery.js"></script>
		<script type="text/javascript" src="${ctx}/js/hovering.js"></script>
		<script type="text/javascript">
			if (window != top){
				top.location.href = location.href;
			}
			function QueryString(item){
     			var sValue=location.search.match(new RegExp("[\?\&]"+item+"=([^\&]*)(\&?)","i"))
    	 		return sValue?sValue[1]:sValue
			}
			function load()
			{
				if(QueryString("error")=="user")
				{
					alert("您还没有登录，请先登录！");
				}
			}
			setTimeout(load,1000);
		</script>
	</head>

	<body>
		<form name="myform" action="${ctx}/user.do?method=login" method="post">
			<div class="dvbody">
				<div class="dvlogo" style="margin-top: 12px;">
					<a href="${ctx}/type.do?method=selectIndexType"> <img
							src="${ctx}/images/logo-buyer.png" />
					</a>
				</div>
				<div class="loginhead"></div>
				<div style="margin-top: 6px; text-align: center">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td width="640" align="center" valign="top"
								style="padding-top: 30px">
								<img src="${ctx}/images/main.jpg" />
								<table width="75%" border="0" cellpadding="6" cellspacing="0"
									style="margin-top: 12px;">
									<tr>
										<td align="left" class="fontgreen">
											更多选择、更低价格
										</td>
										<td rowspan="2">
											<img src="${ctx}/images/login_232ds1.jpg" />
										</td>
										<td align="left" class="fontgreen">
											全场免运费、货到付款
										</td>
										<td rowspan="2">
											<img src="${ctx}/images/login_232ds1.jpg" />
										</td>
										<td align="left" class="fontgreen">
											真实、优质的商品评论
										</td>
									</tr>
									<tr>
										<td align="left" valign="top" style="line-height: 18px">
											100多万种商品供您的选
											<br />
											择，价格低于地面店20%
											<br />
											以上
										</td>
										<td align="left" valign="top" style="line-height: 18px">
											免费送货上门、360个城市
											<br />
											货到付款。另有网上支付、
											<br />
											邮局汇款等多种支付方式
										</td>
										<td align="left" valign="top" style="line-height: 18px">
											千万用户真实、优质的商品
											<br />
											评论，给您多角度、全方位
											<br />
											的购物参考
										</td>
									</tr>
								</table>

							</td>
							<td align="center" valign="top">
								<div
									style="border: 1px solid #666666; border-bottom: 0; width: 313px; text-align: left;">
									<table width="100%" border="0" cellpadding="6" cellspacing="0">
										<tr>
											<td colspan="2">
												<div
													style="color: #853200; font-size: 16px; font-weight: bold">
													<img src="${ctx}/images/loginkey.gif" width="18"
														height="22" align="absmiddle" />
													登录账户
												</div>
												<div style="margin-top: 6px; margin-bottom: 6px">
													登录 享受购物之旅
												</div>
												<img src="${ctx}/images/login_123.jpg" width="300"
													height="3" />

											</td>
										</tr>
										<tr>
											<td width="60" height="40" align="right">
												账户：
											</td>
											<td width="229" align="left">
												<input name="name" type="text" class="loginkuang"
													value="jack123" />
											</td>
										</tr>
										<tr>
											<td height="40" align="right">
												密码：
											</td>
											<td align="left">
												<input name="password" type="password" class="loginkuang"
													value="jack123" />
											</td>
										</tr>
										<tr>
											<td height="40" align="right">
												&nbsp;
											</td>
											<td align="left">
												<input type="submit" value="" class="butlogin" />
											</td>
										</tr>
										<tr>
											<td colspan="2" align="left" style="padding-top: 12px;">
												<div
													style="background-image: url(${ctx}/images/login_33213.jpg); background-repeat: repeat-x; height: 102px">
													<div
														style="padding-top: 12px; padding-left: 18px; width: 280px; margin-left: auto; margin-right: auto">
														<span style="color: #000; font-weight: bold">还不是 用户?</span>
														快捷方便的免费注册，让你立
														<br />
														刻尽享 提供的各项优惠及服务...
													</div>
													<div style="text-align: right; padding-top: 12px">
														<input type="button" class="buttore"
															onclick="window.location.href='${ctx}/user/register.jsp'" />
													</div>
												</div>
											</td>
										</tr>
									</table>
								</div>
								<img src="${ctx}/images/login_2321.jpg" />
							</td>
						</tr>
					</table>
				</div>
				<div class="bottomline"></div>
				<div style="color: #4E4E4E; text-align: center">
				</div>
			</div>
		</form>
	</body>
</html>
