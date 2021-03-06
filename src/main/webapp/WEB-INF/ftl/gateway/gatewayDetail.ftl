<!-- 网关详情 -->
<#import "/spring.ftl" as spring />
<#include "../_head0.ftl"/>

<div class="row-horizontal">
            <div class="text-center"><h2><@spring.message code="label.gatewaydetail"/></h2></div>
    <hr>
            <div class="text-left"><h4><@spring.message code="label.gatewayinfo"/></h4></div>
    <div class="col-xs-12 col-sm-12 col-md-12">
        <div class="col-xs-3 col-sm-3 col-md-3" >
            <@spring.message code="label.dname"/>
        </div>
        <div class="col-xs-3 col-sm-3 col-md-3" >
        <#if (gwd.name)??>${gwd.name}<#else><@spring.message code="label.none"/></#if>
        </div>
        <div class="col-xs-3 col-sm-3 col-md-3" >
        <@spring.message code="label.model"/>
        </div>
        <div class="col-xs-3 col-sm-3 col-md-3" >
        <#if (gwd.model)??>${gwd.model}<#else><@spring.message code="label.none"/></#if>
        </div>
    </div>
            <div class="col-xs-12 col-sm-12 col-md-12">
                <div class="col-xs-3 col-sm-3 col-md-3" >
                <@spring.message code="label.gatewayID"/>
                </div>
                <div class="col-xs-3 col-sm-3 col-md-3" >
                <#if (gwd.deviceid)??>${gwd.deviceid}<#else><@spring.message code="label.none"/></#if>
                </div>
                <div class="col-xs-3 col-sm-3 col-md-3" >
                <@spring.message code="label.firmwareversion"/>
                </div>
                <div class="col-xs-3 col-sm-3 col-md-3" >
                <#if (gwd.firmwareversion)??>${gwd.firmwareversion}<#else><@spring.message code="label.none"/></#if>
                </div>
            </div>
            <div class="col-xs-12 col-sm-12 col-md-12">
                <div class="col-xs-3 col-sm-3 col-md-3" >
                <@spring.message code="label.user"/>
                </div>
                <div class="col-xs-3 col-sm-3 col-md-3" >
                <#if (gwd.customer)??>${gwd.customer}<#else><@spring.message code="label.none"/></#if>
                </div>
                <div class="col-xs-3 col-sm-3 col-md-3" >
                <@spring.message code="label.devicestatus"/>
                </div>
                <div class="col-xs-3 col-sm-3 col-md-3" >
                <#if (gwd.status)?exists && gwd.status==1><@spring.message code="label.online"/><#elseif (gwd.status)?exists && gwd.status==0><@spring.message code="label.offline"/><#else><@spring.message code="label.unknown"/></#if>
                </div>
            </div>
            <div class="col-xs-12 col-sm-12 col-md-12">
                <div class="col-xs-3 col-sm-3 col-md-3" >
                <@spring.message code="label.installtime"/>
                </div>
                <div class="col-xs-3 col-sm-3 col-md-3" >
                <#if (gwd.createtime)??>${gwd.createtime?string('yyyy-MM-dd')}<#else><@spring.message code="label.none"/></#if>
                </div>
                <div class="col-xs-3 col-sm-3 col-md-3" >
                <@spring.message code="label.InstallerPersonList"/>
                </div>
                <div class="col-xs-3 col-sm-3 col-md-3" >
                <#if (gwd.installer)??>${gwd.installer}<#else><@spring.message code="label.none"/></#if>
                </div>
            </div>
            <div class="text-left"><h7>&nbsp;</h7></div>
            <hr>
            <div class="text-left"><h4><@spring.message code="label.phonecardinfo"/></h4></div>

            <div class="col-xs-12 col-sm-12 col-md-12">
                <div class="col-xs-3 col-sm-3 col-md-3" >
                <@spring.message code="label.serialnumber"/>
                </div>
                <div class="col-xs-3 col-sm-3 col-md-3" >
                <#if (gwd.phonecardserialnumber)??>${gwd.phonecardserialnumber}<#else><@spring.message code="label.none"/></#if>
                </div>
                <div class="col-xs-3 col-sm-3 col-md-3" >
                <@spring.message code="label.model"/>
                </div>
                <div class="col-xs-3 col-sm-3 col-md-3" >
                <#if (gwd.phonecardmodel)??>${gwd.phonecardmodel}<#else><@spring.message code="label.none"/></#if>
                </div>
            </div>

            <div class="col-xs-12 col-sm-12 col-md-12">
                <div class="col-xs-3 col-sm-3 col-md-3" >
                <@spring.message code="label.status"/>
                </div>
                <div class="col-xs-3 col-sm-3 col-md-3" >
                    <span id="phonecardstatus"></span>
                <#if (gwd.phonecardstatus)?exists&&gwd.phonecardstatus==1>
                    <@shiro.hasPermission name="label.FreezeSIM">
                        <input style="margin-left: 20px;" class="btn btn-sm" value="<@spring.message code="label.freeze"/>"
                               type="button" onclick="togglePhonecardStatus('freeze')">
                    </@shiro.hasPermission>
                <#elseif (gwd.phonecardstatus)?exists>
                    <@shiro.hasPermission name="label.ActiveSIM">
                        <input style="margin-left: 20px;" class="btn btn-sm  btn-success"
                               value="<@spring.message code="label.unsuspence"/>" type="button"
                               onclick="togglePhonecardStatus('start')">
                    </@shiro.hasPermission>
                </#if>
                </div>
                <div class="col-xs-3 col-sm-3 col-md-3" >
                    Rate Plan
                </div>
                <div class="col-xs-3 col-sm-3 col-md-3" >
                <#if (gwd.rateplan)??>${gwd.rateplan}<#else><@spring.message code="label.none"/></#if>
                </div>
            </div>

            <div class="col-xs-12 col-sm-12 col-md-12">
                <div class="col-xs-3 col-sm-3 col-md-3" >
                <@spring.message code="label.firmwareversion"/>
                </div>
                <div class="col-xs-3 col-sm-3 col-md-3" >
                <#if (gwd.phonecardfirmwareversion)??>${gwd.phonecardfirmwareversion}<#else><@spring.message code="label.none"/></#if>
                </div>
                <div class="col-xs-3 col-sm-3 col-md-3" >
                    Activation Date
                </div>
                <div class="col-xs-3 col-sm-3 col-md-3" >
                <#if (gwd.activationdate)??>${gwd.activationdate?string('yyyy-MM-dd')}<#else><@spring.message code="label.none"/></#if>
                </div>
            </div>

            <div class="col-xs-12 col-sm-12 col-md-12">
                <div class="col-xs-3 col-sm-3 col-md-3" >
                    First programmed On
                </div>
                <div class="col-xs-3 col-sm-3 col-md-3" >
                <#if (gwd.firstprogrammedon)??>${gwd.firstprogrammedon?string('yyyy-MM-dd')}<#else><@spring.message code="label.none"/></#if>
                </div>
                <div class="col-xs-3 col-sm-3 col-md-3" >
                    Last Programmed On
                </div>
                <div class="col-xs-3 col-sm-3 col-md-3" >
                <#if (gwd.lastprogrammedon)??>${gwd.lastprogrammedon?string('yyyy-MM-dd')}<#else><@spring.message code="label.none"/></#if>
                </div>
            </div>

            <div class="col-xs-12 col-sm-12 col-md-12">
                <div class="col-xs-3 col-sm-3 col-md-3" >
                    Ordering Date
                </div>
                <div class="col-xs-3 col-sm-3 col-md-3" >
                <#if (gwd.orderingdate)??>${gwd.orderingdate?string('yyyy-MM-dd')}<#else><@spring.message code="label.none"/></#if>
                </div>
                <div class="col-xs-3 col-sm-3 col-md-3" >
                    Expire Date
                </div>
                <div class="col-xs-3 col-sm-3 col-md-3" >
                <#if (gwd.expiredate)??>${gwd.expiredate?string('yyyy-MM-dd')}<#else><@spring.message code="label.none"/></#if>
                </div>
            </div>

    <div class="text-left">&nbsp;</div>
    <hr>
    <div class="text-left"><h4><@spring.message code="label.associatedequipment"/></h4></div>
    <div class="col-md-12">


        <table id="table" data-toggle="table">
            <thead>
            <tr>
                <th data-field="zwavedeviceid">ID</th><!--复选框-->
                <th data-field="name" class="text-center"><@spring.message code="label.pname"/></th>
                <th data-field="devicetype" class="text-center"
                    data-formatter='formatter_devicetype'><@spring.message code="label.devicetype"/></th>
                <th data-field="warningstatuses" class="text-center"
                    data-formatter='formatter_warnigstatuses'><@spring.message code="label.alarmstatus"/></th>
                <th data-field="status" class="text-center"
                    data-formatter='formatter_devicestatus'><@spring.message code="label.status"/></th>
                <th data-field="battery" class="text-center"><@spring.message code="label.energy"/></th>
                <th data-field="area" class="text-center"
                    data-formatter='formatter_area'><@spring.message code="label.areanumber"/></th>
            </tr>
            </thead>
        </table>
    </div>
    <!--<div class="col-md-1"></div> -->
