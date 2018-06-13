<!-- 录入电话卡信息页面 -->
<#include "../_head0.ftl"/>
    <div class="row-horizontal">
        <div class="col-md-1"></div>
        <div class="col-md-10">
          <form id="defaultForm" method="POST">
          
              <div class="text-center"><h1><@spring.message code='label.enteringuserinfo'/></h1></div>
              <hr>
              <div  class="form-group">
                  <label for="codepostfix"  class="col-sm-2 control-label"><@spring.message code='label.serviceprovider'/></label>
                  <div class="col-sm-10">
                      <#if supname??>${(supname)}<#else><@spring.message code='label.none'/></#if>
                  </div>
              </div>
              <div  class="form-group">
                  <label for="codepostfix"  class="col-sm-2 control-label"><@spring.message code='label.installerorg'/></label>
                  <div class="col-sm-10">
                  <#if insname??>${(insname)}<#else><@spring.message code='label.none'/></#if>
                  </div>
              </div>

              <div  class="form-group">
                  <label for="codepostfix"  class="col-sm-2 control-label"><@spring.message code='label.usercodepostfix'/></label>
                  <div class="col-sm-10">
                  ${(userCode)!}
                      <input type="hidden" class="form-control" id="codepostfix" name="codepostfix" value="${(userCode)!}">
                  </div>
              </div>
              <div  class="form-group">
                  <label for="appaccount"  class="col-sm-2 control-label"><@spring.message code='label.appaccount'/></label>
                  <div class="col-sm-10">
                      <input type="text" class="form-control" id="appaccount" name="appaccount" onblur="fillGateway(this)" placeholder="<@spring.message code='label.appaccount'/>">
                  </div>
              </div>
              <div  class="form-group">
                <label for="firstname"  class="col-sm-2 control-label"><@spring.message code='label.firstname'/>*</label>
                <div class="col-sm-10">
                 <input type="text" class="form-control" id="firstname" name="firstname" placeholder="<@spring.message code='label.firstname'/>">
                </div>
              </div>
              <div  class="form-group">
                <label for="lastname"  class="col-sm-2 control-label"><@spring.message code='label.lastname'/>*</label>
                <div class="col-sm-10">
                 <input type="text" class="form-control" id="lastname" name="lastname" placeholder="<@spring.message code='label.lastname'/>">
                </div>
              </div>
              <div  class="form-group">
                <label for="ssn"  class="col-sm-2 control-label"><@spring.message code='label.ssn'/>*</label>
                <div class="col-sm-10">
                 <input type="text" class="form-control" id="ssn" name="ssn" placeholder="<@spring.message code='label.ssn'/>">
               </div>
              </div>
             <#--<div  class="form-group">
                <label for="gender"  class="col-sm-2 control-label"><@spring.message code='label.gender'/></label>
                <div class="col-sm-10">
               <select id="gender" name="gender" class="selectpicker" title="<@spring.message code='label.choosegender'/>">
                      <option value=""><@spring.message code='label.choosegender'/></option>
                      <option value="0"><@spring.message code='label.female'/></option>
                      <option value="1"><@spring.message code='label.male'/></option>
                      <option value="3">LGBT</option>
                    </select>
                </div>
              </div>-->
              <div  class="form-group">
                <label for="phonenumber"  class="col-sm-2 control-label"><@spring.message code='label.phonenumber'/></label>
                <div class="col-sm-10">
                 <input type="text" class="form-control" id="phonenumber" name="phonenumber" placeholder="<@spring.message code='label.phonenumber'/>">
                </div>
              </div>
              <div  class="form-group">
                <label for="email"  class="col-sm-2 control-label"><@spring.message code='label.email'/></label>
                <div class="col-sm-10">
                 <input type="text" class="form-control" id="email" name="email" placeholder="<@spring.message code='label.email'/>">
               </div>
              </div>
              <div  class="form-group">
                <label for="fax"  class="col-sm-2 control-label"><@spring.message code='label.fax'/></label>
                <div class="col-sm-10">
                 <input type="text" class="form-control" id="fax" name="fax" placeholder="<@spring.message code='label.fax'/>">
                </div>
              </div>

              <div  class="form-group">
                  <label for="detailaddress"  class="col-sm-2 control-label"><@spring.message code='label.detailaddress'/>*</label>
                  <div class="col-sm-10">
                      <input type="text" class="form-control" id="detailaddress" name="detailaddress" placeholder="<@spring.message code='label.detailaddress'/>">
                  </div>
              </div>
                <div  class="form-group">
                <label for="address"  class="col-sm-2 control-label"></label>
                <div class="col-sm-10">
                 <div class="row text-left">
                 
                  <div class="col-sm-4">
                    <select name="country" id="country" class="selectpicker" data-size="10" title="<@spring.message code='label.choosecountry'/>">
                    </select>
                  </div>
                  
                  <div class="col-sm-4">
                    <select id="province" name="province" class="selectpicker" data-size="10" title="<@spring.message code='label.chooseprovince'/>">
                    </select>
                  </div>
                  
                  <div class="col-sm-4">
                    <select id="city" name="city" class="selectpicker" data-size="10" title="<@spring.message code='label.choosecity'/>">
                    </select>
                  </div>
              	 </div>
               </div>
              </div>
              


              
              <div  class="form-group">
                <label for="deviceid"  class="col-sm-2 control-label"><@spring.message code='label.gatewayID'/>*</label>
                  <div class="col-sm-10">
                <div class="col-sm-8" style="margin-left:0px;padding-left: 0px">
                 <input type="text" class="form-control"  id="deviceid" name="deviceid" placeholder="<@spring.message code='label.gatewayID'/>">
               </div>
                  <div class="col-sm-4">
                      <button id="gatewaydevice" onclick="popup()" type="" class="btn btn-success" style="width:25%;"><@spring.message code='label.query'/></button>
                  </div>
                  </div>
               </div>
               
              <div  class="form-group">
                <label for="serialnumber"  class="col-sm-2 control-label"><@spring.message code='label.phonecardid'/>*</label>
                <div class="col-sm-10">
                 <input type="text" class="form-control" id="serialnumber" name="serialnumber" placeholder="<@spring.message code='label.phonecardid'/>">
               </div>
              </div>
              
              <div id="msg" class="text-center"></div>
              <div class="row text-center">
	              <div class="col-sm-6"><button id="btn-submit" type="submit" class="btn btn-default" style="width:25%;"><@spring.message code='label.submit'/></button></div>
	              <div class="col-sm-6"><button type="reset" class="btn btn-default" style="width:25%;"><@spring.message code='label.reset'/></button></div>
              </div>
              
        </form>

        </div>

        <div class="col-md-1"></div>
    </div>

