<%@ page language="java" pageEncoding="utf-8"%>
<script type="text/javascript">
   		$(function(){
				$("#search2").suggest("goods.do?method=ajax&name=",{
            		onSelect: function() {
					var str=$("#search2").val();
					var t = str.substring(0,str.indexOf("　"));
					$("#search2").val(t);
           				 }
                }); 
		});
		
		function select2()
		{
			var name = document.getElementById('search2').value;
			window.location.href="${ctx}/goods.do?method=page&goodsName="+name;
		}
		</script>
<div class="dvbody">
	<div class="dvsearch" style="margin-top:20px">
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td height="38">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td width="580" height="38" align="right">
								<input type="text" id="search2" name="search2" class="textsearch" />
							</td>
							<!--<td width="29" align="center">
								<span style="font-weight: bold; color: #000">IN</span>
							</td>-->
							<td width="351">
								<table border="0" cellspacing="0" cellpadding="0">
									<tr>
										<!--<td>
											<select name="select" class="selecttype">
												<option selected="selected">
													所有商品
												</option>
											</select>
										</td>-->
										<td>
											<a href="javascript:select2()" class="redbutton">搜索</a>
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
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td>
				<div class="mainbottom">
					<img src="${ctx}/images/indeximg/11.jpg" width="300" height="120" />
				</div>
			</td>
			<td>
				<div class="mainbottom">
					<img src="${ctx}/images/indeximg/12.jpg" width="300" height="120" />
				</div>
			</td>
			<td>
				<div class="mainbottom">
					<img src="${ctx}/images/indeximg/13.jpg" width="300" height="120" />
				</div>
			</td>
		</tr>
	</table>
	<div style="color: #4E4E4E; text-align: center; margin-top: 10px;">
		关于我们 | 条款和条件 | 安全和保密 | 开发者 | 招聘求职 | 分支机构 | 网站地图 | 工具下载 | 联络我们 | 导航中心
	</div>
	<div
		style="color: #4E4E4E; text-align: center; margin:10px auto 10px auto">
		<table border="0" cellspacing="0" cellpadding="2" style="margin:auto">
			<tr>
				<td>
					<img src="${ctx}/images/cardimg/visa.png" width="150" height="80" />
				</td>
				<td>
					<img src="${ctx}/images/cardimg/maestro.png" width="150" height="80" />
				</td>
				<td>
					<img src="${ctx}/images/cardimg/ae.png" width="150" height="80" />
				</td>
				<td>
					<img src="${ctx}/images/cardimg/dc.png" width="150" height="80" />
				</td>
				<td>
					<img src="${ctx}/images/cardimg/jcb.png" width="150" height="80" />
				</td>
			</tr>
			<tr>
				<td>
					&nbsp;
				</td>
				<td>
					<img src="${ctx}/images/cardimg/paypal.gif" width="150" height="80" />
				</td>
				<td>
					<img src="${ctx}/images/cardimg/tnt.gif" width="150" height="80" />
				</td>
				<td>
					<img src="${ctx}/images/cardimg/dhl.gif" width="150" height="80" />
				</td>
				<td>
					&nbsp;
				</td>
			</tr>
		</table>
	</div>
	<div style="color: #4E4E4E; text-align: center">
		Copyright © 2009 U-Bai.com - ICP: 沪ICP备 09033184
	</div>
</div>