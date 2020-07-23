<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<%@ include file="../../inc/header.jsp" %>
<%@ include file="../../init.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>数字证书服务网</title>
    <%--<link type="text/css" href="<%=path%>/css/css.css" rel="stylesheet" />--%>
    <link type="text/css" href="<%=path%>/css/bootstrap.min.css" rel="stylesheet" />
    <link type="text/css" href="<%=path%>/css/tableSorter/style.css" rel="stylesheet" />
    <link type="text/css" href="<%=path%>/css/multiple-select.css" rel="stylesheet" />
    <%-- 		<script type="text/javascript" src="<%=path%>/js/validate/jquery.validate.js"></script>
            <script type="text/javascript" src="<%=path%>/js/validate/additional-methods.js"></script>
            <script type="text/javascript" src="<%=path%>/js/validate/messages_zh.js"></script> --%>


    <%--<script type="text/javascript" src="<%=path%>/js/example.js"></script>--%>
    <script type="text/javascript" src="<%=path%>/js/multiple-select.js"></script>
    <script type="text/javascript" src="<%=path%>/js/jquery.tablesorter.js"></script>
    <script type="text/javascript" src="<%=path%>/js/datepicker/WdatePicker.js"></script>
    <link type="text/css" href="<%=path%>/css/main.css" rel="stylesheet" />
    <%--<link type="text/css" href="<%=path%>/js/themes/jquery-ui.min.css"--%>
          <%--rel="stylesheet" />--%>
    <%@ include file="../../power.jsp" %>
    <style type="text/css">
        .checkText{
            height: 25px !important;
            width: 154px !important;
            border: 1px solid #707070;
        }
        .selectText{
            width: 140px!important;
        }
        .tdiv_a_1 {
            min-width: 165px;
            padding-right: 15px!important;
        }
        /*.checkText1{*/
            /*height: 35px !important;*/
            /*width: 300px !important;*/
            /*border: 1px solid #707070;*/
        /*}*/
        body{
            background-color: #e6e6e6;
        }

        .ms-choice {
            height: 16px !important;
        }
        .checker{
            text-align: center;
        }

        /*浏览器窗口缩放-防止表格超出*/
        .table .table_td {
            min-width: 0px!important;
        }
    </style>
    <script type="text/javascript">
        $(function() {
            //跳转导航页面
            changeDaoHang('订单查询');
        })
    </script>
</head>
<body class="page_content">


