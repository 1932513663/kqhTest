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
	var companyId='${companyId}';
	initDeptPostIfrMenu(companyId);
});
//加载树形菜单
function initDeptPostIfrMenu(companyId){
	var deptPostIfrMenu = document.getElementById("deptPostIfrMenu");
	deptPostIfrMenu.src = "${ctx }/manage/sys/post/deptTree?companyId="+companyId;
}
function switchTo(url) {
	var postIndexIfrCont = document.getElementById("postIndexIfrCont");
	postIndexIfrCont.src = "${ctx}"+url;
}
//新增
function add(){
	var postIndexIfrCont=document.getElementById("postIndexIfrCont");
	postIndexIfrCont.contentWindow.add();
}
//修改
function update(){
	var postIndexIfrCont=document.getElementById("postIndexIfrCont");
	postIndexIfrCont.contentWindow.update();
}
//删除
function del(){
	var postIndexIfrCont=document.getElementById("postIndexIfrCont");
	postIndexIfrCont.contentWindow.del();
}
</script>	 
<body style="padding: 0px;margin: 0px;">
	<div id="sysMainContent" class="easyui-layout" style="width:100%;height:580px;">
		<!-- 左侧菜单 -->
		<div data-options="region:'west'" style="width:200px;">
			<iframe allowtransparency="true" id="deptPostIfrMenu" src="" frameborder="0" scrolling="no" style="height:100%;width:100%;"></iframe>
		</div>
		<!-- 主体部分 -->
		<div data-options="region:'center',iconCls:'icon-ok'">
			<iframe allowtransparency="true" id="postIndexIfrCont" src="" frameborder="0" scrolling="no" style="height:100%;width:100%;"></iframe>
		</div>
	</div>
</body>
</html>