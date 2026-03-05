<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<div class="wrap" style="background: #FAF8F5">
	<section id="main_hero">
		<div class="swiper heroSwiper">
			<div class="swiper-wrapper">
				<div class="hero-content">
					<h1 class="brand-title en fade-up">VIN'st Hotel</h1>

					<div class="brand-divider fade-up" aria-hidden="true">◆</div>

					<h2 class="brand-subtitle ko fade-up">도심 속에서 만나는 프리미엄 휴식, 당신의
						하루를 더 특별하게</h2>

					<p class="brand-desc ko fade-up">
						<span class="title en">VIN'st Hotel</span>는 도시의 중심에서 자연의 여유를 느낄 수
						있는 프리미엄 호텔입니다.
					</p>

					<p class="brand-desc-sub ko fade-up">감각적인 인테리어와 편안한 침구, 그리고 섬세한
						서비스로 여행의 피로를 완벽하게 풀어드립니다.</p>
					<p class="brand-desc-sub ko fade-up">다양한 객실 타입과 스위트룸, 라운지 &amp;
						편의시설까지 머무는 순간이 곧 쉼이 되도록 준비했습니다.</p>
				</div>

				<div class="swiper-slide slide1"></div>

				<div class="swiper-slide slide2"></div>

				<div class="swiper-slide slide3"></div>

			</div>
		</div>
	</section>

	<section id="about_Room">
		<div class="top_title fade-up">
			<h2 class="en">Room Types</h2>
			<p class="text">스탠다드부터 스위트까지, 품격 있는 선택의 폭을 제공합니다.</p>
		</div>
		<div class="swiper roomListSwiper">
			<div class="swiper-wrapper">
				<div class="swiper-slide slide1">
					<div class="imgBox">
						<img
							src="${pageContext.request.contextPath}/assets/images/main/roomList/roomList_02.jpg"
							alt="roomList_02">
					</div>
					<div class="textBox">
						<p class="title en">City View Deluxe</p>
						<p class="text">도심의 야경을 감각적으로 담아낸 세련된 공간</p>
					</div>
				</div>
				<div class="swiper-slide slide2">
					<div class="imgBox">
						<img
							src="${pageContext.request.contextPath}/assets/images/main/roomList/roomList_03.jpg"
							alt="roomList_03">
					</div>
					<div class="textBox">
						<p class="title en">Skyline Corner Suite</p>
						<p class="text">전면 코너 창으로 파노라마 시티뷰를 감상할 수 있는 특별한 객실</p>
					</div>
				</div>
				<div class="swiper-slide slide3">
					<div class="imgBox">
						<img
							src="${pageContext.request.contextPath}/assets/images/main/roomList/roomList_04.jpg"
							alt="roomList_04">
					</div>
					<div class="textBox">
						<p class="title en">Classic Signature</p>
						<p class="text">안락함과 세련미를 동시에 담은 클래식 스타일 객실</p>
					</div>
				</div>
				<div class="swiper-slide slide4">
					<div class="imgBox">
						<img
							src="${pageContext.request.contextPath}/assets/images/main/roomList/roomList_05.jpg"
							alt="roomList_05">
					</div>
					<div class="textBox">
						<p class="title en">Garden Terrace Suite</p>
						<p class="text">자연 채광과 테라스 감성이 어우러진 여유로운 휴식 공간</p>
					</div>
				</div>
				<div class="swiper-slide slide5">
					<div class="imgBox">
						<img
							src="${pageContext.request.contextPath}/assets/images/main/roomList/roomList_06.jpg"
							alt="roomList_06">
					</div>
					<div class="textBox">
						<p class="title en">White Twin Deluxe</p>
						<p class="text">밝고 깔끔한 화이트 톤과 트윈 베드가 조화를 이루는 편안한 객실</p>
					</div>
				</div>
			</div>

			<div class="swiper-button-prev"></div>
			<div class="swiper-button-next"></div>
			<div class="swiper-pagination"></div>
		</div>
	</section>

	<section id="about_Hotel">
		<div class="top_title  fade-up">
			<h2 class="en">Hotel Experience</h2>
			<p class="text">품격 있는 다이닝, 여유로운 라운지, 피트니스와 스파 등 다양한 부대시설을 통해 완성된
				휴식을 제공합니다.</p>
		</div>
		<div class="experienceSwiper swiper">
			<div class="swiper-wrapper">

				<c:forEach var="f" items="${facilityList}">

					<div class="swiper-slide">
						<div class="list_item">

							<div class="imgBox">

								<img src="${f.imagePath}?v=${f.facilityId}"
									alt="${f.facilityName}">

								<div class="overlay">
									<a href="facilityDetail.do?id=${f.facilityId}"
										class="viewBtn en"> VIEW MORE </a>
								</div>

							</div>

							<div class="textBox">
								<p class="text en">${f.facilityName}</p>
							</div>

						</div>
					</div>

				</c:forEach>

			</div>
		</div>
	</section>
</div>
<script src="${pageContext.request.contextPath}/assets/js/main.js"></script>
