<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<div class="page-content login-page">
	<div class="container">
		<div class="form-box text-center">

			<h2 class="title en">찾으신 아이디</h2>

			<div class="complete-icon">
				<span>✓</span>
			</div>

			<p class="result-msg">${name}고객님의 아이디를 찾았습니다.</p>

			<div class="result-box">
				<span class="result-text">${foundId}</span>
			</div>

			<div class="flex gap-10">
				<a href="${pageContext.request.contextPath}/login/login.do"
					class="btn btn-primary btn-half"> 로그인하기 </a> <a
					href="${pageContext.request.contextPath}/member/findPw.do"
					class="btn btn-outline btn-half"> 비밀번호 찾기 </a>
			</div>

		</div>
	</div>
</div>