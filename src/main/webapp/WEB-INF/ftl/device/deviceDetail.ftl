<!-- 网关详情 -->
<#include "../_head0.ftl"/>
    <div class="row-horizontal">
        <div class="col-md-1"></div>
        <div class="col-md-8">
            <div class="text-center"><h1><@spring.message code="label.devicedetail"/></h1></div>
            <br>
            <br>

            <div class="row">
                <div class="col-md-2"></div>
                <#if zwave??>
                    <div class="col-md-8">
                        <table class="table table-hover">
                            <tr><#--网关id-->
                                <th><@spring.message code="label.gatewayID"/></th>
                                <th><#if (zwave.deviceid)??>${zwave.deviceid}<#else><@spring.message code="label.none"/></#if></th>
                            </tr>
                            <tr>
                                <th width="50%"><@spring.message code="label.devicename"/></th>
                                <th width="50%"><#if (zwave.name)??>${zwave.name}<#else><@spring.message code="label.none"/></#if></th>
                            </tr>
                           <#-- <tr hidden>
                                <th>id</th>
                                <th id="zwavedevicetype">${(zwave.devicetype)!}</th>
                            </tr>-->
                            <tr><#--设备类型-->
                                <th><@spring.message code="label.devicetype"/></th>
                                <th id="zwavedevicetype"><#if (zwave.devicetype)??>${zwave.devicetype}<#else><@spring.message code="label.none"/></#if></th>
                            </tr>
                            <tr>

                            <tr><#--服务商-->
                                <th><@spring.message code="label.serviceprovider"/></th>
                                 <th><#if (zwave.organizationname)??>${zwave.organizationname}<#else><@spring.message code="label.none"/></#if></th>
                            </tr>
                            <tr>
                            <tr><#--安装商-->
                                <th><@spring.message code="label.installerorg"/></th>
                                 <th><#if (zwave.installerorgname)??>${zwave.installerorgname}<#else><@spring.message code="label.none"/></#if></th>
                            </tr>
                            <tr><#--安装员-->
                                <th><@spring.message code="label.installer"/></th>
                                <th><#if (zwave.installername)??>${zwave.installername}<#else><@spring.message code="label.none"/></#if></th>
                            </tr>
                            <tr><#--用户-->
                                <th><@spring.message code="label.user"/></th>
                                <th><#if (zwave.username)??>${zwave.username}<#else><@spring.message code="label.none"/></#if></th>
                            </tr>
                            <tr><#--告警状态-->
                                <th><@spring.message code="label.alarmstatus"/></th>
                                <th id="zwavewarningstatus"><#if (zwave.warningstatuses)??>${zwave.warningstatuses}<#else><@spring.message code="label.none"/></#if></th>
                            </tr>
                            <tr>

                            <tr><#--状态-->
                                <th><@spring.message code="label.status"/></th>
                                <th id="status"><#if (zwave.status)??>${zwave.status}<#else><@spring.message code="label.none"/></#if></th>
                            </tr>
                            <#if (zwave.statuses)??>
                            <tr><#--多位状态-->
                                <th><@spring.message code="label.statuses"/></th>
                                <th id="statuses"><#if (zwave.statuses)??>${zwave.statuses}<#else><@spring.message code="label.none"/></#if></th>
                            </tr>
                            </#if>
                            <tr><#--电量-->
                                <th><@spring.message code="label.energy"/></th>
                                <th><#if (zwave.battery)??>${zwave.battery}<#else><@spring.message code="label.none"/></#if></th>
                            </tr>
                            <tr><#--地区-->
                                <th><@spring.message code="label.area"/></th>
                                <th><#if (zwave.city)??>${zwave.city}<#else><@spring.message code="label.none"/></#if></th>
                            </tr>

                    <@shiro.hasPermission name="toggleDeviceStatus">
                            <tr  id="operatetr" ><#--操作-->
                                <th><@spring.message code="label.operate"/></th>
                                <th>
                                    <button id="operate" type="submit" class="btn btn-default"
                                            onclick='toggleDeviceStatus(${(zwave.zwavedeviceid)?c!})'
                                            style="width:40%"><@spring.message code="label.close"/></button>
                                </th>
                            </tr>
                    </@shiro.hasPermission>
                    <@shiro.hasPermission name="label.ModifyDeviceArea">
                            <tr>
                                <th><@spring.message code="label.areanumber"/></th>
                                <th>
                                    <#if (zwave.area??&&zwave.area>1000)>1-${(zwave.area-1000)?c}<#else>
                                    <input type="text" style="width:150px" class="form-control" id="area" name="area" value="${(zwave.area)!}"
                                           placeholder="<@spring.message code="label.areanumber"/>"></#if>
                                </th>
                            </tr>
                        <#if (zwave.area??&&zwave.area>1000)>
                        <#else><tr>
                                <th></th>
                                <th><button id="area" type="submit" class="btn btn-default" onclick='savearea()'
                                            style="width:150px"><@spring.message code="label.save"/></button></th></tr></#if>
                    </@shiro.hasPermission>
                        </table>
                    </div>

                <#else>
                     <div class="col-md-6">
                        <#-- <h3><@spring.message code="label.mustchooseadevice"/></h3>-->
                         <h3>Loading...</h3>
                     </div>
                </#if>
                <div class="col-md-2"></div>
            </div>
        </div>
        <div class="col-md-3"></div>
    </div>
