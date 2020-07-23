<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<%@ include file="../../inc/header.jsp" %>
<%@ include file="../../init.jsp" %>
<%@ include file="../../power.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>数字证书服务网</title>
    <link type="text/css" href="<%=path%>/css/bootstrap.min.css" rel="stylesheet" />
    <link type="text/css" href="<%=path%>/css/main.css" rel="stylesheet" />
    <link type="text/css" href="<%=path%>/css/tableSorter/style.css" rel="stylesheet" />
    <script type="text/javascript" src="<%=path%>/js/jquery.tablesorter.js"></script>
    <%--<script type="text/javascript" src="<%=path%>/js/My97DatePicker/WdatePicker.js"></script>--%>
    <script type="text/javascript" src="<%=path%>/js/datepicker/WdatePicker.js"></script>

    <style type="text/css">
        body{
            background-color: #e6e6e6;
        }
        .checkText {
                height: 25px !important;
                min-width: 143px !important;
                border: 1px solid #707070;
        }
        .red_label{
            margin-left: 5px;
            color: red;
        }
        .buttonPost{
            background-repeat: no-repeat;
            position: relative;
            margin-right: 5px;
            margin-top: -8px;
        }
        .ms-choice {
            height: 16px !important;
        }
        input[type=text]{
            border: 1px solid #707070;
        }

        input[type=radio]{
            margin-left: 1.5%;
        }
        input{
            margin-left: 1%;
            margin-right: 1%;
        }
        .spanLabel{
            /*background-color: gainsboro;*/
            text-align: center !important;
            width: 40px !important;
            /*margin-left: 6px;*/
            margin-right: 12px !important;
            margin-left: 12px !important;

        }
        .spanLabelLeft{
            text-align: center !important;
            width: 40px !important;
            /*margin-left: 6px;*/
            margin-right: 12px !important;
        }
    </style>
    <script type="text/javascript">
        $(function() {
            //跳转导航页面
            changeDaoHang('系统管理','已售证书标号管理');

            //缩略展开+-
            $("p").hover(function() {
                $(this).addClass("p_hover");
            }, function() {
                $(this).removeClass("p_hover");
            });
//            $("div[class='unit']").next().hide();
            $("p[class='unit']").parent().next().show();
            $("p[class^='unit']").on(
                    'click',
                    function() {
                        if ($(this).parent().next().css('display') == 'none') {
//                            $("div[class='unit_select']").prev().attr("class", "menu-i");
//                            $(this).prev().attr("class", "menu-i");
//                            $(".unit_select").parent().next().slideUp(300).removeClass();
//                            $(this).attr("class", "unit_select").parent().next().slideDown(300);

                            $(this).prev().attr("class", "menu-i");
                            $(this).parent().next().slideUp(300).removeClass();
                            $(this).parent().next().slideDown(300);
                        } else {
                            $(this).prev().attr("class", "menu-c");

                            $(this).removeClass();
                            $(this).parent().next().slideUp(300);
                            $(this).addClass("p_hover");
                        }
                    });
        })

    </script>
