<!-- 网关详情 -->
<#import "/spring.ftl" as spring />
<#include "../_head0.ftl"/>
<div class="row-horizontal">
    <div class="col-md-1"></div>

    <div class="col-md-10">
        <div class="text-center"><h2><@spring.message code="label.gatewaydetail"/></h2></div>
        <div class="text-left"><h4><@spring.message code="label.gatewayinfo"/></h4></div>
        <hr>
        <div class="row">
            <div class="col-md-3">
                <p><@spring.message code="label.dname"/></p>
                <p><@spring.message code="label.gatewayID"/></p>
                <p><@spring.message code="label.businessstatus"/></p>
                <p><@spring.message code="label.user"/></p>
                <p><@spring.message code="label.installtime"/></p>
            </div>

            <div class="col-md-3">
                <p><#if (gwd.name)??>${gwd.name}<#else><@spring.message code="label.none"/></#if></p>
                <p><#if (gwd.deviceid)??>${gwd.deviceid}<#else><@spring.message code="label.none"/></#if></p>
                <p><#if (gwd.businessstatus)?exists&&gwd.businessstatus==1><@spring.message code="label.online"/><#else><@spring.message code="label.unknown"/></#if></p>
                <p><#if (gwd.customer)??>${gwd.customer}<#else><@spring.message code="label.none"/></#if></p>
                <p><#if (gwd.createtime)??>${gwd.createtime?string('yyyy-MM-dd')}<#else><@spring.message code="label.none"/></#if></p>

            </div>
            <div class="col-md-3">
                <p><@spring.message code="label.model"/></p>
                <p><@spring.message code="label.firmwareversion"/></p>
                <p><@spring.message code="label.devicestatus"/></p>
                <p><@spring.message code="label.installer"/></p>
            </div>
            <div class="col-md-3">
                <p><#if (gwd.model)??>${gwd.model}<#else><@spring.message code="label.none"/></#if></p>
                <p><#if (gwd.firmwareversion)??>${gwd.firmwareversion}<#else><@spring.message code="label.none"/></#if></p>
                <p><#if (gwd.status)?exists && gwd.status==1><@spring.message code="label.online"/><#elseif (gwd.status)?exists && gwd.status==0><@spring.message code="label.offline"/><#else><@spring.message code="label.unknown"/></#if></p>
                <p><#if (gwd.installer)??>${gwd.installer}<#else><@spring.message code="label.none"/></#if></p>
            </div>
        </div>
        <br>
        <div class="text-left"><h4><@spring.message code="label.phonecardinfo"/></h4></div>
        <hr>
        <div class="row">
            <div class="col-md-3">
                <p><@spring.message code="label.serialnumber"/></p>
                <p><@spring.message code="label.status"/></p>
                <p><@spring.message code="label.firmwareversion"/></p>
                <p>First programmed On</p>
                <p>Ordering Date</p>
            </div>
            <div class="col-md-3">
                <p><#if (gwd.phonecardserialnumber)??>${gwd.phonecardserialnumber}<#else><@spring.message code="label.none"/></#if></p>
                <p><#if (gwd.phonecardstatus)?exists&&gwd.phonecardstatus==1><@spring.message code="label.online"/><#else><@spring.message code="label.none"/></#if></p>
                <p><#if (gwd.phonecardfirmwareversion)??>${gwd.phonecardfirmwareversion}<#else><@spring.message code="label.none"/></#if></p>
                <p><#if (gwd.firstprogrammedon)??>${gwd.firstprogrammedon?string('yyyy-MM-dd')}<#else><@spring.message code="label.none"/></#if></p>
                <p><#if (gwd.orderingdate)??>${gwd.orderingdate?string('yyyy-MM-dd')}<#else><@spring.message code="label.none"/></#if></p>
            </div>
            <div class="col-md-3">
                <p><@spring.message code="label.model"/></p>
                <p>Rate Plan</p>
                <p>Activation Date</p>
                <p>Last Programmed On</p>
                <p>Expire Date</p>
            </div>
            <div class="col-md-3">
                <p><#if (gwd.phonecardmodel)??>${gwd.phonecardmodel}<#else><@spring.message code="label.none"/></#if></p>
                <p><#if (gwd.rateplan)??>${gwd.rateplan}<#else><@spring.message code="label.none"/></#if></p>
                <p><#if (gwd.activationdate)??>${gwd.activationdate?string('yyyy-MM-dd')}<#else><@spring.message code="label.none"/></#if></p>
                <p><#if (gwd.lastprogrammedon)??>${gwd.lastprogrammedon?string('yyyy-MM-dd')}<#else><@spring.message code="label.none"/></#if></p>
                <p><#if (gwd.expiredate)??>${gwd.expiredate?string('yyyy-MM-dd')}<#else><@spring.message code="label.none"/></#if></p>
            </div>
        </div>
        <div class="text-left"><h4><@spring.message code="label.associatedequipment"/></h4></div>
        <hr>
        <table id="table0" class="table table-hover table-bordered">
            <thead>
            <tr>
                <th><@spring.message code="label.dname"/></th>
                <th data-formatter='formatter_devicetype'><@spring.message code="label.devicetype"/></th>
                <th data-formatter='formatter_warnigstatuses'><@spring.message code="label.alarmstatus"/></th>
                <th data-formatter='formatter_devicestatus'><@spring.message code="label.status"/></th>
                <th><@spring.message code="label.energy"/></th>
            </tr>
            </thead>
            <tbody id="J_template">
			<#if (gdd)??>
				<#list gdd as being>
                <tr id="item">
                    <td>${(being.name)!}</td>
                    <td>${(being.devicetype)!}</td>
                    <td>${(being.warningstatuses)!}</td>
                    <td>${(being.status)!}</td>
                    <td>${(being.battery)!}%</td>
                </tr>
                </#list>
            </#if>
            </tbody>
        </table>
    </div>
    <div class="col-md-1"></div>
</div>

<!-- JavaScript 部分 -->
<script type="text/javascript">
</script>
<#include "../_foot0.ftl"/>