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
    text: lan.supplierlist,
    nodes: [
      {
        text: lan.addsupplier,
		href: 'org/addSupplierPage'
      }
    ],
	href:'org/supplierList'
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
];