<div class="tdiv" >
    <div class="tdiv_title"><p>查询条件</p></div>
    <div class="tdiv_a_z">
        <div class="tdiv_a_1" >订购地区：</div>
        <div class="tdiv_a_5">
        <div class="tdiv_a_4" >
            <select style="padding: 0px 0px;height: 24px;" id="areaProvice" name="provinceCode" class="classData form-control selectText">
                <option selected value="">请选择</option>
                <c:forEach items="${area}" var="areas">
                    <option value="${areas.id}">${areas.name}</option>
                </c:forEach>
            </select>
        </div>
        <div class="tdiv_a_4">
            <select style="padding: 0px 0px;height: 24px;" hidden  id="areaCity" name="cityCode" class="classData form-control selectText">
                <option selected value="">请选择</option>
                <c:forEach items="${city}" var="citys">
                    <option value="${citys.id}">${citys.name}</option>
                </c:forEach>
            </select>
        </div>
        </div>
        <div class="tdiv_a_1" >订购单位：</div>
        <div class="tdiv_a_5" style="width: 160px!important;">
            <input type="text" class="checkText form-control col-md-10 col-xs-4 classData" name="orderUnitName"/>
        </div>
        <div class="tdiv_a_1" >订购人手机号：</div>
        <div class="tdiv_a_4" >
            <input type="text"  class="checkText form-control col-md-10 col-xs-4 classData selectText" name="purchaserMobile"/>
        </div>
    </div>
    <div class="tdiv_a_z">
        <div class="tdiv_a_1" >证书类型名称：</div>
        <div class="tdiv_a_5 appendProductName">

            <%--<input type="text" class="checkText form-control col-md-10 col-xs-4 classData" style="width: 148px!important;" name="productName"/>--%>
        </div>
        <div class="tdiv_a_1" >订购总数：</div>
        <div class="tdiv_a_5" style="width: 160px!important;">
            <input type="text" class="checkText form-control col-md-10 col-xs-4 classData"  name="productNumber"/>
        </div>
        <div class="tdiv_a_1" >订购金额：</div>
        <div class="tdiv_a_4">
            <input type="text" class="checkText form-control col-md-10 col-xs-4 classData selectText"  name="productTotalAmount"/>
        </div>
    </div>
    <div class="clearfloat"></div>
    <div class="tdiv_a_z">
        <div class="tdiv_a_1" >订单类型：</div>
        <div class="tdiv_a_5">
            <select style="padding: 0px 0px;height: 10px!important;" class="classData  form-control orderTypeScope selectText"  multiple="multiple">
                <option value="0">订购</option>
                <option value="1">代购</option>
                <option value="2">项目内</option>
                <option value="3">赠送</option>
                <option value="4">提前发货</option>
                <option value="5">合同已包含</option>
            </select>
        </div>
        <div class="tdiv_a_1" >下单时间：</div>
        <div class="tdiv_a_4">
            <select  id="orderDateType" name="orderDateType" style="padding: 0px 0px;" class="classData form-control selectText">
                    <option value="0">其他</option>
                    <option value="1">今天</option>
                    <option value="2">本周</option>
                    <option value="3">本月</option>
                    <option value="4">本季</option>
                    <option value="5">本年</option>
            </select>
            <input type="text" class="checkText form-control col-md-10 col-xs-4 classData"  name="orderDateStart" onclick="WdatePicker()" style="width: 134px!important;"/>
            <div class="col-md-1 col-xs-1" style="width: 45px;">
                到
            </div>
            <input type="text" class="checkText form-control col-md-10 col-xs-4 classData" name="orderDateEnd" onclick="WdatePicker()" style="width: 134px!important;"/>
        </div>
    </div>
    <div class="clearfloat"></div>
    <div class="tdiv_a_z">
        <div class="tdiv_a_1" >发票状态：</div>
        <div class="tdiv_a_5">
            <select style="padding: 0px 0px;height: 24px;" id="orderInvoiceStatus" name="orderInvoiceStatus" class="classData form-control selectText">
                <option selected value="">请选择</option>
                <option value="1">已开票</option>
                <option value="0">未开票</option>
                <option value="2">部分票据已开</option>
            </select>
        </div>
        <div class="tdiv_a_1" >订单状态：</div>
        <div class="tdiv_a_5" style="width: 160px!important;">
            <select style="padding: 0px 0px;height: 10px!important;" class=" classData checkText form-control orderStatusScope "  multiple="multiple">
                <option value="0">待受理</option>
                <option value="1">已受理待付款</option>
                <option value="7">待生产</option>
                <option value="2">生产中—待生产</option>
                <option value="2.1">生产中—生产中</option>
                <option value="2.2">生产中—生产完待发货</option>
                <option value="3">发运中</option>
                <option value="4">完成</option>
                <option value="5">退回</option>
                <option value="6">作废</option>
            </select>
        </div>
        <div class="tdiv_a_1" >开票方式：</div>
        <div class="tdiv_a_5">
            <select style="padding: 0px 0px;height: 24px;" id="needBeforeInvoice" name="needBeforeInvoice" class="classData form-control selectText">
                <option selected value="">请选择</option>
                <option value="1">先开票</option>
                <option value="0">后开票</option>
            </select>
        </div>
    </div>
    <div class="clearfloat"></div>
    <div class="tdiv_a_z">
        <div class="tdiv_a_1" >是否借转销：</div>
        <div class="tdiv_a_5">
            <select style="padding: 0px 0px;height: 24px;" id="borrowType" name="borrowType" class="classData form-control selectText">
                <option selected value="">请选择</option>
                <option value="0">否</option>
                <option value="1">是</option>
            </select>
        </div>
    </div>
    <div class="clearfloat"></div>
    <hr class="hr_style"/>
    <div class="neir_2">
        <div class="col-sm" style="text-align: center;">
            <input type="button" class="btn btn-info" style="cursor : pointer!important;" value="查询" id="basicInfoSearch"/>
            <input type="button" class="btn btn-info" style="cursor : pointer!important;" id="resetButton" value="重置"/>
        </div>
    </div>
	<div class="clearfloat"></div>
    <div class="tdiv_bottom"></div>