<!-- JavaScript 部分 -->
    <script type="text/javascript">
    
$(document).ready(function() {
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
            appaccount: {
                validators: {
                    remote: {
                        url: 'validCode',
                        message: 'The code is not available',
                        delay: 2000,
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
            },
            firstname: {
                message: 'The firstname is not valid',
                validators: {
                    notEmpty: {
                        message: 'The firstname is required and cannot be empty'
                    }
                }
            },
            lastname: {
                message: 'The lastname is not valid',
                validators: {
                    notEmpty: {
                        message: 'The lastname is required and cannot be empty'
                    }
                }
            },
            ssn: {
                message: 'The ssn is not valid',
                validators: {
                    notEmpty: {
                        message: 'The ssn is required and cannot be empty'
                    }
                }
            },
            detailaddress: {
                validators: {
                    notEmpty: {
                        message: 'The address is required and cannot be empty'
                    },
                }
            },
            country: {
                validators: {
                    notEmpty: {
                        message: 'The address is required and cannot be empty'
                    },
                }
            },
            province: {
                validators: {
                    notEmpty: {
                        message: 'The address is required and cannot be empty'
                    },
                }
            },
            city: {
                validators: {
                    notEmpty: {
                        message: 'The address is required and cannot be empty'
                    },
                }
            },
            deviceid: {
                message: 'The deviceid is not valid',
                validators: {
                    notEmpty: {
                        message: 'The deviceid is required and cannot be empty'
                    }
                }
            },
            serialnumber: {
                message: 'The serialnumber is not valid',
                validators: {
                    notEmpty: {
                        message: 'The serialnumber is required and cannot be empty'
                    }
                }
            }
        }
    });
});
    function popup() {
        var deviceid = $("#deviceid").val();
        $('#myModal').modal('show');
        //$("#iframeDetail").attr("src", 'gatewayDetail?deviceid='+deid);
        setCookie("id", deviceid);

    }


function fillGateway (obj) {
    var appaccount = obj.value;
    if(appaccount == "" || appaccount == undefined || appaccount == null){}else{
        $.ajax({
            type: 'post',
            url: '../user/fillGateway',
            contentType: 'application/json',
            traditional: true,
            data: appaccount,
            success: function (data) {//返回json结果
                if(data!="failed"){
                    $("#deviceid").val(data);
                }
            },
            error: function () {// 请求失败处理函数
            }

        });
    }

}
//将网关id传递到网关详情页面
function setCookie(name, value) {
    var exp = new Date();
    exp.setTime(exp.getTime() + 100 * 1000);//Cookie有效期设置为10s
    document.cookie = name + "=" + escape(value) + ";expires=" + exp.toGMTString();
    $("#iframeDetail").attr("src", '../gateway/gatewayDetail?deviceid=' + value);
}
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
                        var jsonObj = eval('(' + data + ')');
                        if (jsonObj['status'] == 1) {
                            alert("success");
                            window.location.href = "../welcome.html";
                        }else{
                            alert(formatterReturnStatus(jsonObj['msg']));
                        }
                    },
                    error: function(data) {
                        alert("error");
                     }
                });
        }else{
        	alert(lan.notempty);
        }
});
    </script>
	<script src="../static/js/addressController.js"></script>
<#include "../modal.ftl"/>
<#include "../_foot0.ftl"/>