</div>

<!-- JavaScript 部分 -->
<script type="text/javascript">

        var url = document.location.toString();
        var arrUrl = url.split("=");
        var para = arrUrl[1];

    if (para != null) {
        $('#table').bootstrapTable({
            url: "gatewayDeviceDetail?deviceid=" + para,
            method: 'GET',                      //请求方式（*）
            //toolbar: '#toolbar',              //工具按钮用哪个容器
            striped: true,                      //是否显示行间隔色
            cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
            pagination: true,                   //是否显示分页（*）
            sortable: true,                     //是否启用排序
            sortOrder: "asc",                   //排序方式
            sidePagination: "server",           //分页方式：client客户端分页，server服务端分页（*）
            pageNumber: 1,                      //初始化加载第一页，默认第一页,并记录
            pageSize: '10',                     //每页的记录行数（*）
            pageList: [10, 25, 50, 100],        //可供选择的每页的行数（*）
            search: false,                      //是否显示表格搜索
            strictSearch: false,
            showColumns: false,                  //是否显示所有的列（选择显示的列）
            showRefresh: false,                  //是否显示刷新按钮
            minimumCountColumns: 2,             //最少允许的列数
            clickToSelect: true,                //是否启用点击选中行
            //height: 500,                      //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
            uniqueId: "zwavedeviceid",                     //每一行的唯一标识，一般为主键列
            showToggle: false,                   //是否显示详细视图和列表视图的切换按钮
            cardView: false,                    //是否显示详细视图
            detailView: false,                  //是否显示父子表
            //得到查询的参数
            queryParams: function (params) {
                //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
                var temp = {
                    rows: params.limit,                         //页面大小
                    page: (params.offset / params.limit) + 1,   //页码
                    sort: params.zwavedeviceid,      //排序列名
                    sortOrder: params.asc //排位命令（desc，asc）
                };
                return temp;
            },
            columns: [{
                checkbox: false,
                visible: false
            }],
            onLoadSuccess: function () {
            },
            onLoadError: function () {
                //alert('<@spring.message code="label.dataloaderror"/>');
            },
            onDblClickRow: function (row, $element) {
                var id = row.zwavedeviceid;
                //window.location.href = "../device/deviceDetail?zwavedeviceid="+id;
                $("#2iframeDetail").attr("src", '../device/deviceDetail?zwavedeviceid='+id);
                $('#2myModal').modal('show');
            }
        });
    }

