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
    <script type="text/javascript" src="<%=path%>/js/jquery-form.js"></script>
    <style type="text/css">

        input.file{ vertical-align:middle; position:relative; filter:alpha(opacity=0); opacity:0; z-index:1; width:382px; height: 36px; line-height: 36px; cursor:pointer; top: -36px; }

        form input.viewfile { z-index:99;  padding:2px; width:300px; vertical-align:middle; color:#999; }

        input[type=text],
        .viewfile{
            border: 1px solid #707070;
        }
    </style>
    <style type="text/css">
        .tubiao_actions{
            background-color: #9E9E9E;
        }
    </style>
    <script type="text/javascript">
        $(function() {
            //跳转导航页面
            changeDaoHang('系统管理','文件上传');

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
            <div class="tdiv" >
                <div class="tdiv_title"><p>首页文件上传</p></div>
                <form id="uploadForm" enctype="multipart/form-data" method="post">
                    <input name="fileLabel" id="fileLabel" type="hidden" value="home_text"/>
                    <div class="tdiv_a_z">
                        <div class="tdiv_a_1">选择图标<label class="red_label">★</label>：</div>
                        <div class="tdiv_a_3">
                            <div class="tdiv_a_4 tubiao_actions">
                                <div class="home_left_tubiao home_text">
                                    <input type="hidden" value="home_text"/>
                                </div>
                            </div>
                            <div class="tdiv_a_4">
                                <div class="home_left_tubiao home_frame">
                                    <input type="hidden" value="home_frame"/>
                                </div>
                            </div>
                            <div class="tdiv_a_4 ">
                                <div class="home_left_tubiao home_question">
                                    <input type="hidden" value="home_question"/>
                                </div>
                            </div>
                            <div class="tdiv_a_4 ">
                                <div class="home_left_tubiao home_warn">
                                    <input type="hidden" value="home_warn"/>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="tdiv_a_z">
                        <div class="tdiv_a_1">文件排列顺序<label class="red_label">★</label>：</div>
                        <div class="tdiv_a_3">
                            <input type="text"  class="checkText form-control" id="fileLevel" name="fileLevel"/>
                        </div>
                    </div>
                    <div class="clearfloat"></div>
                    <%--<form id="uploadForm" enctype="multipart/form-data" method="post">--%>
                    <%--<input type="hidden" name="orderId" value="${orderId}"/>--%>
                    <div class="tdiv_a_z">
                        <div class="tdiv_a_1">
                            文件上传<label class="red_label">★</label>：
                        </div>
                        <%--<div class="tdiv_a_2">--%>
                        <%--<input type="file" id="zipFile" name="zipFile" class="uplado_input_t" >--%>
                        <%--<input id="fileName" type="text" value="${order.fileName}"/>--%>
                        <%--</div>--%>
                        <%--<div class="tdiv_a_1">--%>
                        <%--<input type="button" onclick="uploadOrder()" value="上传"/>--%>
                        <%--</div>--%>
                        <div class="tdiv_a_2" style="width: 580px;">
                <span>
                    <input type="text"  name="fileName" id = "fileName" style="height: 33px;width: 280px" value="${order.fileName}" class = "form-control viewfile"/>
                </span>
                            <label  class="btn btn-info bottom" style="margin-left: 5px;">选择文件</label>
                            <label  class="btn btn-info bottom" style="margin-left: 10px;" onclick="uploadFile()">上传</label>
                            <a href="/commonUser/home.do" >跳转买家首页</a>
                            <input type="file" id="zipFile" name="zipFile" style="margin-left: -1%"  onchange="document.getElementById('fileName').value=this.value;" class="file">
                        </div>
                        <%--<div >--%>
                        <%--<p class="clear"><input class="btn btn-primary submit" type="button" onclick="uploadOrder()" value="上传"/></p>--%>
                        <%--</div>--%>
                    </div>
                    <div class="tdiv_a_z">
                        <div class="tdiv_a_1"></div>
                        <div class="tdiv_a_2">
                            <div class="container">
                                <div class="demo">
                                    <div style="width: 610px; height: auto; /*border: 1px solid #e1e1e1;*/ /*padding: 10px;*/">
                                        <%--<span id="spanButtonPlaceholder"></span>--%>
                                        <%--<div id="divFileProgressContainer"></div>--%>
                                        <div id="thumbnails">
                                            <ul id="pic_list" style="margin: 5px;margin-left: -10%;">
                                                <c:forEach items="${orderUploadFiles}" var="uploadFile">
                                                    <li id="img_${uploadFile.id}">
                                                        <img class="content" onclick="clickImg(this.src)" src="/file/ioReadImage.do?id=${uploadFile.id}" style="width:100px;height:100px;">
                                                        <img class="button" onclick="_deleteOrderUnloadFiles('${uploadFile.id}')" src="/js/swf/images/fancy_close.png" title="删除">
                                                    </li>
                                                </c:forEach>
                                            </ul>
                                            <div style="clear: both;"></div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="clearfloat"></div>
                </form>
            </div>
            <div class="tdiv">
                <div class="tdiv_title">
                    <i class="menu-i"></i>
                    <p class="unit">查询</p>
                </div>
                <div class="neir_1_kcgl neir_2_kcgl_bod2">

                    <div class="clearfloat"></div>

                    <div>
                        <table class="table table-bordered table-hover rzgggl-table tablesorter" id="sortTable">
                            <thead>
                            <tr>
                                <th>文件图标名称</th>
                                <th>上传文件名称</th>
                                <th>文件排列顺序</th>
                                <th>上传时间</th>
                                <th>操作</th>
                            </tr>
                            </thead>
                            <tbody id="classDataView">
                            </tbody>
                        </table>

                    </div>

                </div>
            </div>
        </div>

    </div>
</div>
<%--编辑上传文件优先级modal--%>
<div class="modal fade bs-example-modal-lg" id="fileHomeModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"  aria-hidden="true"  style="display: none;">
    <div class="modal-dialog modal-lg" role="document">
        <div class="modal-content" style="width: 100%">
            <div class="modal-header">
                <button type="button" class="close invoiceDetailsClose" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>
                <h4 class="modal-title" id="myModalLabel">文件排列顺序更改</h4>
                <br>

            </div>
            <div class="modal-body">
                <div>
                    <input type="hidden" id="modal_TblHomeId"/>
                    <div class="tdiv_a_z">
                        <div class="tdiv_a_1">文件排列顺序<label class="red_label">★</label>：</div>
                        <div class="tdiv_a_3">
                            <input type="text"  class="checkText form-control" id="modal_fileLevel" />
                        </div>
                        <div class="tdiv_a_3">
                            <label tyle="button" class="btn btn-info" onclick="_updateFileHome()">提交</label>
                        </div>
                    </div>
                </div>
                <div class="clearfloat"></div>
            </div>
            <div class="modal-footer"></div>
        </div>
    </div>
</div>

<script type="text/javascript">

    parserAsyncClassDataFormData();
    $(".tdiv_a_4").click(function () {
        $(".tubiao_actions").removeClass("tubiao_actions");
        $(this).find(".home_left_tubiao").addClass("tubiao_actions");
        var fileLabel = $(this).find("input[type=hidden]").val();
        $("#fileLabel").val(fileLabel);
    })
    function uploadFile() {
        if(isNULL("#fileLevel")){
            alert("请填写文件排列顺序");
            return false;
        }
        if($("#fileName").val()==""){
            alert("请传入上传文件。");
            return false;
        }
        openLoading();
        $("#uploadForm").ajaxSubmit({
            type:'post',
            url: "<%=path%>/system/uploadHomeFile.do",
            success: function(data){
                data = JSON.parse(data);
                if(data.result == "SUCCESS"){
                    closeLoading();
                    alert("上传成功");
                    closeLoading();
                    parserAsyncClassDataFormData();
                }else{
                    closeLoading();
                    alert(data.errorMsg);
                }

            },error : function(XMLHttpRequest, textStatus, errorThrown) {
                closeLoading();
                alert("加入失败" + errorThrown);
            }
        })
    }
    function  parserAsyncClassDataFormData(){
        $.ajax({
            url: "<%=path%>/system/showFileHome.do",
            type: "POST",
            success: function(datas){
                closeLoading();
                if(datas==""){
                    return;
                }
                classDataViewAdapter(datas);  //数据
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
        var defaultLevel = "";
        for (var data in datas) {
            var item = datas[data];
            param += Objects.format("<tr>");
            param += Objects.format("<td class=\"table_td\">{0}</td>",item.fileLabel!=null?"<div class=\"home_left_tubiao "+item.fileLabel+"\"></div>":"");
            param += Objects.format("<td class=\"table_td\">{0}</td>",item.fileName!=null?item.fileName:"");
            param += Objects.format("<td class=\"table_td\">{0}</td>",item.fileLevel!=null?item.fileLevel:"");
            param += Objects.format("<td class=\"table_td\">{0}</td>",item.dateCreated!=null?getLocalTime(item.dateCreated):"");
            param += Objects.format("<td class=\"table_td\" style='text-align: center'>" +
                    "<div style='button' class='btn btn-info' onclick=\"_modelUpdateFileHome('{0},{1}')\">编辑</div>" +
                    "<div style='button;margin-left: 5px' class='btn btn-danger' onclick=\"_deleteFileHome('{0}')\">删除</div>",item.id,item.fileLevel);
            param += Objects.format("</tr>");
            defaultLevel = Number(item.fileLevel)+1;
        }
        view.append(param);
        $("#fileLevel").val(defaultLevel);
        $("#sortTable").tablesorter();     //表格排序

    }
    function _modelUpdateFileHome(tblHomeLevelId) {
        var tblHomeId = tblHomeLevelId.split(",")[0];
        var tblHomeLevel = tblHomeLevelId.split(",")[1];
        $("#modal_TblHomeId").val(tblHomeId);
        $("#modal_fileLevel").val(tblHomeLevel);
        $("#fileHomeModal").modal('show');
    }
    function _updateFileHome() {
        var tblHomeId = $("#modal_TblHomeId").val();
        var tblHomeLevel = $("#modal_fileLevel").val();
        if(isNull(tblHomeId) || isNull(tblHomeLevel)){
            alert("请重新选择文件");
            return false;
        }
        $.ajax({
            url: "<%=path%>/system/updateTblHome.do",
            type: "POST",
            cache: false,
            data: {"id": tblHomeId,"fileLevel":tblHomeLevel},
            success: function (data) {
                if (data.result == "SUCCESS") {
                    closeLoading();
                    alert("提交成功！");
                    parserAsyncClassDataFormData();
                    $("#fileHomeModal").modal('hide');
                } else {
                    alert(data.errorMsg);
                    closeLoading();
                }
            }
        });
    }

    function _deleteFileHome(tblHomeId) {
        if(tblHomeId==""){
            alert("请重新选择文件");
            return false;
        }
        if(confirm("是否删除上传文件！")) {
            $.ajax({
                url: "<%=path%>/system/deleteTblHome.do",
                type: "POST",
                cache: false,
                data: {"tblHomeId": tblHomeId},
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