</div>

<div class="tdiv">

    <div class="tdiv_title"><p>订单信息</p></div>
    <div  style="" class="neir_1_kcgl neir_2_kcgl_bod2">

        <table class="table table-bordered table-hover rzgggl-table tablesorter" id="sortTable">
            <thead>
            <tr>
                <th style="width: 12px">
                    <div class="checker">
                        <span><input type="checkbox" onclick="selectAllPaper()" id="selectall"/></span>
                    </div>
                </th>
                <th width="4.8%">订单流水</th>
                <th>订购日期</th>
                <th>订购地区</th>
                <th>订购单位</th>
                <th>订购人姓名</th>
                <th>订购人手机号码</th>
                <th>产品名称</th>
                <th>订购数量</th>
                <th>订购金额（元）</th>
                <th>订单类型</th>
                <th>订单状态</th>
                <th>发票状态</th>
                <th>发货时间</th>
                <th>操作</th>
                <th> <div class="checker">留言</div></th>
            </tr>
            </thead>
            <tbody id="classDataView" style="width: 12px">
            </tbody>
        </table>

    </div>
    <div style="text-align: center;margin: 0 auto;">

            <%--<input type="button" style="margin-right: 5%;" class="tdiv_buttion" onclick="_putOrderStatus(2)" value="批量进入生产"/>--%>
            <input type="button" style="margin-right: 5%;" class="tdiv_buttion" onclick="_putOrderStatus(2.1)" value="批量下生产单"/>
            <input type="button" style="margin-right: 5%;" class="tdiv_buttion" onclick="_putOrderStatus(2.2)" value="批量完成生产"/>
            <input type="button" style="margin-right: 5%;" class="tdiv_buttion" onclick="_putOrderStatus(3)" value="批量完成发货"/>
            <%--<input type="button" style="margin-right: 5%;" class="tdiv_buttion" data-toggle="modal" data-target="#putOrderStatusExpressModal" value="批量完成发货"/>--%>
            <input type="button" style="margin-right: 5%;" class="tdiv_buttion" data-toggle="modal" data-target="#templateExcelModal"  value="数据导出"/>

    </div>


    <div class="control-group nav-fenye">
        <div class="pull-left nav-fenye"><span id="pageSizeInfo"></span>
            <select id="selectPageSizeId" class="nopad" style="width: 45px" onchange="selectPageSize(this)">
                <option selected="selected" value="20">20</option>
                <option value="10">10</option>
                <option value="5">5</option>
            </select> 条
        </div>
        <%--<nav aria-label="Page navigation" class="pull-right">--%>
            <%--<div id="pagination">--%>
                <%--<ul class="pagination">--%>
                <%--</ul>--%>
            <%--</div>--%>
        <%--</nav>--%>
        <div class="row-paginate">
            <ul id="pagination" class="pagination">
            </ul>
        </div>
    </div>
    <div class="clearfloat"></div>
