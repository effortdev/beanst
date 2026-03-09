<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<div class="admin-faq">
	<div class="titleBox">
		<h2 class="admin-title">FAQ 관리</h2>

		<div class="faq-top-bar">

			<a class="btn-add"
				href="${pageContext.request.contextPath}/admin/faq/write.do"> <span
				class="text">FAQ 등록</span>
			</a>

		</div>
	</div>


	<table class="admin-table">

		<thead>

			<tr>
				<th>번호</th>
				<th>카테고리</th>
				<th>질문</th>
				<th>상태</th>
				<th>정렬</th>
				<th>등록일</th>
				<th>관리</th>
			</tr>

		</thead>

		<tbody>

			<c:forEach var="f" items="${faqList}" varStatus="status">

				<tr>

					<td>${status.count}</td>

					<td>${f.category}</td>

					<td class="faq-title"><a
						href="${pageContext.request.contextPath}/admin/faq/edit.do?faq_no=${f.faqNo}">
							${f.question} </a></td>

					<td><c:choose>

							<c:when test="${f.status eq 'ACTIVE'}">
								<span class="status active">사용</span>
							</c:when>

							<c:otherwise>
								<span class="status inactive">숨김</span>
							</c:otherwise>

						</c:choose></td>

					<td>${f.sortOrder}</td>

					<td>${f.regDate}</td>

					<td><a
						href="${pageContext.request.contextPath}/admin/faq/delete.do?faq_no=${f.faqNo}"
						onclick="return confirm('삭제하시겠습니까?')" class="btn-delete"> 삭제 </a></td>

				</tr>

			</c:forEach>

		</tbody>

	</table>

	<div class="pagination">

		<!-- 이전 -->
		<c:choose>
			<c:when test="${pageInfo.currentPage > 1}">
				<a
					href="${pageContext.request.contextPath}/admin/faq/list.do?page=${pageInfo.currentPage - 1}">
					이전 </a>
			</c:when>
			<c:otherwise>
				<span class="disabled">이전</span>
			</c:otherwise>
		</c:choose>


		<!-- 페이지 번호 -->
		<c:forEach var="i" begin="${pageInfo.startPage}"
			end="${pageInfo.endPage}">

			<c:choose>

				<c:when test="${i eq pageInfo.currentPage}">
					<span class="active">${i}</span>
				</c:when>

				<c:otherwise>
					<a
						href="${pageContext.request.contextPath}/admin/faq/list.do?page=${i}">
						${i} </a>
				</c:otherwise>

			</c:choose>

		</c:forEach>


		<!-- 다음 -->
		<c:choose>
			<c:when test="${pageInfo.currentPage < pageInfo.maxPage}">
				<a
					href="${pageContext.request.contextPath}/admin/faq/list.do?page=${pageInfo.currentPage + 1}">
					다음 </a>
			</c:when>
			<c:otherwise>
				<span class="disabled">다음</span>
			</c:otherwise>
		</c:choose>

	</div>
</div>