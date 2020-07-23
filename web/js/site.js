// JavaScript Document
$(function(){});

/**
 * 所有产品下拉 通用
 */
function appendProductNames() {
    var param = "";
    param += Objects.format("<select  id=\"productName1\" style='padding: 0px 0px;width: 140px;' name=\"productName1\" class=\"classData form-control selectText\">");
    param += Objects.format("<option selected value=\"\">请选择</option>");
    param += Objects.format("<option value=\"{0}\">{0}</option>","公安数字证书");
    param += Objects.format("<option value=\"{0}\">{0}</option>","警辅数字证书");
    param += Objects.format("<option value=\"{0}\">{0}</option>","公安、警辅数字证书解锁卡");
    param += Objects.format("<option value=\"{0}\">{0}</option>","公安指纹数字证书");
    param += Objects.format("<option value=\"{0}\">{0}</option>","警辅指纹数字证书");
    param += Objects.format("<option value=\"{0}\">{0}</option>","专网数字证书");
    param += Objects.format("<option value=\"{0}\">{0}</option>","延长线");
    param += Objects.format("<option value=\"{0}\">{0}</option>","吊带");
    param += Objects.format("</select>");
    return param;
}

/**
 * 所有产品类型 多选通用
 */
function appendCheckBoxProductNames(e) {
    e.empty();
    var param = "";
    param += Objects.format("<div class=\"tdiv_a_1\">请选择产品类型：</div>");
    param += Objects.format("<div class=\"tdiv_a_3\"><input type=\"checkbox\" name=\"productListId\" class=\"productName\" value=\"1\">公安数字证书</div>");
    param += Objects.format("<div class=\"tdiv_a_3\"><input type=\"checkbox\" name=\"productListId\" class=\"productName\" value=\"2\">警辅数字证书</div>");
    param += Objects.format("<div class=\"tdiv_a_3\"><input type=\"checkbox\" name=\"productListId\" class=\"productName\" value=\"3\">公安、警辅数字证书解锁卡</div>");
    param += Objects.format("<div class=\"tdiv_a_3\"><input type=\"checkbox\" name=\"productListId\" class=\"productName\" value=\"4\">公安指纹数字证书</div>");
    param += Objects.format("<div class=\"clearfloat\"></div>");
    param += Objects.format("<div class=\"tdiv_a_1\"></div>");
    param += Objects.format("<div class=\"tdiv_a_3\"><input type=\"checkbox\" name=\"productListId\" class=\"productName\" value=\"5\">辅警指纹数字证书</div>");
    param += Objects.format("<div class=\"tdiv_a_3\"><input type=\"checkbox\" name=\"productListId\" class=\"productName\" value=\"6\">专网数字证书</div>");
    param += Objects.format("<div class=\"tdiv_a_3\"><input type=\"checkbox\" name=\"productListId\" class=\"productName\" value=\"0\">配件</div>");
    return param;
}

/**
 * 所有产品类型 多选通用
 */
function appendCheckBoxProductIdNames(e) {
    e.empty();
    var param = "";
    param += Objects.format("<div class=\"tdiv_a_1\">请选择产品类型：</div>");
    param += Objects.format("<div class=\"tdiv_a_3\"><input type=\"checkbox\" name=\"productListId\" class=\"productName\" value=\"1,公安数字证书\">公安数字证书</div>");
    param += Objects.format("<div class=\"tdiv_a_3\"><input type=\"checkbox\" name=\"productListId\" class=\"productName\" value=\"2,警辅数字证书\">警辅数字证书</div>");
    param += Objects.format("<div class=\"tdiv_a_3\"><input type=\"checkbox\" name=\"productListId\" class=\"productName\" value=\"3,公安、警辅数字证书解锁卡\">公安、警辅数字证书解锁卡</div>");
    param += Objects.format("<div class=\"tdiv_a_3\"><input type=\"checkbox\" name=\"productListId\" class=\"productName\" value=\"4,公安指纹数字证书\">公安指纹数字证书</div>");
    param += Objects.format("<div class=\"clearfloat\"></div>");
    param += Objects.format("<div class=\"tdiv_a_1\"></div>");
    param += Objects.format("<div class=\"tdiv_a_3\"><input type=\"checkbox\" name=\"productListId\" class=\"productName\" value=\"5,辅警指纹数字证书\">辅警指纹数字证书</div>");
    param += Objects.format("<div class=\"tdiv_a_3\"><input type=\"checkbox\" name=\"productListId\" class=\"productName\" value=\"6,专网数字证书\">专网数字证书</div>");
    param += Objects.format("<div class=\"tdiv_a_3\"><input type=\"checkbox\" name=\"productListId\" class=\"productName\" value=\"0,配件\">配件</div>");
    return param;
}
/**
 * 订单类型 通用
 * @param orderType
 * @returns {*}
 *
 */