</div>
<div class="modal fade" id="templateExcelModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog modal-lg" role="document">
        <div class="modal-content" style="width: 100%">
            <div class="modal-header">
                <button type="button" class="close invoiceDetailsClose" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>
                <h4 class="modal-title" id="myModalLabel" >数据导出</h4>
            </div>
            <div class="modal-body">
                <form id="expressForm1">
                    <input type="hidden" name="orderId" value="${orderId}"/>
                    <div class="tdiv_a_z">
                        <div class="tdiv_a_1">要导出的数据：</div>
                        <div class="tdiv_a_3">
                            <select name="exportSelect" id="exportSelect" style="width:165px;padding: 0px 0px;height: 24px;float: left;margin-right: 40px;" class="form-control">
                                <option value="0">全部查询结果数据</option>
                                <option value="1">选中数据</option>
                            </select>
                        </div>
                    </div>
                    <div class="tdiv_a_z">
                        <div class="tdiv_a_1">选择模板：</div>
                        <div class="tdiv_a_3">
                            <select name="templateExcelId" id="templateExcelId" style="width:300px;padding: 0px 0px;height: 24px;float: left;margin-right: 40px;" class="form-control">
                                <option value="1">财务开票单</option>
                                <option value="0">生产任务单</option>
                                <option value="2">产品快递面单</option>
                                <c:forEach items="${templateExcelList}" var="template">
                                    <option value="${template.id}">${template.templateName}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="tdiv_a_3"><input type="button" class="btn btn-info" onclick="_exportExcelTemplate()" value="导出"/></div>
                    </div>
                    <%--<div class="clearfloat"></div>--%>
                    <%--<div class="tdiv_a_z">--%>
                        <%--<div class="tdiv_a_1"></div>--%>
                        <%----%>
                    <%--</div>--%>
                    <div class="clearfloat"></div>
                </form>
            </div>

            <div class="modal-footer"></div>
        </div>
    </div>
</div>
<div class="modal fade" id="putOrderStatusExpressModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog modal-lg" role="document">
        <div class="modal-content" style="width: 100%">
            <div class="modal-header">
                <button type="button" class="close invoiceDetailsClose" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>
                <h4 class="modal-title" id="myModalLabel" >批量完成发货</h4>
            </div>
            <div class="modal-body">
                <form id="expressForm2">
                    <input type="hidden" name="orderId" value="${orderId}"/>

                    <div class="tdiv_a_z">
                        <div class="tdiv_a_1">货品说明：</div>
                        <div class="tdiv_a_3">
                            <select name="expressType" id="expressType" style="width:200px;padding: 0px 0px;height: 24px;float: left;margin-right: 40px;" class="form-control">
                                <option value="1">发票</option>
                                <option value="2">证书</option>
                                <option value="3" selected>证书和发票</option>
                            </select>
                        </div>
                    </div>
                    <div class="tdiv_a_z">
                        <div class="tdiv_a_1">选择发货日期：</div>
                        <div class="tdiv_a_3">
                            <input type="text" class="form-control col-md-10 col-xs-4 classData" style="width: 202px" id="deliveryDate" onclick="WdatePicker()" value="<fmt:formatDate value="<%= new java.util.Date()%>" type="date" pattern="yyyy-MM-dd"/>">
                        </div>
                        <div class="tdiv_a_3"><input type="button" class="btn btn-info" onclick="_postPutOrderExcpress(3)" value="提交"/></div>
                    </div>
                    <%--<div class="clearfloat"></div>--%>
                    <%--<div class="tdiv_a_z">--%>
                        <%--<div class="tdiv_a_1"></div>--%>
                        <%----%>
                    <%--</div>--%>
                    <div class="clearfloat"></div>
                </form>
            </div>

            <div class="modal-footer"></div>
        </div>
    </div>
