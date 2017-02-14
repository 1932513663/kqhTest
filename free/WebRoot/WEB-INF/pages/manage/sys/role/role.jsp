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
var roleId='${roleId}';
$(function(){
	if(roleId!=""){
		initMenuTree(roleId);
	}
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
//修改
function update(){
	window.location.href="${ctx}/manage/sys/role/toEdit?roleId="+roleId;
}
//删除
function del(){
	var flag=window.confirm("确认删除此角色？");
	if(flag){
		var roleId='${roleId}';
		$.ajax({
	          type:'post',      
	          url:'${ctx}/manage/sys/role/del',  
	          data:{roleId:roleId},
	          cache:false,  
	          dataType:'json',  
	          success:function(data){
	        	  var json = eval('('+data+')');
	        	  if(json.resCode=='0000'){
	        		  alert("删除成功");
	        		  var roleIfrMenu = window.parent.document.getElementById("roleIfrMenu");
	        		  roleIfrMenu.contentWindow.removeNode(roleId);
	        	  }else{
	        		  var resMsg=json.resMsg;
	        		  alert(resMsg);
	        	  }
	          },
	          error: function(e){
	        	  alert(e);
	       	  }  
	     });
	}
}
//控制按钮是否可用
function isEnableButton(){
	var win=window.parent.parent.parent;
	if(roleId==""){
		win.isEnableAddBt(true);
		win.isEnableEditBt(false);
		win.isEnableDelBt(false);
	}else{
		win.isEnableAddBt(true);
		win.isEnableEditBt(true);
		win.isEnableDelBt(true);
	}
}
</script>
<body style="padding: 0px;">
<c:if test="${empty role}">
<div align="center">
	<h1>暂无数据</h1>
</div>
</c:if>
<c:if test="${not empty role}">
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
		</form>
	</div>
</c:if>
</body>
</html>