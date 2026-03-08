<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<div class="admin-dashboard">

	<h2 class="admin-title">관리자 대시보드</h2>

	<!-- 카드 -->
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


	<div class="dashboard-charts">

		<!-- 예약 그래프 -->
		<div class="dashboard-chart">

			<h3>최근 7일 예약 현황</h3>

			<canvas id="reservationChart"></canvas>

		</div>


		<!-- 객실 점유율 -->
		<div class="dashboard-chart">

			<h3>객실 점유율</h3>

			<canvas id="roomChart"></canvas>

		</div>

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

				<c:forEach var="r" items="${recentReservations}" varStatus="status">
					<tr>
						<td>${status.count}</td>
						<td>${r.userId}</td>
						<td>${r.roomName}</td>
						<td>${r.checkIn}</td>
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
					</tr>
				</c:forEach>

			</tbody>

		</table>

	</div>


	<!-- 최근 문의 -->

	<div class="dashboard-table">

		<h3>최근 문의</h3>

		<table>

			<thead>
				<tr>
					<th>번호</th>
					<th>회원</th>
					<th>제목</th>
					<th>상태</th>
				</tr>
			</thead>

			<tbody>

				<c:forEach var="q" items="${recentQna}" varStatus="status">
					<tr>
						<td>${status.count}</td>
						<td>${q.userId}</td>
						<td>${q.title}</td>
						<td><c:choose>

								<c:when test="${q.status eq 'WAITING'}">
									<span class="status waiting">답변대기</span>
								</c:when>

								<c:otherwise>
									<span class="status done">답변완료</span>
								</c:otherwise>

							</c:choose></td>
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
	type: 'line',
	data: {
		labels: ${chartLabels},
		datasets: [{
			label: '예약 수',
			data: ${chartData},
			borderColor: '#3498db',
			backgroundColor: 'rgba(52,152,219,0.2)',
			tension: 0.4,
			fill:true
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

const roomCtx = document.getElementById('roomChart');

new Chart(roomCtx, {
	type: 'doughnut',
	data: {
		labels: ${roomChartLabels},
		datasets: [{
			data: ${roomChartData},
			backgroundColor: [
				'#3498db',
				'#2ecc71',
				'#f39c12',
				'#9b59b6'
			]
		}]
	},
	options:{
		plugins:{
			legend:{
				position:'bottom'
			}
		}
	}
});

</script>