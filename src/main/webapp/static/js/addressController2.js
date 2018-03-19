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
			var str = "";
			for (var i = 0; i < data.length; i++) {
				str += '<option value=' + data[i].countryid + '>'
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
				url : "../address/getProvince?countryid=" + countryid,
				async : true,
				success : function(data) {
					var str = "";
					for (var i = 0; i < data.length; i++) {
						str += '<option value=' + data[i].provinceid + '>'
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
				url : "../address/getCity?provinceid=" + provinceid,
				async : true,
				success : function(data) {
					var str = "";
					for (var i = 0; i < data.length; i++) {
						str += '<option value=' + data[i].cityid + '>'
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
			var str = "";
			for (var i = 0; i < data.length; i++) {
				str += '<option value=' + data[i].countryid + '>'
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
				url : "../address/getProvince?countryid=" + countryid,
				async : true,
				success : function(data) {
					var str = "";
					for (var i = 0; i < data.length; i++) {
						str += '<option value=' + data[i].provinceid + '>'
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
				url : "../address/getCity?provinceid=" + provinceid,
				async : true,
				success : function(data) {
					var str = "";
					for (var i = 0; i < data.length; i++) {
						str += '<option value=' + data[i].cityid + '>'
								+ data[i].cityname + '</option>'
					}
					$("#cscity").html(str);

					$("#cscity").selectpicker('refresh');

				}
			});
		});
