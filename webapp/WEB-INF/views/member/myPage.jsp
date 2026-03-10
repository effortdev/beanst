<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt"%>

<body>
	<div class="wrap">
		<div class="textBox">
			<div class="top_title fade-up">
				<h2 class="en">My Reservation</h2>
				<p class="text">고객님의 소중한 예약 정보입니다.</p>
			</div>
		</div>
		<section class="titBox">
			<div class="mypage-container">
				<c:choose>
					<c:when test="${not empty selectMember}">

						<c:if test="${not empty sessionScope.msg}">
							<script>
								alert("${sessionScope.msg}");
							</script>
							<c:remove var="msg" scope="session" />
						</c:if>

						<form id="memberForm"
							action="${pageContext.request.contextPath}/member/update.do"
							method="post">

							<!-- 회원 정보 -->
							<div class="mypage-section-f">

								<button type="button" class="delete-btn"
									onclick="withdrawMember()">회원탈퇴요청</button>
								<h3>회원 정보</h3>
								<div class="info-row">
									<label>이름</label> <span>${selectMember.name}</span>
								</div>
								<div class="info-row">
									<label>아이디</label> <span>${selectMember.userId}</span>
								</div>
								<div class="info-row">
									<label>가입일</label> <span><fmt:formatDate
											value="${selectMember.createdAt}" pattern="yyyy년 MM월 dd일" /></span>
								</div>
								<div class="info-row">
									<button type="button" class="reservation-btn"
										onclick="goToReservation()">나의 예약 바로가기</button>
								</div>
							</div>


							<div class="mypage-section">
								<h3>
									비밀번호
									<button type="button" class="pw-toggle-btn"
										onclick="togglePassword()">비밀번호 변경하기</button>
								</h3>
								<div id="passwordArea" class="password-area">
									<div class="info-row">
										<label>현재 비밀번호</label> <input type="password" id="currentPw"
											name="currentPw">
									</div>
									<div class="info-row">
										<label>새 비밀번호</label> <input type="password" id="newPw"
											name="newPw">
									</div>
									<div class="info-row">
										<label>새 비밀번호 확인</label> <input type="password"
											id="newPwCheck" name="newPwCheck">
									</div>
								</div>
							</div>


							<div class="mypage-section-s">
								<h3>연락처 정보</h3>
								<div class="info-row">
									<label>이메일</label> <input type="text" id="email" name="email"
										class="edit-input"
										value="${empty selectMember.email ? '' : selectMember.email}"
										readonly>
									<button type="button" class="edit-btn-f"
										onclick="toggleInput(this)">수정</button>
								</div>
								<div class="info-row">
									<label>전화번호</label> <input type="text" id="phone" name="phone"
										class="edit-input"
										value="${empty selectMember.phone ? '' : selectMember.phone}"
										readonly>
									<button type="button" class="edit-btn-f"
										onclick="toggleInput(this)">수정</button>
								</div>
							</div>


							<div class="info-row password-confirm-row">
								<label>현재 비밀번호</label> <input type="password" id="confirmPw"
									placeholder="비밀번호 입력">
							</div>

							<div class="btn-area">
								<button type="submit" class="update-btn">정보 수정하기</button>
							</div>
						</form>
					</c:when>

					<c:otherwise>
						<p>로그인이 필요합니다.</p>
						<a href="${pageContext.request.contextPath}/login/login.do">로그인</a>
					</c:otherwise>
				</c:choose>
			</div>


			<div id="modalOverlay"
				style="display: none; position: fixed; top: 0; left: 0; width: 100%; height: 100%; background: rgba(0, 0, 0, 0.5); z-index: 999;"></div>
			<div id="withdrawModal"
				style="display: none; position: fixed; top: 50%; left: 50%; transform: translate(-50%, -50%); background: #fff; padding: 30px; border-radius: 8px; box-shadow: 0 0 15px rgba(0, 0, 0, 0.3); z-index: 1000;">
				<h3>회원탈퇴 확인</h3>
				<p>회원탈퇴를 진행하려면 현재 비밀번호를 입력해주세요.</p>
				<input type="password" id="withdrawPw" placeholder="현재 비밀번호"
					style="width: 100%; padding: 10px; margin-top: 10px; margin-bottom: 15px; border: 1px solid #ccc; border-radius: 4px;">
				<div style="text-align: right;">
					<button type="button" onclick="submitWithdraw()"
						style="padding: 6px 12px; margin-right: 10px;">확인</button>
					<button type="button" onclick="closeModal()"
						style="padding: 6px 12px;">취소</button>
				</div>
			</div>
		</section>
	</div>
	<script src="${pageContext.request.contextPath}/assets/js/main.js"></script>

	<script>
		const CONTEXT_PATH = "${pageContext.request.contextPath}";
	</script>

	<script src="${pageContext.request.contextPath}/assets/js/my-page.js"></script>
</body>