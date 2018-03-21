/**
 * 
 */
getCountry();

function getCountry() {
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
			$("#country").html(str);

			$("#country").selectpicker('refresh');

		}
	});
}
$("#country").change(
		function() {
			var countryid = $("#country").val();
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
					$("#province").html(str);

					$("#province").selectpicker('refresh');

				}
			});
		});

$("#province").change(
		function() {
			var provinceid = $("#province").val();
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
					$("#city").html(str);

					$("#city").selectpicker('refresh');

				}
			});
		});