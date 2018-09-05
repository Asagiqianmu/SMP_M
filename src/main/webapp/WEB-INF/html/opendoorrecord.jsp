<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";
%>
<!DOCTYPE html>
<html class=" js csstransforms3d">
<head>
<base href="<%=basePath%>">
<meta charset="utf-8">
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<title>开门记录</title>
<link rel="stylesheet" type="text/css" href="static/css/base.css">
<link rel="stylesheet" type="text/css" href="static/css/page.css">
<link rel="stylesheet" type="text/css" href="static/css/toast.css">
<link rel="stylesheet" type="text/css" href="static/js/bootstrap_3.3.0/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" type="text/css" href="static/js/bs_pagination/css/jquery.bs_pagination.min.css">
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
<script type="text/javascript" src="static/js/zxxFile.js"></script>
</head>
<body style="background: #f6f5fa;">
	<!--content S-->
	<div class="super-content RightMain" id="RightMain">
		<!--header-->
		<div class="superCtab">
			<div class="ctab-title clearfix">
				<h3>开门记录</h3>
				<div class="select_dis"></div>
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
								class="defaultTable space">
								<tbody>
									<tr>
										<th class="t_1_q">编号ID</th>
										<th class="t_3_q">钥匙名称</th>
										<th class="t_1_q">开门状态</th>
										<th class="t_1_q">开门方式</th>
										<th class="t_2_q">开门人信息</th>
										<th class="t_2_q">时间</th>
									</tr>
								</tbody>
							</table>
						</div>
						<div class="default" style="height: 540px; overflow: scroll;">
							<table border="0" cellspacing="0" cellpadding="0"
								class="defaultTable defaultTable2" id="openDoorBody">
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
	<script>
	var token = '${token}'; 
    /* 页面权限设置 */
    var type = '${type}';
    if (type == 2) {
        var htmlStr = '';
        htmlStr += '<select id="select_head" class="select_head" onchange="propertyManagementSysNotice(this.value)"><option>全部小区</option><c:forEach var="item" items="${pmd }" varStatus="status"><option value="${item.id }">${item.management_department_name }</option></c:forEach></select>';
        $(".select_dis").html(htmlStr)
    }
    /* 页面加载完后显示第一页数据*/
    display(1, 10);
    var temp = 0;
    var pagenum = 1;
    var open = "";

    /* 获取下拉框value */
    function propertyManagementSysNotice(val) {
        temp = 0;
        pagenum = 1;
        display(1, 10);
    };
    /* 显示公告管理数据 */
    function display(pageIndex, pageSize) {
    	var pmid = $("#select_head").val();
        if (pmid == "全部小区") 
        {
            pmid = "";
        }
        var searchText = $("#searchText").val();
        var JsonString = {
            "pageIndex": pageIndex,
            "pageSize": pageSize,
            "style": searchText,
            "token":token,
            "pmid":pmid
        };
        var json = JSON.stringify(JsonString);
        $.ajax({
            url: "queryOpenDoorRecord",
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
                        if (obj.isopen == "1") {
                            open = "开门失败";
                        } else {
                            open = "开门成功";
                        }
                        htmlStr += '<tr>';
                        htmlStr += '<td class="t_1_q" value=' + obj.id + '>' + temp + '</td>';
                        htmlStr += '<td class="t_3_q">' + obj.keyname + '</a></td>';
                        htmlStr += '<td class="t_1_q">' + open + '</a></td>';
                        htmlStr += '<td class="t_1_q">' + obj.style + '</a></td>';
                        htmlStr += '<td class="t_2_q">' + obj.iswho + '</td>';
                        htmlStr += '<td class="t_2_q">' + obj.createTime + '</td>';
                        htmlStr += '</tr>';
                    });
                    $("#openDoorBody").html(htmlStr);

                    //隔行换颜色
                    $("#openDoorBody tr:odd").addClass("active");

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
                            if (pageObj.currentPage == totalPages) {
                            	
                            };
                            display(pageObj.currentPage, pageObj.rowsPerPage);
                        }
                    });
                }else{
                	toastr.error("暂无任何信息!");
                }
            }
        });
    }

    $(function() {
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