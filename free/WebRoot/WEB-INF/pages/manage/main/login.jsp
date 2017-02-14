<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="../common/script.jsp"%>
<%@ include file="../common/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script src="${ctx}/static/js/main/region.js"></script>
<title></title>
<title>公共管理-登录</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" /> 
<link href="${ctx}/static/manage/css/common.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/static/manage/css/login.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
// 刷新验证码
function refreshCode() {
	$('#imageValidate').src = '${ctx}/Kaptcha.jpg?' + Math.random();
}

 // 如果在框架中，则跳转刷新上级页面
if(self.frameElement && self.frameElement.tagName=="IFRAME"){
	parent.location.reload();
}
        
window.onload=function(){
	setTimeout(function(){
	   	$("#loginForm .delValue").each(function(){
			checkInputValue(this);
	    });
    	setupCheck();
    },200);
    	   
	// 绑定输入事件
	$("#loginForm .delValue").each(function(){
		$(this).bind('input propertychange', function() {
			checkInputValue(this);
		});
	});
};
        
        
// 循环检测输入值
function setupCheck(){
	setInterval(function(){
	   	$("#loginForm .delValue").each(function(){
	   		checkInputValue(this);
	   	});		
	},100); 	
}
        
// 检测当前输入值
function checkInputValue(currObj){
	var currValue=$(currObj).val();
	if(currValue == ''){
		$(currObj).parent().addClass("showPlaceholder");
	}else{
		$(currObj).parent().removeClass("showPlaceholder");	
	}	
}
</script>
</head>

<body id="wheat">
<!-- <shiro:authenticated>
    <script language="javascript" type="text/javascript">
        window.location.href="${ctx}/manage/member/index";
    </script>
</shiro:authenticated>  -->

<form id="loginForm"  action="${ctx}/shiro/login" method="post">
<div class="login_box">
        <h2 class="login_tit">用户登录</h2>
        <ul class="login_list">    
			<li><span>用户名：</span><input type="text"  name="username" id="username"    placeholder="请输入用户名" /></li>
			<li><span>密码：</span><input type="password" name="password"   id="pwdInput" placeholder="请输入您的密码" /></li>
            <%-- <li><span>验证码：</span><input type="text" name="validateCode" id="validateCode" placeholder="请输入验证码" /><img style="height:24px;width:60px;display:inline-block; vertical-align:middle;cursor:pointer;" src="${ctx}/Kaptcha.jpg" border="0" title="看不清,请点击图片换一张" id="imageValidate" onclick="refreshCode()" TABINDEX="4"></li> --%>
			<li><button id="login_btn">登录</button></li>
        </ul>
        
        <p class="forget_pasw"><%-- <%=errorFlag==null?"":"1".equals(errorFlag)?"验证码错误, 请重试.":"用户名或密码不正确，请重新输入." %> --%></p>
        <p class="forget_pasw"><a href="javascript:;">忘记登录密码？</a></p>     
      
    </div>
</form>

</body>
</html>


