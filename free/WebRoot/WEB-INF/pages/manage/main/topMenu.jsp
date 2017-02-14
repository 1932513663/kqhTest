<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="../common/script.jsp"%>
<%@ include file="../common/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="${ctx}/static/manage/css/common.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/static/manage/css/wheat_header.css" rel="stylesheet" type="text/css" />
<title></title>
</head>
<body>
	<div class="header clear pr">
		<h1 class="logo fl">
			<img src="${ctx}/static/manage/images/logo.png" width="150" height="51" />
		</h1>
		<ul class="header_nav fl">
			<c:if test="${not empty topMenu}">
				<li>
					<a class="home" sourceName="home" source="0" href="javascript:;">首页</a>
				</li>
				<c:forEach var="menu" items="${topMenu}" varStatus="status">
					<c:if test="${not empty menu}">
						<li>
							<a href="javascript:void(0)" sourceName="${menu.menuName}" source="${menu.id}">${menu.menuName}</a>
						</li>
					</c:if>
				</c:forEach>
			</c:if>
		</ul>
		<div class="user fr">
			<span class="photo fl"> 
				<a href="#">
					<img class="userPic" src="${ctx}/static/manage/images/photo.png" />
				</a>
			</span>
			<div class="user_info_do fl">
				<span class="welcome">欢迎您！<a href="#">${userName }</a></span><br />
				<a class="change_psw" href="#">修改密码</a>
				<span>|</span> 
				<a class="quit" href="javascript:;" onclick="javascript:window.parent.location.href='${ctx}/shiro/logout'">退出</a>
			</div>
		</div>
	</div>
</body>
<script>
	$(function() {
		setMenuStyle();
	});
	function leftMenu(parentId) {
		window.parent.leftMenu(parentId);
	}
	$('.header_nav').find('a').on('click', function() {
		var file = $(this).attr('source');
		var sourceName = $(this).attr('sourceName');
		if (sourceName == 'home') { //首页没有左侧导航，需要重新计算宽度，设置左侧iframe宽度为0
			$('#ifrCont',window.top.document).attr('src','${ctx}/manage/sys/menu/index');
			window.parent.removePanel("west");
			window.parent.orgButtons(true);
		} else if (sourceName == '公司管理'){
			$('#ifrCont',window.top.document).attr('src','${ctx}/manage/sys/company/index');
			window.parent.removePanel("west");
			window.parent.orgButtons(false);
		} else {
			window.parent.addPanel("west");
			path = "${ctx }/manage/sys/menu/leftMenu?parentId=" + file;
			//点击头部导航时左侧导航iframe的src跳转到对应地址，右侧内容地址设置在左侧导航页，由左侧导航页第一条栏目决定
			$('#ifrMenu', window.top.document).attr('src', path);
			window.parent.orgButtons(false);
		}
	});
	//按照交易类型查找
	function setMenuStyle() {
		$(".header_nav a").each(function(index, element) {
			var sourceName = $(this).attr('sourceName');
			if (sourceName == 'home') {
				$(this).attr('class', 'home');
			}
			if (sourceName == '系统管理') {
				$(this).attr('class', 'asset');
			}
			if (sourceName == '公司管理') {
				$(this).attr('class', 'repayment');
			}

			if (sourceName == '菜单2') {
				$(this).attr('class', 'usermag');
			}
			if (sourceName == '菜单3') {
				$(this).attr('class', 'statistics');
			}
			if (sourceName == '菜单4') {
				$(this).attr('class', 'sysmag');
			}
			if (sourceName == '菜单5') {
				$(this).attr('class', 'planting');
			}

		});
	}
</script>
</html>
