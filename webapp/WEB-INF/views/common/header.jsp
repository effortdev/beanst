<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

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
					<li><p>${sessionScope.loginMember.name} 님 환영합니다.</p></li>
				</c:if>
				<li><a href=# id="reserv" class="text en">Contact Us</a></li>
				<li><a
					href="${pageContext.request.contextPath}/reservationMain.do"
					class="ko">예약</a></li>
			</ul>
		</div>
	</div>
</header>

<script src="${pageContext.request.contextPath}/assets/js/header.js"></script>