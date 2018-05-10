<#include "../_head0.ftl"/>
<#include "../_head1.ftl"/>
<form class="form-horizontal" id="searchForm" method="POST">
    <div class="text-center"><h1><@spring.message code="label.devicelist"/></h1></div>
    <hr>

    <div class="form-group col-md-4" align="right">
        <label for="searchname" class="col-md-5 control-label"><@spring.message code="label.devicename"/></label>
        <div class="col-md-7">
            <input class="form-control" type="text" id="searchname" name="name"
                   placeholder=<@spring.message code="label.devicename"/>>
        </div>
    </div>

    <div class="form-group col-md-4" align="right">
        <label for="searchcityname" class="col-md-5 control-label"><@spring.message code="label.city"/></label>
        <div class="col-md-7">
            <input type="text" class="form-control" id="searchcityname" name="cityname"
                   placeholder=<@spring.message code="label.city"/>>
        </div>
    </div>

    <div class="form-group col-md-4" align="right">
        <label for="searchcitycode" class="col-md-5 control-label"><@spring.message code="label.citycode"/></label>
        <div class="col-md-7">
            <input type="text" class="form-control" id="searchcitycode" name="citycode"
                   placeholder=<@spring.message code="label.citycode"/>>
        </div>
    </div>

    <div class="form-group col-md-4" align="right">
        <label for="searchcustomer" class="col-md-5 control-label"><@spring.message code="label.customer"/></label>
        <div class="col-md-7">
            <input type="text" class="form-control" id="searchcustomer" name="customer"
                   placeholder=<@spring.message code="label.customer"/>>
        </div>
    </div>

    <div class="form-group col-md-4" align="right">
        <label for="searchserviceprovider"
               class="col-md-5 control-label"><@spring.message code="label.serviceprovider"/></label>
        <div class="col-md-7">
            <input type="text" class="form-control" id="searchserviceprovider" name="serviceprovider"
                   placeholder=<@spring.message code="label.serviceprovider"/>>
        </div>
    </div>

    <div class="form-group col-md-4" align="right">
        <label for="searchinstallerorg"
               class="col-md-5 control-label"><@spring.message code="label.installerorg"/></label>
        <div class="col-md-7">
            <input type="text" class="form-control" id="searchinstallerorg" name="installerorg"
                   placeholder=<@spring.message code="label.installerorg"/>>
        </div>
    </div>

    <div class="form-group col-md-4" align="right">
        <label for="searchinstaller" class="col-md-5 control-label"><@spring.message code="label.installer"/></label>
        <div class="col-md-7">
            <input type="text" class="form-control" id="searchinstaller" name="installer"
                   placeholder=<@spring.message code="label.installer"/>>
        </div>
    </div>

    <div class="form-group col-md-4" align="right">
        <label for="searchinstallerorg" class="col-md-5 control-label"><@spring.message code="label.gatewayID"/></label>
        <div class="col-md-7">
            <input type="text" class="form-control" id="searchgatewayid" name="deviceid"
                   placeholder=<@spring.message code="label.gatewayID"/>>
        </div>
    </div>

    <div class="form-group col-md-4" align="right">
        <div class="col-md-5"></div>
        <div class="col-md-7">
            <button type="button" id="searchsubmit" class="btn btn-default"
                    style="width:100%;"><@spring.message code="label.search"/></button>
        </div>
    </div>
</form>
          <hr>

          
<table id="table" data-toggle="table">
    <thead>
    <tr>
        <th data-field=""></th>
        <th data-field="zwavedeviceid" data-visible="false">ID</th>
        <th data-field="name" class="text-center" data-id="dname"><@spring.message code="label.dname"/></th>
        <th data-field="devicetype" class="text-center"
            data-formatter='formatter_devicetype'><@spring.message code="label.devicetype"/></th>
        <th data-field="warningstatuses" class="text-center"
            data-formatter='formatter_warnigstatuses'><@spring.message code="label.alarmstatus"/></th>
        <th data-field="status" class="text-center" data-formatter='formatter_devicestatus'><@spring.message code="label.status"/></th>
        <th data-field="battery" class="text-center"><@spring.message code="label.energy"/></th>
        <th data-field="city" class="text-center"><@spring.message code="label.area"/></th>
        <th data-field="organizationname" class="text-center"><@spring.message code="label.serviceprovider"/></th>
        <th data-field="installerorgname" class="text-center"><@spring.message code="label.installerorg"/></th>
        <th data-field="installername" class="text-center"><@spring.message code="label.installer"/></th>
        <th data-field="username" class="text-center"><@spring.message code="label.user"/></th>
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
                    searchname:$("#searchname").val(),
                    searchcityname:$("#searchcityname").val(),
                    searchcitycode:$("#searchcitycode").val(),
                    searchcustomer:$("#searchcustomer").val(),
                    searchserviceprovider:$("#searchserviceprovider").val(),
                    searchinstallerorg:$("#searchinstallerorg").val(),
                    searchinstaller:$("#searchinstaller").val(),
                    searchgatewayid:$("#searchgatewayid").val(),
                    rows: params.limit,                         //页面大小
                    page: (params.offset / params.limit) + 1,   //页码
                    sort: /*params.sort*/"",      //排序列名
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
                window.location.href = 'deviceDetail?zwavedeviceid=' + row.zwavedeviceid;
            }
        });
        $("#dname").click(function () {
           alert("别点，要脸");
        });
        //当点击搜索按钮后，表格刷新并跳到第一页
        $("#searchsubmit").click(function(){
            $("#table").bootstrapTable("refreshOptions",{pageNumber:1})
        });
    </script>

<#include "../_foot1.ftl"/>
<#include "../_foot0.ftl"/>