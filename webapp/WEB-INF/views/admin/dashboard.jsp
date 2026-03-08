<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="admin-dashboard">

	<h2 class="admin-title">관리자 대시보드</h2>

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

</div>
</p>