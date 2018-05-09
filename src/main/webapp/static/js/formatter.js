function formatter_gender(value) {
    if(value == 0 )
        return lan.female;
    if(value == 1 )
        return lan.male;
    if(value == 2 )
        return "LGBT";

}
function formatter_status(value, row, index) {
    if (value == 1)
        return lan.normal;
    if (value == 0)
        return lan.unvalid;
    if (value == 2)
        return lan.suspenced;
    if (value == 9)
        return lan.deleted;
    if(value == -1)
        return lan.status0;
}

function toggleStatus(e, value, row, index, url, id, status) {
    $.ajax({
        type: "GET",
        dataType: "html",
        async: false,
        url: url + "?toStatus=" + status + "&id=" + id,
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
    if (row.status == 1) {
        str = str + "<button class='btn btn-default btn-xs' id='btn2'>冻结</button>"
    } else {
        str = str + "<button class='btn btn-default btn-xs' id='btn3'>解冻</button>"
    }
    str = str + "<button class='btn btn-default btn-xs' id='btn9'>删除</button>"
    return str;
}

function formatter_devicetype(value, row, index) {

    if (row.devicetype == '1') {
        return lan.smokesensor;
    }
    if (row.devicetype == '2') {
        return lan.leaksensor;
    }
    if (row.devicetype == '3') {
        return lan.gassensor;
    }
    if (row.devicetype == '4') {
        return lan.doorlock;
    }
    if (row.devicetype == '5') {
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
    //!devicetype是String类型,status是Integer类型
    switch (row.devicetype) {
        case '4':
            switch (row.status) {
                case -1:
                    return lan.error;
                case 0:
                    return lan.close;
                case 255:
                    return lan.open;
                case 251:
                    return lan.takepart;
            }
        case '5':
            switch (row.status) {
                case -1:
                    return lan.error;
                case 1:
                    return lan.dooropen0;
                case 255:
                    return lan.doorlock0;
                case 251:
                    return lan.takepart;
                case 300:
                    return lan.pswfail;
                case 301:
                    return lan.keyerror;
            }
        case '1':
            switch (row.status) {
                case -1:
                    return lan.error;
                case 0:
                    return lan.normal;
                case 255:
                    return lan.alarm;
            }
        case '3':
            switch (row.status) {
                case -1:
                    return lan.error;
                case 0:
                    return lan.normal;
                case 255:
                    return lan.alarm;
            }
        case '2':
            switch (row.status) {
                case -1:
                    return lan.error;
                case 0:
                    return lan.normal;
                case 255:
                    return lan.alarm;
            }
        case '6':
            switch (row.status) {
                case -1:
                    return lan.error;
                case 0:
                    return lan.noperson;
                case 255:
                    return lan.hasperson;
                case 251:
                    return lan.takepart;
            }
        case '11':
            switch (row.status) {
                case -1:
                    return lan.error;
                case 0:
                    return lan.close;
                case 255:
                    return lan.open;
            }
        case '46':
            switch (row.status) {
                case -1:
                    return lan.error;
                case 0:
                    return lan.close;
                case 255:
                    return lan.open;
            }
    }
}

function formatter_warnigstatuses(value, row, index) {
    var strs = [];
    if(row.warningstatuses == null) {
        return '-';
    }
    var json = JSON.parse(row.warningstatuses);
    for (var i = 0; i < json.length; i++) {
        strs[i] = formatter_zwavewarning(row.devicetype, json[i]);;
    }
    return strs;

}

function formatter_zwavewarning(devicetype, warningstatuses){
    switch (devicetype) {
        case '4':
            switch (warningstatuses) {
                case 255:
                    return lan.dooropen;
                case 251:
                    return lan.takepart;
            }
        case '5':
            switch (warningstatuses) {
                case 1:
                    return lan.dooropen0
                case 255:
                    return lan.dooropen;
                case 251:
                    return lan.takepart;
                case 300:
                    return lan.pswfail;
                case 301:
                    return lan.keyerror;
                case 302:
                    return lan.alarm0;
                case 303:
                    return lan.status0;
                case 304:
                    return lan.keyalarm;
                case 305:
                    return lan.closefail;
            }
        case '1':
            switch (warningstatuses) {
                case 255:
                    return lan.alarm;
                case 251:
                    return lan.takepart;
            }
        case '3':
            switch (warningstatuses) {
                case 255:
                    return lan.alarm;
                case 251:
                    return lan.takepart;
            }
        case '2':
            switch (warningstatuses) {
                case 255:
                    // alert(row.warningstatuses);
                    return lan.alarm;
                case 251:
                    return lan.takepart;
            }
        case '6':
            switch (warningstatuses) {
                case 255:
                    // alert(row.warningstatuses);
                    return lan.hasperson;
                case 251:
                    return lan.takepart;
            }
        case '11':
            switch (warningstatuses) {
                case 310:
                    return lan.highpoweralarm;
            }
        case '46':
            return '-';
    }
}

function formatterReturnStatus(status){
    switch (status){
        case '1':return lan.success;
        case '2':return lan.addsuccess;
        case '-1':return lan.error0;
        case '-2':return lan.varificationcodeerror;
        case '-98':return lan.loginfaied;
        case '-99':return lan.notpermission;
        case '-100':return lan.notempty;
        case '-101':return lan.statusnull;
        case '-102':return lan.haduser;
        case '-103':return lan.installerneedscode;
        case '-104':return lan.duplicateempcode;
        case '-105':return lan.loaderror;
        case '-106':return lan.duplicategateway;
        case '-107':return lan.gorpnotnull;
        case '-108':return lan.duplicategatewayuser;
        case '-109':return lan.addgatewayerror;
        case '-110':return lan.duplicatephonecarduser;
        case '-111':return lan.simdontexsit;
        case '-112': return lan.devicenoexist;
        case '-113':return lan.apinopermission;
        case '-114':return lan.codeorpassworderror;
        case '-115':return lan.codeorpassworderror;
        case '-116':return lan.deviceoffline;
        case '-117':return lan.nopermission;
        case '-1113':return lan.connectdbfail;
        default:return lan.error0;
    }
}

//可进行开关操作的设备
function toggleStatusAbleDevicetype(devicetype){
    var changeableDevicetypeList=[11,46];
    for (var d in changeableDevicetypeList) {
        if (devicetype == changeableDevicetypeList[d]) {
            return true;
        }
    }
    return false;
}


