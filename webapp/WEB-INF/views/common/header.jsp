<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<script>
	$(document).ready(function() {
		$(".logout-btn").on("click", function(e) {
			if (!confirm("로그아웃 하시겠습니까?")) {
				e.preventDefault(); // '취소' 누르면 이동 안 함
			}
		});
	});
</script>

<a href="#contents" class="skipnav">컨텐츠바로가기</a>
<header id="hdr">
	<div class="hdr_inner">
		<div class="logo">
			<a href="${pageContext.request.contextPath}/main.do"> <img
				src="${pageContext.request.contextPath}/assets/images/beanstLogo.png"
				alt="logo">
			</a>
		</div>
		<div class="rightItem">
			<ul>
				<c:if test="${sessionScope.loginMember != null}">
					<li><a
						href="${pageContext.request.contextPath}/member/myPage.do">
							${sessionScope.loginMember.name}님환영합니다. </a></li>

					<li><a class="logout-btn"
						href="${pageContext.request.contextPath}/logout.do" class="ko">로그아웃</a>
					</li>

				</c:if>
				<li><a href="${pageContext.request.contextPath}/qnaList.do"
					id="reserv" class="text">1:1 문의하기</a></li>
				<li><a
					href="${pageContext.request.contextPath}/reservationMain.do"
					class="ko">예약</a></li>
			</ul>
		</div>
		<button class="hamburger" aria-label="메뉴 열기" aria-expanded="false">
			<span></span> <span></span> <span></span>
		</button>
	</div>
</header>
<nav class="mobile-nav" aria-hidden="true">
	<ul>
		<li><a href="${pageContext.request.contextPath}/main.do"
			class="text">HOME</a></li>
		<li><a href="${pageContext.request.contextPath}/facilityList.do"
			class="text">시설 안내</a></li>
		<li><a
			href="${pageContext.request.contextPath}/reservation/booking.do"
			class="text btn-reserve">예약하기</a></li>
		<li><a href="${pageContext.request.contextPath}/faqList.do"
			class="text">FAQ</a></li>
		<li><a href="${pageContext.request.contextPath}/qnaList.do"
			class="text">1:1문의</a></li>
	</ul>
</nav>

<script src="${pageContext.request.contextPath}/assets/js/header.js"></script>