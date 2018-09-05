<%@page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() 
	+ request.getContextPath() + "/";
%>
<html class=" js csstransforms3d">
<head>
<base href="<%=basePath%>">
<meta charset="utf-8">
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>缴费管理</title>
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
				<h3>缴费管理</h3>
				<!--选择小区下拉框 -->
				<div class="select_dis"></div>
			</div>

			<div class="ctab-Main">
				<div class="ctab-Mian-cont">
					<div class="Mian-cont-btn clearfix">
						<button id="queryOwnerPayCatBtn" type="button"
							class="bttn-primaryy"> 
							<span class="glyphicon glyphicon-plus"></span>已缴费信息
						</button>
						<button id="queryUnOwnerPayCatBtn" type="button"
							class="bttn-primaryy">
							<span class="glyphicon glyphicon-plus"></span>未缴费信息
						</button>
						<!-- 添加缴费/导入/导出按钮 -->
						<div class="operateBtn1"></div>
						<div class="searchBar">
							<input type="text" id="searchText" value=""
								class="form-control srhTxt" placeholder="输入标题关键字搜索">
							<button id="searchBtn" type="button" class="srhBtn"></button>
						</div>
					</div>
					<div class="Mian-cont-wrap">
						<div class="defaultTab-T">
							<table border="0" cellspacing="0" cellpadding="0"
								class="defaultTable">
								<tbody>
									<tr>
										<th class="t_1_q">编号ID</th>
										<th class="t_2_q">姓名</th>
										<th class="t_2_q">费用类型</th>
										<th class="t_1_q">金额(元)</th>
										<th class="t_2_q">录入时间</th>
										<th class="t_2_q">缴费时间</th>
									</tr>
								</tbody>
							</table>
						</div>
						<div class="default" style="height: 540px; overflow: scroll;">
							<table border="0" cellspacing="0" cellpadding="0"
								class="defaultTable defaultTable2" id="payCatBody">
							</table>
						</div>
						<!--pages S-->
						<div id="pageNoDiv"></div>
						<!-- pages E -->
					</div>
				</div>
			</div>
		</div>
		<!--main-->
	</div>

	<!-- 编辑缴费信息模态框 -->
	<div class="modal fade" id="editPayCatModal" role="dialog">
		<div class="modal-dialog" role="document" >
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">修改缴费<span></span></h4>
				</div>
				<div class="modal-body-title">
					<form class="form-horizontal" role="form">
						<div class="form-group">
							<label for="editSysNoticeTitle" class="control-label">姓名</label>
							<div style="width: 70%;">
								<input type="text" class="form-control" id="editPayCatUserName">
							</div>
							<label for="editPayCatType" class="control-label">缴费类型</label>
							<div style="width: 70%;">
								<select id="selectcatEdit" class="selectcat">
								</select>
							</div>
						</div>
						<div class="form-group">
							<label for="editdCostTypeAmount" class="control-label">缴费金额</label>
							<div style="width: 70%;">
								<textarea class="form-control" rows="3" id="editdCostTypeAmount"></textarea>
							</div>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button id="saveEditPayCatBtn" type="button"
						class="btn btn-primary">更新</button>
				</div>
			</div>
		</div>
	</div>

	<!-- 添加缴费信息管理模态框 -->
	<div class="modal fade" id="createPayCatModal" role="dialog">
		<div class="modal-dialog" role="document" >
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">添加缴费<span></span></h4>
				</div>
				<div class="modal-body-title">
					<form class="form-horizontal" role="form">
						<input id="update" type="hidden">
						<div class="form-group">
							<label for="addPayCatUserName" class="control-label">姓名</label>
							<div style="width: 70%;">
								<input style="margin-bottom: 15px;" type="text"
									class="form-control" id="addPayCatUserName">
							</div>
							<label for="addPayCatType" class="control-label">缴费类型</label>
							<div style="width: 70%;">
								<select id="selectcat" class="selectcat">
								</select>
							</div>
						</div>
						<div class="form-group">
							<label for="addCostTypeAmount" class="control-label">缴费金额</label>
							<div style="width: 70%;">
								<input type="number" class="form-control" id="addPayCatAmount">
							</div>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button id="saveCreatePayCatBtn" type="button"
						class="btn btn-primary">保存</button>
				</div>
			</div>
		</div>
	</div>


	<!-- 导入缴费模态窗口 -->
	<div class="modal fade" id="importPayCatModal" role="dialog">
		<div class="modal-dialogM  modal-content-t" role="document">
			<div class="modal-content-moda">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">×</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">导入缴费信息</h4>
				</div>
				<div class="modal-body" style="height: 350px; overflow: scroll;">
					<div style=" margin-left: 20px;">
						请选择要上传的文件：<small style="color: gray;">[仅支持.xls或.xlsx格式]</small>
					</div>
					<div style="margin: 20px 0 20px 20px;">
						<input type="file" id="activityFile">
					</div>
					<div style=" margin-left: 20px;">
						<h3>重要提示</h3>
						<ul>
							<li>给定文件的第一行将视为字段名。</li>
							<li>请确认您的文件大小不超过5MB。</li>
							<li>从XLS/XLSX文件中导入全部重复记录之前都会被忽略。</li>
							<li>复选框值应该是1或者0。</li>
							<li>日期值必须为MM/dd/yyyy格式。任何其它格式的日期都将被忽略。</li>
							<li>日期时间必须符合MM/dd/yyyy hh:mm:ss的格式，其它格式的日期时间将被忽略。</li>
							<li>默认情况下，字符编码是UTF-8 (统一码)，请确保您导入的文件使用的是正确的字符编码方式。</li>
							<li>建议您在导入真实数据之前用测试文件测试文件导入功能。</li>
						</ul>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button id="importActivityBtn" type="button"
						class="btn btn-primary">导入</button>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
	/* 页面权限设置 */
		var type = '${type}';
	    if (type == 2) {
	        var htmlStr = '';
	        htmlStr += '<select id="select_head" class="select_head" onchange="propertyManagementSysNotice(this.value)"><option>全部小区</option><c:forEach var="item" items="${pmd }" varStatus="status"><option value="${item.id }">${item.management_department_name }</option></c:forEach></select>';
	        $(".select_dis").html(htmlStr);
	    }
		if (type == 3) {
			var htmlStr='';
	        $(".t_2_q_cz").css("display", "block");
	         htmlStr +='<button id="createOwnerPayCatBtn" type="button" class="btn btn-primary"><span class="glyphicon glyphicon-plus"></span>添加缴费信息</button>';
	         htmlStr +='<button id="importOwnerPayCatBtn" type="button" class="btn btn-default" data-toggle="modal"data-target="#importActivityModal"><span class="glyphicon glyphicon-import"></span> 导入</button>';
	         htmlStr +='<button id="exportOwnerPayCatBtn" type="button" class="btn btn-default"><span class="glyphicon glyphicon-export"></span> 导出</button>';
	         $(".operateBtn1").html(htmlStr)
		}
		var token = '${token}'
		var take=1;
		
		/* 获取下拉框value */
	    function propertyManagementSysNotice(val){
	    	temp = 0;
	 		pagenum = 1;
			var val;
			if(take==1)
				{
					display(1,10);
				}
			else if(take==0)
				{
					unPayCatInfo(1,10);
				}
	    };
		/* 修改缴费信息 */
		function editOwnerPayCat(id)
		{      
			$(".modal-title>span").html("");
			$("#update").val(id); 
			$.ajax({
				url : "editPayCatInfo",
				data : {
					"id" : id
				},
				dataType : "json",
				type : "post",
				success : function(data) {
					if(data.code==200){
					 $("#editPayCatUserName").val(data.data.owner_name);
					 $("#editPayCatType").val(data.data.costType.pay_name);
					 $("#editdCostTypeAmount").val(data.data.amount);
					 $("#editPayCatModal").modal("show");  
					}else
					{
						toastr.error("暂无任何信息!");
					}
				}
			}); 
		}
		
		 /* 删除缴费信息 */
	    function deleteOwnerPayCat(id) {
	        Ewin.confirm({
	            message: "确认要删除便民信息吗？"
	        }).on(function(e) {
	            if (!e) {
	                return;
	            }
	            $.ajax({
	                url: "deleteUnPayCatInfo",
	                data: {
	                    "id": id
	                },
	                dataType: "json",
	                type: "post",
	                success: function(data) {
	                    if (data.code == "200") {
	                        var rowsPerPage = $("#pageNoDiv").bs_pagination('getOption', 'rowsPerPage');
	                        temp = (pagenum - 1) * rowsPerPage;
	                        unPayCatInfo(pagenum, $("#pageNoDiv").bs_pagination('getOption', 'rowsPerPage'));
	                    } else {
	                        toastr.error("信息删除失败!");
	                    }
                }
            });
        });
    }
		/* 未缴费列表 */
		var temp = 0;
		var pagenum = 1;
		/* take = 0; */
		function unPayCatInfo(pageIndex, pageSize) {
			var searchText = $("#searchText").val();
			 var pmid=$("#select_head").val();
				if(pmid=="全部小区")
					{
						pmid="";
					} 
			var JsonString = {
				"token" : token,
				"pageIndex" : pageIndex,
				"pageSize" : pageSize,
				"owner_name" : searchText,
				"pmid":pmid
			};
			var json = JSON.stringify(JsonString);
        $.ajax({
            url: 'queryOwnerUnPayCatInfo',
            data: json,
            contentType: 'application/json',
            dataType: 'json',
            type: 'post',
            success: function(data) {
            	if(data.code==200){
                var htmlStr = "";
                $.each(data.data.obj,
                function(index, obj) {
                    temp += 1;
                    htmlStr += '<tr>';
                    htmlStr += '<td id="ownerId" class="t_1_q" value=' + obj.id + '>' + temp + '</td>';
                    htmlStr += '<td class="t_2_q">' + obj.owner_name + '</a></td>';
                    htmlStr += '<td class="t_2_q">' + obj.costType.pay_name + '</td>';
                    htmlStr += '<td class="t_1_q">' + obj.amount + '</td>';
                    htmlStr += '<td class="t_2_q">' + obj.createFormatTime + '</td>';
                    if (type == 3) {
                        htmlStr += '<td class="t_2_q"><div class="btn_gonggao">' + '<button onclick="editOwnerPayCat(' + obj.id + ')" type="button" class="btubf btn-default"><span class="glyphicon glyphicon-pencil"></span> 修改</button>' + '<button onclick="deleteOwnerPayCat(' + obj.id + ')" type="button" class="btubf btn-danger"><span class="glyphicon glyphicon-minus"></span> 删除</button></div></td>';
                    }
                    htmlStr += '</tr>';
                });

                $("#payCatBody").html(htmlStr);
                $("#payCatBody tr:odd").addClass("active");
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
		display(1, 10);
		/* 已缴费列表 */
		var temp1 = 0;
		var pagenum1 = 1;
		/* take= 1; */
		function display(pageIndex, pageSize) {
			 var pmid=$("#select_head").val();
				if(pmid=="全部小区")
					{
						pmid="";
					} 
			var searchText = $("#searchText").val();
			var JsonString = {
				"token" : token,
				"pageIndex" : pageIndex,
				"pageSize" : pageSize,
				"owner_name" : searchText,
				"pmid":pmid
			};
			var json = JSON.stringify(JsonString);
        $.ajax({
            url: 'queryOwnerPayCatInfo',
            data: json,
            contentType: 'application/json',
            dataType: 'json',
            type: 'post',
            success: function(data) {
            	if(data.code==200){
                var htmlStr = "";
                var costTypeHtml = "";
                $.each(data.data.obj, function(index, obj) {
                    temp1 += 1;
                    //alert(temp1);
                    htmlStr += '<tr>';
                    htmlStr += '<td id="ownerId" class="t_1_q" value=' + obj.id + '>' + temp1 + '</td>';
                    htmlStr += '<td class="t_2_q">' + obj.owner_name + '</a></td>';
                    htmlStr += '<td class="t_2_q">' + obj.costType.pay_name + '</td>';
                    htmlStr += '<td class="t_1_q">' + obj.famount + '</td>';
                    htmlStr += '<td class="t_2_q">' + obj.createFormatTime + '</td>';
                    htmlStr += '<td class="t_2_q">' + obj.finishFormatTime + '</td>';
                    htmlStr += '</tr>';
                });

                $("#payCatBody").html(htmlStr);
                $("#payCatBody tr:odd").addClass("active");
                
                $.each(data.costTypeList,
                function(index, obj) {
                    costTypeHtml += "<option value=" + obj.pay_item + ">" + obj.pay_name + "</option>";
                });

                $(".selectcat").html(costTypeHtml);

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
                        temp1 = (pageObj.currentPage - 1) * (pageObj.rowsPerPage);
                        display(pageObj.currentPage, pageObj.rowsPerPage);
                        pagenum1 = pageObj.currentPage;
                    }
                });
            }else{
            	toastr.error("暂无任何信息!");
            }
       	 }
        });
    }

    $(function() {
        /* 弹出缴费信息模态框*/
        $("#createOwnerPayCatBtn").click(function() {
            $("#createPayCatModal").modal("show");
            $(".modal-title>span").html("");
        });
        /* 录入信息保存 */
        $("#saveCreatePayCatBtn").click(function() {
            var id = $("#update").val();
            var userName = $("#addPayCatUserName").val();
            var payCatType = $("#selectcat").val();
            var payCatAmount = $("#addPayCatAmount").val();

            if (userName == '') {
                $(".modal-title>span").html("必须输入姓名!");
                return false
            } else if (payCatAmount == '') {
            	$(".modal-title>span").html("必须输入缴费金额!");
                return false
            } else {
            	$(".modal-title>span").html("");
            }
            $.ajax({
                url: 'ownerPayCatEnter',
                data: {
                    "token": token,
                    "owner_name": userName,
                    "pay_item": payCatType,
                    "amount": payCatAmount
                },
                dataType: "json",
                type: "post",
                success: function(data) {
                    if (data.code == "200") {
                        $("#addPayCatUserName").val();
                        $("#addPayCatType").val();
                        $("#addPayCatAmount").val();
                        var rowsPerPage = $("#pageNoDiv").bs_pagination('getOption', 'rowsPerPage');
                        temp = (pagenum - 1) * rowsPerPage;
                        unPayCatInfo(pagenum, $("#pageNoDiv").bs_pagination('getOption', 'rowsPerPage'));
                        $("#createPayCatModal").modal("hide");
                    } else {
                    	toastr.error("信息添加失败!");
                        $("#createPayCatModal").modal("show");
                    }
                }
            })
        });

        /* 保存编辑信息修改 */
        $("#saveEditPayCatBtn").click(function() {
            var id = $("#update").val();
            var editPayCatType = $("#selectcatEdit").val();
            var payCatName=$("#editPayCatUserName").val();
            var editdCostTypeAmount = $("#editdCostTypeAmount").val();
            
            if (payCatName == '') {
                $(".modal-title>span").html("必须输入姓名!");
                return false
            } else if (editdCostTypeAmount == '') {
            	$(".modal-title>span").html("必须输入缴费金额!");
                return false
            } else {
            	$(".modal-title>span").html("");
            }
            $.ajax({
                url: "saveEditUnOwnerPayCat",
                data: {
                    "id": id,
                    "owner_name": payCatName,
                    "pay_item": editPayCatType,
                    "amount": editdCostTypeAmount
                },
                dataType: "json",
               	type:'post',
                success: function(data) {
                    if (data.code == "200") {
                        var rowsPerPage = $("#pageNoDiv").bs_pagination('getOption', 'rowsPerPage');
                        temp = (pagenum - 1) * rowsPerPage;
                        unPayCatInfo(pagenum, $("#pageNoDiv").bs_pagination('getOption', 'rowsPerPage'));
                        $("#editPayCatModal").modal("hide");
                    } else {
                    	toastr.error("信息更新保存失败!");
                        $("#editPayCatModal").modal("show");
                    }
                }
            });
        });

        /* 导入缴费信息*/
        $("#importOwnerPayCatBtn").click(function() {
            $("#importPayCatModal").modal("show");
        });

        /* 已缴费列表 */
        $("#queryOwnerPayCatBtn").click(function() {

            var tbody = '<tbody>' + +'<tr>' + '	<th class="t_1_q">编号ID</th>' + '	<th class="t_2_q">姓名</th>' + '	<th class="t_2_q">费用类型</th>' + '	<th class="t_1_q">金额(元)</th>' + '	<th class="t_2_q">录入时间</th>' + '	<th class="t_2_q">缴费时间</th>' + '</tr>' + '</tbody>';
            $(".defaultTable").html(tbody);
            $("#payCatBody").html('');
            take = 1;
            temp1 = 0;
            pagenum1 = 1;
            display(1, 10)
        });

        /* 未交费列表 */
        $("#queryUnOwnerPayCatBtn").click(function() {

            var tbody = '<tbody>' + +'<tr>' + '	<th class="t_1_q">编号ID</th>' + '	<th class="t_2_q">姓名</th>' + '	<th class="t_2_q">费用类型</th>' + '	<th class="t_1_q">金额(元)</th>' + '	<th class="t_2_q">录入时间</th>' + '	<th class="t_2_q_cz">操作</th>' + '</tr>' + '</tbody>';
            $(".defaultTable").html(tbody);
            $("#payCatBody").html('');
            if (type == 3) {
                $(".t_2_q_cz").css("display", "block");
            }
            take = 0;
            temp = 0;
            pagenum = 1;
            unPayCatInfo(1, 10);
        });

        /* 搜索 */
        $("#searchBtn").click(function() {
            if (take == 0) {
                var rowsPerPage = $("#pageNoDiv").bs_pagination('getOption', 'rowsPerPage');
                temp = (pagenum - 1) * rowsPerPage;
                unPayCatInfo(1, $("#pageNoDiv").bs_pagination('getOption', 'rowsPerPage'));
            } else if (take == 1) {
                var rowsPerPage = $("#pageNoDiv").bs_pagination('getOption', 'rowsPerPage');
                temp1 = (pagenum1 - 1) * rowsPerPage;
                display(1, $("#pageNoDiv").bs_pagination('getOption', 'rowsPerPage'));
            }
        });
    })
</script>
</body>
</html>