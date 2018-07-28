<!-- 录入电话卡信息页面 -->
<#include "../_head0.ftl"/>
    <div class="row-horizontal">

            <form id="defaultForm" method="POST">
                <div class="text-center"><h1><@spring.message code='label.modifyUserInfo'/></h1></div>
                <hr>
            <#--<#if admin?? && admin == true>-->
                <div class="form-group">
                    <label for="organizationid"
                           class="col-sm-3 control-label" style="text-align: left;"><@spring.message code="label.parentsupplier"/>*</label>
                    <div class="col-sm-9">
                        <select id="organizationid" name="organizationid" class="selectpicker" data-live-search="true"  data-size="10"
                                title="<@spring.message code="label.choosesupplier"/>">
                        </select>
                    </div>
                </div>
            <#--<#else>
                <div class="form-group">
                    <label for="organizationid"
                           class="col-sm-3 control-label" style="text-align: left;"><@spring.message code="label.parentsupplier"/></label>
                    <div class="col-sm-9">
                            <#if userVO.serviceprovider??>${(userVO.serviceprovider!)}<#else><@spring.message code='label.none'/></#if>
                            <input type="hidden" class="form-control" id="organizationid" name="organizationid" value="${(userVO.organizationid)!}">
                    </div>
                </div>
            </#if>-->


                <div  class="form-group">
                    <label for="codepostfix"  class="col-sm-3 control-label"><@spring.message code='label.monitoringstation'/>*</label>
                    <div class="col-sm-9">
                        <select name="monitoringstationid" id="monitoringstation" class="selectpicker" data-live-search="true"
                                title="<@spring.message code="label.monitoringstation"/>">
                            <option value="1" <#if userVO.monitoringstationid??&&userVO.monitoringstationid==1>selected</#if>>Lanvac Surveillance Inc</option>
                            <option value="0" <#if userVO.monitoringstationid??&&userVO.monitoringstationid==0>selected</#if>><@spring.message code="label.none"/></option>
                        </select>
                    </div>
                </div>

                <div  class="form-group"><#--报警中心所需用户代码DNIS-->
                    <label for="codepostfix"  class="col-sm-3 control-label"><@spring.message code='label.groupid'/>*</label>
                    <div class="col-sm-9">
                        <#--<span id="iback"><#if userVO.groupid??&&userVO.groupid!=''>${(userVO.groupid)!}<#else><@spring.message code='label.none'/></#if></span>-->
                        <input  class="form-control" id="inscode" name="groupid" value="${(userVO.groupid)!}" placeholder="<@spring.message code='label.groupid'/>"/>
                    </div>
                </div>

                <div  class="form-group"><#--报警中心所需accountnumber-->
                    <label for="codepostfix"  class="col-sm-3 control-label"><@spring.message code='label.code'/>*</label>
                    <div class="col-sm-9">
                        <#--<span id="sback"><#if userVO.supcode??&&userVO.supcode!=''>${(userVO.supcode)!}<#else><@spring.message code='label.none'/></#if></span>-->
                        <input  class="form-control" id="supcode" name="supcode" value="${(userVO.supcode)!}" placeholder="<@spring.message code='label.code'/>"/>
                    </div>
                </div>
                <div  class="form-group">
                    <label for="codepostfix"  class="col-sm-3 control-label"><@spring.message code='label.aibaseid'/></label>
                    <div class="col-sm-9">
                    <span id="uback"><#if (userVO.usercode)??>${userVO.usercode}<#else><@spring.message code="label.none"/></#if></span>
                        <input type="hidden" class="form-control" id="codepostfix" name="usercode" value="${(userVO.usercode)!}" placeholder="<@spring.message code='label.usercodepostfix'/>">
                    </div>
                </div>
                <div  class="form-group">
                    <label for="firstname"  class="col-sm-3 control-label"><@spring.message code='label.firstname'/>*</label>
                    <div class="col-sm-9">
                        <input id="userid" name="userid" type="hidden" value="${(userVO.userid)!?c}"/>
                        <input type="text" class="form-control" id="firstname" name="firstname" value="${(userVO.firstname)!}" placeholder="<@spring.message code='label.firstname'/>">
                    </div>
                </div>
                <div  class="form-group">
                    <label for="lastname"  class="col-sm-3 control-label"><@spring.message code='label.lastname'/>*</label>
                    <div class="col-sm-9">
                        <input type="text" class="form-control" id="lastname" name="lastname" value="${(userVO.lastname)!}" placeholder="<@spring.message code='label.lastname'/>">
                    </div>
                </div>
                <div  class="form-group">
                    <label for="phonenumber"  class="col-sm-3 control-label"><@spring.message code='label.phonenumber'/></label>
                    <div class="col-sm-9">
                        <input type="text" class="form-control" id="phonenumber" name="phonenumber" value="${(userVO.phonenumber)!}" placeholder="<@spring.message code='label.phonenumber'/>">
                    </div>
                </div>
                <div  class="form-group">
                    <label for="email"  class="col-sm-3 control-label"><@spring.message code='label.email'/></label>
                    <div class="col-sm-9">
                        <input type="text" class="form-control" id="email" name="email" value="${(userVO.email)!}" placeholder="<@spring.message code='label.email'/>">
                    </div>
                </div>
                <div  class="form-group">
                    <label for="fax"  class="col-sm-3 control-label"><@spring.message code='label.fax'/></label>
                    <div class="col-sm-9">
                        <input type="text" class="form-control" id="fax" name="fax" value="${(userVO.fax)!}" placeholder="<@spring.message code='label.fax'/>">
                    </div>
                </div>

                <div  class="form-group">
                    <label for="detailaddress"  class="col-sm-3 control-label"><@spring.message code='label.detailaddress'/></label>
                    <div class="col-sm-9">
                        <input type="text" class="form-control" id="detailaddress" name="detailaddress" value="${(userVO.detailaddress)!}" placeholder="<@spring.message code='label.detailaddress'/>">
                    </div>
                </div>
                <div  class="form-group">
                    <label for="address"  class="col-sm-3 control-label"></label>
                    <div class="col-sm-9">
                        <div class="row text-left">

                            <div class="col-sm-4">
                                <select name="country" id="country" class="selectpicker" data-size="10" title="<@spring.message code='label.choosecountry'/>">
                                </select>
                            </div>

                            <div class="col-sm-4">
                                <select id="province" name="province" class="selectpicker" data-size="10" title="<@spring.message code='label.chooseprovince'/>">
                                </select>
                            </div>

                            <div class="col-sm-4">
                                <select id="city" name="city" class="selectpicker" data-size="10" title="<@spring.message code='label.choosecity'/>">
                                </select>
                            </div>
                        </div>
                    </div>
                </div>




                <div  class="form-group">
                    <label for="deviceid"  class="col-sm-3 control-label"><@spring.message code='label.gatewayID'/></label>
                    <div class="col-sm-9">
                        <input type="text" class="form-control" id="deviceid" name="deviceid" value="${(userVO.deviceid)!}" placeholder="<@spring.message code='label.gatewayID'/>">
                    </div>
                </div>

                <div  class="form-group">
                    <label for="serialnumber"  class="col-sm-3 control-label"><@spring.message code='label.phonecardid'/></label>
                    <div class="col-sm-9">
                        <input type="text" class="form-control" id="serialnumber" name="serialnumber" value="${(userVO.serialnumber)!}" placeholder="<@spring.message code='label.phonecardid'/>">
                    </div>
                </div>

                <div id="msg" class="text-center"></div>
               <#-- <div class="row text-center">
                    <div class="col-sm-6"><button id="btn-submit" type="buttom" class="btn btn-default" style="width:25%;"><@spring.message code='label.submit'/></button></div>
                </div>-->
                <div class="row">
                    <div class="col-sm-3"></div>
                    <div class="col-sm-9">
                        <button id="btn-submit" class="btn btn-default"
                                style="width:50%"><@spring.message code="label.submit"/></button>
                    </div>
                </div>
            </form>

        </div>

    </div>

