<!-- 电话卡详情页面 -->
<#import "/spring.ftl" as spring />
<#include "../_head0.ftl"/>
<div class="row-horizontal" xmlns="http://www.w3.org/1999/html">
    <div class="col-md-1"></div>
    <div class="col-md-10">

<div class="text-center"><h1><@spring.message code='label.phonecarddetail'/></h1></div>
<hr>
        <div align="center">
            <div  class="form-group">
                <label for="name"  class="col-sm-6 control-label"><@spring.message code='label.serialnumber'/></label>
               <#if (pnd.serialnumber)??>${pnd.serialnumber}<#else><@spring.message code="label.none"/></#if>
                <input type="hidden" name="" id="sesese" value="${pnd.phonecardid}"/>
            </div>
            <div  class="form-group">
                <label for="name"  class="col-sm-6 control-label"><@spring.message code='label.status'/></label>
                <label style="align-content: center">
                <#if (pnd.status)?exists && pnd.status==1><@spring.message code="label.normal"/><#elseif (pnd.status)?exists && pnd.status==2><@spring.message code="label.suspenced"/><#elseif (pnd.status)?exists && pnd.status==9><@spring.message code="label.delete"/><#else><@spring.message code="label.unknown"/></#if>
                <#if (pnd.serialnumber)??><input style="margin-left:10px;" class="btn btn-sm" value='<@spring.message code="label.synchronous"/>' type="button" onclick='updatePhonecardStatus("synchronous");'></button></#if>
               </label>
            </div>
            <div  class="form-group">
                <label for="code"  class="col-sm-6 control-label"><@spring.message code='label.model'/></label>
                <#if (pnd.model)??>${pnd.model}<#else><@spring.message code="label.none"/></#if>
            </div>
            <div  class="form-group">
                <label for="answer"  class="col-sm-6 control-label"><@spring.message code='label.firmwareversion'/></label>
                <#if (pnd.firmwareversion)??>${pnd.firmwareversion}<#else><@spring.message code="label.none"/></#if>
            </div>
            <div  class="form-group">
                <label for="name"  class="col-sm-6 control-label">Rate Plan</label>
                <#if (pnd.rateplan)??>${pnd.rateplan}<#else><@spring.message code="label.none"/></#if>
            </div>
            <div  class="form-group">
                <label for="code"  class="col-sm-6 control-label">Activation Date</label>
                <#if (pnd.activationdate)??>${pnd.activationdate}<#else><@spring.message code="label.none"/></#if>
            </div>


            <div  class="form-group">
                <label for="answer"  class="col-sm-6 control-label">First programmed On</label>
                <#if (pnd.firstprogrammedondate)??>${pnd.firstprogrammedondate}<#else><@spring.message code="label.none"/></#if>
            </div>
            <div  class="form-group">
                <label for="name"  class="col-sm-6 control-label">Last Programmed On</label>
                <#if (pnd.lastprogrammedondate)??>${pnd.lastprogrammedondate}<#else><@spring.message code="label.none"/></#if>
            </div>

            <div  class="form-group">
                <label for="code"  class="col-sm-6 control-label">Last Saved On</label>
                <#if (pnd.lastsavedondate)??>${pnd.lastsavedondate}<#else><@spring.message code="label.none"/></#if>
            </div>
            <div  class="form-group">
                <label for="answer"  class="col-sm-6 control-label">Ordering Date</label>
                <#if (pnd.orderingdate)??>${pnd.orderingdate}<#else><@spring.message code="label.none"/></#if>
            </div>

            <div  class="form-group">
                <label for="answer"  class="col-sm-6 control-label">Expiredate Date</label>
                <#if (pnd.expiredate)??>${pnd.expiredate}<#else><@spring.message code="label.none"/></#if>
            </div>
        </div>
        <script>
            //更新状态
            function updatePhonecardStatus(obj) {

                var trans = [];
                var confirmdelete = "";


                if (obj == "synchronous") {
                    if (!confirm("<@spring.message code='label.synchronousconfirm'/>")) {
                        return;
                    }
                }
                    var id = $("#sesese").val();
                    trans.push(${pnd.serialnumber});

                //异步更新
                $.ajax({
                    type: 'post',
                    url: 'update',
                    contentType: 'application/json',
                    traditional: true,
                    data: "{\"hope\":\"" + obj + "\",\"ids\":[" + id+"],\"confirmdelete\":\""+ confirmdelete+"\"}",
                    success: function (data) {//返回json结果
                        if ("1" == data["msg"]) {
                            alert("<@spring.message code='label.updatesuccess'/>");
                        }else if(data["msg"] == "-900"){
                            alert("<@spring.message code='label.needcorrectchar'/>");
                        }else if(data["msg"] == "-899"){}
                        else {
                            alert("<@spring.message code='label.updatefailed'/>");
                        }
                        $('#table').bootstrapTable('refresh');
                    },
                    error: function (data) {// 请求失败处理函数
                        alert("<@spring.message code='label.updatefailed'/>");
                        $('#table').bootstrapTable('refresh');
                    }
                });
            }

        </script>
<#include "../_foot0.ftl"/>