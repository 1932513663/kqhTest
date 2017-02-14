<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="../../common/script.jsp"%>
<%@ include file="../../common/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<meta name="description" content="" />
<meta name="keywords" content="" />
</head>
<script type="text/javascript">
$(function(){
	initRoleIfrMenu();
});

//加载树形菜单
function initRoleIfrMenu(){
	var roleIfrMenu = document.getElementById("roleIfrMenu");
	roleIfrMenu.src = "${ctx }/manage/sys/role/roleTree";
}
//中部主体内容
function switchTo(url) {
	var roleIfrCont = document.getElementById("roleIfrCont");
	roleIfrCont.src = "${ctx}"+url;
}

//新增
function add(){
	var roleIfrCont=document.getElementById("roleIfrCont");
	roleIfrCont.src = "${ctx}/manage/sys/role/toAdd";
}

//修改
function update(){
	var roleIfrCont=document.getElementById("roleIfrCont");
	roleIfrCont.contentWindow.update();
}

//删除
function del(){
	var roleIfrCont=document.getElementById("roleIfrCont");
	roleIfrCont.contentWindow.del();
}
</script>	 
<body style="padding: 0px;">
	<div id="roleContent" class="easyui-layout" style="width:100%;height:580px;border: 0px;">
		<!-- 左侧菜单 -->
		<div data-options="region:'west'" style="width:200px;">
			<iframe allowtransparency="true" id="roleIfrMenu" src="" frameborder="0" scrolling="no" style="height:100%;width:100%;"></iframe>
		</div>
		<!-- 主体部分 -->
		<div data-options="region:'center',iconCls:'icon-ok'">
			<iframe allowtransparency="true" id="roleIfrCont" src="" frameborder="0" scrolling="no" style="height:100%;width:100%;"></iframe>
		</div>
	</div>
</body>
</html>