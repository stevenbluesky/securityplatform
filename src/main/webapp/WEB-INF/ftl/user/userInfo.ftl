<!-- 录入电话卡信息页面 -->
<#include "../_head0.ftl"/>
    <div class="row-horizontal">

        <div class="col-md-12">
            <form class="form-horizontal" id="defaultForm" method="POST">
                <div class="text-center"><h1><@spring.message code='label.userInfo'/></h1></div>
                <hr>
                <div class="form-group">
                    <label for="codepostfix"
                           class="col-sm-2 "><@spring.message code='label.usercodepostfix'/></label>
                    <div class="col-sm-4">
                    ${(userVO.codepostfix)!}
                    </div>
                    <label for="codepostfix"
                           class="col-sm-2 "><@spring.message code='label.alarmcode'/></label>
                    <div class="col-sm-4">
                    ${(userVO.supcode)!}
                    </div>
                </div>
                <div class="form-group">
                    <label for="firstname" class="col-sm-2 " ><@spring.message code='label.firstname'/></label>
                    <div class="col-sm-4">
                    ${(userVO.firstname)!}
                    </div>

                    <label for="lastname" class="col-sm-2 " ><@spring.message code='label.lastname'/>
                    </label>
                    <div class="col-sm-4">
                    ${(userVO.lastname)!}
                    </div>
                </div>

                <div class="form-group">

                    <label for="codepostfix"
                           class="col-sm-2 "><@spring.message code='label.appaccount'/></label>
                    <div class="col-sm-4">
                    ${(userVO.appaccount)!}
                    </div>
                    <label for="ssn" class="col-sm-2 "><@spring.message code='label.ssn'/></label>
                    <div class="col-sm-4">
                    ${(userVO.ssn)!}
                    </div>
                </div>

                <div class="form-group">
                    <label for="phonenumber"
                           class="col-sm-2 "><@spring.message code='label.phonenumber'/></label>
                    <div class="col-sm-4">
                    ${(userVO.phonenumber)!}
                    </div>

                    <label for="email" class="col-sm-2 "><@spring.message code='label.email'/></label>
                    <div class="col-sm-4">
                    ${(userVO.email)!}
                    </div>
                </div>

                <div class="form-group">
                    <label for="fax" class="col-sm-2 "><@spring.message code='label.fax'/></label>
                    <div class="col-sm-4">
                    ${(userVO.fax)!}
                    </div>
                    <label for="detailaddress"
                           class="col-sm-2 "><@spring.message code=''/></label>
                    <div class="col-sm-4">

                    </div>

                </div>

                <div class="form-group">
                    <label for="address" class="col-sm-2 "><@spring.message code='label.detailaddress'/></label>
                    <div class="col-sm-10">
                    ${(userVO.detailaddress)!}&nbsp;&nbsp;
                    ${(userVO.countryname)!}&nbsp;${(userVO.provincename)!}&nbsp;${(userVO.cityname)!}
                    </div>


                </div>
                <hr>
                <div class="form-group">
                    <label for="detailaddress"
                           class="col-sm-2 "><@spring.message code='label.serviceprovider'/></label>
                    <div class="col-sm-10">
                    ${(userVO.serviceprovider)!}
                    </div>
                </div>
                <div class="form-group">
                    <label for="detailaddress"
                           class="col-sm-2 "><@spring.message code='label.parentInstaller'/></label>
                    <div class="col-sm-4">
                    ${(userVO.installerorg)!}
                    </div>

                    <label for="detailaddress"
                           class="col-sm-2 "><@spring.message code='label.installer'/></label>
                    <div class="col-sm-4">
                    ${(userVO.installer)!}
                    </div>
                </div>

                <hr>
                <div class="form-group">
                    <label for="deviceid" class="col-sm-2 "><@spring.message code='label.gatewayID'/>
                        </label>
                    <div class="col-sm-4">
                    ${(userVO.deviceid)!}
                    <#if (userVO.deviceid)??>
                        <input type="hidden" id="deviceid" name="deviceid" value="${userVO.deviceid}">
                        <@shiro.hasPermission name="label.UnbundlingUserGateway">
                            <input style="margin-left:10px;" class="btn btn-sm" value='<@spring.message code="label.unbundling"/>' type="button" onclick='unbundlingUserWithGateway();'></button>
                        </@shiro.hasPermission>
                    </#if>
                    </div>
                    <label for="address" class="col-sm-2 "><@spring.message code='label.status'/></label>
                    <div class="col-sm-4">
                    <#if (userVO.deviceid)??>
                        <#if (userVO.gatewaystatus)?exists && userVO.gatewaystatus==1><@spring.message code="label.online"/><#elseif (userVO.gatewaystatus)?exists && userVO.gatewaystatus==0><@spring.message code="label.offline"/><#else><@spring.message code="label.unknown"/></#if>
                    </#if>
                    </div>
                </div>
                <div class="form-group">
                    <label for="serialnumber" class="col-sm-2 "><@spring.message code='label.serialnumber'/></label>
                    <div class="col-sm-4">
                        <#if (userVO.userid)??><input type="hidden" id="userid" name="userid" value="${userVO.userid}"></#if>
                        <#if (userVO.serialnumber)??><input type="hidden" id="serialnumber" name="serialnumber" value="${userVO.serialnumber}"></#if>
                        <#if (userVO.phonecardid)??><input type="hidden" id="phonecardid" name="phonecardid" value="${userVO.phonecardid}"></#if>
                    ${(userVO.serialnumber)!}
                    <#if (userVO.serialnumber)??>
                        <@shiro.hasPermission name="label.UnbundlingUserGateway">
                            <input style="margin-left:10px;" class="btn btn-sm" value='<@spring.message code="label.unbundling"/>' type="button" onclick='unbundlingUserWithPhoneNmber();'></button>
                        </@shiro.hasPermission>
                    </#if>
                    </div>
                    <label for="address" class="col-sm-2 "><@spring.message code='label.status'/></label>
                    <div class="col-sm-4">
                            <#if (userVO.serialnumber)??>
                             <#if (userVO.status)?exists && userVO.status==1><@spring.message code="label.normal"/><#elseif (userVO.status)?exists && userVO.status==2><@spring.message code="label.suspenced"/><#elseif (userVO.status)?exists && userVO.status==9><@spring.message code="label.delete"/><#else><@spring.message code="label.unknown"/></#if>
                            </#if>
                        <@shiro.hasPermission name="label.SynchronousSIMInfo">
                            <#if (userVO.serialnumber)??>
                        <button style="" type="button" class="btn btn-sm" onclick='updatePhonecardStatus("synchronous");'><@spring.message code="label.synchronous"/></button>
                            </#if>
                        </@shiro.hasPermission>
                        <@shiro.hasPermission name="label.ActiveeSIM">
                            <#if (userVO.status)?exists && userVO.status==2>
                        <button style="" type="button" id='startPhonecard' class='btn btn-sm' onclick='updatePhonecardStatus("start");'><@spring.message code='label.start'/></button>
                            </#if>
                        </@shiro.hasPermission>
                            <@shiro.hasPermission name="label.FreezeSIM">
                            <#if (userVO.status)?exists && userVO.status==1>
                        <button style="" type="button" id='stopPhonecard' class='btn btn-sm' onclick='updatePhonecardStatus("freeze");'><@spring.message code='label.freeze'/></button>
                            </#if>
                        </@shiro.hasPermission>
                    </div>
                </div>

            </form>

        </div>


    </div>

