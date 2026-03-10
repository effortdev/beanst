<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<div class="wrap hero">
	<section id="main_hero">
		<div class="swiper heroSwiper">
			<div class="swiper-wrapper">
				<div class="hero-content">
					<h1 class="brand-title en fade-up">VIN'st Hotel</h1>

					<div class="brand-divider fade-up" aria-hidden="true">◆</div>

					<h2 class="brand-subtitle ko fade-up">일상을 잠시 내려놓는 곳, 당신만의 완벽한
						쉼</h2>

					<p class="brand-desc ko fade-up">
						<span class="title en">VIN'st Hotel</span>도심 한복판에서 자연의 고요함을 만나다.
					</p>

					<p class="brand-desc-sub ko fade-up">정제된 공간과 세심한 서비스로, 머무는 모든
						순간이 특별한 기억이 되는 프리미엄 호텔입니다.</p>
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
			<div class="top_textBox">
				<p class="text">스탠다드부터 스위트까지, 품격 있는 선택의 폭을 제공합니다.</p>
				<span><a
					href="${pageContext.request.contextPath}/roomList.do"> 객실 전체 보기
				</a></span>
			</div>
		</div>
		<div class="swiper roomListSwiper">

			<div class="swiper-wrapper">

				<c:forEach var="room" items="${roomList}">
					<div class="swiper-slide">
						<div class="list_item">

							<div class="imgBox">

								<a href="roomDetail.do?id=${room.room_id}" class="viewBtn en">

									<c:choose>

										<c:when test="${not empty room.image_path}">
											<img src="${room.image_path}" alt="${room.room_name}"
												onerror="this.src='${pageContext.request.contextPath}/assets/images/default/no_image.png'">
										</c:when>

										<c:otherwise>
											<img
												src="${pageContext.request.contextPath}/assets/images/default/no_image.png"
												alt="no image">
										</c:otherwise>

									</c:choose>

								</a>

							</div>

							<div class="textBox">
								<p class="text en">${room.room_name}</p>
								<p class="text">${room.room_location}</p>
							</div>

						</div>
					</div>
				</c:forEach>
			</div>
			<div class="swiper-button-prev"></div>
			<div class="swiper-button-next"></div>
			<div class="swiper-pagination"></div>
		</div>
	</section>


	<section id="about_Hotel">
		<div class="top_title  fade-up">
			<h2 class="en">Hotel Experience</h2>
			<div class="top_textBox">
				<p class="text">품격 있는 다이닝, 여유로운 라운지, 피트니스와 스파 등 다양한 부대시설을 통해 완성된
					휴식을 제공합니다.</p>
				<span><a
					href="${pageContext.request.contextPath}/facilityList.do"> 시설
						전체 보기 </a></span>
			</div>
		</div>
		<div class="experienceSwiper swiper">
			<div class="swiper-wrapper">

				<c:forEach var="f" items="${facilityList}">

					<div class="swiper-slide">
						<div class="list_item">

							<div class="imgBox">

								<c:choose>

									<c:when test="${not empty f.imagePath}">
										<img src="${f.imagePath}?v=${f.facilityId}"
											alt="${f.facilityName}"
											onerror="this.src='${pageContext.request.contextPath}/assets/images/default/no_image.png'">
									</c:when>

									<c:otherwise>
										<img
											src="${pageContext.request.contextPath}/assets/images/default/no_image.png"
											alt="no image">
									</c:otherwise>

								</c:choose>

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