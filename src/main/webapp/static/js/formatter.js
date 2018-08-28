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

function formatter_devicetype1(devicetype) {

    if (devicetype == '1') {
        return lan.smokesensor;
    }
    else if (devicetype == '2') {
        return lan.leaksensor;
    }
    else if (devicetype == '3') {
        return lan.gassensor;
    }
    else if (devicetype == '4') {
        return lan.doorlock;
    }
    else if (devicetype == '5') {
        return lan.doorlock0;
    }
    else if (devicetype == '6') {
        return lan.PyroelectricSensors;
    }
    else if (devicetype == '7') {
        return lan.switch1;
    }
    else if (devicetype == '8') {
        return lan.switch2;
    }
    else if (devicetype == '9') {
        return lan.switch3;
    }
    else if (devicetype == '10') {
        return lan.sirenalarm;
    }
    else if (devicetype == '12') {
        return lan.valvecontroller;
    }
    else if (devicetype == '13') {
        return lan.curtain;
    }
    else if (devicetype == '14') {
        return lan.ac;
    }
    else if (devicetype == '15') {
        return lan.electricitymeter;
    }
    else if (devicetype == '16') {
        return lan.sos;
    }
    else if (devicetype == '17') {
        return lan.watermeter;
    }
    else if (devicetype == '18') {
        return lan.doorbell;
    }
    else if (devicetype == '19') {
        return lan.suoxin;
    }
    else if (devicetype == '20') {
        return lan.dimmer;
    }
    else if (devicetype == '22') {
        return lan.accesscontrol;
    }
    else if (devicetype == '23') {
        return lan.airsensor;
    }
    else if (devicetype == '24') {
        return lan.scenepanel1;
    }
    else if (devicetype == '25') {
        return lan.scenepanel2;
    }
    else if (devicetype == '26') {
        return lan.scenepanel3;
    }
    else if (devicetype == '27') {
        return lan.scenepanel4;
    }
    else if (devicetype == '28') {
        return lan.airconditioning;
    }
    else if (devicetype == '29') {
        return lan.ventilation;
    }
    else if (devicetype == '30') {
        return lan.hometheater;
    }
    else if (devicetype == '31') {
        return lan.passiveswitch1;
    }
    else if (devicetype == '32') {
        return lan.passiveswitch2;
    }
    else if (devicetype == '33') {
        return lan.passiveswitch3;
    }
    else if (devicetype == '34') {
        return lan.passiveswitch4;
    }
    else if (devicetype == '35') {
        return lan.passiveswitch6;
    }
    else if (devicetype == '36') {
        return lan.dry1;
    }
    else if (devicetype == '37') {
        return lan.dry2;
    }
    else if (devicetype == '38') {
        return lan.ventilationsystem1;
    }
    else if (devicetype == '39') {
        return lan.backgroundmusic;
    }
    else if (devicetype == '40') {
        return lan.ventilationsystem2;
    }
    else if (devicetype == '41') {
        return lan.multiswitch1;
    }
    else if (devicetype == '42') {
        return lan.multiswitch2;
    }
    else if (devicetype == '43') {
        return lan.multiswitch3;
    }
    else if (devicetype == '44') {
        return lan.projector;
    }
    else if (devicetype == '45') {
        return lan.armdevice;
    }
    else if (devicetype == '48') {
        return lan.scenepanel21;
    }
    else if (devicetype == '49') {
        return lan.scenepanel22;
    }
    else if (devicetype == '50') {
        return lan.scenepanel24;
    }
    else if (devicetype == '51') {
        return lan.heating;
    }
    else if (devicetype == '52') {
        return lan.scenepanel23;
    }
    else if (devicetype == '11') {
        return lan.socket0;
    }
    else if (devicetype == '46') {
        return lan.Coloringlamp;
    }else if (devicetype == '47') {
        return lan.DSCSecurity;
    }
    //console.log(row);
    else return devicetype;
}

