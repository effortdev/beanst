<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<div class="wrap">
	<div class="textBox">
		<div class="top_title  fade-up">
			<h2 class="en">Room Types</h2>
			<p class="text">스탠다드부터 스위트까지, 품격있는 선택의 폭을 제공합니다.</p>
		</div>
	</div>
	<section class="roomList">
		<c:forEach var="room" items="${roomList}">

			<div class="roomCard">

				<div class="imgBox">
					<img src="${room.image_path}" alt="${room.room_name}" width="200px">
				</div>

				<div class="textBox">
					<h3>${room.room_name}</h3>

					<p class="location">위치 : ${room.room_location}</p>

					<p class="time">이용시간 : ${room.usage_time}</p>

					<a
						href="${pageContext.request.contextPath}/roomDetail.do?id=${room.room_id}"
						class="detailBtn"> VIEW DETAIL </a>
				</div>

			</div>

		</c:forEach>

	</section>
</div>

<script>
document.addEventListener("DOMContentLoaded", function () {

    const targets = document.querySelectorAll(".fade-up");

    const observer = new IntersectionObserver((entries, observer) => {
        entries.forEach(entry => {

            if (entry.isIntersecting) {
                entry.target.classList.add("show");


                observer.unobserve(entry.target);
            }

        });
    }, {
        threshold: 0.3  
    });

    targets.forEach(target => {
        observer.observe(target);
    });

});

</script>