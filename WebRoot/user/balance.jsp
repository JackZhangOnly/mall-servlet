<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/userhead.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title></title>
		<script type="text/javascript">
			function sub(t)
			{
				var str = /^([1-9]\d*)(\.[1-9]{1,2})?$/;
				var num=document.getElementById('cz').value;
				if(num!=""){ 			
				 	window.location.href="${ctx}/user.do?method=result&money="+num;
				 }else{
				 	alert("您输入的金额不能为空！");
				 }
				
			}
			function init()
			{
				var src ="${falg}";
				if(src!="")
				{
					alert("充值成功！");
				}
			}
			
			function nan(t) 
			{ 
			 var num=t.value;
			 var str = /^([1-9]\d*)(\.[1-9]{1,2})?$/; 
			 if(!str.exec(num)){
			 	t.value = "";
			 }
			 
			}  
			
		</script>
	</head>
	<body onload="init()">
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
						<div style="border: 1px solid #FFCD06">
							<table width="100%" border="0" cellpadding="0" cellspacing="0">
								<tr>
									<td width="12" height="26" bgcolor="#FFE580">
										<img src="${ctx }/images/img9dots_12x12.gif" />
									</td>
									<td width="70" bgcolor="#FFE580">
										<span class="fontbb">您的账户</span>
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
										<img src="${ctx }/images/balanceca.png" />
									</td>
									<td height="40">
										<span class="fontbb">帐户余额：<span class="fontred" id="ye">￥${user.balance }</span>
</span>
									</td>
								</tr>
								<tr>
									<td height="40">
										<span class="fontbb">充值：</span><input type="text" id="cz"  onblur="nan(this)" onchange="nan(this)" onkeyup="nan(this)"/><input type="button" value="确定" onclick="sub(this)" >
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
