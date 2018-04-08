var jsontree ;
$(function() {
    //读取菜单, 异步请求
    getMenu();
});

function getMenu() {
    $.ajax({
        url : 'getMenuTree',//请求的url
        dataType : 'json',//返回的数据类型: jquery把返回的值转成json数据
        type : 'post',//请求的方式
        success : function(obj) {
            //把数据中的菜单赋值
            jsontree = JSON.stringify(obj);
            var $tree = $('#treeview12').treeview({
                data:jsontree,
                enableLinks:false
            });

            $('#treeview12').on('nodeSelected', function(event, data) {
                $("#right").attr("src",data.href);
            });
        },
        error:function (obj) {
            alert("Menu Load Failed!");
        }
    });
}



/*var jsontree = [
  {
    text: lan.supplierlist,
      href:'org/supplierList',
    nodes: [
      {
        text: lan.addsupplier,
		href: 'org/addSupplierPage'
      }
    ]
  },
  {
    text: lan.installerlist,
    nodes: [
      {
        text: lan.addinstaller,
		href: 'org/addInstallerPage'
      }
    ],
	href:'org/installerList'
  },
  {
    text: lan.employeelist,
    nodes: [
      {
        text: lan.addemployee,
		href: 'employee/addEmployeePage'
      }
    ],
	href:'employee/employeeList'
  },
  {
    text: lan.gatewaylist,
    nodes: [
      {
        text: lan.enteringgatewayinfo,
		href: 'gateway/typeGatewayInfo'
      },
	   {
        text: lan.gatewaydetail,
		href: 'gateway/gatewayDetail'
      }
    ],
	href:'gateway/gatewayList'
  },
  {
    text: lan.devicelist,
    nodes: [
      {
        text: lan.devicedetail,
		href: 'device/deviceDetail'
      }
    ],
	href:'device/deviceList'
  },
  {
    text: lan.userlist,
    nodes: [
      {
        text: lan.enteringuserinfo,
		href: 'user/typeUserInfo'
      }
    ],
	href:'user/userList'
  },
  {
    text: lan.phonecardlist,
    nodes: [
      {
        text: lan.enteringphonecardinfo,
		href: 'phonecard/typePhonecardInfo'
      }
    ],
	href:'phonecard/phonecardList'
  }
];*/
