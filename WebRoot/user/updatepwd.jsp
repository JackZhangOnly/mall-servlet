<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/userhead.jsp"%>

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
		$(document).ready(function(){
			$("#oldpwd").focus(	
				function(){
					$("#dvoldpwd").html("");
				}
			);
			$("#oldpwd").blur(	
				function(){
					$("#dvoldpwd").html("");
				}
			);
			$("#uppwd").click(
				function(){
					var oldpwd = $("#oldpwd").val();
					var newpwd = $("#pwd").val();
					if($.trim($("#oldpwd").val())==""){
						$("#dvoldpwd").html(msg[23]);			
					}else if(ckpwd()&&ckrpwd()){
						$.ajax({
                            type: "POST",
                            url: "${ctx}/user.do?method=updpwd&oldpwd="+oldpwd+"&newpwd="+newpwd,
                            error: function(){
                                alert("服务器繁忙，请稍后重试");
                            },
                            success: function(msg){
                                if ('ok' == msg) {
                                    alert("修改密码成功！");
                                    window.location.href="${ctx}/user.do?method=main";
                                }
                                else {
                                	alert("旧密码输入错误，修改密码失败！");
                                }
                            }
                        });
					}
				}
			)
		});
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
									<a
										href="${ctx }/comments.do?method=pageUser&user_id=${user.user_id }">评论管理</a>
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
										旧密码：
									</td>
									<td width="245">
										<input name="oldpwd" type="password" class="kuang" id="oldpwd" />
										<span class="fontred">*</span>
									</td>
									<td width="325">
										<div id="dvoldpwd"></div>
									</td>
								</tr>
								<tr>
									<td align="right">
										新密码：
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
										重复新密码：
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
									</td>
									<td>
										<input type="button" value="修改" class="bluebutton" id="uppwd">
										&nbsp;
										<input type="button" value="取消" class="bluebutton"
											onclick="back()">
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
	</body>
</html>
