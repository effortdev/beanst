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
					<li><p>${sessionScope.loginMember.name}님환영합니다.</p></li>

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
	</div>
</header>

<script src="${pageContext.request.contextPath}/assets/js/header.js"></script>