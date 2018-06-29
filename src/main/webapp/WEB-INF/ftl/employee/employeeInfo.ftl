<!-- 新增员工页面 -->
<#include "../_head0.ftl">
    <div class="row-horizontal">
       <#-- <div class="col-md-1"></div>-->
        <div class="col-md-12">
            <form class="form-horizontal" id="defaultForm" method="POST">
              <div class="text-center"><h1><@spring.message code="label.employeeInfo"/></h1></div>
                <hr>
                <div class="text-left"><h4><@spring.message code="label.employeeinfo"/></h4></div>
                <hr>
              <#if emp?? && emp.organizationid == 1>
              <div class="form-group">
                  <label for="organizationid"
                         class="col-sm-3 "  style="text-align: left;"><@spring.message code="label.parentorg"/></label>
                  <div class="col-sm-9">
                      ${(empVO.parentOrgName)!}
                  </div>
              </div>
              </#if>
                <div class="form-group">
                    <label for="loginname"
                           class="col-sm-3 "  style="text-align: left;"><@spring.message code="label.orgcode"/></label>
                    <div class="col-sm-9">
                    ${(empVO.orgCode)!}
                    </div>
                </div>
                <div class="form-group">
                    <label for="loginname"
                           class="col-sm-3 "  style="text-align: left;"><@spring.message code="label.loginname"/></label>
                    <div class="col-sm-9">
                        ${(empVO.loginname)!}
                    </div>
                </div>

                <div class="form-group">
                    <label for="question"
                           class="col-sm-3 "  style="text-align: left;"><@spring.message code="label.pquestion"/></label>
                    <div class="col-sm-9">
                      ${(empVO.question)!}
                    </div>
                </div>

                <div class="form-group">
                    <label for="code" class="col-sm-3 "  style="text-align: left;"><@spring.message code="label.empcode"/></label>
                    <div class="col-sm-9">
                        ${(empVO.code)!}
                    </div>
                </div>

                <div class="form-group">
                    <label for="expiredate" class="col-sm-3 "  style="text-align: left;"><@spring.message code="label.expiredate"/></label>
                    <div class="col-sm-9" align="left">
                        <div class="col-sm-4 input-group">
                           ${(empVO.expiredate)!}
                        </div>
                    </div>
                </div>
                <input type="hidden" id="dtp_input1" value=""/>

                <div class="form-group">
                    <label for="status" class="col-sm-3 "  style="text-align: left;"><@spring.message code="label.status"/></label>
                    <div class="col-sm-9">
                        <span id="status"></span>
                    </div>
                </div>

                <div class="text-left" hidden><h4><@spring.message code="label.empprivilege"/></h4></div>
                <hr hidden>

                <div style="visibility: hidden;">br</div>
                <div class="text-left"><h4><@spring.message code="label.personinfo"/></h4></div>
                <hr>
               <#-- <div class="form-group">
                    <label for="lastname"
                           class="col-sm-3 "  style="text-align: left;"><@spring.message code="label.lastname"/></label>
                    <div class="col-sm-9">
                     ${(empVO.lastname)!}
                    </div>
                </div>-->
                <div class="form-group">
                    <label for="firstname"
                           class="col-sm-3 "  style="text-align: left;"><@spring.message code="label.name"/></label>
                    <div class="col-sm-9">
                       ${(empVO.firstname)!}
                    </div>
                </div>

                <div class="form-group">
                    <label for="title" class="col-sm-3 "  style="text-align: left;"><@spring.message code="label.ptitle"/></label>
                    <div class="col-sm-9">
                        ${(empVO.title)!}
                    </div>
                </div>
               <#-- <div class="form-group">
                    <label for="gender" class="col-sm-3 "  style="text-align: left;"><@spring.message code="label.gender"/></label>
                    <div class="col-sm-9">
                        <span id="gender"></span>
                    </div>
                </div>-->

                <div class="form-group">
                    <label for="phonenumber"
                           class="col-sm-3 "  style="text-align: left;"><@spring.message code="label.phonenumber"/></label>
                    <div class="col-sm-9">
                        ${(empVO.phonenumber)!}
                    </div>
                </div>

                <div class="form-group">
                    <label for="email" class="col-sm-3 " style="text-align: left;"><@spring.message code="label.email"/></label>
                    <div class="col-sm-9">
                        ${(empVO.email)!}
                    </div>
                </div>

                <div class="form-group">
                    <label for="fax" class="col-sm-3 " style="text-align: left;"><@spring.message code="label.fax"/></label>
                    <div class="col-sm-9">
                        ${(empVO.fax)!}
                    </div>
                </div>
                <div class="form-group">
                    <label for="detailaddress"
                           class="col-sm-3 " style="text-align: left;"><@spring.message code="label.detailaddress"/></label>
                    <div class="col-sm-9">
                    ${(empVO.detailaddress)!}
                    </div>
                </div>
                <div class="form-group">
                    <label for="address"
                           class="col-sm-3 " style="text-align: left;"></label>
                    <div class="col-sm-9">
                        <div class="row text-left">
                            <div class="col-sm-4">
                            ${(empVO.countryname)!}
                            </div>
                            <div class="col-sm-4">
                            ${(empVO.provincename)!}
                            </div>
                            <div class="col-sm-4">
                            ${(empVO.cityname)!}
                            </div>
                        </div>
                    </div>
                </div>



            </form>

        </div>

       <#-- <div class="col-md-1"></div>-->
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

    //getParentOrg("#organizationid");

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
        format: 'yyyy-mm-dd HH:mm',
        weekStart: 1,
        minView: 'day',
        todayBtn: 1,
        autoclose: 1,
        todayHighlight: 1,
        startView: 2,
        forceParse: 0,
        showMeridian: 1
    });

    function formatterStatus() {
        let status = ${(empVO.status)!"-1"};
        let formatterStatus = formatter_status(status, null, null);
        $("#status").text(formatterStatus);
    }
/*    function formatterGender(){
        let gender = {(empVO.gender)!"-1"};
        let formatterStatus = formatter_gender(gender);
        $("#gender").text(formatterStatus);
    }*/

    $(function () {
        formatterStatus();
        //formatterGender();
    });
</script>
<#include "../_foot0.ftl"/>