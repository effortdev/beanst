<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="admin-qna">
	<div class="titleBox">
		<h2 class="admin-title">회원 관리</h2>
		<div class="qna-filter">
			<a href="${pageContext.request.contextPath}/admin/memberManage.do"
				style="${empty param.filter ? '' : ''}">전체
				회원보기</a> <a
				href="${pageContext.request.contextPath}/admin/memberManage.do?filter=withdraw"
				style="${param.filter == 'withdraw' ? '' : ''}">탈퇴요청
				회원보기</a>
		</div>
	</div>





	<table class="admin-table">
		<thead>
			<tr>
				<th>아이디</th>
				<th>이름</th>
				<th>이메일</th>
				<th>연락처</th>
				<th>권한</th>
				<th>상태</th>
				<th>관리</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="user" items="${userList}">
				<tr>
					<td>${user.userId}</td>
					<td>${user.name}</td>
					<td>${user.email}</td>
					<td>${user.phone}</td>
					<td>${user.role}</td>

					<td><c:choose>
							<c:when test="${user.status == '1'}">
								<span class="status done">정상</span>
							</c:when>
							<c:when test="${user.status == '2'}">
								<span class="status waiting">탈퇴요청</span>
							</c:when>
							<c:when test="${user.status == '3'}">
								<span class="status" style="background: #f1f1f1; color: #888;">탈퇴완료</span>
							</c:when>
							<c:otherwise>
								<span class="status">상태미상</span>
							</c:otherwise>
						</c:choose></td>

					<td>
						<div style="display: flex; gap: 5px; justify-content: center; align-items: center;">
							<a
								href="${pageContext.request.contextPath}/admin/memberManage.do?action=edit&userId=${user.userId}"
								class="btn-list">수정</a>

							<c:if test="${user.status == '2'}">
								<form
									action="${pageContext.request.contextPath}/admin/memberManage.do?action=approveWithdraw"
									method="post" style="margin: 0;"
									onsubmit="return confirm('정말 탈퇴 승인하시겠습니까?');">
									<input type="hidden" name="userId" value="${user.userId}">
									<button type="submit" class="btn-delete"
										style="padding: 6px 12px; font-size: 14px; border: none; cursor: pointer;">탈퇴승인</button>
								</form>
							</c:if>
						</div>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

</div>

<c:if test="${param.msg == 'update'}">
	<script>
		alert('회원 정보 수정이 완료되었습니다.');
		history.replaceState({}, null, location.pathname);
	</script>
</c:if>
<c:if test="${param.msg == 'withdraw'}">
	<script>
		alert('회원 탈퇴 승인이 정상적으로 처리되었습니다.');
		history.replaceState({}, null, location.pathname);
	</script>
</c:if>