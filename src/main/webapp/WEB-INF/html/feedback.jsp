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
<title>投诉管理</title>
<link rel="stylesheet" type="text/css" href="static/css/base.css">
<link rel="stylesheet" type="text/css" href="static/css/page.css">
<link rel="stylesheet" type="text/css" href="static/css/toast.css">
<link rel="stylesheet" type="text/css" href="static/js/bootstrap_3.3.0/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="static/js/bootstrap-table/src/bootstrap-table.css">
<link rel="stylesheet" type="text/css" href="static/js/bs_pagination/css/jquery.bs_pagination.min.css">
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
<script type="text/javascript" src="static/js/main_min.js"></script>
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
				<h3>投诉管理</h3>
				<!--选择小区下拉框 -->
				<!-- <div class="select_dis"></div> -->
			</div>
			<div class="ctab-Main">
				<div class="ctab-Mian-cont">
					<div class="Mian-cont-btn clearfix">  
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
										<th class="t_4_q">投诉内容</th>
										<th class="t_2_q">联系方式</th>
										<th class="t_2_q">发布时间</th>
										<th class="t_2_q_cz">操作</th>
									</tr>
								</tbody>
							</table>
						</div>
						<div class="default" style="height: 540px; overflow: scroll;">
							<table border="0" cellspacing="0" cellpadding="0"
								class="defaultTable defaultTable2" id="noticeBody">
							</table>
						</div>
						<div id="pageNoDiv"></div>
						<!--pages E-->
					</div>
				</div>
			</div>
		</div>
		<!--main-->
	</div> 
<!-- 投诉信息详情显示 -->
		<div class="modal fade" id="FeedBack" role="dialog">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="Notice-title">投诉详情</h4>
				</div>
				<div class="modal-body-title-notice">
				<div class="notice-text"><span id="FeedbackTitle"></span></div>
				<div class="notice-text1"><span id="FeedbackTime"></span></div>
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
    if (type == 2) {
        var htmlStr = '';
        htmlStr += '<select id="select_head" class="select_head" onchange="propertyManagementSysNotice(this.value)"><option>全部小区</option><c:forEach var="item" items="${pmd }" varStatus="status"><option value="${item.id }">${item.management_department_name }</option></c:forEach></select>';
        $(".select_dis").html(htmlStr);
    }
    if (type == 3) {
        $(".t_2_q_cz").css("display", "block");
    }
    /* 获取下拉框value */
    function propertyManagementSysNotice(val) {
        var val;
        alert(val);

        temp = 0;
        pagenum = 1;
        display(1, 10);
    };
    /* 投诉信息详情模态框 */
    function onFeedback(id) {
        $("#FeedBack").modal("show");
        $("#update").val(id);
        $.ajax({
            url: "sel_FeedbackInfo",
            data: {
                "id": id
            },
            dataType: "json",
            type: "post",
            success: function(data) {
            	if(data.code==200){
                $("#FeedbackTitle").text(data.data.content);
                $("#FeedbackTime").text(data.data.create_time);
            }else{
            	toastr.error("暂无任何信息!");
            }
            }
        });

    };
    /* 删除投诉信息 */
    function deleteFeedBack(id) {

        Ewin.confirm({
            message: "确认要删除投诉信息吗？"
        }).on(function(e) {
            if (!e) {
                return;
            }
            $.ajax({
                url: "deleteFeedBackInfo",
                data: {
                    "id": id
                },
                dataType: "json",
                type: "post",
                success: function(data) {
                    if (data.code == "200") {
                        var rowsPerPage = $("#pageNoDiv").bs_pagination('getOption', 'rowsPerPage');
                        temp = (pagenum - 1) * rowsPerPage;
                        display(pagenum, $("#pageNoDiv").bs_pagination('getOption', 'rowsPerPage'));
                    } else {
                        toastr.error("信息删除失败!");
                    }
                }
            });
        });
    }

    /* 页面加载完后显示第一页数据*/ 
    display(1, 10);
    var temp = 0;
    var pagenum = 1;

    /* 显示投诉管理数据 */
    function display(pageIndex, pageSize) {
    	var content="";
    	var telphone="";
        var searchText = $("#searchText").val();
        if(/^[1][3,4,5,7,8][0-9]{9}$/.test(searchText))
        	{
        		telphone=searchText;
        	}else{
        		content=searchText;
        	}
        var JsonString = {
            "pageIndex": pageIndex,
            "pageSize": pageSize,
            "telphone": telphone,
            "content":content
        };
        var json = JSON.stringify(JsonString);
        $.ajax({
            url: "queryFeedBackInfo",
            data: json,
            contentType: 'application/json',
            dataType: "json",
            type: "post",
            success: function(data) {
                if (data.code == "200") {
                    var htmlStr = "";
                    $.each(data.data.obj,
                    function(index, obj) {
                        temp += 1;
                        htmlStr += '<tr>';
                        htmlStr += '<td class="t_0_q_5" value=' + obj.id + '>' + temp + '</td>';
                        htmlStr += '<td class="t_4_q"><a onclick="onFeedback(' + obj.id + ')" title="详情"">' + obj.conStr + '</a></td>';
                        htmlStr += '<td class="t_2_q">' + obj.telphone + '</a></td>';
                        htmlStr += '<td class="t_2_q">' + obj.create_time + '</td>';
                        if (type == 3) {
                            htmlStr += '<td class="t_2_q"><div class="btn_gonggao">' + '<button onclick="deleteFeedBack(' + obj.id + ')" type="button" class="btubf btn-danger"><span class="glyphicon glyphicon-minus"></span> 删除</button></div></td>';
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

    $(function() {
        /* 更新投诉管理 */
        $("#saveEditFeedBackBtn").click(function() {
            var id = $("#update").val();
            var content = $("#editFeedBackContent").val();

            var JsonString = {
                "id": id,
                "content": content
            };

            var json = JSON.stringify(JsonString);
            $.ajax({
                url: "saveEditFeedBackInfo",
                data: json,
                dataType: "json",
                contentType: 'application/json',
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

        /* 搜索投诉记录  */
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