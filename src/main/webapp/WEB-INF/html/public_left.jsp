<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%String basePath = request.getScheme() + "://" + request.getServerName() + ":" +request.getServerPort() + request.getContextPath() + "/";%>
<!DOCTYPE html>
<html class=" js csstransforms3d">
<head>
<base href="<%=basePath%>">
<meta charset="utf-8">
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta content="width=device-width,user-scalable=no" name="viewport"> 
<title>公共侧边栏</title>
<link rel="stylesheet" type="text/css" href="static/css/base.css">
<link rel="stylesheet" type="text/css" href="static/css/page.css">
<script type="text/javascript" src="static/js/jquery.min.js"></script>
<script type="text/javascript" src="static/js/json2.js"></script>
<script type="text/javascript" src="static/js/main.js"></script>
<script type="text/javascript" src="static/js/modernizr.js"></script>
<script type="text/javascript" src="static/js/left.js"></script>
<script type="text/javascript" src="static/js/main_min.js"></script>
</head>
<body> 
	<!--side S-->
	<div class="super-side-menu">
		<div class="logo"><a href="javascript:void(0)" target="_parent">
		<img class="logimg" src="static/images/into_logo.png"></a></div>
		<div class="side-menu">
		<ul>
			<li title="公告管理"><a href="index?page=1&token=${token }" ><i class="ico-1"></i>&nbsp;公告管理</a></li>
			<li title="业主管理"><a href="index?page=2&token=${token }" ><i class="ico-2"></i>&nbsp;业主管理</a></li>
			<li title="缴费管理"><a href="index?page=3&token=${token }" ><i class="ico-3"></i>&nbsp;缴费管理</a></li>
			<li title="开门记录"><a href="index?page=4&token=${token }" ><i class="ico-4"></i>&nbsp;开门记录</a></li>
			<li title="便民信息"><a href="index?page=5&token=${token }" ><i class="ico-5"></i>&nbsp;便民信息</a></li>
			<li title="居家维修"><a href="index?page=6&token=${token }" ><i class="ico-6"></i>&nbsp;居家维修</a></li>
			<li title="投诉管理"><a href="index?page=7&token=${token }" ><i class="ico-7"></i>&nbsp;投诉管理</a></li>
			<c:if test="${type!=2}">
			<li title="基价管理"><a href="index?page=8&token=${token }" ><i class="ico-8"></i>&nbsp;基价管理</a></li>
			</c:if>
			<li title="能耗设备"><a href="index?page=9&token=${token }" ><i class="ico-9"></i>&nbsp;能耗设备</a></li>
			
			<li title="房源信息"><a href="index?page=10&token=${token }" ><i class="ico-9"></i>&nbsp;房源信息</a></li>
			<c:if test="${type!=2}">
				<li title="物业公司"><a href="index?page=11&token=${token }" ><i class="ico-10"></i>&nbsp;物业公司</a></li>
			</c:if> 
			</ul>
		</div>
	</div>
</body>
</html>