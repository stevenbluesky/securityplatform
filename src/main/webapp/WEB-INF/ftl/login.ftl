<!DOCTYPE html>
<html lang="zh-CN">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <!-- 最新版本的 Bootstrap 核心 CSS 文件 -->
    <script src="https://cdn.bootcss.com/jquery/3.3.1/jquery.min.js"></script>

    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
  	<link href="https://cdn.bootcss.com/bootstrap-select/2.0.0-beta1/css/bootstrap-select.min.css" rel="stylesheet">
  	<link href="https://cdn.bootcss.com/bootstrap-table/1.12.1/bootstrap-table.min.css" rel="stylesheet">
  	<link href="static/css/bootstrapValidator.min.css" rel="stylesheet">
	<script src="static/js/bootstrapValidator.min.js"></script>
    <script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
	<script src="https://cdn.bootcss.com/bootstrap-select/2.0.0-beta1/js/bootstrap-select.min.js"></script>
	<script src="https://cdn.bootcss.com/bootstrap-table/1.12.1/bootstrap-table.min.js"></script>

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="https://cdn.bootcss.com/html5shiv/3.7.3/html5shiv.min.js"></script>
      <script src="https://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    <style type="text/css">
    .form-group>div{
    	margin-bottom:10px;
    }
</style>
  </head>
  <body>
 	<div class="row">
  		<div class="col-md-3"></div>
  		<div class="col-md-6">
			 <form id="defaultForm" method="POST">
			 <br>
			 <br>
			 <br>
			 <br>
              <div class="text-center" style="margin-bottom:70px;"><h1>Ameta安防管理平台</h1></div>
              <div class="form-group">
                <label for="code"  class="col-sm-2 control-label">机构代码</label>
                <div class="col-sm-10">
                 <input type="text" class="form-control" id="code" name="code" placeholder="机构代码" value="1">
                </div>
              </div>
              
              <div class="form-group">
                <label for="loginname"  class="col-sm-2 control-label">账号</label>
                <div class="col-sm-10">
                 <input type="text" class="form-control" id="loginname" name="loginname" placeholder="账号">
                </div>
              </div>
              
              <div  class="form-group">
                <label for="password" class="col-sm-2 control-label">密码</label>
                <div class="col-sm-10">
                <input type="text" class="form-control" id="password" name="password" placeholder="密码">
                </div>
              </div>
              
              <div  class="form-group">
                <label for="captchacode" class="col-sm-2 control-label">验证码</label>
                <div class="col-sm-6">
                <input type="text" class="form-control" id="captchacode" name="captchacode" placeholder="验证码 ">
                </div>
                <div class="col-sm-4">
                <img class="img-responsive" src="static/img/code.jpg" style="height:100px;"/>
              </div>
              </div>
              <button id="btn-submit" class="btn btn-default  btn-lg" style="float:right;">登录</button>
       		 </form>
        </div>

  		<div class="col-md-3"></div>
</div>

<script type="text/javascript">
$(document).ready(function() {
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
            var url= "login/login";       
                $.ajax({
                    type: "POST",
                    dataType: "html",
                    url: url,
                    data: $('#defaultForm').serialize(),
                    success: function (data) {
						var data = $.parseJSON(data);
                   	 	//alert(data.status+data.msg);
                    	if(data.status == 1){
                    		window.location.href='index';
                    	}else{
                    		alert(data.msg);
                    	}
                    },
                    error: function(data) {
                        alert("error:"+data.responseText);
                     }
                });
        }else{
        	alert("必填字段不能为空!");
        }
});

</script>

<#include "_foot0.ftl"/>