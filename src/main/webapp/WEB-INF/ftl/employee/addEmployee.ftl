<!-- 新增员工页面 -->
<#include "/_head0.ftl">
    <div class="row-horizontal">
        <div class="col-md-1"></div>
        <div class="col-md-10">
          <form id="defaultForm" method="POST">
          
              <div class="text-center"><h1>新增员工</h1></div>

              <div class="text-left"><h4>账号信息</h4></div>
              <div  class="form-group">
                <label for="organizationid"  class="col-sm-2 control-label">所属机构</label>
                <div class="col-sm-10">
               <select name="organizationid" id="organizationid" class="selectpicker" data-live-search="true" title="选择机构">
                    </select>
                </div>
              </div>
              
              <div  class="form-group">
                <label for="loginname"  class="col-sm-2 control-label">登录名</label>
                <div class="col-sm-10">
                 <input type="text" class="form-control" id="loginname" name="loginname" placeholder="登录名">
                </div>
              </div>
              
              <div  class="form-group">
                <label for="password" class="col-sm-2 control-label">密码</label>
                <div class="col-sm-10">
                <input type="password" class="form-control" id="password" maxlength='16' name="password" placeholder="密码">
                </div>
              </div>
              
              <div  class="form-group">
                <label  for="repassword" class="col-sm-2 control-label">确认密码</label>
                <div class="col-sm-10">
                <input type="password" class="form-control" id="repassword" maxlength='16' name="repassword" placeholder="重新输入密码">
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
              
              <div  class="form-group">
                <label for="code"  class="col-sm-2 control-label">员工代码</label>
                <div class="col-sm-10">
                 <input type="text" class="form-control" id="code" name="code" placeholder="如果是新增安装员,请填写员工代码">
               </div>
              </div>
              
              <div  class="form-group">
                <label for="expiredate"  class="col-sm-2 control-label" data-date="1979-09-16T05:25:07Z" data-date-format="dd MM yyyy - HH:ii p" data-link-field="dtp_input1">失效日期</label>
		                <div class="col-sm-3 input-group date form_datetime">
		                  <input class="form-control" size="16" name="expiredate" type="text" value="" readonly>
		                    <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
							<span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
		                </div>
					<input type="hidden" id="dtp_input1"  value="" /><br/>
               </div>
            
              <div  class="form-group">
                <label for="status"  class="col-sm-2 control-label">状态</label>
                <div class="col-sm-10">
             	   <select id="status" name="status" class="selectpicker" title="选择状态">
                      <option value="1">生效</option>
                      <option value="0">未生效</option>
                      <option value="2">冻结</option>
                      <option value="3">删除</option>
                   </select>
                </div>
              </div>
              
              <div class="text-left"><h4>员工权限</h4></div><hr>
              
              <div class="text-left"><h4>个人信息</h4></div>
              
              <div  class="form-group">
                <label for="lastname"  class="col-sm-2 control-label">姓</label>
                <div class="col-sm-10">
                 <input type="text" class="form-control" id="lastname" name="lastname" placeholder="姓">
               </div>
              </div>
              <div  class="form-group">
                <label for="firstname"  class="col-sm-2 control-label">名</label>
                <div class="col-sm-10">
                 <input type="text" class="form-control" id="firstname" name="firstname" placeholder="名">
               </div>
              </div>
              
              <div  class="form-group">
                <label for="title"  class="col-sm-2 control-label">头衔</label>
                <div class="col-sm-10">
                 <input type="text" class="form-control" id="title" name="title" placeholder="头衔">
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
               <select name="gender" class="selectpicker" title="选择性别">
                      <option value="">请选择性别</option>
                      <option value="0">女</option>
                      <option value="1">男</option>
                      <option value="2">LGBT</option>
                    </select>
                </div>
              </div>
              
               <div  class="form-group">
                <label for="phonenumber"  class="col-sm-2 control-label">电话</label>
                <div class="col-sm-10">
                 <input type="text" class="form-control" id="phonenumber" name="phonenumber" placeholder="Email">
               </div>
              </div>
              
               <div  class="form-group">
                <label for="email"  class="col-sm-2 control-label">邮箱</label>
                <div class="col-sm-10">
                 <input type="text" class="form-control" id="email" name="email" placeholder="Email">
               </div>
              </div>
              
               <div  class="form-group">
                <label for="fax"  class="col-sm-2 control-label">传真</label>
                <div class="col-sm-10">
                 <input type="text" class="form-control" id="fax" name="fax" placeholder="Email">
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
            var url= "../employee/add";       
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

getParentOrg();
function getParentOrg() {
	$.ajax({
		type : "get",
		url : "../org/listAllOrgSelect",
		async : true,
		success : function(data) {
			var str = "<option value=''></option>";
			for (var i = 0; i < data.length; i++) {
				str += '<option value=' + data[i].organizationid + '>'
						+ data[i].name + '</option>'
			}
			$("#organizationid").html(str);

			$("#organizationid").selectpicker('refresh');

		}
	});
}

  $('.form_datetime').datetimepicker({
        //language:  'fr',
        format: 'yyyy/mm/dd hh:ii',
        weekStart: 1,
        todayBtn:  1,
		autoclose: 1,
		todayHighlight: 1,
		startView: 2,
		forceParse: 0,
        showMeridian: 1
    });
</script>
<#include "/_foot0.ftl"/>