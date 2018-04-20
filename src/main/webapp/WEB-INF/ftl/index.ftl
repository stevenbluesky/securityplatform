<#import "/spring.ftl" as spring />
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title><@spring.message code='label.logintitle'/></title>
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <!-- 最新版本的 Bootstrap 核心 CSS 文件 -->
<#-- <script src="https://cdn.bootcss.com/jquery/3.3.1/jquery.min.js"></script>-->
    <script src="static/js/jquery.min.js"></script>
<#-- <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css"
       integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">-->
    <link rel="stylesheet" href="static/css/bootstrap.min.css">
<#--<script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"
        integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
        crossorigin="anonymous"></script>-->
    <script src="static/js/bootstrap.min.js"></script>
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>-->
<#--<script src="https://cdn.bootcss.com/html5shiv/3.7.3/html5shiv.min.js"></script>-->
    <script src="static/js/html5shiv.min.js"></script>
    <!--<script src="https://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>-->
    <script src="static/js/respond.min.js"></script>
    <![endif]-->
    <style type="text/css">
        .form-group > div {
            margin-bottom: 10px;
        }
        div.form-group>label {
            text-align: left;
        }
    </style>
</head>
<body style="overflow-x: hidden;">
<br>
<div class="row">
    <div class="col-md-3"  style="height:70px;width: 300px" align="right">
        <img src="image/logo.png"  height="70" >
    </div>

    <div class="col-md-7">
    </div>
    <div class="col-md-2" align="middle">
        <br>
        <#if emp?? ><h4 style="display: inline;"><@spring.message code='label.welcome'/>,${emp.loginname}&nbsp;&nbsp;<a href="logout" ><@spring.message code='label.logout'/></a></h4></#if>
        <br>
    </div>
</div>
<br>
<div class="row">
    <div id="wrap" class="col-md-2" >
        <div class="container">
            <div class="row">
                <div class="col-sm-3">
                    <div id="treeview12" class=""></div>
                </div>
            </div>
        </div>
    </div>

    <div class="col-md-10" style="height: 800px;overflow: hidden;z-index: 999;">
        <iframe class="" id="right" name="right" frameborder="0" src="welcome.html"
                style="height: 100%;width: 100%;overflow-x: hidden;" ></iframe>
    </div>
</div>

<script src="static/js/bootstrap-treeview.min.js"></script>
<script src="static/js/i18n/iRemoteLanguage/<@spring.message code="label.language.js"/>"></script>
<script src="static/js/jstree.js"></script>
<#include "_foot0.ftl"/>