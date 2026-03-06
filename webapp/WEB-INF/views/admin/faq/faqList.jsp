<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<div class="admin-faq">

	<h2 class="admin-title">FAQ 관리</h2>

	<div class="faq-top-bar">

		<a class="btn-add"
			href="${pageContext.request.contextPath}/admin/faq/write.do">
			<p class="text">FAQ 등록</p>
		</a>

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
			</tr>

		</thead>

		<tbody>

			<c:forEach var="f" items="${faqList}">

				<tr>

					<td>${f.faqNo}</td>

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
						onclick="return confirm('삭제하시겠습니까?')"> 삭제 </a></td>

				</tr>

			</c:forEach>

		</tbody>

	</table>

</div>