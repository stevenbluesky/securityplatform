<#include "/_head0.ftl"/>
<#include "/_head1.ftl"/>
                  <div class="row">
                      <div class="text-center"><h1><@spring.message code="label.installerlist"/></h1></div>
                      <hr>
                      <form class="form-inline" action="" method="POST">
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
                                  <input type="text" class="form-control" id="code" name="code"
                                         placeholder="<@spring.message code="label.city"/>">
                              </div>
                          </div>
                          <div class="form-group col-md-3">
                              <div>
                                  <b><@spring.message code="label.citycode"/></b>
                                  <input type="text" class="form-control" id="code" name="code"
                                         placeholder="<@spring.message code="label.citycode"/>">
                              </div>
                          </div>
                          <div class=" col-md-3">
                              <button type="submit"
                                      class="btn btn-default"><@spring.message code="label.borgpostal"/></button>
                          </div>
                      </form>
                  </div>
          <hr>
              <button onclick="window.location.href='addInstallerPage'" style="float: right;" type="submit"
                      class="btn btn-default"><@spring.message code="label.addnew"/></button>
          
<table id="table" data-toggle="table">
    <thead>
    <tr>
        <th data-field="id">ID</th><!-- 页面会吃掉一行, i don't know why.. -->
        <!--<th data-field="organizationid">ID</th>-->
        <th data-field="name"><@spring.message code="label.pname"/></th>
        <th data-field="code"><@spring.message code="label.code"/></th>
        <th data-field="city"><@spring.message code="label.city"/></th>
        <th data-field="status"><@spring.message code="label.status"/></th>
        <th data-field="operate"><@spring.message code="label.operate"/></th>
    </tr>
    </thead>
</table>
      </div>
        <div class="col-md-1"></div>
    </div>
    <script type="text/javascript">
        $('#table').bootstrapTable({
            url: 'installerJsonList',
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
            search: true,                      //是否显示表格搜索
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
                alert(lan.loaderror);
            },
            onDblClickRow: function (row, $element) {
                var id = row.ID;
            }
        });
    </script>

<#include "/_foot1.ftl"/>
<#include "/_foot0.ftl"/>