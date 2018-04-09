<#include "../_head0.ftl"/>
<#include "../_head1.ftl"/>
<#import "/spring.ftl" as spring />
<form class="form-inline" id="searchForm" method="POST">
    <div class="text-center"><h1><@spring.message code="label.gatewaylist"/></h1></div><hr>
    <div class="row">
        <div class="form-group col-md-4" align="right">
            <div>
                <b><@spring.message code="label.devicename"/></b>
                <input type="text" class="form-control" id="searchname" name="name" placeholder=<@spring.message code="label.devicename"/>>
            </div>
        </div>
        <div class="form-group col-md-4" align="right">
            <div>
                <b><@spring.message code="label.city"/></b>
                <input type="text" class="form-control" id="searchcityname" name="cityname" placeholder=<@spring.message code="label.city"/>>
            </div>
        </div>

        <div class="form-group col-md-4" align="right">
            <div>
                <b><@spring.message code="label.citycode"/></b>
                <input type="text" class="form-control" id="searchcitycode" name="citycode" placeholder=<@spring.message code="label.citycode"/>>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="form-group col-md-4" align="right">
            <div>
                <b><@spring.message code="label.customer"/></b>
                <input type="text" class="form-control" id="searchcustomer" name="customer" placeholder=<@spring.message code="label.customer"/>>
            </div>
        </div>
        <div class="form-group col-md-4" align="right">
            <div>
                <b><@spring.message code="label.serviceprovider"/></b>
                <input type="text" class="form-control" id="searchserviceprovider" name="serviceprovider" placeholder=<@spring.message code="label.serviceprovider"/>>
            </div>
        </div>
        <div class="form-group col-md-4" align="right">
            <div>
                <b><@spring.message code="label.installerorg"/></b>
                <input type="text" class="form-control" id="searchinstallerorg" name="installerorg" placeholder=<@spring.message code="label.installerorg"/>>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="form-group col-md-4" align="right">
            <div>
                <b><@spring.message code="label.installer"/></b>
                <input type="text" class="form-control" id="searchinstaller" name="installer" placeholder=<@spring.message code="label.installer"/>>
            </div>
        </div>
        <div class="form-group col-md-4" align="right">
            <div>
                <b><@spring.message code="label.gatewayID"/></b>
                <input type="text" class="form-control" id="searchgatewayid" name="deviceid" placeholder=<@spring.message code="label.gatewayID"/>>
            </div>
        </div>
        <div class="form-group col-md-4" align="right">
            <button type="button" id="searchsubmit" class="btn btn-default" style="width:28%;"><@spring.message code="label.search"/></button>
        </div>
    </div>
</form>
<hr>
<#--新增，启用，停用按钮-->
<button style="float: right;"  type="button" id='stopGateway' class='btn btn-default' onclick='updateGatewayStatus("stop");'><@spring.message code='label.stop'/></button>
<button style="float: right;"  type="button" id='startGateway' class='btn btn-default' onclick='updateGatewayStatus("start");'><@spring.message code='label.start'/></button>
<button style="float: right;"  type="button" class="btn btn-default" onclick="window.location.href='typeGatewayInfo'"><@spring.message code="label.entering"/></button>

<table id="table" data-toggle="table">
    <thead>
    <tr>
        <th data-field="">复选框列</th>
        <th data-field="name"><@spring.message code="label.dname"/></th>
        <#--<th data-field=""><@spring.message code="label.alarmstatus"/></th>-->
        <th data-field="status" data-formatter="formatter_gatewaystatus"><@spring.message code="label.status"/></th>
        <th data-field="deviceid"><@spring.message code="label.gatewayID"/></th>
        <th data-field="cityname"><@spring.message code="label.area"/></th>
        <th data-field="serviceprovider"><@spring.message code="label.serviceprovider"/></th>
        <th data-field="installerorg"><@spring.message code="label.installerorg"/></th>
        <th data-field="installer"><@spring.message code="label.installer"/></th>
        <th data-field="customer"><@spring.message code="label.user"/></th>
        <#--<th data-field=""><@spring.message code="label.businessstatus"/></th>-->
	<#--<th data-field="operate" data-formatter="formatter_operate"><@spring.message code="label.operate"/></th>-->
    </tr>
    </thead>
