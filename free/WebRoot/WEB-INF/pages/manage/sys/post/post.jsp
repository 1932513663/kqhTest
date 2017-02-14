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
var postId='${postId}';
$(function(){
	isEnableButton();
});
//修改
function update(){
	window.location.href="${ctx}/manage/sys/post/toEdit?postId="+postId;
}
//删除
function del(){
	var flag=window.confirm("确认删除此职位？");
	if(flag){
		$.ajax({
	        type:'post',      
	        url:'${ctx}/manage/sys/post/del',  
	        data:{postId:postId},
	        cache:false,  
	        dataType:'json',  
	        success:function(data){
	      	  	var json = eval('('+data+')');
	      	  	if(json.resCode=='0000'){
	      		  	alert("删除成功");
	      		  	//删除职位下的该职位
      		 	 	var postIfrMenu = window.parent.parent.document.getElementById("postIfrMenu");
      		 		postIfrMenu.contentWindow.removeNode(postId,companyId,deptId);
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
	var win=window.parent.parent.parent.parent.parent.parent;
	if(deptId==""){
		win.isEnableAddBt(false);
		win.isEnableEditBt(false);
		win.isEnableDelBt(false);
	}else{
		if(postId==""){
			win.isEnableAddBt(true);
			win.isEnableEditBt(false);
			win.isEnableDelBt(false);
		}else{
			win.isEnableAddBt(true);
			win.isEnableEditBt(true);
			win.isEnableDelBt(true);
		}
	}
}
</script>
<body style="padding: 0px;">
	<div>
		<table>
			<tr><td>职位名称:</td><td>${post.postName }</td></tr>
			<tr><td>公司ID:</td><td>${post.companyId }</td></tr>
			<tr><td>部门ID:</td><td>${post.deptId }</td></tr>
		</table>
	</div>
</body>
</html>