<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt"%>

<div class="admin-room">
	<div class="titleBox">
		<h2 class="admin-title">객실 상세 관리</h2>
	</div>


	<table class="admin-table">

		<thead>
			<tr>
				<th>번호</th>
				<th>객실명</th>
				<th>기본인원</th>
				<th>최대인원</th>
				<th>기본요금</th>
				<th>추가요금</th>
				<th>관리</th>
			</tr>
		</thead>

		<tbody>

			<c:forEach var="r" items="${roomList}" varStatus="status">

				<tr>

					<td>${(pageInfo.currentPage - 1) * pageInfo.boardLimit + status.count}</td>

					<td class="room-name">${r.roomName}</td>

					<td>${r.baseCapacity}</td>

					<td>${r.maxCapacity}</td>

					<td><fmt:formatNumber value="${r.basePrice}" pattern="#,###" />원
						/ 1박</td>

					<td><fmt:formatNumber value="${r.extraCharge}" pattern="#,###" />원
						/ 명</td>

					<td><a class="btn-edit"
						href="${pageContext.request.contextPath}/admin/room/edit.do?room_id=${r.roomId}">
							수정 </a></td>

				</tr>

			</c:forEach>

		</tbody>

	</table>
	<div class="pagination">


		<c:choose>
			<c:when test="${pageInfo.currentPage > 1}">
				<a
					href="${pageContext.request.contextPath}/admin/room/detailList.do?page=${pageInfo.currentPage - 1}">이전</a>
			</c:when>
			<c:otherwise>
				<a class="disabled">이전</a>
			</c:otherwise>
		</c:choose>


		<c:forEach var="i" begin="${pageInfo.startPage}"
			end="${pageInfo.endPage}">
			<c:choose>
				<c:when test="${i eq pageInfo.currentPage}">
					<span class="active">${i}</span>
				</c:when>
				<c:otherwise>
					<a
						href="${pageContext.request.contextPath}/admin/room/detailList.do?page=${i}">
						${i} </a>
				</c:otherwise>
			</c:choose>
		</c:forEach>


		<c:choose>
			<c:when test="${pageInfo.currentPage < pageInfo.maxPage}">
				<a
					href="${pageContext.request.contextPath}/admin/room/detailList.do?page=${pageInfo.currentPage + 1}">다음</a>
			</c:when>
			<c:otherwise>
				<a class="disabled">다음</a>
			</c:otherwise>
		</c:choose>

	</div>
</div>