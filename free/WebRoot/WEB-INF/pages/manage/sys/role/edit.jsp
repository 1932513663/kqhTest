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
<script type="text/javascript">
$(function(){
	var roleId="${role.id}";
	//初始化菜单树
	initMenuTree(roleId);
	isEnableButton();
});

//加载角色树显示权限分配
function initMenuTree(roleId) {
	$('#menuTree').tree({
		lines:true,
		url : '${ctx}/manage/sys/role/findMenuTreeOfRole',
		checkbox:true,
		queryParams:{
			roleId:roleId
		},
		loadFilter : function(data) {
			if (data.d) {
				return data.d;
			} else {
				return data;
			}
		}
	});
}
//保存
function save(){
	var url = "${ctx}/manage/sys/role/edit";
	setRoleMenus();
	$.ajax({
		type : "POST",
		dataType : "json",
		url : url,
		data : $('#role_form').serialize(),
		beforeSend : function() {
			return $('#role_form').form('validate');
		},
		success : function(data) {
			var json = eval('('+data+')');
      	  	if(json.resCode=='0000'){
      	  		alert("成功");
      	  		var role=json.info;
				var id=role.id;
				var roleIfrMenu = window.parent.document.getElementById("roleIfrMenu");
				roleIfrMenu.contentWindow.initRoleTree(id);
      	  	}else{
      		  	var resMsg=json.resMsg;
      		  	alert(resMsg);
      	  	}
		},
		error : function(e) {
			alert("出错：" + e);
		}
	});
}
//给roleMenus赋值
function setRoleMenus(){
	var roleMenus={};
	var nodes = $("#menuTree").tree('getChecked');
	var len=nodes.length;
	var menuIds=[];
	if(len>0){
		for (var i = 0; i < len; i++) {
			var node=nodes[i];
			var menuId=node.id;
			menuIds.push(menuId);
		}
	}
	roleMenus.menuIds=menuIds;
	var roleMenusStr=JSON.stringify(roleMenus);
	$("#roleMenusStr").val(roleMenusStr);
}

//控制按钮是否可用
function isEnableButton(){
	var win=window.parent.parent.parent;
	win.isEnableAddBt(false);
	win.isEnableEditBt(false);
	win.isEnableDelBt(false);
}
</script>	 
</head>
<body style="padding: 0px;">
	<div>
		<form id="role_form">
			<table>
				<tr>
					<td align="right" width="150px">父节点：</td>
					<td><input id="parentName" class="easyui-textbox" data-options="disabled:true" style="width:200px;" type="text" value="${parentName }" /></td>
					<td align="right" width="150px">角色名称：</td>
					<td><input id="roleName" name="roleName" class="easyui-textbox" data-options="required: true" style="width:200px;" value="${role.roleName }"> </td>
				</tr>
				<tr>
					<td align="right" width="150px">描述信息:</td>
					<td colspan="3"><input id="description" name="description" class="easyui-textbox" data-options="multiline:true" style="width:560px;" value="${role.description }"></td>
				</tr>
				<tr>
					<td align="right" style="vertical-align:text-top;" width="150px">菜单权限:</td>
					<td colspan="3">
						<div style="height:210px;overflow:auto;">
							<ul id="menuTree" style="height:210px;">
							</ul>
						</div>
					</td>
				</tr>
			</table>
			<input id="parentId" name="parentId" type="hidden" value="${role.parentId }"/>
			<input id="id" name="id" type="hidden" value="${role.id }"/>
			<input id="roleMenusStr" name="roleMenusStr" type="hidden" />
		</form>
	</div>
	<div style="clear: both;margin-bottom: 80px;"></div>
	<div align="center">
		<a onclick="save()" class="easyui-linkbutton" data-options="iconCls:'icon-save',width:80" href="javascript:;">保存</a>
		<!-- <a onclick="reset()" class="easyui-linkbutton" data-options="iconCls:'icon-remove',width:80" href="javascript:;">重置</a> -->
	</div>
</body>
</html>