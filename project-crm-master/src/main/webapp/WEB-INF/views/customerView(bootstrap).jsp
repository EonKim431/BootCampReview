<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>고객정보 관리</title>
<link
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	rel="stylesheet">
<style>
body {
	font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
	background-color: #f8f9fa;
}

html, body {
	height: 90%;
}

.container-fluid {
	height: calc(100% - 56px);
}

.card {
	height: 100%;
	display: flex;
	flex-direction: column;
}

.card-body {
	flex: 1;
	display: flex;
	flex-direction: column;
}

.buttons {
	margin-top: auto; /* Pushes buttons to the bottom */
}
</style>
</head>
<body>
	<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
		<a class="navbar-brand" href="#">고객정보 관리</a>
	</nav>

	<div class="container-fluid mt-3 d-flex flex-column">
		<div class="row flex-fill">
			<div class="col-md-3">
				<div class="card h-100">
					<div class="card-header">
						<h2>고객 검색</h2>
					</div>
					<div class="card-body">
						<input type="text" id="searchInput" class="form-control mb-3"
							placeholder="고객 이름을 입력하세요">
						<button id="searchBy" class="btn btn-primary btn-block mb-2">조건
							검색</button>
						<button id="searchAll" class="btn btn-secondary btn-block mb-3">전체
							검색</button>
						<div id="searchResults" class="form-check">
							<input type="radio" class="form-check-input" name="customer"
								id="customer1"> <label class="form-check-label"
								for="customer1">홍길동</label><br> <input type="radio"
								class="form-check-input" name="customer" id="customer2">
							<label class="form-check-label" for="customer2">강해라</label><br>
							<input type="radio" class="form-check-input" name="customer"
								id="customer3"> <label class="form-check-label"
								for="customer3">나란다</label><br> <input type="radio"
								class="form-check-input" name="customer" id="customer4">
							<label class="form-check-label" for="customer4">담나라</label><br>
							<input type="radio" class="form-check-input" name="customer"
								id="customer5"> <label class="form-check-label"
								for="customer5">라면다</label><br> <input type="radio"
								class="form-check-input" name="customer" id="customer6">
							<label class="form-check-label" for="customer6">박석일</label><br>
							<input type="radio" class="form-check-input" name="customer"
								id="customer7"> <label class="form-check-label"
								for="customer7">이성경</label>
						</div>
					</div>
				</div>
			</div>

			<div class="col-md-5">
				<div class="card h-100">
					<div class="card-header">
						<h2>고객 정보</h2>
					</div>
					<div class="card-body">
						<form action="" method="post" id="frm_update">
							<div class="form-group row">
								<label for="FRST_REG_DT" class="col-sm-4 col-form-label">작성일자</label>
								<div class="col-sm-8">
									<input type="text" class="form-control" name="FRST_REG_DT"
										id="FRST_REG_DT">
								</div>
							</div>
							<div class="form-group row">
								<label for="CUST_NM" class="col-sm-4 col-form-label">고객명</label>
								<div class="col-sm-8">
									<input type="text" class="form-control" name="CUST_NM"
										id="CUST_NM">
								</div>
							</div>
							<div class="form-group row">
								<label for="PRIDTF_NO" class="col-sm-4 col-form-label">실명번호</label>
								<div class="col-sm-8">
									<input type="text" class="form-control" name="PRIDTF_NO"
										id="PRIDTF_NO">
								</div>
							</div>
							<div class="form-group row">
								<label for="EML_ADDR" class="col-sm-4 col-form-label">E-mail</label>
								<div class="col-sm-8">
									<input type="text" class="form-control" name="EML_ADDR"
										id="EML_ADDR">
								</div>
							</div>
							<div class="form-group row">
								<label for="HOME_TELNO" class="col-sm-4 col-form-label">전화번호</label>
								<div class="col-sm-8">
									<input type="text" class="form-control" name="HOME_TELNO"
										id="HOME_TELNO">
								</div>
							</div>
							<div class="form-group row">
								<label for="MBL_TELNO" class="col-sm-4 col-form-label">휴대폰번호</label>
								<div class="col-sm-8">
									<input type="text" class="form-control" name="MBL_TELNO"
										id="MBL_TELNO">
								</div>
							</div>
							<div class="form-group row">
								<label for="CR_NM" class="col-sm-4 col-form-label">직업</label>
								<div class="col-sm-8">
									<input type="text" class="form-control" name="CR_NM" id="CR_NM">
								</div>
							</div>
							<div class="form-group row">
								<label for="ROAD_NM_ADDR" class="col-sm-4 col-form-label">주소</label>
								<div class="col-sm-8">
									<input type="text" class="form-control" name="ROAD_NM_ADDR"
										id="ROAD_NM_ADDR">
								</div>
							</div>
							<div class="form-group row">
								<label for="FRST_RGTR_SN" class="col-sm-4 col-form-label">관리담당자</label>
								<div class="col-sm-8">
									<input type="text" class="form-control" name="FRST_RGTR_SN"
										id="FRST_RGTR_SN">
								</div>
							</div>
							<div class="form-group row">
								<label class="col-sm-4 col-form-label">부서</label>
								<div class="col-sm-8">
									<input type="text" class="form-control-plaintext" value="관리 2팀"
										readonly>
								</div>
							</div>
							<div class="form-group row">
								<label class="col-sm-4 col-form-label">직위</label>
								<div class="col-sm-8">
									<input type="text" class="form-control-plaintext" value="사원"
										readonly>
								</div>
							</div>
							<div class="form-group row">
								<label class="col-sm-4 col-form-label">연락처</label>
								<div class="col-sm-8">
									<input type="text" class="form-control-plaintext"
										value="010-0000-0000" readonly>
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>

			<div class="col-md-4">
				<div class="card h-100">
					<div class="card-header">
						<h2>상담 내역</h2>
					</div>
					<div class="card-body">
						<div class="history mb-3"
							style="overflow-y: auto; max-height: 300px;">
							<p>2023-09-12 오후 2시 통화 요청함</p>
							<p>2023-09-16 오후 4시 미팅 약속을 오전 10시로 변경</p>
							<p>2023-09-19 오후 2시 청약저축 가입 안내 미팅</p>
						</div>
						<div class="buttons">
							<div class="d-flex flex-wrap mb-1">
								<button class="btn btn-primary flex-fill m-1">등록</button>
								<button class="btn btn-primary flex-fill m-1">변경</button>
								<button class="btn btn-primary flex-fill m-1">삭제</button>
								<button class="btn btn-primary flex-fill m-1">신규</button>
							</div>
							<div class="d-flex flex-wrap">
								<button class="btn btn-danger flex-fill m-1">고객조회</button>
								<button class="btn btn-secondary flex-fill m-1">종료</button>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
	<script>
	// JavaScript to handle dynamic functionalities
	document.getElementById('searchBy').addEventListener('click', function() {
		alert('조건 검색 기능은 구현되지 않았습니다.');
	});

	document.getElementById('searchAll').addEventListener('click', function() {
		alert('전체 검색 기능은 구현되지 않았습니다.');
	});
	</script>
</body>
</html>
