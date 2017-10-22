<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="taglib.jsp"%>
<link href="${ctx}/css/common.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/css/head.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/css/lavalamp.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/css/jquery.suggest.css" rel="stylesheet"
	type="text/css" />
<script type="text/javascript" src="${ctx}/js/jquery.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery.suggest.js"></script>

<script type="text/javascript" src="${ctx}/js/jquery.lavalamp.js"></script>
<!-- Optional -->
<script type="text/javascript" src="${ctx}/js/jquery.easing.js"></script>
<script type="text/javascript">
   		$(function(){
				$("#search1").suggest("goods.do?method=ajax&name=",{
            		onSelect: function() {
					var str=$("#search1").val();
					var t = str.substring(0,str.indexOf("　"));
					$("#search1").val(t);
           				 }
                }); 
			$(".lavaLamp").lavaLamp({fx:"backout",speed:700});
		});
		
		function select1()
		{
			var name = document.getElementById('search1').value;
			window.location.href="${ctx}/goods.do?method=page&goodsName="+name;
		}
		</script>
<style type="text/css">
* {
	padding-bottom: 0px;
	margin: 0px;
	padding-left: 0px;
	padding-right: 0px;
	padding-top: 0px
}
</style>
<div class="dvbody">
	<div class="dvtophead">
		<span class="fontww">欢迎光临U-bai. </span><a
			href="${ctx }/user.do?method=main">${user.name }</a>
		<c:if test="${user.name!=null}"><a href="${ctx }/user.do?method=out">注销</a></c:if>
		<c:if test="${user.name==null}"><a href="${ctx }/user/login.jsp">用户登录</a></c:if>
	</div>
	<div class="dvlogo" style="margin-top: 12px;">
		<a href="${ctx}/type.do?method=selectIndexType"><img src="${ctx}/images/logo.png" /></a>
	</div>
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td width="20" align="center" valign="top" style="padding-top: 9px;">
				<img src="${ctx}/images/icon-xmas.gif" />
			</td>
			<td valign="bottom">
				<div class="dvlava">
					<ul class="lavaLamp">
						<li>
							<a href="${ctx}/type.do?method=selectIndexType" id="shouye">首页</a>
						</li>
						<li>
							<a href="${ctx}/type.do?method=selectTypes" id="leibie">类别</a>
						</li>
						<li>
							<a href="${ctx}/goods.do?method=page&goodsName=" id="allgoods">所有商品</a>
						</li>
						<li>
							<a href="${ctx}/cart.do?method=select" id="gwcar">购物车 </a>
						</li>
						<!-- 
						<li>
							<a href="${ctx}/goods.do?method=page&type_id=${type1.type_id}">${type1.name
								}</a>
						</li>
						<li>
							<a href="${ctx}/goods.do?method=page&type_id=${type2.type_id}">${type2.name
								}</a>
						</li>
						<li>
							<a href="${ctx}/goods.do?method=page&type_id=${type3.type_id}">${type3.name
								}</a>
						</li> -->
					</ul>
				</div>
			</td>
			<td height="26" align="right" valign="top">
				<a href="${ctx}/cart.do?method=select"><img
						src="${ctx}/images/MyShopCard.png" /> </a>
			</td>
		</tr>
	</table>
	<div class="dvsearch">
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td height="38">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td width="580" height="38" align="right">
								<input type="text" id="search1" name="search1"
									class="textsearch" />
							</td>
							<!-- <td width="29" align="center">
								<span style="font-weight: bold; color: #000">IN</span>
							</td>-->
							<td width="351">
								<table border="0" cellspacing="0" cellpadding="0">
									<tr>
									<!-- 
										<td>
											<select name="select" class="selecttype">
												<option selected="selected">
													所有商品
												</option>
											</select>
										</td> -->
										<td>
											<a href="javascript:select1()" class="redbutton">搜索</a>
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</div>
	<div class="dvhelp"></div>
</div>