function formatter_devicestatus1(devicetype,status) {
    //!devicetype是String类型,status是Integer类型
    switch (devicetype) {
        case '1':case '2':case '3':case '10':
        switch (status) {
            case -1:
                return lan.error;
            case 0:
                return lan.normal;
            case 255:
                return lan.alarm;
        }
        case '4':
            switch (status) {
                case -1:
                    return lan.error;
                case 0:
                    return lan.close;
                case 255:
                    return lan.open;
                case 251:
                    return lan.takepart;
            }
        case '5':case '19':case '22':
            switch (status) {
                case -1:
                    return lan.error;
                case 1:
                    return lan.dooropen0;
                case 255:
                    return lan.doorclose0;
                case 251:
                    return lan.takepart;
                case 300:
                    return lan.pswfail;
                case 301:
                    return lan.keyerror;
            }
        case '6':
            switch (status) {
                case -1:
                    return lan.error;
                case 0:
                    return lan.noperson;
                case 255:
                    return lan.hasperson;
                case 251:
                    return lan.takepart;
            }
        case '8':case '9':case '23':case '24':case '25':case '26':case '27':case '32':case '33':case '34':case '35':case '41':case '43':case '51':
        switch (status) {
            case -1:
                return lan.error;
            case 0:
                return lan.normal;
            default:
                return lan.normal;
        }
        case '13':
            switch (status) {
                case -1:
                    return lan.error;
                case 0:
                    return lan.close;
                case 99:
                    return lan.open;
                default :
                    return lan.open+":"+status+"%";
            }
        case '14':
            switch (status) {
                case -1:
                    return lan.error;
                case 0:
                    return lan.close;
                case 1:
                    return lan.open;
            }
        case '16':
            switch (status) {
                case -1:
                    return lan.error;
                case 0:
                    return lan.normal;
                case 255:
                    return lan.alarm;
                default:
                    return lan.normal;
            }

        case '7':case '11':case '12':case '15':case '17':case '18':case '29':case '30':case '31':case '36':case '37':case '39':case '40':case '46':
        switch (status) {
            case -1:
                return lan.error;
            case 0:
                return lan.close;
            case 255:
                return lan.open;
        }
        case '19':
            switch (status) {
                case -1:
                    return lan.error;
                case 1:
                    return lan.dooropen0;
                case 255:
                    return lan.doorclose0;
                case 251:
                    return lan.takepart;
                case 300:
                    return lan.pswfail;
                case 301:
                    return lan.keyerror;
            }
        case '20':
            switch (status) {
                case -1:
                    return lan.error;
                case 0:
                    return lan.close;
                case 99:
                    return lan.open;
                default :
                    return lan.open+":"+status+"%";
            }
        case '22':
            switch (status) {
                case -1:
                    return lan.error;
                case 1:
                    return lan.dooropen0;
                case 255:
                    return lan.doorclose0;
                case 251:
                    return lan.takepart;
                case 300:
                    return lan.pswfail;
                case 301:
                    return lan.keyerror;
            }
        case '47':
            switch (status) {
                case -1:
                    return lan.error;
                case 0:
                    return lan.normal;
            }
        case '48':case '49':case '50':case '52':
        switch (status) {
            default :
                return lan.normal;
        }
        default:
            switch (status) {
                case -1:
                    return lan.error;
                case 0:
                    return lan.normal;
                case 255:
                    return lan.alarm;
                case 251:
                    return lan.takepart;
            }
    }
}
function formatter_devicetype(value, row, index) {
    return formatter_devicetype1(row.devicetype);
}
function formatter_devicestatus(value, row, index) {
    //!devicetype是String类型,status是Integer类型
    return formatter_devicestatus1(row.devicetype,row.status)
}

function formatter_warnigstatuses(value, row, index) {
    var strs = [];
    if(row.warningstatuses == null || row.warningstatuses == "") {
        return '-';
    }
    var json = JSON.parse(row.warningstatuses);
    for (var i = 0; i < json.length; i++) {
        strs[i] = formatter_zwavewarning(row.devicetype, json[i]);;
    }
    return strs;

}
function formatter_statuses(value, row, index) {
    var strs = [];
    if(row.statuses == null || row.statuses == "") {
        return '-';
    }
    var json = JSON.parse(row.statuses);
    for (var i = 0; i < json.length; i++) {
        strs[i] = formatter_zwavestatuses(i,row.devicetype, json[i]);;
    }
    var str1 = strs.toString();
    var str2 = str1.replace(/-/g,'');
    var str3 = str2.replace(/,/g,'');
    var str4 = str3.replace(/"/g,'');
    if(str4==''){
        return "-"
    }
    return strs;

}
function formatter_zwavestatuses(i,devicetype,statuses) {
    switch (devicetype) {
        case '8': case '9': case '41': case '42': case '43':
            switch (statuses) {
                case 0:
                    return lan.s811+(i+1)+lan.s812;
                case 255:
                    return lan.s811+(i+1)+lan.s822;
            }
        default:
            return "-";
    }
}
function formatter_zwavewarning(devicetype, warningstatuses){
    if(devicetype==47){
        if(warningstatuses>0&&warningstatuses<65){
            return eval("lan.channel"+warningstatuses);
        }
    }
    switch (devicetype) {
        case '4':
            switch (warningstatuses) {
                case 255:
                    return lan.dooropen;
                case 251:
                    return lan.takepart;
            }
        case '5':case '19':case '22':
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
                case 306:
                    return lan.indoorunlock;
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
                    return lan.alarm;
                case 251:
                    return lan.takepart;
            }
        case '10':case '16':
            switch (warningstatuses) {
                case 255:
                    return lan.alarm;
                default:
                    return "-";
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
        case '-3':return lan.orgfreeze;//机构或父机构被冻结
        case '-4':return lan.dblose;//数据库连接断开
        case '-6':return lan.nameerror;//用户名错误
        case '-7':return lan.accountfroze;//账号冻结
        case '-11':return lan.orgnameexist;
        case '-12':return lan.loginnameexist;
        case '-98':return lan.loginfaied;//账户被冻结或者填写错误
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
        case '-118':return lan.validateCodeFail;
        case '-119':return lan.alrgateway;
        case '-120':return lan.alrphonecard;
        case '-121':return lan.alrappaccount;
        case '-122':return lan.wholeaddress;
        case '-123':return lan.repeatgateway;
        case '-124':return lan.repeatsim;
        case '-133':return lan.notsup;//不支持的设备类型
        case '-134':return lan.devicetimeout;//超时
        case '-135':return lan.noresponse;//设备无响应
        case '-136':return lan.devicebusy;//设备忙
        case '-199':return lan.unknownerror;//未知错误
        // default:return lan.error0;
        default:return status;
    }
}

//可进行开关操作的设备
function toggleStatusAbleDevicetype(devicetype){
    //哪些设备可以操作
    var changeableDevicetypeList=[5,7,10,11,12,15,19,22,36,37,40,46];
    for (var d in changeableDevicetypeList) {
        if (devicetype == changeableDevicetypeList[d]) {
            return true;
        }
    }
    return false;
}


