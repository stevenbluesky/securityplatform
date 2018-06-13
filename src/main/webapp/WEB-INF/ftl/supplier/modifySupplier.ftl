<!-- 新增服务商页面 -->
<#include "../_head0.ftl"/>
<style type="text/css">
</style>
<div class="row-horizontal">
    <#--<div class="col-md-1"></div>-->
    <div class="col-md-12">
        <form class="form-horizontal" id="modifySupplierForm">
        <#if orgInfo??>
            <input id="organizationid" name="organizationid" type="hidden" value="${(orgInfo.organizationid)!?c}"/>
            <div class="text-center"><h1><@spring.message code="label.modifysupplier"/></h1></div>
        <#else>
            <div class="text-center"><h1><@spring.message code="label.addsupplier"/></h1></div>
        </#if>
            <hr>
            <div class="text-left"><h4><@spring.message code="label.supplierinfo"/></h4></div>
            <hr>
            <div class="form-group">
                <label for="name" class="col-sm-3 control-label"
                       style="text-align: left;"><@spring.message code="label.orgname"/>*</label>
                <div class="col-sm-9">
                    <input type="text" class="form-control" id="name" name="name" value="${(orgInfo.name)!}"
                           placeholder="<@spring.message code="label.orgname"/>">
                </div>
            </div>

            <div class="form-group">
                <label for="code" class="col-sm-3 control-label"  style="text-align: left;"><@spring.message code="label.orgcode"/>*</label>
                <div class="col-sm-9">
                <#if (orgInfo.code)??>${orgInfo.code}<#else><@spring.message code="label.none"/></#if>
                    <input type="hidden" class="form-control" id="code" name="code" value="${(orgInfo.code)!}" placeholder="<@spring.message code="label.orgcode"/>">
                </div>
            </div>
            <div class="form-group">
                <label for="detailaddress" class="col-sm-3 control-label"
                       style="text-align: left;"><@spring.message code="label.detailaddress"/>*</label>
                <div class="col-sm-9">
                    <input type="text" class="form-control" id="detailaddress" name="detailaddress"
                           value="${(orgInfo.address.detailaddress)!}"
                           placeholder="<@spring.message code="label.detailaddress"/>">
                </div>
            </div>
            <div class="form-group">
                <label for="country" class="col-sm-3 control-label"
                       style="text-align: left;"></label>
                <div class="col-sm-9">
                    <div class="row">
                        <div class="col-sm-4">
                            <select name="country" id="country" class="selectpicker"  data-size="10"
                                    title="<@spring.message code="label.choosecountry"/>">
                            </select>
                        </div>
                        <div class="col-sm-4">
                            <select id="province" name="province" class="selectpicker" data-size="10"
                                    title="<@spring.message code="label.chooseprovince"/>">
                            </select>
                        </div>
                        <div class="col-sm-4">
                            <select id="city" name="city" class="selectpicker" data-size="10"
                                    title="<@spring.message code="label.choosecity"/>">
                            </select>
                        </div>
                    </div>
                </div>
            </div>



            <div class="form-group">
                <label for="postal" class="col-sm-3 control-label"
                       style="text-align: left;"><@spring.message code="label.orgpostal"/></label>
                <div class="col-sm-9">
                    <input type="text" class="form-control" id="postal" name="postal"
                           value="${(orgInfo.address.postal)!}"
                           placeholder="<@spring.message code="label.orgpostal"/>">
                </div>
            </div>
            <div class="form-group">
                <label for="bdetailaddress" class="col-sm-3 control-label"
                       style="text-align: left;"><@spring.message code="label.bdetailaddress"/><#if (orgInfo.baddress)??>*</#if></label>
                <div class="col-sm-9">
                    <input type="text" class="form-control" name="bdetailaddress" id="bdetailaddress"
                           value="${(orgInfo.baddress.detailaddress)!}"
                           placeholder="<@spring.message code="label.bdetailaddress"/>">
                </div>
            </div>
            <div class="form-group">
                <label for="bcountry" class="col-sm-3 control-label"
                       style="text-align: left;">

                </label>
                <div class="col-sm-9">
                    <div class="row text-left">
                        <div class="col-sm-4">
                            <select name="bcountry" id="bcountry" class="selectpicker"  data-size="10"
                                    title="<@spring.message code="label.choosecountry"/>">
                            </select>
                        </div>
                        <div class="col-sm-4">
                            <select id="bprovince" name="bprovince" class="selectpicker"  data-size="10"
                                    title="<@spring.message code="label.chooseprovince"/>">
                            </select>
                        </div>
                        <div class="col-sm-4">
                            <select id="bcity" name="bcity" class="selectpicker"  data-size="10"
                                    title="<@spring.message code="label.choosecity"/>">
                            </select>
                        </div>
                    </div>
                </div>
            </div>


            <div class="form-group">
                <label for="bpostal" class="col-sm-3 control-label"
                       style="text-align: left;"><@spring.message code="label.borgpostal"/></label>
                <div class="col-sm-9">
                    <input type="text" class="form-control" name="bpostal" id="bpostal"
                           value="${(orgInfo.baddress.postal)!}"
                           placeholder="<@spring.message code="label.borgpostal"/>">
                </div>
            </div>

            <div style="visibility: hidden;">br</div>
            <div class="text-left"><h4><@spring.message code="label.supplierperson"/></h4></div>
            <hr>
            <div class="form-group">
                <label for="sname" class="col-sm-3 control-label"
                       style="text-align: left;"><@spring.message code="label.name"/></label>
                <div class="col-sm-9">
                    <input type="text" class="form-control" id="sname" name="sname" value="${(orgInfo.sname)!}"
                           placeholder="<@spring.message code="label.name"/>">
                </div>
            </div>

            <div class="form-group">
                <label for="sphonenumber"
                       class="col-sm-3 control-label"
                       style="text-align: left;"><@spring.message code="label.phonenumber"/></label>
                <div class="col-sm-9">
                    <input type="text" class="form-control" id="sphonenumber" name="sphonenumber"
                           value="${(orgInfo.sphonenumber)!}"
                           placeholder="<@spring.message code="label.phonenumber"/>">
                </div>
            </div>

            <div class="form-group">
                <label for="sfax" class="col-sm-3 control-label"
                       style="text-align: left;"><@spring.message code="label.fax"/></label>
                <div class="col-sm-9">
                    <input type="text" class="form-control" id="sfax" name="sfax" value="${(orgInfo.sfax)!}"
                           placeholder="<@spring.message code="label.fax"/>">
                </div>
            </div>

            <div class="form-group">
                <label for="semail" class="col-sm-3 control-label" style="text-align: left;"><@spring.message code="label.email"/></label>
                <div class="col-sm-9">
                    <input type="text" class="form-control" id="semail" name="semail" value="${(orgInfo.semail)!}"
                           placeholder="<@spring.message code="label.email"/>">
                </div>
            </div>

            <div style="visibility: hidden;">br</div>
            <div class="text-left"><h4><@spring.message code="label.sparentrorg"/></h4></div>
            <hr>
            <div class="form-group">
                <label for="csname"
                       class="col-sm-3 control-label" style="text-align: left;"><@spring.message code="label.parentrorgname"/></label>
                <div class="col-sm-9">
                    <input type="text" class="form-control" id="csname" name="csname" value="${(orgInfo.csname)!}"
                           placeholder="<@spring.message code="label.parentrorgname"/>">
                </div>
            </div>

            <div class="form-group">
                <label for="cscountry"
                       class="col-sm-3 control-label" style="text-align: left;"><@spring.message code="label.parentrorgaddress"/>
                <#if (orgInfo.csaddress)??>*</#if>
                </label>
                <div class="col-sm-9">
                    <div class="row text-left">
                        <div class="col-sm-4">
                            <select name="cscountry" id="cscountry" class="selectpicker"  data-size="10"
                                    title="<@spring.message code="label.choosecountry"/>">

                            </select>
                        </div>
                        <div class="col-sm-4">
                            <select id="csprovince" name="csprovince" class="selectpicker"  data-size="10"
                                    title="<@spring.message code="label.chooseprovince"/>">
                            </select>
                        </div>
                        <div class="col-sm-4">
                            <select id="cscity" name="cscity" class="selectpicker"  data-size="10"
                                    title="<@spring.message code="label.choosecity"/>">
                            </select>
                        </div>
                    </div>
                </div>
            </div>

            <div class="form-group">
                <label for="cspostal"
                       class="col-sm-3 control-label" style="text-align: left;"><@spring.message code="label.parentorgpostal"/></label>
                <div class="col-sm-9">
                    <input type="text" class="form-control" id="cspostal" name="cspostal"
                           value="${(orgInfo.csaddress.postal)!}"
                           placeholder="<@spring.message code="label.parentorgpostal"/>">
                </div>
            </div>

            <div style="visibility: hidden;">br</div>
            <div class="text-left"><h4><@spring.message code="label.sparentorgperson"/></h4></div>
            <hr>
            <div class="form-group">
                <label for="cspname" class="col-sm-3 control-label" style="text-align: left;"><@spring.message code="label.name"/></label>
                <div class="col-sm-9">
                    <input type="text" class="form-control" id="cspname" name="cspname"
                           value="${(orgInfo.cspname)!}" placeholder="<@spring.message code="label.name"/>">
                </div>
            </div>

            <div class="form-group">
                <label for="cspphonenumber"
                       class="col-sm-3 control-label" style="text-align: left;"><@spring.message code="label.phonenumber"/></label>
                <div class="col-sm-9">
                    <input type="text" class="form-control" id="cspphonenumber" name="cspphonenumber"
                           value="${(orgInfo.cspphonenumber)!}"
                           placeholder="<@spring.message code="label.phonenumber"/>">
                </div>
            </div>

            <div class="form-group">
                <label for="cspfax" class="col-sm-3 control-label" style="text-align: left;"><@spring.message code="label.fax"/></label>
                <div class="col-sm-9">
                    <input type="text" class="form-control" id="cspfax" name="cspfax" value="${(orgInfo.cspfax)!}"
                           placeholder="<@spring.message code="label.fax"/>">
                </div>
            </div>

            <div class="form-group">
                <label for="cspemail" class="col-sm-3 control-label" style="text-align: left;"><@spring.message code="label.email"/></label>
                <div class="col-sm-9">
                    <input type="text" class="form-control" id="cspemail" name="cspemail"
                           value="${(orgInfo.cspemail)!}" placeholder="<@spring.message code="label.email"/>">
                </div>
            </div>

            <div style="visibility: hidden;">br</div>


            <div style="visibility: hidden;">br</div>
            <div class="text-left" hidden><h4><@spring.message code="label.sprivilege"/></h4></div>

            <div class="row">
                <div class="col-sm-3"></div>
                <div class="col-sm-9">
                    <button id="btn-submit" class="btn btn-default"
                            style="width:50%	"><@spring.message code="label.submit"/></button>
                </div>
            </div>
            <br>
        </form>
    </div>
   <#-- <div class="col-md-1"></div>-->
