<#include "../_head0.ftl"/>
<#include "../_head1.ftl"/>
<div class="">
   <form class="form-horizontal" action="" method="POST">
       <div class="text-center"><h1><@spring.message code='label.registendusers'/></h1></div>
       <hr>
       <div class="form-group">
           <div class="col-md-4" align="left">
               <label for="searchinstallerorg" class="col-md-5 control-label"><@spring.message code='label.username'/></label>
               <div class="col-md-7">
                   <input type="text" class="form-control" id="searchName" name="searchName"
                          placeholder="<@spring.message code='label.username'/>">
               </div>
           </div>
           <div class="col-md-4" align="right">
               <label for="searchSerialnumber" class="col-md-5 control-label"><@spring.message code='label.apploginemail'/></label>
               <div class="col-md-7">
                   <input type="text" class="form-control" id="searchPhonenumber" name="searchPhonenumber"
                          placeholder="<@spring.message code='label.apploginemail'/>">
               </div>
           </div>
           <div class="col-md-4" align="right">
               <label for="searchAppAccount" class="col-md-5 control-label"><@spring.message code='label.gatewayID'/></label>
               <div class="col-md-7">
                   <input type="text" class="form-control" id="searchGatewayid" name="searchGatewayid"
                          placeholder="<@spring.message code='label.gatewayID'/>">
               </div>
           </div>
       </div>

       <div class="form-group">
           <div class="col-md-4" align="left">
               <label for="searchinstallerorg" class="col-md-5 control-label"><@spring.message code='label.starttime'/></label>
               <div class="col-md-7">

                       <div class="col-sm-12 input-group date form_datetime">
                           <input class="form-control" size="16" id="searchStarttime" name="searchStarttime" type="text" readonly placeholder="<@spring.message code='label.starttime'/>" >
                           <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                           <span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
                       </div>
                   </div>
           </div>
           <div class="col-md-4" align="right">
               <label for="searchinstallerorg" class="col-md-5 control-label"><@spring.message code='label.endtime'/></label>
               <div class="col-md-7">
                       <div class="col-md-12 input-group date form_datetime">
                           <input class="form-control" size="16" id="searchEndtime" name="searchEndtime" type="text" readonly placeholder="<@spring.message code='label.endtime'/>" >
                           <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                           <span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
                       </div>
               </div>
           </div>
           <div class="col-md-4" align="right">
               <div class="col-md-5"></div>
               <div class="col-md-7">
                   <button type="button" id="searchbtn" class="btn btn-default"
                           style="width:100%;"><@spring.message code="label.search"/></button>
               </div>
           </div>
       </div>
   </form>
</div>
<button style="float: right;" type="button" id='export' class='btn btn-default' onclick='exportData()'><@spring.message code='label.export'/></button>
<table id="table" data-toggle="table">
    <thead>
    <tr>
        <th data-field=""></th>
        <th data-field="phoneuserid" data-visible="false">ID</th>
        <th data-field="name" class="text-center col-md-2"><@spring.message code='label.username'/></th>
        <th data-field="phonenumber" class="text-center col-md-3"><@spring.message code='label.apploginemail'/></th>
        <th data-field="createtime" data-formatter="formatter_date" class="text-center col-md-2"><@spring.message code='label.registtime'/></th>
        <th data-field="deviceid" class="text-center col-md-5"><@spring.message code='label.gatewayID'/></th>
    </tr>
    </thead>
</table>
      <#--</div>
        <div class="col-md-1"></div>
    </div>-->
    <script type="text/javascript">
        $('#table').bootstrapTable({
            url: 'registuserJsonList',
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
            pageList: [10, 25, 50, 100,200,500,1000],        //可供选择的每页的行数（*）
            search: false,                      //是否显示表格搜索
            strictSearch: false,
            showColumns: false,                  //是否显示所有的列（选择显示的列）
            showRefresh: false,                  //是否显示刷新按钮
            minimumCountColumns: 2,             //最少允许的列数
            clickToSelect: true,                //是否启用点击选中行
            //height: 500,                      //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
            uniqueId: "userid",                     //每一行的唯一标识，一般为主键列
            showToggle: false,                   //是否显示详细视图和列表视图的切换按钮
            cardView: false,                    //是否显示详细视图
            detailView: false,                  //是否显示父子表
            //得到查询的参数
            queryParams: function (params) {
                //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
                var temp = {
                    searchName:$("#searchName").val(),
                    searchPhonenumber:$("#searchPhonenumber").val(),
                    searchStarttime:$("#searchStarttime").val(),
                    searchEndtime:$("#searchEndtime").val(),
                    searchGatewayid:$("#searchGatewayid").val(),
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
            },
            onDblClickRow: function (row, $element) {
                var id = row.userid;
                /*$("#iframeDetail").attr("src", 'queryUserInfo?userid='+id);
                $('#myModal').modal('show');*/
            }
        });

        //当点击搜索按钮后，表格刷新并跳到第一页
        $("#searchbtn").click(function () {
            $("#table").bootstrapTable("refreshOptions", {pageNumber: 1})
        });

        // <#--获取复选框选中的列的id数组-->
        function getCheckedId() {
            var arr = $("#table").bootstrapTable('getSelections');
            var ids = [];
            arr.forEach(function (val, index, arr) {
                ids[index] = val.userid;
            });
            return ids;
        }
        function exportData() {
            var searchName = $("#searchName").val();
            var searchPhonenumber =  $("#searchPhonenumber").val();
            var searchStarttime =  $("#searchStarttime").val();
            var searchEndtime =  $("#searchEndtime").val();
            var searchGatewayid =  $("#searchGatewayid").val();
            window.open("exportregisteredendusersdata?searchName="+searchName+"&searchPhonenumber="+searchPhonenumber+"&searchStarttime="+searchStarttime+
                    "&searchEndtime="+searchEndtime+ "&searchGatewayid="+searchGatewayid);

        }
        function formatter_date(value, row, index) {
            return new Date(value).Format("yyyy-MM-dd hh:mm:ss");
        }
        <#--日期格式化器-->
        Date.prototype.Format = function (fmt) {
            var o = {
                "M+": this.getMonth() + 1, //月份
                "d+": this.getDate(), //日
                "h+": this.getHours(), //小时
                "m+": this.getMinutes(), //分
                "s+": this.getSeconds(), //秒
                "q+": Math.floor((this.getMonth() + 3) / 3), //季度
                "S": this.getMilliseconds() //毫秒
            };
            if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
            for (var k in o)
                if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
            return fmt;
        }
        $('.form_datetime').datetimepicker({
            //language:  'fr',
            format: 'yyyy-mm-dd',
            weekStart: 1,
            minView:'month',
            todayBtn:  1,
            autoclose: 1,
            todayHighlight: 1,
            startView: 2,
            forceParse: 0,
            showMeridian: 1
        });
    </script>
<#include "../modal.ftl"/>
<#include "../_foot1.ftl"/>
<#include "../_foot0.ftl"/>