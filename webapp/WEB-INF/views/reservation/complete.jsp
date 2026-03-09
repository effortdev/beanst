<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>


<div class="page-content login-page">
	<div class="container">
		<div class="form-box text-center">

			<div class="complete-icon">
				<span>✓</span>
			</div>

			<h2 class="title en">예약 완료</h2>

			<p class="result-msg">
				고객님의 예약이 성공적으로 완료되었습니다.<br> Vinst Hotel을 선택해 주셔서 감사합니다. 편안한 휴식이
				되도록 최선을 다하겠습니다.
			</p>

			<div class="flex gap-10 mt-30">
				<a href="${pageContext.request.contextPath}/reservation/check.do"
					class="btn btn-primary btn-half"> 예약 내역 조회 </a> <a
					href="${pageContext.request.contextPath}/main.do"
					class="btn btn-outline btn-half"> 메인으로 가기 </a>
			</div>

		</div>
	</div>
</div>

</div>