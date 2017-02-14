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
//在ie下面，下拉框存在backspace回退问题，咱没解决
/* $(document).keydown(function (e) {
    if (e.keyCode == 8) {
        return false;
    }
}); */
$(function(){
	//控制按钮是否可用
	isEnableButton();
	selectCompany();
	selectDept(null);
	selectPost(null,null);
});
//选择公司
function selectCompany(){
	$('#companyName').combotree({    
		url : '${ctx}/manage/sys/user/selectCompany',
	    required: true,
	    editable:false,
	    panelHeight:'auto',//高度自适应
	    onSelect:function(node){
	    	var companyId=node.id;
	    	var selectOld=$('#companyName').combotree('getValue');
	    	if(companyId!=selectOld){
		    	$("#companyId").val(companyId);
		    	selectDept(companyId);
		    	selectPost(null,null);
	    	}
	    },
	    loadFilter:function(data){
			var len=data.length;
	    	if(len===0){
	    		$('#companyName').combo("disable",true);
	    	}
	    	return data;
	    }
	}); 
}
//选择部门
function selectDept(companyId){
	$('#deptName').combotree({    
		url : '${ctx}/manage/sys/user/selectDept',
	    required: false,
	    editable:false,
	    panelHeight:'auto',//高度自适应
	    queryParams:{
			companyId:companyId
		},
	    onSelect:function(node){
	    	var deptId=node.id;
	    	var selectOld=$('#deptName').combotree('getValue');
	    	if(deptId!=selectOld){
		    	$("#deptId").val(deptId);
		    	selectPost(companyId,deptId);
	    	}
	    },
	    onShowPanel:function(){
	    	if(!companyId){
	    		alert("请先选择公司");
	    		return;
	    	}
	    },
	    loadFilter:function(data){
			var len=data.length;
	    	if(len===0){
	    		$('#deptName').combo("disable",true);
	    	}
	    	return data;
	    }
	}); 
}
//选择职位
function selectPost(companyId,deptId){
	$('#postName').combotree({    
		url : '${ctx}/manage/sys/user/selectPost',
	    required: false,
	    editable:false,
	    panelHeight:'auto',//高度自适应
	    queryParams:{
			companyId:companyId,
			deptId:deptId
		},
	    onSelect:function(node){
	    	var postId=node.id;
	    	var selectOld=$('#postName').combotree('getValue');
	    	if(postId!=selectOld){
		    	$("#postId").val(postId);
	    	}
	    },
	    onShowPanel:function(){
	    	var companyId=$("#companyId").val();
	    	var deptId=$("#deptId").val();
	    	if(!companyId){
	    		alert("请先选择公司");
	    		return;
	    	}
	    	if(!deptId){
	    		alert("请先选择部门");
	    		return;
	    	}
	    },
	    loadFilter:function(data){
			var len=data.length;
	    	if(len===0){
	    		$('#postName').combo("disable",true);
	    	}
	    	return data;
	    }
	}); 
}
//保存
function save(){
	var url = "${ctx}/manage/sys/user/save";
	$.ajax({
		type : "POST",
		dataType : "json",
		url : url,
		data : $('#user_form').serialize(),
		beforeSend : function() {
			return $('#user_form').form('validate');
		},
		success : function(data) {
			var json = eval('('+data+')');
      	  	if(json.resCode=='0000'){
				alert("成功");
				window.parent.parent.switchTo("/manage/sys/user/toList");
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
	<div style="clear: both;margin-bottom: 20px;"></div>
	<div>
		<form id="user_form">
			<table>
				<tr>
					<td align="right" width="150px">选择公司：</td>
					<td><input id="companyName" style="width:200px;" type="text" /></td>
					<td align="right" width="150px">选择部门：</td>
					<td><input id="deptName" style="width:200px;"> </td>
					<td align="right" width="150px">选择职位：</td>
					<td><input id="postName" style="width:200px;"> </td>
				</tr>
				<tr>
					<td align="right" width="150px">用户名:</td>
					<td><input id="userName" name="userName" class="easyui-textbox" data-options="required: true" style="width:200px;"></td>
					<td align="right" width="150px">密码:</td>
					<td><input id="password" name="password" class="easyui-textbox" data-options="required: true,type:password" style="width:200px;"></td>
				</tr>
			</table>
			<input id="companyId" name="companyId" type="hidden" />
			<input id="deptId" name="deptId" type="hidden" />
			<input id="postId" name="postId" type="hidden" />
		</form>
	</div>
	<div style="clear: both;margin-bottom: 80px;"></div>
	<div align="center">
		<a onclick="save()" class="easyui-linkbutton" data-options="iconCls:'icon-save',width:80" href="javascript:;">保存</a>
	</div>
</body>
</html>