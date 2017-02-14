<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="../../common/script.jsp"%>
<%@ include file="../../common/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
</head>
<script type="text/javascript">
	$(function() {
		//控制按钮是否可用
		isEnableButton();
		var selectCompanyId=null;
		initCompanyTree(selectCompanyId);
	});
	function initCompanyTree(selectCompanyId) {
		$('#companyTree').tree({
			lines:true,
			url : '${ctx}/manage/sys/company/findCompanyTree',
			queryParams:{
				companyId:selectCompanyId
			},
			loadFilter : function(data) {
				if (data.d) {
					return data.d;
				} else {
					return data;
				}
			}, onSelect : function(node) {
				var companyId=node.id;
				window.parent.switchTo("/manage/sys/company/main?companyId="+companyId);
			},onLoadSuccess:function(node,data){
				// 查找一个节点并选择它
				if(selectCompanyId==null){
					selectCompanyId=data[0]==undefined?null:data[0].id;
				}
				var nodeFirst = $('#companyTree').tree('find', selectCompanyId);
				if(nodeFirst==undefined){
					window.parent.switchTo("/manage/sys/company/main");
				}else{
					if(node==null){
						$('#companyTree').tree('select', nodeFirst.target);
					}
				}
				selectCompanyId=null;
			}
		});
	}
	
	//从树节点删除该id的节点
	function removeNode(id){
		// 查找一个节点并选择它
		var node = $("#companyTree").tree('find', id);
		if(node!=undefined){
			$("#companyTree").tree('remove', node.target);
			window.parent.switchTo("/manage/sys/company/main");
		}
	}
	
	//控制按钮是否可用
	function isEnableButton(){
		var win=window.parent.parent;
		win.isEnableAddBt(false);
		win.isEnableEditBt(false);
		win.isEnableDelBt(false);
	}
</script>
<body style="padding: 0px;border:1px;border-style: solid;border-color:#95b8e7;">
	<div style="width:100%;height:430px;overflow:auto;">
		<ul id="companyTree">
		</ul>
	</div>
</body>
</html>