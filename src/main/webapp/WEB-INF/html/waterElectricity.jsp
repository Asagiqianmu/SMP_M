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
<title>基价管理</title>
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
<script type="text/javascript" src="static/js/toastr.js"></script>
<script type="text/javascript" src="static/js/bootbox.js"></script>
</head>
<body style="background: #f6f5fa;">
<div class="super-content RightMain" id="RightMain">
    <!--header-->
    <div class="superCtab">
        <div class="ctab-title clearfix">
            <h3>基价管理</h3>
			<!--选择小区下拉框 -->
			<div class="select_dis"></div>
        </div>
       	<!-- 导航栏 ↓-->
        <div class="ctab-Main-back">
        <div class="ctab-head">
	        <div style="width: 719px;">
	        	<div class="head_select">
	        	<select id="test" class="test">
	        	</select>
	        	</div>
	        	<div class="head_price">单价</div>
	        	<input class="head_input" id="head_input" onblur="upblur(head_input)">
	        	<div class="head_unit">单位</div>
	        	<input class="head_input2" id="head_input2"><span class="head-cue">(例如:元/月,仅需填月)</span>
	        	<button class="add-price" id="add-price">新增</button>
	        	<div class="prompt">
	        	<span></span>
	        	</div>
	        </div>
        </div>
            <div class="ctab-Mian-cont-water">
<!--                 <div class="water-price">
	 					<div class="water-text">水 费</div>
	 					<input class="inputprice" id="inputprice" value="1.54" onfocus="editprice('but-price')">
	 					<div class="price-text">元/吨</div>
 						<button id="but-price" class="but-price" disabled="disabled" onclick="aclick(this)">修改</button>
 				</div> -->
 				<div class="Table-price"></div>
 				</div>
        </div>
    </div>
</div>
</body>
</html>

<script type="text/javascript">
/* 页面权限设置 */
var type = '${type}';
var temp="";

/* 添加基价类型以及数据*/
$("#add-price").click(function(){
	
	$('#test').val();
	var val=$('#test option:selected').val();
	var text=$('#test option:selected').text();
	var price=$("#head_input").val();
	var unit=$("#head_input2").val();
	var flag = true;
	if(price==""){
		$(".prompt>span").html("单价不能为空!");
		return false;
	}else if(unit==""){
		$(".prompt>span").html("单位不能为空!");
		return false;
	}else{
		$(".prompt>span").html("");
	}
 	$.each(temp, function(index,obj) {
 		if(text==obj.payName){
 			flag = false;
 		}
	});
 	 if(flag){
 		var jsonString={
				"token":token,
				"price":price,
				"costTypeId":val,
				"unit":unit
		};
		var json = JSON.stringify(jsonString)
		$.ajax({
			url:'insertWaterElectricityPrice',
			data:json,
			contentType:"application/json",
			dataType:"json",
			type:"post",
			success:function(data){
				if(data.code==200){
					updata();
					toastr.success("信息添加成功!");
				}
				else{
					toastr.error(data.mag);
				}
			}		
		});
 	 }else{
 		toastr.error("当前名目已存在!");
 	 }
})

function editprice(name){
	$(name).attr('disabled',false);
}

function upblur(name){
		name.value = name.value.replace(/[\.]$/, '');
		name.value = name.value.replace(/(.*)\.([\d]{2})(\d*)/g,'$1.$2');
		name.value = Number(name.value).toFixed(2);
	    var logNum = name.value.toString();
	    if(logNum.match(/\./g) != null){
	        var integerNum = parseInt(logNum).toString().replace(/\d(?=(\d{3})+$)/g,'$&,');
	        var decimalNum = '.' + logNum.replace(/(.*)\.(.*)/g,'$2');
	        $(".logbox").innerHTML = integerNum + decimalNum;
	    }else{
	        $(".logbox").innerHTML = logNum.replace(/\d(?=(\d{3})+$)/g,'$&,');
	    } 
}

/* 页面js执行入口 */
$(function(){
	
	updata();
	
})
/* 查询基价内容 */
	function updata(){
	var precapital;
	var jsonString = {
           "token": token,
    };

    var json = JSON.stringify(jsonString);
    $.ajax({
        url: "queryWaterElectricityPrice",
        data: json,
        contentType: "application/json",
        dataType: "json",
        type: "post",
       
        success: function(data) {
        	if(data.code==200){
        	var htmlStr = '';
        	var addhtml='';
            $.each(data.data, function(index,obj) {
            	/*  */
            	temp=data.data;
            	var price=obj.price;
            	
            	price = price.toString().replace(/[\.]$/, '');
            	price = price.toString().replace(/(.*)\.([\d]{2})(\d*)/g,'$1.$2');
            	price = Number(price).toFixed(2);
        	    var logNum = price.toString();
        	    if(logNum.match(/\./g) != null){
        	        var integerNum = parseInt(logNum).toString().replace(/\d(?=(\d{3})+$)/g,'$&,');
        	        var decimalNum = '.' + logNum.replace(/(.*)\.(.*)/g,'$2');
        	        $(".logbox").innerHTML = integerNum + decimalNum;
        	    }else{
        	        $(".logbox").innerHTML = logNum.replace(/\d(?=(\d{3})+$)/g,'$&,');
        	    }
            	
	           	htmlStr += '<div class="water-price">'
            	htmlStr +='<div class="water-text" id="costTypeId">'+obj.payName+'</div>'
            	htmlStr +='<input class="inputprice" id="inputprice'+obj.id+'" value='+price+' onfocus="editprice(but_price'+obj.id+')" onblur="upblur(inputprice'+obj.id+')">'
            	htmlStr +='<div class="price-text" id="description">元/'+obj.unit+'</div>'
            	htmlStr += '<button id="but_price'+obj.id+'" class="but-price" disabled="disabled" onclick="fix('+obj.id+')">修改</button></div>';
            	
            	$(".Table-price").html(htmlStr);
            })
            
            $.each(data.costTypeList,function(index,objcet){
  				addhtml+='<option value="'+objcet.pay_item+'">'+objcet.pay_name+'</option>';
              	$(".test").html(addhtml);
            	/* temp = obj.pay_item */
            })
        	
        }else{
        	toastr.error("暂无任何信息!");
        }
    }
    })
	}

function fix(id){
	bootbox.confirm("是否确认修改当前基价?", function(result)
		{
		console.log(result);
		if(result)
			{
			 $("#but_price"+id).attr('disabled',true); 
			var editinputprice = $("#inputprice"+id).val();
			var jsonString = {
			        "price": editinputprice,
			        "id":id
			    };

			    var json = JSON.stringify(jsonString);
			    $.ajax({
			        url: "updateWaterElectricityPrice",
			        data: json,
			        contentType: "application/json",
			        dataType: "json",
			        type: "post",
			        success: function(data) {
			        	if (data.code == "200") {
			        		toastr.success("信息修改成功!");
		                } else {
		                	toastr.error("信息修改失败!");
		                }	
			        }
			    }) 
			}
		});
}


</script>