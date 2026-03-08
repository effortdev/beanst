<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<div class="admin-dashboard">

	<h2 class="admin-title">관리자 대시보드</h2>

	<!-- 카드 영역 -->
	<div class="dashboard-cards">

		<div class="card">
			<h3>오늘 체크인</h3>
			<p>${todayCheckIn}</p>
		</div>

		<div class="card">
			<h3>오늘 체크아웃</h3>
			<p>${todayCheckOut}</p>
		</div>

		<div class="card">
			<h3>현재 투숙</h3>
			<p>${currentStay}</p>
		</div>

		<div class="card">
			<h3>예약 요청</h3>
			<p>${reservationRequest}</p>
		</div>

		<div class="card">
			<h3>취소 요청</h3>
			<p>${cancelRequest}</p>
		</div>

		<div class="card">
			<h3>미답변 문의</h3>
			<p>${qnaWaiting}</p>
		</div>

	</div>


	<!-- 차트 영역 -->
	<div class="dashboard-chart">
		<h3>예약 통계</h3>
		<canvas id="reservationChart"></canvas>
	</div>


	<!-- 최근 예약 -->
	<div class="dashboard-table">

		<h3>최근 예약</h3>

		<table>
			<thead>
				<tr>
					<th>예약번호</th>
					<th>회원</th>
					<th>객실</th>
					<th>체크인</th>
					<th>상태</th>
				</tr>
			</thead>

			<tbody>
				<c:forEach var="r" items="${recentReservations}">
					<tr>
						<td>${r.reservationId}</td>
						<td>${r.memberId}</td>
						<td>${r.roomName}</td>
						<td>${r.checkIn}</td>
						<td>${r.status}</td>
					</tr>
				</c:forEach>
			</tbody>

		</table>

	</div>

</div>

<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
<script>

const ctx = document.getElementById('reservationChart');

new Chart(ctx, {
    type: 'bar',
    data: {
        labels: ['체크인', '체크아웃', '투숙중', '예약요청', '취소요청'],
        datasets: [{
            label: '호텔 현황',
            data: [
                ${todayCheckIn},
                ${todayCheckOut},
                ${currentStay},
                ${reservationRequest},
                ${cancelRequest}
            ],
            backgroundColor: [
                '#4CAF50',
                '#2196F3',
                '#FF9800',
                '#9C27B0',
                '#F44336'
            ]
        }]
    },
    options: {
        responsive: true,
        plugins: {
            legend: {
                display:false
            }
        }
    }
});

</script>