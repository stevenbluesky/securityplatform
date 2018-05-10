<!-- 新增员工页面 -->
<#include "../_head0.ftl">
    <div class="row-horizontal">
        <div class="col-md-1"></div>
        <div class="col-md-10">
            <form class="form-horizontal" id="defaultForm" method="POST">
              <#if empInfo??>
                <div class="text-center"><h1><@spring.message code="label.modifyemployee"/></h1></div>
              <#else>
              <div class="text-center"><h1><@spring.message code="label.addemployee"/></h1></div>
              </#if>
                <hr>
                <div class="text-left"><h4><@spring.message code="label.employeeinfo"/></h4></div>
                <hr>
              <#if emp?? && emp.organizationid == 1>
              <div class="form-group">
                  <label for="organizationid"
                         class="col-sm-2 control-label"  style="text-align: left;"><@spring.message code="label.parentorg"/></label>
                  <div class="col-sm-10">
                      <select name="organizationid" id="organizationid" class="selectpicker" data-live-search="true"
                              title="<@spring.message code="label.chooseorg"/>">
                      </select>
                  </div>
              </div>
              </#if>
                <div class="form-group">
                    <label for="loginname"
                           class="col-sm-2 control-label"  style="text-align: left;"><@spring.message code="label.loginname"/></label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="loginname" name="loginname"
                               value="${(empInfo.loginname)!}" placeholder="<@spring.message code="label.loginname"/>">
                    </div>
                </div>

                <div class="form-group">
                    <label for="password"
                           class="col-sm-2 control-label"  style="text-align: left;"><@spring.message code="label.password"/></label>
                    <div class="col-sm-10">
                        <input type="password" class="form-control" id="password" maxlength='16' name="password"
                               placeholder="<@spring.message code="label.password"/>">
                    </div>
                </div>

                <div class="form-group">
                    <label for="repassword"
                           class="col-sm-2 control-label"  style="text-align: left;"><@spring.message code="label.repassword"/></label>
                    <div class="col-sm-10">
                        <input type="password" class="form-control" id="repassword" maxlength='16' name="repassword"
                               placeholder="<@spring.message code="label.repassword"/>">
                    </div>
                </div>

                <div class="form-group">
                    <label for="question"
                           class="col-sm-2 control-label"  style="text-align: left;"><@spring.message code="label.pquestion"/></label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="question" name="question"
                               value="${(empInfo.question)!}" placeholder="<@spring.message code="label.pquestion"/>">
                    </div>
                </div>

                <div class="form-group">
                    <label for="answer" class="col-sm-2 control-label"  style="text-align: left;"><@spring.message code="label.panswer"/></label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="answer" name="answer"
                               placeholder="<@spring.message code="label.panswer"/>">
                    </div>
                </div>

                <div class="form-group">
                    <label for="code" class="col-sm-2 control-label"  style="text-align: left;"><@spring.message code="label.empcode"/></label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="code" name="code" value="${(empInfo.code)!}"
                               placeholder="<@spring.message code="label.ifnewinstallerpleaseinputcode"/>">
                    </div>
                </div>

                <div class="form-group">
                    <label for="expiredate" class="col-sm-2 control-label" data-date="1979-09-16T05:25:07Z"
                           data-date-format="dd MM yyyy - HH:ii p"
                           data-link-field="dtp_input1"  style="text-align: left;"><@spring.message code="label.expiredate"/></label>
                    <div class="col-sm-10" align="left">
                        <div class="col-sm-4 input-group date form_datetime">
                            <input class="form-control" size="16" name="expiredate" type="text"
                                   value="${(empInfo.expiredate)!}" readonly>
                            <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                            <span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
                        </div>
                    </div>
                </div>
                <input type="hidden" id="dtp_input1" value=""/>

                <div class="form-group">
                    <label for="status" class="col-sm-2 control-label"  style="text-align: left;"><@spring.message code="label.status"/></label>
                    <div class="col-sm-10">
                        <select id="status" name="status" class="selectpicker"
                                title="<@spring.message code="label.choosestatus"/>">
                            <option value="1"><@spring.message code="label.valid"/></option>
                            <option value="0"><@spring.message code="label.unvalid"/></option>
                            <option value="2"><@spring.message code="label.suspenced"/></option>
                            <option value="3"><@spring.message code="label.delete"/></option>
                        </select>
                    </div>
                </div>

                <div class="text-left" hidden><h4><@spring.message code="label.empprivilege"/></h4></div>
                <hr hidden>

                <div style="visibility: hidden;">br</div>
                <div class="text-left"><h4><@spring.message code="label.personinfo"/></h4></div>
                <hr>
                <div class="form-group">
                    <label for="lastname"
                           class="col-sm-2 control-label"  style="text-align: left;"><@spring.message code="label.lastname"/></label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="lastname" name="lastname"
                               value="${(empInfo.lastname)!}" placeholder="<@spring.message code="label.lastname"/>">
                    </div>
                </div>
                <div class="form-group">
                    <label for="firstname"
                           class="col-sm-2 control-label"  style="text-align: left;"><@spring.message code="label.firstname"/></label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="firstname" name="firstname"
                               value="${(empInfo.firstname)!}" placeholder="<@spring.message code="label.firstname"/>">
                    </div>
                </div>

                <div class="form-group">
                    <label for="title" class="col-sm-2 control-label"  style="text-align: left;"><@spring.message code="label.ptitle"/></label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="title" name="title" value="${(empInfo.title)!}"
                               placeholder="<@spring.message code="label.ptitle"/>">
                    </div>
                </div>

                <div class="form-group">
                    <label for="ssn" class="col-sm-2 control-label"  style="text-align: left;"><@spring.message code="label.ssn"/></label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="ssn" name="ssn" value="${(empInfo.ssn)!}"
                               placeholder="<@spring.message code="label.ssn"/>">
                    </div>
                </div>
                <div class="form-group">
                    <label for="gender" class="col-sm-2 control-label"  style="text-align: left;"><@spring.message code="label.gender"/></label>
                    <div class="col-sm-10">
                        <select name="gender" class="selectpicker" title="<@spring.message code="label.choosegender"/>">
                            <option value=""><@spring.message code="label.choosegender"/></option>
                            <option value="0"><@spring.message code="label.female"/></option>
                            <option value="1"><@spring.message code="label.male"/></option>
                            <option value="2">LGBT</option>
                        </select>
                    </div>
                </div>

                <div class="form-group">
                    <label for="phonenumber"
                           class="col-sm-2 control-label"  style="text-align: left;"><@spring.message code="label.phonenumber"/></label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="phonenumber" name="phonenumber"
                               value="${(empInfo.phonenumber)!}"
                               placeholder="<@spring.message code="label.phonenumber"/>">
                    </div>
                </div>

                <div class="form-group">
                    <label for="email" class="col-sm-2 control-label" style="text-align: left;"><@spring.message code="label.email"/></label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="email" name="email" value="${(empInfo.email)!}"
                               placeholder="<@spring.message code="label.email"/>">
                    </div>
                </div>

                <div class="form-group">
                    <label for="fax" class="col-sm-2 control-label" style="text-align: left;"><@spring.message code="label.fax"/></label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="fax" name="fax" value="${(empInfo.fax)!}"
                               placeholder="<@spring.message code="label.fax"/>">
                    </div>
                </div>

                <div class="form-group">
                    <label for="address"
                           class="col-sm-2 control-label" style="text-align: left;"><@spring.message code="label.chooseaddress"/></label>
                    <div class="col-sm-10">
                        <div class="row text-left">

                            <div class="col-sm-4">
                                <select name="country" id="country" class="selectpicker" data-size="10"
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
                                        title="<@spring.message code="label.address"/>">
                                </select>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <label for="detailaddress"
                           class="col-sm-2 control-label" style="text-align: left;"><@spring.message code="label.detailaddress"/></label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="detailaddress" name="detailaddress"
                               value="${(empInfo.detailaddress)!}"
                               placeholder="<@spring.message code="label.detailaddress"/>">
                    </div>
                </div>

                <div style="visibility: hidden;">br</div>
                <hr>

                <div class="row">
                    <div class="col-sm-4"></div>
                    <div class="col-sm-8">
                        <button id="btn-submit" class="btn btn-default"
                                style="width:100px;	"><@spring.message code="label.submit"/></button>
                    </div>
                </div>

            </form>

        </div>

        <div class="col-md-1"></div>
    </div>

