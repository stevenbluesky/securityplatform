/**
 * 
 */
getbcountry();
function getbcountry() {
	$.ajax({
		type : "get",
		url : "../address/getCountry",
		async : true,
		success : function(data) {
			var str = "<option value=''></option>";
			for (var i = 0; i < data.length; i++) {
				str += '<option value=' + data[i].countryname + '>'
						+ data[i].countryname + '</option>'
			}
			$("#bcountry").html(str);
			$("#bcountry").selectpicker('refresh');

		}
	});
}
$("#bcountry").change(
		function() {
			var countryid = $("#bcountry").val();
			$.ajax({
				type : "get",
				url : "../address/getProvince?countryname=" + countryname,
				async : true,
				success : function(data) {
					var str = "<option value=''></option>";
					for (var i = 0; i < data.length; i++) {
						str += '<option value=' + data[i].provincename + '>'
								+ data[i].provincename + '</option>'
					}
					$("#bprovince").html(str);

					$("#bprovince").selectpicker('refresh');

				}
			});
		});

$("#bprovince").change(
		function() {
			var provinceid = $("#bprovince").val();
			$.ajax({
				type : "get",
				url : "../address/getCity?provincename=" + provincename,
				async : true,
				success : function(data) {
					var str = "<option value=''></option>";
					for (var i = 0; i < data.length; i++) {
						str += '<option value=' + data[i].cityname + '>'
								+ data[i].cityname + '</option>'
					}
					$("#bcity").html(str);

					$("#bcity").selectpicker('refresh');

				}
			});
		});

getcscountry();
function getcscountry() {
	$.ajax({
		type : "get",
		url : "../address/getCountry",
		async : true,
		success : function(data) {
			var str = "<option value=''></option>";
			for (var i = 0; i < data.length; i++) {
				str += '<option value=' + data[i].countryname + '>'
						+ data[i].countryname + '</option>'
			}
			$("#cscountry").html(str);

			$("#cscountry").selectpicker('refresh');

		}
	});
}

$("#cscountry").change(
		function() {
			var countryid = $("#cscountry").val();
			$.ajax({
				type : "get",
				url : "../address/getProvince?countryname=" + countryname,
				async : true,
				success : function(data) {
					var str = "<option value=''></option>";
					for (var i = 0; i < data.length; i++) {
						str += '<option value=' + data[i].provincename + '>'
								+ data[i].provincename + '</option>'
					}
					$("#csprovince").html(str);

					$("#csprovince").selectpicker('refresh');

				}
			});
		});

$("#csprovince").change(
		function() {
			var provinceid = $("#csprovince").val();
			$.ajax({
				type : "get",
				url : "../address/getCity?provincename=" + provincename,
				async : true,
				success : function(data) {
					var str = "<option value=''></option>";
					for (var i = 0; i < data.length; i++) {
						str += '<option value=' + data[i].cityname + '>'
								+ data[i].cityname + '</option>'
					}
					$("#cscity").html(str);

					$("#cscity").selectpicker('refresh');

				}
			});
		});
