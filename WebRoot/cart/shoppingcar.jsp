<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/head.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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
			
			function upd(id,t)
			{
			var str = t.value;
			
				window.location.href="${ctx }/cart.do?method=upd&id="+id+"&no="+str;
			
			}
			
			function del(id,name)
			{
				 if(confirm("你确定要删除"+name+"吗？"))
				 {
        			location.href="${ctx }/cart.do?method=del&id="+id;
   				 }
			}
			
			function nan(t,var1) 
			{ 
			var str = t.value;
			var zc = /^[1-9]\d*$/;
			if(!zc.exec(str)){
				t.value=var1;	
			}
			} 

		</script>
	</head>
		<body onload="$('#gwcar').click()">
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
								<span>1. 我的购物车</span>　　 2. 确认收货地址 　　3. 付费
							</div>
						</div>
						<div class="listright" id="listright">
							<div class="dvnavigation">
							<c:if test="${user.name==null||user.name==''}"><span class="showtitle">您还没有登录，请<a style="text-decoration:underline" href="${ctx }/user/login.jsp">登录</a></span></c:if>
								<c:if test="${user.name!=null&&user.name!=''}">
								<span class="showtitle">订单发往买家：<span>${user.name }</span> </span></c:if>
							</div>
							<div class="dvshow">
								
									<c:forEach items="${gwc}" var="gwc">
											<c:forEach items="${gwc.value}" var="g">
											<c:set var="gid" value="${g.key.goods_id}" scope="page"/>
											</c:forEach></c:forEach>
							<c:if test="${gwc==null||gid==''||gid==null}">
							<div style="text-align: center; padding-top:12px;">您的购物车是空的！&nbsp;<a style="text-decoration:underline" href="${ctx}/goods.do?method=page&goodsName=">赶快购物去</a></div>
							</c:if>
							<c:if test="${gwc!=null&&gid!=''&&gid!=null}">
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
											操作
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
											<div class="carimg" onclick="show(${g.key.goods_id })">
												<img width="50" height="50" src="${ctx}/${g.key.img}"/>
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
											<c:set var="sum" value="${g.key.price*g.value+sum}" scope="page"/>
										</td>
										<td align="center">
											<input type="text" name="textfield" id="textfield${g.key.goods_id }" value="${ g.value}"
												class="carnum" onkeyup="nan(this,'${ g.value}')" onchange="upd('${g.key.goods_id }',this)" style="text-align:center;"/> 
										</td>
										<td align="center">
											<a href="javascript:del('${g.key.goods_id }','${g.key.name }')">删除</a>
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
											<span class="sptprice">总价：<span class="fontred">￥${sum }</span>
											</span>
										</td>
									</tr>
									<tr>
										<td colspan="6">
											<div class="dvsolid"></div>
										</td>
									</tr>
									<tr>
										<td colspan="6" align="right">
											<a href="${ctx }/order.do?method=details" class="redbutton">下一步</a>
										</td>
									</tr>
								</table>
							</c:if>
							</div>
						</div>
					</td>
				</tr>
			</table>
		</div>
	</body>
</html>
<%@ include file="/common/bottom.jsp"%>