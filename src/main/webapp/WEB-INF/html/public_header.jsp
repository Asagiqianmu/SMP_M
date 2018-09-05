<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" +request.getServerPort() + request.getContextPath() + "/";
%>
<!DOCTYPE html> 
<html class=" js csstransforms3d">
<head>
<base href="<%=basePath%>">
<meta charset="utf-8">
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>公共头部</title>
<link rel="stylesheet" type="text/css" href="static/css/base.css">
<link rel="stylesheet" type="text/css" href="static/css/page.css">
<script type="text/javascript" src="static/js/jquery.min.js"></script>
<script type="text/javascript" src="static/js/json2.js"></script>
<script type="text/javascript" src="static/js/main.js"></script>
<script type="text/javascript" src="static/js/modernizr.js"></script>
</head>

<body>
<div class="super-header clearfix">
	<h2>物业管理后台系统</h2>
	<div class="head-right">
		<i class="ico-user"></i>当前用户:${username}
		<div class="userslideDown">
			<a class="superUser" id="uname_a"><i class="ico-tri"></i></a>
			<div class="slidedownBox">
				<ul>
					<li><a href="index?page=12&token=${token }">账户设置</a></li>
					<!-- <li><a href="change_psw.jsp">账户资产</a></li> -->
					<li><a href="outLogin?token=${token }">退出账户</a></li>
				</ul>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
var token = '${token}'; 
var uname = '${userName}';
$("#uname_a").text(uname);
</script>
</body></html>