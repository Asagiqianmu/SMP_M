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
<title>忘记密码</title>
<link rel="stylesheet" type="text/css" href="static/css/base.css">
<link rel="stylesheet" type="text/css" href="static/css/login.css">
<script type="text/javascript" src="static/js/jquery.min.js"></script>
<script type="text/javascript" src="static/js/main_min.js"></script>
<script type="text/javascript" src="static/js/json2.js"></script>
</head>
<body>
<div class="superlogin"></div>
<div class="loginBox">
	<div class="resetpsw">密码重置</div>
	<div class="stepBar">
		<img src="static/images/login_step_2.png">
	</div>
	<div class="loginMain loginMain2">
	<div class="pointer"><span>验证码已发至邮箱:${email}</span></div>
		<div class="tabwrap fls">
		<table border="0" cellspacing="0" cellpadding="0">
			<tbody><tr>
			<td class="title1"></td><td><input maxlength="18"  id="userPwd" placeholder="输入密码" type="text" class="form-control txt" onkeyup="value=value.replace(/[\W]/g,'')" ></td>
			</tr>
			<tr>
			<td class="title2"></td><td><input id="confirmPwd" placeholder="确认密码" type="text" class="form-control txt" onkeyup="value=value.replace(/[\W]/g,'')" >
			</tr>
			<tr>
			<td class="title1"></td><td><input id="validateCode" placeholder="验证码" type="text" class="form-control txt"></td>
			</tr>
			<tr class="errortd"><td>&nbsp;</td><td>
			<i class="ico-error"></i>
			<span class="errore">请输入验证码！</span>
			<span class="errorword">请输入密码！密码长度必须大于8位.</span>
			<span class="erroremali">请再次输入密码！</span>
			<span class="errorpass">两次密码不一致！请重新输入。</span>
			</td></tr>		
			<tr><td>&nbsp;</td><td><a id="nextlogin"><input type="button" class="loginbtn" value="重置密码" ></a><a href="login"><input type="button" class="resetbtn" value="返回"></a></td></tr>		
		</tbody></table>
		</div>
	</div>
</div>
<div class="cd-popup1" role="alert">
        <div class="cd-popup-container">
            <p>密码重置成功!完成跳转至登陆页面.</p>
            <ul class="cd-buttons">
                <li><a id="Close1" class="close">完成</a></li>
            </ul>
        </div>
        </div>
<div class="cd-popup" role="alert">
        <div class="cd-popup-container">
            <p>验证码有误!</p>
            <ul class="cd-buttons">
                <li><a id="Close">确认</a></li>
            </ul>
        </div>
        </div>
<div class="footer">Copyright © 2018 讯飞无限  All Rights Reserved.</div>
<script>
    $(function() {
        var userName = '${userName}';
        $("#Close").click(function() {
            $('.cd-popup').removeClass('is-visible');
        });
        $("#Close1").click(function() {
            $('.cd-popup1').removeClass('is-visible');
            window.location.href = "login";
        });
        $("#nextlogin").click(function() {
            var ctime = '${ctime}';
            var userPwd = $("#userPwd").val();
            var confirmPwd = $("#confirmPwd").val();
            var validateCode = $("#validateCode").val();
            if (userPwd == '' || userPwd.length < 8) {
                $('.ico-error,.errorword').css("display", "block");
                $('.erroremali,.errorpass,.errore').css("display", "none");
                return false;
            } else if (confirmPwd == '') {
                $('.ico-error,.erroremali').css("display", "block");
                $('.errorword,.errorpass,.errore').css("display", "none");
                return false
            } else if (userPwd != confirmPwd) {
                $('.ico-error,.errorpass').css("display", "block");
                $('.erroremali,.errorword,.errore').css("display", "none");
                return false
            } else if (validateCode == '') {
                $('.ico-error,.errore').css("display", "block");
                $('.erroremali,.errorword,.errorpass').css("display", "none");
                return false
            } else {
                $('.ico-error,.errore,.erroremali,.errorword,.errorpass').css("display", "none");
            }

            var jsonString = {
                "userName": userName,
                "userPwd": userPwd,
                "confirmPwd": confirmPwd,
                "validateCode": validateCode,
                "ctime": ctime
            };
            var json = JSON.stringify(jsonString);

            $.ajax({
                url: 'modifyPassWord',
                data: json,
                contentType: 'application/json',
                type: 'post',
                dateType: 'json',
                success: function(data) {
                    if (data.code == "200") {
                        $('.cd-popup1').addClass('is-visible');
                    } else {
                        $('.cd-popup').addClass('is-visible');
                    }
                }
            })
        });

    })
</script>
</body></html>