<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<div class="container text-center complete-wrap">
    
    <div class="complete-icon">
        <span>✓</span>
    </div>
    
    <h2 class="complete-title">
        Welcome to Vinst
    </h2>
    
    <p class="complete-desc">
        회원가입이 성공적으로 완료되었습니다.<br>
        Vinst Hotel의 멤버가 되신 것을 환영합니다. 다양한 혜택을 누려보세요.
    </p>
    
    <div class="complete-btns">
        <a href="${pageContext.request.contextPath}/login/login.do" class="btn btn-primary btn-lg">
            로그인하기
        </a>
        <a href="${pageContext.request.contextPath}/main.do" class="btn btn-outline btn-lg">
            메인으로 가기
        </a>
    </div>

</div>