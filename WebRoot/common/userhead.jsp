<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="taglib.jsp"%>

<link href="${ctx}/css/common.css" rel="stylesheet" type="text/css" />
<div class="dvbody"
	style="padding-left: 1px; padding-right: 1px; text-align: left;">
	<div class="dvlogo" style="margin-top: 12px;">
		<img src="${ctx}/images/logo-buyer.png" />
	</div>
	<div class="userhead">
		<div>
			<a style="float: right;" href="${ctx }/user.do?method=out">注销</a>
			<a href="${ctx }/user.do?method=main" style="color: #666666; font-weight: bold;">我的个人主页</a> |
			<a href="${ctx}/type.do?method=selectIndexType" style="color: #666666; font-weight: bold;">U-bai首页</a>
		</div>
	</div>
	<div style="padding-bottom: 6px; text-align: center">
		<img src="${ctx}/images/rtcurve.jpg" />
	</div>
</div>
