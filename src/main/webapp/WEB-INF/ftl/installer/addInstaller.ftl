<!-- 新增安装商页面 -->
<#include "/_head0.ftl"/>
<#import "/spring.ftl" as spring/>
    <div class="row-horizontal">
        <div class="col-md-1"></div>
        <div class="col-md-10">
            <form id="defaultForm" method="POST">
                <#if orgInfo??>
                    <input id="orgid" type="hidden" value="${(orgInfo.organizationid)!}"/>
                    <div class="text-center"><h1><@spring.message code="label.modifyinstaller"/></h1></div>
                <#else>
                    <div class="text-center"><h1><@spring.message code="label.addinstaller"/></h1></div>
                </#if>

                <div class="text-left"><h4><@spring.message code="label.installerinfo"/></h4></div>

				<#if admin?? && admin == true>
                <div class="form-group">
                    <label for="parentorgid"
                           class="col-sm-2 control-label"><@spring.message code="label.parentsupplier"/></label>
                    <div class="col-sm-10">
                        <select id="parentorgid" name="parentorgid" class="selectpicker" data-live-search="true"
                                title="<@spring.message code="label.choosesupplier"/>">
                        </select>
                    </div>
                </div>
                </#if>

                <div class="form-group">
                    <label for="name"
                           class="col-sm-2 control-label"><@spring.message code="label.orgname"/>*</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="name" name="name" value="${(orgInfo.name)!}"
                               placeholder="<@spring.message code="label.orgname"/>">
                    </div>
                </div>

                <div class="form-group">
                    <label for="code" class="col-sm-2 control-label"><@spring.message code="label.orgcode"/>*</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="code" name="code" value="${(orgInfo.code)!}"
                               placeholder="<@spring.message code="label.orgcode"/>">
                    </div>
                </div>

                <div class="form-group">
                    <label for="country" class="col-sm-2 control-label"><@spring.message code="label.orgaddress"/>
                        *</label>
                    <div class="col-sm-10">
                        <div class="row text-left">
                            <div class="col-sm-4">
                                <select name="country" id="country" class="selectpicker"
                                        title="<@spring.message code="label.choosecity"/>">
                                </select>
                            </div>
                            <div class="col-sm-4">
                                <select id="province" name="province" class="selectpicker"
                                        title="<@spring.message code="label.chooseprovince"/>">
                                </select>
                            </div>
                            <div class="col-sm-4">
                                <select id="city" name="city" class="selectpicker"
                                        title="<@spring.message code="label.choosecity"/>">
                                </select>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <label for="detailaddress"
                           class="col-sm-2 control-label"><@spring.message code="label.detailaddress"/></label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="detailaddress" name="detailaddress"
                               value="${(orgInfo.address.detailaddress)!}"
                               placeholder="<@spring.message code="label.detailaddress"/>">
                    </div>
                </div>

                <div class="form-group">
                    <label for="postal" class="col-sm-2 control-label"><@spring.message code="label.orgpostal"/></label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="postal" name="postal"
                               value="${(orgInfo.address.postal)!}"
                               placeholder="<@spring.message code="label.orgpostal"/>">
                    </div>
                </div>

                <div class="form-group">
                    <label for="bcountry"
                           class="col-sm-2 control-label"><@spring.message code="label.borgaddress"/></label>
                    <div class="col-sm-10">
                        <div class="row text-left">
                            <div class="col-sm-4">
                                <select name="bcountry" id="bcountry" class="selectpicker"
                                        title="<@spring.message code="label.choosecountry"/>">
                                </select>
                            </div>
                            <div class="col-sm-4">
                                <select id="bprovince" name="bprovince" class="selectpicker"
                                        title="<@spring.message code="label.chooseprovince"/>">
                                </select>
                            </div>
                            <div class="col-sm-4">
                                <select id="bcity" name="bcity" class="selectpicker"
                                        title="<@spring.message code="label.choosecity"/>">
                                </select>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <label for="bdetailaddress"
                           class="col-sm-2 control-label"><@spring.message code="label.bdetailaddress"/></label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" name="bdetailaddress" id="bdetailaddress"
                               value="${(orgInfo.baddress.detailaddress)!}"
                               placeholder="<@spring.message code="label.bdetailaddress"/>">
                    </div>
                </div>
                <div class="form-group">
                    <label for="bpostal"
                           class="col-sm-2 control-label"><@spring.message code="label.borgpostal"/></label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" name="bpostal" id="bpostal"
                               value="${(orgInfo.baddress.postal)!}"
                               placeholder="<@spring.message code="label.borgpostal"/>">
                    </div>
                </div>

                <div class="text-left"><h4><@spring.message code="label.installerperosn"/></h4></div>

                <div class="form-group">
                    <label for="sname" class="col-sm-2 control-label"><@spring.message code="label.name"/></label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="sname" name="sname" value="${(orgInfo.sname)!}"
                               placeholder="<@spring.message code="label.name"/>">
                    </div>
                </div>

                <div class="form-group">
                    <label for="sphonenumber"
                           class="col-sm-2 control-label"><@spring.message code="label.phonenumber"/></label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="sphonenumber" name="sphonenumber"
                               value="${(orgInfo.sphonenumber)!}"
                               placeholder="<@spring.message code="label.phonenumber"/>">
                    </div>
                </div>

                <div class="form-group">
                    <label for="sfax" class="col-sm-2 control-label"><@spring.message code="label.fax"/></label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="sfax" name="sfax" value="${(orgInfo.sfax)!}"
                               placeholder="<@spring.message code="label.fax"/>">
                    </div>
                </div>

                <div class="form-group">
                    <label for="semail" class="col-sm-2 control-label"><@spring.message code="label.email"/></label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="semail" name="semail" value="${(orgInfo.semail)!}"
                               placeholder="<@spring.message code="label.email"/>">
                    </div>
                </div>

                <div class="text-left"><h4><@spring.message code="label.parentInstaller"/></h4></div>

                <div class="form-group">
                    <label for="csname"
                           class="col-sm-2 control-label"><@spring.message code="label.parentInstallername"/></label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="csname" name="csname" value="${(orgInfo.csname)!}"
                               placeholder="<@spring.message code="label.parentInstallername"/>">
                    </div>
                </div>

                <div class="form-group">
                    <label for="cscountry"
                           class="col-sm-2 control-label"><@spring.message code="label.parentInstalleraddress"/></label>
                    <div class="col-sm-10">
                        <div class="row text-left">
                            <div class="col-sm-4">
                                <select name="cscountry" id="cscountry" class="selectpicker"
                                        title="<@spring.message code="label.choosecountry"/>">
                                </select>
                            </div>
                            <div class="col-sm-4">
                                <select id="csprovince" name="csprovince" class="selectpicker"
                                        title="<@spring.message code="label.chooseprovince"/>">
                                </select>
                            </div>
                            <div class="col-sm-4">
                                <select id="cscity" name="cscity" class="selectpicker"
                                        title="<@spring.message code="label.choosecity"/>">
                                </select>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <label for="cspostal"
                           class="col-sm-2 control-label"><@spring.message code="label.parentInstallerpostal"/></label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="cspostal" name="cspostal"
                               value="${(orgInfo.csaddress.postal)!}"
                               placeholder="<@spring.message code="label.parentInstallerpostal"/>">
                    </div>
                </div>

                <div class="text-left"><h4><@spring.message code="label.parentinstallerperson"/></h4></div>

                <div class="form-group">
                    <label for="cspname" class="col-sm-2 control-label"><@spring.message code="label.name"/></label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="cspname" name="cspname"
                               value="${(orgInfo.cspname)!}"
                               placeholder="<@spring.message code="label.name"/>">
                    </div>
                </div>

                <div class="form-group">
                    <label for="cspphonenumber"
                           class="col-sm-2 control-label"><@spring.message code="label.phonenumber"/></label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="cspphonenumber" name="cspphonenumber"
                               value="${(orgInfo.cspphonenumber)!}"
                               placeholder="<@spring.message code="label.phonenumber"/>">
                    </div>
                </div>

                <div class="form-group">
                    <label for="cspfax" class="col-sm-2 control-label"><@spring.message code="label.fax"/></label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="cspfax" name="cspfax" value="${(orgInfo.cspfax)!}"
                               placeholder="<@spring.message code="label.fax"/>">
                    </div>
                </div>

                <div class="form-group">
                    <label for="cspemail" class="col-sm-2 control-label"><@spring.message code="label.email"/></label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="cspemail" name="cspemail"
                               value="${(orgInfo.cspemail)!}"
                               placeholder="<@spring.message code="label.email"/>">
                    </div>
                </div>

                <div class="text-left"><h4><@spring.message code="label.admin"/></h4></div>

                <div class="form-group">
                    <label for="loginname" class="col-sm-2 control-label"><@spring.message code="label.loginname"/>
                        *</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="loginname" name="loginname"
                               value="${(orgInfo.loginname)!}"
                               placeholder="<@spring.message code="label.loginname"/>">
                    </div>
                </div>

                <div class="form-group">
                    <label for="password" class="col-sm-2 control-label"><@spring.message code="label.password"/>
                        *</label>
                    <div class="col-sm-10">
                        <input type="password" class="form-control" id="password" name="password"
                               placeholder="<@spring.message code="label.password"/>">
                    </div>
                </div>

                <div class="form-group">
                    <label for="repassword" class="col-sm-2 control-label"><@spring.message code="label.repassword"/>
                        *</label>
                    <div class="col-sm-10">
                        <input type="password" class="form-control" id="repassword" name="repassword"
                               placeholder="<@spring.message code="label.repassword"/>">
                    </div>
                </div>

                <div class="form-group">
                    <label for="question"
                           class="col-sm-2 control-label"><@spring.message code="label.pquestion"/></label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="question" name="question"
                               value="${(orgInfo.question)!}"
                               placeholder="<@spring.message code="label.pquestion"/>">
                    </div>
                </div>

                <div class="form-group">
                    <label for="answer" class="col-sm-2 control-label"><@spring.message code="label.panswer"/></label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="answer" name="answer"
                               placeholder="<@spring.message code="label.panswer"/>">
                    </div>
                </div>

                <div class="text-left"><h4><@spring.message code="label.installerprivilege"/></h4></div>
                <div name="ck" class="checkbox">
                    <label>
                        <input type="checkbox">
                    </label>
                </div>
                <div class="row">
                    <div class="col-sm-4"></div>
                    <div class="col-sm-8">
                        <button id="btn-submit" class="btn btn-default"
                                style="width:100px;"><@spring.message code="label.submit"/>
                        </button>
                    </div>
                </div>

            </form>

        </div>

        <div class="col-md-1"></div>
    </div>