</div>
<script type="text/javascript">
    var currentPageIndex = 1;
    var limit;
    var dataCount = 0;
    var pageSize = 20;
    $(function () {
        var appendProductName = $(".appendProductName");
        appendProductName.prepend(appendProductNames());
        window.orderTypeArray = [];
        window.orderStatusArray = [];
        $(".orderTypeScope,.orderStatusScope").change(function () {
            if($(this).hasClass("orderTypeScope")){
                orderTypeArray = $(this).val();
            }else if($(this).hasClass("orderStatusScope")){
                orderStatusArray = $(this).val();
            }

            var valueText = "";
            for(var i = 0;i<this.selectedOptions.length;i++){
                valueText += this.selectedOptions[i].innerText+",";
            }
            $(".ms-choice").attr("title",valueText);
        }).multipleSelect({
            width:'100%',
            filter:true
        });

        $("#orderDateType").change(function () {
            if($(this).val()==0){
                $(this).siblings().show();
            }else {
                $(this).siblings().hide();
            }

        })

        //基本信息搜索
        $("body").on("click","#basicInfoSearch",function () {
            currentPageIndex = 1;
            openLoading();
            parserAsyncClassDataFormData();
        })
//        //全量信息搜索
//        $("body").on("click","#basicInfoSearchAll",function () {
//            currentPageIndex = 1;
//            openLoading();
//            parserAsyncClassDataFormDataAll();
//        })

        //分页查询
//        $("body").on("click", ".pagination li.item a", itemOfpageViewOnSelected);
        $("body").on("click", ".pagination li a", itemOfpageViewOnSelected);

        parserAsyncClassDataFormData();

        //省更改
        $("#areaProvice").change(function () {
            var areaProviceId = $(this).val();
            if(areaProviceId==undefined||areaProviceId==""){
                $("#areaCity").val("");
                $("#areaCity").hide();
                return false;
            }
            url = "<%=path%>/common/showCity.do?areaProviceId="+areaProviceId;
            Ajax.jsonGet(url,function (cityVal) {
                if (cityVal == undefined) {
                    alert("未有该分类");
                } else {
                    $("#areaCity").empty();
//						var cityVal = JSON.parse(city);
					$("#areaCity").append("<option value=''>全部 </option>")
                    for (var i = 0; i < cityVal.length; i++) {
                        $("#areaCity").append("<option value='" + cityVal[i].id + "'>" + cityVal[i].name + "</option>")
                    }
                    $("#areaCity").show();
                }
            })
            closeLoading();
        })

        //文字提醒时间间隔
        setInterval("changeColor()",200);

      	//输入框的重置按钮
		$("#resetButton").on("click",function(){
			$(":input").not(":radio,:checkbox,:submit,:reset,:button,:hidden")
				.val('').removeAttr("checked").removeAttr("selected");
			//清除多选下拉
			orderTypeArray = [];
			orderStatusArray = [];
			$(".ms-choice span").html('');
			$("li[class='selected']").removeClass();
			$("input[data-name='selectItem']").removeAttr("checked");
		});


    })
    //消息提醒文字效果
    function changeColor() {
        var color = "#f00|#0f0|#00f|#880|#808|#088|yellow|green|blue|gray";
        color = color.split("|");
        var blink = document.getElementsByClassName("blink")
        for(var i=0; i<blink.length;i++){
            blink[i].style.color=color[parseInt(Math.random()*color.length)];
        }
    }
    function itemOfpageViewOnSelected() {
        openLoading();
        var p = $(this).attr("data");
        currentPageIndex = parseInt(p);
        computePagination();
        parserAsyncClassDataFormData();
    }
    function selectPageSize(data) {
        var size = $("#selectPageSizeId option:selected").val();
        pageSize = size;
        currentPageIndex = 1;
        parserAsyncClassDataFormData();
    }
    //全量查询
    <%--function  parserAsyncClassDataFormDataAll(){--%>
        <%--var json = {OrderQuerySearcher: {}};--%>
        <%--json.OrderQuerySearcher["pageCount"] = pageSize;--%>
        <%--json.OrderQuerySearcher["offset"] = (currentPageIndex - 1) * pageSize;--%>
        <%--$.ajax({--%>
            <%--url: "<%=path%>/commonCompanyOrderQuery/showOrderPage.do",--%>
            <%--type: "POST",--%>
            <%--data: json.OrderQuerySearcher,--%>
            <%--success: function(datas){--%>
                <%--closeLoading();--%>
<%--//                var datas = JSON.parse(data);--%>
                <%--limit = datas.limit;--%>
                <%--limit.total = datas.total;--%>
                <%--if (datas.data) {--%>
                    <%--dataCount = datas.data.length;--%>
                <%--} else {--%>
                    <%--dataCount = 0;--%>
                <%--}--%>
                <%--computePagination(); //分页--%>
                <%--classDataViewAdapter(datas);  //数据--%>
                <%--$("#pageSizeInfo").html("总共"+datas.total+"条，每页显示");--%>

                <%--//全选按钮重置--%>
                <%--$("#selectall").prop("checked",false);--%>
                <%--closeLoading();--%>

            <%--}--%>
        <%--})--%>
    <%--}--%>
    // 查询
    function  parserAsyncClassDataFormData(){
        var json = {OrderQuerySearcher: {}};
        $.each($(".classData"), function (index, item) {
            var key = $(item).attr("name"), 
            _val = $(item).val();
            json.OrderQuerySearcher[key] = _val;
        });
        json.OrderQuerySearcher["orderTypeArrays"] = JSON.stringify(orderTypeArray);
        json.OrderQuerySearcher["orderStatusArrays"] = JSON.stringify(orderStatusArray);
        json.OrderQuerySearcher["pageCount"] = pageSize;
        json.OrderQuerySearcher["offset"] = (currentPageIndex - 1) * pageSize;
        $.ajax({
            url: "<%=path%>/commonCompanyOrderQuery/showOrderPage.do",
            type: "POST",
            data: json.OrderQuerySearcher,
            success: function(datas){
                closeLoading();
//                var datas = JSON.parse(data);
                limit = datas.limit;
                limit.total = datas.total;
                if (datas.data) {
                    dataCount = datas.data.length;
                } else {
                    dataCount = 0;
                }
                computePagination(); //分页
                classDataViewAdapter(datas);  //数据
//                var pageCount = Math.floor(limit.total % limit.count == 0 ? limit.total / limit.count : limit.total / limit.count + 1);
                $("#pageSizeInfo").html("总共"+limit.total+"条，每页显示");

                //全选按钮重置
                $("#selectall").prop("checked",false);
                closeLoading();

            }
//            ,error : function(XMLHttpRequest, textStatus, errorThrown) {
//                closeLoading();
//                alert("加入失败" + errorThrown);
//            }
        })
    }
    function classDataViewAdapter(datas) {
        var view = $("#classDataView");
        view.empty();
        var i = 0;
        var param = "";
        for (var data in datas.data) {
            var item = datas.data[data];
            param += Objects.format("<tr>");
            param += Objects.format("<td class=\"table_td\"><div class='checker'><span> <input type='checkbox' class='orderCheckbox' value='{0}'/></span></div></td>",item.orderId);
            param += Objects.format("<td class=\"table_td\">{0}</td>",item.flowId!=null?item.flowId:"");
            param += Objects.format("<td class=\"table_td\">{0}</td>",item.orderDate!=null?getLocalTimeYYYYMMDD(item.orderDate):"");
            param += Objects.format("<td class=\"table_td\"><div style='overflow: hidden;white-space: nowrap;text-overflow: ellipsis;width: 53px' title='{0}'>{0}</div></td>",item.provinceName!=null?item.provinceName:"");
//            param += Objects.format("<td class=\"table_td\"><div style='overflow: hidden;white-space: nowrap;text-overflow: ellipsis;width: 100px' title='{0}'>{0}</div></td>",item.provinceName!=null?item.cityName!=null?item.provinceName+item.cityName:item.provinceName:item.cityName!=null?item.cityName:item.provinceName+item.provinceName);
            param += Objects.format("<td class=\"table_td\"><div style='overflow: hidden;white-space: nowrap;text-overflow: ellipsis;width: 200px' title='{0}'>{0}</div></td>",item.orderUnitName!=null?item.orderUnitName:"");
            param += Objects.format("<td class=\"table_td\"><div style='overflow: hidden;white-space: nowrap;text-overflow: ellipsis;width: 65px' title='{0}'>{0}</div></td>",item.purchaser!=null?item.purchaser:"");
            param += Objects.format("<td class=\"table_td\">{0}</td>",item.purchaserMobile!=null?item.purchaserMobile:"");
            var productName = "";
            var obj = {};
            item.orderProducts = (item.orderProducts).reduce(function (items,next) {
                obj[next.productName] ? '' : obj[next.productName] = true && items.push(next);
                return items;
            },[]);
            for(var i in item.orderProducts){
                var product = item.orderProducts[i];
                productName += product.productName!=null?(i!=item.orderProducts.length-1?product.productName+"、":product.productName):"";
            }
            param += Objects.format("<td class=\"table_td\"><div style='overflow: hidden;white-space: nowrap;text-overflow: ellipsis;width: 205px' title='{0}'>{0}</div></td>",productName);
            param += Objects.format("<td class=\"table_td\" align='right'>{0}</td>",item.productTotalCount!=null?item.productTotalCount:"");
            param += Objects.format("<td class=\"table_td\" align='right'>{0}</td>",item.productTotalAmount!=null?item.productTotalAmount:"");
            //订单类型 4提前发货
            //提前发货 子状态是否为借转销
            var borrowTypeStr = '';
            if(item.orderType==4) {
            	borrowTypeStr += getBorrowType(item.borrowType);
            }
            param += Objects.format("<td class=\"table_td\">{0}{1}</td>",getOrderType(item.orderType),borrowTypeStr);
            //订单状态 订单状态：(0.待受理、1.已受理待付款、2.生产中—待生产、2.1.生产中—生产中、2.2.生产中—生产完待发货、3.发运中、4.完成、5.退回、6.作废)
            param += Objects.format("<td class=\"table_td\">{0}</td>",getOrderStatusAdmin(item.orderStatus));
            //发票状态：0未开票，1已开票 ，2部分票据已开
            if(item.orderInvoiceStatus==0){
                param += Objects.format("<td class=\"table_td\">{0}</td>","未开票");
            }else if(item.orderInvoiceStatus==1){
                param += Objects.format("<td class=\"table_td\">{0}</td>","已开票");
            }else if(item.orderInvoiceStatus==2){
                param += Objects.format("<td class=\"table_td\" title='部分票据已开'>{0}</td>","部票已开");
            }else{
                param += Objects.format("<td class=\"table_td\"></td>");
            }

            param += Objects.format("<td class=\"table_td\">{0}</td>",item.deliveryDate!=null?getLocalTimeYYYYMMDD(item.deliveryDate):"");
            param += Objects.format("<td class=\"table_td\"><a onclick=\"detailsOrder('{0}')\">查看详情</a></td>",item.orderId);
            if(item.warn==true){
                param += Objects.format("<td class=\"table_td\"><div class=\"blink\">有新留言</div></td>");
            }else{
                param += Objects.format("<td class=\"table_td\"></td>");
            }
            param += Objects.format("</tr>");
       }
        view.append(param);
        tableAddClass();
        $("#sortTable").tablesorter({widthFixed:false,headers:{0:{sorter:false}}});     //表格排序

    }