</head>
<body class="page_content">
<div>
    <div class="content">
        <div class="down_area">
            <div class="tdiv" style="font-size:12px;">
                <div class="tdiv_title">
                    <i class="menu-i"></i>
                    <p class="unit">添加</p>
                </div>
                <div class="neir_1_kcgl neir_2_kcgl_bod2">
                    <form id="productForm">
                        <%--<div class="tdiv_a_z">--%>
                            <%--<div class="tdiv_a_1">产品编号<label class="red_label">★</label>：</div>--%>
                            <%--<div class="tdiv_a_4">--%>
                                <%--<input type="text"  style="width: 142px;" id="labelStart" name="labelStart" placeholder=" JX32023153GA"/>--%>
                                 <%-----%>
                                <%--<input type="text"  style="width: 142px;" id="labelEnd" name="labelEnd"  placeholder=" JX32023163GA"/>--%>
                            <%--</div>--%>
                        <%--</div>--%>
                        <div class="tdiv_a_z">
                            <div class="tdiv_a_1"></div>
                            <div class="tdiv_a_3"><div class="left_tubiao warn"></div><strong>提醒：起始编号请根据输入框填写。例如：JX32023153GA - JX32023163GA</strong></div>
                        </div>
                        <div class="tdiv_a_z">
                            <div  class="tdiv_a_1">产品名称<label class="red_label">★</label>：</div>
                            <c:forEach items="${productLists}" var="productLists" varStatus="xh">
                                <c:if test="${(xh.index-1)%4==0 && xh.index!=1 && !xh.first}">
                                    <div class="clearfloat"></div>
                                    <div class="tdiv_a_1"></div>
                                </c:if>
                                <c:if test="${productLists.id!=0}">
                                    <div  class="tdiv_a_3">
                                        <input type="radio" name="productradio" class="productName" value="${productLists.productName}`${productLists.productType}`${productLists.labelSuffix}`${productLists.supplier}`${productLists.id}"/>${productLists.productName}
                                    </div>
                                </c:if>
                            </c:forEach>
                            <div class="clearfloat"></div>
                            <div class = "productTable"></div>
                            <input type="hidden" id="productName" name="productName" />
                            <input type="hidden" id="provinceCode" name="provinceCode" />
                            <input type="hidden" id="cityCode" name="cityCode"/>
                            <input type="hidden" name="id" value="${orderId}"/>
                            <input type="hidden" class="productListId" name="productListId"/>
                        </div>
                        <div class="clearfloat"></div>
                        <hr class="hr_style">
                        <div class="clearfloat"></div>
                        <div class="tdiv_bottom"></div>
                        <div class="clearfloat"></div>
                        <div class="tdiv_a_z">
                            <div class="tdiv_a_1">老数据流水号：</div>
                            <div class="tdiv_a_4">
                                <input type="text" class="checkText col-md-10 col-xs-4"  id="flowId" name="flowId"/>
                            </div>
                            <div class="tdiv_a_1">生产时间：</div>
                            <div class="tdiv_a_4">
                                <input type="text" class="checkText col-md-10 col-xs-4" id="productionDate" name="productionDate" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})"/>
                            </div>

                            <%--<div class="tdiv_a_1">证书类型：</div>--%>
                            <%--<div class="tdiv_a_4">--%>
                                <%--<select name="productListId" id="productListId" style="width:175px;padding: 0px 0px;height: 24px;float: left;margin-right: 40px;" class="form-control">--%>
                                    <%--<option value="1" selected>公安数字证书</option>--%>
                                    <%--<option value="2">警辅数字证书</option>--%>
                                    <%--<option value="3">公安、警辅数字证书解锁卡</option>--%>
                                    <%--<option value="4">公安指纹数字证书</option>--%>
                                    <%--<option value="5">警辅指纹数字证书</option>--%>
                                    <%--<option value="6">专网数字证书</option>--%>
                                <%--</select>--%>
                            <%--</div>--%>
                            <div class="tdiv_a_1">
                                <input type="button" class="btn btn-info buttonPost" onclick="_postTblProductionProduct()" value="添加">
                            </div>

                        </div>
                        <div class="clearfloat"></div>
                        <div class="tdiv_bottom"></div>
                    </form>
                </div>
            </div>
            <div class="tdiv">
            <div class="tdiv_title">
                <i class="menu-i"></i>
                <p class="unit">查询</p>
            </div>
            <div class="neir_1_kcgl neir_2_kcgl_bod2">


                <form id="queryProductForm">
                    <div class="tdiv_a_z">
                        <div  class="tdiv_a_1">产品名称<label class="red_label">★</label>：</div>
                        <c:forEach items="${productLists}" var="productLists" varStatus="xh">
                            <c:if test="${(xh.index-1)%4==0 && xh.index!=1 && !xh.first}">
                                <div class="clearfloat"></div>
                                <div class="tdiv_a_1"></div>
                            </c:if>
                            <c:if test="${productLists.id!=0}">
                                <div  class="tdiv_a_3">
                                    <input type="radio" name="productradio" class="productName" value="${productLists.productName}`${productLists.productType}`${productLists.labelSuffix}`${productLists.supplier}`${productLists.id}"/>${productLists.productName}
                                </div>
                            </c:if>
                        </c:forEach>
                        <div class="clearfloat"></div>
                        <div class = "productTable"></div>
                        <input type="hidden" class="productListId" name="productListId"/>
                    </div>
                    <div class="clearfloat"></div>


                <%--<div class="tdiv_a_z">--%>
                    <%--<div class="tdiv_a_1">产品编号<label class="red_label">★</label>：</div>--%>
                    <%--<div class="tdiv_a_4">--%>
                        <%--<input type="text" style="width: 28px;" class="classData" name="labelPrefix" placeholder=" JX">--%>
                        <%--<input type="text" style="width: 127px;" class="classData" name="labelStart" placeholder=" 32023153GA">--%>
                        <%-----%>
                        <%--<input type="text" style="width: 127px;" class="classData" name="labelEnd" placeholder=" 32023163GA">--%>

                    <%--</div>--%>

                    <%--&lt;%&ndash;<div class="tdiv_a_5">&ndash;%&gt;--%>
                        <%--&lt;%&ndash;<input type="text"  class="checkText form-control col-md-10 col-xs-4 classData"  name="labelProduct"/>&ndash;%&gt;--%>
                    <%--&lt;%&ndash;</div>&ndash;%&gt;--%>


                    <%--<div class="tdiv_a_1" >证书类型：</div>--%>
                    <%--<div class="tdiv_a_4">--%>
                        <%--<select name="productListId"  style="width:175px;padding: 0px 0px;height: 24px;float: left;margin-right: 40px;" class="form-control classData">--%>
                            <%--<option value="1" selected>公安数字证书</option>--%>
                            <%--<option value="2">警辅数字证书</option>--%>
                            <%--<option value="3">公安、警辅数字证书解锁卡</option>--%>
                            <%--<option value="4">公安指纹数字证书</option>--%>
                            <%--<option value="5">警辅指纹数字证书</option>--%>
                            <%--<option value="6">专网数字证书</option>--%>
                        <%--</select>--%>
                    <%--</div>--%>
                <%--</div>--%>
                <%--<div class="clearfloat"></div>--%>
                <div class="tdiv_a_z">

                    <div class="tdiv_a_1">添加人员：</div>
                    <div class="tdiv_a_4">
                        <input type="text"  class="checkText form-control col-md-10 col-xs-4 classData"  name="userName"/>
                    </div>
                    <div class="tdiv_a_1">添加时间：</div>

                    <input type="text" class="checkText col-md-10 col-xs-4 classData"  name="addDateStart" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" style="width: 127px!important;"/>
                    <div class="col-md-1 col-xs-1" style="width: 45px;">
                        到
                    </div>
                    <input type="text" class="checkText col-md-10 col-xs-4 classData" name="addDateEnd" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" style="width: 128px!important;"/>

                    <div class="tdiv_a_1">
                        <input type="button" class="btn btn-info" id= "basicInfoSearch" value="查询">
                    </div>
                </div>
                </form>
                <div class="clearfloat"></div>

            <div  style="margin-top: 30px;min-height: 200px;">
                <table class="table table-bordered table-hover rzgggl-table tablesorter" id="sortTable">
                    <thead>
                    <tr>
                        <th>产品开始编号</th>
                        <th>产品结束编号</th>
                        <th>生产库产品编号</th>
                        <th>流水号</th>
                        <th>证书类型</th>
                        <th>生产时间</th>
                        <th>添加时间</th>
                        <th>添加人员</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tbody id="classDataView">
                    </tbody>
                </table>

            </div>
            <div class="clearfloat"></div>
                </div>
            </div>
            <div class="control-group nav-fenye">
                <div class="pull-left nav-fenye"><span id="pageSizeInfo"></span>
                    <select id="selectPageSizeId" class="nopad" style="width: 45px" onchange="selectPageSize(this)">
                        <option selected="selected" value="5">5</option>
                        <option value="10">10</option>
                        <option  value="20">20</option>
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
                <div class="clearfloat"></div>
            </div>
            </div>
        </div>

    </div>
