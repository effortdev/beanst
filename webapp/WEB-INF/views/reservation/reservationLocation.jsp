<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

	<body>

		<div class="wrap">
			<section id="reservationLocation">
				<div class="top_title fade-up">
					<h2 class="en">Location</h2>
					<p class="text">당신의 하루가 머무는 가장 빛나는 공간, Vinst Hotel에 초대합니다.</p>
				</div>
				<div class="Locationposition">
					<div class="LocationLeft">
						<div class="LocationMap">
							<img src="${pageContext.request.contextPath}/assets/images/reservation/hotel-Local.png"
								alt="hotel_Location" class="mapImg">
							<div class="overlay">
								<a href="#" class="viewBtn en">Location</a>
							</div>
						</div>

						<div class="Locationadress">
							<h5>주소 :</h5>
							<p> 경기도 안양시 만안구 안양로314번길 10 광창빌딩 3층</p>
							<br>
							<p>지하철 1호선 안양역 → 지하상가 13번 출구</p>
						</div>
					</div>

					<div class="LocationExtra">
						
						<div class="localhotel">
						<h4>오시는 길</h4>
							</div>

						<h4>도보 이용</h4>
						
							<p>안양역 1번 출구로 나오신 후</p>
							<p>→ 안양역 로터리 방향 직진</p>
							<p>→ 안양일번가 거리 지나 약 5분 도보 이동</p>
							<p>→ 안양로314번길 방면으로 우회전</p>
							<p>→ 광창빌딩 3층 도착 (총 소요시간 약 7~10분)</p>
						</p><br><br>
						<h4>버스 이용</h4>
							<p>안양역 앞 버스정류장에서</p>
							<p>→ 3번, 5-2번, 11-3번 버스 탑승</p>
							<p>→ 안양초등학교 또는 만안구청 정류장 하차 (1정거장, 약 3~5분 소요)</p>
							<p>→ 안양로314번길 방향 도보 3분 이동</p>
							<p>→ 광창빌딩 3층 도착</p>
						</p>					
					</div>
				</div>
			</section>
		</div>
		<script src="${pageContext.request.contextPath}/assets/js/reservationLocation.js"></script>
	</body>