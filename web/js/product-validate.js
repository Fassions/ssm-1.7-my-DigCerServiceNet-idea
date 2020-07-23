//
function productAppend() {
    productSuffixProductName = productSuffix[0];
    productSuffixProductType = productSuffix[1];
    productSuffixProductSuffix = productSuffix[2];
    productSuffixProductsupplier = productSuffix[3];
    $productSuffixId = productSuffix[4];
    var json = {product: {}};
    json.product[""] = _val;



    productSuffix = $(this).val().split("`");
    productSuffixProductName = productSuffix[0];
    productSuffixProductType = productSuffix[1];
    productSuffixProductSuffix = productSuffix[2];
    productSuffixProductsupplier = productSuffix[3];
    $productSuffixId = productSuffix[4];
    $("#provinceCode").val($("#areaProvice").val());
    $("#cityCode").val($("#areaCity").val());
    $("#productName").val(productSuffixProductName);

    $("#productListId").val($productSuffixId);

//				$("#productType").append('<option value="'+productSuffix[1]+'">'+productSuffix[1]+'</option>');
//				$("#labelSuffix").text(productSuffix[2]);
//				$("#supplier").text(productSuffix[3]);
//				$("#productPrefix").val($productPrefix.prefixName);
    var productTable = $("#productTable");
    var param = "";
    $(".removeProductTable").remove();


    productSuffixProductType = productSuffixProductType.split(",");
    param +=Objects.format("<div class=\" removeProductTable\">");
    param +=Objects.format("<div class=\"tdiv_a_z\">");
    param +=Objects.format("<div class=\"tdiv_a_1\">产品型号：</div>");
    param +=Objects.format("<div class=\"tdiv_a_2\">");
    param +=Objects.format("<select name=\"productType\" style=\"width:200px\"  id=\"productType\">");
    for( var type in productSuffixProductType){
        //公安指纹数字证书型号特殊
        if($productSuffixId=="4"){
            if($productPrefix!=null){
                if($productPrefix.prefixName=="GD"){
                    param +=Objects.format("<option value=\"{0}\">{0}</option>",productSuffixProductType[1]);
                    break;
                }else {
                    param +=Objects.format("<option value=\"{0}\">{0}</option>",productSuffixProductType[0]);
                    break;
                }
            }
        }
        param +=Objects.format("<option onclick=\"clickProductType('{0}')\" value=\"{0}\">{0}</option>",productSuffixProductType[type]);
    }
    param +=Objects.format("</select>");
    param +=Objects.format("<span class=\"red_label\">★</span>");
    param +=Objects.format("</div>");

    param +=Objects.format("<div class=\"tdiv_a_1\">");
    param +=Objects.format("单价(元)：");
    param +=Objects.format("</div>");
    param +=Objects.format("<div class=\"tdiv_a_2\">");
    if(productSuffixProductType[0]=='延长线'){
        param +=Objects.format("<input type=\"text\" name=\"productPrice\" readonly=\"readonly\"  style='background-color:gainsboro' value='4' onkeyup=\"this.value=this.value.replace(/[^\\d.]/g,'')\" /><span class=\"red_label\">★</span>");
    }else if(productSuffixProductType[0]=='吊带'){
        param +=Objects.format("<input type=\"text\" name=\"productPrice\" readonly=\"readonly\"  style='background-color:gainsboro' value='2' onkeyup=\"this.value=this.value.replace(/[^\\d.]/g,'')\" /><span class=\"red_label\">★</span>");
    }else {
        param +=Objects.format("<input type=\"text\" name=\"productPrice\"onkeyup=\"this.value=this.value.replace(/[^\\d.]/g,'')\" /><span class=\"red_label\">★</span>");
    }
    param +=Objects.format("</div>");
    param +=Objects.format("<div class=\"clearfloat\"></div>");

    //配件
    if($productSuffixId=="0"){
        param +=Objects.format("<div class=\"tdiv_a_z\">");
        param +=Objects.format("<div class=\"tdiv_a_1\">");
        param +=Objects.format("数量(个)：");
        param +=Objects.format("</div>");
        param +=Objects.format("<div class=\"tdiv_a_2\">");
        param +=Objects.format("<input type=\"text\"  name=\"productNumber\" onkeyup=\"this.value=this.value.replace(/\\D/g,'')\" />");
        param +=Objects.format("</div>");
        param +=Objects.format("<div class=\"tdiv_a_1\">");
        param +=Objects.format("<input type=\"button\" class=\"button_left15\" onclick=\"javascript:addProduct();\" value=\"加入已选\"/>");
        param +=Objects.format("</div>");
        param +=Objects.format("<div class=\"clearfloat\"></div>");
        param +=Objects.format("</div>");
        productTable.append(param);
        return;
    }

    //公安，辅警数字证书
    if($productSuffixId=="3"){
        $("#supplier").val(productSuffixProductsupplier);
        $("#labelSuffix").val(productSuffixProductSuffix);
        param +=Objects.format("<div class=\" removeProductTable\">");
        param +=Objects.format("<div class=\"tdiv_a_z\" style=\"min-width: 1000px;\">");
        param +=Objects.format("<div class=\"tdiv_a_1\">起始编号：</div>");
        param +=Objects.format("<input type=\"text\" name=\"labelPrefix\" class=\"spanLabelLeft\" id=\"labelPrefix\"  style=\"width: 30px\" value=\"{0}\"/>",$productPrefix!=null?($productPrefix.prefixName!=undefined?$productPrefix.prefixName:""):"");


        param +=Objects.format("<input type=\"text\" id=\"supplier\" class=\"spanLabel\" readonly=\"readonly\" name=\"supplier\" value=\"{0}\"/>",productSuffixProductsupplier);
        param +=Objects.format("<input type=\"text\"  name=\"productRegionLabelInfix\" onkeyup=\"this.value=this.value.replace(/\\D/g,'')\" class=\"width_30\">");
        param +=Objects.format("<input type=\"text\" id=\"labelSuffix\" class=\"spanLabel\" readonly=\"readonly\" name=\"labelSuffix\" value=\"{0}\"/>",productSuffixProductSuffix);
        param +=Objects.format("<input type=\"text\" name=\"labelNumStart\" onkeyup=\"this.value=this.value.replace(/\\D/g,'')\" class=\"width_30\">");
        param +=Objects.format("-");
        param +=Objects.format("<input type=\"text\" name=\"labelNumEnd\" onkeyup=\"this.value=this.value.replace(/\\D/g,'')\" class=\"width_30\">");
        param +=Objects.format("<input type=\"button\" class=\"button_left15\" onclick=\"javascript:addProduct();\" value=\"加入已选\"> </td>");
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
        param +=Objects.format("<div class=\"tdiv_a_1\">起始编号：</div>");
        param +=Objects.format("<input type=\"text\" id=\"labelSuffix\" class=\"spanLabelLeft\" style='    background-color: gainsboro;' readonly=\"readonly\" name=\"labelSuffix\" value=\"{0}\"/>",productSuffixProductSuffix);
        param +=Objects.format("<input type=\"text\" name=\"labelPrefix\" class=\"spanLabelLeft\" id=\"labelPrefix\"  style=\"width: 30px\" value=\"{0}\"/>",$productPrefix!=null?($productPrefix.prefixName!=undefined?$productPrefix.prefixName:""):"");

        param +=Objects.format("<input name=\"labelNumStart\" onkeyup=\"this.value=this.value.replace(/\\D/g,'')\"  type=\"text\">");
        param +=Objects.format("-");
        param +=Objects.format("<input name=\"labelNumEnd\" onkeyup=\"this.value=this.value.replace(/\\D/g,'')\" type=\"text\">");
        param +=Objects.format("<input type=\"button\" class=\"button_left15\" onclick=\"javascript:addProduct();\" value=\"加入已选\"> </td>");
        param +=Objects.format("</p></div>");
        productTable.append(param);
        return;
    }
    //公安指纹数字证书
    if($productSuffixId=="4"){

        param +=Objects.format("<div class=\" removeProductTable\">");
        param +=Objects.format("<div class=\"tdiv_a_z\" style=\"min-width: 1000px;\">");
        param +=Objects.format("<div class=\"tdiv_a_1\">起始编号：</div>");
        param +=Objects.format("<input type=\"text\" class='spanLabelLeft' name=\"labelPrefix\" id=\"labelPrefix\"  style=\"width: 30px\" value=\"{0}\"/>",$productPrefix!=null?($productPrefix.prefixName!=undefined?$productPrefix.prefixName:""):"");
        //浙江后缀为Z 无中间字符
        if($productPrefix!=null && $productPrefix.prefixName=="ZJ"){
            param +=Objects.format("<input name=\"labelNumStart\" onkeyup=\"this.value=this.value.replace(/\\D/g,'')\"  type=\"text\">");
            param +=Objects.format("-");
            param +=Objects.format("<input name=\"labelNumEnd\" onkeyup=\"this.value=this.value.replace(/\\D/g,'')\"  type=\"text\">");

            param +=Objects.format("<input type=\"text\" class='spanLabel' readonly=\"readonly\" id=\"labelSuffix\" name=\"labelSuffix\" value=\"{0}\"/>",productSuffixProductSuffix.split(",")[1]);
        }else {
            param +=Objects.format("<input type=\"text\" class='spanLabel' readonly=\"readonly\" id=\"supplier\" name=\"supplier\" value=\"{0}\"/>",productSuffixProductsupplier);
            param +=Objects.format("<input name=\"labelNumStart\" onkeyup=\"this.value=this.value.replace(/\\D/g,'')\"  type=\"text\">");
            param +=Objects.format("-");
            param +=Objects.format("<input name=\"labelNumEnd\" onkeyup=\"this.value=this.value.replace(/\\D/g,'')\"  type=\"text\">");
            param +=Objects.format("<input type=\"text\" class='spanLabel' readonly=\"readonly\" id=\"labelSuffix\" name=\"labelSuffix\" value=\"{0}\"/>",productSuffixProductSuffix.split(",")[0]);
        }
        param +=Objects.format("<input type=\"button\" class=\"button_left15\" onclick=\"javascript:addProduct();\" value=\"加入已选\"> </td>");
        param +=Objects.format("</p></div>");
        productTable.append(param);
        return;
    }else {
        param +=Objects.format("<div class=\" removeProductTable\">");
        param +=Objects.format("<div class=\"tdiv_a_z\" style=\"min-width: 1000px;\">");
        param +=Objects.format("<div class=\"tdiv_a_1\">起始编号：</div>");
        param +=Objects.format("<input type=\"text\" class='spanLabelLeft' name=\"labelPrefix\" id=\"labelPrefix\"  style=\"width: 30px\" value=\"{0}\"/>",$productPrefix!=null?($productPrefix.prefixName!=undefined?$productPrefix.prefixName:""):"");

        param +=Objects.format("<input type=\"text\" class='spanLabel' readonly=\"readonly\" id=\"supplier\" name=\"supplier\" value=\"{0}\"/>",productSuffixProductsupplier);
        param +=Objects.format("<input name=\"labelNumStart\" onkeyup=\"this.value=this.value.replace(/\\D/g,'')\"  type=\"text\">");
        param +=Objects.format("-");
        param +=Objects.format("<input name=\"labelNumEnd\" onkeyup=\"this.value=this.value.replace(/\\D/g,'')\"  type=\"text\">");
        param +=Objects.format("<input type=\"text\" class='spanLabel' readonly=\"readonly\" id=\"labelSuffix\" name=\"labelSuffix\" value=\"{0}\"/>",productSuffixProductSuffix);
        param +=Objects.format("<input type=\"button\" class=\"button_left15\" onclick=\"javascript:addProduct();\" value=\"加入已选\"> </td>");
        param +=Objects.format("</p></div>");
        productTable.append(param);
        return;
    }
}