<#include "../_head0.ftl"/>
    <div class="row-horizontal">
        <div class="col-md-1"></div>
        <div class="col-md-10">
          <form class="form-horizontal" id="defaultForm" method="POST">
          
              <div class="text-center"><h1><@spring.message code='label.enteringuserinfo'/></h1></div>
              <hr>
              <div class="col-sm-12" style="height: 35px">
              <div  class="form-group">
                  <label for="codepostfix"  class="col-sm-2 control-label" style="text-align: left;"><@spring.message code='label.serviceprovider'/></label>
                  <div class="col-sm-10" style="line-height:34px;">
                      ${supname!}
                      <input type="hidden" id="organizationid" name="organizationid" value="${suporgid!}">
                      <#--<select name="organizationid" id="organizationid" class="selectpicker" data-live-search="true" onchange="getGroupidAndCode()"
                              title="<@spring.message code="label.chooseorg"/>">
                      </select>-->
                  </div>
              </div>
              </div>
                  <div class="col-sm-12" style="height: 55px">
                      <div  class="form-group">
                          <label for="codepostfix" class="col-sm-2 control-label" style="text-align: left;"><@spring.message code='label.monitoringstation'/>*</label>
                          <div class="col-sm-10">
                              <select name="monitoringstationid" id="monitoringstation" class="selectpicker" data-live-search="true"
                                      title="<@spring.message code="label.monitoringstation"/>">
                                  <option value="143" >Lanvac Surveillance Inc</option>
                                  <option value="356" >Global Link</option>
                                  <option value="0" selected><@spring.message code="label.none"/></option>
                              </select>
                          </div>
                      </div>
              </div>

              <div class="col-sm-12" style="height: 55px">
                  <div  class="form-group"><#--报警中心所需代码 DNIS-->
                      <label for="codepostfix"  class="col-sm-2 control-label" style="text-align: left;"><@spring.message code='label.groupid'/>*</label>
                      <div class="col-sm-4">
                          <input  class="form-control" id="inscode" name="groupid"  placeholder="<@spring.message code='label.groupid'/>" value="${groupid!}">
                      </div>
                      <label for="codepostfix"  class="col-sm-2 control-label" style="text-align: left"><@spring.message code='label.InstallerPersonList'/>:</label>
                      <div class="col-sm-4" style="line-height:34px;">
                            <#if installername??>${(installername)}(${(installercode)})<#else><@spring.message code='label.none'/></#if>
                      </div>
                  </div>

              </div>
              <div class="col-sm-12" style="height: 35px">
                  <div  class="form-group">
                      <label for="codepostfix"  class="col-sm-2 control-label" style="text-align: left;"></label>
                      <div class="col-sm-10" style="line-height:34px;">
                          <div id="dnistips">*Important:Contact Monitoring Station to have the Station Dealer Number</div>
                      </div>
                  </div>
              </div>
              <div class="col-sm-12" style="height: 35px">
                  <div  class="form-group">
                      <label for="codepostfix"  class="col-sm-2 control-label" style="text-align: left;"><@spring.message code='label.installerorg'/></label>
                      <div class="col-sm-10" style="line-height:34px;">
                      <#if insname??&&groupid??>${(insname)}(${(groupid)})<#else><@spring.message code='label.none'/></#if>
                      </div>
                  </div>
              </div>
              <div class="col-sm-12" style="height: 55px">
              <div  class="form-group"><#--报警中心所需用户代码-->
                  <label for="codepostfix"  class="col-sm-2 control-label" style="text-align: left;"><@spring.message code='label.code'/>*</label>
                  <div class="col-sm-10">
                      <input class="form-control" id="supcode" name="supcode" placeholder="<@spring.message code='label.code'/>" />
                  </div>
              </div>
              </div>
              <div class="col-sm-12" >
              <div  class="form-group"><#--项目所需用户代码-->
                  <label for="codepostfix"  class="col-sm-2 control-label" style="text-align: left;"><@spring.message code='label.aibaseid'/></label>
                  <div class="col-sm-10" style="line-height:34px;">
                  <span id="uback">-</span>
                      <input type="hidden" class="form-control" id="codepostfix" name="usercode" value="">
                      <input type="hidden" class="form-control" id="codepostfix1" >
                  </div>
              </div>
              </div>
              <div class="col-sm-12" >
              <div  class="form-group">
                  <label for="appaccount"  class="col-sm-2 control-label" style="text-align: left;"><@spring.message code='label.apploginemail'/></label>
                  <div class="col-sm-10">
                      <input type="text" class="form-control" id="appaccount" name="appaccount" onblur="fillGateway(this)" placeholder="<@spring.message code='label.apploginemail'/>">
                  </div>
              </div>
              </div>
              <div class="col-sm-12" >
                  <div  class="form-group">
                      <label for="apphometitle"  class="col-sm-2 control-label" style="text-align: left;"><@spring.message code='label.apphometitle'/></label>
                      <div class="col-sm-10">
                          <input type="text" class="form-control" id="apphometitle" name="apphometitle" <#if insname??>value="${(insname)}"</#if> placeholder="<@spring.message code='label.apphometitle'/>">
                      </div>
                  </div>
              </div>
              <div class="col-sm-12" >
              <div  class="form-group">
                <label for="firstname"  class="col-sm-2 control-label" style="text-align: left;"><@spring.message code='label.firstname'/>*</label>
                <div class="col-sm-10">
                 <input type="text" class="form-control" id="firstname" name="firstname" placeholder="<@spring.message code='label.firstname'/>">
                </div>
              </div>
              </div>
              <div class="col-sm-12" >
              <div  class="form-group">
                <label for="lastname"  class="col-sm-2 control-label" style="text-align: left;"><@spring.message code='label.lastname'/>*</label>
                <div class="col-sm-10">
                 <input type="text" class="form-control" id="lastname" name="lastname" placeholder="<@spring.message code='label.lastname'/>">
                </div>
              </div>
              </div>
              <div class="col-sm-12">
                  <div  class="form-group">
                      <label for="phonenumber"  class="col-sm-2 control-label" style="text-align: left;"></label>

                      <div class="col-sm-10">
                        <@spring.message code='label.additioninformation'/>
                            <input type="checkbox" id="addbox" name="addbox"  style="zoom:150%;vertical-align: -3px;">
                      </div>
                  </div>
              </div>
              <div id="additioninformation">
              <div class="col-sm-12">
              <div  class="form-group">
                <label for="phonenumber"  class="col-sm-2 control-label" style="text-align: left;"><@spring.message code='label.phonenumber'/></label>
                <div class="col-sm-10">
                 <input type="text" class="form-control" id="phonenumber" name="phonenumber" placeholder="<@spring.message code='label.phonenumber'/>">
                </div>
              </div>
              </div>
              <div class="col-sm-12" >
              <div  class="form-group">
                <label for="email"  class="col-sm-2 control-label" style="text-align: left;"><@spring.message code='label.email'/></label>
                <div class="col-sm-10">
                 <input type="text" class="form-control" id="email" name="email" placeholder="<@spring.message code='label.email'/>">
               </div>
              </div>
              </div>
              <div class="col-sm-12" >
              <div  class="form-group">
                <label for="fax"  class="col-sm-2 control-label" style="text-align: left;"><@spring.message code='label.fax'/></label>
                <div class="col-sm-10">
                 <input type="text" class="form-control" id="fax" name="fax" placeholder="<@spring.message code='label.fax'/>">
                </div>
              </div>
              </div>
              <div class="col-sm-12" >
              <div  class="form-group">
                  <label for="detailaddress"  class="col-sm-2 control-label" style="text-align: left;"><@spring.message code='label.detailaddress'/></label>
                  <div class="col-sm-10">
                      <input type="text" class="form-control" id="detailaddress" name="detailaddress" placeholder="<@spring.message code='label.detailaddress'/>">
                  </div>
              </div>
              </div>
              <div class="col-sm-12" >
                <div  class="form-group">
                <label for="address"  class="col-sm-2 control-label" style="text-align: left;"></label>
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
              </div>
              </div>
              <div id="addgatewaydiv1">
              <div class="col-sm-12" >
              <div  class="form-group">
                <label for="deviceid"  class="col-sm-2 control-label" style="text-align: left;"><@spring.message code='label.gatewayID'/> 1*</label>
                  <div class="col-sm-10">
                <div class="col-sm-6" style="margin-left:0px;padding-left: 0px">
                 <input type="text" class="form-control"  id="deviceid1" name="deviceid" placeholder="<@spring.message code='label.gatewayID'/>">
               </div>
                  <div class="col-sm-6">
                      <button id="gatewaydevice1" type="button" onclick="popup(1)" type="" class="btn btn-success" style="width:30%;"><@spring.message code='label.query'/></button>
                      <button id="gatewaydevice2" type="button" onclick="addgateway()" type="" class="btn btn-success" style="width:30%;"><@spring.message code='label.addnew'/></button>
                      <button id="gatewaydevice3" type="button" onclick="restoregateway()" type="" class="btn btn-success" style="width:30%;"><@spring.message code='label.restoregateway'/></button>
                  </div>
                  </div>
               </div>
              </div>
              <div class="col-sm-12" >
              <#--<div class="col-sm-2" id="serialnumberdiv">
                <input type="radio" id="serialnumberbox" name="serialnumberbox"  style="zoom:150%;vertical-align: -3px;">YES
                <input type="radio" id="serialnumberbox2" name="serialnumberbox"  checked="checked"  style="zoom:150%;vertical-align: -3px;">NO
               </div>-->
              <div  class="form-group">
                <label for="serialnumber"  class="col-sm-2 control-label" style="text-align: left;"><@spring.message code='label.phonecardid'/> 1</label>
                <div class="col-sm-10" style="height: 45px">
                 <input type="text" class="form-control" id="serialnumber1" name="serialnumber" placeholder="Default NONE. Input serial number if activate">
               </div>
              </div>
              </div>
              </div>
              <div id="msg" class="text-center"></div>
              <div class="row text-center">
	              <div class="col-sm-7"><button id="btn-submit" type="button" class="btn btn-default" style="width:30%;"><@spring.message code='label.submit'/></button></div>
	              <div class="col-sm-5"><button type="reset" class="btn btn-default" style="width:40%;"><@spring.message code='label.reset'/></button></div>
              </div>
              <br/>
              <br/>
        </form>

        </div>

        <div class="col-md-1"></div>
    </div>

