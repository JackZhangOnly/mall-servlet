<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/head.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>商品详细</title>
		<link href="${ctx}/css/list.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript">
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
			$(document).ready(function () {
				$("#subcom").click(
					function(){
						if($.trim($("#comment").val())==""){
							return false;
						}
						return true;
					}
				)
			});
		</script>
	</head>
	<body onload="$('#allgoods').click()">
	<form action="${ctx}/goods.do?method=addComments&goods_id=${goods.goods_id}" method="post">
		<div class="dvbody">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td colspan="2">
						<div class="listhead">
							<div>
								<a href="${ctx}/type.do?method=selectIndexType">所有商品</a> >  ${type.name }
							</div>
							<span>${type.name }</span>
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
								<a href="${ctx}/goods.do?method=page&type_id=${type.type_id }"><span>${type.name }</span> </a>
							</c:forEach>
						</div>
					</td>
					<td valign="top">
						<div class="listright" id="listright">
							<div class="dvnavigation">
								<span class="showtitle">${goods.name }</span>
							</div>
							<div class="dvshow">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td width="314" rowspan="2" align="left" valign="top">
											<div class="showimg">
												<img height=290 width=290  src="${ctx}/${goods.img }"/>
											</div>
										</td>
										<td width="397" valign="top">
											<span class="showprice">单价：￥${goods.price }</span>
											<div class="showtype">
												类别：
												<span>${goods.type.name }</span>
											</div>
											<div class="showdesc">
												${goods.desc }
											</div>
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
										<td height="10" colspan="2" align="left" valign="top"
											class="showbottom">
											&nbsp;
										</td>
									</tr>
								</table>
								<div class="dvcomments">
									<div class="dvcommenthead">
										<span>商品评论</span>
									</div>
									<span class="spmsg">请留言：</span>
									<div class="dvwritecomments">
										<textarea name="comment" id="comment"></textarea>
										<div class="dvcommentsub">
											<input name="input" type="submit" value="提交" id="subcom"/>
											
										</div>
										<c:forEach items="${commentsAll}" var="comments">
										<div class="showcomment">
											<span class="spusername">${comments.user.name }：</span><span
												class="spcomment">${comments.comment }</span>
										</div>
										</c:forEach>
										<c:if test="${count>1}">
										<table width="100%" border="0" cellspacing="0" cellpadding="0">
											<tr>
												<td width="120" height="35">
													<div class="dvcount">
														共&nbsp;${count }&nbsp;条记录
													</div>
												</td>
												<td>
													<div class="dvpage">
														<a
													href="goods.do?method=goodsShow&goods_id=${goods.goods_id }&pageMethod=first&pageCount=${page.currentPage }">首页</a>
												<a
													href="goods.do?method=goodsShow&goods_id=${goods.goods_id }&pageMethod=previous&pageCount=${page.currentPage }">上一页</a>
												<a
													href="goods.do?method=goodsShow&goods_id=${goods.goods_id }&pageMethod=next&pageCount=${page.currentPage }">下一页</a>
												<a
													href="goods.do?method=goodsShow&goods_id=${goods.goods_id }&pageMethod=last&pageCount=${page.currentPage }">末页</a>
													</div>
												</td>
											</tr>
										</table></c:if>
									</div>
								</div>
							</div>
							<img src="${ctx}/images/chlc.png" class="chlc" />
						</div>
					</td>
				</tr>
			</table>

		</div>
		</form>
	</body>
</html>
<%@ include file="/common/bottom.jsp"%>