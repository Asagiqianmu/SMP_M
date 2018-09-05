<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath() + "/";
%>
<!DOCTYPE html>
<html class=" js csstransforms3d">
<head>
<base href="<%=basePath%>">
<meta charset="utf-8">
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>账户设置</title>
<link rel="stylesheet" type="text/css" href="static/css/base.css">
<link rel="stylesheet" type="text/css" href="static/css/page.css">
<link rel="stylesheet" type="text/css" href="static/css/login.css" />
<link rel="stylesheet" type="text/css" href="static/css/toast.css">
<link rel="stylesheet" type="text/css" href="static/js/bootstrap_3.3.0/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="static/js/bootstrap-table/src/bootstrap-table.css">
<link rel="stylesheet" type="text/css" href="static/js/bs_pagination/css/jquery.bs_pagination.min.css">
<script type="text/javascript" src="static/js/jquery.min.js"></script>
<script type="text/javascript" src="static/js/main.js"></script>
<script type="text/javascript" src="static/js/modernizr.js"></script>
<script type="text/javascript" src="static/js/bootstrap_3.3.0/js/bootstrap.min.js"></script>
<script type="text/javascript" src="static/js/bs_datetimepicker/js/bootstrap-datetimepicker.js"></script>
<script type="text/javascript" src="static/js/bs_datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js"></script>
<script type="text/javascript" src="static/js/bs_pagination/js/jquery.bs_pagination.min.js"></script>
<script type="text/javascript" src="static/js/bs_pagination/js/localization/en.js"></script>
<script type="text/javascript" src="static/js/bootstrap-table/src/bootstrap-table.js"></script>
<script type="text/javascript" src="static/js/bootstrap-table/src/locale/bootstrap-table-zh-CN.js"></script>
<script type="text/javascript" src="static/js/json2.js"></script>
<script type="text/javascript" src="static/js/main_min.js"></script>
</head>
<body style="background: #f6f5fa;">
	<!--content S-->
	<div class="super-content RightMain" id="RightMain">
		<div class="superCtab">
			<div class="ctab-title clearfix">
				<h3>账户设置</h3>
			</div>
			<!-- 导航栏 ↓-->
			<div class="ctab-Main">
				<div class="ctab-Main-title">
					<ul class="clearfix">
						<li id="onIndividualCenter" class="cur"><a>个人中心</a></li>
						<li id="onSetPass" ><a>修改密码</a></li>
					</ul>
				</div>
			</div>
			<!-- 导航栏 ↑-->
					<div class="Individual">
						<div class="IndividualCenter" >
						<div class="personal_head">基本信息</div>
						<div class="personal_Info">
