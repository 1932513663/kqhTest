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
	var url="/manage/sys/user/toList";
	switchTo(url);
});

//中部主体内容
function switchTo(url) {
	var userIfrCont = document.getElementById("userIfrCont");
	userIfrCont.src = "${ctx}"+url;
}

//新增
function add(){
	var userIfrCont=document.getElementById("userIfrCont");
	userIfrCont.contentWindow.add();
}

//修改
function update(){
	var userIfrCont=document.getElementById("userIfrCont");
	userIfrCont.contentWindow.update();
}

//删除
function del(){
	var userIfrCont=document.getElementById("userIfrCont");
	userIfrCont.contentWindow.del();
}
</script>	 
<body style="padding: 0px;">
	<div id="userContent" class="easyui-layout" style="width:100%;height:430px;border: 0px;">
		<!-- 顶部查询条件 -->
		<!-- <div data-options="region:'north'" style="height:126px;">
			<iframe allowtransparency="true" id="userIfrSearch" src="" frameborder="0" scrolling="no" style="height:100%;width:100%;"></iframe>
		</div> -->
		<!-- 主体部分 -->
		<div data-options="region:'center',iconCls:'icon-ok'" style="height:430px;">
			<iframe allowtransparency="true" id="userIfrCont" src="" frameborder="0" scrolling="no" style="height:100%;width:100%;"></iframe>
		</div>
	</div>
</body>
</html>