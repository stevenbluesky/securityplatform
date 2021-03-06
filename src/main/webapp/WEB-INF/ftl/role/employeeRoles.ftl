<#include "../_head0.ftl"/>
<#include "../_head1.ftl"/>

<@spring.message code="label.EmployeeRole"/>
<hr>

<#list roleList as role>
    <#if role_index== 0 || (role_index)%3 == 0>
    <div class="row" style="text-align: left; margin-top: 20px;">
    </#if>
        <div class="col-md-4">
            <span style="margin-right: 10px;"><input id="role_${(role.roleid)!?c}" type="checkbox" value="${(role.roleid)!?c}" /></span><span style="margin-right: 40px;" />${(role.name)!}</span>
        </div>
    <#if (role_index+1)%3 == 0 || !role_has_next>
    </div>
    </#if>
</#list>

<br>
<br>
<input class="btn btn-default" value="<@spring.message code="label.toggle"/>" type="button" style="margin-right: 30px;" onclick="$('input').iCheck('toggle');" />
<input class="btn btn-default" value="<@spring.message code="label.clear"/>" type="button" style="margin-right: 30px;" onclick="$('input').iCheck('uncheck');" />
<input class="btn btn-default" value="<@spring.message code="label.save"/>" type="button" onclick="submit0()">
<script src="https://cdn.bootcss.com/iCheck/1.0.2/icheck.min.js"></script>
<link href="https://cdn.bootcss.com/iCheck/1.0.2/skins/square/blue.css" rel="stylesheet">
<script type="text/javascript">

    //基础使用方法
    $('input').iCheck({
        checkboxClass: 'icheckbox_square-blue',
        radioClass: 'iradio_square-blue',
        increaseArea: '20%', // optional
        labelHover: true,
    });
    function submit0() {
        var employeeids = [];
        <#if employeeids??>
            <#list employeeids as item>
                 employeeids.push("${item}");
            </#list>
        </#if>
        var checks = [];
        $("div.checked>input").each(function (i,value) {
            checks.push(value.value);
        });
        changeRole(employeeids,checks);
    }

    function addRole(checks) {
        if (!"${(cemp.name)!}") {
            alert("no name");
        }
    }

    function changeRole(employeeids,checks){
        //异步更新
        $.ajax({
            type: 'post',
            url: 'modifyEmployeeRoles',
            contentType: 'application/json',
            traditional: true,
            data: "{\"employeeids\":" + JSON.stringify(employeeids) + ",\"list\":" + JSON.stringify(checks) + "}",
            success: function (data) {//返回json结果
                if(data.msg==="needcode"){
                    alert("<@spring.message code='label.needcode'/>")
                }else {
                    alert("<@spring.message code='label.updatesuccess'/>");
                }
                //window.location.href = "../employee/employeeList";
                parent.location.href=parent.location.href;
            },
            error: function () {// 请求失败处理函数
                alert("<@spring.message code='label.updatefailed'/>");
            }

        });
    }
</script>
<#include "../_foot1.ftl"/>
<#include "../_foot0.ftl"/>