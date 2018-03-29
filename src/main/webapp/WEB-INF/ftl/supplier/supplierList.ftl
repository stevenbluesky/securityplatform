<#include "/_head0.ftl"/>
<#include "/_head1.ftl"/>
        
                  <div class="row">
                      <div class="text-center"><h1><@spring.message code="label.supplierlist"/></h1></div>
                      <hr>
                      <form id="searchform" class="form-inline" method="POST">
                          <div class="form-group col-md-3">
                              <div>
                                  <b><@spring.message code="label.pname"/></b>
                                  <input type="text" class="form-control" id="name" name="name"
                                         placeholder="<@spring.message code="label.pname"/>">
                              </div>
                          </div>
                          <div class="form-group col-md-3">
                              <div>
                                  <b><@spring.message code="label.city"/></b>
                                  <input type="text" class="form-control" id="city" name="city"
                                         placeholder="<@spring.message code="label.city"/>">
                              </div>
                          </div>
                          `
                          <div class="form-group col-md-3">
                              <div>
                                  <b><@spring.message code="label.citycode"/></b>
                                  <input type="text" class="form-control" id="citycode" name="citycode"
                                         placeholder="<@spring.message code="label.citycode"/>">
                              </div>
                          </div>
                          <div class=" col-md-3">
                              <button id="searchbtn"
                                      class="btn btn-default"><@spring.message code="label.search"/></button>
                          </div>
                      </form>
                  </div>
          <hr>

            <button style="float: right;" class='btn btn-default'
                    onclick='toggleOrganizationStatus("unsuspence");'><@spring.message code='label.unsuspence'/></button>
			<button style="float: right;" class='btn btn-default'
                    onclick='toggleOrganizationStatus("suspence");'><@spring.message code='label.suspenced'/></button>
            <button onclick="window.location.href='addSupplierPage'" style="float: right;"
                    class="btn btn-default"><@spring.message code="label.addnew"/></button>

<table id="table" data-toggle="table">
    <thead>
    <tr>
        <th data-field=""></th>
        <th data-field="organizationid" data-visible="false">ID</th>
        <!--<th data-field="organizationid">ID</th>-->
        <th data-field="name"><@spring.message code="label.pname"/></th>
        <th data-field="code"><@spring.message code="label.code"/></th>
        <th data-field="city"><@spring.message code="label.city"/></th>
        <th data-field="status" data-formatter="formatter_status"><@spring.message code="label.status"/></th>
        <th data-field="operate" data-formatter="formatter_op"
            data-events="supplieraddEvents"><@spring.message code="label.operate"/></th>

    </tr>
    </thead>
</table>
      </div>
        <div class="col-md-1"></div>
    </div>
    <script type="text/javascript">
        $('#table').bootstrapTable({
            url: 'supplierJsonList',
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
            uniqueId: "code",                     //每一行的唯一标识，一般为主键列
            showToggle: false,                   //是否显示详细视图和列表视图的切换按钮
            cardView: false,                    //是否显示详细视图
            detailView: false,                  //是否显示父子表
            //得到查询的参数
            queryParams: function (params) {
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
                checkbox: true,
                visible: true
            }],
            onLoadSuccess: function () {
            },
            onLoadError: function () {
                //alert(lan.loaderror);
            },
            onDblClickRow: function (row, $element) {
                var id = row.id;
            }
        });

        window.supplieraddEvents =
                {
                    "click #btn1": function (e, value, row, index) {
                        // alert(row.name);
                        window.location.href = "addSupplierPage?organizationid=" + row.organizationid;
                    },
                    "click #btn2": function (e, value, row, index) {
                        toggleStatus(e, value, row, index, 'toggleOrganizationStatus', row.organizationid, 2);
                    },
                    "click #btn3": function (e, value, row, index) {
                        toggleStatus(e, value, row, index, 'toggleOrganizationStatus', row.organizationid, 1);
                    },
                    "click #btn9": function (e, value, row, index) {
                        toggleStatus(e, value, row, index, 'toggleOrganizationStatus', row.organizationid, 9);
                    }

                };

        $("#searchbtn").click(function () {
            if (1) {//获取验证结果，如果成功，执行下面代码
                var url = "../org/searchSupplier";
                $.ajax({
                    type: "POST",
                    dataType: "html",
                    async: false,
                    url: url,
                    data: $('#searchform').serialize(),
                    success: function (data) {
                        var strresult = data;
                        alert(strresult);
                    },
                    error: function (data) {
                        alert("error:" + data.responseText);
                    }
                });
            } else {
                alert("<@spring.message code="label.nullstrexception"/>");
            }
        });


        // <#--获取复选框选中的列的id数组-->
        function getCheckedId() {
            var arr = $("#table").bootstrapTable('getSelections');
            var ids = [];
            arr.forEach(function (val, index, arr) {
                ids[index] = val.organizationid;
            });
            return ids;
        }
        // $("#table").bootstrapTable('getSelections')[1].organizationid
        function toggleOrganizationStatus(obj) {
            var ids = getCheckedId();
            // alert(checkedIds);
            if (ids[0] == null || ids[0] =="") {
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
            //异步更新
            $.ajax({
                type: 'post',
                url: '../org/toggleOrganizationStatus0',
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
    </script>
<#include "/_foot1.ftl"/>
<#include "/_foot0.ftl"/>