<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath() + "/";
	String urlath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/";
	request.getSession().setAttribute("urlath", urlath);
%>
<!DOCTYPE html>
<html class=" js csstransforms3d">
<head>
<base href="<%=basePath%>">
<meta charset="utf-8">
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>物业公司</title>
<link rel="stylesheet" type="text/css" href="static/css/base.css">
<link rel="stylesheet" type="text/css" href="static/css/page.css">
<link rel="stylesheet" type="text/css" href="static/css/toast.css">
<link rel="stylesheet" type="text/css" href="static/js/fileinput/fileinput.min.css">
<link rel="stylesheet" type="text/css" href="static/js/bootstrap_3.3.0/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="static/js/bs_pagination/css/jquery.bs_pagination.min.css">
<link rel="stylesheet" type="text/css" href="static/js/bootstrap-table/src/bootstrap-table.css">
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
<script type="text/javascript" src="static/js/fileinput/fileinput.min.js"></script>
<script type="text/javascript" src="static/js/fileinput/fileinput_locale_zh.js"></script>
<script type="text/javascript" src="static/js/json2.js"></script>
<script type="text/javascript" src="static/js/main_min.js"></script>
<script type="text/javascript" src="static/js/Ewin.js"></script>
<script type="text/javascript" src="static/js/toastr.js"></script>
<script type="text/javascript" src="static/js/PCASClass.js"></script>
<script type="text/javascript" src="static/js/bootbox.js"></script>
</head>
<body style="background: #f6f5fa;">
	<div class="super-content">
		<div class="superCtab">
			<div class="ctab-title clearfix">
				<h3>物业公司</h3>
			</div>
			<!-- 导航栏 ↓-->
			<div class="ctab-Main">
				<div class="ctab-Main-title">
					<ul class="clearfix">
						<li id="onProperty" class="cur"><a>物业公司</a></li>
						<li id="onProperty-admin"><a>小区物业</a></li>
						<li id="onHousingresource"><a>房源信息</a></li>
						<li id="onSmart-home"><a>智能家居</a></li>
						
					</ul>
				</div>
			</div>
			<!-- 导航栏 ↑-->
			<div class="Property-head">
				<!-- 物业公司↓ -->
				<div class="Property-com" id="onPropertycom">
					<div class="com-info">

						<span class="com-info-title1">
							<h3>公司信息</h3>
							<p id="p1"></p>
							<p id="p2"></p>
							<p id="p3"></p>
							<p id="p4"></p>
						</span>
						<div style="height: 50px;"></div>
						<span class="com-info-title2">
							<h3>小程序信息</h3>
							<p id="p5"></p>
							<p id="p6"></p>
							<p id="p7"></p>
							<p id="p8"></p>
						</span>
					</div>
					<!-- <div class="com-blank"></div>-->
					<div> 
						<img class="bg-img" src="static/images/bg_img.png"> 
						<img class="bg-head-img" id="bg-head-img" src="" >  <!-- src="static/images/head_img.png" -->
						<img class="bg-img-log"  src="static/images/bg_img_log.png">
						<img class="logo-img" id="logo-img" src=""><!-- src="static/images/log_yijia.png" -->
						<button type="button" class="bttn-primarimg" id="but-reset">重置</button>
						<button type="button" class="bttn-primarim" id="but-save">修改</button>
					</div>
					<div class="imgupload">

					<div>
						<label  class="control-label">图片信息<span>-(修改背景图片!推荐比例(750*514))</span></label>
							<form>
							    <div id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
							        <div class="modal-dialogfile" role="document">
						                <div>
						                    <input type="file" name="txt_file" id="txt_file" multiple class="file-loading" />
						                </div>
							        </div>
							    </div>
							</form>
					</div>
					 <div>
					 <label  class="control-label">图片信息<span>-(修改LOGO图片!推荐比例(454*121))</span></label>
							<form>
							    <div id="myModal1" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
							        <div class="modal-dialogfile" role="document">
						                <div>
						                    <input type="file" name="txt_file1" id="txt_file1" multiple class="file-loading" />
						                </div>
							        </div>
							    </div>
							</form>
					</div> 
					</div>
				<div>
				</div>
				</div>
				<!-- 物业公司↑ -->
				<!-- 物业管理小区↓ -->
				<div class="Property-admin" id="onPropertyadmin">
					<div class="ctab-Mian-cont">
						<div class="Mian-cont-btn clearfix">
							<div class="operateBtn">
								<button id="CommunityinformationBtn" type="button"
									class="btn btn-primary">
									<span class="glyphicon glyphicon-plus"></span>添加小区
								</button>
							</div>
						</div>
						<div class="Mian-cont-wrap">
							<div class="defaultTab-T">
								<table border="0" cellspacing="0" cellpadding="0"
									class="defaultTable">
									<tbody>
										<tr>
											<th class="t_1">编号ID</th>
											<th class="t_1">小区名称</th>
											<th class="t_1">联系电话</th>
											<th class="t_1">省份/城市/区域</th>
											<th class="t_1">操作</th>
										</tr>
									</tbody>
								</table>
							</div>
							<div class="default" style="height: 540px; overflow: scroll;">
								<table border="0" cellspacing="0" cellpadding="0"
									class="defaultTable defaultTable2" id="propertyCompanyBody">
								</table>
							</div>
							<div id="pageNoDiv"></div>
							<!--pages E-->
						</div>
					</div>
					<!-- 编辑小区信息模态框 -->
					<div class="modal fade" id="editPropertyCompanyModal" role="dialog">
						<div class="modal-dialog" role="document">
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal"
										aria-label="Close">
										<span aria-hidden="true">&times;</span>
									</button>
									<h4 class="modal-title">
										编辑信息<span></span>
									</h4>
								</div>
								<div class="modal-body-title">
									<form class="form-horizontal" role="form">
										<input id="update" type="hidden">
										<div class="form-group">
											<label for="editDistrictname" class="control-label">小区名称</label>
											<div class="" style="width: 70%;">
												<input style="margin-bottom: 15px;" type="text"
													class="form-control" id="editDistrictname">
											</div>
											<label for="editContactnumbere" class="control-label">联系电话</label>
											<div class="" style="width: 70%;">
												<input type="text" class="form-control" maxlength="11"
													onkeyup="value=value.replace(/\D/,'')"
													id="editContactnumber">
											</div>
										</div>
										<div class="form-group">
											<label for="create-describe" class="control-label">地址</label>
											<div class="form-control-3"
												style="width: 60%; white-space: nowrap;">
												<select class="select_area" id="editProvince" name="Province"></select>
												<select class="select_area" id="editCity" name="City"></select>
												<select class="select_area" id="editArea" name="Area"></select>
											</div>
										</div>
									</form>
								</div>
								<div class="modal-footer">
									<button type="button" class="btn btn-default"
										data-dismiss="modal">关闭</button>
									<button id="saveEditPropertyCompanyBtn" type="button"
										class="btn btn-primary">更新</button>
								</div>
							</div>
						</div>
					</div>

					<!-- 添加小区信息模态框 -->
					<div class="modal fade" id="CommunityinformationBtnModal"
						role="dialog">
						<div class="modal-dialog" role="document">
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal"
										aria-label="Close">
										<span aria-hidden="true">&times;</span>
									</button>
									<h4 class="modal-title">
										添加小区<span></span>
									</h4>
								</div>
								<div class="modal-body-title">
									<form class="form-horizontal" role="form">
										<div class="form-group">
											<label for="addDistrictname" class="control-label">小区名称</label>
											<div style="width: 70%;">
												<input style="margin-bottom: 15px;" type="text"
													class="form-control" id="addDistrictname">
											</div>
											<label for="addContactnumber" class="control-label">联系电话</label>
											<div style="width: 70%;">
												<input type="text" class="form-control" maxlength="11"
													onkeyup="value=value.replace(/\D/,'')"
													id="addContactnumber">
											</div>
										</div>
										<div class="form-group">
											<label for="addUserName" class="control-label">管理员名称</label>
											<div style="width: 70%;">
												<input style="margin-bottom: 15px;" type="text"
													class="form-control" id="addUserName">
											</div>
											<label for="addDistrictEmail" class="control-label">管理员邮箱</label>
											<div style="width: 70%;">
												<input style="margin-bottom: 15px;" type="text"
													class="form-control" id="addDistrictEmail">
											</div>
										</div>
										<div class="form-group">
											<label for="addDistrictPassWord" class="control-label">管理员密码</label>
											<div style="width: 70%;">
												<input style="margin-bottom: 15px;" type="text"
													class="form-control" id="addDistrictPassWord">
											</div>
											<label for="create-describe" class="control-label">地址</label>
											<div class="form-control-3"
												style="width: 60%; white-space: nowrap;">
													<select class="select_area" id="onProvince" name="PProvince"></select>
													<select class="select_area" id="onCity" name="PCity"></select>
													<select class="select_area" id="onArea" name="PArea"></select>
													</div>
										</div>
									</form>
								</div>
								<div class="modal-footer">
									<button type="button" class="btn btn-default"
										data-dismiss="modal">关闭</button>
									<button id="savePropertyCompanyBtn" type="button"
										class="btn btn-primary">保存</button>
								</div>
							</div>
						</div>
					</div>
				</div>
				<!-- 物业管理小区↑ -->
				
				<!-- 房源信息↓ -->
				<div class="Housingresource" id="on-Housingresource">
					<div class="ctab-Mian-cont">
						<div class="Mian-cont-btn clearfix">
							<div class="operateBtn">
								<button id="CommunityinformationBtn" type="button"
									class="btn btn-primary">
									<span class="glyphicon glyphicon-plus"></span>添加房源
								</button>
							</div>
						</div>
						<div class="Mian-cont-wrap">
							<div class="defaultTab-T">
								<table border="0" cellspacing="0" cellpadding="0"
									class="defaultTable">
									<tbody>
										<tr>
											<th class="t_1">编号ID</th>
											<th class="t_1">小区名称</th>
											<th class="t_1">联系电话</th>
											<th class="t_1">省份/城市/区域</th>
											<th class="t_1">操作</th>
										</tr>
									</tbody>
								</table>
							</div>
							<div class="default" style="height: 540px; overflow: scroll;">
								<table border="0" cellspacing="0" cellpadding="0"
									class="defaultTable defaultTable2" id="propertyCompanyBody">
								</table>
							</div>
							<div id="pageNoDiv"></div>
							<!--pages E-->
						</div>
					</div>
					<!-- 编辑小区信息模态框 -->
					<div class="modal fade" id="editHousingresourceModal" role="dialog">
						<div class="modal-dialog" role="document">
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal"
										aria-label="Close">
										<span aria-hidden="true">&times;</span>
									</button>
									<h4 class="modal-title">
										编辑信息<span></span>
									</h4>
								</div>
								<div class="modal-body-title">
									<form class="form-horizontal" role="form">
										<input id="update" type="hidden">
										<div class="form-group">
											<label for="editDistrictname" class="control-label">小区名称</label>
											<div class="" style="width: 70%;">
												<input style="margin-bottom: 15px;" type="text"
													class="form-control" id="editDistrictname">
											</div>
											<label for="editContactnumbere" class="control-label">联系电话</label>
											<div class="" style="width: 70%;">
												<input type="text" class="form-control" maxlength="11"
													onkeyup="value=value.replace(/\D/,'')"
													id="editContactnumber">
											</div>
										</div>
										<div class="form-group">
											<label for="create-describe" class="control-label">地址</label>
											<div class="form-control-3"
												style="width: 60%; white-space: nowrap;">
												<select class="select_area" id="editProvince" name="Province"></select>
												<select class="select_area" id="editCity" name="City"></select>
												<select class="select_area" id="editArea" name="Area"></select>
											</div>
										</div>
									</form>
								</div>
								<div class="modal-footer">
									<button type="button" class="btn btn-default"
										data-dismiss="modal">关闭</button>
									<button id="saveEditPropertyCompanyBtn" type="button"
										class="btn btn-primary">更新</button>
								</div>
							</div>
						</div>
					</div>

					<!-- 添加小区信息模态框 -->
					<div class="modal fade" id="HousingresourceBtnModal"
						role="dialog">
						<div class="modal-dialog" role="document">
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal"
										aria-label="Close">
										<span aria-hidden="true">&times;</span>
									</button>
									<h4 class="modal-title">
										添加小区<span></span>
									</h4>
								</div>
								<div class="modal-body-title">
									<form class="form-horizontal" role="form">
										<div class="form-group">
											<label for="addDistrictname" class="control-label">小区名称</label>
											<div style="width: 70%;">
												<input style="margin-bottom: 15px;" type="text"
													class="form-control" id="addDistrictname">
											</div>
											<label for="addContactnumber" class="control-label">联系电话</label>
											<div style="width: 70%;">
												<input type="text" class="form-control" maxlength="11"
													onkeyup="value=value.replace(/\D/,'')"
													id="addContactnumber">
											</div>
										</div>
										<div class="form-group">
											<label for="addUserName" class="control-label">管理员名称</label>
											<div style="width: 70%;">
												<input style="margin-bottom: 15px;" type="text"
													class="form-control" id="addUserName">
											</div>
											<label for="addDistrictEmail" class="control-label">管理员邮箱</label>
											<div style="width: 70%;">
												<input style="margin-bottom: 15px;" type="text"
													class="form-control" id="addDistrictEmail">
											</div>
										</div>
										<div class="form-group">
											<label for="addDistrictPassWord" class="control-label">管理员密码</label>
											<div style="width: 70%;">
												<input style="margin-bottom: 15px;" type="text"
													class="form-control" id="addDistrictPassWord">
											</div>
											<label for="create-describe" class="control-label">地址</label>
											<div class="form-control-3"
												style="width: 60%; white-space: nowrap;">
													<select class="select_area" id="onProvince" name="PProvince"></select>
													<select class="select_area" id="onCity" name="PCity"></select>
													<select class="select_area" id="onArea" name="PArea"></select>
													</div>
										</div>
									</form>
								</div>
								<div class="modal-footer">
									<button type="button" class="btn btn-default"
										data-dismiss="modal">关闭</button>
									<button id="saveHousingresourceBtn" type="button"
										class="btn btn-primary">保存</button>
								</div>
							</div>
						</div>
					</div>
				</div>
				<!-- 房源信息↑ -->
				
				
				<!-- 智能家居提供商↓ -->
				<div class="Smarthome" id="onSmarthome">
					<div class="ctab-Mian-cont">
						<div class="Mian-cont-btn clearfix">
							<div class="operateBtn">
								<button id="SmarthomeBtn" type="button" class="btn btn-primary">
									<span class="glyphicon glyphicon-plus"></span>添加服务商
								</button>
							</div>
						</div>
						<div class="Mian-cont-wrap">
							<div class="defaultTab-T">
								<table border="0" cellspacing="0" cellpadding="0"
									class="defaultTable">
									<tbody>
										<tr>
											<th class="t_1">编号ID</th>
											<th class="t_1">服务商</th>
											<th class="t_1">联系人</th>
											<th class="t_1">联系电话</th>
											<th class="t_1">服务类型</th>
											<th class="t_1">操作</th>
										</tr>
									</tbody>
								</table>
							</div>
							<div class="default" style="height: 540px; overflow: scroll;">
								<table border="0" cellspacing="0" cellpadding="0"
									class="defaultTable defaultTable2"
									id="EquipmentSysProviderBody">
								</table>
							</div>
							<div id="providerPageNoDiv"></div>
							<!--pages E-->
						</div>
					</div>
					<!-- 添加智能家居模态框 -->
					<div class="modal fade" id="insertEquipmentSysProviderModal"
						role="dialog">
						<div class="modal-dialog" role="document">
							<div class="modal-content" >
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal"
										aria-label="Close">
										<span aria-hidden="true">&times;</span>
									</button>
									<h4 class="modal-title">
										添加信息<span></span>
									</h4>
								</div>
								<div class="modal-body-title">
									<form class="form-horizontal" role="form">
										<div class="form-group">

											<label for="addDistrictname" class="control-label">服务商名称</label>
											<div style="width: 70%;">
												<input style="margin-bottom: 15px;" type="text"
													class="form-control" id="addEquipmentSysProviderCompany">
											</div>

											<label for="addContactnumbere" class="control-label">联系人信息</label>
											<div style="width: 70%;">
												<input type="text" class="form-control"
													id="addEquipmentSysProviderContacts">
											</div>
										</div>
										<div class="form-group">
											<label for="addEquipmentSysProviderTelphone"
												class="control-label">联系电话</label>
											<div class="" style="width: 70%;">
												<input style="margin-bottom: 15px;" type="text"
													class="form-control" id="addEquipmentSysProviderTelphone"
													onkeyup="value=value.replace(/\D/,'')" maxlength="11">
											</div>
											<label for="addEquipmentSysProviderServices"
												class="control-label">服务类型</label>
											<div class="" style="width: 70%;">
												<input type="text" class="form-control"
													id="addEquipmentSysProviderServices">
											</div>
										</div>

									</form>
								</div>
								<div class="modal-footer">
									<button type="button" class="btn btn-default"
										data-dismiss="modal">关闭</button>
									<button id="saveCreateEquipmentSysProviderBtn" type="button"
										class="btn btn-primary">保存</button>
								</div>
							</div>
						</div>
					</div>
					<!-- 编辑智能管理模态框 -->
					<div class="modal fade" id="editEquipmentSysProviderModal"
						role="dialog">
						<div class="modal-dialog" role="document">
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal"
										aria-label="Close">
										<span aria-hidden="true">&times;</span>
									</button>
									<h4 class="modal-title">
										编辑信息<span></span>
									</h4>
								</div>
								<div class="modal-body-title">
									<form class="form-horizontal" role="form">
										<input id="update" type="hidden">
										<div class="form-group">
											<label for="editEquipmentSysProviderCompany"
												class="control-label">服务提供商</label>
											<div class="" style="width: 70%;">
												<input style="margin-bottom: 15px;" type="text"
													class="form-control" id="editEquipmentSysProviderCompany">
											</div>
											<label for="editEquipmentSysProviderContacts"
												class="control-label">联系人</label>
											<div class="" style="width: 70%;">
												<input type="text" class="form-control"
													id="editEquipmentSysProviderContacts">
											</div>
										</div>
										<div class="form-group">
											<label for="editEquipmentSysProviderTelphone"
												class="control-label">联系电话</label>
											<div class="" style="width: 70%;">
												<input style="margin-bottom: 15px;" type="text"
													class="form-control" id="editEquipmentSysProviderTelphone"
													onkeyup="value=value.replace(/\D/,'')" maxlength="11">
											</div>

											<label for="editEquipmentSysProviderServices"
												class="control-label">服务类型</label>
											<div class="" style="width: 70%;">
												<input type="text" class="form-control"
													id="editEquipmentSysProviderServices">
											</div>
										</div>
									</form>
								</div>
								<div class="modal-footer">
									<button type="button" class="btn btn-default"
										data-dismiss="modal">关闭</button>
									<button id="saveEditEquipmentSysProviderBtn" type="button"
										class="btn btn-primary">更新</button>
								</div>
							</div>
						
					</div>
				</div>
				<!-- 智能家居提供商↑ -->
			</div>
		</div>
	</div>
	<script type="text/javascript">
	var type = '${type}';
	var urlath = "${urlath}";
    var token = '${token}';
    var temp1 = 0;
    var pagenum1 = 1;
    var temp = 0;
    var pagenum = 1;
    var Provincep = '';
    var Cityp = '';
    var Areap = '';
    new PCAS("PProvince","PCity","PArea");
    var logo_url ;
    var bg_url;
    var vsrc;
    var vsrc1;
    var companyID;
		//省、市、地区对象取得的值均为实际值。
		//注：省、市、地区提示信息选项的值为""(空字符串)
    displayCompanyInfo();
    /* 编辑服务商信息 */
    function editEquipmentSysProvider(id) {
        //var id = $("#editSysNotice").attr("name");
        $(".modal-title>span").html("");
        $.ajax({
            url: "editEquipmentSysProviderInfo",
            data: {
                "id": id
            },
            dataType: "json",
            type: "post",
            success: function(data) {
            	if(data.code==200){
            	$("#update").val(data.data.id);
                $("#editEquipmentSysProviderCompany").val(data.data.company_name);
                $("#editEquipmentSysProviderContacts").val(data.data.contacts);
                $("#editEquipmentSysProviderTelphone").val(data.data.telphone);
                $("#editEquipmentSysProviderServices").val(data.data.services);
                $("#editEquipmentSysProviderModal").modal("show");
            }else{
            	toastr.error("暂无任何信息!");
            }
        }
        });
    }
    
    $(function () {
        //0.初始化fileinput
        var oFileInput = new FileInput();
        oFileInput.Init("txt_file", "fileUpload");
        oFileInput.Init("txt_file1", "fileUpload");
        
    });
    //初始化fileinput 背景图上传
    var FileInput = function () {
        var oFile = new Object();
        //初始化fileinput控件（第一次初始化）
        oFile.Init = function(ctrlName, uploadUrl) {
        var control = $('#' + ctrlName);
        //初始化上传控件的样式
        control.fileinput({
            language: 'zh', //设置语言
            uploadUrl: uploadUrl, //上传的地址
            allowedFileExtensions: ['jpg', 'gif', 'png'],//接收的文件后缀
            showUpload: true, //是否显示上传按钮
            showCaption: false,//是否显示标题
            showPreview :true, //是否显示预览
            browseClass: "btn btn-primary", //按钮样式     
            //dropZoneEnabled: false,//是否显示拖拽区域
            minImageWidth: 454, //图片的最小宽度
            minImageHeight: 121,//图片的最小高度
            maxImageWidth: 750,//图片的最大宽度
            maxImageHeight: 514,//图片的最大高度
            //maxFileSize: 0,//单位为kb，如果为0表示不限制文件大小
            //minFileCount: 0,
            maxFileCount: 1, //表示允许同时上传的最大文件个数
            enctype: 'multipart/form-data',
            validateInitialCount:true,
            previewFileIcon: "<i class='glyphicon glyphicon-king'></i>",
            msgFilesTooMany: "选择上传的文件数量({n}) 超过允许的最大数值{m}！", 
            previewSettings: { image: {width: "100px", height: "100px"}, }
        });
        
        $("#txt_file").on("fileuploaded", function (event, data, previewId, index) { 	
            vsrc=data.response.img;
            $("#bg-head-img").attr("src", urlath+vsrc); 
            var data = data.response.lstOrderImport;
            if (data == undefined) {
                 /* toastr.error('文件格式类型不正确');  */
                return;
            }
            //1.初始化表格
            var oTable = new TableInit();
            oTable.Init(data);
            $("#div_startimport").show();
        });
        $("#txt_file1").on("fileuploaded", function (event, data, previewId, index) {
            vsrc1=data.response.img;
            $("#logo-img").attr("src", urlath+vsrc1); 
            
            var data = data.response.lstOrderImport;
            if (data == undefined) {
            	
                 /* toastr.error('文件格式类型不正确');  */
                return;
            }
            //1.初始化表格
            var oTable = new TableInit();
            oTable.Init(data);
            $("#div_startimport").show();
        });
    }
        return oFile;
    }; 
    /* 保存图片 */
     $("#but-save").click(function(){
   	bootbox.confirm("是否确认修改图片?", function(result)
   			{
   		if(result){
    	 var JsonString = {
    			 "bgUrl":vsrc,
    			 "logoUrl":vsrc1,
    			 "id":companyID
    	 };
    	 var json = JSON.stringify(JsonString);
    	 $.ajax({
    		 url:"updatePropertyUrl",
    		 data:json,
    		 contentType: 'application/json',
    		 dataType: 'json',
             type: 'post',
             success: function(data) {
    	     if(data.code==200){
    	    	 toastr.success("图片修改成功!");
    	     }else{
    	    	 toastr.error("图片修改失败!");
    	     }
             }
    	 })
   		}
   	 })
   })
    /* 重置图片 */
    $("#but-reset").click(function(){
    	$("#bg-head-img").attr("src", urlath+bg_url); 
    	$("#logo-img").attr("src", urlath+logo_url); 
    })
    
    /* 删除服务商信息 */
    function deleteEquipmentSysProvider(id) {
        Ewin.confirm({
            message: "确认要删除服务商吗？"
        }).on(function(e) {
            if (!e) {
                return;
            }
            $.ajax({
                url: "deleteEquipmentSysProviderInfo",
                data: {
                    "id": id
                },
                dataType: "json",
                type: "post",
                success: function(data) {
                    if (data.code == "200") {
                        var rowsPerPage = $("#providerPageNoDiv").bs_pagination('getOption', 'rowsPerPage');
                        temp1 = (pagenum1 - 1) * rowsPerPage;
                        displayProviderInfo(pagenum1, $("#providerPageNoDiv").bs_pagination('getOption', 'rowsPerPage'));
                    } else {
                        toastr.error("信息删除失败");
                    }
                }
            });
        });
    }
    /* 弹出添加智能家居服务商模态框 */
    $("#SmarthomeBtn").click(function() {
    	$(".modal-title>span").html("");
        $("#insertEquipmentSysProviderModal").modal("show");
        
    });
    function displayProviderInfo(pageIndex, pageSize) {
        var jsonString = {
            "pageIndex": pageIndex,
            "pageSize": pageSize
        };
        /**
			1、引入json2.js文件，封装正规json格式数据，解决浏览无法封装json数据，默认以键值对的对象形式携带参数提交。
			2、带参数提交方式默认使用POST方式，无参数提交使用GET方式。
			3、contentType是提交数据格式类型
			4、dataType是接收返回数据格式类型
			5、以后统一使用json数据格式进行前后台数据交互
			 */
        var json = JSON.stringify(jsonString);
        $.ajax({
            url: "queryEquipmentSysProviderInfo",
            data: json,
            contentType: "application/json",
            dataType: "json",
            type: "POST",
            success: function(data) {
                if (data.code == "200") {
                    var htmlStr = "";
                    $.each(data.data.obj,
                    function(index, obj) {
                        temp1 += 1;
                        htmlStr += '<tr>';
                        htmlStr += '<td class="t_1" value=' + obj.id + '>' + temp1 + '</td>';
                        htmlStr += '<td class="t_1">' + obj.company_name + '</a></td>';
                        htmlStr += '<td class="t_1">' + obj.contacts + '</td>';
                        htmlStr += '<td class="t_1">' + obj.telphone + '</td>';
                        htmlStr += '<td class="t_1">' + obj.services + '</td>';
                        htmlStr += '<td class="t_1"><div class="btn_gonggao">' + '<button  onclick="editEquipmentSysProvider(' + obj.id + ')" type="button" class="btubf btn-default"><span class="glyphicon glyphicon-pencil"></span> 修改</button>' + '<button  onclick="deleteEquipmentSysProvider(' + obj.id + ')" type="button" class="btubf btn-danger"><span class="glyphicon glyphicon-minus"></span> 删除</button></div></td>';
                        htmlStr += '</tr>';
                    });

                    $("#EquipmentSysProviderBody").html(htmlStr);

                    //隔行换颜色
                    $("#EquipmentSysProviderBody tr:odd").addClass("active");

                    /* 显示翻页信息 */
                    /* 总页数  */
                    var totalPages = data.totalPageCount;

                    $("#providerPageNoDiv").bs_pagination({
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
                            displayProviderInfo(pageObj.currentPage, pageObj.rowsPerPage);
                            pagenum1 = pageObj.currentPage;
                        }
                    });
                }else{
                	toastr.error("暂无任何信息!");
                }
            }
        });
    }

    /* ------------------------------------------------------------------------------------------ */

    /* addDistrictname  addContactnumbere  addContactaddress savePropertyCompanyBtn  editPropertyCompanyModal*/
    /* 编辑小区物业信息列表 */
    function editPMDInfo(id) {
        $("#update").val(id);
        $.ajax({
            url: "editPMDInfo",
            data: {
                "id": id
            },
            dataType: "json",
            type: "post",
            success: function(data) {
            	if(data.code==200){
            	$(".modal-title>span").html("");
            	$("#update").val(data.data.id);
                $("#editDistrictname").val(data.data.management_department_name);
                $("#editContactnumber").val(data.data.contact_number);
                Provincep = data.data.province;
                Cityp = data.data.city;
                Areap = data.data.area;
                //alert($("[name='Province']").val());
                if($("[name='Province']").val() == Provincep||$("[name='City']").val() == Cityp||$("[name='Area']").val() == Areap)
                	{
                	
                	}else{
                		new PCAS("Province","City","Area",Provincep,Cityp,Areap);
                	}
            	}else {
              		toastr.error("信息更新保存失败!");
          	  }
			}
        });
       // alert(Areap);
        $("#editPropertyCompanyModal").modal("show");
        
    }

    /* 删除小区物业管理  */
    function deletePMDInfo(id) {

        Ewin.confirm({
            message: "确认要删除小区物业吗？"
        }).on(function(e) {
            if (!e) {
                return;
            }
            $.ajax({
                url: "deletePMD",
                data: {
                    "id": id
                },
                dataType: "json",
                type: "post",
                success: function(data) {
                    if (data.code == "200") {
                        var rowsPerPage = $("#pageNoDiv").bs_pagination('getOption', 'rowsPerPage');
                        temp = (pagenum - 1) * rowsPerPage;
                        displayPMD(1, $("#pageNoDiv").bs_pagination('getOption', 'rowsPerPage'));
                    } else {
                        toastr.error("信息删除失败!");
                    }
                }
            });
        });
    }

    /* 显示公司信息数据 */
    function displayCompanyInfo() {
        var token = "${token}";
        var jsonString = {
            "token": token
        };
        var json = JSON.stringify(jsonString);
        $.ajax({
            url: "queryPropertyInfo",
            data: json,
            contentType: "application/json",
            dataType: "json",
            type: "POST",
            success: function(data) {
       
                if (data.code == "200") {
                	bg_url = data.data.bg_url;
                	logo_url = data.data.logo_url;
                	vsrc=bg_url;
                	vsrc1=logo_url;
                	companyID=data.data.id;
                    /* 显示物业公司信息  */
                    $("#p1").html("公司名称:" + data.data.company_name);
                    $("#p2").html("公司地址:" + data.data.addr);
                    $("#p3").html("联系方式:" + data.data.telphone);
                    $("#p4").html("E-mail邮箱:" + data.email);
                    $("#p5").html("微信小程序ID:" + data.data.appid);
                    $("#p6").html("微信小程序App secret:" + data.data.app_secret);
                    $("#p7").html("微信小程序App key:" + data.data.app_key);
                    $("#p8").html("微信商户号:" + data.data.mch_id);
                	$("#bg-head-img").attr("src", urlath+vsrc); 
                	$("#logo-img").attr("src", urlath+vsrc1); 
                }else
                	{
                	toastr.error("暂无公司任何信息!");
                	}
            }
        });
    }

    /* 显示小区物业管理处信息列表数据 */
    function displayPMD(pageIndex, pageSize) {
        var token = "${token}";
        var jsonString = {
            "pageIndex": pageIndex,
            "pageSize": pageSize,
            "token": token
        };
        /**
			1、引入json2.js文件，封装正规json格式数据，解决浏览无法封装json数据，默认以键值对的对象形式携带参数提交。
			2、带参数提交方式默认使用POST方式，无参数提交使用GET方式。
			3、contentType是提交数据格式类型
			4、dataType是接收返回数据格式类型
			5、以后统一使用json数据格式进行前后台数据交互
			 */
        /* 小区物业管理 信息 */
        var json = JSON.stringify(jsonString);
        $.ajax({
            url: "queryPMDlist",
            data: json,
            contentType: "application/json",
            dataType: "json",
            type: "POST",
            success: function(data) {
                if (data.code == "200") {
                    var htmlStr = "";
                    $.each(data.data.obj,
                    function(index, obj) {
                        //alert(obj.contact_number);
                        temp += 1;
                        htmlStr += '<tr>';
                        htmlStr += '<td class="t_1" value=' + obj.id + '>' + temp + '</td>';
                        htmlStr += '<td class="t_1">' + obj.management_department_name + '</a></td>';
                        htmlStr += '<td class="t_1">' + obj.contact_number + '</td>';
                        htmlStr += '<td class="t_1">' + obj.province + obj.city + obj.area + '</td>';
                        htmlStr += '<td class="t_1"><div class="btn_gonggao">' + '<button  onclick="editPMDInfo(' + obj.id + ')" type="button" class="btubf btn-default"><span class="glyphicon glyphicon-pencil"></span> 修改</button>' + '<button  onclick="deletePMDInfo(' + obj.id + ')" type="button" class="btubf btn-danger"><span class="glyphicon glyphicon-minus"></span> 删除</button></div></td>';
                        htmlStr += '</tr>';
                    });

                    $("#propertyCompanyBody").html(htmlStr);

                    //隔行换颜色
                    $("#propertyCompanyBody tr:odd").addClass("active");
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
                            //alert(pageObj.currentPage);
                            //alert(pageObj.rowsPerPage);
                            temp = (pageObj.currentPage - 1) * (pageObj.rowsPerPage);
                            displayPMD(pageObj.currentPage, pageObj.rowsPerPage);
                            pagenum = pageObj.currentPage;
                        }
                    });
                }else{
                 	toastr.error("暂无任何信息!");
                }
            }
        });
    }

    $(function() {
        $('.ctab-Main-title li').click(function() {
            //alert("1");
            $(this).addClass('cur').siblings().removeClass('cur');
        })

        $('#onProperty').click(function() {
            if ($("#onPropertycom").css("display") == 'none') {

                $("#onPropertycom").css("display", "block");
                $("#onPropertyadmin").css("display", "none");
                $("#onSmarthome").css("display", "none");
                $("#on-Housingresource").css("display", "none");
                displayCompanyInfo();
            }
        })

        $('#onProperty-admin').click(function() {
            if ($("#onPropertyadmin").css("display") == 'none') {
                $("#onPropertycom").css("display", "none");
                $("#onPropertyadmin").css("display", "block");
                $("#onSmarthome").css("display", "none");
                $("#on-Housingresource").css("display", "none");
                /* div加载完后显示第一页数据*/
                temp = 0;
                pagenum = 1;
                displayPMD(1, 10);
            }
        })
        
        
        $('#onHousingresource').click(function() {
            if ($("#on-Housingresource").css("display") == 'none') {
                $("#onPropertycom").css("display", "none");
                $("#onPropertyadmin").css("display", "none");
                $("#onSmarthome").css("display", "none");
                $("#on-Housingresource").css("display", "block");
                /* div加载完后显示第一页数据*/
                temp = 0;
                pagenum = 1;
                displayPMD(1, 10);
            }
        })
        $('#onSmart-home').click(function() {
            if ($("#onSmarthome").css("display") == 'none') {
                $("#onPropertycom").css("display", "none");
                $("#onPropertyadmin").css("display", "none");
                $("#onSmarthome").css("display", "block");
                $("#on-Housingresource").css("display", "none");
                /* div加载完后显示第一页数据*/
                temp1 = 0;
                pagenum1 = 1;
                displayProviderInfo(1, 10);
            };
        });
        
        /* 保存编辑小区管理列表 */
        $("#saveEditPropertyCompanyBtn").click(function() {
            var id = $("#update").val();
            var editContactnumber = $("#editContactnumber").val();
            var editDistrictname = $("#editDistrictname").val();
            var editProvince = $("#editProvince").val();
            var editCity = $("#editCity").val();
            var editArea = $("#editArea").val();
            if (editDistrictname == '') {
            	$(".modal-title>span").html("必须输入小区名称!");
                return false;
            } else if (editContactnumber == '') {
            	$(".modal-title>span").html("必须输入联系电话!");
                return false;
            } else if (editProvince == ''||editCity == ''||editArea == '') {
            	$(".modal-title>span").html("必须输入地址!");
                return false;
            } else {
            	$(".modal-title>span").html("");
            }
            var jsonString = {
                "id": id,
                "telphone": editContactnumber,
                "department_name": editDistrictname,
                "province":editProvince,
                "city":editCity,
                "area":editArea,
            };
            var json = JSON.stringify(jsonString);
            $.ajax({
                url: "saveEditPMDInfo",
                data: json,
                contentType: 'application/json',
                dataType: "json",
                type: "post",
                success: function(data) {
                    if (data.code == "200") {
                        var rowsPerPage = $("#pageNoDiv").bs_pagination('getOption', 'rowsPerPage');
                        temp = (pagenum - 1) * rowsPerPage;
                        displayPMD(pagenum, $("#pageNoDiv").bs_pagination('getOption', 'rowsPerPage'));
                        $("#editPropertyCompanyModal").modal("hide");
                    } else {
                    	toastr.error("信息更新保存失败!");
                        $("#editPropertyCompanyModal").modal("show");
                    }
                }
            });
        });

        /* 添加小区管理弹出模态框 */
        $("#CommunityinformationBtn").click(function() {
        	$(".modal-title>span").html("");
            $("#CommunityinformationBtnModal").modal("show");
        });
        
        /* 保存添加小区物业管理处信息*/
        $("#savePropertyCompanyBtn").click(function() {
            var addDistrictname = $("#addDistrictname").val();
            var addContactnumbere = $("#addContactnumber").val();
            var addContactaddress = $("#addContactaddress").val();
            var addProvince = $("#onProvince").val();
            var addDistrictEmail=$("#addDistrictEmail").val();
            var addUserName=$("#addUserName").val();
            var addCity = $("#onCity").val();
            var addArea = $("#onArea").val();
            var passWord=$("#addDistrictPassWord").val();

            if (addDistrictname == '') {
            	$(".modal-title>span").html("必须输入小区名称!");
                return false;
            } else if (addContactnumbere == '') {
            	$(".modal-title>span").html("必须输入联系电话!");
                return false;
            } else if (addUserName == '') {
            	$(".modal-title>span").html("必须输入管理员名称!");
                return false;
            } else if (addDistrictEmail == '') {
            	$(".modal-title>span").html("必须输入管理员邮箱!");
                return false;
            } else if (passWord == '') {
            	$(".modal-title>span").html("必须输入管理员密码!");
                return false;
            } else if (addProvince == ''||addCity == ''||addArea == '') {
            	$(".modal-title>span").html("必须输入地址!");
                return false;
            } else {
            	$(".modal-title>span").html("");
            }
            var jsonString = {
        		"department_name": addDistrictname,
                "telphone": addContactnumbere,
                "province": addProvince,
                "city": addCity,
                "area": addArea,
                "token": token,
                "email":addDistrictEmail,
                "userName":addUserName,
                "passWord":passWord
            };
            var json = JSON.stringify(jsonString);
            $.ajax({
                url: "addPMD",
                data: json,
                contentType: "application/json",
                dataType: "json",
                type: "post",
                success: function(data) {
                    if (data.code == "200") {
                        $("#addDistrictname").val("");
                        $("#addContactnumbere").val("");
                        $("#addProvince").val("");
                        $("#addCity").val("");
                        $("#addArea").val("");
                        $("#email").val("");
                        $("#userName").val();
                        $("#passWord").val();
                        var rowsPerPage = $("#pageNoDiv").bs_pagination('getOption', 'rowsPerPage');
                        temp = (pagenum - 1) * rowsPerPage;
                        displayPMD(pagenum, $("#pageNoDiv").bs_pagination('getOption', 'rowsPerPage'));
                        $("#CommunityinformationBtnModal").modal("hide");
                    } else {
                    	toastr.error("信息保存失败!");
                        $("#CommunityinformationBtnModal").modal("show");
                    }
                }
            });
        });

        $("#searchBtn").click(function() {
            var rowsPerPage = $("#pageNoDiv").bs_pagination('getOption', 'rowsPerPage');
            temp = (pagenum - 1) * rowsPerPage;
            displayPMD(1, $("#pageNoDiv").bs_pagination('getOption', 'rowsPerPage'));
        });

        /* -----------------------------服务商 */
        /* 修改服务商信息 */
        $("#saveEditEquipmentSysProviderBtn").click(function() {
        	var id = $("#update").val();
            var company_name = $("#editEquipmentSysProviderCompany").val();
            var contacts = $("#editEquipmentSysProviderContacts").val();
            var telphone = $("#editEquipmentSysProviderTelphone").val();
            var services = $("#editEquipmentSysProviderServices").val();
            if (company_name == '') {
            	$(".modal-title>span").html("必须输入服务商名称!");
                return false;
            } else if (contacts == '') {
            	$(".modal-title>span").html("必须输入联系人!");
                return false;
            } else if (telphone == '') {
            	$(".modal-title>span").html("必须输入联系电话!");
                return false;
            } else if (services == '') {
            	$(".modal-title>span").html("必须输入服务类型!");
                return false;
            } else {
            	$(".modal-title>span").html("");
            }
            $.ajax({
                url: 'saveEditEquipmentSysProvider',
                data: {
                	"id": id,
                    "company_name": company_name,
                    "contacts": contacts,
                    "telphone": telphone,
                    "services": services
                },
                dataType: 'json',
                type: 'post',
                success: function(data) {
                    if (data.code == "200") {
                        var rowsPerPage = $("#providerPageNoDiv").bs_pagination('getOption', 'rowsPerPage');
                        temp1 = (pagenum1 - 1) * rowsPerPage;
                        displayProviderInfo(pagenum1, $("#providerPageNoDiv").bs_pagination('getOption', 'rowsPerPage'));
                        $("#editEquipmentSysProviderModal").modal("hide");
                    } else {
                    	toastr.error("信息更新保存失败!");
                        $("#editEquipmentSysProviderModal").modal("show");
                        $(".modal-title>span").html("");
                    }
                }
            });
        });

        /* 服务商管理弹出模态框 */
        $("#SmarthomeBtn").click(function() {
        	$(".modal-title>span").html("");
            $("#insertEquipmentSysProviderModal").modal("show");
        });

        /* 保存添加服务商管理*/
        $("#saveCreateEquipmentSysProviderBtn").click(function() {
            var company_name = $("#addEquipmentSysProviderCompany").val();
            var contacts = $("#addEquipmentSysProviderContacts").val();
            var telphone = $("#addEquipmentSysProviderTelphone").val();
            var services = $("#addEquipmentSysProviderServices").val();
            if (company_name == '') {
            	$(".modal-title>span").html("必须输入服务商名称!");
                return false;
            } else if (contacts == '') {
            	$(".modal-title>span").html("必须输入联系人!");
                return false;
            } else if (telphone == '') {
            	$(".modal-title>span").html("必须输入联系方式!");
                return false;
            } else if (services == '') {
            	$(".modal-title>span").html("必须输入服务类型!");
                return false;
            } else {
            	$(".modal-title>span").html("");
            }
            $.ajax({
                url: "insertEquipmentSysProvider",
                data: {
                    "company_name": company_name,
                    "contacts": contacts,
                    "telphone": telphone,
                    "services": services
                },
                dataType: "json",
                type: "post",
                success: function(data) {
                    if (data.code == "200") {
                        $("#addEquipmentSysProviderCompany").val("");
                        $("#addEquipmentSysProviderContacts").val("");
                        $("#addEquipmentSysProviderTelphone").val("");
                        $("#addEquipmentSysProviderServices").val("");
                        var rowsPerPage = $("#providerPageNoDiv").bs_pagination('getOption', 'rowsPerPage');
                        temp1 = (pagenum1 - 1) * rowsPerPage;
                        displayProviderInfo(pagenum1, $("#providerPageNoDiv").bs_pagination('getOption', 'rowsPerPage'));
                        $("#insertEquipmentSysProviderModal").modal("hide");
                    } else {
                    	toastr.error("信息保存失败!");
                        $("#insertEquipmentSysProviderModal").modal("show");
                    }
                }
            });
        });
    });
</script>
</body>
</html>
