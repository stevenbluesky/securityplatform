<#include "../_head0.ftl"/>
<#include "../_head1.ftl"/>
<form class="form-inline" id="searchForm" method="POST">
    <div class="text-center"><h1><@spring.message code="label.devicelist"/></h1></div>
    <hr>
    <div class="row">
        <div class="form-group col-md-4" align="right">
            <div>
                <b><@spring.message code="label.devicename"/></b>
                <input type="text" class="form-control" id="searchname" name="name"
                       placeholder=<@spring.message code="label.devicename"/>>
            </div>
        </div>
        <div class="form-group col-md-4" align="right">
            <div>
                <b><@spring.message code="label.city"/></b>
                <input type="text" class="form-control" id="searchcityname" name="cityname"
                       placeholder=<@spring.message code="label.city"/>>
            </div>
        </div>

        <div class="form-group col-md-4" align="right">
            <div>
                <b><@spring.message code="label.citycode"/></b>
                <input type="text" class="form-control" id="searchcitycode" name="citycode"
                       placeholder=<@spring.message code="label.citycode"/>>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="form-group col-md-4" align="right">
            <div>
                <b><@spring.message code="label.customer"/></b>
                <input type="text" class="form-control" id="searchcustomer" name="customer"
                       placeholder=<@spring.message code="label.customer"/>>
            </div>
        </div>
        <div class="form-group col-md-4" align="right">
            <div>
                <b><@spring.message code="label.serviceprovider"/></b>
                <input type="text" class="form-control" id="searchserviceprovider" name="serviceprovider"
                       placeholder=<@spring.message code="label.serviceprovider"/>>
            </div>
        </div>
        <div class="form-group col-md-4" align="right">
            <div>
                <b><@spring.message code="label.installerorg"/></b>
                <input type="text" class="form-control" id="searchinstallerorg" name="installerorg"
                       placeholder=<@spring.message code="label.installerorg"/>>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="form-group col-md-4" align="right">
            <div>
                <b><@spring.message code="label.installer"/></b>
                <input type="text" class="form-control" id="searchinstaller" name="installer"
                       placeholder=<@spring.message code="label.installer"/>>
            </div>
        </div>
        <div class="form-group col-md-4" align="right">
            <div>
                <b><@spring.message code="label.gatewayID"/></b>
                <input type="text" class="form-control" id="searchgatewayid" name="deviceid"
                       placeholder=<@spring.message code="label.gatewayID"/>>
            </div>
        </div>
        <div class="form-group col-md-4" align="right">
            <button type="button" id="searchsubmit" class="btn btn-default"
                    style="width:28%;"><@spring.message code="label.search"/></button>
        </div>
    </div>
</form>
          <hr>

          
<table id="table" data-toggle="table">
    <thead>
    <tr>
        <th data-field=""></th>
        <th data-field="zwavedeviceid" data-visible="false">ID</th>
        <th data-field="name"><@spring.message code="label.dname"/></th>
        <th data-field="devicetype"
            data-formatter='formatter_devicetype'><@spring.message code="label.devicetype"/></th>
        <th data-field="warningstatuses"
            data-formatter='formatter_warnigstatuses'><@spring.message code="label.alarmstatus"/></th>
        <th data-field="status" data-formatter='formatter_devicestatus'><@spring.message code="label.status"/></th>
        <th data-field="battery"><@spring.message code="label.energy"/></th>
        <th data-field="city"><@spring.message code="label.area"/></th>
        <th data-field="organizationname"><@spring.message code="label.serviceprovider"/></th>
        <th data-field="installerorgname"><@spring.message code="label.installerorg"/></th>
        <th data-field="installername"><@spring.message code="label.installer"/></th>
        <th data-field="username"><@spring.message code="label.user"/></th>
    </tr>
    </thead>
</table>
      </div>
        <div class="col-md-1"></div>
    </div>
    <script type="text/javascript">
        $('#table').bootstrapTable({
            url: 'deviceJsonList',
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
                    name:$("#searchname").val(),
                    cityname:$("#searchcityname").val(),
                    citycode:$("#searchcitycode").val(),
                    customer:$("#searchcustomer").val(),
                    serviceprovider:$("#searchserviceprovider").val(),
                    installerorg:$("#searchinstallerorg").val(),
                    installer:$("#searchinstaller").val(),
                    deviceid:$("#searchgatewayid").val(),
                    rows: params.limit,                         //页面大小
                    page: (params.offset / params.limit) + 1,   //页码
                    sort: params.sort,      //排序列名
                    sortOrder: params.order //排位命令（desc，asc）
                };
                return temp;
            },
            columns: [{
                checkbox: true,
                visible: true
            }],
            onLoadSuccess: function () {
            },
            onLoadError: function () {
                // alert(lan.loaderror);
            },
            onDblClickRow: function (row, $element) {
                //console.log(value);
                //console.log(row);
                //console.log(index);
                window.location.href = 'deviceDetail?zwavedeviceid=' + row.zwavedeviceid;
            }
        });

        //当点击搜索按钮后，表格刷新并跳到第一页
        $("#searchsubmit").click(function(){
            $("#table").bootstrapTable("refreshOptions",{pageNumber:1})
        });
    </script>

<#include "../_foot1.ftl"/>
<#include "../_foot0.ftl"/>