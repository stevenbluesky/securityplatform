<!-- 网关详情 -->
<#import "/spring.ftl" as spring />
<#include "../_head0.ftl"/>
<div class="row-horizontal">
    <div class="col-md-1"></div>

    <div class="col-md-10">
        <form class="form-horizontal">
        <div class="text-center"><h1><@spring.message code='label.queryqrcode'/></h1></div>
        <hr>
        <div  class="form-group" >
            <label for="deviceid"  class="col-sm-2 control-label" ><@spring.message code='label.gatewayID'/>*</label>
            <div class="col-sm-7">
                <input type="text" class="form-control" id="deviceid" name="gatewayid" value="" placeholder="<@spring.message code='label.gatewayID'/>">
            </div>
            <div class="col-sm-3">
                <button style="float: center;width: 40%" type="button" id='queryGateway' class='btn btn-default' onclick='getGatewayQrcode()'><@spring.message code='label.query'/></button>
            </div>
        </div>
        </form>
    </div>
    <div class="text-center">
        <img id="qrcode" class="q_code" src="" />
    </div>
    <div class="col-md-1"></div>
</div>

<!-- JavaScript 部分 -->
<script type="text/javascript">
    function getGatewayQrcode() {
        var deviceid = $("#deviceid").val().trim();
        if(deviceid==""||deviceid==undefined||deviceid==null){
            return;
        }
        $.ajax({
            type: 'post',
            url: 'getGatewayQrcode',
            contentType: 'application/json',
            traditional: true,
            data:  deviceid ,
            success: function (data) {//返回json结果
                if (data!="") {
                    $("#qrcode").attr("src", "getGatewayQrcode1?qrstring=" + data);
                }else{
                    $("#qrcode").attr("src", "");
                    alert("Please check the gateway ID !");
                }
            },
            error: function (data) {// 请求失败处理函数
                alert("<@spring.message code='label.operatefailed'/>");
            }
        });
    }

</script>


<#include "../_foot0.ftl"/>