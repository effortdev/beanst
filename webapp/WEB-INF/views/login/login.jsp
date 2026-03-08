<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<div class="page-content login-page">

	<div class="container">
		<div class="form-box">
			<h2 class="title en">로그인</h2>

			<c:if test="${not empty errorMsg}">
				<p class="error-text text-center mb-20">${errorMsg}</p>
			</c:if>

			<form action="${pageContext.request.contextPath}/login/login.do"
				method="post">

				<input type="hidden" name="dest"
					value="${param.dest != null ? param.dest : dest}">

				<div class="form-group">
					<label>ID</label> <input type="text" name="user_id"
						class="form-control" required placeholder="아이디를 입력하세요">
				</div>

				<div class="form-group">
					<label>Password</label> <input type="password" name="password"
						class="form-control" required placeholder="비밀번호를 입력하세요">
				</div>

				<button type="submit" class="btn btn-primary btn-block btn-lg">로그인</button>



				<div class="text-center mt-20 login-links">
					<a href="${pageContext.request.contextPath}/findForm.do">아이디/비밀번호
						찾기</a> | <a href="${pageContext.request.contextPath}/member/join.do"
						class="join-link">회원가입</a>
				</div>

			</form>
		</div>
	</div>
</div>