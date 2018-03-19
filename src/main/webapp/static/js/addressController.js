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
			var str = "";
			for (var i = 0; i < data.length; i++) {
				str += '<option value=' + data[i].countryid + '>'
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
				url : "../address/getProvince?countryid=" + countryid,
				async : true,
				success : function(data) {
					var str = "";
					for (var i = 0; i < data.length; i++) {
						str += '<option value=' + data[i].provinceid + '>'
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
				url : "../address/getCity?provinceid=" + provinceid,
				async : true,
				success : function(data) {
					var str = "";
					for (var i = 0; i < data.length; i++) {
						str += '<option value=' + data[i].cityid + '>'
								+ data[i].cityname + '</option>'
					}
					$("#city").html(str);

					$("#city").selectpicker('refresh');

				}
			});
		});