<!-- JavaScript 部分 -->
<script type="text/javascript">
    $(function () {
        formatterwarning();
        hiddenOperate();
        formatterstatus();
        formatterstatuses();
        formatterdevicetype();
        if (${(zwave.devicetype)!'-1'} == 5 || ${(zwave.devicetype)!'-1'}== 19 || ${(zwave.devicetype)!'-1'}== 22){//如果是门锁，锁芯，门禁机
            if (${(zwave.status)!"-1"}== 255){
                $("#operate").text("<@spring.message code="label.open"/>");
            }else{
                $("#operate").text("<@spring.message code="label.close"/>");
            }
        }else{
            if (${(zwave.status)!"-1"}== 0){
                $("#operate").text("<@spring.message code="label.open"/>");
            }else{
                $("#operate").text("<@spring.message code="label.close"/>");
            }
        }
    });

    function formatterwarning() {
        if ($("#zwavewarningstatus").text() != "None" && $("#zwavewarningstatus").text() != "无" && $("#zwavewarningstatus").text() != "NONE"&& $("#zwavewarningstatus").text() != "") {
            var strs = [];
            /*if($("#zwavewarningstatus").text().search("[") != -1){*/
                var json = JSON.parse($("#zwavewarningstatus").text());
                for (var i = 0; i < json.length; i++) {
                    strs[i] = formatter_zwavewarning($("#zwavedevicetype").text(), json[i]);
                }

           // }
        }
        // alert(strs);
        $("#zwavewarningstatus").text(strs);
    }

    function formatterdevicetype() {
        var formatterDevicetype1 = formatter_devicetype1(${(zwave.devicetype)!'-1'});

        //var formatterDevicetype1 =  formatter_devicetype(${(zwave.devicetype)!'-1'});
        $("#zwavedevicetype").text(formatterDevicetype1);
    }

    function formatterstatus(){
        var formatterStatus = formatter_devicestatus1("${(zwave.devicetype)!'-1'}",${(zwave.status)!-1});
        $("#status").text(formatterStatus);
    }
    function formatterstatuses(){
        if ($("#statuses").text() != "None" && $("#statuses").text() != "无" && $("#statuses").text() != "NONE"&& $("#statuses").text() != "") {
            var strs = [];
            var json = JSON.parse($("#statuses").text());
            for (var i = 0; i < json.length; i++) {
                strs[i] = formatter_zwavestatuses(i,$("#zwavedevicetype").text(), json[i]);
            }
            var str1 = strs.toString();
            var str2 = str1.replace(/-/g,'');
            var str3 = str2.replace(/,/g,'');
            var str4 = str3.replace(/"/g,'');
            if(str4==''){
                $("#statuses").text('-');
            }else{
                $("#statuses").text(strs);
            }
        }
    }
    function hiddenOperate(){
        if(!toggleStatusAbleDevicetype(${(zwave.devicetype)}) || ${(zwave.status)!"-1"}=="-1") {
            $("#operatetr").css("visibility", "hidden");
        }
    }


    function toggleDeviceStatus(id) {
        if (!toggleStatusAbleDevicetype(${(zwave.devicetype)}) || ${(zwave.status)!"-1"}=="-1") {
            alert("<@spring.message code="label.canttoggledevice"/>");
            return null;
        }
        var toStatus;//统一设置为1为打开命令，0为关闭命令
        if (${(zwave.devicetype)!'-1'}==5 || ${(zwave.devicetype)!'-1'}== 19 || ${(zwave.devicetype)!'-1'}== 22){//如果是门锁，锁芯，门禁机
            if (${(zwave.status)!"-1"} ==255){//目前是关
                toStatus = 1;
            }else{
                toStatus = 0;
            }
        }else{
            if (${(zwave.status)!"-1"} ==0){//目前是关
                if(${(zwave.devicetype)}==13){
                    toStatus = 99;
                }else if(${(zwave.devicetype)}==14){
                    toStatus = 1;
                }else if(${(zwave.devicetype)}==20){
                    toStatus = 99;
                }else{
                    toStatus = 255;
                }
            }else{//目前不为关
                toStatus = 0;
            }
        }

        var data0 ={zwavedeviceid:${(zwave.zwavedeviceid)?c!},toStatus:toStatus};
        $.ajax({
            type: 'post',
            url: 'toggleDeviceStatus',
            traditional: true,
            data: JSON.parse(JSON.stringify(data0)),
            success: function (data) {//返回json结果
                if (data['status'] == 1) {
                    alert("<@spring.message code='label.updatesuccess'/>");
                    window.location.href="deviceDetail?zwavedeviceid=${(zwave.zwavedeviceid)}";
                } else {
                    alert(formatterReturnStatus(data['msg']));
                }
            },
            error: function () {// 请求失败处理函数
                alert("<@spring.message code='label.updatefailed'/>");
            }
        });
    }

    function  savearea() {
        var areanumber = '${zwave.zwavedeviceid}'+"#"+$("#area").val();
        $.ajax({
            type: 'post',
            url: 'savearea',
            contentType: 'application/json',
            traditional: true,
            data: areanumber,
            success: function (data) {//返回json结果
                if (data == "success") {
                    alert("<@spring.message code='label.updatesuccess'/>");
                } else if(data =="format") {
                    alert("<@spring.message code='label.format1100'/>");
                }else{
                    alert("<@spring.message code='label.updatefailed'/>");
                }
            },
            error: function () {// 请求失败处理函数
                alert("<@spring.message code='label.updatefailed'/>");
            }
        });
    }
</script>

<#include "../_foot0.ftl"/>