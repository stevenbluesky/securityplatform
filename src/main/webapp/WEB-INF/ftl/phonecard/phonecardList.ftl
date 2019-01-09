<#include "../_head0.ftl"/>
<#include "../_head1.ftl"/>
<#import "/spring.ftl" as spring />
          <div class="">
              <div class="text-center"><h1><@spring.message code='label.phonecardlist'/></h1></div>
              <hr>
              <form id="searchForm" class="form-horizontal" method="POST" >
                  <div class="form-group">
                  <div class="col-md-4">
                      <label class="col-md-5  control-label"><@spring.message code='label.serialnumber'/></label>
                      <div class="col-md-7">
                          <input type="text" class="form-control" id="searchserialnumber" name="serialnumber"
                                 placeholder="<@spring.message code='label.serialnumber'/>" >
                      </div>
                  </div>
                      <div class="col-md-4">
                          <label class="col-md-5 control-label"><@spring.message code='label.status'/></label>
                          <div class="col-md-7">
                              <select id="searchstatus" name="status" <#--class="selectpicker"--> class="form-control" style="width: 100%"
                                      title="<@spring.message code='label.choosestatus'/>">
                                  <option value="1"><@spring.message code='label.all'/></option>
                                  <option value="2"><@spring.message code='label.activated'/></option>
                                  <option value="3"><@spring.message code='label.deactivated'/></option>
                                  <option value="4"><@spring.message code='label.inventory'/></option>
                              </select>
                          </div>
                      </div>
                  <div class="col-md-4">
                      <label class="col-md-5 control-label">Rate Plan</label>
                      <div class="col-md-7">
                          <input type="text" class="form-control" id="searchrateplan" name="rateplan"
                                 placeholder="Rate Plan" >
                      </div>
                  </div>

                  </div>
                  <div class="form-group">
                      <div class="col-md-4" align="right">
                          <label for="searchDealername" class="col-md-5 control-label"></label>
                          <div class="col-md-7">
                              <input type="hidden" class="form-control" id="" name=""
                                     placeholder="">
                          </div>
                      </div>
                      <div class="col-md-4" align="right">
                          <label for="searchDealername" class="col-md-5 control-label"></label>
                          <div class="col-md-7">
                              <input type="hidden" class="form-control" id="" name=""
                                     placeholder="">
                          </div>
                      </div>

                      <div class="col-md-4" align="right">
                          <div class="col-md-5"></div>
                          <div class="col-md-7">
                              <button type="button" id="searchsubmit" class="btn btn-default"
                                      style="width:100%;"><@spring.message code='label.search'/></button>
                          </div>
                      </div>
                  </div>
              </form>
          </div>
          <hr>
<#--新增，启用，停用按钮-->
<@shiro.hasPermission  name="label.DeleteSIM">
            <button style="float: right;" type="button" id='deletePhonecard' class='btn btn-default' onclick='send();'><@spring.message code='label.delete'/></button>
</@shiro.hasPermission>
<@shiro.hasPermission name="label.FreezeSIM">
			<button style="float: right;" type="button" id='stopPhonecard' class='btn btn-default' onclick='updatePhonecardStatus("freeze");'><@spring.message code='label.freeze'/></button>
</@shiro.hasPermission>
<@shiro.hasPermission name="label.ActiveSIM">
			<button style="float: right;" type="button" id='startPhonecard' class='btn btn-default' onclick='updatePhonecardStatus("start");'><@spring.message code='label.start'/></button>
</@shiro.hasPermission>
<@shiro.hasPermission name="label.SynchronousSIMInfo">
            <button style="float: right;" type="button" class="btn btn-default" onclick='updatePhonecardStatus("synchronous");'><@spring.message code="label.synchronous"/></button>
</@shiro.hasPermission>
<button style="float: right;" type="button" id='export' class='btn btn-default' onclick='exportData()'><@spring.message code='label.export'/></button>
<#--<@shiro.hasPermission name="label.AddPhonecard">
           	<button style="float: right;" type="button" class="btn btn-default" onclick="window.location.href='typePhonecardInfo'"><@spring.message code="label.entering"/></button>
