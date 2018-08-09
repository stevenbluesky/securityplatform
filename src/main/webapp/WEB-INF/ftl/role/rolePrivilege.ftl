<#include "../_head0.ftl"/>
<#include "../_head1.ftl"/>

<#if role??>
    <@spring.message code="label.rolename"/>: ${(role.name)!}
<#else >
    <@spring.message code="label.rolename"/><span style="color:red;">*</span>:<input id="rolename" name="rolename" />
</#if>
<hr>

<@spring.message code="label.RolePrivilege"/>
<hr>
<#list privilegeList as privilege>
    <#if privilege_index== 0 || (privilege_index)%2 == 0>
    <div class="row" style="text-align: left; margin-top: 20px;">
    </#if>
        <div class="col-md-6">
            <span style="margin-right: 10px;"><input id="privilege_${(privilege.privilegeid)!}" type="checkbox" value="${(privilege.privilegeid)!?c}"/></span>
                <span style="margin-right: 80px;">${(privilege.code)!}</span>
        </div>
    <#if (privilege_index+1)%2 == 0 || !privilege_has_next>
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
    $(function(){
        var oldPrivilege =[];
        //初始化将测试集包含的用例存在数组里面
       <#if oldPrivilege??>
           <#list oldPrivilege as item>
                 oldPrivilege.push("${item}");
           </#list>
       </#if>
        // alert(oldRole);
        for(var i in oldPrivilege) {
            $("#privilege_"+oldPrivilege[i]).iCheck('check');
        }
    });

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

    function addNew() {
        if (!"${(role.name)!}") {
            return "addRole";
        }
        return "changeRolePrivilege";
    }

    function putData(checks){
        if (!"${(role.name)!}") {
            let rolename = $("#rolename").val();
            return "{\"id\":\"" + ${(role.roleid)!"null"} +"\",\"list\":" + JSON.stringify(checks) + ",\"rolename\":" + JSON.stringify(rolename) + "}";
        }
        return "{\"id\":\"" + ${(role.roleid)!"null"} +"\",\"list\":" + JSON.stringify(checks) + "}";
    }

    function changeRole(checks) {
        if (!"${(role.name)!}" && !$("#rolename").val()) {
            alert(lan.notempty);
            return null;
        }
        //异步更新
        $.ajax({
            type: 'post',
            url: /*'changeRolePrivilege',*/addNew(),
            contentType: 'application/json',
            traditional: true,
            data: putData(checks),
            success: function (data) {//返回json结果
                if(data["msg"] == "1"){
                    alert("<@spring.message code='label.updatesuccess'/>");
                    //window.location.href = "roleList";
                    parent.location.href=parent.location.href;
                }else{
                    alert("<@spring.message code='label.updatefailed'/>"+data["msg"]);
                }
            },
            error: function () {// 请求失败处理函数
                alert("<@spring.message code='label.updatefailed'/>");
            }

        });
    }
</script>
<#include "../_foot1.ftl"/>
<#include "../_foot0.ftl"/>