function formatter_status(value,row,index){
	if(value==1)
		return lan.normal;
	if(value==0)
		return lan.unvalid;
	if(value==0)
		return lan.suspenced;
	if(value==9)
		return lan.deleted;
}

function formatter_op(value,row,index){
	return "<button class='btn btn-default btn-xs' id='btn1' onclick='xixi()'>操作</button>";
}
function formatter_devicetype(value,row,index){
	if(row.devicetype == '4'){
		return "门磁";
	}
	if(row.devicetype == '2'){
		return "漏水传感器";
	}
	if(row.devicetype == '6'){
		return "移动感应器";
	}
	if(row.devicetype == '11'){
		return "插座";
	}
	if(row.devicetype == '46'){
		return "调色灯";
	}
	//console.log(row);
	return row.devicetype;
}

function formatter_devicestatus(value,row,index){
	if(row.status == '0'){
		return "关";
	}
	if(row.status == '255'){
		return "开";
	}
	return row.status;
}