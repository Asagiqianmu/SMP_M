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
<title>便民信息管理</title>
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
<script type="text/javascript" src="static/js/Ewin.js"></script>
<script type="text/javascript" src="static/js/toastr.js"></script>
</head>
<body style="background: #f6f5fa;">
	<!--content S-->
	<div class="super-content RightMain" id="RightMain">

		<!--header-->
		<div class="superCtab">
			<div class="ctab-title clearfix">
				<h3>便民信息</h3>
				<!--选择小区下拉框 -->
				<div class="select_dis"></div>
							
			</div>

			<div class="ctab-Main">

				<div class="ctab-Mian-cont">
					<div class="Mian-cont-btn clearfix">
						<!-- 添加便民信息按钮  -->
						<div class="operateBtn"></div>
						<div class="searchBar">
							<input type="text" id="searchText" class="form-control srhTxt"
								placeholder="输入标题关键字搜索">
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
										<th class="t_1_q">姓名</th>
										<th class="t_1_q_5">联系电话</th>
										<th class="t_3_q">联系地址</th>
										<th class="t_2_q">服务内容</th>
										<th class="t_2_q_cz">操作</th>
									</tr>
								</tbody>
							</table>
						</div>
						<div class="default" style="height: 540px; overflow: scroll;">
							<table border="0" cellspacing="0" cellpadding="0"
								class="defaultTable defaultTable2" id="convenienceInfoBody">
							</table>
						</div>
						<!--pages S-->
						<div id="pageNoDiv"></div>
						<!--pages E-->
					</div>

				</div>
			</div>
		</div>
		<!--main-->
	</div>

	<!-- 编辑便民信息管理模态框 -->
	<div class="modal fade" id="editConvenienceInfoModal" role="dialog">
		<div class="modal-dialog" role="document">
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
						<div class="form-group" style="margin-bottom: 0;">
							<label for="editConvenienceInfoName" class="control-label">姓名</label>
							<div style="width: 70%;">
								<input type="text"
									class="form-control" id="editConvenienceInfoName">
							</div>
							<label for="editConvenienceInfoTelphone" class="control-label">手机号</label>
							<div style="width: 70%;">
								<input type="text" class="form-control" maxlength="11"
									onkeyup="value=value.replace(/\D/,'')"
									id="editConvenienceInfoTelphone">
							</div>
						</div>
						<div class="form-group" style="margin-bottom: 0;">
							<label for="editConvenienceInfoAddress" class="control-label">住址</label>
							<div style="width: 70%;">
								<input type="text" class="form-control"	
									id="editConvenienceInfoAddress">
							</div>
							<label for="editConvenienceInfoServiceType" class="control-label">服务类型</label>
							<div style="width: 70%;">
								<input type="text" class="form-control"
									id="editConvenienceInfoServiceType">
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
					<button id="saveEditConvenienceInfoBtn" type="button"
						class="btn btn-primary">保存</button>
				</div>
			</div>
		</div>
		</form>
	</div>
	</div>
	</div>
	</div>
	<!-- 添加便民管理模态框 -->
	<div class="modal fade" id="createConvenienceInfoBtnModal"
		role="dialog">
		<div class="modal-dialog " role="document">
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
						<div class="form-group" style="margin-bottom: 0;">
							<label for="addConvenienceInfoName" class="control-label">姓名</label>
							<div style="width: 70%;">
								<input type="text"
									class="form-control" id="addConvenienceInfoName">
							</div>
							<label for="addConvenienceInfoTelphone" class="control-label">联系电话</label>
							<div style="width: 70%;">
								<input type="text" id="addConvenienceInfoTelphone"
									class="form-control" maxlength="11"
									onkeyup="value=value.replace(/\D/,'')"
									id="addConvenienceInfoTelphone">
							</div>
						</div>
						<div class="form-group" style="margin-bottom: 0;">
							<label for="addConvenienceInfoAddress" class="control-label">住址</label>
							<div style="width: 70%;">
								<input type="text"
									class="form-control" id="addConvenienceInfoAddress">
							</div>
							<label for="addConvenienceInfoServiceType" class="control-label">服务内容</label>
							<div style="width: 70%;">
								<input type="text" class="form-control"
									id="addConvenienceInfoServiceType">
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
					<button id="saveConvenienceInfoBtn" type="button"
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
        var htmlStr = '';
        $(".t_2_q_cz").css("display", "block");
        htmlStr += '<button id="createConvenienceInfoBtn" type="button"class="btn btn-primary"><span class="glyphicon glyphicon-plus"></span>添加便民信息</button>';
        $(".operateBtn").html(htmlStr);
    }
    var token = '${token}';

    /* 获取下拉框value */
 
    function propertyManagementSysNotice(val) {
        var val;
        temp = 0;
        pagenum = 1;
        display(1, 10);
    };

    /* 编辑便民信息 */
    function editConvenienceInfo(id) {
	   	 $("#img-edit").css("display",'block');
		 $("#editimg").css("display",'block');
		 $("#myModal").css("display",'none');
        $("#update").val(id);
        //alert(id);
        $.ajax({
            url: "editConvenienceInfo",
            data: {
                "id": id
            },
            dataType: "json",
            type: "post",
            success: function(data) {
                $("#editConvenienceInfoName").val(data.data.contacts);
                $("#editConvenienceInfoTelphone").val(data.data.c_telphone);
                $("#editConvenienceInfoAddress").val(data.data.c_address);
                $("#editConvenienceInfoServiceType").val(data.data.service_type);
                $("#editConvenienceInfoModal").modal("show");
                $(".modal-title>span").html("");
                $("#img-edit").attr("src",urlath+data.data.headimg);
                dsrc=data.data.headimg;
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
    };
    /* 删除便民信息 */
    function deleteConvenienceInfo(id) {

        Ewin.confirm({
            message: "确认要删除便民信息吗？"
        }).on(function(e) {
            if (!e) {
                return;
            }
            $.ajax({
                url: "deleteConvenienceInfo",
                data: {
                    "id": id
                },
                dataType: "json",
                type: "post",
                success: function(data) {
                    if (data.code == "200")
                    {
                    	 var rowsPerPage = $("#pageNoDiv").bs_pagination('getOption', 'rowsPerPage');
                         temp = (pagenum - 1) * rowsPerPage;
                         display(pagenum, $("#pageNoDiv").bs_pagination('getOption', 'rowsPerPage'));
                    } else 
                    {
                        toastr.error("信息删除失败!");
                    }
                }
            });
        });
    }

    
    display(1, 10);
		var temp = 0;
		var pagenum = 1;
		function display(pageIndex, pageSize) {
			var pmid=$("#select_head").val();
			if(pmid=="全部小区")
				{
					pmid="";
				} 
			var searchText = $("#searchText").val();
			
			var contacts = "";
			var telphone = "";
			if (/^[1][3,4,5,7,8][0-9]{9}$/.test(searchText)) {
				telphone = searchText;
			} else if (/^([\u4E00-\uFA29]|[\uE7C7-\uE7F3]|[a-zA-Z0-9])*$/
					.test(searchText)) {
				contacts = searchText;
			}
			var JsonString = {
				"pageIndex" : pageIndex,
				"pageSize" : pageSize,
				"telphone" : telphone,
				"contacts" : contacts,
				"token":token,
				"pmid":pmid
			};
			var json = JSON.stringify(JsonString);
			$.ajax({
				url : 'queryConvenienceInfo',
				data : json,
				contentType : 'application/json',
				dataType : 'json',
				type : 'post',
				success : function(data) {
					if(data.code==200){
					var htmlStr = '';
                	$.each(data.data.obj,function(index, obj) {
                    temp += 1;
                    htmlStr += '<tr>';
                    htmlStr += '<td class="t_0_q_5" value=' + obj.id + '>' + temp + '</td>';
                    htmlStr += '<td class="t_1_q">' + obj.contacts + '</a></td>';
                    htmlStr += '<td class="t_1_q_5">' + obj.c_telphone + '</td>';
                    htmlStr += '<td class="t_3_q">' + obj.c_address + '</td>';
                    htmlStr += '<td class="t_2_q">' + obj.service_type + '</td>';
                    if (type == 3) {
                        htmlStr += '<td class="t_2_q"><div class="btn_gonggao">' + '<button id="editConvenienceInfo"  onclick="editConvenienceInfo(' + obj.id + ')" type="button" class="btubf btn-default"><span class="glyphicon glyphicon-pencil"></span> 修改</button>' + '<button id="deleteConvenienceInfo" onclick="deleteConvenienceInfo(' + obj.id + ')" type="button" class="btubf btn-danger"><span class="glyphicon glyphicon-minus"></span> 删除</button></div></td>';
                    };
                    htmlStr += '</tr>';
                });

                $("#convenienceInfoBody").html(htmlStr);
                $("#convenienceInfoBody tr:odd").addClass("active");
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
                        display(pageObj.currentPage, pageObj.rowsPerPage);
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
        /* 保存修改便民信息 */
        $("#saveEditConvenienceInfoBtn").click(function() {
            var id = $("#update").val();
            var name = $("#editConvenienceInfoName").val();
            var telphone = $("#editConvenienceInfoTelphone").val();
            var address = $("#editConvenienceInfoAddress").val();
            var serviceType = $("#editConvenienceInfoServiceType").val();
            if (name == '') {
            	$(".modal-title>span").html("必须输入姓名!");
                return false;
            } else if (telphone == '') {
            	$(".modal-title>span").html("必须输入联系电话!");
                return false;
            } else if (address == '') {
            	$(".modal-title>span").html("必须输入住址!");
                return false;
            } else if (serviceType == '') {
            	$(".modal-title>span").html("必须输入服务内容!");
                return false;
            } else if (dsrc == '') {
            	$(".modal-title>span").html("请选择并且上传图片!");
                return false;
            } else {
            	$(".modal-title>span").html("");
            }
            $.ajax({
                url: 'saveEditConvenienceInfo',
                data: {
                    "id": id,
                    "contacts": name,
                    "service_type": serviceType,
                    "c_address": address,
                    "c_telphone": telphone,
                    "headimg":dsrc
                },
                dataType: 'json',
                type: 'post',
                success: function(data) {
                    if (data.code == "200") {
                        /* 保存成功 */
                        var rowsPerPage = $("#pageNoDiv").bs_pagination('getOption', 'rowsPerPage');
                        temp = (pagenum - 1) * rowsPerPage;
                        display(pagenum, $("#pageNoDiv").bs_pagination('getOption', 'rowsPerPage'));
                        $("#editConvenienceInfoModal").modal("hide");
                    } else {
                    	toastr.error("信息保存失败!");
                        $("#editConvenienceInfoModal").modal("show");
                    }
                }
            });
        });

        /* 增加便民信息 */
        $("#createConvenienceInfoBtn").click(function() {
        	$('#txt_file').fileinput('clear')
            $("#addConvenienceInfoName").val("");
            $("#addConvenienceInfoTelphone").val("");
            $("#addConvenienceInfoAddress").val("");
            $("#addConvenienceInfoServiceType").val("");
            $("#createConvenienceInfoBtnModal").modal("show");
            $(".modal-title>span").html("");
        })

        /* 保存便民信息 */
        $("#saveConvenienceInfoBtn").click(function() {
            var name = $("#addConvenienceInfoName").val();
            var telphone = $("#addConvenienceInfoTelphone").val();
            var address = $("#addConvenienceInfoAddress").val();
            var serviceType = $("#addConvenienceInfoServiceType").val();
            if (name == '') {
            	$(".modal-title>span").html(" 必须输入姓名!");
                return false;
            } else if (telphone == '') {
            	$(".modal-title>span").html("必须输入联系电话!");
                return false;
            } else if (address == '') {
            	$(".modal-title>span").html("必须输入住址!");
                return false;
            } else if (serviceType == '') {
            	$(".modal-title>span").html("必须输入服务内容!");
                return false;
            } else if (vsrc == '') {
            	$(".modal-title>span").html("请选择并且上传图片!");
                return false;
            } else {
            	$(".modal-title>span").html("");
            }
            $.ajax({
                url: 'insertConvenienceInfo',
                data: {
                	"headimg":vsrc,
                    "contacts": name,
                    "c_telphone": telphone,
                    "c_address": address,
                    "service_type": serviceType,
                    "token":token
                },
                dataType: 'json',
                type: 'post',
                success: function(data) {
                    if (data.code == "200") {
                        var rowsPerPage = $("#pageNoDiv").bs_pagination('getOption', 'rowsPerPage');
                        temp = (pagenum - 1) * rowsPerPage;
                        display(pagenum, $("#pageNoDiv").bs_pagination('getOption', 'rowsPerPage'));
                        $("#createConvenienceInfoBtnModal").modal("hide");
                    } else {
                    	toastr.error("信息保存失败!");
                        $("#createConvenienceInfoBtnModal").modal("show");
                    }
                }
            })
        });

        /* 搜索便民信息 */
        /* 搜索开门记录  */
        $("#searchBtn").click(function() {
            var rowsPerPage = $("#pageNoDiv").bs_pagination('getOption', 'rowsPerPage');
            temp = (pagenum - 1) * rowsPerPage;
            display(1, $("#pageNoDiv").bs_pagination('getOption', 'rowsPerPage'));
        });
    });
</script>
</body>
</html>