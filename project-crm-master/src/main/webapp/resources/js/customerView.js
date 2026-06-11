$(document).ready(function() {
	let now = new Date();
	let year = now.getFullYear();
	let month = now.getMonth() + 1;
	let day = now.getDate();

	$("#FRST_REG_DT").val(
		year + '-' + (month < 10 ? '0' + month : month) + '-' + (day < 10 ? '0' + day : day)
	);

	var allData;

	document.getElementById('searchManagerBtn').addEventListener('click', function() {
		var managerName = document.getElementById('FRST_RGTR_SN').value;

		if (managerName) {
			alert(managerName + " 검색합니다.");
		} else {
			alert("관리담당자 이름을 입력하세요.");
		}
	});

	$("#res").on("click", function() {
		$("#frm_update").each(function() {
			this.reset();
		});

		$("#content").empty();

		let now = new Date();
		let year = now.getFullYear();
		let month = now.getMonth() + 1;
		let day = now.getDate();

		$("#FRST_REG_DT").val(
			year + '-' + (month < 10 ? '0' + month : month) + '-' + (day < 10 ? '0' + day : day)
		);

		$('#frm_update').attr('action', "< c:url value='/' />");
	});

	$("#end").on("click", function() {
		window.location.href = ajaxUrl;
	});

	$(document).on("click", "input:radio[name='result']", function() {
		var targetValue = $(this).val();
		var obj = {};
		obj["keyword"] = targetValue;

		$.ajax({
			url: ajaxUrl + '/TextLogAjax',
			type: "post",
			data: JSON.stringify(obj),
			dataType: "json",
			contentType: "application/json",
			success: function(data) {
				var content = "";

				data.forEach(function(dto) {
					content += "날짜: " + dto.date + "<br>";
					content += "내용: " + dto.content + "<br><br>";
				});

				$("#content").html(content);
			},
			error: function(errorThrown) {
				alert(errorThrown.statusText);
			}
		});
	});

	$("#search").on("click", function() {
		var keyword = $("#searchInput").val().trim();
		$("#content").empty();

		if (keyword == "") {
			alert('아이디를 입력하세요');
			return;
		}

		var obj = {};
		obj["keyword"] = keyword;

		$.ajax({
			url: ajaxUrl + '/searchAjax',
			type: "post",
			data: JSON.stringify(obj),
			dataType: "json",
			contentType: "application/json",
			success: function(data) {
				$("#searchResults").empty();

				for (var i = 0; i < data.length; i++) {
					var str = "<label><input type='radio' value='" + data[i].cust_SN + "' name='result' class='result'>"
						+ data[i].cust_NM
						+ "</label><br>";

					$("#searchResults").append(str);

					if (i == 0) {
						console.log(str);

						var strContent = "<td>" + data[i].a_name + "</td><td>" + data[i].a_phone + "</td>";
						$("#content").html(strContent);
					} else {
						console.log(data[i].cust_NM);
					}
				}
			},
			error: function(errorThrown) {
				alert(errorThrown.statusText);
			}
		});
	});

	$("#searchAll").on("click", function() {
		$.ajax({
			url: ajaxUrl + '/searchAllAjax',
			type: "post",
			dataType: "json",
			success: function(data) {
				$("#searchResults").empty();

				for (var i = 0; i < data.length; i++) {
					if (i == 0) {
						var str = "<label><input type='radio' value='" + data[i].cust_SN + "' name='result' class='result' checked>"
							+ data[i].cust_NM
							+ "</label><br>";

						console.log(str);
						$("#searchResults").append(str);

						var strContent = "<td>" + data[i].a_name + "</td><td>" + data[i].a_phone + "</td>";
						$("#content").html(strContent);
					} else {
						console.log(data[i].cust_NM);

						var str = "<label><input type='radio' value='" + data[i].cust_SN + "' name='result'>"
							+ data[i].cust_NM
							+ "</label><br>";

						$("#searchResults").append(str);
					}
				}
			},
			error: function(errorThrown) {
				alert(errorThrown.statusText);
			}
		});
	});

	$(document).on("click", "input:radio[name='result']", function() {
		var targetId = $(this).val();
		var obj = {};
		obj["keyword"] = targetId;

		$.ajax({
			url: ajaxUrl + '/searchOneAjax',
			type: "post",
			data: JSON.stringify(obj),
			dataType: "json",
			contentType: "application/json",
			success: function(data) {
				$("#CUST_NM").val(data.cust_NM);
				$("#CUST_SN").val(data.cust_SN);
				$("#FRST_REG_DT").val(data.frst_REG_DT);
				$("#PRIDTF_NO").val(data.pridtf_NO);
				$("#CUST_SN").val(data.cust_SN);
				$("#VBRDT").val(data.vbrdt);
				$("#CR_NM").val(data.cr_NM);
				$("#MBL_TELNO").val(data.mbl_TELNO);
				$("#HOME_TELNO").val(data.home_TELNO);
				$("#ROAD_NM_ADDR").val(data.road_NM_ADDR);
				$("#FRST_RGTR_SN").val(data.frst_RGTR_SN);
				$("#EML_ADDR").val(data.eml_ADDR);

				$('#frm_update').attr('action', "< c:url value='/' />");
			},
			error: function(errorThrown) {
				alert(errorThrown.statusText);
			}
		});
	});

	$('#del-btn').click(function() {
		var userConfirmed = confirm("정말 이 고객의 정보를 삭제하시겠습니까?");

		if (userConfirmed) {
			var CUST_SN = $('#CUST_SN').val();

			$.ajax({
				url: ajaxUrl + '/deleteCustomer',
				type: "POST",
				data: { CUST_SN: CUST_SN },
				success: function(response) {
					alert("정상적으로 삭제되었습니다.");
					location.href = ajaxUrl + '/cust';
				},
				error: function(xhr, status, error) {
					alert("삭제 중 오류가 발생했습니다.");
					console.error(xhr.responseText);
				}
			});
		} else {
			alert("삭제를 취소합니다.");
		}
	});

	$("#registerBtn").on("click", function() {
		let customerDTO = {
			CUST_NM: $('input[name=CUST_NM]').val(),
			PRIDTF_NO: $('input[name=PRIDTF_NO]').val(),
			EML_ADDR: $('input[name=EML_ADDR]').val(),
			HOME_TELNO: $('input[name=HOME_TELNO]').val(),
			MBL_TELNO: $('input[name=MBL_TELNO]').val(),
			CR_NM: $('input[name=CR_NM]').val(),
			ROAD_NM_ADDR: $('input[name=ROAD_NM_ADDR]').val()
		};

		$.ajax({
			url: ajaxUrl + '/registerCustomer',
			type: "POST",
			data: customerDTO,
			success: function(response) {
				if ($.trim(response) === "success") {
					alert("고객 등록이 완료되었습니다.");
					location.reload();
				} else {
					alert(response);
				}
			},
			error: function(xhr, status, error) {
				alert("등록 중 오류가 발생했습니다.");
				console.error(xhr.responseText);
			}
		});
	});

	$("#updateBtn").on("click", function() {
		let customerDTO = {
			CUST_SN: $("#CUST_SN").val(),
			CUST_NM: $('input[name=CUST_NM]').val(),
			PRIDTF_NO: $('input[name=PRIDTF_NO]').val(),
			EML_ADDR: $('input[name=EML_ADDR]').val(),
			HOME_TELNO: $('input[name=HOME_TELNO]').val(),
			MBL_TELNO: $('input[name=MBL_TELNO]').val(),
			CR_NM: $('input[name=CR_NM]').val(),
			ROAD_NM_ADDR: $('input[name=ROAD_NM_ADDR]').val()
		};

		$.ajax({
			url: ajaxUrl + '/updateCustomer',
			type: "POST",
			data: customerDTO,
			success: function(response) {
				if ($.trim(response) === "success") {
					alert("고객 정보가 수정되었습니다.");
					location.reload();
				} else {
					alert(response);
				}
			},
			error: function(xhr, status, error) {
				alert("변경 중 오류가 발생했습니다.");
				console.error(xhr.responseText);
			}
		});
	});
});