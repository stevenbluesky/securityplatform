<#import "/spring.ftl" as spring />
<!DOCTYPE html>
<html lang="zh-CN" xmlns="http://www.w3.org/1999/html">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <#--<meta name="viewport" content="width=device-width, initial-scale=1">-->
    <title><@spring.message code='label.logintitle'/></title>
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <!-- 最新版本的 Bootstrap 核心 CSS 文件 -->
    <script src="static/js/jquery.min.js"></script>
    <link rel="stylesheet" href="static/css/bootstrap.min.css">
    <link href="static/css/bootstrap-select.min.css" rel="stylesheet">
    <link href="static/css/bootstrap-table.min.css" rel="stylesheet">
    <link href="static/css/bootstrapValidator.min.css" rel="stylesheet">
    <script src="static/js/bootstrapValidator.min.js"></script>
    <script src="static/js/bootstrap.min.js"></script>
    <script src="static/js/bootstrap-select.min.js"></script>
    <script src="static/js/bootstrap-table.min.js"></script>
    <script src="static/js/i18n/iRemoteLanguage/<@spring.message code="label.language.js"/>"></script>
    <script src="static/js/formatter.js"></script>
    <script src="static/js/html5shiv.min.js"></script>
    <script src="static/js/respond.min.js"></script>
    <![endif]-->
    <style type="text/css">
        .form-group > div {
            margin-bottom: 10px;
        }
    </style>
    <link href="static/css/login.css" rel="stylesheet" type="text/css" />
</head>
<#--<body style="overflow-x: hidden;">-->
<body >
        <div class="login_box">
            <div style="text-align:center;">
                <h1><@spring.message code='label.logintitle'/></h1>
            </div>
            <div class="login_l_img"><img src="image/login-img.png" /></div>
            <div class="login">

        <form id="defaultForm" method="POST">
            <div class="form-group">
                <label for="code"
                       class="col-sm-2 control-label"><@spring.message code='label.organizationcode'/></label>
                <div class="col-sm-10">
                    <input type="text" class="form-control" id="code" name="code"
                           placeholder="<@spring.message code='label.organizationcode'/>" value="">
                </div>
            </div>

            <div class="form-group">
                <label for="loginname" class="col-sm-2 control-label"><@spring.message code='label.username'/></label>
                <div class="col-sm-10">
                    <input type="text" class="form-control" id="loginname" name="loginname"
                           placeholder="<@spring.message code='label.username'/>">
                </div>
            </div>

            <div class="form-group">
                <label for="password" class="col-sm-2 control-label"><@spring.message code='label.password'/></label>
                <div class="col-sm-10">
                    <input type="password" class="form-control" id="password" name="password"
                           placeholder="<@spring.message code='label.password'/>">
                </div>
            </div>

            <div class="form-group">
                <label for="captchacode"
                       class="col-sm-2 control-label"><@spring.message code='label.captchcode'/></label>
                <div class="col-sm-4">
                    <input type="text" class="form-control" id="captchacode" name="captchacode"
                           placeholder="<@spring.message code='label.captchcode'/> ">
                </div>
                <div class="col-sm-6">
                    <img id="validateCode" class="img-responsive" src=""
                         onclick="this.src='login/getCode?time='+new Date().getTime();"
                         style="height:35px;float: right"/>
                </div>
            </div>
            <button id="btn-submit" class="btn btn-default  btn-lg"
                    style="float:right;;width: 400px"><@spring.message code='label.login'/></button>
        </form>
            </div>
            <#--<div class="copyright">公司 版权所有©2016-2018 技术支持电话：000-00000000</div>-->
        </div>

<script type="text/javascript">
    $("#validateCode").click();
    $(document).ready(function () {
        $('#defaultForm').bootstrapValidator({
//      live: 'disabled',
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
                            message: 'The loginname must be more than 6 and less than 30 characters long'
                        },
                        regexp: {
                            regexp: /^[a-zA-Z0-9_\.]+$/,
                            message: 'The username can only consist of alphabetical, number, dot and underscore'
                        }
                    }
                },
                code: {
                    validators: {
                        notEmpty: {
                            message: 'The Organization Code is required and cannot be empty'
                        }
                    }
                },
                password: {
                    validators: {
                        notEmpty: {
                            message: 'The password is required and cannot be empty'
                        }
                    }
                }
            }
        });
    });

    $("#btn-submit").click(function () {
        $("#defaultForm").bootstrapValidator('validate');//提交验证  
        if ($("#defaultForm").data('bootstrapValidator').isValid()) {//获取验证结果，如果成功，执行下面代码  
            var url = "login/login";
            $.ajax({
                type: "POST",
                dataType: "html",
                url: url,
                data: $('#defaultForm').serialize(),
                success: function (data) {
                    var data = $.parseJSON(data);
                    //alert(data.status+data.msg);
                    if (data.status == 1) {
                        window.location.href = 'index';
                    } else {
                        $("#validateCode").click();
                        alert(formatterReturnStatus(data.msg));
                    }
                },
                error: function (data) {
                    alert("error");
                }
            });
        }
    });

</script>

<#include "_foot0.ftl"/>