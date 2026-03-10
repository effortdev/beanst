<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<c:set var="pageUrl" value="${pageContext.request.requestURI}" />

<div class="admin-qna">
	<div class="titleBox">
		<h2 class="admin-title">1:1 문의 관리</h2>

		<div class="qna-filter">

			<a href="${pageContext.request.contextPath}/admin/qna/list.do">
				전체보기 </a> <a
				href="${pageContext.request.contextPath}/admin/qna/waiting.do">
				답변대기만 보기 </a>

		</div>
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

					<td>${(pageInfo.currentPage - 1) * pageInfo.boardLimit + status.count}</td>

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

	<div class="pagination">

		<!-- 이전 -->
		<c:choose>
			<c:when test="${pageInfo.currentPage > 1}">
				<a
					href="${pageContext.request.contextPath}${pageUrl}?page=${pageInfo.currentPage - 1}">이전</a>
			</c:when>
			<c:otherwise>
				<a class="disabled">이전</a>
			</c:otherwise>
		</c:choose>

		<!-- 페이지 -->
		<c:forEach var="i" begin="${pageInfo.startPage}"
			end="${pageInfo.endPage}">
			<c:choose>
				<c:when test="${i eq pageInfo.currentPage}">
					<span class="active">${i}</span>
				</c:when>
				<c:otherwise>
					<a href="${pageContext.request.contextPath}${pageUrl}?page=${i}">${i}</a>
				</c:otherwise>
			</c:choose>
		</c:forEach>

		<!-- 다음 -->
		<c:choose>
			<c:when test="${pageInfo.currentPage < pageInfo.maxPage}">
				<a
					href="${pageContext.request.contextPath}${pageUrl}?page=${pageInfo.currentPage + 1}">다음</a>
			</c:when>
			<c:otherwise>
				<a class="disabled">다음</a>
			</c:otherwise>
		</c:choose>

	</div>

</div>