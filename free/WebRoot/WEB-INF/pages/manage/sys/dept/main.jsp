<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="../../common/script.jsp"%>
<%@ include file="../../common/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
</head>
<script type="text/javascript">
var companyId='${companyId}';
var deptId='${deptId}';
$(function(){
	initDept(deptId);
});
//加载选中公司的详细信息
function initDept(deptId){
	var ifDept = document.getElementById("ifDept");
	ifDept.src = "${ctx}/manage/sys/department/dept?deptId="+deptId;
}
//新增
function add(){
	var ifDept=document.getElementById("ifDept");
	ifDept.src = "${ctx}/manage/sys/department/toAdd?companyId="+companyId;
}
//修改
function update(){
	var ifDept=document.getElementById("ifDept");
	ifDept.contentWindow.update();
}
//删除
function del(){
	var ifDept=document.getElementById("ifDept");
	ifDept.contentWindow.del();
}
</script>
<body style="padding: 0px;height:600px;">
	<iframe allowtransparency="true" id="ifDept" src="" frameborder="0" scrolling="no" style="height:100%;width:100%;"></iframe>
</body>
</html>
