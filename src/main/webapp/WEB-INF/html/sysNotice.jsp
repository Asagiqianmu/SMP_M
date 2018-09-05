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
<title>公告发布</title>
<link rel="stylesheet" type="text/css" href="static/css/base.css">
<link rel="stylesheet" type="text/css" href="static/css/page.css">
<link rel="stylesheet" type="text/css" href="static/css/login.css">
<link rel="stylesheet" type="text/css" href="static/css/toast.css">
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
<script type="text/javascript" src="static/js/zxxFile.js"></script>
</head>
<body style="background: #f6f5fa;">
	<!--content S-->
	<div class="super-content RightMain" id="RightMain">
    <!--header-->
    <div class="superCtab">
        <div class="ctab-title clearfix">
            <h3> 公告管理 </h3>
            <!--选择小区下拉框 -->
            <div class="select_dis"></div>
        </div>
        <div class="ctab-Main">
            <div class="ctab-Mian-cont">
                <div class="Mian-cont-btn clearfix">
                    <!-- 添加公告按钮 -->
                    <div class="operateBtn">
                    </div>
                    <div class="searchBar">
                        <input type="text" id="searchText" class="form-control srhTxt" placeholder="输入标题关键字搜索">
                        <button id="searchBtn" type="button" class="srhBtn">
                        </button>
                    </div>
                </div>
                <div class="Mian-cont-wrap">
                    <div class="defaultTab-T">
                        <table border="0" cellspacing="0" cellpadding="0" class="defaultTable space">
                            <tbody>
                                <tr>
                                    <th class="t_1_q"> 编号ID</th>
                                    <th class="t_2_q"> 标题</th>
                                    <th class="t_3_q">公告内容</th>
                                    <th class="t_2_q"> 发布时间</th>
                                    <th class="t_2_q_cz">操作</th>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                    <div class="default" style="height: 540px; overflow: scroll; ">
                        <table border="0" cellspacing="0" cellpadding="0" class="defaultTable defaultTable2"
                        id="noticeBody">
                        </table>
                    </div>
                    <div id="pageNoDiv">
                    </div>
                    
                    <!--pages E-->
                </div>
            </div>
        </div>
    </div>
</div>
	<!-- 编辑公告管理模态框 -->
	<div class="modal fade" id="editSysNoticeModal" role="dialog">
		<div class="modal-dialog" role="document">
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
						<div class="form-group">
							<label for="editSysNoticeTitle" class=" control-label">标题</label>
							<div style="width: 70%; ">
								<input style="margin-bottom:15px;" maxlength="8" type="text" class="form-control" id="editSysNoticeTitle">
							</div>
							<label for="editSysNoticeSubTitle" class=" control-label">副标题</label>
							<div style="width: 70%;">
								<input type="text" class="form-control" maxlength="8"
									id="editSysNoticeSubTitle">
							</div>
						</div>
						<div class="form-group">
							<label for="create-describe" class="control-label">公告内容</label>
							<div style="width: 81%;">
								<textarea style="height: 200px" class="form-control" rows="3" maxlength="200"
									id="editSysNoticeContent"></textarea>
							</div>
						</div>
					</form>

				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button id="saveEditSysNoticeBtn" type="button"
						class="btn btn-primary">更新</button>
				</div>
			</div>
		</div>
	</div>

	<!-- 添加公告管理模态框 -->
	<div class="modal fade" id="createSysNoticeModal" role="dialog">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">添加信息<span></span>
					</h4>
					
				</div>
				<div class="modal-body-title">
					<form class="form-horizontal" role="form">
						<div class="form-group">
						
							<label for="addSysNoticeTitle" class="control-label">标题</label>
								<div style="width: 70%;">
								<input style="margin-bottom:15px;" maxlength="8" type="text" class="form-control" id="addSysNoticeTitle"/>
								</div>
							<label for="addSysNoticeSubTitle" class="control-label">副标题</label>
							<div class="" style="width: 70%;">
								<input type="text" maxlength="8"  class="form-control"
									id="addSysNoticeSubTitle" value="">
							</div>
						</div>
						<div class="form-group">
						<label for="create-describe" class="control-label">公告内容</label>
						<div style="width: 81%;">
							<textarea style="height: 200px" maxlength="200"  class="form-control" rows="3"id="addSysNoticeContent"></textarea>
						</div>
						</div>
					</form>

				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button id="saveCreateSysNoticeBtn" type="button"
						class="btn btn-primary">保存</button>
				</div>
			</div>
		</div>
	</div>
