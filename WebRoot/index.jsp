<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<%@ include file="/common/head.jsp"%>
		<title>欢迎光临 购物天堂</title>
		<link href="${ctx}/css/index.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="${ctx}/js/kxbdMarquee.js"></script>
		<script type="text/javascript">
	    $(function(){
			$('#marquee').kxbdMarquee({
					direction:'up'
				});		
		});
		</script>
	</head>
	<body onload="$('#shouye').click()">
		<div class="dvbody">
			<table width="100%" border="0" cellspacing="0" cellpadding="0"
				style="margin-top: 6px;">
				<tr>
					<td valign="top">
						<div class="mainlefttop">
							<div>
								<object height="270" width="600"
									type="application/x-shockwave-flash"
									data="${ctx}/flash/ImagesShow.swf" id="HomeMain"
									style="visibility: visible;">
									<param name="FlashVars"
										value="pics=${ctx}/images/indeximg/t1.jpg|${ctx}/images/indeximg/t2.jpg|${ctx}/images/indeximg/t3.jpg|${ctx}/images/indeximg/t4.jpg&links=
									|1|
										2|
										3|
										4||&pic_width=600&pic_height=270&show_text=0&txtcolor=000000&bgcolor=DDDDDD&button_pos=4&stop_time=3000" />
									<param name="movie" value="flash/ImagesShow.swf" />
								</object>
							</div>
						</div>
						<div class="mainleftbottom">
							<div id="dvlb">
								<span class="maintitle">商品目录</span>

								<table width="100%" border="0" cellspacing="0" cellpadding="0"
									style="margin-top: 8px;">
									<tr>
										<td width="50%" height="105" valign="top">
											<div>
												<a
													href="${ctx}/goods.do?method=page&type_id=${type1.type_id}">${type1.name
													}</a>
											</div>
											<span><c:forEach items="${type1.subList}" var="t1"varStatus="state">
													<c:if test="${state.count%5==0}">
														<br />
													</c:if>
													<a href="${ctx}/goods.do?method=page&type_id=${t1.type_id}">
														${t1.name } | </a>
												</c:forEach> </span>

										</td>
										<td width="50%" valign="top">

											<div>
												<a
													href="${ctx}/goods.do?method=page&type_id=${type2.type_id}">${type2.name
													}</a>
											</div>
											<span><c:forEach items="${type2.subList}" var="t2"
													varStatus="state">
													<c:if test="${state.count%5==0}">
														<br />
													</c:if>
													<a href="${ctx}/goods.do?method=page&type_id=${t2.type_id}">
														${t2.name } | </a>
												</c:forEach> </span>

										</td>
									</tr>
									<tr>
										<td height="56" valign="top">

											<div>
												<a
													href="${ctx}/goods.do?method=page&type_id=${type3.type_id}">${type3.name
													}</a>
											</div>
											<span><c:forEach items="${type3.subList}" var="t3"
													varStatus="state">
													<c:if test="${state.count%5==0}">
														<br />
													</c:if>
													<a href="${ctx}/goods.do?method=page&type_id=${t3.type_id}">
														${t3.name } | </a>
												</c:forEach> </span>

										</td>
										<td valign="top">

											<div>
												<a
													href="${ctx}/goods.do?method=page&type_id=${type4.type_id}">${type4.name
													}</a>
											</div>
											<span><c:forEach items="${type4.subList}" var="t4"
													varStatus="state">
													<c:if test="${state.count%5==0}">
														<br />
													</c:if>
													<a href="${ctx}/goods.do?method=page&type_id=${t4.type_id}">
														${t4.name } | </a>
												</c:forEach> </span>

										</td>
									</tr>
									<tr>
										<td height="82" valign="top">

											<div>
												<a
													href="${ctx}/goods.do?method=page&type_id=${type5.type_id}">${type5.name
													}</a>
											</div>
											<span><c:forEach items="${type5.subList}" var="t5"
													varStatus="state">
													<c:if test="${state.count%5==0}">
														<br />
													</c:if>
													<a href="${ctx}/goods.do?method=page&type_id=${t5.type_id}">
														${t5.name } | </a>
												</c:forEach> </span>

										</td>
										<td valign="top">

											<div>
												<a
													href="${ctx}/goods.do?method=page&type_id=${type6.type_id}">${type6.name
													}</a>
											</div>
											<span><c:forEach items="${type6.subList}" var="t6"
													varStatus="state">
													<c:if test="${state.count%5==0}">
														<br />
													</c:if>
													<a href="${ctx}/goods.do?method=page&type_id=${t6.type_id}">
														${t6.name } | </a>
												</c:forEach> </span>

										</td>
									</tr>
								</table>
							</div>
						</div>
					</td>
					<td valign="top">
						<div class="mainright">
							<a href="${ctx}/goods.do?method=goodsShow&goods_id=1"><img
									src="${ctx}/images/indeximg/1.jpg" width="300" height="120" />
							</a>
						</div>
						<div class="mainright">
							<a href="${ctx}/goods.do?method=goodsShow&goods_id=2"><img
									src="${ctx}/images/indeximg/6.jpg" width="300" height="120" />
							</a>
						</div>
						<div class="mainright">
							<a href="${ctx}/goods.do?method=goodsShow&goods_id=3"><img
									src="${ctx}/images/indeximg/8.jpg" width="300" height="120" />
							</a>
						</div>
						<div class="mainscroll">
							<div style="height: 10px"></div>
							<div id="marquee">
								<ul>
									<li>
										<a href="${ctx}/goods.do?method=goodsShow&goods_id=4"> <img
												src="${ctx}/images/indeximg/4.jpg" width="300" height="120" />
										</a>
									</li>
									<li>
										<a href="${ctx}/goods.do?method=goodsShow&goods_id=5"><img
												src="${ctx}/images/indeximg/7.jpg" width="300" height="120" />
										</a>
									</li>
									<li>
										<a href="${ctx}/goods.do?method=goodsShow&goods_id=6"><img
												src="${ctx}/images/indeximg/10.jpg" width="300" height="120" />
										</a>
									</li>
								</ul>
							</div>
						</div>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div class="mainbottomleft">
							<span class="maintitle">诺基亚</span>
							<div>
								<a href="${ctx}/goods.do?method=page&type_id=8"><img
										src="${ctx}/images/indeximg/2.jpg" width="450" height="170" />
								</a>
							</div>
						</div>
						<div class="mainbottomright">
							<span class="maintitle">苹果</span>
							<div>
								<a href="${ctx}/goods.do?method=page&type_id=46"><img
										src="${ctx}/images/indeximg/3.jpg" width="450" height="170" />
								</a>
							</div>
						</div>
					</td>
				</tr>
			</table>
		</div>
	</body>
</html>
<%@ include file="/common/bottom.jsp"%>