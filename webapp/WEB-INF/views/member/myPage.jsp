<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<body>
	<div class="wrap">
		<section class="firstSection">
			<div class="mypage-container">
				<div class="top_title fade-up">
					<h2 class="en">My Reservation</h2>
					<p class="text">고객님의 소중한 정보입니다.</p>
				</div>

				<c:choose>
					<c:when test="${not empty loginMember}">

						<!-- 세션 메시지 alert -->
						<c:if test="${not empty sessionScope.msg}">
							<script>
								alert("${sessionScope.msg}");
							</script>
							<c:remove var="msg" scope="session" />
						</c:if>

						<form action="${pageContext.request.contextPath}/member/update.do"
							method="post">

							<!-- 회원 정보 -->
							<div class="mypage-section">
								<h3>회원 정보</h3>
								<!-- 회원탈퇴 버튼 -->
								<button type="button" class="delete-btn"
									onclick="withdrawMember()">회원탈퇴</button>
								<div class="info-row">
									<label>이름</label> <span>${loginMember.name}</span>
								</div>
								<div class="info-row">
									<label>아이디</label> <span>${loginMember.userId}</span>
								</div>
								<div class="info-row">
									<label>가입일</label> <span>${loginMember.createdAt}</span>
								</div>

								<div class="info-row">
									<button type="button" class="reservation-btn"
										onclick="goToReservation()">나의 예약 바로가기</button>
								</div>
							</div>

							<!-- 비밀번호 변경 -->
							<div class="mypage-section">
								<h3>
									비밀번호 변경
									<button type="button" class="pw-toggle-btn"
										onclick="togglePassword()">비밀번호 변경하기</button>
								</h3>

								<div id="passwordArea" class="password-area"
									style="display: none;">
									<div class="info-row">
										<label>현재 비밀번호</label> <input type="password" name="currentPw">
									</div>
									<div class="info-row">
										<label>새 비밀번호</label> <input type="password" name="newPw">
									</div>
									<div class="info-row">
										<label>새 비밀번호 확인</label> <input type="password"
											name="newPwCheck">
									</div>
								</div>
							</div>

							<!-- 연락처 정보 -->
							<div class="mypage-section">
								<h3>연락처 정보</h3>

								<div class="info-row">
									<label>이메일</label> <input type="text" id="email" name="email"
										value="${empty loginMember.email ? '등록된 이메일이 없습니다.' : loginMember.email}"
										class="edit-input" readonly>
									<button type="button" class="edit-btn"
										onclick="toggleInput(this)">수정</button>
								</div>

								<div class="info-row">
									<label>전화번호</label> <input type="text" id="phone" name="phone"
										value="${empty loginMember.phone ? '등록된 전화번호가 없습니다.' : loginMember.phone}"
										class="edit-input" readonly>
									<button type="button" class="edit-btn"
										onclick="toggleInput(this)">수정</button>
								</div>
							</div>

							<div class="btn-area">
								<button type="submit" class="update-btn">정보 수정하기</button>
							</div>
						</form>

					</c:when>

					<c:otherwise>
						<p>로그인이 필요합니다.</p>
						<a href="${pageContext.request.contextPath}/login.do">로그인</a>
					</c:otherwise>
				</c:choose>
			</div>
		</section>
	</div>

	<script>
	
	// 나의 예약 바로가기
	function goToReservation() {
	    // 페이지 이동
	    window.location.href = "${pageContext.request.contextPath}/reservation/check.do";
	}
	// 회원탈퇴
	function withdrawMember() {
	    const pwConfirm = prompt("회원탈퇴를 진행하려면 현재 비밀번호를 입력해주세요.");
	    if(!pwConfirm || pwConfirm.trim() === "") {
	        alert("비밀번호를 입력해야 회원탈퇴가 가능합니다.");
	        return;
	    }

	    if(confirm("관리자에게 탈퇴 요청을 보냈습니다.승인하시겠습니까?")) {
	        // 폼 생성
	        const form = document.createElement("form");
	        form.method = "post";
	        form.action = "${pageContext.request.contextPath}/member/update.do";

	        // 비밀번호 input
	        const pwInput = document.createElement("input");
	        pwInput.type = "hidden";
	        pwInput.name = "currentPw";
	        pwInput.value = pwConfirm;
	        form.appendChild(pwInput);

	        // status 값 input (2 = 탈퇴)
	        const statusInput = document.createElement("input");
	        statusInput.type = "hidden";
	        statusInput.name = "status";
	        statusInput.value = "2";
	        form.appendChild(statusInput);

	        document.body.appendChild(form);
	        form.submit();
	    }
	}
		// 수정 ↔ 저장 토글
		function toggleInput(button) {
			const input = button.parentElement.querySelector("input");
			if (input.hasAttribute("readonly")) {
				input.removeAttribute("readonly");
				input.focus();
				button.textContent = "저장";
				input.dataset.editing = "true"; // 저장 전 상태 표시
			} else {
				input.setAttribute("readonly", true);
				button.textContent = "수정";
				input.dataset.editing = "false"; // 저장 완료
			}
		}

		// 비밀번호 영역 토글
		function togglePassword() {
			const area = document.getElementById("passwordArea");
			area.style.display = (area.style.display === "block") ? "none" : "block";
		}

		// 폼 제출 이벤트
		document.querySelector("form").addEventListener("submit", function(e) {
			// 1️⃣ 새 비밀번호 확인
			const newPw = document.querySelector("input[name='newPw']").value;
			const newPwCheck = document.querySelector("input[name='newPwCheck']").value;

			if(newPw !== "" || newPwCheck !== "") {
				if(newPw !== newPwCheck) {
					alert("새 비밀번호가 일치하지 않습니다.");
					e.preventDefault();
					return;
				}
			}

			// 2️⃣ 이메일/전화번호 저장 여부 체크
			const editingInputs = document.querySelectorAll("input[data-editing='true']");
			if(editingInputs.length > 0) {
				alert("변경 중인 정보는 먼저 저장 버튼을 눌러주세요.");
				e.preventDefault();
				return;
			}

			// 3️⃣ 비밀번호 확인 (prompt)
			const pwConfirm = prompt("정보를 수정하려면 현재 비밀번호를 입력해주세요.");
			if(!pwConfirm || pwConfirm.trim() === "") {
				alert("비밀번호를 입력해야 수정할 수 있습니다.");
				e.preventDefault();
				return;
			}

			// hidden input으로 currentPw 전달
			let hiddenPwInput = document.querySelector("input[name='currentPw']");
			if(!hiddenPwInput) {
				hiddenPwInput = document.createElement("input");
				hiddenPwInput.type = "hidden";
				hiddenPwInput.name = "currentPw";
				document.querySelector("form").appendChild(hiddenPwInput);
			}
			hiddenPwInput.value = pwConfirm;

			// 4️⃣ readonly 초기화
			document.querySelectorAll(".edit-input").forEach(input => input.setAttribute("readonly", true));
			document.querySelectorAll(".edit-btn").forEach(btn => btn.textContent = "수정");
		});
	</script>
</body>