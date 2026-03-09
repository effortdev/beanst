<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<div class="page-content login-page">
	<div class="container">
		<div class="form-box">
			<h2 class="title en">본인 확인</h2>
			<p class="form-desc text-center">비밀번호를 재설정하기 위해 가입 정보를 입력해주세요.</p>

			<c:if test="${not empty errorMsg}">
				<p class="error-text text-center mb-20">${errorMsg}</p>
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
				<div class="form-group mb-30">
					<label>이메일</label> <input type="email" name="email"
						class="form-control" required placeholder="가입 시 입력한 이메일">
				</div>

				<button type="submit" class="btn btn-primary btn-block btn-lg">본인
					확인하기</button>
			</form>

			<div class="text-center mt-20 login-links">
				<a href="${pageContext.request.contextPath}/member/findId.do">아이디
					찾기</a> | <a href="${pageContext.request.contextPath}/login/login.do">로그인</a>
			</div>
		</div>
	</div>
</div>