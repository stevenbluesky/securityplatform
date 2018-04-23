<#include "../_head0.ftl"/>
<#include "../_head1.ftl"/>

${(emp.name)!}
<hr>
角色列表
<hr>
<#list roleList as role>
        <input type="checkbox" value="${(role.roleid)!}" /><span style="margin-right: 40px;" />${(role.name)!}</span>
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
        $("div.checked>input").each(function (i,value) {
            checks.push(value.value);
        });
        // alert(checks);
        changeRole(checks);
    }

    function changeRole(checks){
        //异步更新
        $.ajax({
            type: 'post',
            url: 'changeRoles',
            contentType: 'application/json',
            traditional: true,
            data: "{\"id\":\"" + ${(emp.employeeid)!} + "\",\"list\":" + JSON.stringify(checks) + "}",
            success: function (data) {//返回json结果
                alert("<@spring.message code='label.updatesuccess'/>");
                window.location.href = "employeeList";
            },
            error: function () {// 请求失败处理函数
                alert("<@spring.message code='label.updatefailed'/>");
            }

        });
    }
</script>
<#include "../_foot1.ftl"/>
<#include "../_foot0.ftl"/>