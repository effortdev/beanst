<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>



<div class="admin-facility">
	<div class="facility-header">
		<h2>호텔 시설 관리</h2>
		<a
			href="${pageContext.request.contextPath}/admin/facility/insertForm.do"
			class="facility-add-btn"> + 시설 추가 </a>
	</div>

	<table class="facility-table">
		<thead>
			<tr>
				<th>ID</th>
				<th>타입</th>
				<th>시설명</th>
				<th>위치</th>
				<th>운영시간</th>
				<th>관리</th>
			</tr>
		</thead>
		<tbody>
			<c:if test="${empty facilityList}">
				<tr>
					<td colspan="6" class="empty">등록된 시설이 없습니다.</td>
				</tr>
			</c:if>

			<c:forEach var="f" items="${facilityList}" varStatus="status">
				<tr>
					<td>${(pageInfo.currentPage - 1) * pageInfo.boardLimit + status.count}</td>
					<td>${f.facilityType}</td>
					<td>${f.facilityName}</td>
					<td>${f.location}</td>
					<td>${f.openTime}</td>
					<td><a
						href="${pageContext.request.contextPath}/admin/facility/updateForm.do?id=${f.facilityId}"
						class="facility-btn-edit">수정</a> <a
						href="${pageContext.request.contextPath}/admin/facility/delete.do?id=${f.facilityId}"
						onclick="return confirm('정말 삭제하시겠습니까?');"
						class="facility-btn-delete">삭제</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="pagination">

		<!-- 이전 -->
		<c:choose>
			<c:when test="${pageInfo.currentPage > 1}">
				<a
					href="${pageContext.request.contextPath}/admin/facility/list.do?page=${pageInfo.currentPage - 1}">이전</a>
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
					<a
						href="${pageContext.request.contextPath}/admin/facility/list.do?page=${i}">
						${i} </a>
				</c:otherwise>
			</c:choose>
		</c:forEach>

		<!-- 다음 -->
		<c:choose>
			<c:when test="${pageInfo.currentPage < pageInfo.maxPage}">
				<a
					href="${pageContext.request.contextPath}/admin/facility/list.do?page=${pageInfo.currentPage + 1}">다음</a>
			</c:when>
			<c:otherwise>
				<a class="disabled">다음</a>
			</c:otherwise>
		</c:choose>

	</div>
</div>