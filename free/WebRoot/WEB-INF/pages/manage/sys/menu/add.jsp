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
	selectParentName();
	//控制按钮是否可用
	isEnableButton();
});
//选择父类节点
function selectParentName(companyId){
	$('#parentName').combotree({    
		url : '${ctx}/manage/sys/menu/findMenuTreeOfSelect',
	    required: true,
	    panelHeight:'auto',//高度自适应
	    onSelect:function(node){
	    	$("#parentId").val(node.id);
	    }
	}); 
}
//保存
function save(){
	var url = "${ctx}/manage/sys/menu/save";
	$.ajax({
		type : "POST",
		dataType : "json",
		url : url,
		data : $('#menu_form').serialize(),
		beforeSend : function() {
			return $('#menu_form').form('validate');
		},
		success : function(data) {
			var json = eval('('+data+')');
      	  	if(json.resCode=='0000'){
				alert("成功");
				var menu=json.menu;
				window.parent.parent.switchTo("/manage/sys/menu/toList");
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
<body style="padding: 0px;">
	<div>
		<form id="menu_form">
			<table>
				<tr>
					<td align="right" width="150px">选择父节点：</td>
					<td><input id="parentName" style="width:200px;" type="text" /></td>
					<td align="right" width="150px">菜单名称：</td>
					<td><input id="menuName" name="menuName" class="easyui-textbox" data-options="required: true" style="width:200px;"> </td>
				</tr>
				<tr>
					<td align="right" width="150px">路径:</td>
					<td><input id="tel" name="tel" class="easyui-textbox" data-options="" style="width:200px;"></td>
				</tr>
				<tr>
					<td align="right" width="150px">排序号:</td>
					<td><input id="seq" name="seq" class="easyui-numberbox" data-options="min:1,precision:0" style="width:30px;"></td>
				</tr>
				<tr>
					<td align="right" width="150px">描述信息:</td>
					<td colspan="3"><input id="description" name="description" class="easyui-textbox" data-options="multiline:true" style="width:560px;"></td>
				</tr>
			</table>
			<input id="parentId" name="parentId" type="hidden" />
		</form>
	</div>
	<div style="clear: both;margin-bottom: 80px;"></div>
	<div align="center">
		<a onclick="save()" class="easyui-linkbutton" data-options="iconCls:'icon-save',width:80" href="javascript:;">保存</a>
	</div>
</body>
</html>