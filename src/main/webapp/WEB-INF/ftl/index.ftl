<#import "/spring.ftl" as spring />
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title><@spring.message code='label.logintitle'/></title>
    <script src="static/js/jquery.min.js"></script>
    <link rel="stylesheet" href="static/css/bootstrap.min.css">
    <script src="static/js/bootstrap.min.js"></script>
    <script src="static/js/html5shiv.min.js"></script>
    <script src="static/js/respond.min.js"></script>
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
<#--<br>-->
<div class="row">
    <div class="col-xs-2 col-sm-2 col-md-2" align="center">
        <img src="image/logo.png"  style="width:100%" >
    </div>

    <div class="col-xs-5 col-sm-5 col-md-5">
    <#if emp??&&loginorg?? ><h4 style="display: inline;float:left"><#if emp.type==1><@spring.message code='label.installerportal'/><#elseif loginorg.orgtype==1><@spring.message code='label.distributorportal'/><#elseif loginorg.orgtype==2><@spring.message code='label.installercompanyportal'/></#if></h4></#if>
    </div>
    <div class="col-xs-5 col-sm-5 col-md-5" align="middle">
        <br>
        <#if emp??&&loginorg?? ><h4 style="display: inline;float: right"><@spring.message code='label.welcome'/>,${loginorg.name}_${emp.loginname}&nbsp;&nbsp;<a href="logout" ><@spring.message code='label.logout'/></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</h4></#if>
        <br>
    </div>
</div>
<#--<br>-->
<div class="row">
    <div id="wrap" class="col-xs-2 col-sm-2 col-md-2" >
        <div id="treeview12" class=""></div>
    </div>

    <div class="col-xs-10 col-sm-10 col-md-10" style="height: 800px;overflow: hidden;z-index: 999;">
        <iframe class="" id="right" name="right" frameborder="0" src="welcome.html"
                style="height: 100%;width: 100%;overflow-x: hidden;" ></iframe>
    </div>
</div>

<script src="static/js/bootstrap-treeview.min.js"></script>
<script src="static/js/i18n/iRemoteLanguage/<@spring.message code="label.language.js"/>"></script>
<script src="static/js/jstree.js"></script>
<#include "_foot0.ftl"/>