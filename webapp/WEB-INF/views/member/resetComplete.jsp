<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<div class="page-content login-page">
	<div class="container">
		<div class="form-box text-center">

			<div class="complete-icon">
				<span>✓</span>
			</div>

			<h2 class="title en">비밀번호 변경 성공!</h2>

			<p class="result-msg">
				비밀번호가 성공적으로 변경되었습니다.<br> 이제 새로운 비밀번호로 안전하게 로그인하실 수 있습니다.
			</p>

			<div class="flex gap-10 mt-30">
				<a href="${pageContext.request.contextPath}/login/login.do"
					class="btn btn-primary btn-half"> 로그인하기 </a> <a
					href="${pageContext.request.contextPath}/main.do"
					class="btn btn-outline btn-half"> 홈으로 가기 </a>
			</div>

		</div>
	</div>
</div>