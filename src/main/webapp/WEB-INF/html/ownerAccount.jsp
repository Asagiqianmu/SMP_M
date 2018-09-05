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
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<title>业主管理</title>
<link rel="stylesheet" type="text/css" href="static/css/base.css">
<link rel="stylesheet" type="text/css" href="static/css/page.css">
<link rel="stylesheet" type="text/css" href="static/css/toast.css">
<link rel="stylesheet" type="text/css" href="static/js/bootstrap_3.3.0/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="static/js/bs_pagination/css/jquery.bs_pagination.min.css">
<link rel="stylesheet" type="text/css" href="static/js/bootstrap-table/src/bootstrap-table.css">
<script type="text/javascript" src="static/js/jquery.min.js"></script>
<script type="text/javascript" src="static/js/json2.js"></script>
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
<script type="text/javascript" src="static/js/Ewin.js"></script>
<script type="text/javascript" src="static/js/PCASClass.js"></script>
<script type="text/javascript" src="static/js/bootbox.js"></script>
<script type="text/javascript" src="static/js/toastr.js"></script>
</head>

<body style="background: #f6f5fa;">
	<!--content S-->
	<div class="super-content" id="super-content">
		<div class="superCtab">
			<div class="ctab-title clearfix">
				<h3>业主管理</h3>
				<!--选择小区下拉框 -->
				<div class="select_dis"></div> 
			</div>
			<!-- 导航栏 ↓-->
			<div class="ctab-Main">
				<div class="ctab-Main-title">
					<ul class="clearfix">
						<li id="checkin" class="cur"><a>已入住</a></li>
						<li id="nocheck"><a>未入住</a></li>
					</ul>
				</div>
			</div>
			<!-- 导航栏 ↑-->
			<div class="ctab-Main">
				<div class="ctab-Mian-conts">
					<div class="Mian-cont-btn clearfix">
						<!-- 添加业主按钮 -->
						<div class="operateBtn"></div>
						<div class="searchBar">
							<input type="text" id="searchText" class="form-control srhTxt"
								placeholder="输入标题关键字搜索">
							<button id="searchBtn" type="button" class="srhBtn"></button>
						</div>
					</div>

					<!-- 添加业主管理模态框 -->
					<div class="modal fade" id="createOwnerAccountModal" role="dialog">
						<div class="modal-dialog" role="document" >
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal"
										aria-label="Close">
										<span aria-hidden="true">&times;</span>
									</button>
									<h4 class="modal-title">添加信息<span></span></h4>
								</div>
								<div class="modal-body-title">
									<form class="form-horizontal" role="form">
										<div class="form-group">
											<label for="create-describe" class="control-label">省/市/区</label>
											<div class="form-control-3"
												style=" width: 60%;margin-bottom: 15px; white-space: nowrap;">
												<select class="select_area" id="addProvince" name="Province"></select>
												<select class="select_area" id="addCity" name="City"></select>
												<select class="select_area" id="addArea" name="Area"></select>
											</div>
											
											<label for="addDistrictname" class="control-label">小区名称(请输入或者选择小区)</label>
											<div style="width: 70%; padding: 0;">
												 <input style="width: 50%;float: left"  type="text"
													class="form-control" id="addDistrictname">
												<select class="form-control"  style="padding: 0; width: 50%"  id="addressselection">
													<option>暂无数据</option>
												</select>
											</div>
										</div>	
										<div class="form-group">
											<label for="addOwnerAccountName" class="control-label">业主姓名</label>
											<div style="width: 70%;">
												<input style="margin-bottom: 15px;" type="text"
													class="form-control"
													onkeyup="value=value.replace(/[\d]/g,'')"
													id="addOwnerAccountName" maxlength="8">
											</div>
											<label for="addOwnerAccountTelphone" class="control-label">业主电话</label>
											<div style="width: 70%;">
												<input type="text" class="form-control"
													onkeyup="value=value.replace(/.\D/,'')"
													id="addOwnerAccountTelphone" maxlength="11">
											</div>
										</div>
										<div class="form-group">
											<label for="addOwnerAccountUnits" class="control-label">住址</label>
											<div class="form-control-3"
												style="width: 50%; white-space: nowrap;">
												<input type="text" class="Address" id="ondong" maxlength="5"
													onkeyup="value=value.replace(/[\W]/g,'')">栋 <input
													type="text" class="Address" id="onzuo" maxlength="5"
													onkeyup="value=value.replace(/[\W]/g,'')">座 <input
													type="text" class="Address" id="onroom" maxlength="12"
													onkeyup="value=value.replace(/[\W]/g,'')">房
											</div>
										</div>
										<div class="form-group">
											<label for="addHousingtypes" class="control-label">类型</label>
											<div style="width: 70%;margin-bottom: 15px;">
												<select class="form-control" id="addHousingtypes" style="padding: 0;">
													<option>暂无数据</option>
												</select>
											</div>
											<label for="addCashPledge" class="control-label">押金</label>
											<div style="width: 70%;">
												<input  style="margin-bottom: 15px;" type="number"
													class="form-control"
													onkeyup="value=value.replace(/[^\d^\.]+/g,'')"
													id="addCashPledge">
											</div>
											<label for="addRent" class="control-label">房租</label>
											<div style="width: 70%;">
												<input type="text" class="form-control"
													onkeyup="value=value.replace(/[^\d^\.]+/g,'')"
													id="addRent" >
											</div>
										</div>
									</form>

								</div>
								<div class="modal-footer">
									<button type="button" class="btn btn-default"
										data-dismiss="modal">关闭</button>
									<button id="saveCreateOwnerAccountBtn" type="button"
										class="btn btn-primary">保存</button>
								</div>
							</div>
						</div>
					</div>

					<!-- 编辑业主管理模态框 -->
					<div class="modal fade" id="editOwnerAccountModal" role="dialog">
						<div class="modal-dialog" role="document" >
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal"
										aria-label="Close">
										<span aria-hidden="true">&times;</span>
									</button>
									<h4 class="modal-title">修改信息<span></span></h4>
								</div>
								<div class="modal-body-title">
									<form class="form-horizontal" role="form">
										<input id="update" type="hidden">
										<input id="editpmid" type="hidden">
										<div class="form-group">
											<label for="create-describe" class="control-label">省/市/区</label>
											<div class="form-control-3"
												style=" width: 60%;margin-bottom: 15px; white-space: nowrap;">
												<select class="select_area" id="editProvince" name="Provincer"></select>
												<select class="select_area" id="editCity" name="Cityr"></select>
												<select class="select_area" id="editArea" name="Arear"></select>
											</div>
											
											<label for="editDistrictname" class="control-label">小区名称(请输入或者选择小区)</label>
											<div style="width: 70%; padding: 0;">
												 <input style="width: 50%;float: left"  type="text"
													class="form-control" id="edittDistrictname">
												<select class="form-control" id="editressselection" style="padding: 0; width: 50%">
													<option>暂无数据</option>
												</select>
											</div>
										</div>	
										<div class="form-group">
											<label for="editOwnerAccountName" class="control-label">业主姓名</label>
											<div style="width: 70%;">
												<input style="margin-bottom: 15px;" type="text" 
													maxlength="8" class="form-control"
													onkeyup="value=value.replace(/[\d]/g,'')"
													id="editOwnerAccountName">
											</div>
											<label for="editOwnerAccountTelphone" class="control-label">联系电话</label>
											<div style="width: 70%;">
												<input type="text" class="form-control"
													onkeyup="value=value.replace(/\D/,'')"
													id="editOwnerAccountTelphone" maxlength="11">
											</div>
										</div>

										<div class="form-group">
											<label for="editOwnerAccountUnits" class="control-label">住址</label>
											<div style="width: 70%;">
												<div class="form-control-3"
													style="width: 70%; white-space: nowrap;">
													<input type="text" class="Address" id="editdong" maxlength="5"
														onkeyup="value=value.replace(/[\W]/g,'')">栋 <input
														type="text" class="Address" id="editzuo" maxlength="5"
														onkeyup="value=value.replace(/[\W]/g,'')">座 <input
														type="text" class="Address" id="editroom" maxlength="12"
														onkeyup="value=value.replace(/[\W]/g,'')">房
												</div>
											</div>
										</div>
										<div class="form-group">
											<label for="editHousingtypes" class="control-label">类型</label>
											<div style="width: 70%;margin-bottom: 15px;">
												<select class="form-control" id="editHousingtypes" style="padding: 0;">
													<option>暂无数据</option>
												</select>
											</div>
											<label for="editCashPledge" class="control-label">押金</label>
											<div style="width: 70%;">
												<input  style="margin-bottom: 15px;" type="number"
													class="form-control"
													onkeyup="value=value.replace(/[^\d^\.]+/g,'')"
													id="editCashPledge">
											</div>
											<label for="editRent" class="control-label">房租</label>
											<div style="width: 70%;">
												<input type="text" class="form-control"
													onkeyup="value=value.replace(/[^\d^\.]+/g,'')"
													id="editRent" >
											</div>
										</div>
									</form>

								</div>
								<div class="modal-footer">
									<button type="button" class="btn btn-default"
										data-dismiss="modal">关闭</button>
									<button id="saveEditOwnerAccountBtn" type="button"
										class="btn btn-primary">保存</button>
								</div>
							</div>
						</div>
					</div>
					<!-- 		查看详细信息			 -->
					<div class="modal fade" id="Seedetails" role="dialog">
						<div class="modal-dialog" role="document" >
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal"
										aria-label="Close">
										<span aria-hidden="true">&times;</span>
									</button>
									<h4 class="modal-title">详细信息<span></span></h4>
								</div>
								<div class="modal-body-title">
									<form class="form-horizontal" role="form">
										<div class="form-group">
											<label for="seeOwnerAccountName" class="control-label">业主姓名</label>
											<div style="width: 70%;">
												<span style="margin-bottom: 15px;" class="form-control" id="seeOwnerAccountName"></span>
											</div>
											<label for="seeOwnerAccountTelphone" class="control-label">联系电话</label>
											<div style="width: 70%;">
												<span class="form-control" id="seeOwnerAccountTelphone"></span>
											</div>
										</div>

										<div class="form-group">
											<label for="seeOwnerAccountUnits" class="control-label">住址</label>
											<div style="width: 70%;">
												<div class="form-control-3" style="width: 70%;margin-top: 5px; white-space: nowrap;">
													<span type="text" class="Address" id="seedong"></span>
													<span type="text" class="Address" id="seezuo"></span>
													<span type="text" class="Address" id="seeroom"></span>
												</div>
											</div>
										</div>
										<div class="form-group">
											<label for="seeHousingtypes" class="control-label">类型</label>
											<div style="width: 70%;margin-bottom: 15px;">
												<span style="margin-bottom: 15px;" class="form-control" id="seeHousingtypes"></span>
											</div>
											<label for="seeCashPledge" class="control-label">押金</label>
											<div style="width: 70%;">
												<span  style="margin-bottom: 15px;" class="form-control" id="seeCashPledge"></span>
											</div>
											<label for="seeRent" class="control-label">房租</label>
											<div style="width: 70%;">
												<span class="form-control" id="seeRent"></span>
											</div>
										</div>
									</form>

								</div>
								<div class="modal-footer">
									<button type="button" class="btn btn-default"
										data-dismiss="modal">关闭</button>
								</div>
							</div>
						</div>
					</div>
