<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt"%>
<%@ taglib prefix="fn" uri="jakarta.tags.functions"%>

<div class="page-content">
	<div class="container">
		<div class="board-header">
			<h3>Q&A</h3>
			<form class="search-box"
				action="${pageContext.request.contextPath}/qnaList.do" method="get">
				<input type="text" name="keyword" class="form-control"
					placeholder="검색어 입력" value="${keyword}">
				<button type="submit" class="btn btn-primary btn-sm">검색</button>
			</form>
		</div>

		<%-- 페이징: 검색어 유지용 쿼리 스트링 --%>
		<c:set var="queryKeyword" value="" />
		<c:if test="${not empty keyword}">
			<c:set var="queryKeyword" value="&keyword=${fn:escapeXml(keyword)}" />
		</c:if>

		<c:if test="${pi.listCount > 0}">
			<div class="paging-info">
				전체 <strong>${pi.listCount}</strong>건
				<c:if test="${pi.maxPage > 1}">
            (${(pi.currentPage - 1) * pi.boardLimit + 1} ~ ${pi.currentPage * pi.boardLimit > pi.listCount ? pi.listCount : pi.currentPage * pi.boardLimit}번째)
        </c:if>
			</div>
		</c:if>

		<div class="table-wrap">
			<table class="qna-table">
				<thead>
					<tr>
						<th>번호</th>
						<th class="text-left">제목</th>
						<th>작성자</th>
						<th>상태</th>
						<th>등록일</th>
					</tr>
				</thead>
				<tbody>
					<c:choose>
						<c:when test="${empty list}">
							<tr>
								<td colspan="5" class="text-center qna-empty">등록된 문의가 없습니다.</td>
							</tr>
						</c:when>
						<c:otherwise>
							<c:forEach var="q" items="${list}" varStatus="status">
								<tr>
									<td>${status.count}</td>
									<td class="text-left"><a
										href="${pageContext.request.contextPath}/qnaDetail.do?no=${q.qnaNo}">${q.title}</a>
									</td>
									<td>${fn:substring(q.memberId, 0, 3)}***</td>
									<td><span class="badge badge-${fn:toLowerCase(q.status)}">${q.status == 'WAITING' ? '답변대기' : '답변완료'}</span></td>
									<td><fmt:formatDate value="${q.regDate}"
											pattern="yyyy-MM-dd" /></td>
								</tr>
							</c:forEach>
						</c:otherwise>
					</c:choose>
				</tbody>
			</table>
		</div>


		<c:if test="${pi.maxPage > 1}">
			<div class="pagination">
				<%-- 이전: 1페이지면 비활성(링크 없음), 아니면 활성 링크 --%>
				<c:choose>
					<c:when test="${pi.currentPage > 1}">
						<a
							href="${pageContext.request.contextPath}/qnaList.do?page=${pi.currentPage - 1}${queryKeyword}"
							class="page-prev" title="이전">&laquo;</a>
					</c:when>
					<c:otherwise>
						<span class="disabled" title="이전">&laquo;</span>
					</c:otherwise>
				</c:choose>
				<c:forEach begin="${pi.startPage}" end="${pi.endPage}" var="p">
					<c:choose>
						<c:when test="${p == pi.currentPage}">
							<span class="active">${p}</span>
						</c:when>
						<c:otherwise>
							<a
								href="${pageContext.request.contextPath}/qnaList.do?page=${p}${queryKeyword}">${p}</a>
						</c:otherwise>
					</c:choose>
				</c:forEach>
				<%-- 다음: 마지막 페이지면 비활성(링크 없음), 아니면 활성 링크 --%>
				<c:choose>
					<c:when test="${pi.currentPage < pi.maxPage}">
						<a
							href="${pageContext.request.contextPath}/qnaList.do?page=${pi.currentPage + 1}${queryKeyword}"
							class="page-next" title="다음">&raquo;</a>
					</c:when>
					<c:otherwise>
						<span class="disabled" title="다음">&raquo;</span>
					</c:otherwise>
				</c:choose>
			</div>
		</c:if>


		<c:if
			test="${not empty sessionScope.loginMember && sessionScope.loginMember.role != 'ADMIN'}">
			<div class="text-right mb-10">
				<a href="${pageContext.request.contextPath}/qnaWriteForm.do"
					class="btn btn-primary btn-sm">문의 작성</a>
			</div>
		</c:if>
	</div>
</div>