/*    function getCookie(name) {
        var arr,reg=new RegExp("(^| )"+name+"=([^;]*)(;|$)");
        if(arr=document.cookie.match(reg))
            return unescape(arr[2]);
        else
            return null;
    }*/

    function formatter_gatewaystatus(value, row, index) {
        if (value == 0)
            return '<@spring.message code="label.offline"/>';
        if (value == 1)
            return '<@spring.message code="label.online"/>';
    }
    function formatter_area(value,row,index) {
        if(value>1000){
            return "1-"+(value-1000);
        }
        return value;
    }
    function togglePhonecardStatus(hope) {
        let ids = [${(gwd.phonecardid)!}];
        //异步更新
        $.ajax({
            type: 'post',
            url: '../phonecard/update',
            contentType: 'application/json',
            traditional: true,
            data: "{\"hope\":\"" + hope + "\",\"ids\":" + JSON.stringify(ids) + "}",
            success: function (data) {//返回json结果
                if ("1" == data["msg"]) {
                    alert("<@spring.message code='label.updatesuccess'/>");
                    window.location.reload();
                } else {
                    alert("<@spring.message code='label.updatefailed'/>" /*+ "(" + data['msg'] + ")"*/);
                }
            },
            error: function () {// 请求失败处理函数
                alert("<@spring.message code='label.updatefailed'/>");
            }
        });
    }

    function formatterwarning() {
        let phonecardstatus = ${(gwd.phonecardstatus)!"-1"};
        var formatterStatus = formatter_status(phonecardstatus, null, null);
        $("#phonecardstatus").text(formatterStatus);
    }

    $(function () {
        formatterwarning();
    });
</script>
<!-- 模态框（Modal） -->
<div class="modal fade" id="2myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog" style="width:95%;height:100%;">
        <div class="modal-content">
        <#--引入详情界面-->
            <div class="modal-body">
                <div class="col-md-12" style="height:540px;width:100%">
                    <iframe id="2iframeDetail" class="embed-responsive-item" frameborder="0" src=""
                            style="height:100%;width:100%;"></iframe>
                </div>
            </div>

            <div class="modal-footer">
                <button type="button" class="btn btn-default"
                        data-dismiss="modal"><@spring.message code="label.close"/></button>
            </div>
        </div>
    </div>
</div>

<#include "../_foot0.ftl"/>