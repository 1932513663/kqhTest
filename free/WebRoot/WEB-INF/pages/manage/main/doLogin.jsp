<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="../common/script.jsp"%>
<%@ include file="../common/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script src="${ctx}/static/js/main/region.js"></script>
<title></title>
<head>
    <title>HR-登录</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <script type="text/javascript">
        // 如果在框架中，则跳转刷新上级页面
       if(self.frameElement && self.frameElement.tagName=="IFRAME"){
            parent.location.reload();
        }       
    </script>
</head>
<body class="fr">
<shiro:authenticated>
    <script language="javascript" type="text/javascript">
        window.location.href="${ctx}/pages/main/home-page.jsp";
    </script>
</shiro:authenticated>
<shiro:notAuthenticated>
	<script language="javascript" type="text/javascript">
      window.location.href="${ctx}/pages/main/logins.jsp";
    </script>	
</shiro:notAuthenticated>

</body>
</html>


