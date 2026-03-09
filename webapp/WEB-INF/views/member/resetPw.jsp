<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<div class="page-content login-page">
	<div class="container">
		<div class="form-box">
			<h2 class="title en">새 비밀번호</h2>
			<p class="form-desc text-center">새로 사용할 비밀번호를 입력해주세요.</p>

			<form action="${pageContext.request.contextPath}/member/resetPw.do"
				method="post" id="resetForm">
				<div class="form-group">
					<label>새 비밀번호</label> <input type="password" name="password"
						id="password" class="form-control" required
						placeholder="대문자, 숫자, 특수문자 포함 8~20자">
					<div id="pw-msg" class="val-msg"></div>
				</div>

				<div class="form-group mb-30">
					<label>비밀번호 확인</label> <input type="password" name="password_re"
						id="password_re" class="form-control" required
						placeholder="비밀번호 재입력">
					<div id="pw-re-msg" class="val-msg"></div>
				</div>

				<button type="submit" class="btn btn-primary btn-block btn-lg">비밀번호
					변경 완료</button>
			</form>
		</div>
	</div>
</div>

<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>
	$(document)
			.ready(
					function() {
						// 비밀번호 실시간 검사 (회원가입 로직 재활용)
						$("#password")
								.on(
										"input",
										function() {
											const val = $(this).val();
											const regex = /^(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,20}$/;
											const $msgArea = $("#pw-msg");

											if (val.length === 0) {
												$msgArea
														.text("")
														.removeClass(
																"text-success text-danger");
											} else if (regex.test(val)) {
												$msgArea.text("안전한 비밀번호입니다.")
														.addClass(
																"text-success")
														.removeClass(
																"text-danger");
											} else {
												$msgArea
														.text(
																"대문자, 숫자, 특수문자 포함 8~20자여야 합니다.")
														.addClass("text-danger")
														.removeClass(
																"text-success");
											}
											$("#password_re").trigger("input");
										});

						// 비밀번호 일치 실시간 검사
						$("#password_re").on(
								"input",
								function() {
									const pw = $("#password").val();
									const pwRe = $(this).val();
									const $msgArea = $("#pw-re-msg");

									if (pwRe.length === 0) {
										$msgArea.text("").removeClass(
												"text-success text-danger");
									} else if (pw === pwRe) {
										$msgArea.text("비밀번호가 일치합니다.").addClass(
												"text-success").removeClass(
												"text-danger");
									} else {
										$msgArea.text("비밀번호가 일치하지 않습니다.")
												.addClass("text-danger")
												.removeClass("text-success");
									}
								});

						// 최종 전송 전 체크
						$("#resetForm")
								.on(
										"submit",
										function(e) {
											const pw = $("#password").val();
											const pwRe = $("#password_re")
													.val();
											const regex = /^(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,20}$/;

											if (!regex.test(pw) || pw !== pwRe) {
												alert("비밀번호 형식을 확인하거나 일치 여부를 확인해주세요.");
												e.preventDefault();
											}
										});
					});
</script>