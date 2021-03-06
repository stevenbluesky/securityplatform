<#include "../_head0.ftl"/>
<#include "../_head1.ftl"/>
<div class="">
   <form class="form-horizontal" action="" method="POST">
       <div class="text-center"><h1><@spring.message code='label.UserList'/></h1></div>
       <hr>
       <div class="form-group">
           <div class="col-md-4" align="right">
               <label for="searchinstallerorg" class="col-md-5 control-label"><@spring.message code='label.aibaseid'/></label>
               <div class="col-md-7">
                   <input type="text" class="form-control" id="searchCode" name="searchCode"
                          placeholder="<@spring.message code='label.aibaseid'/>">
               </div>
           </div>
               <div class="col-md-4" align="right">
                   <label for="searchinstallerorg" class="col-md-5 control-label"><@spring.message code='label.name'/></label>
                   <div class="col-md-7">
                       <input type="text" class="form-control" id="searchName" name="searchName"
                              placeholder="<@spring.message code='label.name'/>">
                   </div>
               </div>

           <div class="col-md-4" align="right">
               <label for="searchGatewayid" class="col-md-5 control-label"><@spring.message code='label.gatewayID'/></label>
               <div class="col-md-7">
                   <input type="text" class="form-control" id="searchGatewayid" name="searchGatewayid"
                          placeholder="<@spring.message code='label.gatewayID'/>">
               </div>
           </div>
       </div>
       <div class="form-group">
           <div class="col-md-4" align="right">
               <label for="searchSerialnumber" class="col-md-5 control-label"><@spring.message code='label.serialnumber'/></label>
               <div class="col-md-7">
                   <input type="text" class="form-control" id="searchSerialnumber" name="searchSerialnumber"
                          placeholder="<@spring.message code='label.serialnumber'/>">
               </div>
           </div>
           <div class="col-md-4" align="right">
               <label for="searchAppAccount" class="col-md-5 control-label"><@spring.message code='label.apploginemail'/></label>
               <div class="col-md-7">
                   <input type="text" class="form-control" id="searchAppAccount" name="searchAppAccount"
                          placeholder="<@spring.message code='label.apploginemail'/>">
               </div>
           </div>

           <div class="col-md-4" align="right">
               <label for="searchCity" class="col-md-5 control-label"><@spring.message code='label.city'/></label>
               <div class="col-md-7">
                   <input type="text" class="form-control" id="searchCity" name="searchCity"
                          placeholder="<@spring.message code='label.city'/>">
               </div>
           </div>
       </div>
       <div class="form-group">
           <div class="col-md-4" align="right">
               <label for="searchDealername" class="col-md-5 control-label"><@spring.message code='label.serviceprovider'/></label>
               <div class="col-md-7">
                   <input type="text" class="form-control" id="searchDealername" name="searchDealername"
                          placeholder="<@spring.message code='label.serviceprovider'/>">
               </div>
           </div>
           <div class="col-md-4" align="right">
               <label for="searchinstallerorg" class="col-md-5 control-label"><@spring.message code='label.status'/></label>
               <div class="col-md-7">
                   <select id="searchStatus" name="status" class="col-md-12 form-control selectpicker" data-live-search="true" style="width: 100%"
                           title="<@spring.message code='label.devicetype'/>" >
                       <option value="" selected><@spring.message code='label.all'/></option>
                       <option value="1"><@spring.message code='label.normal'/></option>
                       <option value="2"><@spring.message code='label.freeze'/></option>
                   </select>
               </div>
           </div>
           <div class="col-md-4" align="right">
               <label for="searchinstallerorg" class="col-md-5 control-label"><@spring.message code='label.monitoringstation'/></label>
               <div class="col-md-7">
                   <select id="searchStation" name="monitoringstation" class="col-md-12 form-control selectpicker" data-live-search="true" style="width: 100%"
                           title="<@spring.message code='label.monitoringstation'/>" >
                       <option value="" selected><@spring.message code='label.all'/></option>
                       <option value="143" >Lanvac Surveillance Inc</option>
                       <option value="356" >Global Link</option>
                       <option value="0" ><@spring.message code="label.none"/></option>
                   </select>
               </div>
           </div>
       </div>
       <div class="form-group">
            <#--终端用户创建时间 开始时间搜索-->
           <div class="col-md-4" align="right">
               <label for="searchinstallerorg" class="col-md-5 control-label"><@spring.message code='label.starttime'/></label>
               <div class="col-md-7">
                   <div class="col-sm-12 input-group date form_datetime">
                       <input class="form-control" size="16" id="searchStarttime" name="starttime" type="text" readonly placeholder="<@spring.message code='label.starttime'/>" >
                       <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                       <span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
                   </div>
               </div>
           </div>
            <#--终端用户创建时间 结束时间搜索-->
           <div class="col-md-4" align="right">
               <label for="searchinstallerorg" class="col-md-5 control-label"><@spring.message code='label.endtime'/></label>
               <div class="col-md-7">
                   <div class="col-md-12 input-group date form_datetime">
                       <input class="form-control" size="16" id="searchEndtime" name="endtime" type="text" readonly placeholder="<@spring.message code='label.endtime'/>" >
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