<!--************************ 已入住****************************** -->
					<div class="Mian-cont-wrap-owner1">
						<div class="defaultTab-T">
							<table border="0" cellspacing="0" cellpadding="0"
								class="defaultTable space">
								<tbody>
									<tr>
										<th class="t_0_q_5">编号ID</th>
										<th class="t_1_q">省/市/区</th>
										<th class="t_1_q">小区名称</th>
										<th class="t_1_q">业主姓名</th>
										<th class="t_1_q_5">联系电话</th>
										<th class="t_1_q">详细信息</th>
										<th class="t_1_q">入住时间</th>
										<th class="t_1_q_5">智能设备</th>
										<th class="t_2_q_cz">操作</th>
									</tr>
								</tbody>
							</table>

						</div>
						<div class="default" style="height: 540px; overflow: scroll;">
							<table border="0" cellspacing="0" cellpadding="0"
								class="defaultTable defaultTable2" id="ownerAccountBody">
							</table>
						</div>
						<!--pages S-->
						<div id="pageNoDiv" class="pageSelect"></div>
						<!--pages E-->
					</div>
				</div>
			</div>
		</div>
		<!--main-->
	</div>
	<div class="super-info" id="super-info">
		<div class="superCtab">
			<div class="ctab-title ctob-info">
				<h3>设备详情</h3>
				<!--选择小区下拉框 -->
				<div class="select_dis"></div> 
			</div>
		</div>
		<div class="context">
			<!-- <div class="div-wrap">
				<div class="div-header" style="background-color: #38b1fa;">
					<div class="glyphicon glyphicon-tint"></div><span calss="header-span">水表</span>
				</div>
					<div class="div-content" id="water"> 
						<div >水表类型:</div>    
						<div>水表数值:</div>
						<div>设备状态:</div>
						<div>服务公司:</div>
					</div>
			</div>
			<div class="div-wrap">
				<div class="div-header" style="background-color: #6f899e;">
					<div class="glyphicon glyphicon-flash"></div><span calss="header-span">电表</span>
				</div>
					<div class="div-content" id="equipment">
						<div>电表类型:</div>     
						<div>电表数值:</div>
						<div>设备状态:</div>
						<div>服务公司:</div>
					</div>
			</div>
			<div class="div-wrap">
				<div class="div-header" style="background-color: #6f899e;">
					<div class="glyphicon glyphicon-lock"></div><span calss="header-span">门锁</span>
				</div>
					<div class="div-content" id="lock">
						<div>设备状态:</div>
						<div>门锁类型:</div>
						<div>设备型号:</div>
						<div>设备电量:</div>
						<div>服务公司:</div>
					</div>
			</div> -->
		</div>
	</div>
