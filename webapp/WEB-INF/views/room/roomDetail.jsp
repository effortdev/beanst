<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<div class="wrap">
	<div class="facilityHeader">

		<div class="breadcrumb fade-up">
			<a href="${pageContext.request.contextPath}/main.do" class="en">Home</a>
			> <a href="${pageContext.request.contextPath}/roomList.do">객실 정보</a>
			> <span class="selectNone">${room.room_name}</span>
		</div>

		<h1 class="title en fade-up">${room.room_name}</h1>

		<p class="desc fade-up">${room.room_description}</p>

	</div>

	<section class="bgImg">
		<c:set var="heroImg" value="" />

		<c:forEach var="img" items="${images}">
			<c:if test="${img.is_main eq 'Y'}">
				<c:set var="heroImg" value="${img.image_path}" />
			</c:if>
		</c:forEach>

		<div class="facilityHero">

			<c:choose>

				<c:when test="${not empty heroImg}">
					<img src="${heroImg}"
						onerror="this.src='${pageContext.request.contextPath}/assets/images/default/no_image.png'">
				</c:when>

				<c:otherwise>
					<img
						src="${pageContext.request.contextPath}/assets/images/default/no_image.png">
				</c:otherwise>

			</c:choose>

		</div>
	</section>

	<section class="hotelInfo">
		<table class="facilityInfoTable">
			<tr>
				<th>객실 이름</th>
				<td>${room.room_name}</td>
			</tr>
			<tr>
				<th>체크인</th>
				<td>${room.usage_time}</td>
			</tr>
			<tr>
				<th>위치</th>
				<td>${room.room_location}</td>
			</tr>
			<tr>
				<th>편의시설</th>
				<td>${room.amenity}</td>
			</tr>
			<tr>
				<th>미니바</th>
				<td>${room.minibar}</td>
			</tr>
		</table>
	</section>

	<section class="Gallery">

		<div class="roomGallery">

			<c:forEach var="img" items="${images}" varStatus="status">
				<c:if test="${img.is_main ne 'Y'}">

					<div class="galleryItem" data-index="${status.index}">
						<img src="${img.image_path}">
					</div>

				</c:if>
			</c:forEach>

		</div>

	</section>

	<div class="galleryModal">

		<div class="galleryModalInner">

			<span class="galleryClose">✕</span>

			<div class="swiper gallerySwiper">

				<div class="swiper-wrapper">

					<c:forEach var="img" items="${images}">
						<c:if test="${img.is_main ne 'Y'}">

							<div class="swiper-slide">
								<img src="${img.image_path}">
							</div>

						</c:if>
					</c:forEach>

				</div>

				<div class="swiper-button-next"></div>
				<div class="swiper-button-prev"></div>

			</div>

		</div>

	</div>

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


document.addEventListener("DOMContentLoaded", function(){

	const modal = document.querySelector(".galleryModal");
	const closeBtn = document.querySelector(".galleryClose");
	const items = document.querySelectorAll(".galleryItem");

	const swiper = new Swiper(".gallerySwiper",{
		navigation:{
			nextEl:".swiper-button-next",
			prevEl:".swiper-button-prev"
		}
	});

	items.forEach((item,index)=>{

		item.addEventListener("click",()=>{

			modal.classList.add("active");

			swiper.slideTo(index,0);

		});

	});

	closeBtn.addEventListener("click",()=>{

		modal.classList.remove("active");

	});

	modal.addEventListener("click",(e)=>{

		if(e.target === modal){
			modal.classList.remove("active");
		}

	});

});
</script>