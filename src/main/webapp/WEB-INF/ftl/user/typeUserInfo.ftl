<!-- 录入电话卡信息页面 -->
<#include "../_head0.ftl"/>
    <div class="row-horizontal">
        <div class="col-md-1"></div>
        <div class="col-md-10">
          <form id="defaultForm" method="POST">
          
              <div class="text-center"><h1><@spring.message code='label.enteringuserinfo'/></h1></div>
              <hr>
              <div  class="form-group">
                  <label for="codepostfix"  class="col-sm-2 control-label"><@spring.message code='label.serviceprovider'/>*</label>
                  <div class="col-sm-10">
                      <select name="organizationid" id="organizationid" class="selectpicker" data-live-search="true" onchange="getGroupidAndCode()"
                              title="<@spring.message code="label.chooseorg"/>">
                      </select>
                  </div>
              </div>
              <div  class="form-group">
                  <label for="codepostfix"  class="col-sm-2 control-label"><@spring.message code='label.installerorg'/></label>
                  <div class="col-sm-10">
                  <#if insname??>${(insname)}<#else><@spring.message code='label.none'/></#if>
                  </div>
              </div>
              <div  class="form-group"><#--报警中心所需用户代码-->
                  <label for="codepostfix"  class="col-sm-2 control-label"><@spring.message code='label.groupid'/></label>
                  <div class="col-sm-10">
                      <input  class="form-control" id="inscode" name="groupid" value="${(orgInfo.code)!}" placeholder="<@spring.message code='label.groupid'/>">
                  </div>
              </div>
              <div  class="form-group"><#--报警中心所需用户代码-->
                  <label for="codepostfix"  class="col-sm-2 control-label"><@spring.message code='label.alarmcode'/></label>
                  <div class="col-sm-10">
                      <input class="form-control" id="supcode" name="supcode" placeholder="<@spring.message code='label.alarmcode'/>" />
                  </div>
              </div>
              <div  class="form-group"><#--项目所需用户代码-->
                  <label for="codepostfix"  class="col-sm-2 control-label"><@spring.message code='label.usercodepostfix'/></label>
                  <div class="col-sm-10">
                  <span id="uback"><#if userCode??>${(userCode)!}<#else><@spring.message code='label.none'/></#if></span>
                      <input type="hidden" class="form-control" id="codepostfix" name="usercode" value="${(userCode)!}">
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
	              <div class="col-sm-6"><button id="btn-submit" type="button" class="btn btn-default" style="width:25%;"><@spring.message code='label.submit'/></button></div>
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
            organizationid: {
                message: 'The Distributor is not valid',
                validators: {
                    notEmpty: {
                        message: 'The Distributor is required and cannot be empty'
                    }
                }
            },
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
            inscode: {
                validators: {
                    notEmpty: {
                        message: 'The code is required and cannot be empty'
                    }, stringLength: {
                        min: 5,
                        max: 5,
                        message: 'The code must be 5 characters long'
                    },
                    regexp: {
                        regexp: /[1-9][0-9][0-9][0-9][0-9]/g,
                        message: 'The code format is 10001-99999.'
                    }
                }
            },
            supcode: {
                validators: {
                    notEmpty: {
                        message: 'The code is required and cannot be empty'
                    }, stringLength: {
                        min: 4,
                        max: 4,
                        message: 'The code must be 4 characters long'
                    },
                    regexp: {
                        regexp: /[1-9][0-9][0-9][0-9]/g,
                        message: 'The code format is 1001-9999.'
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
getParentOrg();

function getParentOrg() {
    $.ajax({
        type: "get",
        url: "../org/listAllSupOrg",
        async: true,
        success: function (data) {
            var parentorgid ="";
            <#if orgInfo.parentorgid??>parentorgid='${(orgInfo.parentorgid)}' </#if>
            var str ="<option value=''></option>";
            for (var i = 0; i < data.length; i++) {
                //str += '<option value=' + data[i].organizationid + '>' + data[i].name + '</option>'
                if(data[i].organizationid == parentorgid) {
                    str += "<option value='" + data[i].organizationid + "' selected='selected' >" + data[i].name + "</option>"
                }else {
                    str += '<option value=' + data[i].organizationid + '>' + data[i].name + '</option>'
                }
            }
            $("#organizationid").html(str);

            $("#organizationid").selectpicker('refresh');
            getGroupidAndCode();
        }
    });
}

function getGroupidAndCode() {
    var suporgid = $("#organizationid").val();
    if(suporgid==""||suporgid==null||suporgid==undefined){
        return ;
    }
    $.ajax({
        type: 'get',
        url: '../user/findCodes',
        contentType: 'application/json',
        traditional: true,
        data: {
            suporgid:suporgid,
        },
        success: function (data) {//返回json结果
            var split = data.split("#");
            //$("#sback").html(split[0]);
            $("#uback").html(data);
            //document.getElementById("supcode").value= split[0];
            document.getElementById("codepostfix").value= data;
        },
        error: function () {// 请求失败处理函数
        }

    });
}
    </script>
	<script src="../static/js/addressController.js"></script>
<#include "../modal.ftl"/>
<#include "../_foot0.ftl"/>