</table>
</div>
<div class="col-md-1"></div>
</div>
<script type="text/javascript">
    $('#table').bootstrapTable({
        url: 'gatewayJsonList',
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
        search: false,                      //是否显示表格搜索*******
        strictSearch: false,
        showColumns: false,                  //是否显示所有的列（选择显示的列）******
        showRefresh: false,                  //是否显示刷新按钮*******
        minimumCountColumns: 2,             //最少允许的列数
        clickToSelect: true,                //是否启用点击选中行
        //height: 500,                      //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
        uniqueId: "deviceid",                     //每一行的唯一标识，一般为主键列
        showToggle: false,                   //是否显示详细视图和列表视图的切换按钮
        cardView: false,                    //是否显示详细视图
        detailView: false,                  //是否显示父子表
        //得到查询的参数
        queryParams : function (params) {
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
                sort: params.deviceid,      //排序列名
                sortOrder: params.asc //排位命令（desc，asc）
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
	<#--双击触发模态框展示-->
        onDblClickRow: function (row, $element) {
            var deid = row.deviceid;
            $('#myModal').modal('show');
            $("#iframeDetail").attr("src", 'gatewayDetail?deviceid='+deid);
        }
    });

    //当点击搜索按钮后，表格刷新并跳到第一页
    $("#searchsubmit").click(function(){
        $("#table").bootstrapTable("refreshOptions",{pageNumber:1})
        // $('#table').bootstrapTable('refresh');
    });

	<#--隐藏列-->
	<#--$('#table').bootstrapTable('hideColumn', 'id');-->
	<#--状态格式转换-->
    function formatter_gatewaystatus(value,row,index){
        if(value==0)
            return '<@spring.message code="label.offline"/>';
        if(value==1)
            return '<@spring.message code="label.online"/>';
    }
	<#--获取复选框选中的列的id数组-->
    function getCheckedId(){
        var tbodyObj = document.getElementById('table');
        var ids = [];
        $("table :checkbox").each(function(key,value){
            if($(value).prop('checked')){
                if(key!=0){
                    ids[key-1] = tbodyObj.rows[key].cells[3].innerHTML;
                }
            }
        })
        if(ids.length==0){
            return new Array("-1");
        }
        return ids;
    }
    function updateGatewayStatus(obj){
        var checkedIds = getCheckedId();
        var trans = [];
        if(checkedIds[0] == -1){
            alert("<@spring.message code="label.nochecked"/>");
            return ;
        }
        if(obj=="start"){
            if(!confirm("<@spring.message code='label.startconfirm'/>")){
                return ;
            }
        }
        if(obj=="stop"){
            if(!confirm("<@spring.message code='label.stopconfirm'/>")){
                return ;
            }
        }
        for(var index in checkedIds){
            trans.push(checkedIds[index]);
        }
        //异步更新     
        $.ajax({
            type:'post',
            url:'update',
            contentType:'application/json',
            traditional:true,
            data:"{\"hope\":\""+obj+"\",\"ids\":"+JSON.stringify(trans)+"}",
            success:function(data){//返回json结果
                alert("<@spring.message code='label.updatesuccess'/>");
                $('#table').bootstrapTable('refresh');
            },
            error : function() {// 请求失败处理函数
                alert("<@spring.message code='label.updatefailed'/>");
                $('#table').bootstrapTable('refresh');
            }

        });
    }
</script>
<!-- 模态框（Modal） -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" >
    <div class="modal-dialog" style="width:90%;height:90%;">
        <div class="modal-content">
            <div class="modal-header" style="height: 40px;width:100%">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">X</button>
                <h4 class="modal-title" id="myModalLabel"><@spring.message code="label.gatewaydetail"/></h4>
            </div>
		<#--引入网关详情界面-->
            <div class="modal-body" >
                <div  class="col-md-10"  style="height:550px;width:100%">
                    <iframe id="iframeDetail" class="embed-responsive-item" frameborder="0" src="gatewayDetail" style="height:100%;width:100%;"></iframe>
                </div>
            </div>

            <div class="col-md-1"></div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal"><@spring.message code="label.close"/></button>
            </div>
        </div>
    </div>
</div>

<#include "../_foot1.ftl"/>
<#include "../_foot0.ftl"/>