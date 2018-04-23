<#include "../_head0.ftl"/>
<#include "../_head1.ftl"/>

${(role.name)!}
<hr>
权限列表
<hr>
<#list privilegeList as privilege>
    <#if privilege_index== 0 || (privilege_index)%3 == 0>
    <div class="row" style="text-align: left; margin-top: 20px;">
    </#if>
        <div  class="col-md-4">
        <input type="checkbox" value="${(privilege.privilegeid)!}"/><span style="margin-right: 80px;">${(privilege.code)!}</span>
        </div>
    <#if (privilege_index+1)%3 == 0 || !privilege_has_next>
    </div>
    </#if>
</#list>
<br>
<br>
<input class="btn btn-default" value="<@spring.message code="label.submit"/>" type="button" onclick="submit0()">
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
        let checks = [];
        $("div.checked>input").each(function (i, value) {
            checks.push(value.value);
        });
        // alert(checks);
        changeRole(checks);
    }

    function changeRole(checks) {
        //异步更新
        $.ajax({
            type: 'post',
            url: 'changeRolePrivilege',
            contentType: 'application/json',
            traditional: true,
            data: "{\"roleid\":\"" + ${(role.roleid)!} +"\",\"privileges\":" + JSON.stringify(checks) + "}",
            success: function (data) {//返回json结果
                alert("<@spring.message code='label.updatesuccess'/>");
                window.location.href = "roleList";
            },
            error: function () {// 请求失败处理函数
                alert("<@spring.message code='label.updatefailed'/>");
            }

        });
    }
</script>
<#include "../_foot1.ftl"/>
<#include "../_foot0.ftl"/>