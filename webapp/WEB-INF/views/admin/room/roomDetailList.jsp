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

					<td>${status.count}</td>

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

</div>


