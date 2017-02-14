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
var companyId='${companyId}';
var deptId='${deptId}';
$(function(){
	isEnableButton();
});
//修改
function update(){
	window.location.href="${ctx}/manage/sys/department/toEdit?deptId="+deptId;
}
//删除
function del(){
	var flag=window.confirm("确认删除此部门？");
	if(flag){
		$.ajax({
	          type:'post',      
	          url:'${ctx}/manage/sys/department/del',  
	          data:{deptId:deptId},
	          cache:false,  
	          dataType:'json',  
	          success:function(data){
	        	  var json = eval('('+data+')');
	        	  if(json.resCode=='0000'){
	        		  alert("删除成功");
	        		  //删除后初始化部门的部门树
	        		  var deptIfrMenu = window.parent.parent.document.getElementById("deptIfrMenu");
	        		  deptIfrMenu.contentWindow.removeNode(deptId,companyId);
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
	var win=window.parent.parent.parent.parent.parent;
	if(deptId==""){
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
	<div>
		<table>
			<tr><td>部门名称:</td><td>${dept.deptName }</td></tr>
			<tr><td>部门类型:</td><td>${dept.deptType }</td></tr>
			<tr><td>部门介绍:</td><td>${dept.deptIntro }</td></tr>
			<tr><td>描述信息:</td><td>${dept.description }</td></tr>
		</table>
	</div>
</body>
</html>