function getOrderType(type) {
    var orderType = Number(type);
    if(orderType=="0"){
        return "订购";
    }else if(orderType=="1"){
        return "代购";
    }else if(orderType=="2"){
        return "项目内";
    }else if(orderType=="3"){
        return "赠送";
    }else if(orderType=="4"){
        return "提前发货";
    }else if(orderType=="5"){
        return "合同已包含";
    }else if(orderType=="6"){
        return "先货后款";
    }
    return "";
}
/**
 * 借转销
 * @param BorrowType
 *
 */
function getBorrowType(type) {
    var borrowType = Number(type);
    if(borrowType=="0") {
        return "";
    } else if(borrowType=="1") {
        return "-借转销";
    }
    return "";
}
/**
 *  用户 共用订单状态
 * @param status    订单状态
 * @param returnReason  退回原因
 */
function getOrderStatusUser(status,returnReason) {
    status = parseInt(status);
    returnReason=returnReason!=null?returnReason:"";
    if(status=="0"){
        return "待受理";
    }else if(status=="1"){
        return "已受理待付款";
    }else if(status=="7"){  //用户端待生产变更为生产中
        return "生产中";
    }else if(status=="2"){
        return "生产中";
    }else if(status=="3"){
        return "发运中";
    }else if(status=="4"){
        return "完成";
    }else if(status=="5"){
        return "已退回  "+ returnReason;
    }else if(status=="6"){
        return "作废";
    }
}

/**
 * 管理员共用订单状态
 * @param status
 * @returns {*}
 * href detailsOrder orderIndex
 */
function getOrderStatusAdmin(status) {
    if(status==0){
        return "待受理";
    }else if(status==1){
        return "待付款";
    }else if(status==7){
        return "待生产";
    }else if(status==2){
        return "生产未下";
    }else if(status==2.1){
        return "生产已下";
    }else if(status==2.2){
        return "生产完成";
    }else if(status==3){
        return "发运中";
    }else if(status==4){
        return "完成";
    }else if(status==5){
        return "退回";
    }else if(status==6){
        return "作废";
    }
    return "";
}



//分页样式
function computePagination() {
    var pageView = $("#pagination");
    pageView.empty();
    var pageCount = Math.floor(limit.total % limit.count == 0 ? limit.total / limit.count : limit.total / limit.count + 1);
    pageCount= pageCount==0 ? 1:pageCount;
    limit.pageCount = pageCount;
    //开头
    pageView.append("<li><a  class='pg-item pg-prev-up' data='1'></a></li>");
    //上一页
    upPage = currentPageIndex-1>1?currentPageIndex-1:1;
    pageView.append("<li><a  class='pg-item pg-prev' data='"+upPage+"'></a></li>");
    var limitFrontIndex = pageCount-currentPageIndex<3?7-(Math.ceil(pageCount)-currentPageIndex):3;
    for (var i = limitFrontIndex; i > 0; i--) {
        var p = currentPageIndex - i;
        if (p > 0) {
            pageView.append(itemTemplateOfpageView("pg-item pg-item", p, p, limit));
        }
    }

    var ss = currentPageIndex;
    var dd = limit;
    var limitIndex = currentPageIndex>3?3:currentPageIndex-1;
    pageView.append(itemTemplateOfpageView("pg-item pg-item active", currentPageIndex, currentPageIndex, limit));
    for (var i = 1; i < 7-limitIndex; i++) {
        var p = currentPageIndex + i;
        if (p <= limit.pageCount) {
            pageView.append(itemTemplateOfpageView("pg-item pg-item", p, p, limit));
        }
    }
    // }
    //下一页
    nextPage = currentPageIndex+1<=limit.pageCount?currentPageIndex+1:currentPageIndex;
    pageView.append("<li><a  class='pg-item pg-next' data='"+nextPage+"'></a></li>");
    //结尾
    pageView.append("<li><a  class='pg-item pg-next-down' data='"+limit.pageCount+"'></a></li>");
    pageView.append(Objects.format("<li class=\"disabled\"><span>{0}/{1}</span></li>", currentPageIndex, pageCount));
    pageView.append(Objects.format("<li ><span style=\"min-width: 175px;\">跳转<input  onkeyup=\"this.value=this.value.replace(/\\D/g,'')\" style=\"width:50px\" id=\"currentPage\" name=\"currentPage\"  type='text'>页 <input type=\"button\" value=\"跳转\"  onclick=\"limitSearch()\"></span> </li>" ));
}

//分页跳转
function limitSearch() {
    openLoading();
    var p = $("#currentPage").val();
    currentPageIndex = parseInt(p);
    var pageCount = Math.floor(limit.total % limit.count == 0 ? limit.total / limit.count : limit.total / limit.count + 1);
    if(p>pageCount){
        closeLoading();
        return false;
    }
    computePagination();
    parserAsyncClassDataFormData();
}
