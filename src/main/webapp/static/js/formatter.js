function formatter_status(value, row, index) {
    if (value == 1)
        return lan.normal;
    if (value == 0)
        return lan.unvalid;
    if (value == 2)
        return lan.suspenced;
    if (value == 9)
        return lan.deleted;
}

function toggleStatus(e,value,row,index,url,id,status) {
    $.ajax({
        type: "GET",
        dataType: "html",
        async: false,
        url: url+"?toStatus="+status+"&id="+id,
        success: function (data) {
            var strresult = data;
            var jsonObj = eval('(' + strresult + ')');
            if (jsonObj['status'] == 1) {
                alert("success");
                $('#table').bootstrapTable('refresh');
                // window.location.href = "employeeList";
            }
        },
        error: function (data) {
            alert("error:" + data.responseText);
        }
    });
}

function formatter_op(value, row, index) {
    // var name = row.name;
    // console.log(name);
    var str = "<button class='btn btn-default btn-xs' id='btn1'>修改</button>";
    // alert(row.status);
    if(row.status == 1) {
        str=str+"<button class='btn btn-default btn-xs' id='btn2'>冻结</button>"
    }else{
        str = str + "<button class='btn btn-default btn-xs' id='btn3'>解冻</button>"
    }
    str = str + "<button class='btn btn-default btn-xs' id='btn9'>删除</button>"
    return str;
}

function formatter_devicetype(value, row, index) {
    if (row.devicetype == '4') {
        return lan.doorlock;
    }
    if (row.devicetype == '2') {
        return lan.leaksensor;
    }
    if (row.devicetype == '6') {
        return lan.PyroelectricSensors;
    }
    if (row.devicetype == '11') {
        return lan.socket0;
    }
    if (row.devicetype == '46') {
        return lan.Coloringlamp;
    }
    //console.log(row);
    return row.devicetype;
}

function formatter_devicestatus(value, row, index) {
    if (row.status == '0') {
        return lan.close;
    }
    if (row.status == '255') {
        return lan.open;
    }
    return row.status;
}