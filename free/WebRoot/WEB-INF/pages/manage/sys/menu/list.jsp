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
	initMenuTreegrid();
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
function initMenuTreegrid(){
	var url="${ctx}/manage/sys/menu/findMenuTreegrid";
	$('#menuTreegrid').treegrid({    
	    url:url,  
	    method: 'post',
	    fitColumns: false,
	    pagination : false,//不分页
	    rownumbers: false,
		collapsible: true,
		lines: true,
	    idField:'id',    
	    treeField:'menuName', //树形节点位置   
	    columns:[[    
	        {title:'菜单名称',field:'menuName',width:250,align:'left',halign:'center'/* ,
	        	editor:{type:'text'} */
	        },    
	        {title:'路径',field:'forward',width:350,align:'left',halign:'center'/* ,
	        	editor:{type:'text'} */
	        },    
	        {title:'排序号',field:'seq',width:80,align:'right',halign:'center'/* ,
	        	editor:{type:'numberbox',
	        		options:{min:0,precision:0}
	        	} */
	        }
	    ]]/* ,
	    onClickCell:function(field,row){
	    	var index=row.id;
	    	if(endIndex!=undefined){
		    	endEditCellSelect("menuTreegrid",endIndex);
	    	}
	    	beginEditCellSelect("menuTreegrid",field,index);
	    	endIndex=index;
	    } */
	}); 
}

//新增
function add(){
	//跳转新增页面
	window.location.href="${ctx}/manage/sys/menu/toAdd";
}

//修改
function update(){
	var row = $('#menuTreegrid').datagrid('getSelected');/*获取选中的行*/
	if(row != null){
		var menuId=row.id;
		window.location.href="${ctx}/manage/sys/menu/toEdit?menuId="+menuId;
	}else{
		alert("请选择要修改的数据");
	}
}
//删除
function del(){
	var row = $('#menuTreegrid').datagrid('getSelected');/*获取选中的行*/
	if(row != null){
		var flag=window.confirm("确认删除此菜单？");
		if(flag){
			var menuId=row.id;
			$.ajax({
		          type:'post',      
		          url:'${ctx}/manage/sys/menu/del',  
		          data:{menuId:menuId},
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

//删除节点
function removeNode(flag,id){
	$('#'+flag).treegrid('remove',id);
}

//开始编辑单元格
function beginEditCellSelect(flag,field,index){
	$('#'+flag).datagrid('selectRow', index).datagrid('beginEditCell', {index:index,field:field});
}
//结束编辑单元格
function endEditCellSelect(flag,index){
	$('#'+flag).datagrid('endEdit', index);
}

//扩展开始编辑单元格属性
$.extend($.fn.datagrid.methods, {
	beginEditCell: function(jq,param){
		return jq.each(function(){
			var opts = $(this).datagrid('options');
			var fields = $(this).datagrid('getColumnFields',true).concat($(this).datagrid('getColumnFields'));
			for(var i=0; i<fields.length; i++){
				var col = $(this).datagrid('getColumnOption', fields[i]);
				col.editor1 = col.editor;
				if (fields[i] != param.field){
					col.editor = null;
				}
			}
			$(this).datagrid('beginEdit', param.index);
			for(var i=0; i<fields.length; i++){
				var col = $(this).datagrid('getColumnOption', fields[i]);
				col.editor = col.editor1;
			}
		});
	}
});
</script>
<body style="padding: 0px;">
	<div style="width:100%;height:400px;">
		<table id="menuTreegrid" style="width:100%;height:100%"></table>
	</div>
</body>
</html>