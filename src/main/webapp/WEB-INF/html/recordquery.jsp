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
<title>能耗设备</title>
<link rel="stylesheet" type="text/css" href="static/css/base.css">
<link rel="stylesheet" type="text/css" href="static/css/page.css">
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
<script type="text/javascript" src="static/js/toastr.js"></script>
</head>
<body style="background: #f6f5fa;">
<div class="super-content RightMain" id="RightMain">
    <!--header-->
    <div class="superCtab">
        <div class="ctab-title clearfix">
            <h3>能耗设备</h3>
			<!--选择小区下拉框 -->
			<div class="select_dis"></div>
        </div>
       	<!-- 导航栏 ↓-->
		<div class="ctab-Main">
			<div class="ctab-Main-title">
				<ul class="clearfix">
					<li id="water" class="cur"><a>水表列表</a></li>
					<li id="Electricity"><a>电表列表</a></li>
				</ul>
			</div>
		</div>
        <div class="ctab-Main">
            <div class="ctab-Mian-conts">
                <div class="Mian-cont-btn clearfix">
                    <!-- 添加公告按钮 -->
                    <div class="searchBar">
                        <input type="text" id="searchText" class="form-control srhTxt" placeholder="输入标题关键字搜索">
                        <button id="searchBtn" type="button" class="srhBtn">
                        </button>
                    </div>
                </div>
<!--************************ 水费记录****************************** -->
                <div class="Mian-cont-wrap">
                    <div class="defaultTab-T">
                        <table border="0" cellspacing="0" cellpadding="0" class="defaultTable">
                           <tbody>
							<tr>
								<th class="t_1_q">编号ID</th>
								<!-- <th class="t_2_q">业主姓名</th> -->
								<th class="t_2_q">房间号</th>
								<th class="t_1_q">使用量</th>
								<th class="t_2_q">类型</th>
								<th class="t_2_q">状态</th>
								<th class="t_2_q">创建时间</th>
							</tr>
                        </table>
                    </div>
                    <div class="default" style="height: 540px; overflow: scroll;">
                        <table border="0" cellspacing="0" cellpadding="0" class="defaultTable defaultTable2"
                        id="ownerAccountBody">
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
</body>
</html>

<script type="text/javascript">
/* 页面权限设置 */
var type = '${type}';
var Types = 0;
var temp = 0;
var pagenum = 1;
$(".t_2_q_cz").css("display", "block")
$('.ctab-Main-title li').click(function() {
    $(this).addClass('cur').siblings().removeClass('cur');
});
$(function(){
	display(1,10);
})

Types 

/* 水表表查询 */
function display(pageIndex, pageSize){
	var jsonString = {
         "pageIndex": pageIndex,
         "pageSize": pageSize,
         "energyType":Types,
		 "token": token
	};
	var json=JSON.stringify(jsonString)
	$.ajax({
		url:'queryEnergyConsumptionDeviceByHouseIds',
        data: json,
        contentType: "application/json",
        dataType: "json",
        type: "POST",
		success:function(data){
			if(data.code==200)
			{
				 var htmlStr = "";
                 $.each(data.data.obj,
                 function(index, obj) {
                     temp += 1;
                     htmlStr += '<tr>';
                     htmlStr += '<td class="t_1_q" value=' + obj.id + '>' + temp + '</td>';
                     htmlStr += '<td class="t_2_q">' + obj.house_id + '</td>';
                     htmlStr += '<td class="t_1_q">' + obj.meter_value + '</td>';
                     htmlStr += '<td class="t_2_q">' + obj.subTypeName + '</td>';
                     htmlStr += '<td class="t_2_q">' + obj.strDeviceStstus + '</td>';
                     htmlStr += '<td class="t_2_q">' + obj.createTime + '</td>';
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
$('#water').click(function() {
    var tbody = '<tbody>' + +'<tr>' + '	<th class="t_1_q">编号ID</th>' + '<th class="t_2_q">房间号</th>' + '<th class="t_1_q">使用量</th>' + '<th class="t_2_q">类型</th>' + '<th class="t_2_q">状态</th>' + '<th class="t_2_q">创建时间</th>'+'</tr>' + '</tbody>';
    $(".defaultTable").html(tbody);
    $("#ownerAccountBody").html('');
    Types = 0;
    temp = 0;
    pagenum = 1;
    display(1,10)
});
$('#Electricity').click(function() {

	var tbody = '<tbody>' + +'<tr>' + '	<th class="t_1_q">编号ID</th>' + '<th class="t_2_q">房间号</th>' + '<th class="t_1_q">使用量</th>' + '<th class="t_2_q">类型</th>'+ '<th class="t_2_q">状态</th>' + '<th class="t_2_q">创建时间</th>' + '</tr>' + '</tbody>';
    $(".defaultTable").html(tbody);
    $("#ownerAccountBody").html('');
    Types = 1;
    temp = 0;
    pagenum = 1;
    display(1,10)
})
 
</script>