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
	initCompanyIfrMenu();
});

//加载树形菜单
function initCompanyIfrMenu(){
	var companyIfrMenu = document.getElementById("companyIfrMenu");
	companyIfrMenu.src = "${ctx }/manage/sys/company/companyTree";
}
//中部主体内容
function switchTo(url) {
	var companyIfrCont = document.getElementById("companyIfrCont");
	companyIfrCont.src = "${ctx}"+url;
}

//新增
function add(){
	var companyIfrCont=document.getElementById("companyIfrCont");
	companyIfrCont.contentWindow.add();
}

//修改
function update(){
	var companyIfrCont=document.getElementById("companyIfrCont");
	companyIfrCont.contentWindow.update();
}

//删除
function del(){
	var companyIfrCont=document.getElementById("companyIfrCont");
	companyIfrCont.contentWindow.del();
}
</script>	 
<body style="padding: 0px;">
	<div id="sysMainContent" class="easyui-layout" style="width:100%;height:580px;border: 0px;">
		<!-- 左侧菜单 -->
		<div data-options="region:'west'" style="width:200px;">
			<iframe allowtransparency="true" id="companyIfrMenu" src="" frameborder="0" scrolling="no" style="height:100%;width:100%;"></iframe>
		</div>
		<!-- 主体部分 -->
		<div data-options="region:'center',iconCls:'icon-ok'">
			<iframe allowtransparency="true" id="companyIfrCont" src="" frameborder="0" scrolling="no" style="height:100%;width:100%;"></iframe>
		</div>
	</div>
</body>
</html>