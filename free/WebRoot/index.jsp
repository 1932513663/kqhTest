<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<script type="text/javascript" src="<%=basePath%>static/js/main/jquery-1.10.2.min.js"></script>
<title></title>
<script>
function text(){
	alert(0);
	var url='<%=basePath%>manage/member/findAll';
	$.ajax({
		type : 'post',
		url : url,
		dataType : 'json',
		success : function(data) {
			alert(data);
		}
	});
}
</script>
</head>

<body>
<button onclick="text();">text</button>
</body>
</html>