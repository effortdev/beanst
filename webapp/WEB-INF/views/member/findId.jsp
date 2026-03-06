<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<div class="join-container">
	<div class="join-wrap">
		<h2 class="join-title">Find ID</h2>

		<c:if test="${not empty errorMsg}">
			<div class="text-danger"
				style="text-align: center; margin-bottom: 20px; color: #dc3545;">
				${errorMsg}</div>
		</c:if>

		<form action="${pageContext.request.contextPath}/member/findId.do"
			method="post">
			<div class="form-group">
				<label>이름</label> <input type="text" name="name"
					class="form-control" required placeholder="가입 시 입력한 이름">
			</div>
			<div class="form-group" style="margin-bottom: 30px;">
				<label>이메일</label> <input type="email" name="email"
					class="form-control" required placeholder="가입 시 입력한 이메일">
			</div>

			<button type="submit" class="btn btn-primary join-btn">아이디
				찾기</button>
		</form>
	</div>
</div>