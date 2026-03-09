<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="admin-qna-detail">

	<h2 class="admin-title">회원 정보 수정</h2>

	<div class="qna-meta">
		<p>
			<strong>아이디:</strong> ${user.userId}
		</p>
		<p>
			<strong>현재 상태:</strong>
			<c:choose>
				<c:when test="${user.status == '1'}">정상 회원</c:when>
				<c:when test="${user.status == '2'}">탈퇴 요청 접수됨</c:when>
				<c:when test="${user.status == '3'}">탈퇴 처리 완료</c:when>
				<c:otherwise>알 수 없음</c:otherwise>
			</c:choose>
		</p>
	</div>

	<form
		action="${pageContext.request.contextPath}/admin/memberManage.do?action=update"
		method="post">

		<input type="hidden" name="user_id" value="${user.userId}">

		<div class="question-box">
			<div style="margin-bottom: 20px;">
				<h3 style="margin-bottom: 10px; font-size: 15px;">이름</h3>
				<input type="text" name="name" value="${user.name}"
					style="width: 100%; padding: 12px; border: 1px solid #ddd; border-radius: 6px; box-sizing: border-box;"
					required>
			</div>

			<div style="margin-bottom: 20px;">
				<h3 style="margin-bottom: 10px; font-size: 15px;">이메일</h3>
				<input type="email" name="email" value="${user.email}"
					style="width: 100%; padding: 12px; border: 1px solid #ddd; border-radius: 6px; box-sizing: border-box;"
					required>
			</div>

			<div style="margin-bottom: 20px;">
				<h3 style="margin-bottom: 10px; font-size: 15px;">연락처</h3>
				<input type="text" name="phone" value="${user.phone}"
					style="width: 100%; padding: 12px; border: 1px solid #ddd; border-radius: 6px; box-sizing: border-box;"
					required>
			</div>

			<div style="display: flex; gap: 20px;">
				<div style="flex: 1;">
					<h3 style="margin-bottom: 10px; font-size: 15px;">권한 (Role)</h3>
					<select name="role"
						style="width: 100%; padding: 12px; border: 1px solid #ddd; border-radius: 6px; box-sizing: border-box;">
						<option value="USER" ${user.role == 'USER' ? 'selected' : ''}>일반
							회원 (USER)</option>
						<option value="ADMIN" ${user.role == 'ADMIN' ? 'selected' : ''}>관리자
							(ADMIN)</option>
					</select>
				</div>
				<div style="flex: 1;">
					<h3 style="margin-bottom: 10px; font-size: 15px;">계정 상태</h3>
					<select name="status"
						style="width: 100%; padding: 12px; border: 1px solid #ddd; border-radius: 6px; box-sizing: border-box;">
						<option value="1" ${user.status == '1' ? 'selected' : ''}>정상
							(1)</option>
						<option value="2" ${user.status == '2' ? 'selected' : ''}>탈퇴요청
							(2)</option>
						<option value="3" ${user.status == '3' ? 'selected' : ''}>탈퇴완료
							(3)</option>
					</select>
				</div>
			</div>
		</div>

		<div class="answer-buttons">
			<button type="submit" class="btn-save">수정완료</button>
			<a href="${pageContext.request.contextPath}/admin/memberManage.do"
				class="btn-list">목록으로 돌아가기</a>
		</div>

	</form>

</div>