</div>

<!-- JavaScript 部分 -->
<#--<script src="../static/js/addressController2.js"></script>-->
<script src="../static/js/validAddress.js"></script>

<script type="text/javascript">

    $(document).ready(function () {
        getCountry(-1);
        getbcountryB(-1);
        getcscountry(-1);
        // alert($("#organizationid").val() == null);
        $('#modifySupplierForm').bootstrapValidator({
            //       live: 'disabled',
            message: 'This value is not valid',
            feedbackIcons: {
                valid: 'glyphicon glyphicon-ok',
                invalid: 'glyphicon glyphicon-remove',
                validating: 'glyphicon glyphicon-refresh'
            },
            fields: {
                loginname: {
                    message: 'The loginname is not valid',
                    validators: {
                        notEmpty: {
                            message: 'The loginname is required and cannot be empty'
                        },
                        stringLength: {
                            min: 4,
                            max: 30,
                            message: 'The loginname must be more than 4 and less than 30 characters long'
                        },
                        regexp: {
                            regexp: /^[a-zA-Z0-9_\.]+$/,
                            message: 'The loginname can only consist of alphabetical, number, dot and underscore'
                        }
                    }
                },
                password: {
                    validators: {
                        notEmpty: {
                            message: 'The password is required and cannot be empty'
                        },
                        identical: {
                            field: 'repassword',
                            message: 'The password and its confirm are not the same'
                        }
                    }
                },
                repassword: {
                    validators: {
                        notEmpty: {
                            message: 'The repassword is required and cannot be empty'
                        },
                        identical: {
                            field: 'password',
                            message: 'The password and its confirm are not the same'
                        }
                    }
                },
                detailaddress: {
                    validators: {
                        notEmpty: {
                            message: 'The address is required and cannot be empty'
                        },
                    }
                },
                country: {
                    validators: {
                        notEmpty: {
                            message: 'The address is required and cannot be empty'
                        },
                    }
                },
                province: {
                    validators: {
                        notEmpty: {
                            message: 'The address is required and cannot be empty'
                        },
                    }
                },
                city: {
                    validators: {
                        notEmpty: {
                            message: 'The address is required and cannot be empty'
                        },
                    }
                },
                name: {
                    validators: {
                        notEmpty: {
                            message: 'The name is required and cannot be empty'
                        }
                    }
                }
            }
        });
    });

    $("#btn-submit").click(function () {
        // alert(validAddressB());
        if ($("#organizationid").val() != null) {
            /*$("#modifySupplierForm").bootstrapValidator('removeField', 'loginname');*/
            $("#modifySupplierForm").bootstrapValidator('removeField', 'password');
            $("#modifySupplierForm").bootstrapValidator('removeField', 'repassword');
        }
        $("#modifySupplierForm").bootstrapValidator('validate');//提交验证
        if(!validAddressB() || !validAddressC()){
            alert(formatterReturnStatus('-122'));
        }else {
            if ($("#modifySupplierForm").data('bootstrapValidator').isValid() && validAddressB() && validAddressC()) {//获取验证结果，如果成功，执行下面代码
                var url = "../org/add";
                $.ajax({
                    type: "POST",
                    dataType: "html",
                    async: false,
                    url: url,
                    data: $('#modifySupplierForm').serialize(),
                    success: function (data) {
                        var jsonObj = eval('(' + data + ')');
                        if (jsonObj['status'] == 1) {
                            alert("success");
                            //window.location.href = "supplierList";
                            parent.location.href = parent.location.href;
                        } else {
                            alert(formatterReturnStatus(jsonObj['msg']));
                        }
                    },
                    error: function (data) {
                        alert("error");
                    }
                });
            } else {
                alert("<@spring.message code="label.nullstrexception"/>");
            }
        }
    });

    /**
     * 回显地址下拉框
     * @param str
     * @param position
     */
    function getCountry(num) {
        var str = '';
        if(num==-1){
            str = "<option value=''></option>";
        }
        ajaxcountry(str,"#country");
    }
    function ajaxcountry(str,position) {
        $.ajax({
            type: "get",
            url: "../address/getCountry",
            async: true,
            success: function (data) {
                var country = "";
                if(position=='#country'){
                    <#if (orgInfo.address)?? && (orgInfo.address.country)??>country = "${(orgInfo.address.country)}"</#if>
                    for (var i = 0; i < data.length; i++) {
                        if(data[i].countryname == country) {
                            str += "<option value='" + data[i].countryid + "' selected='selected' >" + data[i].countryname + "</option>"
                            changeProvince(data[i].countryid);
                        }else {
                            str += '<option value=' + data[i].countryid + '>' + data[i].countryname + '</option>'
                        }
                    }
                }
                if(position=='#bcountry'){
                    <#if (orgInfo.baddress)?? && (orgInfo.baddress.country)??>country ="${(orgInfo.baddress.country)}"</#if>
                    for (var i = 0; i < data.length; i++) {
                        if(data[i].countryname == country) {
                            str += "<option value='" + data[i].countryid + "' selected='selected' >" + data[i].countryname + "</option>"
                            changeProvinceB(data[i].countryid);
                        }else {
                            str += '<option value=' + data[i].countryid + '>' + data[i].countryname + '</option>'
                        }
                    }
                }
                if(position=='#cscountry'){
                    <#if (orgInfo.csaddress)?? && (orgInfo.csaddress.country)??>country = "${(orgInfo.csaddress.country)}"</#if>
                    for (var i = 0; i < data.length; i++) {
                        if(data[i].countryname == country) {
                            str += "<option value='" + data[i].countryid + "' selected='selected' >" + data[i].countryname + "</option>"
                            changeProvinceC(data[i].countryid);
                        }else {
                            str += '<option value=' + data[i].countryid + '>' + data[i].countryname + '</option>'
                        }
                    }
                }
                $(position).html(str);
                $(position).selectpicker('refresh');
            }
        });
    }
    function ajaxprovince(str,position,id) {
        $.ajax({
            type: "get",
            url: "../address/getProvince?countryid=" + id,
            async: true,
            success: function (data) {
                var province = "";
                if(position=='#province'){
                    <#if (orgInfo.address)?? && orgInfo.address.province??>province = "${(orgInfo.address.province)}"</#if>
                    for (var i = 0; i < data.length; i++) {
                        if(data[i].provincename == province) {
                            str += "<option value='" + data[i].provinceid + "' selected='selected' >" + data[i].provincename + "</option>"
                            changeCity(data[i].provinceid);
                        }else {
                            str += '<option value=' + data[i].provinceid + '>' + data[i].provincename + '</option>'
                        }
                    }
                }
                if(position=='#bprovince'){
                    <#if (orgInfo.baddress)?? && orgInfo.baddress.province??>province = "${(orgInfo.baddress.province)}"</#if>
                    for (var i = 0; i < data.length; i++) {
                        if(data[i].provincename == province) {
                            str += "<option value='" + data[i].provinceid + "' selected='selected' >" + data[i].provincename + "</option>"
                            changeCityB(data[i].provinceid);
                        }else {
                            str += '<option value=' + data[i].provinceid + '>' + data[i].provincename + '</option>'
                        }
                    }
                }
                if(position=='#csprovince'){
                    <#if (orgInfo.csaddress)?? && orgInfo.csaddress.province??>province = "${(orgInfo.csaddress.province)}"</#if>
                    for (var i = 0; i < data.length; i++) {
                        if(data[i].provincename == province) {
                            str += "<option value='" + data[i].provinceid + "' selected='selected' >" + data[i].provincename + "</option>"
                            changeCityC(data[i].provinceid);
                        }else {
                            str += '<option value=' + data[i].provinceid + '>' + data[i].provincename + '</option>'
                        }
                    }
                }
                $(position).html(str);
                $(position).selectpicker('refresh');

            }
        });
    }
    function ajaxcity(str,position,id) {
        $.ajax({
            type: "get",
            url: "../address/getCity?provinceid=" + id,
            async: true,
            success: function (data) {
                var city = "";
                if(position=='#city'){
                    <#if (orgInfo.address)?? && orgInfo.address.city??>city = "${(orgInfo.address.city)}"</#if>
                }
                if(position=='#bcity'){
                    <#if (orgInfo.baddress)?? && orgInfo.baddress.city??>city = "${(orgInfo.baddress.city)}"</#if>
                }
                if(position=='#cscity'){
                    <#if (orgInfo.csaddress)?? && orgInfo.csaddress.city??>city = "${(orgInfo.csaddress.city)}"</#if>
                }
                for (var i = 0; i < data.length; i++) {
                    if(data[i].cityname == city) {
                        str += "<option value='" + data[i].cityid + "' selected='selected' >" + data[i].cityname + "</option>"
                    }else {
                        str += '<option value=' + data[i].cityid + '>' + data[i].cityname + '</option>'
                    }
                }
                $(position).html(str);
                $(position).selectpicker('refresh');

            }
        });
    }
    $("#country").change(function () {
        var countryid = $("#country").val();
        changeProvince(countryid);
    });
    function changeProvince(countryid) {
        var str ="<option value=''></option>";
        ajaxprovince(str,"#province",countryid);
    }
    $("#province").change(function () {
        var provinceid = $("#province").val();
        changeCity(provinceid);
    });
    function changeCity(provinceid) {
        var str ="<option value=''></option>";
        ajaxcity(str,"#city",provinceid);
    }
    //第二处地址栏
    function getbcountryB(num) {
        var str = '';
        if(num==-1){
            str = "<option value=''></option>";
        }
        ajaxcountry(str,"#bcountry");
    }
    //第二处切换国家
    $("#bcountry").change(function () {
        var countryid = $("#bcountry").val();
        changeProvinceB(countryid);
    });
    function changeProvinceB(countryid) {
        var str ="<option value=''></option>";
        ajaxprovince(str,"#bprovince",countryid);
    }
    //第二处切换省份
    $("#bprovince").change(function () {
        var provinceid = $("#bprovince").val();
        changeCityB(provinceid);
    });
    function changeCityB(provinceid) {
        var str="<option value=''></option>";
        ajaxcity(str,"#bcity",provinceid)
    }
    //第三处地址栏
    function getcscountry(num) {
        var str = '';
        if(num==-1){
            str = "<option value=''></option>";
        }
        ajaxcountry(str,"#cscountry");
    }
    //第三处切换国家
    $("#cscountry").change(function () {
        var countryid = $("#cscountry").val();
        changeProvinceC(countryid);
    });
    function changeProvinceC(countryid) {
        var str = "<option value=''></option>";
        ajaxprovince(str,"#csprovince",countryid)
    }
    //第三处切换省份
    $("#csprovince").change(function () {
        var provinceid = $("#csprovince").val();
        changeCityC(provinceid);
    });
    function changeCityC(provinceid) {
        var str = "<option value=''></option>";
        ajaxcity(str,"#cscity",provinceid)
    }
</script>

<#include "../_foot0.ftl"/>