<!-- JavaScript 部分 -->
    <script type="text/javascript">

        $(document).ready(function() {
            getCountry(-1);

            $('#defaultForm').bootstrapValidator({
                message: 'This value is not valid',
                feedbackIcons: {
                    valid: 'glyphicon glyphicon-ok',
                    invalid: 'glyphicon glyphicon-remove',
                    validating: 'glyphicon glyphicon-refresh'
                },
                fields: {
                    organizationid: {
                        message: 'The Distributor is not valid',
                        validators: {
                            notEmpty: {
                                message: 'The Distributor is required and cannot be empty'
                            }
                        }
                    },
                    groupid: {
                        message: 'The DNIS is not valid',
                        validators: {
                            notEmpty: {
                                message: 'The DNIS is required and cannot be empty'
                            }, stringLength: {
                                min: 5,
                                max: 5,
                                message: 'The code must be 5 characters long'
                            }
                        }
                    },supcode: {
                        validators: {
                            notEmpty: {
                                message: 'The code is required and cannot be empty'
                            }, stringLength: {
                                min: 4,
                                max: 4,
                                message: 'The code must be 4 characters long'
                            },
                            regexp: {
                                regexp: /[1-9][0-9][0-9][0-9]/g,
                                message: 'The code format is 1001-9999.'
                            }
                        }
                    },
                    firstname: {
                        message: 'The firstname is not valid',
                        validators: {
                            notEmpty: {
                                message: 'The firstname is required and cannot be empty'
                            }
                        }
                    },
                    lastname: {
                        message: 'The lastname is not valid',
                        validators: {
                            notEmpty: {
                                message: 'The lastname is required and cannot be empty'
                            }
                        }
                    }
                }
            });
        });
        getParentOrg();

        function getParentOrg() {
            $.ajax({
                type: "get",
                url: "../org/listAllSupplier",
                async: true,
                success: function (data) {
                    var parentorgid ="";
                    <#if userVO.organizationid??>parentorgid='${(userVO.organizationid)}' </#if>
                    var str ="";
                    for (var i = 0; i < data.length; i++) {
                        //str += '<option value=' + data[i].organizationid + '>' + data[i].name + '</option>'
                        if(data[i].organizationid == parentorgid) {
                            str += "<option value='" + data[i].organizationid + "' selected='selected' >" + data[i].name + "</option>"
                        }else {
                            str += '<option value=' + data[i].organizationid + '>' + data[i].name + '</option>'
                        }
                    }
                    $("#organizationid").html(str);

                    $("#organizationid").selectpicker('refresh');

                }
            });
        }
        $("#btn-submit").click(function () {
            $("#defaultForm").bootstrapValidator('validate');//提交验证
                if ($("#defaultForm").data('bootstrapValidator').isValid()) {//获取验证结果，如果成功，执行下面代码
                    var url = "../user/modify";
                    $.ajax({
                        type: "POST",
                        dataType: "html",
                        url: url,
                        data: $('#defaultForm').serialize(),
                        success: function (data) {
                            var jsonObj = eval('(' + data + ')');
                            if (jsonObj['status'] == 1) {
                                alert("success");
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
                    alert(lan.notempty);
                }
        });
        //为了地址下拉框回显
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
                    <#if userVO.countryname??>country = '${(userVO.countryname)}'</#if>
                    for (var i = 0; i < data.length; i++) {
                        if(data[i].countryname == country) {
                            str += "<option value='" + data[i].countryid + "' selected='selected' >" + data[i].countryname + "</option>"
                            changeProvince(data[i].countryid);
                        }else {
                            str += '<option value=' + data[i].countryid + '>' + data[i].countryname + '</option>'
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
                    <#if userVO.provincename??>province = '${(userVO.provincename)}'</#if>
                    for (var i = 0; i < data.length; i++) {
                        if(data[i].provincename == province) {
                            str += "<option value='" + data[i].provinceid + "' selected='selected' >" + data[i].provincename + "</option>"
                            changeCity(data[i].provinceid);
                        }else {
                            str += '<option value=' + data[i].provinceid + '>' + data[i].provincename + '</option>'
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
                    <#if userVO.cityname??>city = '${(userVO.cityname)}'</#if>
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
                }
        );
        function changeProvince(countryid) {
            var str ="<option value=''></option>";
            ajaxprovince(str,"#province",countryid);
        }
        function changeCity(provinceid) {
            var str ="<option value=''></option>";
            ajaxcity(str,"#city",provinceid);
        }
        $("#province").change(function () {
                    var provinceid = $("#province").val();
                    changeCity(provinceid);
                }
        );
        /*$("#organizationid").change(function () {
            var suporgid = $("#organizationid").val();
            var userid = $-{(userVO.userid)?c};
            if(suporgid==""||suporgid==null||suporgid==undefined){
                return ;
            }
            $.ajax({
                type: 'get',
                url: '../user/findCodes',
                contentType: 'application/json',
                traditional: true,
                data: {
                    suporgid:suporgid,
                    userid:userid
                },
                success: function (data) {//返回json结果
                    var split = data.split("#");
                    $("#sback").html(split[0]);
                    document.getElementById("supcode").value= split[0];
                },
                error: function () {// 请求失败处理函数
                }

            });
        });*/
    </script>
<#include "../_foot0.ftl"/>