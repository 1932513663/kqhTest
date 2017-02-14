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
	isEnableButton();
});
//保存
function save(){
	var url = "${ctx}/manage/sys/department/edit";
	$.ajax({
		type : "POST",
		dataType : "json",
		url : url,
		data : $('#dept_form').serialize(),
		beforeSend : function() {
			return $('#dept_form').form('validate');
		},
		success : function(data) {
			var json = eval('('+data+')');
      	  	if(json.resCode=='0000'){
				alert("成功");
				var dept=json.dept;
				var id=dept.id;
				var companyId=dept.companyId;
				//更新页面部门的部门树
				var deptIfrMenu = window.parent.parent.document.getElementById("deptIfrMenu");
				deptIfrMenu.contentWindow.initDeptTree(companyId,id);
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
//控制按钮是否可用
function isEnableButton(){
	var win=window.parent.parent.parent.parent.parent;
	win.isEnableAddBt(false);
	win.isEnableEditBt(false);
	win.isEnableDelBt(false);
}
</script>	 
</head>
<body style="padding: 0px;">
	<div>
		<form id="dept_form">
			<table>
				<tr>
					<td align="right" width="150px">父节点：</td>
					<td><input id="parentName" class="easyui-textbox" data-options="disabled:true" style="width:200px;" type="text" value="${parentName }" /></td>
					<td align="right" width="150px">部门名称：</td>
					<td><input id="deptName" name="deptName" class="easyui-textbox" data-options="required: true" style="width:200px;" value="${dept.deptName}" > </td>
				</tr>
				<tr>
					<td align="right" width="150px">电话:</td>
					<td><input id="tel" name="tel" class="easyui-textbox" data-options="" style="width:200px;" value="${dept.tel}" ></td>
				</tr>
				<tr>
					<td align="right" width="150px">地址:</td>
					<td colspan="3"><input id="address" name="address" class="easyui-textbox" data-options="multiline:true" style="width:500px;" value="${dept.address}" ></td>
				</tr>
				<tr>
					<td align="right" width="150px">描述信息:</td>
					<td colspan="3"><input id="description" name="description" class="easyui-textbox" data-options="multiline:true" style="width:560px;" value="${dept.description}" ></td>
				</tr>
			</table>
			<input id="parentId" name="parentId" type="hidden" value="${dept.parentId}" />
			<input id="id" name="id" type="hidden" value="${dept.id}" />
			<input id="companyId" name="companyId" type="hidden" value="${dept.companyId}" />
		</form>
	</div>
	<div style="clear: both;margin-bottom: 80px;"></div>
	<div align="center">
		<a onclick="save()" class="easyui-linkbutton" data-options="iconCls:'icon-save',width:80" href="javascript:;">保存</a>
		<!-- <a onclick="reset()" class="easyui-linkbutton" data-options="iconCls:'icon-remove',width:80" href="javascript:;">重置</a> -->
	</div>
</body>
</html>