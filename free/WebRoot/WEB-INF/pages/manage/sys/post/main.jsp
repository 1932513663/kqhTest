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
var postId='${postId}';
$(function(){
	initPost(postId);
});
//加载选中公司的详细信息
function initPost(postId){
	var ifPost = document.getElementById("ifPost");
	ifPost.src = "${ctx}/manage/sys/post/post?postId="+postId+"&deptId="+deptId;
}
//新增
function add(){
	var ifPost=document.getElementById("ifPost");
	ifPost.src = "${ctx}/manage/sys/post/toAdd?companyId="+companyId+"&deptId="+deptId;
}
//修改
function update(){
	var ifPost=document.getElementById("ifPost");
	ifPost.contentWindow.update();
}
//删除
function del(){
	var ifPost=document.getElementById("ifPost");
	ifPost.contentWindow.del();
}
</script>
<body style="padding: 0px;height:600px;">
	<iframe allowtransparency="true" id="ifPost" src="" frameborder="0" scrolling="no" style="height:100%;width:100%;"></iframe>
</body>
</html>