</div>
		<!-- 公告详情显示 -->
		<div class="modal fade" id="Notice" role="dialog">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="Notice-title">公告详情</h4>
				</div>
				<div class="modal-body-title-notice">
				<div class="notice-titletext" ><h3><span id="NoticeTitle"></span></h3></div>
				<div class="notice-halftitle"><h4><span id="NoticeSubTitle"></span></h4></div> 
				<div class="notice-text"><span id="NoticeContent"></span></div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
				</div>
			</div>
		</div>
	</div>
	<script>
    /* 页面权限设置 */
    var type = '${type}';
    var userName='${userName}';
    if (type == 2) {
        var htmlStr = '';
        htmlStr += '<select id="select_head" class="select_head" onchange="propertyManagementSysNotice(this.value)"><option>全部小区</option><c:forEach var="item" items="${pmd }" varStatus="status"><option value="${item.id }">${item.management_department_name }</option></c:forEach></select>';
        $(".select_dis").html(htmlStr);
    }
    if (type == 3) {
        var htmlStr = '';
        $(".t_2_q_cz").css("display", "block");
        htmlStr += '<button id="createSysNoticeBtn" type="button"class="btn btn-primary"><span class="glyphicon glyphicon-plus"></span>创建公告</button>';
        $(".operateBtn").html(htmlStr);
    }
	
	var token = '${token}'; 
	var temp = 0;
	var pagenum = 1;
	/* 查询物业管理下的公告 */
	/* 获取下拉框value */
 	function propertyManagementSysNotice(val){
 		temp = 0;
 		pagenum = 1; 
 		display(1, 10);
	}
	  
	/* 编辑公告列表 */
	function editSysNotice(id) {
		$("#update").val(id);
		//var id = $("#editSysNotice").attr("name");
		$.ajax({
			url : "editSysNotice",
			data : {
				"id" : id
			},
			dataType : "json",
			type : "post",
			success : function(data) {
				if(data.code==200){
				$("#editSysNoticeTitle").val(data.data.title);
				$("#editSysNoticeSubTitle").val(data.data.subtitle);
				$("#editSysNoticeContent").val(data.data.content);
				}else{
					toastr.error("暂无任何信息!");
				}
			}
		});
		$("#editSysNoticeModal").modal("show");
	}
	/* 删除公告列表 */
	function deleteSysNotice(id) {
		Ewin.confirm({ message: "确认要删除公告吗？" }).on(function (e) {
               if (!e) {
                   return;
               }
               $.ajax({
               	url : "deleteSysNotice",
   				data : {
   					"id" : id
   				},
   				dataType : "json",
   				type : "post",
   				success : function(data) {
   					if (data.code == "200") {
   						var rowsPerPage = $("#pageNoDiv").bs_pagination(
   								'getOption', 'rowsPerPage');
   						temp = (pagenum - 1) * rowsPerPage;
   						display(pagenum, $("#pageNoDiv").bs_pagination('getOption',
   								'rowsPerPage'));
   					} else {
   						toastr.error("信息删除失败!");
   					}
   				}
               });
           });
	}
	$("#searchBtn").click(function() {
		temp = 0;
		pagenum = 1;
		display(1, 10);
	});
	
	
    /* 编辑公告列表 */
    function editSysNotice(id) {
        $("#update").val(id);
        $.ajax({
            url: "editSysNotice",
            data: {
                "id": id,
            },
            dataType: "json",
            type: "post",
            success: function(data) {
            	if(data.code==200){
                $("#editSysNoticeTitle").val(data.data.title);
                $("#editSysNoticeSubTitle").val(data.data.subtitle);
                $("#editSysNoticeContent").val(data.data.content);
            	}else{
            		toastr.error("暂无任何信息!");
            	}
            }	
        });
        $("#editSysNoticeModal").modal("show");
        $(".modal-title>span").html("");
    }

    /* 显示公告管理数据 */
    function display(pageIndex, pageSize) { 
        var searchTitle = $("#searchText").val();
        var pmid = $("#select_head").val();
        if (pmid == "全部小区") 
        {
            pmid = "";
        }
        var jsonString = {
            "pageIndex": pageIndex,
            "pageSize": pageSize,
            "title": searchTitle,
            "token": token,
            "pmid": pmid,
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
            url: "querySysNotice",
            data: json,
            contentType: "application/json",
            dataType: "json",
            type: "POST",
            success: function(data) {
                if (data.code == "200") {
                    var htmlStr = "";
                    $.each(data.data.obj,
                    function(index, obj) {
                        temp += 1;
                        htmlStr += '<tr>';
                        htmlStr += '<td class="t_1_q" value=' + obj.id + '>' + temp + '</td>';
                        htmlStr += '<td class="t_2_q">' + obj.title + '</td>';
                        htmlStr += '<td class="t_3_q"><a onclick="NoticeBtn(' + obj.id + ')" title="详情">' + obj.conStr + '</a></td>';
                        htmlStr += '<td class="t_2_q">' + obj.createtime + '</td>';
                        if (type == 3) {
                            htmlStr += '<td class="t_3_q"><div class="btn_gonggao">' + '<button onclick="editSysNotice(' + obj.id + ')" type="button" class="btubf btn-default"><span class="glyphicon glyphicon-pencil"></span> 修改</button>' + '<button onclick="deleteSysNotice(' + obj.id + ')" type="button" class="btubf btn-danger"><span class="glyphicon glyphicon-minus"></span> 删除</button></div></td>';
                        }
                        htmlStr += '</tr>';
                    });
                    $("#noticeBody").html(htmlStr);

                    //隔行换颜色
                    $("#noticeBody tr:odd").addClass("active");

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

    /* 页面加载完后显示第一页数据*/
    display(1, 10);

    /* 公告详情模态框 */
    function NoticeBtn(id) {
        $("#Notice").modal("show");
        $("#update").val(id);
        $(".modal-title>span").html("");
        //var id = $("#editSysNotice").attr("name");
        $.ajax({
            url: "editSysNotice",
            data: {
                "id": id
            },
            dataType: "json",
            type: "post",
            success: function(data) {
            	if(data.code){
                $("#NoticeTitle").text(data.data.title);
                $("#NoticeSubTitle").text(data.data.subtitle);
                $("#NoticeContent").text(data.data.content);
            	}else
           		{
           		toastr.error("暂无任何信息!");
           		}
            }
        });
    };

    $(function() {
        /* 保存编辑公告列表 */
        $("#saveEditSysNoticeBtn").click(function() {
            var id = $("#update").val();
            var title = $("#editSysNoticeTitle").val();
            var subtitle = $("#editSysNoticeSubTitle").val();
            var content = $("#editSysNoticeContent").val();
            if (title == '') {
                $(".modal-title>span").html(" 必须输入标题!");
                return false
            } else if (subtitle == '') {
            	$(".modal-title>span").html("必须输入副标题!");
                return false
            } else if (content == '') {
            	$(".modal-title>span").html("必须输入公告内容!");
                return false
            } else {
            	$(".modal-title>span").html("");
            }
            $.ajax({
                url: "saveEditSysNotice",
                data: {
                    "id": id,
                    "title": title,
                    "subtitle": subtitle,
                    "content": content,
                },
                dataType: "json",
                type: "post",
                success: function(data) {
                    if (data.code == "200") {
                        var rowsPerPage = $("#pageNoDiv").bs_pagination('getOption', 'rowsPerPage');
                        temp = (pagenum - 1) * rowsPerPage;
                        display(pagenum, $("#pageNoDiv").bs_pagination('getOption', 'rowsPerPage'));
                        $("#editSysNoticeModal").modal("hide");
                    } else {
                    	toastr.error("信息更新保存失败!");
                        $("#editSysNoticeModal").modal("show");
                    }
                }
            });
        });

        /* 添加公告弹出模态框 */
        $("#createSysNoticeBtn").click(function() {
            $("#editSysNoticeTitle").val("");
            $("#editSysNoticeSubTitle").val("");
            $("#editSysNoticeContent").val("");
            $("#createSysNoticeModal").modal("show");
            $(".modal-title>span").html("");
        });
        /* 保存添加公告*/
        $("#saveCreateSysNoticeBtn").click(function() {
            var addSysNoticeTitle = $("#addSysNoticeTitle").val();
            var addSysNoticeSubTitle = $("#addSysNoticeSubTitle").val();
            var addSysNoticeContent = $("#addSysNoticeContent").val();
            if (addSysNoticeTitle == '') {
            	 $(".modal-title>span").html("必须输入标题!");
                return false
            } else if (addSysNoticeSubTitle == '') {
            	 $(".modal-title>span").html("必须输入副标题!");
                return false
            } else if (addSysNoticeContent == '') {
            	 $(".modal-title>span").html("必须输入公告内容!");
                return false
            } else {
            	 $(".modal-title>span").html("");
            }

            $.ajax({
                url: "insertSysNotice",
                data: {
                    "title": addSysNoticeTitle,
                    "subtitle": addSysNoticeSubTitle,
                    "content": addSysNoticeContent,
                    "token": token,
                },
                dataType: "json",
                type: "post",
                success: function(data) {
                    if (data.code == "200") {
                        $("#addSysNoticeTitle").val("");
                        $("#addSysNoticeSubTitle").val("");
                        $("#addSysNoticeContent").val("");
                        var rowsPerPage = $("#pageNoDiv").bs_pagination('getOption', 'rowsPerPage');
                        temp = (pagenum - 1) * rowsPerPage;
                        display(pagenum, $("#pageNoDiv").bs_pagination('getOption', 'rowsPerPage'));
                        $("#createSysNoticeModal").modal("hide");
                    } else {
                    	toastr.error("信息添加失败!");
                        $("#createSysNoticeModal").modal("show");
                    }
                }
            });
        }); 
    });
</script>
</body>
</html>





































































