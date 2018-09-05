<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath() + "/";
%>
<!DOCTYPE html>
<html><head>
<meta charset="UTF-8">
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no"/>
<title>重置密码</title>
<link rel="stylesheet" type="text/css" href="static/css/base.css">
<link rel="stylesheet" type="text/css" href="static/css/login.css">
<script type="text/javascript" src="static/js/jquery.min.js"></script>
<script type="text/javascript" src="static/js/json2.js"></script>
</head>
<body>
<div class="superlogin"></div>
<div class="loginBox">
	<div class="resetpsw">密码重置</div>
	<div class="stepBar">
		<img src="static/images/login_step_1.png">
	</div>
	<div class="loginMain loginMain2">
		<div class="tabwrap">
		<table border="0" cellspacing="0" cellpadding="0">
			<tbody><tr>
			<td class="title1"></td><td><input maxlength="18"  id="username" placeholder="用户名" type="text" class="form-control txt"></td>
			</tr>
			<tr class="errortd"><td>&nbsp;</td><td>
			<i class="ico-error"></i>
			<span class="errorword">请输入用户名！</span>
			</td></tr>		
			<tr><td>&nbsp;</td><td><a id="nextlogin"><input type="button" class="loginbtn" value="下一步" ></a><a href="login"><input type="button" class="resetbtn" value="返回"></a></td></tr>		
		</tbody></table>
		</div>
	</div>
</div>
<div class="footer">Copyright © 2018 讯飞无限  All Rights Reserved.</div>
<div class="cd-popup" role="alert">
	<div class="cd-popup-container">
		<p>用户名无效!</p>
		<ul class="cd-buttonss">
			<li><a class="close" id="Close">关闭</a></li>
		</ul>
	</div>
<script>
$("#nextlogin").click(function(){
	   var userName=$("#username").val();
	   
	   if(userName=='')
	   { 
		   $('.ico-error').css("display","block");
		   $('.errorword').css("display","block");
		   return false
	   }else 
		   {		   
		   $('.ico-error').css("display","none");
		   $('.errorword').css("display","none");
		   }
	   $.ajax({
			url : 'sendMailCode',
			data : {
				"userName" : userName,
			},
			type : 'post',
			dataType : 'json',
			success : function(data) {
				if (data.code == "200") {					
					window.location.href="login_forgetb_fls?userName="+data.userName+"&ctime="+data.ctime;
				} else {
					$('.cd-popup').addClass('is-visible');
				}
			}
		});
});
$("#Close").click(function() {
	$('.cd-popup').removeClass('is-visible');
			});
$("body").keydown(function() {
             if (event.keyCode == "13") {//keyCode=13是回车键
                 $('#nextlogin').click();
             }
	   });			
</script>
</body>
</html>