</@shiro.hasPermission>-->

	<table id="table" data-toggle="table">
        <thead>
        <tr>
            <th data-field="id">复选框</th>
            <th data-field="phonecardid" data-visible="false">主键</th>
            <th data-field="serialnumber" class="text-center"><@spring.message code='label.serialnumber'/></th>
            <th data-field="status" data-formatter="formatter_status"
                class="text-center"><@spring.message code='label.status'/></th>
            <th data-field="model" class="text-center"><@spring.message code='label.model'/></th>
            <th data-field="firmwareversion" class="text-center"><@spring.message code='label.firmwareversion'/></th>
            <th data-field="rateplan" class="text-center">Rate Plan</th>
            <th data-field="activationdate" data-formatter="formatter_date" class="text-center">Activation Date</th>
        <#-- <th data-field="employeeroleid">Dealer</th>-->
        </tr>
        </thead>
    </table>
      </div>
        <div class="col-md-1"></div>
    </div>

    <script type="text/javascript">
        var temp;
        var confirmdelete = "";
        $('#table').bootstrapTable({
            url: 'phonecardJsonList',
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
            uniqueId: "phonecardid",                     //每一行的唯一标识，一般为主键列
            showToggle: false,                   //是否显示详细视图和列表视图的切换按钮
            cardView: false,                    //是否显示详细视图
            detailView: false,                  //是否显示父子表
            //得到查询的参数
            queryParams: function (params) {
                //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
                temp = {
                    serialnumber: $("#searchserialnumber").val(),
                    rateplan: $("#searchrateplan").val(),
                    status: $("#searchstatus").val(),
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
            <#--alert('<@spring.message code="label.dataloaderror"/>');-->
            },
            onDblClickRow: function (row, $element) {//双击操作，打开电话卡详情页
                var phonecardid = row.phonecardid;
                $('#myModal').modal('show');
                $("#iframeDetail").attr("src", 'phonecardDetail?phonecardid='+phonecardid);
            }
        });
        //当点击搜索按钮后，表格刷新并跳到第一页
        $("#searchsubmit").click(function () {
            $("#table").bootstrapTable("refreshOptions", {pageNumber: 1})
        });
        function exportData() {
            var serialnumber = $("#searchserialnumber").val();
            var rateplan =  $("#searchrateplan").val();
            var status =  $("#searchstatus").val();

            window.open("exportsimcarddata?serialnumber="+serialnumber+"&rateplan="+rateplan+"&status="+status);

        }
        //状态格式化器
        function formatter_status(value, row, index) {
            if (value == 1)
                return '<@spring.message code='label.activated'/>';
            if (value == 2)
                return '<@spring.message code='label.deactivated'/>';
            if (value == 3)
                return '<@spring.message code='label.inventory'/>';
            if (value == 0)
                return '<@spring.message code='label.noeffect'/>';
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

        function formatter_date(value, row, index) {
            if(value==null){
                return '';
            }
            return new Date(value).Format("yyyy-MM-dd");
        }
        <#--获取复选框选中的列的id数组-->
        function getCheckedId() {
            var pid = $("#table").bootstrapTable('getSelections');
            var ids = [];
            for (var index in pid) {
                ids.push(pid[index].phonecardid);
            }
            if (ids.length == 0) {
                return new Array("-1");
            }
            return ids;
        }

        //更新状态
        function updatePhonecardStatus(obj) {
            var checkedIds = getCheckedId();
            var trans = [];
            if (checkedIds[0] == -1) {
                alert("<@spring.message code='label.nochecked'/>");
                return;
            }
            if (obj == "start") {
                if (!confirm("<@spring.message code='label.startconfirm'/>")) {
                    return;
                }
            }
            if (obj == "freeze") {
                if (!confirm("<@spring.message code='label.freezeconfirm'/>")) {
                    return;
                }
            }
            if (obj == "synchronous") {
                if (!confirm("<@spring.message code='label.synchronousconfirm'/>")) {
                    return;
                }
            }
            for (var index in checkedIds) {
                trans.push(checkedIds[index]);
            }
            //异步更新
            $.ajax({
                type: 'post',
                url: 'update',
                contentType: 'application/json',
                traditional: true,
                data: "{\"hope\":\"" + obj + "\",\"ids\":" + JSON.stringify(trans)+",\"confirmdelete\":\""+ confirmdelete+"\"}",
                success: function (data) {//返回json结果
                    if ("1" == data["msg"]) {
                        alert("<@spring.message code='label.updatesuccess'/>");
                    }else if(data["msg"] == "-900"){
                        alert("<@spring.message code='label.needcorrectchar'/>");
                    }else if(data["msg"] == "-899"){}
                    else if (data["msg"] == "-898"){
                        alert("<@spring.message code='label.albinding'/>");
                    }
                    else {
                        alert("<@spring.message code='label.updatefailed'/>");
                    }
                    $('#table').bootstrapTable('refresh');
                },
                error: function (data) {// 请求失败处理函数
                    alert("<@spring.message code='label.updatefailed'/>");
                    $('#table').bootstrapTable('refresh');
                }
            });
        }
        function send() {
            $('#mymyModal').modal('show');
        }
        function deleteAll() {
            confirmdelete = "YES";
            updatePhonecardStatus("delete");
            $('#mymyModal').modal('hide');
        }
        function deleteDB() {
            confirmdelete = "NO";
            updatePhonecardStatus("delete");
            $('#mymyModal').modal('hide');
        }
    </script>
<!-- 模态框（Modal） -->
<div class="modal fade" id="mymyModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog" style="width:40%;height:15%;">
        <div class="modal-content">
        <#--引入详情界面-->
            <div class="modal-body">
            <@spring.message code="label.phonecarddeleteconfirm"/>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" onclick="deleteAll()"><@spring.message code="label.freezeanddelete"/></button>
                <button type="button" class="btn btn-default" onclick="deleteDB()"><@spring.message code="label.justdelete"/></button>
                <button type="button" class="btn btn-default" data-dismiss="modal"><@spring.message code="label.cancel"/></button>
            </div>
        </div>
    </div>
</div>
<#include "../modal.ftl"/>
<#include "../_foot1.ftl"/>
<#include "../_foot0.ftl"/>
</head>