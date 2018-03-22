<!-- 新增服务商页面 -->
<#include "/_head0.ftl"/>
<#import "/spring.ftl" as spring />
    <div class="row-horizontal">
        <div class="col-md-1"></div>
        <div class="col-md-10">
          <form id="defaultForm">
              <div class="text-center"><h1>新增服务商</h1></div>

              <div class="text-left"><h4>服务商信息</h4></div>
              
              <div  class="form-group">
                <label for="name"  class="col-sm-2 control-label"><@spring.message code="label.supplier.officename"/>*</label>
                <div class="col-sm-10">
                 <input type="text" class="form-control" id="name" name="name" placeholder="<@spring.message code="label.supplier.officename"/>">
                </div>
              </div>
              
              <div  class="form-group">
                <label for="code"  class="col-sm-2 control-label">公司代码*</label>
                <div class="col-sm-10">
                 <input type="text" class="form-control" id="code" name="code" placeholder="公司代码">
                </div>
              </div>
             
              <div  class="form-group">
                <label for="country"  class="col-sm-2 control-label">公司地址*</label>
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
                <label for="postal"  class="col-sm-2 control-label">公司邮编</label>
                <div class="col-sm-10">
                 <input type="text" class="form-control" id="postal" name="postal" placeholder="公司邮编">
               </div>
              </div>
              
              <div  class="form-group">
                <label for="bcountry"  class="col-sm-2 control-label">公司账务地址</label>
                <div class="col-sm-10">
                 <div class="row text-left">
                  <div class="col-sm-4">
                    <select name="bcountry" id="bcountry" class="selectpicker" title="选择国家">
                    </select>
                  </div>
                  <div class="col-sm-4">
                    <select id="bprovince" name="bprovince" class="selectpicker" title="选择省份">
                    </select>
                  </div>
                  <div class="col-sm-4">
                    <select id="bcity" name="bcity" class="selectpicker" title="选择城市">                          
                    </select>
                  </div>
               </div>
               </div>
              </div>
              
              <div  class="form-group">
                <label for="bdetailaddress"  class="col-sm-2 control-label">账务详细地址</label>
                <div class="col-sm-10">
                 <input type="text" class="form-control" name="bdetailaddress" id="bdetailaddress" placeholder="账务详细地址">
               </div>
              </div>
              <div  class="form-group">
                <label for="bpostal"  class="col-sm-2 control-label">公司账务邮编</label>
                <div class="col-sm-10">
                 <input type="text" class="form-control" name="bpostal" id="bpostal" placeholder="公司账务邮编">
               </div>
              </div>

              <div class="text-left"><h4>服务商联系人</h4></div>
              
              <div  class="form-group">
                <label for="sname"  class="col-sm-2 control-label">姓名</label>
                <div class="col-sm-10">
                 <input type="text" class="form-control" id="sname" name="sname" placeholder="姓名">
               </div>
              </div>
              
              <div  class="form-group">
                <label for="sphonenumber"  class="col-sm-2 control-label">电话</label>
                <div class="col-sm-10">
                 <input type="text" class="form-control" id="sphonenumber" name="sphonenumber" placeholder="电话">
               </div>
              </div>
              
              <div  class="form-group">
                <label for="sfax"  class="col-sm-2 control-label">传真</label>
                <div class="col-sm-10">
                 <input type="text" class="form-control" id="sfax" name="sfax" placeholder="传真">
               </div>               
               </div>
               
              <div  class="form-group">
                <label for="semail"  class="col-sm-2 control-label">邮箱</label>
                <div class="col-sm-10">
                 <input type="text" class="form-control" id="semail" name="semail" placeholder="邮箱">
               </div>
              </div>

              <div class="text-left"><h4>服务商总公司</h4></div>
              
              <div  class="form-group">
                <label for="csname"  class="col-sm-2 control-label">总公司名称</label>
                <div class="col-sm-10">
                 <input type="text" class="form-control" id="csname" name="csname" placeholder="总公司名称">
               </div>
              </div>
               
              <div  class="form-group">
                <label for="cscountry"  class="col-sm-2 control-label">总公司地址</label>
                <div class="col-sm-10">
                 <div class="row text-left">
                  <div class="col-sm-4">
                    <select name="cscountry" id="cscountry" class="selectpicker" title="选择国家">
                    </select>
                  </div>
                  <div class="col-sm-4">
                    <select id="csprovince" name="csprovince" class="selectpicker" title="选择省份">
                    </select>
                  </div>
                  <div class="col-sm-4">
                    <select id="cscity" name="cscity" class="selectpicker" title="选择城市">                          
                    </select>
                  </div>
               </div>
               </div>
              </div>
              
              <div  class="form-group">
                <label for="cspostal"  class="col-sm-2 control-label">总公司邮编</label>
                <div class="col-sm-10">
                 <input type="text" class="form-control" id="cspostal" name="cspostal" placeholder="总公司邮编">
               </div>               
               </div>
               
               <div class="text-left"><h4>服务商总公司联系人</h4></div>
               
              <div  class="form-group">
                <label for="cspname"  class="col-sm-2 control-label">姓名</label>
                <div class="col-sm-10">
                 <input type="text" class="form-control" id="cspname" name="cspname" placeholder="姓名">
               </div>
              </div>
              
              <div  class="form-group">
                <label for="cspphonenumber"  class="col-sm-2 control-label">电话</label>
                <div class="col-sm-10">
                 <input type="text" class="form-control" id="cspphonenumber" name="cspphonenumber" placeholder="电话">
               </div>
              </div>
              
              <div  class="form-group">
                <label for="cspfax"  class="col-sm-2 control-label">传真</label>
                <div class="col-sm-10">
                 <input type="text" class="form-control" id="cspfax" name="cspfax" placeholder="传真">
               </div>
               </div>  
                          
              <div  class="form-group">
                <label for="cspemail"  class="col-sm-2 control-label">邮箱</label>
                <div class="col-sm-10">
                 <input type="text" class="form-control" id="cspemail" name="cspemail" placeholder="邮箱">
               </div>
              </div>
               
               <div class="text-left"><h4>管理员账号</h4></div>
               
              <div  class="form-group">
                <label for="loginname"  class="col-sm-2 control-label">登录名*</label>
                <div class="col-sm-10">
                 <input type="text" class="form-control" id="loginname" name="loginname" placeholder="登录名">
               </div>
              </div>
              
              <div  class="form-group">
                <label for="password"  class="col-sm-2 control-label">密码*</label>
                <div class="col-sm-10">
                 <input type="password" class="form-control" id="password" name="password" placeholder="密码">
               </div>
              </div>
              
              <div  class="form-group">
                <label for="repassword"  class="col-sm-2 control-label">确认密码*</label>
                <div class="col-sm-10">
                 <input type="password" class="form-control" id="repassword" name="repassword" placeholder="确认密码">
               </div>
               </div>  
                         
              <div  class="form-group">
                <label for="question"  class="col-sm-2 control-label">密码问题</label>
                <div class="col-sm-10">
                 <input type="text" class="form-control" id="question" name="question" placeholder="密码问题">
               </div>
              </div>
              
              <div  class="form-group">
                <label for="answer"  class="col-sm-2 control-label">密码答案</label>
                <div class="col-sm-10">
                 <input type="text" class="form-control" id="answer" name="answer" placeholder="密码答案">
               </div>
              </div>
             
              <div class="text-left"><h4>服务商权限</h4></div>
              
              <div class="row">
	              <div class="col-sm-4"></div>
	              <div class="col-sm-8"><button id="btn-submit" type="submit" class="btn btn-default" style="width:100px;	">提交</button></div>
              </div>
        </form>
        </div>

        <div class="col-md-1"></div>
    </div>

<!-- JavaScript 部分 -->
	<script src="../static/js/addressController.js"></script>
	<script src="../static/js/addressController2.js"></script>
	
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
                    },
                    remote: {
                        url: 'validCode',
                        message: 'The code is not available',
                        delay : 2000,
                        type: 'POST',
						 /**自定义提交数据，默认值提交当前input value
						  *  data: function(validator) {
						       return {
						           password: $('[name="passwordNameAttributeInYourForm"]').val(),
						           whatever: $('[name="whateverNameAttributeInYourForm"]').val()
						       };
						    }
						  */
                    } 
                }
            }
        }
    });
});

$("#btn-submit").click(function () {
        $("#defaultForm").bootstrapValidator('validate');//提交验证  
        if ($("#defaultForm").data('bootstrapValidator').isValid()) {//获取验证结果，如果成功，执行下面代码  
            var url= "../org/add";       
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
<#include "/_foot0.ftl"/>