//    function computePagination() {
//        var pageView = $("#pagination ul.pagination");
//        pageView.empty();
//        var pageCount = limit.total % limit.count == 0 ? limit.total / limit.count : limit.total / limit.count + 1;
//        limit.pageCount = pageCount;
//        pageView.append(itemTemplateOfpageView("item", 1, "&laquo;", limit));
//        for (var i = 4; i > 0; i--) {
//            var p = currentPageIndex - i;
//            if (p > 0) {
//                pageView.append(itemTemplateOfpageView("item", p, p, limit));
//            }
//        }
//        pageView.append(itemTemplateOfpageView("item active", currentPageIndex, currentPageIndex, limit));
//        for (var i = 1; i < 5; i++) {
//            var p = currentPageIndex + i;
//            if (p < limit.pageCount) {
//                pageView.append(itemTemplateOfpageView("item", p, p, limit));
//            }
//        }
//        pageView.append(itemTemplateOfpageView("item", limit.pageCount, "&raquo;", limit));
//        pageView.append(Objects.format("<li class=\"disabled\"><a href=\"#\">{0}/{1}</a></li>", limit.offset + dataCount, limit.total));
//    }
    function itemTemplateOfpageView(cls, pageValue, text, limit) {
        if (pageValue < 0) {
            pageValue = 0;
        } else if (pageValue > limit.pageCount) {
            pageValue = limit.pageCount;
        }
        return Objects.format("<li class=\"{0}\"><a data=\"{1}\">{2}</a></li>", cls, pageValue, text);
    }

    function detailsOrder(orderId) {
        changeDaoHangFrame('订单查询`<%=path%>/commonCompanyOrderQuery/getOrderIndex.do,订单详情`<%=path%>/commonUserOrderQuery/showDetailsOrder.do?orderId='+orderId);
        window.location.href='<%=path%>/commonUserOrderQuery/showDetailsOrder.do?orderId='+orderId;
    }

    //多选
    function selectAllPaper() {
        orderCheckbox=[];
        if($("#selectall").prop("checked")==true){
            $($(".orderCheckbox")).each(function () {
                orderCheckbox.push($(this).val());
                $(this).prop("checked",true);
            })
        }else{
            $($(".orderCheckbox")).each(function () {
                orderCheckbox.push($(this).val());
                $(this).prop("checked",false);
            })
        }
    }

    //批量操作 批发运
    function _postPutOrderExcpress(num) {
        var deliveryDate = $("#deliveryDate").val();
        if(deliveryDate=="") {
            alert("请填入发货日期。");
            return false;
        }
        var expressType = $("#expressType").val();
        if(expressType==""){
            alert("请填写发运物品");
            return false;
        }
        var orderId = "";
        $.each($(".orderCheckbox"),function (index,item) {
            var checkStatus = $(item).prop('checked');
            var _var = $(item).val();
            if(checkStatus){
                orderId +=_var+";";
            }
        })
        if(orderId==""){
            alert("批处理必须勾选一项。");
            closeLoading();
            return false;
        }
        openLoading();
        $.ajax({
            url: "<%=path%>/commonCompanyOrderQuery/postPutOrderExcpress.do",
            type: "POST",
            data:{"orderStatus":num,"orderId":orderId,"deliveryDate":deliveryDate,"expressType":expressType},
            success: function(data){
                if(data.result=="SUCCESS"){
                    alert("操作成功！");
                    closeLoading();
                    parserAsyncClassDataFormData();
                    $("#putOrderStatusExpressModal").modal("hide");
                }else{
                    alert(data.errorMsg);
                    closeLoading();
                }
            }
        })
    }



    //批量操作
    function _putOrderStatus(num){
        openLoading();
        var orderId = "";
        $.each($(".orderCheckbox"),function (index,item) {
            var checkStatus = $(item).prop('checked');
            var _var = $(item).val();
            if(checkStatus){
                orderId +=_var+";";
            }
        })
        if(orderId==""){
            alert("批处理必须勾选一项。");
            closeLoading();
            return false;
        }
        $.ajax({
            url: "<%=path%>/commonCompanyOrderQuery/putOrderStatus.do",
            type: "POST",
            data:{"orderStatus":num,"orderId":orderId},
            success: function(data){
                if(data.result=="SUCCESS"){
                    alert("操作成功！");
                    closeLoading();
                    parserAsyncClassDataFormData();
                }else{
                    alert(data.errorMsg);
                    closeLoading();
                }
            }
        })
    }

    function _exportExcelTemplate(){
        var orderId = "";
        var exportSelect = $("#exportSelect").val();
        var templateExcelId = $("#templateExcelId").val();
        $.each($(".orderCheckbox"),function (index,item) {
            var checkStatus = $(item).prop('checked');
            var _var = $(item).val();
            if(checkStatus){
                orderId +=_var+";";
            }
        })
        if(exportSelect=="1" && orderId==""){
            alert("数据导出必须勾选一项。");
            closeLoading();
            return false;
        }else if(exportSelect=="0"){
            var json = {OrderQuerySearcher: {}};
            $.each($(".classData"), function (index, item) {
                var key = $(item).attr("name"), _val = $(item).val();
                json.OrderQuerySearcher[key] = _val;
            });
            json.OrderQuerySearcher["orderTypeArrays"] = JSON.stringify(orderTypeArray);
            json.OrderQuerySearcher["orderStatusArrays"] = JSON.stringify(orderStatusArray);
            json.OrderQuerySearcher["pageCount"] = pageSize;
            json.OrderQuerySearcher["offset"] = (currentPageIndex - 1) * pageSize;
        }
        var orderQuerySearcher = json!=undefined?jQuery.param(json.OrderQuerySearcher):"";
        location.href= "<%=path%>/excelFile/exportExcelTemplate.do?templateExcelId="+templateExcelId+"&orderId="+orderId+"&exportType="+exportSelect+"&"+orderQuerySearcher;
    }





</script>
</body>
</html>
