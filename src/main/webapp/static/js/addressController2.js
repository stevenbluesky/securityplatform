/**
 * 
 */
getbcountryB();
// changeProvinceB(1);
// changeCityB(1);
function getbcountryB() {
	$.ajax({
		type : "get",
		url : "../address/getCountry",
		async : true,
		success : function(data) {
			var str = "<option value=''></option>";
			for (var i = 0; i < data.length; i++) {
				str += '<option value=' + data[i].countryid + '>'
						+ data[i].countryname + '</option>'
			}
			$("#bcountry").html(str);
			$("#bcountry").selectpicker('refresh');

		}
	});
}
function changeProvinceB(countryid) {
    $.ajax({
        type : "get",
        url : "../address/getProvince?countryid=" + countryid,
        async : true,
        success : function(data) {
            var str = "<option value=''></option>";
            for (var i = 0; i < data.length; i++) {
                str += '<option value=' + data[i].provinceid + '>'
                    + data[i].provincename + '</option>'
            }
            $("#bprovince").html(str);

            $("#bprovince").selectpicker('refresh');

        }
    });
}
$("#bcountry").change(function () {
        var countryid = $("#bcountry").val();
		changeProvinceB(countryid);
    }
		);

function changeCityB(provinceid) {
    $.ajax({
        type : "get",
        url : "../address/getCity?provinceid=" + provinceid,
        async : true,
        success : function(data) {
            var str = "<option value=''></option>";
            for (var i = 0; i < data.length; i++) {
                str += '<option value=' + data[i].cityid + '>'
                    + data[i].cityname + '</option>'
            }
            $("#bcity").html(str);

            $("#bcity").selectpicker('refresh');

        }
    });
}

$("#bprovince").change(function () {
        var provinceid = $("#bprovince").val();
		changeCityB(provinceid);
    }
		);

getcscountry();
// changeProvinceC(1);
// changeCityC(1);

function getcscountry() {
	$.ajax({
		type : "get",
		url : "../address/getCountry",
		async : true,
		success : function(data) {
			var str = "<option value=''></option>";
			for (var i = 0; i < data.length; i++) {
				str += '<option value=' + data[i].countryid + '>'
						+ data[i].countryname + '</option>'
			}
			$("#cscountry").html(str);

			$("#cscountry").selectpicker('refresh');

		}
	});
}

function changeProvinceC(countryid) {
    $.ajax({
        type : "get",
        url : "../address/getProvince?countryid=" + countryid,
        async : true,
        success : function(data) {
            var str = "<option value=''></option>";
            for (var i = 0; i < data.length; i++) {
                str += '<option value=' + data[i].provinceid + '>'
                    + data[i].provincename + '</option>'
            }
            $("#csprovince").html(str);

            $("#csprovince").selectpicker('refresh');

        }
    });
}

$("#cscountry").change(function () {
        var countryid = $("#cscountry").val();
        changeProvinceC(countryid);
    }
		);

function changeCityC(provinceid) {
    $.ajax({
        type : "get",
        url : "../address/getCity?provinceid=" + provinceid,
        async : true,
        success : function(data) {
            var str = "<option value=''></option>";
            for (var i = 0; i < data.length; i++) {
                str += '<option value=' + data[i].cityid + '>'
                    + data[i].cityname + '</option>'
            }
            $("#cscity").html(str);

            $("#cscity").selectpicker('refresh');

        }
    });
}
$("#csprovince").change(function () {
        var provinceid = $("#csprovince").val();
        changeCityC(provinceid);
    }
		);
