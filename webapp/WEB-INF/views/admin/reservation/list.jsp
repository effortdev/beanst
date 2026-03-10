<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt"%>
<c:set var="pageUrl" value="${requestScope.pageUrl}" />

<div class="admin-reservation">
	<div class="titleBox">
		<h2 class="admin-title">예약 관리</h2>

		<div class="reservation-filter">

			<a
				href="${pageContext.request.contextPath}/admin/reservation/list.do"
				class="btn-filter"> 전체보기 </a> <a
				href="${pageContext.request.contextPath}/admin/reservation/active.do"
				class="btn-filter"> 예약취소 제외 </a>

		</div>
	</div>


	<table class="admin-table">

		<thead>
			<tr>
				<th>순번</th>
				<th>예약자</th>
				<th>예약번호</th>
				<th>객실</th>
				<th>체크인</th>
				<th>체크아웃</th>
				<th>인원</th>
				<th>금액</th>
				<th>상태</th>
				<th>관리</th>
			</tr>
		</thead>

		<tbody>

			<c:forEach var="r" items="${reservationList}" varStatus="status">

				<tr>

					<td>${(pageInfo.currentPage - 1) * pageInfo.boardLimit + status.count}</td>

					<td>${r.name}</td>
					
					<td>${r.reservationId}</td>

					<td>${r.roomName}</td>

					<td>${r.checkIn}</td>

					<td>${r.checkOut}</td>

					<td>성인 ${r.adultCount} / 아동 ${r.childCount}</td>

					<td><fmt:formatNumber value="${r.totalPrice}" pattern="#,###" />원</td>
					<td><c:choose>

							<c:when test="${r.status eq '1'}">
								<span class="status request">예약요청</span>
							</c:when>

							<c:when test="${r.status eq '2'}">
								<span class="status confirm">예약완료</span>
							</c:when>

							<c:when test="${r.status eq '3'}">
								<span class="status cancel-request">예약 취소 요청</span>
							</c:when>

							<c:otherwise>
								<span class="status cancel">예약취소</span>
							</c:otherwise>

						</c:choose></td>

					<td><c:if test="${r.status eq '1'}">

							<a class="btn-confirm"
								href="${pageContext.request.contextPath}/admin/reservation/confirm.do?reservation_id=${r.reservationId}"
								onclick="return confirm('예약을 확정하시겠습니까?')">예약확정</a>

						</c:if> <c:if test="${r.status eq '3'}">

							<a class="btn-cancel"
								href="${pageContext.request.contextPath}/admin/reservation/cancel.do?reservation_id=${r.reservationId}"
								onclick="return confirm('예약을 취소 처리하시겠습니까?')">취소승인</a>

						</c:if></td>

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