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
	var name = row.name;
	console.log(name);
	return "<button class='btn btn-default btn-xs' id='btn1' onclick=xixi("+name+")>操作</button>";
}
function formatter_devicetype(value,row,index){
	if(row.devicetype == '4'){
		return lan.doorlock;
	}
	if(row.devicetype == '2'){
		return lan.leaksensor;
	}
	if(row.devicetype == '6'){
		return lan.PyroelectricSensors;
	}
	if(row.devicetype == '11'){
		return lan.socket0;
	}
	if(row.devicetype == '46'){
		return lan.Coloringlamp;
	}
	//console.log(row);
	return row.devicetype;
}

function formatter_devicestatus(value,row,index){
	if(row.status == '0'){
		return lan.close;
	}
	if(row.status == '255'){
		return lan.open;
	}
	return row.status;
}