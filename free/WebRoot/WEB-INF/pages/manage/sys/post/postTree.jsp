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
		var deptId='${deptId}';
		var selectPostId = null;
		initPostTree(companyId,deptId,selectPostId);
	});
	//初始化职位树
	function initPostTree(companyId,deptId,selectPostId) {
		$('#postTree').tree({
			lines:true,
			url : '${ctx}/manage/sys/post/findPostTree',
			queryParams:{
				companyId:companyId,
				deptId:deptId,
				postId:selectPostId
			},
			loadFilter : function(data) {
				if (data.d) {
					return data.d;
				} else {
					return data;
				}
			},
			onSelect : function(node) {
				var postId=node.id;
				window.parent.switchTo("/manage/sys/post/main?postId="+postId+"&companyId="+companyId+"&deptId="+deptId);
			},
			onLoadSuccess:function(node,data){
				// 查找一个节点并选择它
				if(selectPostId==null){
					selectPostId=data[0]==undefined?null:data[0].id;
				}
				var nodeFirst = $('#postTree').tree('find', selectPostId);
				if(nodeFirst==undefined){
					window.parent.switchTo("/manage/sys/post/main?companyId="+companyId+"&deptId="+deptId);
				}else{
					if(node==null){
						$('#postTree').tree('select', nodeFirst.target);
					}
				}
				selectPostId=null;
			}
		});
	}
	//从树节点删除该id的节点
	function removeNode(id,companyId,deptId){
		// 查找一个节点并选择它
		var node = $("#postTree").tree('find', id);
		if(node!=undefined){
			$("#postTree").tree('remove', node.target);
			window.parent.switchTo("/manage/sys/post/main?companyId="+companyId+"&deptId="+deptId);
		}
	}
</script>
<body style="padding: 0px;">
	<div style="width:100%;height:400px;overflow:auto;">
		<li>公司部门职位树</li>
		<ul id="postTree">
		</ul>
	</div>
</body>
</html>