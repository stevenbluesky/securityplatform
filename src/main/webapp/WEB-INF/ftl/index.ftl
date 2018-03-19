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
    <script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
    
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
  		<div class="col-md-12">
  			<br><br><br>
  		</div>
  	</div>
 	<div class="row">
  		<div id="wrap" class="col-md-2">
  			<div class="tree well">
        <ul>
            <li>
              <i class="icon-folder-open"></i> <a style="text-decoration:none;" href="supplier/supplierList" target="right">服务商列表</a>
                <ul>
                    <li></i> <a style="text-decoration:none;" href="supplier/addSupplierPage" target="right">新增服务商</a></li>
                </ul>
            </li>
            <li>
              <i class="icon-folder-open"></i> <a style="text-decoration:none;" href="employee/employeeList" target="right">员工列表</a>
                <ul>
                    <li></i> <a style="text-decoration:none;" href="employee/addEmployeePage" target="right">新增员工</a></li>
                </ul>
            </li>
            <li>
              <i class="icon-folder-open"></i> <a style="text-decoration:none;" href="installer/installerList" target="right">安装商列表</a>
                <ul>
                    <li></i> <a style="text-decoration:none;" href="installer/addInstallerPage" target="right">新增安装商</a></li>
                </ul>
            </li>
            <li>
              <i class="icon-folder-open"></i> <a style="text-decoration:none;" href="gateway/gatewayList" target="right">网关列表</a>
                <ul>
                    <li></i> <a style="text-decoration:none;" href="gateway/typeGatewayInfo" target="right">录入网关信息</a></li>
                    <li></i> <a style="text-decoration:none;" href="gateway/gatewayDetail" target="right">网关详情</a></li>
                </ul>
            </li>
            <li>
              <i class="icon-folder-open"></i> <a style="text-decoration:none;" href="device/deviceList" target="right">设备列表</a>
                <ul>
                    <li></i> <a style="text-decoration:none;" href="device/deviceDetail" target="right">设备详情</a></li>
                </ul>
            </li>
            <li>
              <i class="icon-folder-open"></i> <a style="text-decoration:none;" href="phonecard/phonecardList" target="right">电话卡列表</a>
                <ul>
                    <li></i> <a style="text-decoration:none;" href="phonecard/typePhonecardInfo" target="right">录入电话卡信息</a></li>
                </ul>
            </li>
            <li>
              <i class="icon-folder-open"></i> <a style="text-decoration:none;" href="user/userList" target="right">用户列表</a>
                <ul>
                    <li></i> <a style="text-decoration:none;" href="user/typeUserInfo" target="right">录入用户信息</a></li>
                </ul>
            </li>
            <li>
              <i class="icon-folder-open"></i> <a style="text-decoration:none;" href="phonecard/phonecardList" target="right">电话卡列表</a>
                <ul>
                    <li></i> <a style="text-decoration:none;" href="phonecard/typePhonecardInfo" target="right">录入电话卡信息</a></li>
                </ul>
            </li>
        </ul>
        <br><br><br>
  		</div>
    </div>
  		<div  class="col-md-10"  style="height: 800px;">
		    <iframe class="embed-responsive-item" id="right" name="right" frameborder="0" src="supplier/supplierList" style="height: 100%;width: 100%;"></iframe> 
  		</div>
   </div>
<#include "_foot0.ftl"/>