<@shiro.hasPermission name="label.DeleteUser">
<button style="float: right;" type="button" class='btn btn-default' onclick='toggleUserStatus0("delete");'><@spring.message code='label.delete'/></button>
</@shiro.hasPermission>
<@shiro.hasPermission name="label.FreezeUser">
            <button style="float: right;" class='btn btn-default' type="button"
                    onclick='toggleUserStatus0("unsuspence");'><@spring.message code='label.unsuspence'/></button>
			<button style="float: right;" class='btn btn-default' type="button"
                    onclick='toggleUserStatus0("suspence");'><@spring.message code='label.suspenced'/></button>
</@shiro.hasPermission>
<@shiro.hasPermission name="label.ModifyUser">
<button style="float: right;" type="button" class='btn btn-default' onclick='modifyUser();'><@spring.message code='label.modify'/></button>
</@shiro.hasPermission>
<button style="float: right;" type="button" id='export' class='btn btn-default' onclick='exportData()'><@spring.message code='label.export'/></button>
<table id="table" data-toggle="table">
    <thead>
    <tr>
        <th data-field=""></th>
        <th data-field="userid" data-visible="false">ID</th>
        <th data-field="usercode" class="text-center"><@spring.message code='label.aibaseid'/></th>
        <th data-field="name" class="text-center"><@spring.message code='label.name'/></th>
        <th data-field="deviceid" class="text-center"><@spring.message code='label.gatewayID'/></th>
        <th data-field="monitoringstation" class="text-center"><@spring.message code='label.monitoringstation'/></th>
        <th data-field="serialnumber" class="text-center"><@spring.message code='label.serialnumber'/></th>
        <th data-field="simstatus" class="text-center"  data-formatter="formatter_statuss"><@spring.message code='label.status'/></th>
        <th data-field="activationdate" class="text-center" data-formatter="formatter_date" >Activation Date</th>
        <th data-field="appaccount" class="text-center"><@spring.message code='label.apploginemail'/></th>
        <th data-field="city" class="text-center"><@spring.message code='label.city'/></th>
        <th data-field="suppliername" class="text-center"><@spring.message code='label.serviceprovider'/></th>
        <th data-field="status" class="text-center" data-formatter="formatter_status"><@spring.message code='label.status'/></th>
        <th data-field="createtime" data-formatter="formatter_date" class="text-center"><@spring.message code="label.createtime"/></th>
        <th data-field="operate" class="text-center" data-visible="false"><@spring.message code='label.operate'/></th>
    </tr>
    </thead>
