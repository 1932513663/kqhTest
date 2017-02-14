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
	var url = "${ctx}/manage/sys/post/edit";
	$.ajax({
		type : "POST",
		dataType : "json",
		url : url,
		data : $('#post_form').serialize(),
		beforeSend : function() {
			return $('#post_form').form('validate');
		},
		success : function(data) {
			var json = eval('('+data+')');
      	  	if(json.resCode=='0000'){
				alert("成功");
				var post=json.post;
				var id=post.id;
				var companyId=post.companyId;
				var deptId=post.deptId;
				//更新页面职位下的职位树
				var postIfrMenu = window.parent.parent.document.getElementById("postIfrMenu");
				postIfrMenu.contentWindow.initPostTree(companyId,deptId,id);
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
	var win=window.parent.parent.parent.parent.parent.parent;
	win.isEnableAddBt(false);
	win.isEnableEditBt(false);
	win.isEnableDelBt(false);
}
</script>	 
</head>
<body style="padding: 0px;">
	<div>
		<form id="post_form">
			<table>
				<tr>
					<td align="right" width="150px">父节点：</td>
					<td><input id="parentName" class="easyui-textbox" data-options="disabled:true" style="width:200px;" type="text" value="${parentName }" /></td>
				</tr>
				<tr>
					<td align="right" width="150px">职位名称：</td>
					<td><input id="postName" name="postName" class="easyui-textbox" data-options="required: true" style="width:200px;" value="${post.postName }"> </td>
				</tr>
			</table>
			<input id="parentId" name="parentId" type="hidden"  value="${post.parentId}"/>
			<input id="companyId" name="companyId" type="hidden" value="${post.companyId}" />
			<input id="deptId" name="deptId" type="hidden" value="${post.deptId}" />
			<input id="id" name="id" type="hidden" value="${post.id}" />
		</form>
	</div>
	<div style="clear: both;margin-bottom: 80px;"></div>
	<div align="center">
		<a onclick="save()" class="easyui-linkbutton" data-options="iconCls:'icon-save',width:80" href="javascript:;">保存</a>
	</div>
</body>
</html>