<!-- JavaScript 部分 -->
    <script type="text/javascript">


$(document).ready(function() {
    //$("#serialnumber").hide();
    $("#additioninformation").hide();
    cou = 2;
    getCountry(-1);
    $("#dnistips").hide();
    getParentOrg();
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
            monitoringstation: {
                message: 'The Monitoring Station is not valid',
                validators: {
                notEmpty: {
                message: 'The Monitoring Station is required and cannot be empty'
                    }
                }
            },
            groupid: {
                message: 'The DNIS is not valid',
                validators: {
                    notEmpty: {
                        message: 'The DNIS is required and cannot be empty'
                    }, stringLength: {
                        min: 5,
                        max: 5,
                        message: 'The code must be 5 characters long'
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
            }
        }
    });
});
function addgateway() {
    var co = cou;
    var vbottom = "<div id=\"addgatewaydiv"+co+"\"><div class=\"col-sm-12\" >\n" +
            "              <div  class=\"form-group\">\n" +
            "                <label for=\"deviceid\"  class=\"col-sm-2 control-label\" style=\"text-align: left;\"><@spring.message code='label.gatewayID'/> "+co+"*</label>\n" +
            "                  <div class=\"col-sm-10\">\n" +
            "                <div class=\"col-sm-6\" style=\"margin-left:0px;padding-left: 0px\">\n" +
            "                 <input type=\"text\" class=\"form-control\"  id=\"deviceid"+co+"\" name=\"deviceid\" placeholder=\"<@spring.message code='label.gatewayID'/>\">\n" +
            "               </div>\n" +
            "                  <div class=\"col-sm-6\">\n" +
            "                      <button id=\"gatewaydevice\" type=\"button\" onclick=\"popup("+co+")\" type=\"\" class=\"btn btn-success\" style=\"width:30%;\"><@spring.message code='label.query'/></button>\n" +
            "                  </div>\n" +
            "                  </div>\n" +
            "               </div>\n" +
            "              </div>\n" +
            "              <div class=\"col-sm-12\" style=\"height: 45px\">\n" +
            "              <div  class=\"form-group\">\n" +
            "                <label for=\"serialnumber\"  class=\"col-sm-2 control-label\" style=\"text-align: left;\"><@spring.message code='label.phonecardid'/> "+co+"</label>\n" +
            "                <div class=\"col-sm-10\" style=\"height: 45px\">\n" +
            "                 <input type=\"text\" class=\"form-control\" id=\"serialnumber"+co+"\" name=\"serialnumber\" placeholder=\"Default NONE. Input serial number if activate\">\n" +
            "               </div>\n" +
            "              </div>\n" +
            "              </div></div>";
    var vcurrent = document.getElementById("addgatewaydiv"+(co-1));
    //下面
    var newNodeBottom = document.createElement("div");
    newNodeBottom.innerHTML = vbottom;
    cou = co + 1;
    vcurrent.parentNode.insertBefore(newNodeBottom, vcurrent.nextSibling);
}
function restoregateway() {
    for(var i=2;i<cou+1;i++){
        var vcurrent = document.getElementById("addgatewaydiv"+(i));
        if(vcurrent!=null){
            vcurrent.parentNode.removeChild(vcurrent);
        }
    }
    cou = 2;
}
function popup(cou) {
    var deviceid = $("#deviceid"+cou).val();
    $('#myModal').modal('show');
    setCookie("id", deviceid);
}
/*$("#serialnumberdiv").change(function () {
    if($("#serialnumberbox").is(':checked')){
        $("#serialnumber").show();
    }else if($("#serialnumberbox2").is(':checked')){
        $("#serialnumber").hide();
        $("#serialnumber").val("");
    }
});*/
$("#addbox").change(function () {
    if($("#addbox").is(':checked')){
        $("#additioninformation").show();
    }else{
        $("#additioninformation").hide();
    }
});
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
                if(data!="failed" && data.indexOf("[") == 0){
                    var oo = data.substring(1).substring(0,data.length-2)
                    var devicearray = oo.split(",");
                    var gatewaycount = devicearray.length;
                    restoregateway();
                    for(var i=0;i<gatewaycount;i++) {
                        if (i>0) {
                            addgateway();
                        }
                        $("#deviceid"+(i+1)).val(devicearray[i].trim());
                    }
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
    exp.setTime(exp.getTime() + 100 * 1000);//Cookie有效期设置为100s
    document.cookie = name + "=" + escape(value) + ";expires=" + exp.toGMTString();
    $("#iframeDetail").attr("src", '../gateway/gatewayDetail?deviceid=' + value);
}
$("#btn-submit").click(function () {
        for(var i=1;i<cou;i++){
            var de = $("#deviceid"+i).val();
            if(de==null||de==""||de==undefined){
                alert("Gateway ID "+i+" Required!");
                return ;
            }
        }
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
                            window.location.href = "../user/userList";
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


function getParentOrg() {
    $.ajax({
        type: "get",
        url: "../org/listAllSupOrg",
        async: true,
        success: function (data) {
            var parentorgid ="";
            <#if orgInfo.parentorgid??>parentorgid='${(orgInfo.parentorgid)?c}' </#if>
            var str ="";
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
$("#supcode").keyup(function () {
    var citycode = "001";
    var userid = $("#supcode").val();
    if(userid.length>3){
        var codepostfixval = $("#codepostfix1").val();
        var inscode =${(orgInfo.code)!};
        var finalcode = codepostfixval+inscode+${(installercode)}+userid;
        $("#uback").html(finalcode);
        document.getElementById("codepostfix").value= citycode+finalcode;
    }
});
/*$("#inscode").keyup(function () {
    var ndis= $("#inscode").val();
    if(ndis.length>4){
        $("#dnistips").hide();
    }else{
        $("#dnistips").show();
    }
});*/
$("#monitoringstation").change(function () {
    var ndisselect = $("#monitoringstation").val();
    if(ndisselect!=0){
        $("#dnistips").show();
        $("#inscode").val("");
    }else{
        $("#dnistips").hide();
        $("#inscode").val(${(groupid)});
    }

});
function getGroupidAndCode() {
    var suporgid = $("#organizationid").val();
    if(suporgid==""||suporgid==null||suporgid==undefined){
        return ;
    }
    $.ajax({
        type: 'get',
        url: '../user/findsuppliercode',
        contentType: 'application/json',
        traditional: true,
        data: {
            suporgid:suporgid,
        },
        success: function (data) {//返回json结果
            //$("#uback").html(data);
            document.getElementById("codepostfix1").value= data;
        },
        error: function () {
        }

    });
}
/*function getGroupidAndCode() {
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
}*/
    </script>
	<script src="../static/js/addressController.js"></script>
<#include "../modal.ftl"/>
<#include "../_foot0.ftl"/>