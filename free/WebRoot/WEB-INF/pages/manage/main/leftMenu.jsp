<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="../common/script.jsp"%>
<%@ include file="../common/taglibs.jsp"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>leftMenu</title>
	<link href="${ctx}/static/manage/css/common.css" rel="stylesheet" type="text/css" />
	<link href="${ctx}/static/manage/css/wheat_leftNav.css" rel="stylesheet" type="text/css" />
</head>
<script type="text/javascript">
var subMenu='${subMenu}';
//首页
if(subMenu == 0){
	$('#ifrCont',window.top.document).attr('src','${ctx}/manage/sys/menu/index');
	window.parent.removePanel("west");
}

function switchTo(url){
	window.parent.switchTo(url); 
}

$(function(){
	$('#leftNav h2').click(function(){
        if($(this).hasClass('open')){
            return false;
        }
        $('#leftNav ul').slideUp(function(){$(this).css('display','none')});
        if(!$(this).attr('src')){
            $(this).next().slideDown(function(){$(this).next().css('display','block')});
        }else{
            $('#ifrCont',window.top.document).attr('src',$(this).attr('src'));
        }
        $('#leftNav h2').removeClass('open');
        $(this).addClass('open');
	});

    $('#leftNav ul a').on('click',function(){
        $('#leftNav ul a').removeClass('active');
        $(this).addClass('active');
    });

  	//页面加载时根据左侧导航加载右侧内容页面(初始化加载第一个菜单项)
    $('#ifrCont',window.top.document).attr('src',"${ctx}"+$('#leftNav').find('h2').find('a').eq(0).attr('id')); 
  	//选中第一个标识
    $('#leftNav').find('h2').eq(0).addClass('open');
});
</script> 
<body style="width:99%;height:99.6%;border:1px;border-style: solid;border-color:#95b8e7;">

<div id="leftNav" >
	<h1><span class="asset_mag">${menu.menuName }</span></h1>
	<c:forEach var="leftMenu" items="${leftMenuList}" varStatus="status">   
        <h2 >
        	<span>
        		<a id="${leftMenu.forward}" href="javascript:void(0);" onclick="switchTo('${leftMenu.forward}')"> ${leftMenu.menuName}</a>
        	</span>
        </h2> 
	</c:forEach>      
</div>
</body>
</html>
