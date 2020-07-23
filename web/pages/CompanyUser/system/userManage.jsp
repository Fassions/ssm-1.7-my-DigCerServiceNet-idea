<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ include file="../../init.jsp" %>
<%@ include file="../../inc/header.jsp" %>
<%@ include file="../../power.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>数字证书服务网</title>
    <%--<link type="text/css" href="<%=path%>/css/bootstrap.min.css" rel="stylesheet" />--%>
    <%--<link type="text/css" href="<%=path%>/css/main.css" rel="stylesheet" />--%>
    <link type="text/css" href="<%=path%>/css/tableSorter/style.css" rel="stylesheet" />
    <script type="text/javascript" src="<%=path%>/js/jquery.tablesorter.js"></script>

    <style type="text/css">
        body{
            background-color: #e6e6e6;
        }
        .checkText {
                height: 25px !important;
                min-width: 200px !important;
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
            margin-top: 10px;
        }
        .ms-choice {
            height: 16px !important;
        }

    </style>
    <script type="text/javascript">
        $(function() {
            //跳转导航页面
            changeDaoHang('系统管理','买家用户管理');

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
<div >
    <div class="content">
        <div class="down_area">
            <div class="tdiv" style="font-size:12px;">
                <div class="tdiv_title">
                    <i class="menu-i"></i>
                    <p class="unit">添加</p>
                </div>
                <div class="neir_1_kcgl neir_2_kcgl_bod2">
                    <form id="orderUnitForm">
                        <div class="tdiv_a_z">
                            <div class="tdiv_a_1">单位名称<label class="red_label">★</label>：</div>
                            <div class="tdiv_a_5">
                                <input type="text"  class="checkText form-control col-md-10 col-xs-4" id="orderUnitName" name="orderUnitName"/>
                            </div>

                        </div>
                        <div class="clearfloat"></div>
                        <div class="tdiv_a_z">
                            <div class="tdiv_a_1">成员姓名<label class="red_label">★</label>：</div>
                            <div class="tdiv_a_4">
                                <input type="text" class="checkText form-control col-md-10 col-xs-4" id="userName" name="userName"/>

                            </div>

                            <div class="tdiv_a_1">身份证号<label class="red_label">★</label>：</div>
                            <div class="tdiv_a_5">
                                <input type="text" class="checkText form-control col-md-10 col-xs-4" id="certificateCode" name="certificateCode"/>
                            </div>

                        </div>
                        <div class="clearfloat"></div>
                        <div class="tdiv_a_z">
                            <div class="tdiv_a_1">用户备注：</div>
                            <div class="tdiv_a_2">
                                <textarea class=" form-control col-md-10 col-xs-4" name="userMessage" style="margin: 0px; height: 65px; width: 580px; border: 1px solid rgb(112, 112, 112);"></textarea>
                            </div>
                            <div class="tdiv_a_1">
                                <input type="button" class="btn btn-info buttonPost" onclick="_postOrderUnit()" value="添加">
                            </div>
                        </div>
                        <div class="clearfloat"></div>
                        <div class="tdiv_a_z">
                            <div class="tdiv_a_1"></div>
                            <div class="tdiv_a_3"><div class="left_tubiao warn"></div><strong>提醒：为防止添加错误，请先进行单位名称或用户信息查询，在进行添加。</strong></div>
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
            <div class="tdiv_a_z">
                <div class="tdiv_a_1">单位名称：</div>
                <div class="tdiv_a_5">
                    <input type="text" class="checkText form-control col-md-10 col-xs-4 classData" name="orderUnitName"/>
                </div>
            </div>
            <div class="clearfloat"></div>
            <div class="tdiv_a_z">
                <div class="tdiv_a_1">成员姓名：</div>
                <div class="tdiv_a_4">
                    <input type="text" class="checkText form-control col-md-10 col-xs-4 classData" name="userName"/>
                </div>

                <div class="tdiv_a_1">身份证号：</div>
                <div class="tdiv_a_5">
                    <input type="text" class="checkText form-control col-md-10 col-xs-4 classData" name="certificateCode"/>
                </div>
                <div class="tdiv_a_1">
                    <input type="button" class="btn btn-info" id= "basicInfoSearch" value="查询">
                </div>
            </div>
            <div class="clearfloat"></div>

            <div  style="margin-top: 30px;min-height: 200px;">
                <table class="table table-bordered table-hover rzgggl-table tablesorter" id="sortTable">
                    <thead>
                    <tr>
                        <th>单位名称</th>
                        <th>成员姓名</th>
                        <th>成员身份证号</th>
                        <th>创建时间</th>
                        <th>单位添加人员</th>
                        <th>用户修改时间</th>
                        <th>用户备注</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tbody id="classDataView">
                    </tbody>
                </table>

            </div>


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
        parserAsyncClassDataFormData();
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
        $.each($(".classData"), function (index, item) {
            var key = $(item).attr("name"), _val = $(item).val();
            json.TblOrderUnitSearcher[key] = _val;
        });
        json.TblOrderUnitSearcher["pageCount"] = pageSize;
        json.TblOrderUnitSearcher["offset"] = (currentPageIndex - 1) * pageSize;
        $.ajax({
            url: "<%=path%>/system/showUserUnitPage.do",
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
            param += Objects.format("<td class=\"table_td\">{0}</td>",item.orderUnitName!=null?item.orderUnitName:"");
            param += Objects.format("<td class=\"table_td\">{0}</td>",item.userName!=null?item.userName:"");
            param += Objects.format("<td class=\"table_td\">{0}</td>",item.certificateCode!=null?item.certificateCode:"");
            param += Objects.format("<td class=\"table_td\">{0}</td>",item.userDateCreated!=null?getLocalTime(item.userDateCreated):"");
            param += Objects.format("<td class=\"table_td\">{0}</td>",item.unitUserCreatedName!=null?item.unitUserCreatedName:"");
            param += Objects.format("<td class=\"table_td\">{0}</td>",item.userDateModified!=null?getLocalTime(item.userDateModified):"");
            param += Objects.format("<td class=\"table_td\">{0}</td>",item.userMessage!=null?item.userMessage:"");

            param += Objects.format("<td class=\"table_td\"><a onclick=\"_deleteOrderUnitName('{0}')\">删除关联单位</a></td>",item.userId);

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

    function _postOrderUnit(){
        if(isNULL("#orderUnitName")||isNULL("#userName")||isNULL("#certificateCode")){
            alert("请填写必填项");
            return false;
        }
        openLoading();
        $.ajax({
            url: "<%=path%>/system/postOrderUnit.do",
            type: "POST",
            cache: false,
            data: $("#orderUnitForm").serialize(),
            success: function(data){
                if(data.result=="SUCCESS"){
                    alert("添加成功！");
                    closeLoading();
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
