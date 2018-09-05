<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
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
<title>维修管理</title>
<link rel="stylesheet" type="text/css" href="static/css/base.css">
<link rel="stylesheet" type="text/css" href="static/css/page.css">
<link rel="stylesheet" type="text/css" href="static/css/toast.css">
<link rel="stylesheet" type="text/css" href="static/js/bootstrap_3.3.0/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="static/js/bootstrap-table/src/bootstrap-table.css">
<link rel="stylesheet" type="text/css" href="static/js/bs_pagination/css/jquery.bs_pagination.min.css">
<link rel="stylesheet" type="text/css" href="static/js/fileinput/fileinput.min.css">
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
<script type="text/javascript" src="static/js/fileinput/fileinput.min.js"></script>
<script type="text/javascript" src="static/js/fileinput/fileinput_locale_zh.js"></script>
<script type="text/javascript" src="static/js/main_min.js"></script>
<script type="text/javascript" src="static/js/json2.js"></script>
<script type="text/javascript" src="static/js/main_min.js"></script>
<script type="text/javascript" src="static/js/Ewin.js"></script>
<script type="text/javascript" src="static/js/toastr.js"></script>
</head>
<body style="background: #f6f5fa;">
	<!--content S-->
	<div class="super-content RightMain" id="RightMain">
		<!--header-->
		<div class="superCtab">
			<div class="ctab-title clearfix">
				<h3>维修管理</h3>
				<!--选择小区下拉框 -->
				<div class="select_dis"></div>
			</div>
			<div class="ctab-Main">
				<div class="ctab-Mian-cont">
					<div class="Mian-cont-btn clearfix">
						<!--创建维修信息按钮  -->
						<div class="operateBtn"></div>
						<div class="searchBar">
							<input type="text" id="searchText" class="form-control srhTxt"
								placeholder="输入服务商关键字搜索">
							<button id="searchBtn" type="button" class="srhBtn"></button>
						</div>
					</div>

					<div class="Mian-cont-wrap">
						<div class="defaultTab-T">
							<table border="0" cellspacing="0" cellpadding="0"
								class="defaultTable">
								<tbody>
									<tr>
										<th class="t_0_q_5">编号ID</th>
										<th class="t_1_q_5">服务商</th>
										<th class="t_2_q">内容介绍</th>
										<th class="t_1_q">联系方式</th>
										<th class="t_3_q">地址</th>
										<th class="t_2_q_cz">操作</th>
									</tr>
								</tbody>
							</table>
						</div>
						<div class="default" style="height: 540px; overflow: scroll;">
							<table border="0" cellspacing="0" cellpadding="0"
								class="defaultTable defaultTable2" id="mainTenanceBody">

							</table>
						</div>
						<!--pages S-->
						<div id="pageNoDiv"></div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- 编辑维修管理模态框 -->
	<div class="modal fade" id="editMaintenanceModal" role="dialog">
		<div class="modal-dialog" role="document" >
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">编辑信息<span></span></h4>
				</div>
				<div class="modal-body-title1">
					<form class="form-horizontal" role="form">
						<input id="update" type="hidden">
						<div class="form-group" style="margin-bottom:0;">
							<label for="editMaintenanceComPany"
								class="control-label">服务商</label>
							<div style="width: 70%;">
								<input type="text" class="form-control"
									id="editMaintenanceComPany">
							</div>
							</div>
						<div class="form-group" style="margin-bottom:0;">
							<label for="editMaintenanceTelphone"
								class=" control-label">联系电话</label>
							<div style="width: 70%;">
								<input  type="text" class="form-control" maxlength="11"
									onkeyup="value=value.replace(/\D/,'')"
									id="editMaintenanceTelphone">
							</div>
						</div>

						<div class="form-group" style="margin-bottom:0;">
							<label for="editMaintenanceAddress"
								class="control-label">地址</label>
							<div  style="width: 70%;">
								<input class="form-control" rows="3"
									id="editMaintenanceAddress"></input>
							</div>
							<label for="editMaintenanceBusiness"
								class="control-label">服务内容</label>
							<div style="width: 70%;">
								<input class="form-control" rows="3"
									id="editMaintenanceBusiness"></input>
							</div>
							<label class="control-label">图片信息</label>
							<img class="img-edit" id=img-edit >
							<form>
							    <div  class="modalfade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
							        <div class="modal-dialogfile" role="document">
							            <div style="width: 80%;">
							                <div>
							                    <input type="file" name="txt_file1" id="txt_file1" multiple class="file-loading" />
							                </div>
							             </div>
							        </div>
							    </div>
							</form>
						</div>
					</form>

				</div>
				<div class="modal-footer">
					<button class="but-editimg" id="editimg">更改图片</button>
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button id="saveEditMaintenanceBtn" type="button"
						class="btn btn-primary">更新</button>
				</div>
			</div>
		</div>
	</div>
	
	<!-- 添加居家维修模态框 -->
	<div class="modal fade" id="createMaintenanceModal" role="dialog">
		<div class="modal-dialog" role="document" >
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">添加信息<span></span></h4>
				</div>
				<div class="modal-body-title1">
					<form class="form-horizontal" role="form">
						<div class="form-group" style="margin-bottom:0;">
							<label for="addMaintenanceComPany" class="control-label">服务商</label>
							<div style="width: 70%;">
								<input  type="text" class="form-control"
									id="addMaintenanceComPany">
							</div>
							<label for="addMaintenanceTelphone"
								class="control-label">联系电话</label>
							<div  style="width: 70%;">
								<input type="text" class="form-control" maxlength="11"
									onkeyup="value=value.replace(/\D/,'')"
									id="addMaintenanceTelphone">
							</div>
						</div>
						<div class="form-group" style="margin-bottom:0;">
							<label for="addMaintenanceAddress" class="control-label">地址</label>
							<div style="width: 70%;">
								<input class="form-control" rows="3"
									id="addMaintenanceAddress"></input>
							</div>
							<label for="addMaintenanceBusiness"
								class="control-label">服务内容</label>
							<div style="width: 70%;">
								<input class="form-control" rows="3"
									id="addMaintenanceBusiness"></input>
							</div>
							<label  class="control-label">图片信息</label>
							<form>
							    <div id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
							        <div class="modal-dialogfile" role="document">
							            <div style="width: 80%;">
							                <div>
							                    <input type="file" name="txt_file" id="txt_file" multiple class="file-loading" />
							                </div>
							             </div>
							        </div>
							    </div>
							</form>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button id="saveCreateMaintenanceBtn" type="button"
						class="btn btn-primary">保存</button>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		var urlath = "${urlath}";
	/* 添加图片并且上传 */
	$(function () {
	    //0.初始化fileinput
	    var oFileInput = new FileInput();
	    oFileInput.Init("txt_file", "fileUpload");
	});
	//初始化fileinput
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
	        browseClass: "btn btn-primary", //按钮样式     
	        //dropZoneEnabled: false,//是否显示拖拽区域
	        //minImageWidth: 50, //图片的最小宽度
	        //minImageHeight: 50,//图片的最小高度
	        maxImageWidth: 504,//图片的最大宽度
	        maxImageHeight: 504,//图片的最大高度
	        //maxFileSize: 0,//单位为kb，如果为0表示不限制文件大小
	        //minFileCount: 0,
	        maxFileCount: 1, //表示允许同时上传的最大文件个数
	        enctype: 'multipart/form-data',
	        validateInitialCount:true,
	        previewFileIcon: "<i class='glyphicon glyphicon-king'></i>",
	        msgFilesTooMany: "选择上传的文件数量({n}) 超过允许的最大数值{m}！",
	        
	    });

	    //导入文件上传完成之后的事件
	    $("#txt_file").on("fileuploaded", function (event, data, previewId, index) {
	        $("#myModal").modal("hide");
	        vsrc=data.response.img;
	        var data = data.response.lstOrderImport;
	        console.log(data);
	        if (data == undefined) {
	            /* toastr.error('文件格式类型不正确!'); */
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
		/* 编辑-更改图片 */
	     $("#editimg").click(function() {
	    	 dsrc='';
	    	 $('#txt_file1').fileinput('clear')
	    	 $("#img-edit").css("display",'none');
	    	 $("#editimg").css("display",'none');
			 $("#myModal").css("display",'block');
	     });
	/* 页面权限设置 */
	    var vsrc='';
   		 var dsrc='';
		var type = '${type}';
	    if (type == 2) {
	        var htmlStr = '';
	        htmlStr += '<select id="select_head" class="select_head" onchange="propertyManagementSysNotice(this.value)"><option>全部小区</option><c:forEach var="item" items="${pmd }" varStatus="status"><option value="${item.id }">${item.management_department_name }</option></c:forEach></select>';
	        $(".select_dis").html(htmlStr);
	    }
		if (type == 3) {
			var htmlStr='';
	        $(".t_2_q_cz").css("display", "block");
	         htmlStr +='<button id="createMaintenanceBtn" type="button" class="btn btn-primary"><span class="glyphicon glyphicon-plus"></span>创建维修信息</button>';
	        $(".operateBtn").html(htmlStr)
		}
		var token='${token}';
		/* 页面加载完后显示第一页数据*/
		display(1, 10);
		var temp = 0;
		var pagenum = 1;
		
		 /* 获取下拉框value */
	    function propertyManagementSysNotice(val){
			var val;
	 		temp = 0;
	 		pagenum = 1;
	 		display(1, 10);
	    };
		 
    /* 显示维修管理数据 */
    function display(pageIndex, pageSize) {
    	var pmid=$("#select_head").val();
    	if(pmid=="全部小区")
		{
			pmid="";
		}
        var searchText = $("#searchText").val();

        var JsonString = {
            "pageIndex": pageIndex,
            "pageSize": pageSize,
            "company": searchText,
            "token": token,
            "pmid":pmid
        };
        var json = JSON.stringify(JsonString);

        $.ajax({
            url: 'queryMaintanance',
            data: json,
            contentType: 'application/json',
            dataType: 'json',
            type: 'post',
            success: function(data) {
            	if(data.code==200){ 
                var htmlStr = "";
                $.each(data.data.obj,function(index, obj) {
                    temp += 1;
                    htmlStr += '<tr>';
                    htmlStr += '<td class="t_0_q_5" value=' + obj.id + '>' + temp + '</td>';
                    htmlStr += '<td class="t_1_q_5">' + obj.company_name + '</a></td>';
                    htmlStr += '<td class="t_2_q">' + obj.main_business + '</td>';
                    htmlStr += '<td class="t_1_q">' + obj.telephone + '</td>';
                    htmlStr += '<td class="t_3_q">' + obj.address + '</td>';
                    if (type == 3) {
                        htmlStr += '<td class="t_2_q"><div class="btn_gonggao">' + '<button  onclick="editMaintenance(' + obj.id + ')" type="button" class="btubf btn-default"><span class="glyphicon glyphicon-pencil"></span> 修改</button>' + '<button  onclick="deleteMaintenance(' + obj.id + ')" type="button" class="btubf btn-danger"><span class="glyphicon glyphicon-minus"></span> 删除</button></div></td>';
                    }
                    htmlStr += '</tr>';
                });

                $("#mainTenanceBody").html(htmlStr);

                $("#mainTenanceBody  tr:odd").addClass("active");

                /* 显示翻页信息  */
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
                        display(pageObj.currentPage, pageObj.rowsPerPage);
                        pagenum = pageObj.currentPage;
                    }
                });
            }else{
                toastr.error("暂无任何信息!");
            }
          }
        })
    }

    /* 编辑维修信息 */
    function editMaintenance(id) {
      	 $("#img-edit").css("display",'block');
    	 $("#editimg").css("display",'block');
    	 $("#myModal").css("display",'none');		
        $("#update").val(id);
        $.ajax({
            url: 'editMaintenance',
            data: {
                "id": id
            },
            dataType: 'json',
            type: 'post',
            success: function(data) {
                if (data.code == "200") {
                    $("#editMaintenanceComPany").val(data.data.company_name);
                    $("#editMaintenanceBusiness").val(data.data.main_business);
                    $("#editMaintenanceAddress").val(data.data.address);
                    $("#editMaintenanceTelphone").val(data.data.telephone);
                    $("#editMaintenanceModal").modal("show");
                    $(".modal-title>span").html("");
                    $("#img-edit").attr("src",urlath+data.data.image_url);
                    dsrc=data.data.image_url;
                }else{
                	toastr.error("信息获取失败!");
                }
            }
        });
        /* 修改图片并且上传 */
        //初始化fileinput
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
                browseClass: "btn btn-primary", //按钮样式     
                //dropZoneEnabled: false,//是否显示拖拽区域
                //minImageWidth: 50, //图片的最小宽度
                //minImageHeight: 50,//图片的最小高度
                maxImageWidth: 504,//图片的最大宽度
                maxImageHeight: 504,//图片的最大高度
                //maxFileSize: 0,//单位为kb，如果为0表示不限制文件大小
                //minFileCount: 0,
                maxFileCount: 1, //表示允许同时上传的最大文件个数
                enctype: 'multipart/form-data',
                validateInitialCount:true,
                previewFileIcon: "<i class='glyphicon glyphicon-king'></i>",
                msgFilesTooMany: "选择上传的文件数量({n}) 超过允许的最大数值{m}！",           
            });

            //导入文件上传完成之后的事件
            $("#txt_file1").on("fileuploaded", function (event, data, previewId, index) {
                $("#myModal").modal("hide");
                dsrc=data.response.img;
                var data = data.response.lstOrderImport;
                if (data == undefined) {
                    /* toastr.error('文件格式类型不正确'); */
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
        $(function () {
            //0.初始化fileinput
            var oFileInput = new FileInput();
            oFileInput.Init("txt_file1", "fileUpload");
        });
    }

    /* 删除维修信息 */
    function deleteMaintenance(id) {

        Ewin.confirm({
            message: "确认要删除维修信息吗？"
        }).on(function(e) {
            if (!e) {
                return;
            }
            $.ajax({
                url: "deleteMaintenanceInfo",
                data: {
                    "id": id
                },
                dataType: "json",
                type: "post",
                success: function(data) {
                    if (data.code == "200") {
                        var rowsPerPage = $("#pageNoDiv").bs_pagination('getOption', 'rowsPerPage');
                        temp = (pagenum - 1) * rowsPerPage;
                        display(1, $("#pageNoDiv").bs_pagination('getOption', 'rowsPerPage'));
                    } else {
                        toastr.error('信息删除失败!');
                    }
                }
            });
        });
    }
    $(function() {
        /* 保存维修修改 */
        $("#saveEditMaintenanceBtn").click(function() {
            var id = $("#update").val();
            var company = $("#editMaintenanceComPany").val();
            var business = $("#editMaintenanceBusiness").val();
            var address = $("#editMaintenanceAddress").val();
            var telphone = $("#editMaintenanceTelphone").val();
            if (company == '') {
            	$(".modal-title>span").html("必须输入服务商!");
                return false;
            } else if (telphone == '') {
            	$(".modal-title>span").html("必须输入联系电话!");
                return false;
            } else if (address == '') {
            	$(".modal-title>span").html("必须输入住址!");
                return false;
            } else if (business == '') {
            	$(".modal-title>span").html("必须输入服务内容!");
                return false;
            } else if (dsrc == '') {
            	$(".modal-title>span").html("请选择并且上传图片!");
                return false;
            } else {
            	$(".modal-title>span").html("");
            }
            $.ajax({
                url: 'saveEditMaintenance',
                data: {
                    "id": id,
                    "company_name": company,
                    "main_business": business,
                    "telephone": telphone,
                    "address": address,
                    "image_url":dsrc
                },
                dataType: 'json',
                type: 'post',
                success: function(data) {
                    if (data.code == "200") {
                        var rowsPerPage = $("#pageNoDiv").bs_pagination('getOption', 'rowsPerPage');
                        temp = (pagenum - 1) * rowsPerPage;
                        display(pagenum, $("#pageNoDiv").bs_pagination('getOption', 'rowsPerPage'));
                        $("#editMaintenanceModal").modal("hide");
                    } else {
                    	toastr.error("信息更新保存失败!");
                        $("#editMaintenanceModal").modal("show");
                    }
                }
            });

        });

        /* 添加维修模态框*/
        $("#createMaintenanceBtn").click(function() {
        	$('#txt_file').fileinput('clear')
            $("#addMaintenanceComPany").val("");
            $("#addMaintenanceTelphone").val("");
            $("#addMaintenanceAddress").val("");
            $("#addMaintenanceBusiness").val("");
            $("#createMaintenanceModal").modal("show");
            $(".modal-title>span").html("");
        });

        /* 保存添加维修信息 */
        $("#saveCreateMaintenanceBtn").click(function() {
            var addMaintenanceComPany = $("#addMaintenanceComPany").val();
            var addMaintenanceTelphone = $("#addMaintenanceTelphone").val();
            var addMaintenanceAddress = $("#addMaintenanceAddress").val();
            var addMaintenanceBusiness = $("#addMaintenanceBusiness").val();
            if (addMaintenanceComPany == '') {
            	$(".modal-title>span").html("必须输入服务商!");
                return false;
            } else if (addMaintenanceTelphone == '') {
            	$(".modal-title>span").html("必须输入联系电话!");
                return false;
            } else if (addMaintenanceAddress == '') {
            	$(".modal-title>span").html("必须输入住址!");
                return false;
            } else if (addMaintenanceBusiness == '') {
            	$(".modal-title>span").html("必须输入服务内容!");
                return false;
            }  else if (vsrc == '') {
            	$(".modal-title>span").html("请选择并且上传图片!");
                return false;
            } else {
            	$(".modal-title>span").html("");
            }
            $.ajax({
                url: 'insertMaintenance',
                data: {
                	"image_url":vsrc,
                    "company_name": addMaintenanceComPany,
                    "telephone": addMaintenanceTelphone,
                    "address": addMaintenanceAddress,
                    "main_business": addMaintenanceBusiness,
                    "token":token
                },
                dataType: 'json',
                type: 'post',
                success: function(data) {
                    if (data.code == "200") {
                    	var rowsPerPage = $("#pageNoDiv").bs_pagination('getOption', 'rowsPerPage');
                        temp = (pagenum - 1) * rowsPerPage;
                        display(pagenum, $("#pageNoDiv").bs_pagination('getOption', 'rowsPerPage'));
                        $("#createMaintenanceModal").modal("hide");
                    }else 
                    {
                        toastr.error("信息添加失败!");
                        $("#createMaintenanceModal").modal("show");
                    }
                }
            });
        });

        /* 搜索维修记录  */
        $("#searchBtn").click(function() {
            //alert(11);
            var rowsPerPage = $("#pageNoDiv").bs_pagination('getOption', 'rowsPerPage');
            temp = (pagenum - 1) * rowsPerPage;
            display(1, $("#pageNoDiv").bs_pagination('getOption', 'rowsPerPage'));
        });
    });
</script>
</body>
</html>