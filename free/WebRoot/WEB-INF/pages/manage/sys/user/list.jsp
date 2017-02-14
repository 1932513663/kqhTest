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
var endIndex;
$(function(){
	initUserDatagrid();
	isEnableButton();
});

//控制按钮是否可用
function isEnableButton(){
	var win=window.parent.parent;
	win.isEnableAddBt(true);
	win.isEnableEditBt(true);
	win.isEnableDelBt(true);
}
//初始化菜单树形可编辑表哥
function initUserDatagrid(){
	var url="${ctx}/manage/sys/user/findUserDatagrid";
	$('#userDatagrid').datagrid({    
	    url:url,  
	    method: 'post',
	    singleSelect:true,
	    fitColumns: false,
	    toolbar: '#conditionSearch',
	    pagination : true,//分页
	    pageSize : 10,//页面大小
	    pageList : [ 10, 20, 30, 40, 50 ],
	    sortName : 'id',
		sortOrder : 'desc',
	    rownumbers: true,
		collapsible: true,
		lines: true,
	    idField:'id',    
	    columns:[[    
	        {title:'用户名',field:'userName',width:100,align:'left',halign:'center'
	        },
	        {title:'性别',field:'sex',width:50,align:'left',halign:'center'
	        },
	        {title:'公司名称',field:'companyId',width:250,align:'left',halign:'center'
	        },    
	        {title:'部门名称',field:'deptId',width:250,align:'left',halign:'center'
	        },    
	        {title:'职位名称',field:'postId',width:150,align:'left',halign:'center'
	        },    
	        {title:'手机号',field:'mobile',width:100,align:'left',halign:'center'
	        },
	        {title:'邮箱',field:'email',width:120,align:'left',halign:'center'
	        },
	        {title:'操作',field:'operation',width:100,align:'center',halign:'center',
	        	formatter: function(value,row,index){
	        		var userId=row.id;
					var str="<button onclick=\"authorityToUser('"+userId+"')\">授权</button>";
	        		return str;
				}
	        }
	    ]]
	}); 
}

//新增
function add(){
	//跳转新增页面
	window.location.href="${ctx}/manage/sys/user/toAdd";
}

//修改
function update(){
	var row = $('#userDatagrid').datagrid('getSelected');/*获取选中的行*/
	if(row != null){
		var userId=row.id;
		window.location.href="${ctx}/manage/sys/user/toEdit?userId="+userId;
	}else{
		alert("请选择要修改的数据");
	}
}
//删除
function del(){
	var row = $('#userDatagrid').datagrid('getSelected');/*获取选中的行*/
	if(row != null){
		var flag=window.confirm("确认删除此菜单？");
		if(flag){
			var userId=row.id;
			$.ajax({
		          type:'post',      
		          url:'${ctx}/manage/sys/user/del',  
		          data:{userId:userId},
		          cache:false,  
		          dataType:'json',  
		          success:function(data){
		        	  var json = eval('('+data+')');
		        	  if(json.resCode=='0000'){
		        		  alert("删除成功");
		        		  removeNode("menuTreegrid",menuId);
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
	}else{
		alert("请选择要删除的数据");
	}
}

//搜索
function doSearch() {
	var userName=$('#userName').textbox('getValue');
	var mobile=$('#mobile').textbox('getValue');
	$('#userDatagrid').datagrid('load', {
		userName : userName,
		mobile : mobile
	});
}

//打开授权窗口
function authorityToUser(userId){
	$("#userId_win").val(userId);
	initRoleTree(userId);
	$('#authority').window('open');
}
//关闭授权窗口
function closeWin(){
	$("#userId_win").val("");
	$('#authority').window('close');
}

//保存角色权限
function saveWin(){
	var userId=$("#userId_win").val();
	if(userId){
		var userRoles={};
		var nodes = $("#roleTree").tree('getChecked');
		var len=nodes.length;
		var roleIds=[];
		if(len>0){
			for (var i = 0; i < len; i++) {
				var node=nodes[i];
				var roleId=node.id;
				roleIds.push(roleId);
			}
		}
		userRoles.userId=userId;
		userRoles.roleIds=roleIds;
		var userRolesStr=JSON.stringify(userRoles);
		$.ajax({
	          type:'post',      
	          url:"${ctx}/manage/sys/user/saveRoleUsers",
	          data:{userRolesStr:userRolesStr},  
	          cache:false,  
	          dataType:'json',  
	          success:function(data){
	        	  var json = eval('('+data+')');
	        	  if(json.resCode=='0000'){
	        		  alert("授权成功");
	        		  closeWin();
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
//加载角色树显示权限分配
function initRoleTree(userId) {
	$('#roleTree').tree({
		lines:true,
		url : '${ctx}/manage/sys/user/findRoleTreeOfUser',
		checkbox:true,
		queryParams:{
			userId:userId
		}
	});
}

</script>
<body style="padding: 0px;">
	<div style="width:100%;height:434px;">
		<table id="userDatagrid" style="width:100%;height:100%"></table>
	</div>
	<!-- 搜索条件 -->
	<div id="conditionSearch" style="height:100px;background-color: #ffffff;">
		<div style="clear: both;margin-bottom: 24px;"></div>
		<div>
			<span style="margin-left:50px;">用户名:</span>
			<input id="userName" class="easyui-textbox" style="width:100px;"> 
			<span style="margin-left:50px;">手机号:</span>
			<input id="mobile" class="easyui-textbox" style="width:100px;"> 
			<a href="javascript:void(0);" style="margin-left:50px;" class="easyui-linkbutton" data-options="iconCls:'icon-search',width:80" onclick="doSearch();">搜索</a>
		</div>
	</div>
	<!-- 角色授权 -->
	<div id="authority" class="easyui-window" title="授权" data-options="modal:true,closed:true,minimizable:false,maximizable:false,collapsible:false,resizable:false" style="width:500px;height:300px;padding:10px;">
		<div style="height:210px;overflow:auto;">
			<ul id="roleTree" style="height:210px;">
			</ul>
		</div>
		<div style="clear: both;margin-bottom: 10px;"></div>
		<div align="center">
			<button onclick="saveWin();">保存</button>
			<button onclick="closeWin();">关闭</button>
		</div>
		<input type="hidden" id="userId_win" />
	</div>
</body>
</html>