<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="../common/script.jsp"%>
<%@ include file="../common/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script src="${ctx}/static/js/main/region.js"></script>
<title></title>
</head>
<script type="text/javascript">
	$(function() {
		/*初始化省市区*/
		var provinceId_ = "";
		var cityId_ = "";
		var countyId_ = "";
		initRegion(provinceId_, cityId_, countyId_);
	});
</script>
<body>
	<div class="regionLinkage "></div>
</body>
</html>