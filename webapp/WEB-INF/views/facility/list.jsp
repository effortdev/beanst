<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<div class="wrap">
	<div class="textBox">
		<div class="top_title  fade-up">
			<h2 class="en">Hotel Experience</h2>
			<p class="text">품격 있는 다이닝, 여유로운 라운지, 피트니스와 스파 등 다양한 부대시설을 통해 완성된
				휴식을 제공합니다.</p>
		</div>
	</div>
	<section class="facilityList">
		<c:forEach var="f" items="${facilityList}">

			<div class="facilityCard">

				<div class="imgBox">
					<img src="${f.imagePath}" alt="${f.facilityName}">
				</div>

				<div class="textBox">
					<h3>${f.facilityName}</h3>

					<p class="location">위치 : ${f.location}</p>

					<p class="time">운영시간 : ${f.openTime}</p>

					<a
						href="${pageContext.request.contextPath}/facilityDetail.do?id=${f.facilityId}"
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