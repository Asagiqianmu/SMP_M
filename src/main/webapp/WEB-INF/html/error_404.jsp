<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath() + "/";
%>
<!doctype html>
<html style="background: #fff;">
<head>
	<meta charset="utf-8">
	<title>404错误提示</title>
	<link rel="stylesheet" type="text/css" href="static/css/404style.css" />
</head>
<body>
	<div class="notice_page">
		<div class="notice_404_p"></div>
		<div class="home_page"><a class="notice_retun" href="javascript:history.go(-1);">返回上一页</a><a class="notice_home" href="/">返回首页</a></div>
	</div>

</body>
</html>
