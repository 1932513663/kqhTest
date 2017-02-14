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
	var url = "${ctx}/manage/sys/company/edit";
	$.ajax({
		type : "POST",
		dataType : "json",
		url : url,
		data : $('#company_form').serialize(),
		beforeSend : function() {
			return $('#company_form').form('validate');
		},
		success : function(data) {
			var json = eval('('+data+')');
      	  	if(json.resCode=='0000'){
				alert("成功");
				var company=json.info;
				var id=company.id;
				//此处不直接跳转查看页：有可能改该节点的名字及顺序
				var companyIfrMenu = window.parent.parent.document.getElementById("companyIfrMenu");
				companyIfrMenu.contentWindow.initCompanyTree(id);
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
	var win=window.parent.parent.parent;
	win.isEnableAddBt(false);
	win.isEnableEditBt(false);
	win.isEnableDelBt(false);
}
</script>	 
</head>
<body style="padding: 0px;">
	<div>
		<form id="company_form">
			<table>
				<tr>
					<td align="right" width="150px">父节点：</td>
					<td><input id="parentName" class="easyui-textbox" data-options="disabled:true" style="width:200px;" type="text" value="${parentName }" /></td>
					<td align="right" width="150px">公司名称：</td>
					<td><input id="companyName" name="companyName" class="easyui-textbox" data-options="required: true" style="width:200px;" value="${company.companyName }"> </td>
				</tr>
				<tr>
					<td align="right" width="150px">电话:</td>
					<td><input id="tel" name="tel" class="easyui-textbox" data-options="" style="width:200px;" value="${company.tel }"></td>
				</tr>
				<tr>
					<td align="right" width="150px">地址:</td>
					<td colspan="3"><input id="address" name="address" class="easyui-textbox" data-options="multiline:true" style="width:500px;" value="${company.address }"></td>
				</tr>
				<tr>
					<td align="right" width="150px">描述信息:</td>
					<td colspan="3"><input id="description" name="description" class="easyui-textbox" data-options="multiline:true" style="width:560px;" value="${company.description }"></td>
				</tr>
			</table>
			<input id="parentId" name="parentId" type="hidden" value="${company.parentId }"/>
			<input id="id" name="id" type="hidden" value="${company.id }"/>
		</form>
	</div>
	<div style="clear: both;margin-bottom: 80px;"></div>
	<div align="center">
		<a onclick="save()" class="easyui-linkbutton" data-options="iconCls:'icon-save',width:80" href="javascript:;">保存</a>
		<!-- <a onclick="reset()" class="easyui-linkbutton" data-options="iconCls:'icon-remove',width:80" href="javascript:;">重置</a> -->
	</div>
</body>
</html>