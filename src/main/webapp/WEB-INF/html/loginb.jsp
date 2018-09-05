<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath() + "/";
%>
<!DOCTYPE html>
<html class=" js csstransforms3d">
<head>
<base href="<%=basePath%>">
<meta charset="UTF-8">
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no"/>
<title>用户登录</title>
<link rel="stylesheet" type="text/css" href="static/css/base.css" />
<link rel="stylesheet" type="text/css" href="static/css/login.css" />
<link rel="stylesheet" type="text/css" href="static/css/jquery.slider.css" />
<link rel="stylesheet" type="text/css" href="static/css/slide-unlock.css" />
<script type="text/javascript" src="static/js/jquery.min.js"></script>
<script type="text/javascript" src="static/js/json2.js"></script>
<script type="text/javascript" src="static/js/jquery.min.js"></script>
<script type="text/javascript" src="static/js/jquery.slider.min.js"></script>
<script type="text/javascript" src="static/js/jquery.slideunlock.js"></script>
<script type="text/javascript" src="static/js/jquery.cookie.js"></script>
</head>
<body>
	<div class="superlogin"></div>
	<div class="loginBox">
		<div class="logo">
			<img class="login" src="static/images/into_logo.png" />
		</div>
		<div class="loginMain">
			<div class="tabwrap">
				<table border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td><input id="userName" type="text" class="form-control txt"
							maxlength="11"
							placeholder="用户名:" /></td>
					</tr>
					<tr>
						<td><input id="passWord" maxlength="16" type="password"
							class="form-control txt" placeholder="密码:" /></td>
					</tr>
					<tr>
						<td colspan="1">
							<div id="slider">
						        <div id="slider_bg"></div>
						        <span id="label">>></span> <span id="labelTip">拖动滑块验证</span>
						    </div>
							<div id="slider1" class="slider"></div> 
						</td>
					</tr>
					<tr class="errortd">
						<td><i class="ico-error"></i> <span class="errorword">请输入用户名或密码！</span>
							<span class="resetword">验证未通过！</span></td>
					</tr>
					<tr>
						<td><input type="button" class="loginbtn" value="登录" id="login" />
						<input type="button" class="resetbtn" value="重置" id="reset" /></td>
					</tr>
					<tr>
						<td class="forgetpsw"><a href="login_forgetb">忘记密码？</a></td>
					</tr>
				</table>
			</div>
		</div>
	</div>
	<div class="footer">Copyright © 2018  讯飞无限 All Rights
		Reserved.</div>
	<div class="cd-popup" role="alert">
		<div class="cd-popup-container">
			<p>用户名或者密码错误！请重新输入。</p>
			<ul class="cd-buttonss">
				<li><a class="close" id="Close">关闭</a></li>
			</ul>
		</div>
	<script>
	var silde=false;
	$(function () {
		
	    var slider = new SliderUnlock("#slider",{
				successLabelTip : "验证成功",

			},function(){
				silde = true;
				/*alert("验证成功");*/
	        	/*window.location.href="http://www.baidu.com"*/
	        	
	    	});
	    $('#reset').click(function() {
	    	/* slider.reset();	 */     
	    });
	    slider.init();
	})
    $(function() {
        $("#userName").val($.cookie("uName"));
        $("#passWord").val($.cookie("pwd"));
    });
    
    var flag = false;
    $("#Close").click(function() {
        $('.cd-popup').removeClass('is-visible');
    });
    $("#slider1").slider({
        callback: function(result) {
            $("#result1").text(result);
        }
    });
    $('#reset').click(function() {
        $("#userName").val("");
        $("#passWord").val("");
        $("#slider1").slider("restore");
    });
    $("#login").click(function() {
        var userName = $("#userName").val();
        var passWord = $("#passWord").val();
        if (passWord == '' || userName == '') {
            $('.ico-error,.errorword').css("display", "block");
            $('.resetword').css("display", "none");
            return false
        } else if (!flag && !silde) {
            $('.ico-error,.resetword').css("display", "block");
            $('.errorword').css("display", "none");
            return false
        } else {
            $('.ico-error,.errorword,.resetword').css("display", "none");
        }
        $.ajax({
            url: 'doLogin',
            data: {
                "userName": userName,
                "passWord": passWord
            },
            type: 'post',
            dataType: 'json',
            success: function(data) {
                if (data.code == "200") {
                    window.location.href = "index?page=0&token=" + data.token;
                } else {
                    $('.cd-popup').addClass('is-visible');
                    $("#passWord").val("");
                    $("#slider1").slider("restore");
                }
            }
        });
    })

    $("body").keydown(function() {
        if (event.keyCode == "13") { //keyCode=13是回车键
            $('.loginbtn').click();
        }
    })
</script>
</body>

</html>
