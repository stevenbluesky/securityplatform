function ajaxcountry(str,position) {
    $.ajax({
        type: "get",
        url: "../address/getCountry",
        async: true,
        success: function (data) {
            /*var str = "<option value='${(orgInfo.sphonenumber)!}'>${(orgInfo.sphonenumber)!}</option>";*/
            // var str = "<option value=''></option>";
            // var str = "";
            for (var i = 0; i < data.length; i++) {
                str += '<option value=' + data[i].countryid + '>' + data[i].countryname + '</option>'
            }
            $(position).html(str);
            $(position).selectpicker('refresh');
            // alert($("#coutry").html());
        }
    });
}
function ajaxprovince(str,position,id) {
    $.ajax({
        type: "get",
        url: "../address/getProvince?countryid=" + id,
        async: true,
        success: function (data) {
            // var str = "";
            for (var i = 0; i < data.length; i++) {
                str += '<option value=' + data[i].provinceid + '>'
                    + data[i].provincename + '</option>'
            }
            $(position).html(str);
            $(position).selectpicker('refresh');

        }
    });
}
function ajaxcity(str,position,id) {
    $.ajax({
        type: "get",
        url: "../address/getCity?provinceid=" + id,
        async: true,
        success: function (data) {
            // var str = "";
            for (var i = 0; i < data.length; i++) {
                str += '<option value=' + data[i].cityid + '>'
                    + data[i].cityname + '</option>'
            }
            $(position).html(str);

            $(position).selectpicker('refresh');

        }
    });
}

function getCountry(num) {
    var str = '';
    if(num==-1){
        str = "<option value=''></option>";
    }
    ajaxcountry(str,"#country");
}

function changeProvince(countryid) {
    // var countryid = $("#country").val();
    var str ="<option value=''></option>";
    ajaxprovince(str,"#province",countryid);
}

$("#country").change(function () {
        var countryid = $("#country").val();
        changeProvince(countryid);
    }
);
function changeCity(provinceid) {
    var str ="<option value=''></option>";
    ajaxcity(str,"#city",provinceid);
}
$("#province").change(function () {
        var provinceid = $("#province").val();
        changeCity(provinceid);
    }
);
function getbcountryB(num) {
    var str = '';
    if(num==-1){
        str = "<option value=''></option>";
    }
    ajaxcountry(str,"#bcountry");
}
function changeProvinceB(countryid) {
    var str ="<option value=''></option>";
    ajaxprovince(str,"#bprovince",countryid);
}
$("#bcountry").change(function () {
        var countryid = $("#bcountry").val();
        changeProvinceB(countryid);
    }
);
function changeCityB(provinceid) {
    var str="<option value=''></option>";
    ajaxcity(str,"#bcity",provinceid)
}
$("#bprovince").change(function () {
        var provinceid = $("#bprovince").val();
        changeCityB(provinceid);
    }
);
function getcscountry(num) {
    var str = '';
    if(num==-1){
        str = "<option value=''></option>";
    }
    ajaxcountry(str,"#cscountry");
}
function changeProvinceC(countryid) {
    var str = "<option value=''></option>";
    ajaxprovince(str,"#csprovince",countryid)
}
$("#cscountry").change(function () {
        var countryid = $("#cscountry").val();
        changeProvinceC(countryid);
    }
);
function changeCityC(provinceid) {
    var str = "<option value=''></option>";
    ajaxcity(str,"#cscity",provinceid)
}
$("#csprovince").change(function () {
        var provinceid = $("#csprovince").val();
        changeCityC(provinceid);
    }
);