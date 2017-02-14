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
		var companyId='${companyId}';
		var selectDeptId = null;
		initDeptTree(companyId,null);
	});
	
	//根据前面所选公司初始化部门树
	function initDeptTree(companyId,selectDeptId) {
		$('#deptTree').tree({
			lines:true,
			url : '${ctx}/manage/sys/department/findDeptTree',
			queryParams:{
				companyId:companyId,
				deptId:selectDeptId
			},
			loadFilter : function(data) {
				if (data.d) {
					return data.d;
				} else {
					return data;
				}
			},
			onSelect : function(node) {
				var deptId=node.id;
				window.parent.switchTo("/manage/sys/post/mainIndex?companyId="+companyId+"&deptId="+deptId);
			},
			onLoadSuccess:function(node,data){
				// 查找一个节点并选择它
				if(selectDeptId==null){
					selectDeptId=data[0]==undefined?null:data[0].id;
				}
				var nodeFirst = $('#deptTree').tree('find', selectDeptId);
				if(nodeFirst==undefined){
					window.parent.switchTo("/manage/sys/post/mainIndex?companyId="+companyId);
				}else{
					if(node==null){
						$('#deptTree').tree('select', nodeFirst.target);
					}
				}
				selectDeptId=null;
			}
		});
	}
	
	//从树节点删除该id的节点
	function removeNode(id,companyId){
		// 查找一个节点并选择它
		var node = $("#deptTree").tree('find', id);
		if(node!=undefined){
			$("#deptTree").tree('remove', node.target);
			window.parent.switchTo("/manage/sys/post/mainIndex?companyId="+companyId);
		}
	}
</script>
<body style="padding: 0px;">
	<div style="width:100%;height:400px;overflow:auto;">
		<li>部门树</li>
		<ul id="deptTree">
		</ul>
	</div>
</body>
</html>