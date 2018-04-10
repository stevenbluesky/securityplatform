<!-- 网关详情 -->
<#import "/spring.ftl" as spring />
<#include "../_head0.ftl"/>
<div class="row-horizontal">
    <div class="col-md-1"></div>

    <div class="col-md-10">
        <div class="text-center"><h2><@spring.message code="label.gatewaydetail"/></h2></div>
        <div class="text-left"><h4><@spring.message code="label.gatewayinfo"/></h4></div><hr>
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
        <div class="text-left"><h4><@spring.message code="label.phonecardinfo"/></h4></div><hr>
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
        <div class="text-left"><h4><@spring.message code="label.associatedequipment"/></h4></div><hr>
        <table id="table" data-toggle="table">
            <thead>
            <tr>
                <th data-field="zwavedeviceid">ID</th><!--复选框-->
                <th data-field="name"><@spring.message code="label.name"/></th>
                <th data-field="devicetype"><@spring.message code="label.devicetype"/></th>
                <th data-field="warningstatuses"><@spring.message code="label.alarmstatus"/></th>
                <th data-field="statuses"><@spring.message code="label.status"/></th>
                <th data-field="battery"><@spring.message code="label.energy"/></th>
            </tr>
            </thead>
        </table>
    </div>
    <div class="col-md-1"></div>
</div>

<!-- JavaScript 部分 -->
<script type="text/javascript">
    var id = getCookie("id");
            $('#table').bootstrapTable({
                url: "gatewayDeviceDetail?deviceid="+id+"",
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
                showColumns: true,                  //是否显示所有的列（选择显示的列）
                showRefresh: true,                  //是否显示刷新按钮
                minimumCountColumns: 2,             //最少允许的列数
                clickToSelect: true,                //是否启用点击选中行
                //height: 500,                      //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
                uniqueId: "id",                     //每一行的唯一标识，一般为主键列
                showToggle: false,                   //是否显示详细视图和列表视图的切换按钮
                cardView: false,                    //是否显示详细视图
                detailView: false,                  //是否显示父子表
                //得到查询的参数
                queryParams : function (params) {
                    //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
                    var temp = {
                        rows: params.limit,                         //页面大小
                        page: (params.offset / params.limit) + 1,   //页码
                        sort: params.sort,      //排序列名
                        sortOrder: params.order //排位命令（desc，asc）
                    };
                    return temp;
                },
                columns: [{
                    checkbox:true,
                    visible:true
                }],
                onLoadSuccess: function () {
                },
                onLoadError: function () {
                    alert('<@spring.message code="label.dataloaderror"/>');
                },
                onDblClickRow: function (row, $element) {
                    var id = row.ID;
                    EditViewById(id, 'view');
                }
            });
    function getCookie(name)
    {
        var arr = document.cookie.match(new RegExp("(^| )"+name+"=([^;]*)(;|$)"));
        if(arr !=null) return unescape(arr[2]); return null;
    }
    function formatter_gatewaystatus(value,row,index){
        if(value==0)
            return '<@spring.message code="label.offline"/>';
        if(value==1)
            return '<@spring.message code="label.online"/>';
    }
</script>
<#include "../_foot0.ftl"/>