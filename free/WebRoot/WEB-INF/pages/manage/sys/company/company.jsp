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
$(function(){
	isEnableButton();
});
//修改
function update(){
	window.location.href="${ctx}/manage/sys/company/toEdit?companyId="+companyId;
}
//删除
function del(){
	var flag=window.confirm("确认删除此公司？");
	if(flag){
		var companyId='${companyId}';
		$.ajax({
	          type:'post',      
	          url:'${ctx}/manage/sys/company/del',  
	          data:{companyId:companyId},
	          cache:false,  
	          dataType:'json',  
	          success:function(data){
	        	  var json = eval('('+data+')');
	        	  if(json.resCode=='0000'){
	        		  alert("删除成功");
	        		  var companyIfrMenu = window.parent.parent.document.getElementById("companyIfrMenu");
		        	  companyIfrMenu.contentWindow.removeNode(companyId);
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
	if(companyId==""){
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
			<tr><td>公司名称:</td><td>${company.companyName }</td></tr>
			<tr><td>地址:</td><td>${company.address }</td></tr>
			<tr><td>电话:</td><td>${company.tel }</td></tr>
			<tr><td>描述信息:</td><td>${company.description }</td></tr>
		</table>
	</div>
</body>
</html>