<%-- 						<p>用户名:${userName }</p>
						<p>密码：••••••••</p>
						<p>公司名称：${user2.property.company_name }</p>
						<p>联系方式：${user2.property.telphone }</p>
						<p>公司地址：${user2.property.addr }</p>
						<p>E-mali邮箱:${user.email }</p> --%>
						</div>
						</div>
					</div>
					<!-- 个人中心 ↑-->
					<div class="publishArt">
						<div class="pubMain">
							<p class="pubtitle">新密码<span style="font-size:12px">（数字、字母、符号组合，长度不能超过18位）</span></p>
							<div class="pub-txt-bar">
								<input id="userPwd" type="text" class="shuruTxt2" maxlength="18" onkeyup="value=value.replace(/[\W]/g,'')"><b class="error"></b>
							</div>
							<p class="pubtitle">确认新密码</p>
							<div class="pub-txt-bar">
								<input id="confirmPwd" type="text" class="shuruTxt2" onkeyup="value=value.replace(/[\W]/g,'')"><b class="error"></b>
							</div>
							<div class="pub-btn">
								<input type="button" id="modifyPassWord" value="确定" class="saveBtn">
								<input type="button" id="restPassWord" value="重置" class="resetBtn">
							</div>
							<div class="errorBox1">请输入密码！密码长度必须大于8位.</div>
							<div class="errorBox2">请确认密码！</div>
							<div class="errorBox3">两次密码不一致！请重新输入。</div>
						</div>
				</div>
		</div>
	</div>	
		<div class="cd-popup" role="alert">
		<div class="cd-popup-container">
			<p>是否确认修改密码?</p>
			<ul class="cd-buttonse">
				<li><a id="Close">取消</a></li>
				<li><a id="Sure">确认</a></li>
			</ul>
		</div>
	<script type="text/javascript">
	var type = '${type}';
	var user2='${user2}';
	console.log(user2);
	if(type==2){
		var htmlStr = "";
		htmlStr+='<p>账号名:${user2.userName }</p>';
		htmlStr+='<p>密码：••••••••</p>';
		htmlStr+='<p>公司名称：${user2.property.company_name }</p>';
		htmlStr+='<p>联系方式：${user2.property.telphone }</p>';
		htmlStr+='<p>公司地址：${user2.property.addr }</p>';
		htmlStr+='<p>E-mali邮箱:${user2.email }</p>';
	
		$(".personal_Info").html(htmlStr);
	}else{
		var htmlStr = "";
		htmlStr+='<p>账号名:${user2.userName }</p>';
		htmlStr+='<p>密码：••••••••</p>';
		htmlStr+='<p>小区名称：${user2.pmd.management_department_name }</p>';
		htmlStr+='<p>联系方式：${user2.pmd.contact_number }</p>';
		htmlStr+='<p>小区地址：${user2.pmd.province }${user2.pmd.city }${user2.pmd.area }</p>';
		htmlStr+='<p>E-mali邮箱:${user2.email }</p>';
		$(".personal_Info").html(htmlStr);
	}
    $(function() {
        $('.ctab-Main-title li').click(function() {
            $(this).addClass('cur').siblings().removeClass('cur');
        });
        $('#onIndividualCenter').click(function() {
            if ($(".Individual").css("display") == 'none') {
                $(".Individual").css("display", "block");
                $(".publishArt").css("display", "none");
            }
        })

        $('#onSetPass').click(function() {
            $("#onSetPass").addClass('cur');
            $("#onIndividualCenter").removeClass('cur');
            if ($(".publishArt").css("display") == 'none') {
                $(".Individual").css("display", "none");
                $(".publishArt").css("display", "block");
            };
        });

        $("#modifyPassWord").click(function() {
        	var ctime = '${ctime}';
            var userPwd = $("#userPwd").val();
            var confirmPwd = $("#confirmPwd").val();
            if (userPwd == ''||userPwd.length<8) {
                $(".errorBox1").css("display", "block");
                $(".errorBox2,.errorBox3").css("display", "none");
                return false;
            } else if (confirmPwd == '') {
                $(".errorBox1,.errorBox3").css("display", "none");
                $(".errorBox2").css("display", "block");
                return false;
            } else if (userPwd != confirmPwd) {
                $(".errorBox1,.errorBox2").css("display", "none");
                $(".errorBox3").css("display", "block");
                return false;
            } else {
                $(".errorBox1,.errorBox2,.errorBox3").css("display", "none");
            }
            

            var JsonString = {
            		"userName":userName,
					"userPwd": userPwd,
					"confirmPwd": confirmPwd,
					"ctime": ctime
            };
            var json = JSON.stringify(JsonString);
            $.ajax({
                url: 'updatePassWord',
                data: json,
                type: 'post',
                dataType: 'json',
                contentType: 'application/json',
                success: function(data) {
                    if (data.code == "200") {
                    	$('.cd-popup').addClass('is-visible');
                    } else {
                    	toastr.error("密码修改失败!");
                    }
                }
            })
        });
        $("#restPassWord").click(function() {
            $("#userPwd").val('');
            $("#confirmPwd").val('');
        });
        $("#Close").click(function() {
            $('.cd-popup').removeClass('is-visible');
        });
        $("#Sure").click(function() {
            $('.cd-popup').removeClass('is-visible');
            window.location.href = "login";
        });
    });
</script> 
</body>
</html>