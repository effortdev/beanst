<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/complete.css">

<div class="container text-center complete-wrap">
    
    <div class="complete-icon">
        <span>✓</span>
    </div>
    
    <h2 class="complete-title">
        Reservation Complete
    </h2>
    
    <p class="complete-desc">
        고객님의 예약이 성공적으로 완료되었습니다.<br>
        Vinst Hotel을 선택해 주셔서 감사합니다. 편안한 휴식이 되도록 최선을 다하겠습니다.
    </p>
    
    <div class="complete-btns">
        <a href="${pageContext.request.contextPath}/reservation/check.do" class="btn btn-primary btn-lg">
            예약 내역 조회
        </a>
        <a href="${pageContext.request.contextPath}/main.do" class="btn btn-outline btn-lg">
            메인으로 가기
        </a>
    </div>

</div>