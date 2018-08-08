<!-- 录入电话卡信息页面 -->
<#include "../_head0.ftl"/>
    <div class="row-horizontal">

        <div class="col-md-12">
            <form class="form-horizontal" id="defaultForm" method="POST">
                <div class="text-center"><h1><@spring.message code='label.userInfo'/></h1></div>
                <hr>
                <div class="form-group">
                    <label for="codepostfix"
                           class="col-sm-2 "><@spring.message code='label.aibaseid'/></label>
                    <div class="col-sm-4">
                        <#if userVO.usercode??>${(userVO.usercode)!}<#else><@spring.message code='label.none'/></#if>
                    </div>
                </div>
                <div class="form-group">
                    <label for="codepostfix"
                           class="col-sm-2 "><@spring.message code='label.groupid'/></label>
                    <div class="col-sm-4">
                    <#if userVO.groupid??>${(userVO.groupid)!}<#else><@spring.message code='label.none'/></#if>
                    </div>
                    <label for="codepostfix"
                           class="col-sm-2 "><@spring.message code='label.code'/></label>
                    <div class="col-sm-4">
                    <#if userVO.supcode??>${(userVO.supcode)!}<#else><@spring.message code='label.none'/></#if>
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
                           class="col-sm-2 "><@spring.message code='label.apploginemail'/></label>
                    <div class="col-sm-4">
                    ${(userVO.appaccount)!}
                    </div>
                    <label for="fax" class="col-sm-2 "><@spring.message code='label.fax'/></label>
                    <div class="col-sm-4">
                    ${(userVO.fax)!}
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
                           class="col-sm-2 "><@spring.message code='label.InstallerPersonList'/></label>
                    <div class="col-sm-4">
                    ${(userVO.installer)!}
                    </div>
                </div>

                <hr>
    <#assign advList = userVO.gpVO! />
    <#if !advList?exists || advList?size==0>
    <#else>
        <#list advList as adv>
            <div class="form-group">
                <label for="deviceid" class="col-sm-2 "><@spring.message code='label.gatewayID'/> ${adv_index+1}
                </label>
                <div class="col-sm-4">
            <#if (adv.deviceid)??>
                    ${adv.deviceid!}
                    <#if (adv.deviceid)??><input readonly="readonly" type="hidden" id="deviceid${adv_index+1}" name="deviceid" value="${adv.deviceid!}"></#if>
                    <@shiro.hasPermission name="label.UnbundlingUserGateway">
                        <input style="margin-left:10px;" class="btn btn-sm" value='<@spring.message code="label.unbundling"/>' type="button" onclick='unbundlingUserWithGateway(${adv_index+1});'></button>
                    </@shiro.hasPermission>
                </div>
            </#if>
                <label for="address" class="col-sm-2 "><@spring.message code='label.status'/></label>
                <div class="col-sm-4">
                    <#if (adv.deviceid)??>
                        <#if (adv.gatewaystatus)?exists && adv.gatewaystatus==1><@spring.message code="label.online"/><#elseif (adv.gatewaystatus)?exists && adv.gatewaystatus==0><@spring.message code="label.offline"/><#else><@spring.message code="label.unknown"/></#if>
                    </#if>
                </div>
            </div>
            <div class="form-group">
                <label for="serialnumber" class="col-sm-2 "><@spring.message code='label.serialnumber'/> ${adv_index+1}</label>
                <div class="col-sm-4">
                    <#if (userVO.userid)??><input type="hidden" id="userid" name="userid" value="${userVO.userid?c}"></#if>
                    <#if (adv.serialnumber)??><input readonly="readonly" type="hidden" id="serialnumber${adv_index+1}" name="serialnumber" value="${adv.serialnumber!}"></#if>
                    <#if (adv.phonecardid)??><input type="hidden" id="phonecardid${adv_index+1}" name="phonecardid" value="${adv.phonecardid?c}"></#if>
                    <#if (adv.serialnumber)??>
                        ${adv.serialnumber!}
                        <@shiro.hasPermission name="label.UnbundlingUserGateway">
                            <input style="margin-left:10px;" class="btn btn-sm" value='<@spring.message code="label.unbundling"/>' type="button" onclick='unbundlingUserWithPhoneNmber(${adv_index+1});'></button>
                        </@shiro.hasPermission>
                    </#if>
                </div>
                <label for="address" class="col-sm-2 "><@spring.message code='label.status'/></label>
                <div class="col-sm-4">
                    <#if (adv.serialnumber)??>
                        <#if (adv.phonecardstatus)?exists && adv.phonecardstatus==1><@spring.message code="label.activated"/><#elseif (adv.phonecardstatus)?exists && adv.phonecardstatus==2><@spring.message code="label.deactivated"/><#elseif (adv.phonecardstatus)?exists && adv.phonecardstatus==3><@spring.message code="label.inventory"/><#elseif (adv.phonecardstatus)?exists && adv.phonecardstatus==0><@spring.message code="label.noeffect"/><#else><@spring.message code="label.unknown"/></#if>
                    </#if>
                    <@shiro.hasPermission name="label.SynchronousSIMInfo">
                        <#if (adv.serialnumber)??>
                            <button style="" type="button" class="btn btn-sm" onclick='updatePhonecardStatus("synchronous#${adv_index+1}");'><@spring.message code="label.synchronous"/></button>
                        </#if>
                    </@shiro.hasPermission>
                    <@shiro.hasPermission name="label.ActiveeSIM">
                        <#if (adv.phonecardstatus)?exists && adv.phonecardstatus==2>
                            <button style="" type="button" id='startPhonecard' class='btn btn-sm' onclick='updatePhonecardStatus("start#${adv_index+1}");'><@spring.message code='label.start'/></button>
                        </#if>
                    </@shiro.hasPermission>
                    <@shiro.hasPermission name="label.FreezeSIM">
                        <#if (adv.phonecardstatus)?exists && adv.phonecardstatus==1>
                            <button style="" type="button" id='stopPhonecard' class='btn btn-sm' onclick='updatePhonecardStatus("freeze#${adv_index+1}");'><@spring.message code='label.freeze'/></button>
                        </#if>
                    </@shiro.hasPermission>
                </div>
            </div>
        </#list>
    </#if>
            </form>
        </div>
    </div>

<!-- JavaScript 部分 -->
    <script type="text/javascript">
        function unbundlingUserWithPhoneNmber(obj) {
            if (!confirm("<@spring.message code='label.unbundlingconfirm'/>")) {
                return;
            }
           var userid = $("#userid").val();
           var serialnumber = $("#serialnumber"+obj).val();
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
        function unbundlingUserWithGateway(obj){
            if (!confirm("<@spring.message code='label.unbundlingconfirm'/>")) {
                return;
            }
            var mydata =$("#deviceid"+obj).val();
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
        //更新状态
        function updatePhonecardStatus(obje) {
            var nu = obje.split("#")[1];
            var obj = obje.split("#")[0];
            var confirmdelete = "";
            var id = $("#phonecardid"+nu).val();
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