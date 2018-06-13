include "../_head0.ftl"/>
<#include "../_head1.ftl"/>
          <div class="row">
              <div class="text-center"><h1><@spring.message code="label.EmployeeList"/></h1></div>
              <hr>
              <form id="searchform" class="form-horizontal">
                  <div class="form-group col-md-3">
                      <label for="searchname"
                             class="col-md-4 control-label"><@spring.message code="label.pname"/></label>
                      <div class="col-md-8">
                          <input type="text" class="form-control" id="searchname" name="searchname"
                                 placeholder="<@spring.message code="label.pname"/>">
                      </div>
                  </div>
                  <div class="form-group col-md-3">
                      <label for="searchcity"
                             class="col-md-4 control-label"><@spring.message code="label.city"/></label>
                      <div class="col-md-8">
                          <input type="text" class="form-control" id="searchcity" name="searchcity"
                                 placeholder="<@spring.message code="label.city"/>">
                      </div>
                  </div>
                  <div class="form-group col-md-3">
                      <label for="searchcitycode"
                             class="col-md-5 control-label"><@spring.message code="label.citycode"/></label>
                      <div class="col-md-7">
                          <input type="text" class="form-control" id="searchcitycode" name="searchcitycode"
                                 placeholder="<@spring.message code="label.citycode"/>">
                      </div>
                  </div>
                  <div class="form-group col-md-3">
                      <div class="col-md-2"></div>
                      <div class="col-md-10">
                          <button id="searchbtn" type="button"
                                  class="btn btn-default"><@spring.message code="label.search"/></button>
                      </div>
                  </div>
              </form>
          </div>
          <hr>
<@shiro.hasPermission name="button:changeStatus">
<#--    <button style="float: right;" class='btn btn-default'
            onclick='toggleEmployeeStatus0("unsuspence");'><@spring.message code='label.unsuspence'/></button>
 <button style="float: right;" class='btn btn-default'
         onclick='toggleEmployeeStatus0("suspence");'><@spring.message code='label.suspenced'/></button>
 <button onclick="window.location.href='addEmployeePage'" style="float: right;"
         class="btn btn-default"><@spring.message code="label.addnew"/></button>
<button style="float: right;" class='btn btn-default' id='btn1'  onclick='toggleEmployeeStatus0("modify");'><@spring.message code='label.modify'/></button>-->
</@shiro.hasPermission>
<table id="table" data-toggle="table">
    <thead>
    <tr>
    <#--<th data-field=""></th>-->
        <th data-field="name" class="text-center"><@spring.message code="label.name"/></th>
        <th data-field="employeeid" data-visible="false">ID</th>
        <th data-field="parentOrgName" class="text-center"><@spring.message code="label.parentorg"/></th>
        <th data-field="code" class="text-center"><@spring.message code="label.empcode"/></th>
        <th data-field="status" data-formatter='formatter_status'
            class="text-center"><@spring.message code="label.status"/></th>
        <th data-field="employeeroleid" class="text-center"><@spring.message code="label.privilege"/></th>
        <th data-field="operate" data-formatter="formatter_op"
            data-events="operateEvents" data-visible="false"
            class="text-center"><@spring.message code="label.operate"/></th>
    </tr>
    </thead>
</table>
      </div>
        <div class="col-md-1"></div>
    </div>
    <script type="text/javascript">
        $('#table').bootstrapTable({
            url: '../employee/employeeJsonList',
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
            uniqueId: "employeeid",                     //每一行的唯一标识，一般为主键列
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
                // checkbox: true,
                visible: true,
            }],
            onLoadSuccess: function () {
            },
            onLoadError: function () {
                // alert(lan.loaderror);
            },
            onDblClickRow: function (row, $element) {
                var id = row.employeeid;
                window.location.href = "listEmployeeRole?employeeid=" + id;
            }
        });

        //当点击搜索按钮后，表格刷新并跳到第一页
        $("#searchbtn").click(function () {
            $("#table").bootstrapTable("refreshOptions", {pageNumber: 1})
        });
    </script>
<#include "../_foot1.ftl"/>
<#include "../_foot0.ftl"/>