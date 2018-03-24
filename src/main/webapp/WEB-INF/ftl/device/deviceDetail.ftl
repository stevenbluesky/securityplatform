<!-- 网关详情 -->
<#include "/_head0.ftl"/>
    <div class="row-horizontal">
        <div class="col-md-1"></div>
        
        <div class="col-md-10">
              <div class="text-center"><h1>设备详情</h1></div>
              <br>
              <br>
              <br>
              <br>
              <div class="row">
			        <div class="col-md-4"></div>
			        <#if zwave??>
			        <div class="col-md-3">
				        <p>设备名称</p>
				        <p>服务商</p>
				        <p>告警状态</p>
				        <p>设备状态 </p>
				        <p>操作</p>
			        </div>
			        <div class="col-md-3">
					        <p>${(zwave.devicename)!'无'}</p>
					        <p>${(zwave.suppliename)!'无'}</p>
					        <p>${(zwave.warningstatuses)!'无'}</p>
					        <p>
					        ${zwave.open}
						        <#if (zwave.status)??>
						        	<#if (zwave.status)==255>
						        		开
						        	<#elseif (zwave.status)==0>
						        		关 
						        	<#else>
						        		无
					       			</#if>
							   </#if>
					        </p>
					        <p><button type="submit" class="btn btn-default" style="width:28%">关闭</button></p>
			        </div>
			        <#else>
			         <div class="col-md-6"">
			         	<h3>请先在设备列表里面选择一个设备...</h3>
			         </div>
			        </#if>
			        <div class="col-md-2"></div>
              </div>
        </div>
        <div class="col-md-1"></div>
    </div>

<!-- JavaScript 部分 -->
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
          alert("数据加载失败！");
      },
      onDblClickRow: function (row, $element) {
          var id = row.ID;
          EditViewById(id, 'view');
      }
});
    </script>
<#include "/_foot0.ftl"/>