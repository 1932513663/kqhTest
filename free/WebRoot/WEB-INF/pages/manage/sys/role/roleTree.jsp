<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="../../common/script.jsp"%>
<%@ include file="../../common/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<script type="text/javascript">
	$(function() {
		//控制按钮是否可用
		isEnableButton();
		var selectRoleId=null;
		initRoleTree(selectRoleId);
	});
	function initRoleTree(selectRoleId) {
		$('#roleTree').tree({
			lines:true,
			url : '${ctx}/manage/sys/role/findRoleTree',
			queryParams:{
				roleId:selectRoleId
			},
			loadFilter : function(data) {
				if (data.d) {
					return data.d;
				} else {
					return data;
				}
			}, 
			onSelect : function(node) {
				var roleId=node.id;
				window.parent.switchTo("/manage/sys/role/role?roleId="+roleId);
			},
			onLoadSuccess:function(node,data){
				// 查找一个节点并选择它
				if(selectRoleId==null){
					selectRoleId=data[0]==undefined?null:data[0].id;
				}
				var nodeFirst = $('#roleTree').tree('find', selectRoleId);
				if(nodeFirst==undefined){
					window.parent.switchTo("/manage/sys/role/role");
				}else{
					if(node==null){
						$('#roleTree').tree('select', nodeFirst.target);
					}
				}
				selectRoleId=null;
			}
		});
	}
	
	//从树节点删除该id的节点
	function removeNode(id){
		// 查找一个节点并选择它
		var node = $("#roleTree").tree('find', id);
		if(node!=undefined){
			$("#roleTree").tree('remove', node.target);
			window.parent.switchTo("/manage/sys/role/role");
		}
	}
	
	//控制按钮是否可用
	function isEnableButton(){
		var win=window.parent.parent;
		win.isEnableAddBt(false);
		win.isEnableEditBt(false);
		win.isEnableDelBt(false);
	}
</script>
</head>
<!-- border:1px;border-style: solid; 作用加边框 -->
<body style="padding: 0px;border-color:#95b8e7;">
	<span class="tree_root_image">角色树</span>
	<div style="width:100%;height:400px;overflow:auto;">
		<ul id="roleTree">
		</ul>
	</div>
</body>
</html>