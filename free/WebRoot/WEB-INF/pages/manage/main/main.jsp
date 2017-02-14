<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="../common/script.jsp"%>
<%@ include file="../common/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<meta name="description" content="" />
<meta name="keywords" content="" />
</head>
<script type="text/javascript">
	$(function(){
		initTopMenu();
		initCont();
		//隐藏按钮
		orgButtons(true);
	});
	//加载顶部导航栏
	function initTopMenu(){
		var ifrHeader = document.getElementById("ifrHeader");
		ifrHeader.src = "${ctx }/manage/sys/menu/topMenu";
	}
	//加载主题页面
	function initCont(){
		var ifrCont = document.getElementById("ifrCont");
		ifrCont.src = "${ctx}/manage/sys/menu/index";
	}
	//添加左侧布局并加载菜单
	function addPanel(region){
		var options = {
			region: region
		};
		options.width = 152;
		options.content = "<iframe allowtransparency=\"true\" id=\"ifrMenu\" frameborder=\"0\" scrolling=\"no\" style=\"height:100%;width:100%;\"></iframe>";
		$('#mainContent').layout('add', options);
	}
	
	//删除左侧布局
	function removePanel(region){
		$('#mainContent').layout('remove', region);
	}
	
	//中部主体内容
	function switchTo(url) {
		var rightFrame = document.getElementById("ifrCont");
		rightFrame.src = "${ctx}"+url;
	}
	//新增
	function add(){
		var ifrCont=document.getElementById("ifrCont");
		ifrCont.contentWindow.add();
	}
	//修改
	function update(){
		var ifrCont=document.getElementById("ifrCont");
		ifrCont.contentWindow.update();
	}
	//删除
	function del(){
		var ifrCont=document.getElementById("ifrCont");
		ifrCont.contentWindow.del();
	}
	
	//新增按钮是否启用
	function isEnableAddBt(flag){
		if(flag){
			$("#addButton").linkbutton('enable');
		}else{
			$("#addButton").linkbutton('disable');
		}
	}
	//修改按钮是否启用
	function isEnableEditBt(flag){
		if(flag){
			$("#editButton").linkbutton('enable');
		}else{
			$("#editButton").linkbutton('disable');
		}
	}
	//删除按钮是否启用
	function isEnableDelBt(flag){
		if(flag){
			$("#delButton").linkbutton('enable');
		}else{
			$("#delButton").linkbutton('disable');
		}
	}
	function orgButtons(flag){
		if(flag){
			$("#org_buttons").children().hide();
		}else{
			isEnableAddBt(false);
			isEnableEditBt(false);
			isEnableDelBt(false);
			$("#org_buttons").children().show();
		}
	}
</script>	 
<body>
	<div id="mainContent" class="easyui-layout" style="width:100%;height:580px;">
		<!-- 导航栏 -->
		<div data-options="region:'north'" style="height:150px;">
			<iframe allowtransparency="true" id="ifrHeader" src="" frameborder="0" scrolling="no" style="height:100px;width:100%;"></iframe>
			<div align="right" id="org_buttons" style="float: right;width:100%;height: 24px; ">
				<a id="addButton" href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-add',width:80" onclick="add();">新增</a>
				<a id="editButton" href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-edit',width:80"  onclick="update();">修改</a>
				<a id="delButton" href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-remove',width:80"  onclick="del();">删除</a>
			</div>
			<div style="clear: both;margin-bottom: 4px;"></div>
			<div class="easyui-tabs" style="width:100%;height:22px;">
			</div>
		</div>
		<!-- 左侧菜单 -->
		<%-- <div data-options="region:'west'" style="width:150px;">
			<iframe allowtransparency="true" id="ifrMenu" src="${ctx }/manage/sys/menu/leftMenu" frameborder="0" scrolling="no" style="height:100%;width:100%;"></iframe>
		</div> --%>
		<!-- 主体部分 --> 
		<div data-options="region:'center',iconCls:'icon-ok'" style="border:1px;border-style: solid;border-color:#95b8e7;">
			<iframe allowtransparency="true" id="ifrCont" src="" frameborder="0" scrolling="no" style="height:100%;width:100%;"></iframe>
		</div>
	</div>
</body>
</html>
