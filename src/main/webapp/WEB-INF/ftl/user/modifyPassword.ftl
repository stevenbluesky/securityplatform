<!-- 录入电话卡信息页面 -->
<#include "../_head0.ftl"/>
<div class="col-md-1"></div>
    <div class="row-horizontal">
        <div class="col-md-10">
            <form id="defaultForm" method="POST">
                <div class="text-center"><h1><@spring.message code='label.modifypassword'/></h1></div>
                <hr>
                <div class="col-sm-12">
                <div  class="form-group" align="middle">
                    <label for="lastname"  class="col-sm-2 control-label"><@spring.message code='label.oldpassword'/>*</label>
                    <div class="col-sm-5">
                        <input type="text" class="form-control" id="oldpassword" name="oldpassword" value="" placeholder="<@spring.message code='label.oldpassword'/>">
                    </div>
                </div>
                </div>
                <div class="col-sm-12">
                <div  class="form-group" align="middle">
                    <label for="lastname"  class="col-sm-2 control-label"><@spring.message code='label.newpassword1'/>*</label>
                    <div class="col-sm-5">
                        <input type="text" class="form-control" id="newpassword1" name="newpassword1" value="" placeholder="<@spring.message code='label.newpassword1'/>">
                    </div>
                </div>
                </div>
                <div class="col-sm-12">
                <div  class="form-group" align="middle">
                <label for="lastname"  class="col-sm-2 control-label"><@spring.message code='label.newpassword'/>*</label>
                <div class="col-sm-5">
                    <input type="text" class="form-control" id="newpassword" name="newpassword" value="" placeholder="<@spring.message code='label.newpassword'/>">
                </div>
            </div>
                </div>
                <div class="row">
                    <div class="col-sm-3"></div>
                    <div class="col-sm-9">
                        <button id="btn-submit" class="btn btn-default"
                                style="width:30%"><@spring.message code="label.submit"/></button>
                    </div>
                </div>
            </form>

        </div>

    </div>
<div class="col-md-1"></div>
<!-- JavaScript 部分 -->
    <script type="text/javascript">

        $(document).ready(function() {


            $('#defaultForm').bootstrapValidator({
                message: 'This value is not valid',
                feedbackIcons: {
                    valid: 'glyphicon glyphicon-ok',
                    invalid: 'glyphicon glyphicon-remove',
                    validating: 'glyphicon glyphicon-refresh'
                },
                fields: {
                    oldpassword: {
                        message: 'The oldpassword is not valid',
                        validators: {
                            notEmpty: {
                                message: 'The oldpassword is required and cannot be empty'
                            }
                        }
                    },
                    newpassword: {
                        message: 'The newpassword is not valid',
                        validators: {
                            notEmpty: {
                                message: 'The newpassword is required and cannot be empty'
                            },
                            identical: {
                                field: 'newpassword1',
                                message: 'The newpassword and its confirm are not the same'
                            }
                        }
                    },
                    newpassword1: {
                        message: 'The newpassword is not valid',
                        validators: {
                            notEmpty: {
                                message: 'The newpassword is required and cannot be empty'
                            },stringLength: {
                                min: 6,
                                message: 'The password must be 6 or more characters long'
                            }
                        }
                    }


                }
            });
        });

        $("#btn-submit").click(function () {
            $("#defaultForm").bootstrapValidator('validate');//提交验证
                if ($("#defaultForm").data('bootstrapValidator').isValid()) {//获取验证结果，如果成功，执行下面代码
                    var url = "../user/updatePassowrd";
                    $.ajax({
                        type: "POST",
                        dataType: "html",
                        url: url,
                        data: $('#defaultForm').serialize(),
                        success: function (data) {
                            if(data=="success"){
                                alert('<@spring.message code='label.updatesuccess'/>');
                                window.location.href="../logout";
                            }else{
                                alert('<@spring.message code='label.updatefailed'/>');
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


    </script>
<#include "../_foot0.ftl"/>