<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>




<div class="wrap">

	<section id="reservation">
		<div class="top_title  fade-up">
			<h2 class="en">Hotel Reservation</h2>
			<p class="text">고객님의 편안한 투숙을 위해 예약, 확인, 안내까지 모든 과정을 간편하게 제공합니다.</p>
		</div>
		<div class="experienceSwiper">
			<div id="flex">

				<div class="item">
					<div class="list_item">
						<div class="imgBox">
							<img
								src="${pageContext.request.contextPath}/assets/images/reservation/hotel-booking.png"
								alt="hotel_list_Dining">
							<div class="overlay">
								<a href="${pageContext.request.contextPath}/reservation/booking.do" class="viewBtn en">호텔 예약</a>
							</div>
						</div>
						<div class="textBox">
							<p class="text en">BOOKING</p>
						</div>
					</div>
				</div>

				<div class="item">
					<div class="list_item">
						<div class="imgBox">
							<img
								src="${pageContext.request.contextPath}/assets/images/reservation/hotel-reservation-check.png"
								alt="hotel_list_Lounge">
							<div class="overlay">
								<a href="${pageContext.request.contextPath}/reservation/check.do" class="viewBtn en">예약 조회</a>
							</div>
						</div>
						<div class="textBox">
							<p class="text en">RESERVATION-CHECK</p>
						</div>
					</div>
				</div>

				<div class="item">
					<div class="list_item">
						<div class="imgBox">
							<img
								src="${pageContext.request.contextPath}/assets/images/reservation/hotel-room-check.png"
								alt="hotel_list_Fitness">
							<div class="overlay">
								<a href="" class="viewBtn en">객실 조회</a>
							</div>
						</div>
						<div class="textBox">
							<p class="text en">ROOM-CHECK</p>
						</div>
					</div>
				</div>

				<div class="item">
					<div class="list_item">
						<div class="imgBox">
							<img
								src="${pageContext.request.contextPath}/assets/images/reservation/hotel-location.png"
								alt="hotel_list_Spa">
							<div class="overlay">
								<a href="${pageContext.request.contextPath}/reservationLocation.do" class="viewBtn en">오시는 길</a>
							</div>
						</div>
						<div class="textBox">
							<p class="text en">LOCATION</p>
						</div>
					</div>
				</div>

				<div class="item">
					<div class="list_item">
						<div class="imgBox">
							<img
								src="${pageContext.request.contextPath}/assets/images/reservation/hotel-faq.png"
								alt="hotel_list_Banquet">
							<div class="overlay">
								<a href="#" class="viewBtn en">자주하는 질문</a>
							</div>
						</div>
						<div class="textBox">
							<p class="text en">FAQ</p>
						</div>
					</div>
				</div>

			</div>
		</div>
	</section>

</div>
<script src="${pageContext.request.contextPath}/assets/js/main.js"></script>