<!-- 录入电话卡信息页面 -->
<#include "/_head0.ftl"/>
    <div class="row-horizontal">
        <div class="col-md-1"></div>
        <div class="col-md-10">
          <form id="defaultForm" method="POST">
          
              <div class="text-center"><h1>录入用户信息</h1></div>
             
              <div  class="form-group">
                <label for="firstname"  class="col-sm-2 control-label">姓</label>
                <div class="col-sm-10">
                 <input type="text" class="form-control" id="firstname" name="firstname" placeholder="姓">
                </div>
              </div>
              <div  class="form-group">
                <label for="lastname"  class="col-sm-2 control-label">名</label>
                <div class="col-sm-10">
                 <input type="text" class="form-control" id="lastname" name="lastname" placeholder="名">
                </div>
              </div>
              <div  class="form-group">
                <label for="ssn"  class="col-sm-2 control-label">身份证</label>
                <div class="col-sm-10">
                 <input type="text" class="form-control" id="ssn" name="ssn" placeholder="身份证">
               </div>
              </div>
             <div  class="form-group">
                <label for="gender"  class="col-sm-2 control-label">性别</label>
                <div class="col-sm-10">
               <select id="gender" name="gender" class="selectpicker" title="选择性别">
                      <option value="">选择性别</option>
                      <option value="0">女</option>
                      <option value="1">男</option>
                      <option value="3">LGBT</option>
                    </select>
                </div>
              </div>
              <div  class="form-group">
                <label for="phonenumber"  class="col-sm-2 control-label">电话</label>
                <div class="col-sm-10">
                 <input type="text" class="form-control" id="phonenumber" name="phonenumber" placeholder="电话">
                </div>
              </div>
              <div  class="form-group">
                <label for="email"  class="col-sm-2 control-label">邮箱</label>
                <div class="col-sm-10">
                 <input type="text" class="form-control" id="email" name="email" placeholder="邮箱">
               </div>
              </div>
              <div  class="form-group">
                <label for="fax"  class="col-sm-2 control-label">传真</label>
                <div class="col-sm-10">
                 <input type="text" class="form-control" id="fax" name="fax" placeholder="传真">
                </div>
              </div>
              
                <div  class="form-group">
                <label for="address"  class="col-sm-2 control-label">地址</label>
                <div class="col-sm-10">
                 <div class="row text-left">
                 
                  <div class="col-sm-4">
                    <select name="country" id="country" class="selectpicker" title="选择国家">
                    </select>
                  </div>
                  
                  <div class="col-sm-4">
                    <select id="province" name="province" class="selectpicker" title="选择省份">
                    </select>
                  </div>
                  
                  <div class="col-sm-4">
                    <select id="city" name="city" class="selectpicker" title="选择城市">                          
                    </select>
                  </div>
              	 </div>
               </div>
              </div>
              
              <div  class="form-group">
                <label for="detailaddress"  class="col-sm-2 control-label">详细地址</label>
                <div class="col-sm-10">
                 <input type="text" class="form-control" id="detailaddress" name="detailaddress" placeholder="详细地址">
                </div>
              </div>
              <div  class="form-group">
                <label for="codepostfix"  class="col-sm-2 control-label">用户代码后缀</label>
                <div class="col-sm-10">
                 <input type="text" class="form-control" id="codepostfix" name="codepostfix" placeholder="用户代码后缀">
                </div>
              </div>
              
              <div  class="form-group">
                <label for="deviceid"  class="col-sm-2 control-label">网关编号</label>
                <div class="col-sm-10">
                 <input type="text" class="form-control" id="deviceid" name="deviceid" placeholder="网关编号">
               </div>
               </div>
               
              <div  class="form-group">
                <label for="phonecardid"  class="col-sm-2 control-label">电话卡号</label>
                <div class="col-sm-10">
                 <input type="text" class="form-control" id="phonecardid" name="phonecardid" placeholder="电话卡号">
               </div>
              </div>
              
              <div id="msg" class="text-center">fsdafasd</div>
              <div class="row text-center">
	              <div class="col-sm-6"><button id="btn-submit" type="submit" class="btn btn-default" style="width:25%;">提交</button></div>
	              <div class="col-sm-6"><button type="reset" class="btn btn-default" style="width:25%;">重置</button></div>
              </div>
              
        </form>

        </div>

        <div class="col-md-1"></div>
    </div>

<!-- JavaScript 部分 -->
    <script type="text/javascript">
    
$(document).ready(function() {
    $('#defaultForm').bootstrapValidator({
 //       live: 'disabled',
        message: 'This value is not valid',
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            firstname: {
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
        $("#defaultForm").bootstrapValidator('validate');//提交验证  
        if ($("#defaultForm").data('bootstrapValidator').isValid()) {//获取验证结果，如果成功，执行下面代码  
            var url= "../user/add";       
                $.ajax({
                    type: "POST",
                    dataType: "html",
                    url: url,
                    data: $('#defaultForm').serialize(),
                    success: function (data) {
                        var strresult=data;
                        alert(strresult);
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
	<script src="../static/js/addressController.js"></script>
<#include "/_foot0.ftl"/>