</div>


<script type="text/javascript">
    var currentPageIndex = 1;
    var limit;
    var dataCount = 0;
    var pageSize = 5;
    $(function () {



        //基本信息搜索
        $("body").on("click","#basicInfoSearch",function () {
            currentPageIndex = 1;
            openLoading();
            parserAsyncClassDataFormData();
        })
        //分页查询
//        $("body").on("click", ".pagination li.item a", itemOfpageViewOnSelected);
        $("body").on("click", ".pagination li a", itemOfpageViewOnSelected);
        //parserAsyncClassDataFormData();
    })

    window.$productPrefix = "";
    window.$productSuffixId = "";

    //型号后缀
    $(".productName:radio").click(function () {
        $("#productType").empty();
        productSuffix = $(this).val().split("`");
        productSuffixProductName = productSuffix[0];
        productSuffixProductType = productSuffix[1];
        productSuffixProductSuffix = productSuffix[2];
        productSuffixProductsupplier = productSuffix[3];
        $productSuffixId = productSuffix[4];

//			showTblAreaLabelSuffix($productSuffixId);

        $("#provinceCode").val($("#areaProvice").val());
        $("#cityCode").val($("#areaCity").val());
        $("#productName").val(productSuffixProductName);

        $(".productListId").val($productSuffixId);
        var productTable = $(this).parent('div').nextAll('.productTable');
        var param = "";
        $(".removeProductTable").remove();
        $(".productListId").val($productSuffixId);

        productSuffixProductType = productSuffixProductType.split(",");
        param +=Objects.format("<div class=\" removeProductTable\">");
        param +=Objects.format("<div class=\"tdiv_a_z\">");
        //公安，辅警数字证书
        if($productSuffixId=="3"){
            $("#supplier").val(productSuffixProductsupplier);
            $("#labelSuffix").val(productSuffixProductSuffix);
            param +=Objects.format("<div class=\" removeProductTable\">");
            param +=Objects.format("<div class=\"tdiv_a_z\" style=\"min-width: 1000px;\">");
            param +=Objects.format("<div class=\"tdiv_a_1\">起始编号<label class=\"red_label\">★</label>：</div>");
            param +=Objects.format("<input type=\"text\" name=\"labelPrefix\" class=\"spanLabelLeft\" id=\"labelPrefix\"  style=\"width: 30px\" value=\"{0}\"/>",$productPrefix!=null?($productPrefix.prefixName!=undefined?$productPrefix.prefixName:""):"");


            param +=Objects.format("<input type=\"text\" id=\"supplier\" class=\"spanLabel\"  name=\"supplier\" value=\"{0}\"/>",productSuffixProductsupplier);
            param +=Objects.format("<input type=\"text\"  name=\"productRegionLabelInfix\" onkeyup=\"this.value=this.value.replace(/\\D/g,'')\" class=\"width_30\">");
            param +=Objects.format("<input type=\"text\" id=\"labelSuffix\" class=\"spanLabel\"  name=\"labelSuffix\" value=\"{0}\"/>",productSuffixProductSuffix);
            param +=Objects.format("<input type=\"text\" name=\"labelNumStart\" onkeyup=\"this.value=this.value.replace(/\\D/g,'')\" class=\"width_30\">");
            param +=Objects.format("-");
            param +=Objects.format("<input type=\"text\" name=\"labelNumEnd\" onkeyup=\"this.value=this.value.replace(/\\D/g,'')\" class=\"width_30\">");
            param +=Objects.format("</td>");
            /*param +=Objects.format("<input type=\"button\" class=\"button_left15\" onclick=\"javascript:addProduct();\" value=\"加入已选\"> </td>");*/
            param +=Objects.format("</div>");
            productTable.append(param);
            return;
        }
        //专网数字证书
        if($productSuffixId=="6"){
            $("#supplier").val("");
            $("#labelSuffix").val(productSuffixProductSuffix);
            param +=Objects.format("<div class=\" removeProductTable\">");
            param +=Objects.format("<div class=\"tdiv_a_z\" style=\"min-width: 1000px;\">");
            param +=Objects.format("<div class=\"tdiv_a_1\">起始编号<label class=\"red_label\">★</label>：</div>");
            param +=Objects.format("<input type=\"text\" id=\"labelSuffix\" class=\"spanLabelLeft\"  name=\"labelSuffix\" value=\"{0}\"/>",productSuffixProductSuffix);
            param +=Objects.format("<input type=\"text\" name=\"labelPrefix\" class=\"spanLabelLeft\" id=\"labelPrefix\"  style=\"width: 30px\" value=\"{0}\"/>",$productPrefix!=null?($productPrefix.prefixName!=undefined?$productPrefix.prefixName:""):"");

            param +=Objects.format("<input name=\"labelNumStart\" onkeyup=\"this.value=this.value.replace(/\\D/g,'')\"  type=\"text\">");
            param +=Objects.format("-");
            param +=Objects.format("<input name=\"labelNumEnd\" onkeyup=\"this.value=this.value.replace(/\\D/g,'')\" type=\"text\">");
            /*param +=Objects.format("<input type=\"button\" class=\"button_left15\" onclick=\"javascript:addProduct();\" value=\"加入已选\"> </td>");*/
            param +=Objects.format("</td>");
            param +=Objects.format("</p></div>");
            productTable.append(param);
            return;
        }
        //公安指纹数字证书
        if($productSuffixId=="4"){

            param +=Objects.format("<div class=\" removeProductTable\">");
            param +=Objects.format("<div class=\"tdiv_a_z\" style=\"min-width: 1000px;\">");
            param +=Objects.format("<div class=\"tdiv_a_1\">起始编号<label class=\"red_label\">★</label>：</div>");
            param +=Objects.format("<input type=\"text\" class='spanLabelLeft' name=\"labelPrefix\" id=\"labelPrefix\"  style=\"width: 30px\" value=\"{0}\"/>",$productPrefix!=null?($productPrefix.prefixName!=undefined?$productPrefix.prefixName:""):"");

            param +=Objects.format("<input type=\"text\" class='spanLabel'  id=\"supplier\" name=\"supplier\" value=\"{0}\"/>",productSuffixProductsupplier);
            param +=Objects.format("<input name=\"labelNumStart\" onkeyup=\"this.value=this.value.replace(/\\D/g,'')\"  type=\"text\">");
            param +=Objects.format("-");
            param +=Objects.format("<input name=\"labelNumEnd\" onkeyup=\"this.value=this.value.replace(/\\D/g,'')\"  type=\"text\">");
            param +=Objects.format("<input type=\"text\" class='spanLabel'  id=\"labelSuffix\" name=\"labelSuffix\" value=\"{0}\"/>",productSuffixProductSuffix.split(",")[0]);

            param +=Objects.format("</td>");
            param +=Objects.format("</p></div>");
            productTable.append(param);
            return;
        }

        //警辅指纹数字证书
        if($productSuffixId=="5"){

            param +=Objects.format("<div class=\" removeProductTable\">");
            param +=Objects.format("<div class=\"tdiv_a_z\" style=\"min-width: 1000px;\">");
            param +=Objects.format("<div class=\"tdiv_a_1\">起始编号<label class=\"red_label\">★</label>：</div>");
            param +=Objects.format("<input type=\"text\" class='spanLabelLeft' name=\"labelPrefix\" id=\"labelPrefix\"  style=\"width: 30px\" value=\"{0}\"/>",$productPrefix!=null?($productPrefix.prefixName!=undefined?$productPrefix.prefixName:""):"");

            param +=Objects.format("<input type=\"text\" class='spanLabel'  id=\"supplier\" name=\"supplier\" value=\"{0}\"/>",productSuffixProductsupplier);
            param +=Objects.format("<input name=\"labelNumStart\" onkeyup=\"this.value=this.value.replace(/\\D/g,'')\"  type=\"text\">");
            param +=Objects.format("-");
            param +=Objects.format("<input name=\"labelNumEnd\" onkeyup=\"this.value=this.value.replace(/\\D/g,'')\"  type=\"text\">");
            param +=Objects.format("<input type=\"text\" class='spanLabel'  id=\"labelSuffix\" name=\"labelSuffix\" value=\"{0}\"/>",productSuffixProductSuffix.split(",")[0]);

            param +=Objects.format("</td>");
            param +=Objects.format("</p></div>");
            productTable.append(param);
            return;
        }else {
            param +=Objects.format("<div class=\" removeProductTable\">");
            param +=Objects.format("<div class=\"tdiv_a_z\" style=\"min-width: 1000px;\">");
            param +=Objects.format("<div class=\"tdiv_a_1\">起始编号<label class=\"red_label\">★</label>：</div>");
            param +=Objects.format("<input type=\"text\" class='spanLabelLeft' name=\"labelPrefix\" id=\"labelPrefix\"  style=\"width: 30px\" value=\"{0}\"/>",$productPrefix!=null?($productPrefix.prefixName!=undefined?$productPrefix.prefixName:""):"");

            param +=Objects.format("<input type=\"text\" class='spanLabel'  id=\"supplier\" name=\"supplier\" value=\"{0}\"/>",productSuffixProductsupplier);
            param +=Objects.format("<input name=\"labelNumStart\" onkeyup=\"this.value=this.value.replace(/\\D/g,'')\"  type=\"text\">");
            param +=Objects.format("-");
            param +=Objects.format("<input name=\"labelNumEnd\" onkeyup=\"this.value=this.value.replace(/\\D/g,'')\"  type=\"text\">");
            param +=Objects.format("<input type=\"text\" class='spanLabel'  id=\"labelSuffix\" name=\"labelSuffix\" value=\"{0}\"/>",productSuffixProductSuffix);
            param +=Objects.format("</td>");
            param +=Objects.format("</p></div>");
            productTable.append(param);
            return;
        }
    })


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
    function  parserAsyncClassDataFormData(){
        var json = {TblOrderUnitSearcher: {}};
//        $.each($(".classData"), function (index, item) {
        $("#queryProductForm").find('input').each(function (index, item) {
            var key = $(item).attr("name"), _val = $(item).val();
            json.TblOrderUnitSearcher[key] = _val;
        });
//        //拼装开始结束标号
//        json.TblOrderUnitSearcher["labelStart"] = json.TblOrderUnitSearcher["labelPrefix"].trim()+json.TblOrderUnitSearcher["labelStart"];
//        json.TblOrderUnitSearcher["labelEnd"] = json.TblOrderUnitSearcher["labelPrefix"].trim()+json.TblOrderUnitSearcher["labelEnd"];
        json.TblOrderUnitSearcher["pageCount"] = pageSize;
        json.TblOrderUnitSearcher["offset"] = (currentPageIndex - 1) * pageSize;
        if(isNull(json.TblOrderUnitSearcher["labelNumStart"])
                || isNull(json.TblOrderUnitSearcher["labelNumEnd"])
                || isNull(json.TblOrderUnitSearcher["labelPrefix"])
                || isNull(json.TblOrderUnitSearcher["productListId"])){
            alert("请填写必填项");
            closeLoading();
            return;
        }

        $.ajax({
            url: "<%=path%>/system/showProductionProductWithPage.do",
            type: "POST",
            data: json.TblOrderUnitSearcher,
            success: function(datas){
                closeLoading();
                if(datas==""){
                    return;
                }
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
                $("#pageSizeInfo").html("总共"+datas.total+"条，每页显示");

                closeLoading();

            }
            ,error : function(XMLHttpRequest, textStatus, errorThrown) {
                closeLoading();
                alert("加入失败" + errorThrown);
            }
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
            param += Objects.format("<td class=\"table_td\">{0}</td>",item.labelStart!=null?item.labelStart:"");
            param += Objects.format("<td class=\"table_td\">{0}</td>",item.labelEnd!=null?item.labelEnd:"");
            param += Objects.format("<td class=\"table_td\">{0}</td>",item.labelProduct!=null?item.labelProduct:"");
            param += Objects.format("<td class=\"table_td\">{0}</td>",item.flowId!=null?item.flowId:"");
            param += Objects.format("<td class=\"table_td\">{0}</td>",item.productName!=null?item.productName:"");
            param += Objects.format("<td class=\"table_td\">{0}</td>",item.productionDate!=null?getLocalTime(item.productionDate):"");
            param += Objects.format("<td class=\"table_td\">{0}</td>",item.dateCreated!=null?getLocalTime(item.dateCreated):"");
            param += Objects.format("<td class=\"table_td\">{0}</td>",item.userName!=null?item.userName:"");
            param += Objects.format("<td class=\"table_td\"><a onclick=\"_deleteProductionProduct('{0}')\">删除</a></td>",item.id);

            param += Objects.format("</tr>");
        }
        view.append(param);
        $("#sortTable").tablesorter();     //表格排序

    }
    <%--function computePagination() {--%>
        <%--var pageView = $("#pagination ul.pagination");--%>
        <%--pageView.empty();--%>
        <%--var pageCount = limit.total % limit.count == 0 ? limit.total / limit.count : limit.total / limit.count + 1;--%>
        <%--limit.pageCount = pageCount;--%>
        <%--pageView.append(itemTemplateOfpageView("item", 1, "&laquo;", limit));--%>
        <%--for (var i = 4; i > 0; i--) {--%>
            <%--var p = currentPageIndex - i;--%>
            <%--if (p > 0) {--%>
                <%--pageView.append(itemTemplateOfpageView("item", p, p, limit));--%>
            <%--}--%>
        <%--}--%>
        <%--pageView.append(itemTemplateOfpageView("item active", currentPageIndex, currentPageIndex, limit));--%>
        <%--for (var i = 1; i < 5; i++) {--%>
            <%--var p = currentPageIndex + i;--%>
            <%--if (p < limit.pageCount) {--%>
                <%--pageView.append(itemTemplateOfpageView("item", p, p, limit));--%>
            <%--}--%>
        <%--}--%>
        <%--pageView.append(itemTemplateOfpageView("item", limit.pageCount, "&raquo;", limit));--%>
        <%--pageView.append(Objects.format("<li class=\"disabled\"><a href=\"#\">{0}/{1}</a></li>", limit.offset + dataCount, limit.total));--%>
    <%--}--%>
    function itemTemplateOfpageView(cls, pageValue, text, limit) {
        if (pageValue < 0) {
            pageValue = 0;
        } else if (pageValue > limit.pageCount) {
            pageValue = limit.pageCount;
        }
        return Objects.format("<li class=\"{0}\"><a data=\"{1}\">{2}</a></li>", cls, pageValue, text);
    }

    function _postTblProductionProduct(){

        var productNumRe = /^\d{7}$/;
        var re=/^[A-Z]{2}\d{7}|[A-Z]{2}\d{4}JM\d{3}|[A-Z]{2}\d{2}JM\d{3}$/;

        if($("input[name=labelPrefix]").val()==""){
            alert("起始编号:证书前缀不可为空");
            return false;
        }
        if($productSuffixId=="3"){ //公安辅警证书验证格式
            var productRegionLabelRe = /^((\d{4})|(\d{2}))$/;
            var productRegionLabelNumRe = /^\d{3}$/;
            if(!productRegionLabelRe.test($("input[name=productRegionLabelInfix]").val())||$("input[name=productRegionLabelInfix]").val()==""){
                alert("起始编号:证书机构编号不符合要求");
                return false;
            }else if(!productRegionLabelNumRe.test($("input[name=labelNumStart]").val())||$("input[name=labelNumStart]").val()==""){
                alert("起始编号:证书开始编号格式错误");
                return false;
            } else if(!productRegionLabelNumRe.test($("input[name=labelNumEnd]").val())||$("input[name=labelNumEnd]").val()==""){
                if($("input[name=labelNumEnd]").val()==""){
                    $("input[name=labelNumEnd]").val($("input[name=labelNumStart]").val());
                }else{
                    alert("结束编号:证书结束编号格式错误");
                    return false;
                }
            }else if($("input[name=labelNumEnd]").val()-$("input[name=labelNumStart]").val()<0){
                alert("起始编号无法小于结束编号");
                return false;
            }
        }else if($productSuffixId=="6") { //专网证书验证格式
            var productNumRez = /^\d{6}$/;
            if(!productNumRez.test($("input[name=labelNumStart]").val())||$("input[name=labelNumStart]").val()==""){
                alert("起始编号:证书开始编号格式错误");
                return false;
            }else if(!productNumRez.test($("input[name=labelNumEnd]").val())||$("input[name=labelNumEnd]").val()==""){
                if($("input[name=labelNumEnd]").val()==""){
                    $("input[name=labelNumEnd]").val($("input[name=labelNumStart]").val());
                }else{
                    alert("结束编号:证书结束编号格式错误");
                    return false;
                }
            }else if($("input[name=labelNumEnd]").val()-$("input[name=labelNumStart]").val()<0){
                alert("起始编号无法小于结束编号");
                return false;
            }
        } else{
             if(!productNumRe.test($("input[name=labelNumStart]").val())||$("input[name=labelNumStart]").val()==""){
                alert("起始编号:证书开始编号格式错误");
                return false;
            }else if(!productNumRe.test($("input[name=labelNumEnd]").val())||$("input[name=labelNumEnd]").val()==""){
                if($("input[name=labelNumEnd]").val()==""){
                    $("input[name=labelNumEnd]").val($("input[name=labelNumStart]").val());
                }else{
                    alert("结束编号:证书结束编号格式错误");
                    return false;
                }
            }else if($("input[name=labelNumEnd]").val()-$("input[name=labelNumStart]").val()<0){
                alert("起始编号无法小于结束编号");
                return false;
            }
        }




//        if(isNULL("#labelStart") || isNULL("#labelEnd")){
//            alert("请填写必填项");
//            return false;
//        }
//        var labelStart = $("#labelStart").val();
//        var labelEnd = $("#labelEnd").val();
//        var productionDate = $("#productionDate").val();
//        var productListId = $("#productListId").val();
//        var productName = $("#productListId").find("option:selected").text();
        openLoading();
        $.ajax({
            url: "<%=path%>/system/postTblProductionProduct.do",
            type: "POST",
            cache: false,
            data: $("#productForm").serialize(),
//            data: {"labelStart":labelStart,"labelEnd":labelEnd,"productionDate":productionDate,"productListId":productListId,"productName":productName},
            success: function(data){
                if(data.result=="SUCCESS"){
                    alert("添加成功！");
                    closeLoading();
                    //清空产品列表
//					$("input[name=productPrice]").val("");
                    $("input[name=labelNumStart]").val("");
                    $("input[name=labelNumEnd]").val("");
                    $("input[name=flowId]").val("");
                    $("input[name=productionDate]").val("");
                }else{
                    alert(data.errorMsg);
                    closeLoading();
                }
            }
        });
    }

    function _deleteOrderUnitName(userId) {
        if(userId==""){
            alert("请重新选择用户");
            return false;
        }
        if(confirm("是否删除关联单位！")) {
            $.ajax({
                url: "<%=path%>/system/putUserInfo.do",
                type: "POST",
                cache: false,
                data: {"userId": userId},
                success: function (data) {
                    if (data.result == "SUCCESS") {
                        alert("删除成功！");
                        closeLoading();
                        parserAsyncClassDataFormData();
                    } else {
                        alert(data.errorMsg);
                        closeLoading();
                    }
                }
            });
        }

    }



</script>
</body>
</html>
