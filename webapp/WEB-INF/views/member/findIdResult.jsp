<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<div class="join-container">
	<div class="join-wrap text-center" style="text-align: center;">
		<h2 class="join-title">ID Found</h2>

		<div class="complete-icon" style="margin: 0 auto 30px;">
			<span>✓</span>
		</div>

		<p style="color: #6F4E37; margin-bottom: 10px;">${name}고객님의 아이디를
			찾았습니다.</p>

		<div
			style="background: #fdf8f3; padding: 25px; border-radius: 6px; border: 1px dashed #C9A962; margin-bottom: 30px;">
			<span
				style="font-size: 24px; color: #2C1810; font-weight: bold; letter-spacing: 1px;">
				${foundId} </span>
		</div>

		<div class="complete-btns"
			style="display: flex; gap: 10px; justify-content: center;">
			<a href="${pageContext.request.contextPath}/login/login.do"
				class="btn btn-primary" style="flex: 1; padding: 15px;"> 로그인하기 </a>
			<a href="${pageContext.request.contextPath}/member/findPw.do"
				class="btn btn-outline"
				style="flex: 1; padding: 15px; border: 1px solid #C9A962; color: #C9A962; text-decoration: none; border-radius: 4px;">
				비밀번호 찾기 </a>
		</div>
	</div>
</div>