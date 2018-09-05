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
<title>房源信息</title>
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
				<h3>房源信息</h3>
			</div>
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
				
		</div>
	</div>
</body>
<script type="text/javascript">
	var type = '${type}';
</script>
</html>