<!-- JavaScript 部分 -->
<script src="../static/js/addressController.js"></script>

<script type="text/javascript">
    $(document).ready(function () {
        getCountry(-1);

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
                organizationid: {
                    validators: {
                        notEmpty: {
                            message: 'The password is required and cannot be empty'
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
                }
            }
        });
    });

    $("#btn-submit").click(function () {
        /*if($("#orgid").val() != null) {
            $("#defaultForm").bootstrapValidator('removeField','repassword');
        }*/
        $("#defaultForm").bootstrapValidator('validate');//提交验证
        if ($("#defaultForm").data('bootstrapValidator').isValid()) {//获取验证结果，如果成功，执行下面代码  
            var url = "../employee/add";
            $.ajax({
                type: "POST",
                dataType: "html",
                async: false,
                url: url,
                data: $('#defaultForm').serialize(),
                success: function (data) {
                    var jsonObj = eval('(' + data + ')');
                    if (jsonObj['status'] == 1) {
                        alert("success");
                        window.location.href = "employeeList";
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

    getParentOrg("#organizationid");

    function getParentOrg(s, id) {
        $.ajax({
            type: "get",
            url: "../org/listAllOrgSelect",
            async: true,
            success: function (data) {
                var str = "<option value=''></option>";
                for (var i = 0; i < data.length; i++) {
                    str += '<option value=' + data[i].organizationid + '>'
                            + data[i].name + '</option>'
                }
                $(s).html(str);

                $("#organizationid").selectpicker('refresh');

            }
        });
    }

    $('.form_datetime').datetimepicker({
        //language:  'fr',
        format: 'yyyy-mm-dd hh:mm',
        weekStart: 1,
        minView: 'day',
        todayBtn: 1,
        autoclose: 1,
        todayHighlight: 1,
        startView: 2,
        forceParse: 0,
        showMeridian: 1
    });
</script>
<#include "../_foot0.ftl"/>