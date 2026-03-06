<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<div class="container text-center complete-wrap">

	<div class="complete-icon">
		<span>✓</span>
	</div>

	<h2 class="complete-title">Password Changed</h2>

	<p class="complete-desc">
		비밀번호가 성공적으로 변경되었습니다.<br> 이제 새로운 비밀번호로 안전하게 로그인하실 수 있습니다.
	</p>

	<div class="complete-btns">
		<a href="${pageContext.request.contextPath}/login/login.do"
			class="btn btn-primary btn-lg"> 로그인하기 </a> <a
			href="${pageContext.request.contextPath}/main.do"
			class="btn btn-outline btn-lg"> 홈으로 가기 </a>
	</div>

</div>