</table>
      </div>
    </div>
    <script type="text/javascript">
        $('#table').bootstrapTable({
            url: 'userInfoJsonList',
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
            uniqueId: "userid",                     //每一行的唯一标识，一般为主键列
            showToggle: false,                   //是否显示详细视图和列表视图的切换按钮
            cardView: false,                    //是否显示详细视图
            detailView: false,                  //是否显示父子表
            //得到查询的参数
            queryParams: function (params) {
                //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
                var temp = {
                    searchName:$("#searchName").val(),
                    searchCode:$("#searchCode").val(),
                    searchCity:$("#searchCity").val(),
                    searchSerialnumber:$("#searchSerialnumber").val(),
                    searchGatewayid:$("#searchGatewayid").val(),
                    //searchPhonenumber:$("#searchPhonenumber").val(),
                    searchAppAccount:$("#searchAppAccount").val(),
                    searchDealername:$("#searchDealername").val(),
                    searchStation:$("#searchStation").val(),
                    status:$("#searchStatus").val(),
                    starttime:$("#searchStarttime").val(),
                    endtime:$("#searchEndtime").val(),
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
                $("#iframeDetail").attr("src", 'queryUserInfo?userid='+id);
                $('#myModal').modal('show');
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

        function modifyUser() {
            if (getCheckedId() == null || getCheckedId().length == 0) {
                alert("<@spring.message code='label.nochecked'/>");
                return;
            } else if (getCheckedId().length > 1) {
                alert("<@spring.message code='label.chooseonepls'/>");
                return;
            }
            $("#iframeDetail").attr("src", '../user/modifyUserInfo?userid='+getCheckedId()[0]);
            $('#myModal').modal('show');
        }

        function toggleUserStatus0(obj) {
            var ids = getCheckedId();
            // alert(checkedIds);
            if (ids[0] == null || ids[0] == "") {
                alert("<@spring.message code='label.nochecked'/>");
                return;
            }
            if (obj == "unsuspence") {
                if (!confirm("<@spring.message code='label.recoverconfirm'/>")) {
                    return;
                }
            }
            if (obj == "suspence") {
                if (!confirm("<@spring.message code='label.freezeconfirm'/>")) {
                    return;
                }
            }
            if (obj == "synchronous") {
                if (!confirm("<@spring.message code='label.synchronousconfirm'/>")) {
                    return;
                }
            }
            if (obj == "delete") {
                if (!confirm("<@spring.message code='label.deleteconfirm'/>")) {
                    return;
                }
            }
            //异步更新
            $.ajax({
                type: 'post',
                url: '../user/toggleUserStatus',
                contentType: 'application/json',
                traditional: true,
                data: "{\"hope\":\"" + obj + "\",\"ids\":" + JSON.stringify(ids) + "}",
                success: function (data) {//返回json结果
                    alert("<@spring.message code='label.updatesuccess'/>");
                    $('#table').bootstrapTable('refresh');
                },
                error: function () {// 请求失败处理函数
                    alert("<@spring.message code='label.updatefailed'/>");
                    $('#table').bootstrapTable('refresh');
                }

            });
        }
        //状态格式化器
        function formatter_statuss(value, row, index) {
            if (value == 1)
                return '<@spring.message code='label.activated'/>';
            if (value == 2)
                return '<@spring.message code='label.deactivated'/>';
            if (value == 3)
                return '<@spring.message code='label.inventory'/>';
            if (value == 0)
                return '<@spring.message code='label.noeffect'/>';
        }
        function exportData() {
            var searchName = $("#searchName").val();
            var searchCode = $("#searchCode").val();
            var searchCity = $("#searchCity").val();
            var searchSerialnumber = $("#searchSerialnumber").val();
            var searchGatewayid = $("#searchGatewayid").val();
            var searchAppAccount = $("#searchAppAccount").val();
            var searchDealername = $("#searchDealername").val();
            var status = $("#searchStatus").val();
            var starttime = $("#searchStarttime").val();
            var endtime = $("#searchEndtime").val();

            window.open("exportenduserdata?searchName="+searchName+"&searchCode="+searchCode+"&searchCity="+searchCity+"&searchSerialnumber="+searchSerialnumber+"&searchGatewayid="+searchGatewayid+
                    "&searchAppAccount="+searchAppAccount+"&searchDealername="+searchDealername+"&status="+status+"&starttime="+starttime+"&endtime="+endtime);

        }
        function formatter_date(value, row, index) {
            if(value==null){
                return '';
            }
            return new Date(value).Format("yyyy-MM-dd");
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