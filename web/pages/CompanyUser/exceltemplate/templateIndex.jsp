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
    <%--<link type="text/css" href="<%=path%>/css/css.css" rel="stylesheet" />--%>
    <%--<link type="text/css" href="<%=path%>/css/main.css" rel="stylesheet" />--%>
    <%--<link type="text/css" href="<%=path%>/js/themes/jquery-ui.min.css"--%>
    <%--rel="stylesheet" />--%>

    <%-- 		<script type="text/javascript" src="<%=path%>/js/validate/jquery.validate.js"></script>
            <script type="text/javascript" src="<%=path%>/js/validate/additional-methods.js"></script>
            <script type="text/javascript" src="<%=path%>/js/validate/messages_zh.js"></script> --%>
    <%--<script type="text/javascript" src="<%=path%>/js/validate.js"></script>--%>
    <style type="text/css">
        /*.neir_2_kcgl_bod2 {*/
            /*background-color: white;*/
        /*}*/
        /*.neir_2_kcgl_bod {*/
            /*background-color: white;*/
        /*}*/
        /*input[type=radio],input[type=checkbox]{*/
            /*vertical-align: middle;*/
            /*margin-top: 0px;*/
            /*float: left;*/
            /*width: 74px;*/

        /*}*/
    </style>
    <script type="text/javascript">

        $(function () {
            changeDaoHang("模板管理");
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
<body class="page_content page_color">
    <div class="content">

        <div class="down_area">

            <div class="tdiv" >
                <div class="tdiv_title">
                    <i class="menu-i"></i>
                    <p class="unit">系统模板</p>
                </div>
                <div>
                    <div class="tdiv_a_z">
                        <div class="tdiv_a_6" style="margin-left: 15px">
                            <div class="upload_image1">
                                <input type="radio" name="systemExcel"  value="1">
                                <div class="upload_image2"></div>
                                <div class="upload_text">财务开票表</div>
                            </div>
                        </div>
                        <div class="tdiv_a_6" >
                            <div class="upload_image1">
                                <input type="radio" name="systemExcel" value="0">
                                <div class="upload_image2"></div>
                                <div class="upload_text">生产任务单</div>
                            </div>
                        </div>
                        <div class="tdiv_a_6" >
                            <div class="upload_image1">
                                <input type="radio" name="systemExcel" value="2">
                                <div class="upload_image2"></div>
                                <div class="upload_text">产品快递面单</div>
                            </div>
                        </div>
                        <input type="button" class="btn btn-info btn-width btn-right"  value="下载模板" onclick="downLoadTemplateSystem()">
                        <div class="clearfloat"></div>
                    </div>
                    <div class="clearfloat"></div>
                    <div class="tdiv_bottom"></div>



                </div>
            </div>


            <div class="tdiv" >
                <div class="tdiv_title">
                    <i class="menu-i"></i>
                    <p class="unit">自定义模板</p>
                </div>
                <div>
                    <div class="tdiv_a_z">
                        <div class="tdiv_a_top" style="margin-left: 10px">
                            <input type="button" class="btn btn-info" id="submit" onclick="createTemplate()" value="新增自定义模板">
                            <input type="button" class="btn btn-danger" id="submit" onclick="deleteTblTemplateExcel()" value="删除">
                        </div>
                    </div>
                    <div class="clearfloat"></div>
                    <p></p>
                    <div class="templateTbody" style="min-height: 300px">

                    </div>
                    <div class="clearfloat"></div>
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
    </div>
<script type="text/javascript">

    var currentPageIndex = 1;
    var limit;
    var dataCount = 0;
    var pageSize = 5;
    $(function () {

        $("body").on('click','.tdiv_a_6',function () {
            var radio = $(this).find("input[type=radio]");
            var checkbox = $(this).find("input[type=checkbox]");
            var ss = $(this);
            radio.prop("checked","checked");
            if(checkbox.is(":checked")){
                checkbox.prop("checked",false);
            }else if(checkbox.not(":checked")){
                checkbox.prop("checked",true);
            }
//            $(this).find("input[type=checkbox]:checked").prop("checked",false);
//            $(this).find("input[type=checkbox]:not(:checked)").prop("checked",true);
        })
//        阻止多选事件冒泡
        $('body').on('click','input[type=checkbox]',function (e) {
            e.stopPropagation();
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
        var json = {TblTemplateExcelSearcher: {}};
        json.TblTemplateExcelSearcher["pageCount"] = pageSize;
        json.TblTemplateExcelSearcher["offset"] = (currentPageIndex - 1) * pageSize;
        $.ajax({
            url: "<%=path%>/excelTemplate/showTblTemplateExcelPage.do",
            type: "POST",
            data: json.TblTemplateExcelSearcher,
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
                $("#pageSizeInfo").html("总共"+datas.total+"条，每页显示");
                closeLoading();

            }
        })
    }
    function classDataViewAdapter(datas) {
        var view = $(".templateTbody");
        view.empty();
        var i = 0;
        var param = "";
        for (var data in datas.data) {
            var item = datas.data[data];
//            param += Objects.format("<div class=\"neir_2_kcgl neir_2_kcgl_bod\">");
//            param += Objects.format("<div class='checker'><span> <input type='checkbox' class='orderCheckbox' value='{0}'/></span></div>",item.id);
//            param += Objects.format("<a style=\"color: black;\" ><span>{0}</span></a>",item.templateName);
//            param += Objects.format("<span style=\"float: right\"><input type=\"button\" class=\"main-btn main-btn-blue\" onclick=\"downLoadTemplateCustom('{0}')\" value=\"下载模板\"> </span>",item.id);
//            param += Objects.format("<span style=\"float: right\"><input type=\"button\" class=\"main-btn main-btn-blue\" onclick=\"editTemplate('{0}')\" value=\"编辑\"> </span>",item.id);
//            param += Objects.format("<p></p>");
//            param += Objects.format("</div>");
            //            param += Objects.format("<div class=\"neir_2_kcgl neir_2_kcgl_bod\">");


                        param += Objects.format("<div class=\"tdiv_a_z\">");
                        param += Objects.format("<div class=\"tdiv_a_6\" style=\"margin-left: 15px;width: 100%\">");
                        param += Objects.format("<div class=\"upload_image3\">");
                        param += Objects.format("<input type='checkbox' class='orderCheckbox' value='{0}'/>",item.id);
            param += Objects.format("<input type=\"button\"  class=\"btn btn-info\" onclick=\"downLoadTemplateCustom('{0}')\" value=\"下载模板\"> ",item.id);
            param += Objects.format("<input type=\"button\"  class=\"btn btn-info\" onclick=\"editTemplate('{0}')\" value=\"编辑\"> ",item.id);
                        param += Objects.format("<div class=\"upload_text\">{0}</div>",item.templateName);
                        param += Objects.format("</div></div></div>");
        }
        view.append(param);
        tableAddClass();
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




    function createTemplate() {

        window.location.href='<%=path%>/excelTemplate/createTemplate.do';
        changeDaoHangFrame('模板管理`<%=path%>/excelTemplate/getTemplateIndex.do,新建自定义模板`<%=path%>/excelTemplate/createTemplate.do');

    }
    function editTemplate(data) {
        window.location.href='<%=path%>/excelTemplate/editTemplate.do?id='+data;
        changeDaoHangFrame('模板管理`<%=path%>/excelTemplate/getTemplateIndex.do,编辑`<%=path%>/excelTemplate/editTemplate.do?id='+data);
    }

    function deleteTblTemplateExcel() {

        if(confirm("是否删除！")){
            var data = "";
            $.each($(".orderCheckbox"),function (index,item) {
                var checkStatus = $(item).prop('checked');
                var _var = $(item).val();
                if(_var!=""){
                    if(checkStatus){
                        data +=_var+";";
                    }
                }
            });
            if(data==""){
                alert("请选择一项模板");
                return false;
            }
            openLoading();
            $.ajax({
                url: "<%=path%>/excelTemplate/deleteTblTemplateExcel.do",
                type: "POST",
                data: {"id":data},
                success: function(data){
                    closeLoading();
                    if(data.result=="SUCCESS"){
                        alert("删除成功！");
                        closeLoading();
                        parserAsyncClassDataFormData();
                    }else{
                        alert(data.errorMsg);
                        closeLoading();
                    }
                }
            });
        }
    }
    function downLoadTemplateCustom(id) {
        location.href= "<%=path%>/excelFile/downloadExcelTemplate.do?id="+id;


    } function downLoadTemplateSystem() {
        var examType = $("input[name=systemExcel]:checked").val();
        if(examType=="" || examType == undefined){
            alert("请选择一个系统模板。");
            return false;
        }
        location.href= "<%=path%>/excelFile/downloadSystemExcelTemplate.do?examType="+examType;
    }


</script>
</body>
</html>