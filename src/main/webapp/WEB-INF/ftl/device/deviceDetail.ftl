<!-- 网关详情 -->
<#include "../_head0.ftl"/>
    <div class="row-horizontal">
        <div class="col-md-1"></div>
        <div class="col-md-8">
            <div class="text-center"><h1><@spring.message code="label.devicedetail"/></h1></div>
            <br>
            <br>
            <br>
            <br>
            <div class="row">
                <div class="col-md-3"></div>

                <#if zwave??>
                    <div class="col-md-8">
                        <table class="table table-hover">
                            <tr>
                                <th width="50%"><@spring.message code="label.devicename"/></th>
                                <th width="50%">${(zwave.devicename)!'NONE'}</th>
                            </tr>
                            <tr hidden>
                                <th>id</th>
                                <th id="zwavedevicetype">${(zwave.devicetype)!}</th>
                            </tr>
                            <tr>
                                <th><@spring.message code="label.serviceprovider"/></th>
                                <th>${(zwave.suppliename)!'NONE'}</th>
                            </tr>
                            <tr>
                                <th><@spring.message code="label.alarmstatus"/></th>
                                <th id="zwavewarningstatus">${(zwave.warningstatuses)!'NONE'}</th>
                            </tr>
                            <tr id="operatetr" >
                                <th><@spring.message code="label.operate"/></th>
                                <th>
                                    <button id="operate" type="submit" class="btn btn-default"
                                            onclick='toggleDeviceStatus(${(zwave.zwavedeviceid)?c!})'
                                            style="width:28%"><@spring.message code="label.close"/></button>
                                </th>
                            </tr>
                        </table>
                    </div>
                <#else>
                     <div class="col-md-6">
                         <h3><@spring.message code="label.mustchooseadevice"/></h3>
                     </div>
                </#if>
                <div class="col-md-1"></div>
            </div>
        </div>
        <div class="col-md-3"></div>
    </div>

        <!-- JavaScript 部分 -->
        <script type="text/javascript">
            $(function () {
                formatterwarning();
                hiddenOperate();
                if (${(zwave.status)!"-1"} == 0){
                    $("#operate").text("<@spring.message code="label.open"/>");
                }else{
                    $("#operate").text("<@spring.message code="label.close"/>");
                }
            });

            function formatterwarning() {
                if ($("#zwavewarningstatus").text() != "NONE") {
                    var strs = [];
                    var json = JSON.parse($("#zwavewarningstatus").text());
                    for (var i = 0; i < json.length; i++) {
                        strs[i] = formatter_zwavewarning($("#zwavedevicetype").text(), json[i]);
                    }
                }
                // alert(strs);
                $("#zwavewarningstatus").text(strs);
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
                var toStatus;
                if (${(zwave.status)!"-1"} ==0){//目前是关
                    toStatus = 255;
                }else{//目前不为关
                    toStatus = 0;
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
        </script>
<#include "/_foot0.ftl"/>