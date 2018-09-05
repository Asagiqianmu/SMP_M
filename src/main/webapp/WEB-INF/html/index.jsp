<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" +request.getServerPort() + request.getContextPath() + "/";
%>
<html class=" js csstransforms3d">
<head>
<base href="<%=basePath%>">
<meta charset="utf-8">
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>物业后台管理系统</title>
<link rel="stylesheet" type="text/css" href="static/css/base.css">
<link rel="stylesheet" type="text/css" href="static/css/page.css">
<script type="text/javascript" src="static/js/jquery.min.js"></script>
<script type="text/javascript" src="static/js/modernizr.js"></script>
<script type="text/javascript" src="static/js/main_min.js"></script>
</head>
<body>
	<div class="superWrap clearfix">
		<!--side S-->
		<div class="super-side-menu">
			<jsp:include page="public_left.jsp" flush="true"></jsp:include>
		</div>
		<!--content S-->
			<div class="superContent">
		    <div class="super-header" style="height: 86px;">
			<jsp:include page="public_header.jsp" flush="true" ></jsp:include>	
			</div>
			<div id="mainContent" class="superCtabBot" style="height: 695px;" >
				<jsp:include page="${page}" flush="true"></jsp:include>
			</div>
			<!--main-->
			</div>
		<!--content E-->
	</div>
	<script type="text/javascript">
	var index = "${index}";
	</script>
</body>
</html>