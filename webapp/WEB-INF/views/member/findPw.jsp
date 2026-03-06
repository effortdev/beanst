<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<div class="join-container">
	<div class="join-wrap">
		<h2 class="join-title">Reset Password</h2>
		<p
			style="text-align: center; color: #666; margin-bottom: 30px; font-size: 14px;">
			비밀번호를 재설정하기 위해 가입 정보를 입력해주세요.</p>

		<c:if test="${not empty errorMsg}">
			<div class="text-danger"
				style="text-align: center; margin-bottom: 20px; color: #dc3545;">
				${errorMsg}</div>
		</c:if>

		<form action="${pageContext.request.contextPath}/member/findPw.do"
			method="post">
			<div class="form-group">
				<label>아이디</label> <input type="text" name="user_id"
					class="form-control" required placeholder="아이디 입력">
			</div>
			<div class="form-group">
				<label>이름</label> <input type="text" name="name"
					class="form-control" required placeholder="가입 시 입력한 이름">
			</div>
			<div class="form-group" style="margin-bottom: 30px;">
				<label>이메일</label> <input type="email" name="email"
					class="form-control" required placeholder="가입 시 입력한 이메일">
			</div>

			<button type="submit" class="btn btn-primary join-btn">본인
				확인하기</button>
		</form>

		<div class="join-footer">
			<a href="${pageContext.request.contextPath}/member/findId.do">아이디
				찾기</a> | <a href="${pageContext.request.contextPath}/login/login.do">로그인</a>
		</div>
	</div>
</div>