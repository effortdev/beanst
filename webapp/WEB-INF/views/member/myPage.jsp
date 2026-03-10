<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:if test="${not empty sessionScope.msg}">
	<script>
		alert("${sessionScope.msg}");
	</script>
	<c:remove var="msg" scope="session" />
</c:if>

<div class="page-content login-page">
	<div class="container">
		<div class="form-box topupd">

			<h2 class="title en text-center mb-30">마이페이지 (정보 수정)</h2>

			<form id="newMyPageForm"
				action="${pageContext.request.contextPath}/member/update.do"
				method="post">
				<input type="hidden" id="actionType" name="actionType"
					value="update">

				<div class="form-group">
					<label>아이디</label> <input type="text"
						class="form-control readonly-bg" disabled value="${myInfo.userId}"
						readonly>
				</div>

				<div class="form-group">
					<label>이름</label> <input type="text" id="name" name="name"
						class="form-control" value="${myInfo.name}" required>
					<div id="name-msg" class="val-msg"></div>
				</div>

				<div class="form-group">
					<label>이메일</label> <input type="email" id="email" name="email"
						class="form-control" value="${myInfo.email}" required>
					<div id="email-msg" class="val-msg"></div>
				</div>

				<div class="form-group mb-30">
					<label>전화번호</label> <input type="text" id="phone" name="phone"
						class="form-control" value="${myInfo.phone}" required>
					<div id="phone-msg" class="val-msg"></div>
				</div>

				<hr class="summary-divider">

				<div class="form-group mt-30">
					<label>새 비밀번호 <span class="text-sm">(변경 시에만)</span></label> <input
						type="password" id="newPw" name="newPw" class="form-control"
						placeholder="영대문자, 숫자, 특수문자 포함 8~20자">
					<div id="pw-msg" class="val-msg"></div>
				</div>
				<div class="form-group mb-30">
					<label>새 비밀번호 확인</label> <input type="password" id="newPwCheck"
						name="newPwCheck" class="form-control">
					<div id="pw-re-msg" class="val-msg"></div>
				</div>

				<hr class="summary-divider">

				<div class="form-group mt-30 mb-30">
					<label class="req-danger" style="color: red;">현재 비밀번호
						확인(필수)</label> <input type="password" id="currentPw" name="currentPw"
						class="form-control" required placeholder="수정/탈퇴를 위해 꼭 입력하세요">
				</div>

				<div class="flex gap-10">
					<button type="button" class="btn btn-primary btn-half"
						onclick="executeAction('update')">정보 수정하기</button>

					<c:if test="${myInfo.status == '1'}">
						<button type="button" class="btn btn-danger btn-half"
							onclick="executeAction('withdraw')">회원탈퇴요청</button>
					</c:if>
				</div>
			</form>

		</div>
	</div>
</div>

<script>

$(document).ready(function() {
	function checkValid(id, regex, msgId, successMsg, failMsg) {
		const value = $(id).val();
		const $msgArea = $(msgId);
		if (value.length === 0) {
			$msgArea.text("").removeClass("text-success text-danger");
			return true; 
		}
		if (regex.test(value)) {
			$msgArea.text(successMsg).addClass("text-success").removeClass("text-danger");
			return true;
		} else {
			$msgArea.text(failMsg).addClass("text-danger").removeClass("text-success");
			return false;
		}
	}

	$("#name").on("input", function() {
		const val = $(this).val();
		const nameRegex = /^[가-힣a-zA-Z]+$/;
		const $msgArea = $("#name-msg");
		if (val.length === 0) {
			$msgArea.text("").removeClass("text-success text-danger");
		} else if (val.length < 2 || val.length > 20) {
			$msgArea.text("2~20자 사이로 입력해주세요.").addClass("text-danger").removeClass("text-success");
		} else if (!nameRegex.test(val)) {
			$msgArea.text("특수문자, 숫자, 공백은 사용할 수 없습니다.").addClass("text-danger").removeClass("text-success");
		} else {
			$msgArea.text("적절한 이름입니다.").addClass("text-success").removeClass("text-danger");
		}
	});

	$("#email").on("input", function() {
		checkValid("#email", /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/, "#email-msg", "올바른 이메일 형식입니다.", "형식이 올바르지 않습니다.");
	});

	$("#phone").on("input", function() {
		checkValid("#phone", /^010-\d{3,4}-\d{4}$/, "#phone-msg", "올바른 번호 형식입니다.", "010-0000-0000 형식으로 입력해주세요.");
	});

	$("#newPw").on("input", function() {
		const val = $(this).val();
		if (val.length === 0) {
			$("#pw-msg").text("").removeClass("text-success text-danger");
		} else {
			checkValid("#newPw", /^(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,20}$/, "#pw-msg", "안전한 비밀번호입니다.", "대문자, 숫자, 특수문자 포함 8~20자여야 합니다.");
		}
		$("#newPwCheck").trigger("input"); 
	});

	$("#newPwCheck").on("input", function() {
		const pw = $("#newPw").val();
		const pwRe = $(this).val();
		const $msgArea = $("#pw-re-msg");
		if (pwRe.length === 0) {
			$msgArea.text("").removeClass("text-success text-danger");
		} else if (pw === pwRe) {
			$msgArea.text("비밀번호가 일치합니다.").addClass("text-success").removeClass("text-danger");
		} else {
			$msgArea.text("비밀번호가 일치하지 않습니다.").addClass("text-danger").removeClass("text-success");
		}
	});
});

function executeAction(type) {
	let currentPw = document.getElementById("currentPw").value;
	
	if (!currentPw) {
		alert("작업을 진행하려면 현재 비밀번호를 입력해주세요.");
		$("#currentPw").focus();
		return;
	}

	if (type === 'update') {
		if ($(".text-danger").length > 0) {
			alert("입력하신 정보 중 형식에 맞지 않는 항목이 있습니다. 다시 확인해주세요.");
			return; 
		}
	}

	if (type === 'withdraw') {
		if (!confirm("정말로 회원탈퇴를 요청하시겠습니까?")) return;
	}

	document.getElementById("actionType").value = type;
	document.getElementById("newMyPageForm").submit();
}
</script>