<script type="text/javascript">
	/* 页面权限设置 */
    var type = '${type}';
    var epmid = '';
    var Provincer = '';
    var Cityr = '';
    var Arear = '';
    new PCAS("Province","City","Area");
    new PCAS("Provincer","Cityr","Arear");
    
	if(type == 2){
		var htmlStr='';
		 htmlStr +='<select id="select_head" class="select_head select_head_display" onchange="propertyManagementSysNotice(this.value)"><option>全部小区</option><c:forEach var="item" items="${pmd }" varStatus="status"><option value="${item.id }">${item.management_department_name }</option></c:forEach></select>';
		 $(".select_dis").html(htmlStr);
	}
	if (type == 3 || type == 2) {
		var htmlStr='';
        $(".t_2_q_cz").css("display", "block");
         htmlStr +='<button id="createOwnerAccountBtn" type="button"class="btn btn-primary"><span class="glyphicon glyphicon-plus"></span>添加业主</button>';
        $(".operateBtn").html(htmlStr);
    }
    $('.ctab-Main-title li').click(function() {
        $(this).addClass('cur').siblings().removeClass('cur');
    });
    
    var token = '${token}';
    var isliving = 1; //默认为在居住状态
    var temp1 = 0;
    var pagenum1 = 1;
    /* 获取下拉框value */
    function propertyManagementSysNotice(val){
		var val;
		if(isliving==1){
			isliving = 1;
			display(1, 10);
			}
		else if(isliving==0){
			isliving = 0;
			displaycheckin(1,10);
		}
 		temp = 0;
 		pagenum = 1; 
    }
    /* 添加地址选择 */
    $("#addressselection").click(function(){
    	var x = $("#addressselection option:selected").focus().text();
    	$("#addDistrictname").val(x);
    })
    
    /* 编辑地址选择 */
    $("#editressselection").click(function(){
    	var x = $("#editressselection option:selected").blur().text();
    	$("#edittDistrictname").val(x);
    })
    
    
    /*添加用户   通过地区获取获取当前地区已录入小区名称 */
    
    var cProvince = "";
    var cCity = "";
    var cArea = "";
    $("#addProvince").blur(function(){
    	cProvince = $("#addProvince option:selected").text();
    });
    
    $("#addCity").blur(function(){
    	cCity = $("#addCity option:selected").text();
    });
    
    $("#addArea").blur(function(){
    	cArea = $("#addArea option:selected").text();
    	
    	if(cProvince!=" "&&cCity!=" "&&cArea!=" "){		
    		var jsonString = {
   			  	"province":cProvince ,
               	"city": cCity ,
               	"area": cArea		
    		};
    		var json = JSON.stringify(jsonString);
            $.ajax({
                url: "queryPMDName",
                data: json,
                dataType: "json",
                contentType: "application/json",
                type: "post",
                success: function(data) {
                	if(data.code ==200){
                		$("#addressselection").html("");
                    	$.each(data.pmds, function(index , obj){
                    		$("#addressselection").append('<option>'+obj.management_department_name+'</option>')
                    	})
                	}else{
                		$("#addressselection").html('<option>暂无数据</option>');
                	}
                }
                	
                })
    	}
    });

    /* 编辑用户   通过地区获取获取当前地区已录入小区名称 */
    
    var eProvince = "";
    var eCity = "";
    var eArea = "";
    $("#editProvince").blur(function(){
    	eProvince = $("#editProvince option:selected").text();
    });
    
    $("#editCity").blur(function(){
    	eCity = $("#editCity option:selected").text();
    });
    
    $("#editArea").blur(function(){
    	eArea = $("#editArea option:selected").text();
    	
    	
    	if(eProvince==""){
    		eProvince = $("#editProvince option:selected").text()
    	}else if(eCity==""){
    		eCity = $("#editCity option:selected").text()
    	}else if(cProvince!=" "&&cCity!=" "&&cArea!=" "){		
    		var jsonString = {
   			  	"province":eProvince ,
               	"city": eCity ,
               	"area": eArea		
    		};
    		var json = JSON.stringify(jsonString);
            $.ajax({
                url: "queryPMDName",
                data: json,
                dataType: "json",
                contentType: "application/json",
                type: "post",
                success: function(data) {
                	if(data.code ==200){
                		$("#editressselection").html("");
                    	$.each(data.pmds, function(index , obj){
                    		$("#editressselection").append('<option>'+obj.management_department_name+'</option>')
                    	})
                	}else{
                		$("#editressselection").html('<option>暂无数据</option>');
                	}
                }
                	
                })
    	}
    });

    
    /* 显示未入住业主数据 */
    function displaycheckin(pageIndex, pageSize) {
    	
        var searchValue = $("#searchText").val();
        var userName = "";
        var telphone = "";
        var pmid=$("#select_head").val();
        isliving = 0;
		if(pmid=="全部小区")
			{
				pmid="";
			} 
        
        if (/^[1][3,4,5,7,8][0-9]{9}$/.test(searchValue)) {
            telphone = searchValue;
        } else if (/^([\u4E00-\uFA29]|[\uE7C7-\uE7F3]|[a-zA-Z0-9])*$/.test(searchValue)) {
            userName = searchValue;
        }

        var jsonString = {
            "pageIndex": pageIndex,
            "pageSize": pageSize,
            "isliving": isliving,
            "userName": userName,
            "telphone": telphone,
            "token": token,
            "pmid":pmid
        };

		var status = isliving==1?"已入住":"未入住";
		
        var json = JSON.stringify(jsonString);
        $.ajax({
            url: "queryOwnerAccount",
            data: json,
            contentType: "application/json",
            dataType: "json",
            type: "post",
            success: function(data) {
            	if(data.code==200){
                var htmlStr = '';
                $.each(data.data.obj,
                function(index, obj) {
                    temp1 += 1;
                    htmlStr += '<tr>';
                    htmlStr += '<td class="t_0_q_5" value=' + obj.id + '>' + temp1 + '</td>';
                    htmlStr += '<td class="t_1_q">' + obj.province +''+ obj.city +''+ obj.area + '</a></td>';
                    htmlStr += '<td class="t_1_q">' + obj.managementDepartmentName + '</a></td>';
                    htmlStr += '<td class="t_1_q">' + obj.ownername + '</a></td>';
                    htmlStr += '<td class="t_1_q_5">' + obj.telphone + '</td>';
                    htmlStr += '<td class="t_1_q">' + '<div class="btn_gonggao"><button onclick="seeinformation(' + obj.id + ')" type="button" class="btubf btn-default">详细信息</button></td>';/*  htmlStr += '<td class="t_1_q">' + obj.units + obj.rooms + '房' + '</td>'; */
                    htmlStr += '<td class="t_1_q_5">' + obj.create_time + '</td>';
                   /*  if (type == 3) {
                        htmlStr += '<td class="t_2_q"><div class="btn_gonggao">' + '<button onclick="editOwnerAccount(' + obj.id + ')" type="button" class="btn btn-default"><span class="glyphicon glyphicon-pencil"></span> 修改</button>' + '<button onclick="deleteOwnerAccount(' + obj.id + ')" type="button" class="btn btn-danger"><span class="glyphicon glyphicon-minus"></span> 删除</button></div></td>';
                    } */
                    htmlStr += '</tr>';
                });
                $("#ownerAccountBody").html(htmlStr);

                //隔行换颜色
                $("#ownerAccountBody tr:odd").addClass("active");

                /* 显示翻页信息 */
                /* 总页数  */
                var totalPages = data.totalPageCount;

                $("#pageNoDiv").bs_pagination({
                    currentPage: pageIndex,
                    //当前页号
                    rowsPerPage: pageSize,
                    //每页显示条数
                    totalRows: data.record,
                    //总条数
                    totalPages: totalPages,
                    //总页数
                    visiblePageLinks: 5,
                    //最多显示的卡片数
                    showGoToPage: true,
                    //是否显示跳转到第几页
                    showRowsPerPage: true,
                    //是否显示每页显示条数
                    showRowsInfo: true,
                    //是否显示记录信息 */
                    //用来监听页号切换的事件，event就是代表这个事件，pageObj代表页信息
                    onChangePage: function(event, pageObj) { // returns page_num and rows_per_page after a link has clicked
                    temp1 = (pageObj.currentPage - 1) * (pageObj.rowsPerPage);
                        displaycheckin(pageObj.currentPage, pageObj.rowsPerPage);
                        pagenum1 = pageObj.currentPage;
                    }
                });
            }else{
            	toastr.error("暂无任何信息!");
            }
            }
        });
    };

    /* 编辑用户 */
    function editOwnerAccount(id) {
        $("#update").val(id);
        $.ajax({
            url: "editOwnerAccountInfo",
            data: {
                "id": id
            },
            dataType: "json",
            type: "post",
            success: function(data) {
            	epmid = data.data.pmid;
            	$("#edittDistrictname").val(data.data.managementDepartmentName);
                $("#editOwnerAccountName").val(data.data.ownername);
                $("#editOwnerAccountTelphone").val(data.data.telphone);
                $("#editdong").val(data.data.dong);
                $("#editzuo").val(data.data.zuo);
                $("#editroom").val(data.data.rooms);
                $("#editHousingtypes option[value='"+data.data.houseType+"']").attr("selected","selected"); 
                $("#editCashPledge").val(data.data.cashPledge);
                $("#editRent").val(data.data.rental);
                
                $("#editOwnerAccountModal").modal("show");
                $(".modal-title>span").html("");
                
                Provincer = data.data.province;
                Cityr = data.data.city;
                Arear = data.data.area;
                if($("[name='Provincer']").val() == Provincer||$("[name='Cityr']").val() == Cityr||$("[name='Arear']").val() == Arear)
            	{
            	
            	}else{
            		new PCAS("Provincer","Cityr","Arear",Provincer,Cityr,Arear);
            	};
            	
            	if(Provincer!=" "&&Cityr!=" "&&Arear!=" "){
            		var jsonString = {
           			  	"province":Provincer ,
                       	"city": Cityr ,
                       	"area": Arear		
            		};
            		var json = JSON.stringify(jsonString);
                    $.ajax({
                        url: "queryPMDName",
                        data: json,
                        dataType: "json",
                        contentType: "application/json",
                        type: "post",
                        success: function(data) {
                        	if(data.code ==200){
                        		$("#editressselection").html("");
                            	$.each(data.pmds, function(index , obj){
                            		$("#editressselection").append('<option>'+obj.management_department_name+'</option>')
                            	})
                        	}else{
                        		$("#editressselection").html('<option>暂无数据</option>');
                        	}
                        }
                        	
                        })
            	}
            }
        });
    };

    /* 删除用户 */
    function deleteOwnerAccount(id) {
        Ewin.confirm({
            message: "确认要删除业主信息吗？"
        }).on(function(e) {
            if (!e) {
                return;
            }
            $.ajax({
                url: "deleteOwnerAccountInfo",
                data: {
                    "id": id
                },
                dataType: "json",
                type: "post",
                success: function(data) {
                    if (data.code == "200") {
                        var rowsPerPage = $("#pageNoDiv").bs_pagination('getOption', 'rowsPerPage');
                        temp = (pagenum - 1) * rowsPerPage;
                        if(isliving==1){
                        		display(1, $("#pageNoDiv").bs_pagination('getOption', 'rowsPerPage'));
                        	}
                        else{
                        	displaycheckin(1, $("#pageNoDiv").bs_pagination('getOption', 'rowsPerPage'));
                        }
                    } else {
                        toastr.error("信息删除失败!");
                    }
                }
            });
        });
    }

    display(1, 10);
    var temp = 0;
    var pagenum = 1;
    /* 显示已入住业主数据 */
    function display(pageIndex, pageSize) {
        var searchValue = $("#searchText").val();
        var userName = "";
        var telphone = "";
        var pmid=$("#select_head").val();
        isliving = 1; 
		if(pmid=="全部小区")
		{
			pmid="";
		} 
        if (/^[1][3,4,5,7,8][0-9]{9}$/.test(searchValue)) {
            telphone = searchValue;
        } else if (/^([\u4E00-\uFA29]|[\uE7C7-\uE7F3]|[a-zA-Z0-9])*$/.test(searchValue)) {
            userName = searchValue;
        }

        var jsonString = {
            "pageIndex": pageIndex,
            "pageSize": pageSize,
            "isliving": isliving,
            "userName": userName,
            "telphone": telphone,
            "token": token,
            "pmid":pmid
        };
		var status = isliving==1?"已入住":"未入住";
        var json = JSON.stringify(jsonString);
        $.ajax({
            url: "queryOwnerAccount",
            data: json,
            contentType: "application/json",
            dataType: "json",
            type: "post",
            success: function(data) {
            	if(data.code==200){
                var htmlStr = '';
                var htmlselect = '';
                $.each(data.data.obj,function(index, obj) {
                    temp += 1;
                    htmlStr += '<tr>';
                    htmlStr += '<td class="t_0_q_5" value=' + obj.id + '>' + temp + '</td>';
                    htmlStr += '<td class="t_1_q">' + obj.province +''+ obj.city +''+ obj.area + '</a></td>';
                    htmlStr += '<td class="t_1_q">' + obj.managementDepartmentName + '</a></td>';
                    htmlStr += '<td class="t_1_q">' + obj.ownername + '</a></td>';
                    htmlStr += '<td class="t_1_q_5">' + obj.telphone + '</td>';
                    htmlStr += '<td class="t_1_q">' + '<div class="btn_gonggao"><button onclick="seeinformation(' + obj.id + ')" type="button" class="btubf btn-default">详细信息</button></td>';/* htmlStr += '<td class="t_1_q">' + obj.units + obj.rooms + '房' + '</td>'; */
                    htmlStr += '<td class="t_1_q">' + obj.create_time + '</td>';
                   
                    htmlStr += '<td class="t_1_q_5">' + '<div class="btn_gonggao"><button onclick="superinfo(' + obj.id + ')" type="button" class="btubf btn-default">查看</button></td>';
                    if (type == 3 || type == 2) {
                        htmlStr += '<td class="t_1_q_5"><div class="btn_gonggao">' + '<button onclick="editOwnerAccount(' + obj.id + ')" type="button" class="btubf btn-default"><span class="glyphicon glyphicon-pencil"></span> 修改</button>' + '<button onclick="deleteOwnerAccount(' + obj.id + ')" type="button" class="btubf btn-danger"><span class="glyphicon glyphicon-minus"></span> 删除</button></div></td>';
                    }
                    htmlStr += '</tr>';
                });
                $("#ownerAccountBody").html(htmlStr);
                $("#addHousingtypes").html('');
                $("#editHousingtypes").html('');
               	$.each(data.houseTypes,function(index,obj){
               		$("#addHousingtypes").append('<option value="'+obj.house_type+'">'+obj.house_name+'</option>');
               		$("#editHousingtypes").append('<option value="'+obj.house_type+'">'+obj.house_name+'</option>');
               	})
                

                //隔行换颜色
                $("#ownerAccountBody tr:odd").addClass("active");

                /* 显示翻页信息 */
                /* 总页数  */
                var totalPages = data.totalPageCount;

                $("#pageNoDiv").bs_pagination({
                    currentPage: pageIndex,
                    //当前页号
                    rowsPerPage: pageSize,
                    //每页显示条数
                    totalRows: data.record,
                    //总条数
                    totalPages: totalPages,
                    //总页数
                    visiblePageLinks: 5,
                    //最多显示的卡片数
                    showGoToPage: true,
                    //是否显示跳转到第几页
                    showRowsPerPage: true,
                    //是否显示每页显示条数
                    showRowsInfo: true,
                    //是否显示记录信息 */
                    //用来监听页号切换的事件，event就是代表这个事件，pageObj代表页信息
                    onChangePage: function(event, pageObj) { // returns page_num and rows_per_page after a link has clicked
                        temp = (pageObj.currentPage - 1) * (pageObj.rowsPerPage);
                        display(pageObj.currentPage, pageObj.rowsPerPage);
                        pagenum = pageObj.currentPage;
                    }
                });
            }else{
            	toastr.error("暂无任何数据!");
            }
            }
        });
    };
    /* 查看设备信息 */
 
	  function superinfo(id) {
		  if(type==3){
	        $.ajax({
	        	url:'queryDeviceInfo',
	        	data:{
	        		"id":id
	        	},
	        	dataType:'json',
	        	type:'post',
	        	success:function(data){
	        		var equipmentHtml="";
	        		var waterHtml="";
	        		var lockHtml="";
	        		if(data.code==200){
	        			$.each(data.data.energyConsumptionDevices,function(index,obj){
	        				if(obj.energy_type==1){
	        					equipmentHtml+='<div class="div-wrap">';
	        					equipmentHtml+='<div class="div-header"  style="background-color: #38b1fa;">';
	        					equipmentHtml+='<div class="glyphicon glyphicon-lock"></div><span calss="header-span">电表</span>';
	        					equipmentHtml+='</div>';
	        					equipmentHtml+='<div class="div-content" id="lock">';
	        					equipmentHtml+='<div>电表类型:'+obj.subTypeName+'</div>';
	        					equipmentHtml+='<div>电表数值:'+obj.meter_value+'</div>';
	        					equipmentHtml+='<div>设备状态:'+obj.strDeviceStstus+'</div>';
	        					equipmentHtml+='</div>';
	        					equipmentHtml+='</div>';
	        				}else if(obj.energy_type==0){
	        					equipmentHtml+='<div class="div-wrap">';
	        					equipmentHtml+='<div class="div-header" style="background-color: #6f899e;">';
	        					equipmentHtml+='<div class="glyphicon glyphicon-lock"></div><span calss="header-span" >水表</span>';
	        					equipmentHtml+='</div>';
	        					equipmentHtml+='<div class="div-content" id="lock">';
	        					equipmentHtml+='<div>水表类型:'+obj.subTypeName+'</div>';
	        					equipmentHtml+='<div>水表数值:'+obj.meter_value+'</div>';
	        					equipmentHtml+='<div>设备状态:'+obj.strDeviceStstus+'</div>';
	        					equipmentHtml+='</div>';
	        					equipmentHtml+='</div>';
	        				}
	        			});
	        			
	        			$.each(data.data.unlockingKeyByHouses,function(index,obj){
	        				equipmentHtml+='<div class="div-wrap">';
	    					equipmentHtml+='<div class="div-header" style="background-color: #38b1fa;">';
	    					equipmentHtml+='<div class="glyphicon glyphicon-lock"></div><span calss="header-span">门锁</span>';
	    					equipmentHtml+='</div>';
	    					equipmentHtml+='<div class="div-content" id="lock">';
	    					equipmentHtml+='<div>门锁类型:'+obj.lockInfo.strLockType+'</div>';
	    					equipmentHtml+='<div>设备状态:'+obj.lockInfo.strDeviceStatus+'</div>';
	    					equipmentHtml+='<div>设备型号:'+obj.lockInfo.deviceModel+'</div>';
	    					equipmentHtml+='<div>电量状态:'+obj.lockInfo.strPowerStatus+'</div>';
	    					equipmentHtml+='<div>服务公司:'+obj.equipmentSysProvider.company_name+'</div>';
	    					equipmentHtml+='</div>';
	    					equipmentHtml+='</div>';
	        			});
	        		}else{
	        			toastr.error("暂无任何数据!");
	        		}
	       			$(".context").html(equipmentHtml);
	        		$("#super-content").css('display','none');
	        		$("#super-info").css('display','block');
	        		
	        	}
	        });      
  }else{
	  toastr.error("暂无权限!");
  }
	  };
    
    /* 查看已入住详细信息按钮 */
    function seeinformation(id){
    	$("#Seedetails").modal("show");
        $("#update").val(id);
        $.ajax({
            url: "editOwnerAccountInfo",
            data: {
                "id": id
            },
            dataType: "json",
            type: "post",
            success: function(data) {
            	$("#seeOwnerAccountName").text(data.data.ownername);
            	$("#seeOwnerAccountTelphone").text(data.data.telphone);
            	$("#seedong").text(data.data.dong+"栋");
            	$("#seezuo").text(data.data.zuo+"座/单元");
            	$("#seeroom").text(data.data.rooms+"房");
            	$("#seeHousingtypes").text(data.data.houseName);
            	$("#seeCashPledge").text(data.data.cashPledge);
            	$("#seeRent").text(data.data.rental);
            	
            	}
            })
    }
    
    $(function() {
        /* 保存编辑 */
        $("#saveEditOwnerAccountBtn").click(function() {
        	var editOwnerAccountName = $("#editOwnerAccountName").val();
        	var editOwnerAccountTelphone = $("#editOwnerAccountTelphone").val();
        	var editProvince = $("#editProvince").val();
            var editCity = $("#editCity").val();
            var editArea = $("#editArea").val();
        	var editDistrictname = $("#edittDistrictname").val();
            var id = $("#update").val();
            var dong = $("#editdong").val();
            var zuo = $("#editzuo").val();
            var room = $("#editroom").val();
            var editHousingtypes = $("#addHousingtypes option:selected").val();
            var editCashPledge = $("#editCashPledge").val();
            var editRent = $("#editRent").val();
            
            if (editProvince == ''|| editCity==''||editArea=='') {
                $(".modal-title>span").html("必须选择省/市/区!");
                return false;
            } else if (editDistrictname == '') {
            	$(".modal-title>span").html("必须输入输入或者选择小区名称!");
                return false;
            } else if (editOwnerAccountName == '') {
            	$(".modal-title>span").html("必须输入业主姓名!");
                return false;
            } else if (editOwnerAccountTelphone == '') {
            	$(".modal-title>span").html("必须输入电话号码!");
                return false;
            } else if (dong == '' || zuo == '' || room == '') {
            	$(".modal-title>span").html("必须输入地址!");
                return false;
            } else if (editHousingtypes == '') {
            	$(".modal-title>span").html("必须选择出租类型!");
                return false;
            } else if (editCashPledge == '') {
            	$(".modal-title>span").html("必须输入押金金额!");
                return false;
            } else if (editRent == '') {
            	$(".modal-title>span").html("必须输入房租金额!");
                return false;
            }  else {
            	$(".modal-title>span").html("");
            }
            
            if (dong == '' || zuo == '' || room == '') {
                $(".modal-title>span").html("必须输入地址!");
                return false
            } else {
            	$(".modal-title>span").html("");
            }
            var JsonString = {
                "id": id,
                "dong": dong,
                "zuo": zuo,
                "room": room,
                "pmid": epmid,
                "province" :editProvince,
                "city":editCity,
                "area":editArea,
                "houseType":editHousingtypes,
                "pmdName":editDistrictname,
                "ownerName":editOwnerAccountName,
                "telphone" :editOwnerAccountTelphone,
                "houseType" :editHousingtypes,
                "cashPledge": editCashPledge,
                "rental":editRent
            };

            var json = JSON.stringify(JsonString);
            $.ajax({
                url: "saveEditOwnerInfo",
                data: json,
                contentType: "application/json",
                dataType: "json",
                type: "post",
                success: function(data) {
                    if (data.code == "200") {
                        var rowsPerPage = $("#pageNoDiv").bs_pagination('getOption', 'rowsPerPage');
                        temp = (pagenum - 1) * rowsPerPage;
                        if(isliving==1){
                    		display(1, $("#pageNoDiv").bs_pagination('getOption', 'rowsPerPage'));
                    	}
                   		else{
                        	displaycheckin(1, $("#pageNoDiv").bs_pagination('getOption', 'rowsPerPage'));
                  		}
                        $("#editOwnerAccountModal").modal("hide");
                    } else {
                    	toastr.error("数据更新保存失败!");
                        $("#editOwnerAccountModal").modal("show");
                    }
                }
            });
        });

        /* 添加业主 */
        $("#createOwnerAccountBtn").click(function() {
            $("#createOwnerAccountModal").modal("show");
            $(".modal-title>span").html("");
        });

        /* 保存添加业主信息 */
        $("#saveCreateOwnerAccountBtn").click(function() {
        	var addProvince = $("#addProvince").val();
            var addCity = $("#addCity").val();
            var addArea = $("#addArea").val();
        	var addDistrictname = $("#addDistrictname").val();
            var userName = $("#addOwnerAccountName").val();
            var telphone = $("#addOwnerAccountTelphone").val();
            var dong = $("#ondong").val();
            var zuo = $("#onzuo").val();
            var room = $("#onroom").val();
            var addHousingtypes = $("#addHousingtypes option:selected").val()
            var addCashPledge = $("#addCashPledge").val();
            var addRent = $("#addRent").val();
            
            if (addProvince == ''|| addCity==''||addArea=='') {
                $(".modal-title>span").html("必须选择省/市/区!");
                return false;
            } else if (addDistrictname == '') {
            	$(".modal-title>span").html("必须输入输入或者选择小区名称!");
                return false;
            } else if (userName == '') {
            	$(".modal-title>span").html("必须输入业主姓名!");
                return false;
            } else if (telphone == '') {
            	$(".modal-title>span").html("必须输入电话号码!");
                return false;
            } else if (dong == '' || zuo == '' || room == '') {
            	$(".modal-title>span").html("必须输入地址!");
                return false;
            } else if (addHousingtypes == '') {
            	$(".modal-title>span").html("必须选择出租类型!");
                return false;
            } else if (addCashPledge == '') {
            	$(".modal-title>span").html("必须输入押金金额!");
                return false;
            } else if (addRent == '') {
            	$(".modal-title>span").html("必须输入房租金额!");
                return false;
            }  else {
            	$(".modal-title>span").html("");
            }
            var JsonString = {
                "userName": userName,
                "telphone": telphone,
                "dong": dong,
                "zuo": zuo,
                "room": room,
                "token": token,
                "province":addProvince,
                "city":addCity,
                "area":addArea,
                "houseType":addHousingtypes,
                "districtName":addDistrictname,
                "cashPledge":addCashPledge,
                "rental":addRent
            };
            var json = JSON.stringify(JsonString);
            $.ajax({
                url: 'addOwnerAccount',
                data: json,
                contentType: 'application/json',
                dataType: 'json',
                type: 'post',
                success: function(data) {
                    if (data.code == "200") {
                        $("#addOwnerAccountName").val("");
                        $("#addOwnerAccountTelphone").val("");
                        $("#ondong").val("");
                        $("#onzuo").val("");
                        $("#onroom").val("");
                        /* 保存成功 */
                        var rowsPerPage = $("#pageNoDiv").bs_pagination('getOption', 'rowsPerPage');
                        temp = (pagenum - 1) * rowsPerPage;
                        if(isliving==1){
                    		display(1, $("#pageNoDiv").bs_pagination('getOption', 'rowsPerPage'));
                    	}
                   		else{
                        	displaycheckin(1, $("#pageNoDiv").bs_pagination('getOption', 'rowsPerPage'));
                  		}
                        $("#createOwnerAccountModal").modal("hide");
                    } else {
                    	toastr.error(data.msg);
                        $("#createOwnerAccountModal").modal("show");
                    }
                }
            });
        });
    });
    /* 搜索业主信息  */
    $("#searchBtn").click(function() {
    	if(isliving == 1){
    		temp = 0;
            pagenum = 1;
    		display(1, 10);
    	}else if(isliving == 0){
            temp1 = 0;
            pagenum1 = 1;
    		displaycheckin(1, 10);
    	}
    });
    $('#checkin').click(function() {
        var tbody = '<tbody>' + +'<tr>' + '	<th class="t_0_q_5">编号ID</th>' + '	<th class="t_1_q">省/市/区</th>' + '	<th class="t_1_q">小区名称</th>' + '	<th class="t_1_q">业主姓名</th>' + '	<th class="t_1_q_5">联系电话</th>' + '	<th class="t_1_q">详细信息</th>' + '<th class="t_1_q">入住时间</th>' + '<th class="t_1_q_5">智能设备</th>' + '<th class="t_2_q_cz">操作</th> '+'</tr>' + '</tbody>';
        
        $(".defaultTable").html(tbody);
        $("#ownerAccountBody").html('');
        $("#createOwnerAccountBtn").css("display","block")
        
            $(".t_2_q_cz").css("display", "block")
        
        temp = 0;
        pagenum = 1;
        isliving = 1;
        display(1, 10);
    });
    $('#nocheck').click(function() {

        var tbody = '<tbody>' + +'<tr>' + '	<th class="t_0_q_5">编号ID</th>' + '<th class="t_1_q">省/市/区</th>' + '	<th class="t_1_q">小区名称</th>' + '<th class="t_1_q">业主姓名</th>' + '	<th class="t_1_q_5">联系电话</th>' + '	<th class="t_1_q">详细信息</th>' + '	<th class="t_1_q_5">退房时间</th>' + '	<th class="t_2_q_cz">操作</th>' + '</tr>' + '</tbody>';
        $(".defaultTable").html(tbody);
        $("#ownerAccountBody").html('');
        $("#createOwnerAccountBtn").css("display","none")
        if (type == 0) {
            $(".t_2_q_cz").css("display", "block")
         }
        temp1 = 0;
        pagenum1 = 1;
        isliving = 0;
        displaycheckin(1, 10);
    })
</script>
</body>
</html>