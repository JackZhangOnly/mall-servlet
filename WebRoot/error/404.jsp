<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title></title>
		<link href="${ctx }/css/admin.css" rel="stylesheet" type="text/css" />
		<style>
td,input {
	color: #4E4E4E
}
</style>
	</head>
	<body
		style="background-image: url(${ctx }/images/admin_login01.jpg); background-repeat: repeat-x;">

			<center>
				<table border="0" cellspacing="6" cellpadding="0"
					style="margin-top: 160px">
					<tr>
						<td align="right">
							<a href="${ctx}/type.do?method=selectIndexType"><img src="${ctx }/images/logoadmin.png"></img></a>
						</td>
						<td>
							<table border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td style="font-size:28px;">
										没有！
									</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
			</center>
	</body>
</html>
