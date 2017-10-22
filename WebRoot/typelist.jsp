<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/head.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>U-bai - 商品类别</title>
		<style>
.dvtypes {
	padding: 10px;
	border: 1px solid #d6d6d6;
}

.dvtype {
	margin-top: 5px;
	margin-bottom: 5px;
	border-bottom: 1px solid #d6d6d6;
	padding-bottom: 10px;
}

.dvtype div {
	padding-top: 6px;
	padding-left: 12px;
}
</style>
	</head>
	<body onload="$('#leibie').click()">
		<div class="dvbody">
			<div class="dvtypes">
				<div class="dvtype">
					<a href="${ctx}/goods.do?method=page&type_id=${type1.type_id}">${type1.name
						}</a>
					<div>
						<c:forEach items="${type1.subList}" var="t1" varStatus="state">
							<c:if test="${state.count%16==0}">
								<br />
							</c:if>
							<a href="${ctx}/goods.do?method=page&type_id=${t1.type_id}">
								${t1.name } | </a>
						</c:forEach>
					</div>
				</div>
				<div class="dvtype">
					<a href="${ctx}/goods.do?method=page&type_id=${type2.type_id}">${type2.name
						}</a>
					<div>
						<c:forEach items="${type2.subList}" var="t2" varStatus="state">
							<c:if test="${state.count%16==0}">
								<br />
							</c:if>
							<a href="${ctx}/goods.do?method=page&type_id=${t2.type_id}">
								${t2.name } | </a>
						</c:forEach>
					</div>
				</div>
				<div class="dvtype">
					<a href="${ctx}/goods.do?method=page&type_id=${type3.type_id}">${type3.name
						}</a>
					<div>
						<c:forEach items="${type3.subList}" var="t3" varStatus="state">
							<c:if test="${state.count%16==0}">
								<br />
							</c:if>
							<a href="${ctx}/goods.do?method=page&type_id=${t3.type_id}">
								${t3.name } | </a>
						</c:forEach>
					</div>
				</div>
				<div class="dvtype">
					<a href="${ctx}/goods.do?method=page&type_id=${type4.type_id}">${type4.name
						}</a>
					<div>
						<c:forEach items="${type4.subList}" var="t4" varStatus="state">
							<c:if test="${state.count%16==0}">
								<br />
							</c:if>
							<a href="${ctx}/goods.do?method=page&type_id=${t4.type_id}">
								${t4.name } | </a>
						</c:forEach>
					</div>
				</div>
				<div class="dvtype">
					<a href="${ctx}/goods.do?method=page&type_id=${type5.type_id}">${type5.name
						}</a>
					<div>
						<c:forEach items="${type5.subList}" var="t5" varStatus="state">
							<c:if test="${state.count%16==0}">
								<br />
							</c:if>
							<a href="${ctx}/goods.do?method=page&type_id=${t5.type_id}">
								${t5.name } | </a>
						</c:forEach>
					</div>
				</div>
				<div class="dvtype">
					<a href="${ctx}/goods.do?method=page&type_id=${type6.type_id}">${type6.name
						}</a>
					<div>
						<c:forEach items="${type6.subList}" var="t6" varStatus="state">
							<c:if test="${state.count%16==0}">
								<br />
							</c:if>
							<a href="${ctx}/goods.do?method=page&type_id=${t6.type_id}">
								${t6.name } | </a>
						</c:forEach>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>

<%@ include file="/common/bottom.jsp"%>