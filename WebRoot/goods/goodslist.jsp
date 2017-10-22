<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/head.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>商品</title>
		<link href="${ctx}/css/list.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript">
			function jump(value)
			{
				window.location.href="${ctx}/goods.do?method=page&type_id=${type.type_id}&goodsName=${goodsName }&sort_id="+value;
			}
			
			function show(id)
			{
			
				window.location.href="${ctx}/goods.do?method=goodsShow&goods_id="+id;
			}
			function add(value)
			{
				$.ajax({
                            type: "POST",
                            url: "${ctx}/cart.do?method=add&goods_id="+value,
                            error: function(){
                                alert("服务器繁忙，请稍后重试");
                            },
                            success: function(msg){
                                if ('ok' == msg) {
                                    alert("添加成功");
                                }
                                else {
                                	alert("添加失败");
                                }
                            }
                        });
			}
			
		</script>
	</head>
	<body onload="$('#allgoods').click()">
		<!-- <form action="${ctx}/goods.do?method=selectAll" method="post"> -->
		<div class="dvbody">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td colspan="2">
						<div class="listhead">
							<div>
								<a href="${ctx}/type.do?method=selectIndexType">所有商品</a> >  ${type.name } ${typeName }
				
							</div>
							<span>${type.name } ${typeName }</span>
						</div>
					</td>
				</tr>
				<tr>
					<td width="210" valign="top">
						<div class="listleft">
							<div class="dvtypename">
								<span>商品目录</span>
							</div>
							<c:forEach items="${typeList}" var="type">
								<a href="${ctx}/goods.do?method=page&type_id=${type.type_id }"><span>${type.name }</span>
								</a>
							</c:forEach>
						</div>
					</td>
					<td width="750" valign="top">
						<div class="listright" id="listright">
							<div class="dvnavigation">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td width="120" height="35">
											<div class="dvcount">
												共&nbsp;${count}&nbsp;条记录
											</div>
										</td>
										<td>
											<div class="dvpage">
												<a
													href="${ctx}/goods.do?method=page&type_id=${type.type_id }&sort_id=${sort_id}&goodsName=${goodsName }&pageMethod=first&pageCount=${page.currentPage }">首页</a>
												<a
													href="${ctx}/goods.do?method=page&type_id=${type.type_id }&sort_id=${sort_id}&goodsName=${goodsName }&pageMethod=previous&pageCount=${page.currentPage }">上一页</a>
												<a
													href="${ctx}/goods.do?method=page&type_id=${type.type_id }&sort_id=${sort_id}&goodsName=${goodsName }&pageMethod=next&pageCount=${page.currentPage }">下一页</a>
												<a
													href="${ctx}/goods.do?method=page&type_id=${type.type_id }&sort_id=${sort_id}&goodsName=${goodsName }&pageMethod=last&pageCount=${page.currentPage }">末页</a>
											</div>
										</td>
										<td align="right">
											<select name="sort_id" onchange="jump(this.value)">
												<c:choose>
													<c:when test="${sort_id=='3'}">
												<option selected="selected" value="3">
													按照价格排序(高到底)
												</option>
														<option   value="2">
													按照价格排序(低到高)
												</option>
														<option value="1">
													按照发布时间排序
												</option>
													</c:when>
													<c:when test="${sort_id=='2'}">
												<option  selected="selected" value="2">
													按照价格排序(低到高)
												</option>
														<option value="1">
													按照发布时间排序
												</option>
												<option value="3">
													按照价格排序(高到底)
												</option>
													</c:when>
													<c:otherwise>
												<option selected="selected" value="1">
													按照发布时间排序
												</option>
												<option value="2">
													按照价格排序(低到高)
												</option>
												<option value="3">
													按照价格排序(高到底)
												</option>
													</c:otherwise>
												</c:choose>
												
											</select>
										</td>
									</tr>
								</table>
							</div>
							<div class="dvbr"></div>
							<div class="dvshow">
								<c:forEach items="${goodsAll}" var="goods">
									<table width="100%" border="0" cellspacing="0" cellpadding="0">
										<tr>
											<td width="128" rowspan="2" align="left" valign="top">
											<!-- 图片 -->
												<div class="dvshowimg" onclick="show('${goods.goods_id }')">
													<img src="${ctx}/${goods.img }"/>
												</div>
											</td>
											<td width="389" valign="top">
												<span class="spname">${goods.name }</span>
												<div class="dvdesc">
													${goods.desc }
												</div>
											</td>
											<td width="194" rowspan="2" valign="top">
												<span class="spprice">单价：￥${goods.price }</span>
											</td>
										</tr>
										<tr>
											<td valign="bottom">
											<!--
												<a href="javascript:add('${goods.goods_id }')" class="a_incar"><div class="dvincar">
														<span>放入购物车</span>
													</div> </a>
													 --> 
												<a href="javascript:add('${goods.goods_id }')" class="a_incar"><div class="dvcollection">
														<span>放入购物车</span>
													</div> </a>
											</td>
										</tr>
										<tr>
											<td height="10" colspan="3" align="left" valign="top"
												class="showbottom">
												&nbsp;
											</td>
										</tr>
									</table>
								</c:forEach>
							</div>
							<div class="dvbr">

							</div>
							<div class="dvnavigation">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td width="120" height="35">
											<div class="dvcount">
												共&nbsp;${count}&nbsp;条记录
											</div>
										</td>
										<td>
											
											<div class="dvpage">
												<a
													href="${ctx}/goods.do?method=page&type_id=${type.type_id }&sort_id=${sort_id}&goodsName=${goodsName }&pageMethod=first&pageCount=${page.currentPage }">首页</a>
												<a
													href="${ctx}/goods.do?method=page&type_id=${type.type_id }&sort_id=${sort_id}&goodsName=${goodsName }&pageMethod=previous&pageCount=${page.currentPage }">上一页</a>
												<a
													href="${ctx}/goods.do?method=page&type_id=${type.type_id }&sort_id=${sort_id}&goodsName=${goodsName }&pageMethod=next&pageCount=${page.currentPage }">下一页</a>
												<a
													href="${ctx}/goods.do?method=page&type_id=${type.type_id }&sort_id=${sort_id}&goodsName=${goodsName }&pageMethod=last&pageCount=${page.currentPage }">末页</a>
											
											</div>
										</td>
										<td align="right">
											<select name="sort_id" onchange="jump(this.value)">
												<c:choose>
													<c:when test="${sort_id=='3'}">
												<option selected="selected" value="3">
													按照价格排序(高到底)
												</option>
														<option   value="2">
													按照价格排序(低到高)
												</option>
														<option value="1">
													按照发布时间排序
												</option>
													</c:when>
													<c:when test="${sort_id=='2'}">
												<option  selected="selected" value="2">
													按照价格排序(低到高)
												</option>
														<option value="1">
													按照发布时间排序
												</option>
												<option value="3">
													按照价格排序(高到底)
												</option>
													</c:when>
													<c:otherwise>
												<option selected="selected" value="1">
													按照发布时间排序
												</option>
												<option value="2">
													按照价格排序(低到高)
												</option>
												<option value="3">
													按照价格排序(高到底)
												</option>
													</c:otherwise>
												</c:choose>
												
											</select>
										</td>
									</tr>
								</table>
							</div>
						</div>
					</td>
				</tr>
			</table>
		</div>
	</body>
</html>
<%@ include file="/common/bottom.jsp"%>