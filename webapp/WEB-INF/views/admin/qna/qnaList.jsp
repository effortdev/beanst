<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<div class="admin-qna">

	<h2 class="admin-title">1:1 문의 관리</h2>

	<div class="qna-filter">

		<a href="${pageContext.request.contextPath}/admin/qna/list.do">
			전체보기 </a> <a
			href="${pageContext.request.contextPath}/admin/qna/waiting.do">
			답변대기만 보기 </a>

	</div>

	<table class="admin-table">

		<thead>
			<tr>
				<th>번호</th>
				<th>작성자</th>
				<th>제목</th>
				<th>상태</th>
				<th>조회수</th>
				<th>작성일</th>
			</tr>
		</thead>

		<tbody>

			<c:forEach var="q" items="${qnaList}" varStatus="status">

				<tr>

					<td>${status.count}</td>

					<td>${q.userId}</td>

					<td class="title"><a
						href="${pageContext.request.contextPath}/admin/qna/detail.do?qna_no=${q.qnaNo}">
							${q.title} </a></td>

					<td><c:choose>

							<c:when test="${q.status eq 'WAITING'}">
								<span class="status waiting">답변대기</span>
							</c:when>

							<c:otherwise>
								<span class="status done">답변완료</span>
							</c:otherwise>

						</c:choose></td>

					<td>${q.viewCount}</td>

					<td>${q.regDate}</td>

				</tr>

			</c:forEach>

		</tbody>

	</table>

</div>