<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<div class="page-content login-page">
	<div class="container">
		<div class="form-box text-center">

			<div class="complete-icon">
				<span>✓</span>
			</div>

			<h2 class="title en">회원가입 성공!</h2>

			<p class="result-msg">
				회원가입이 성공적으로 완료되었습니다.<br> Vinst Hotel의 멤버가 되신 것을 환영합니다. 다양한 혜택을
				누려보세요.
			</p>

			<div class="flex gap-10 mt-30">
				<a href="${pageContext.request.contextPath}/login/login.do"
					class="btn btn-primary btn-half"> 로그인하기 </a> <a
					href="${pageContext.request.contextPath}/main.do"
					class="btn btn-outline btn-half"> 메인으로 가기 </a>
			</div>

		</div>
	</div>
</div>