<!-- JavaScript 部分 -->
    <script type="text/javascript">
        function formatterGender(){
            let gender = ${(userVO.gender)!"-1"};
            let formatterStatus = formatter_gender(gender);
            $("#gender").text(formatterStatus);
        }
        function unbundlingUserWithPhoneNmber() {
            if (!confirm("<@spring.message code='label.unbundlingconfirm'/>")) {
                return;
            }
           var userid = $("#userid").val();
           var serialnumber = $("#serialnumber").val();
           var mydata = userid+"#"+serialnumber;
            $.ajax({
                type: 'post',
                url: '../user/unbundling',
                contentType: 'application/json',
                traditional: true,
                data: mydata,
                success: function (data) {//返回json结果
                    if("1"==data) {
                        alert("<@spring.message code='label.updatesuccess'/>");
                    }else{
                        alert("<@spring.message code='label.updatefailed'/>");
                    }
                   window.location.reload();
                },
                error: function () {// 请求失败处理函数
                    alert("<@spring.message code='label.updatefailed'/>");
                    $('#table').bootstrapTable('refresh');
                }

            });
        }
        function unbundlingUserWithGateway(){
            if (!confirm("<@spring.message code='label.unbundlingconfirm'/>")) {
                return;
            }
            var mydata =$("#deviceid").val();;
            $.ajax({
                type: 'post',
                url: '../user/unbundlinggateway',
                contentType: 'application/json',
                traditional: true,
                data: mydata,
                success: function (data) {//返回json结果
                    if("1"==data) {
                        alert("<@spring.message code='label.updatesuccess'/>");
                    }else{
                        alert("<@spring.message code='label.updatefailed'/>");
                    }
                    window.location.reload();
                },
                error: function () {// 请求失败处理函数
                    alert("<@spring.message code='label.updatefailed'/>");
                    $('#table').bootstrapTable('refresh');
                }

            });
        }
        $(function () {
            formatterGender();
        });
        //更新状态
        function updatePhonecardStatus(obj) {
            var confirmdelete = "";
            var id = $("#phonecardid").val();
            if (obj == "start") {
                if (!confirm("<@spring.message code='label.startconfirm'/>")) {
                    return;
                }
            }
            if (obj == "freeze") {
                if (!confirm("<@spring.message code='label.freezeconfirm'/>")) {
                    return;
                }
            }
            if (obj == "delete") {
                confirmdelete = prompt("<@spring.message code='label.phonecarddeleteconfirm'/>", "");
                if (JSON.stringify(confirmdelete) == "{}") {
                    return;
                }
            }
            if (obj == "synchronous") {
                if (!confirm("<@spring.message code='label.synchronousconfirm'/>")) {
                    return;
                }
            }
            //异步更新
            $.ajax({
                type: 'post',
                url: '../phonecard/update',
                contentType: 'application/json',
                traditional: true,
                data: "{\"hope\":\"" + obj + "\",\"ids\":[" + id + "],\"confirmdelete\":\"" + confirmdelete + "\"}",
                success: function (data) {//返回json结果
                    if ("1" == data["msg"]) {
                        alert("<@spring.message code='label.updatesuccess'/>");
                    } else if (data["msg"] == "-900") {
                        alert("<@spring.message code='label.needcorrectchar'/>");
                    } else if (data["msg"] == "-899") {
                    }
                    else if (data["msg"] == "-898") {
                        alert("<@spring.message code='label.albinding'/>");
                    }
                    else {
                        alert("<@spring.message code='label.updatefailed'/>");
                    }
                    window.location.reload();
                },
                error: function (data) {// 请求失败处理函数
                    alert("<@spring.message code='label.updatefailed'/>");
                    window.location.reload();
                }
            });
        }
    </script>
<#include "../_foot0.ftl"/>