<!-- JavaScript 部分 -->
	<script src="../static/js/addressController.js"></script>
	<#--<script src="../static/js/addressController2.js"></script>-->
    <script src="../static/js/validAddress.js"></script>

<script type="text/javascript">
    $(document).ready(function () {
        getCountry(-1);
        changeProvince(1);
        changeCity(1);
        getbcountryB(-1);
        changeProvinceB(1);
        changeCityB(1);
        getcscountry(-1);
        changeProvinceC(1);
        changeCityC(1);
        $('#defaultForm').bootstrapValidator({
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
                name: {
                    validators: {
                        notEmpty: {
                            message: 'The name is required and cannot be empty'
                        }
                    }
                },
                code: {
                    validators: {
                        notEmpty: {
                            message: 'The code is required and cannot be empty'
                        }
                    }
                }
            }
        });
    });

    $("#btn-submit").click(function () {
        if($("#orgid").val() != null) {
            $("#defaultForm").bootstrapValidator('removeField','loginname');
            $("#defaultForm").bootstrapValidator('removeField','password');
            $("#defaultForm").bootstrapValidator('removeField','repassword');
            $("#defaultForm").bootstrapValidator('removeField','orgcode');
        }
        $("#defaultForm").bootstrapValidator('validate');//提交验证  
        if ($("#defaultForm").data('bootstrapValidator').isValid() && validAddressB() && validAddressC()) {//获取验证结果，如果成功，执行下面代码
            var url = "../org/addInstallerorg";
            $.ajax({
                type: "POST",
                dataType: "html",
                async: false,
                url: url,
                data: $('#defaultForm').serialize(),
                success: function (data) {
                    var strresult = data;
                    var jsonObj = eval('(' + data + ')');
                    // alert(strresult);
                    if (jsonObj['status'] == 1) {
                        alert("success");
                        window.location.href = "installerList";
                    }else{
                        alert(formatterReturnStatus(jsonObj['msg']));
                    }
                },
                error: function (data) {
                    alert("error:" + data.responseText);
                }
            });
        } else {
            alert(lan.notempty);
        }
    });

    getParentOrg();

    function getParentOrg() {
        $.ajax({
            type: "get",
            url: "../org/listAllSupplier",
            async: true,
            success: function (data) {
                var str = "";
                for (var i = 0; i < data.length; i++) {
                    str += '<option value=' + data[i].organizationid + '>'
                            + data[i].name + '</option>'
                }
                $("#parentorgid").html(str);

                $("#parentorgid").selectpicker('refresh');

            }
        });
    }

</script>
<#include "/_foot0.ftl"/>