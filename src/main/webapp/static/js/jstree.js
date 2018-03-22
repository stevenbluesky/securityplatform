$(function() {
var $tree = $('#treeview12').treeview({
	  data: getTree(),
	  enableLinks:false
	});
	
$('#treeview12').on('nodeSelected', function(event, data) {
	$("#right").attr("src",data.href);
});
});

function getTree(){
	return jsontree;
}

var jsontree = [
  {
    text: "服务商列表",
    nodes: [
      {
        text: "新增服务商",
		href: 'org/addSupplierPage'
      }
    ],
	href:'org/supplierList'
  },
  {
    text: "安装商列表",
    nodes: [
      {
        text: "新增安装商",
		href: 'org/addInstallerPage'
      }
    ],
	href:'org/installerList'
  },
  {
    text: "员工列表",
    nodes: [
      {
        text: "新增员工",
		href: 'employee/employeeList'
      }
    ],
	href:'employee/addEmployeePage'
  },
  {
    text: "网关列表",
    nodes: [
      {
        text: "录入网关信息",
		href: 'gateway/typeGatewayInfo'
      },
	   {
        text: "网关详情",
		href: 'gateway/gatewayDetail'
      }
    ],
	href:'gateway/gatewayList'
  },
  {
    text: "设备列表",
    nodes: [
      {
        text: "设备详情",
		href: 'device/deviceDetail'
      }
    ],
	href:'device/deviceList'
  },
  {
    text: "用户列表",
    nodes: [
      {
        text: "录入用户信息",
		href: 'user/userList'
      }
    ],
	href:'user/typeUserInfo'
  },
  {
    text: "电话卡列表",
    nodes: [
      {
        text: "录入电话卡",
		href: 'phonecard/typePhonecardInfo'
      }
    ],
	href:'phonecard/phonecardList'
  }
];
