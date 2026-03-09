<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<div class="page-content login-page">
	<div class="container">
		<div class="form-box">
			<h2 class="title en">아이디 찾기</h2>

			<c:if test="${not empty errorMsg}">
				<p class="error-text text-center mb-20">${errorMsg}</p>
			</c:if>

			<form action="${pageContext.request.contextPath}/member/findId.do"
				method="post">

				<div class="form-group">
					<label>이름</label> <input type="text" name="name"
						class="form-control" required placeholder="가입 시 입력한 이름">
				</div>

				<div class="form-group">
					<label>이메일</label> <input type="email" name="email"
						class="form-control" required placeholder="가입 시 입력한 이메일">
				</div>

				<button type="submit" class="btn btn-primary btn-block btn-lg">아이디
					찾기</button>

				<div class="text-center mt-20 login-links">
					<a href="${pageContext.request.contextPath}/login/login.do">로그인
						화면으로 돌아가기</a>
				</div>
			</form>
		</div>
	</div>
</div>