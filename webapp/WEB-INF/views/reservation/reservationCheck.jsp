<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.time.LocalDate"%>
<%@ page import="java.time.temporal.ChronoUnit"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<body>

	<div class="wrap">

		<!-- 예약이 있는 경우 -->
		<section id="firstSection"
			class="checkMainSection ${not empty reservationList ? 'visible' : 'hidden'}">

			<!-- 상단 타이틀 -->
			<div class="top_title fade-up">

				<h2 class="en">My Reservation</h2>

				<p class="text">고객님의 소중한 예약 정보입니다.</p>

			</div>

			<!-- 🔵 취소 FORM 시작 -->
			<form
				action="${pageContext.request.contextPath}/reservation/cancel.do"
				method="post" onsubmit="return cancelCheck();">

				<!-- 예약 카드 전체 영역 -->
				<div class="reservationContainer">

					<c:forEach var="reservation" items="${reservationList}">

						<div class="checkTop">

							<!-- 체크박스 -->
							<input type="checkbox" name="reservationIds"
								value="${reservation.reservationId}">

							<!-- alert에 표시할 데이터 -->
							<input type="hidden" name="name_${reservation.reservationId}"
								value="${reservation.name}"> <input type="hidden"
								name="price_${reservation.reservationId}"
								value="${reservation.totalPrice}">

							<table class="reservation-table">

								<tr>
									<th class="checkLabel">예약상태</th>
									<td class="checkvalue">${reservation.status}</td>
								</tr>
								<tr>
									<th class="checkLabel">예약자</th>
									<td class="checkvalue">${reservation.name}</td>
								</tr>

								<tr>
									<th class="checkLabel">예약번호</th>
									<td class="checkvalue">${reservation.reservationId}</td>
								</tr>

								<tr>
									<th class="checkLabel">객실</th>
									<td class="checkvalue">${reservation.roomName}</td>
								</tr>

								<tr>
									<th class="checkLabel">체크인</th>
									<td class="checkvalue">${reservation.checkIn}</td>
								</tr>

								<tr>
									<th class="checkLabel">체크아웃</th>
									<td class="checkvalue">${reservation.checkOut}</td>
								</tr>

								<tr>
									<th class="checkLabel">숙박</th>

									<td class="checkvalue"><c:set var="checkInDate"
											value="${reservation.checkIn}" /> <c:set var="checkOutDate"
											value="${reservation.checkOut}" /> <%
 String checkInStr = (String) pageContext.findAttribute("checkInDate");
 String checkOutStr = (String) pageContext.findAttribute("checkOutDate");

 long nights = 0;

 if (checkInStr != null && checkOutStr != null) {

 	LocalDate checkIn = LocalDate.parse(checkInStr);
 	LocalDate checkOut = LocalDate.parse(checkOutStr);

 	nights = ChronoUnit.DAYS.between(checkIn, checkOut);
 }

 pageContext.setAttribute("nights", nights);
 %> <%=pageContext.getAttribute("nights")%>박</td>

								</tr>

								<tr>
									<th class="checkLabel">성인</th>
									<td class="checkvalue">${reservation.adultCount}</td>
								</tr>

								<tr>
									<th class="checkLabel">아이</th>
									<td class="checkvalue">${reservation.childCount}</td>
								</tr>

								<tr>
									<th class="checkLabel">총 금액</th>
									<td class="checkvalue">${reservation.totalPrice}원</td>
								</tr>

							</table>

							<button type="button" class="change-btn">예약 변경</button>

						</div>

					</c:forEach>

				</div>

				<!-- 하단 영역 -->
				<div class="checkBottom">

					<div class="refund">

						<p>예약 취소는 체크인 3일 전까지 가능하며, 해당 기간 내 취소 시 전액 환불됩니다.</p>
						<p>체크인 2일 전부터 당일 취소 시에는 1박 요금이 취소 수수료로 부과됩니다.</p>
						<p>예약 후 별도의 취소 없이 체크인하지 않을 경우(No-show)에도 1박 요금이 부과될 수 있습니다.</p>

					</div>

					<button type="submit" class="cancel-btn">예약 취소 요청</button>

				</div>

			</form>
			<!-- 🔵 취소 FORM 끝 -->

		</section>


		<!-- 예약 없는 경우 -->
		<section id="secondSection"
			class="checkMainSection ${empty reservationList ? 'visible' : 'hidden'}">

			<div class="no-reservation-message">${userId} 고객님의 예약정보가 없습니다.

			</div>

		</section>

	</div>


	<!-- 🔵 JS 파일 없이 내부 스크립트만 사용 -->
	<script>
		function cancelCheck() {

			const checked = document
					.querySelectorAll("input[name='reservationIds']:checked");

			if (checked.length === 0) {

				alert("취소할 예약을 선택해주세요.");
				return false;

			}

			let message = "";

			checked.forEach(function(box) {

				const id = box.value;

				const name = document.querySelector("input[name='name_" + id
						+ "']").value;

				const price = document.querySelector("input[name='price_" + id
						+ "']").value;

				message += "\n" + "예약자: " + name + "\n" + "예약번호: " + id + "\n"
						+ "총 금액: " + price + "\n" + "------------------------";

			});

			message += "\n취소하시겠습니까?";

			return confirm(message);

		}
	</script>

</body>