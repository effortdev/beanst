<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<div class="join-container">
    <div class="join-wrap">
        <h2 class="join-title">Sign Up</h2>
        
        <c:if test="${not empty errorMsg}">
            <div class="text-danger" style="text-align: center; margin-bottom: 15px;">${errorMsg}</div>
        </c:if>

        <form action="${pageContext.request.contextPath}/member/join.do" method="post">
            <div class="form-group">
                <label>아이디</label>
                <input type="text" name="user_id" id="user_id" class="form-control" required placeholder="아이디 입력">
                <div id="id-msg" class="val-msg"></div>
            </div>
            
            <div class="form-group">
                <label>비밀번호</label>
                <input type="password" name="password" id="password" class="form-control" required placeholder="비밀번호 입력">
                <div id="pw-msg" class="val-msg"></div>
            </div>
            
            <div class="form-group">
                <label>비밀번호 확인</label>
                <input type="password" name="password_re" id="password_re" class="form-control" required placeholder="비밀번호 재입력">
                <div id="pw-re-msg" class="val-msg"></div>
            </div>
            
            <div class="form-group">
                <label>이름</label>
                <input type="text" name="name" id="name" class="form-control" required placeholder="실명 입력">
                <div id="name-msg" class="val-msg"></div>
            </div>
            
            <div class="form-group">
                <label>이메일</label>
                <input type="email" name="email" id="email" class="form-control" required placeholder="vinst@hotel.com">
                <div id="email-msg" class="val-msg"></div>
            </div>
            
            <div class="form-group" style="margin-bottom: 25px;">
                <label>전화번호</label>
                <input type="text" name="phone" id="phone" class="form-control" placeholder="010-0000-0000">
                <div id="phone-msg" class="val-msg"></div>
            </div>
            
            <button type="submit" class="btn btn-primary join-btn">
                회원가입 완료
            </button>
        </form>
        
        <div class="join-footer">
            <span>이미 계정이 있으신가요?</span> 
            <a href="${pageContext.request.contextPath}/login/login.do">로그인</a>
        </div>
    </div>
</div>

<script>
$(document).ready(function() {
    // 공통 검증 함수
    function checkValid(id, regex, msgId, successMsg, failMsg) {
        const value = $(id).val();
        const $msgArea = $(msgId);
        
        if (value.length === 0) {
            $msgArea.text("").removeClass("text-success text-danger");
            return false;
        }

        if (regex.test(value)) {
            $msgArea.text(successMsg).addClass("text-success").removeClass("text-danger");
            return true;
        } else {
            $msgArea.text(failMsg).addClass("text-danger").removeClass("text-success");
            return false;
        }
    }

    // 1. 아이디 실시간 검사
    $("#user_id").on("input", function() {
        checkValid("#user_id", /^[a-zA-Z0-9]{4,20}$/, "#id-msg", "사용 가능한 형식입니다.", "영문/숫자 조합 4~20자로 입력해주세요.");
    });

    // 2. 비밀번호 실시간 검사
    $("#password").on("input", function() {
        checkValid("#password", /^(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,20}$/, "#pw-msg", "안전한 비밀번호입니다.", "대문자, 숫자, 특수문자 포함 8~20자여야 합니다.");
        $("#password_re").trigger("input");
    });

    // 3. 비밀번호 확인 실시간 검사
    $("#password_re").on("input", function() {
        const pw = $("#password").val();
        const pwRe = $(this).val();
        const $msgArea = $("#pw-re-msg");

        if (pwRe.length === 0) {
            $msgArea.text("").removeClass("text-success text-danger");
        } else if (pw === pwRe) {
            $msgArea.text("비밀번호가 일치합니다.").addClass("text-success").removeClass("text-danger");
        } else {
            $msgArea.text("비밀번호가 일치하지 않습니다.").addClass("text-danger").removeClass("text-success");
        }
    });

    // 4. 이름 실시간 검사
    $("#name").on("input", function() {
        const val = $(this).val();
        if (val.length >= 4 && val.length <= 20) {
            $("#name-msg").text("적절한 이름입니다.").addClass("text-success").removeClass("text-danger");
        } else if (val.length === 0) {
            $("#name-msg").text("").removeClass("text-success text-danger");
        } else {
            $("#name-msg").text("4~20자 사이로 입력해주세요.").addClass("text-danger").removeClass("text-success");
        }
    });

    // 5. 이메일 실시간 검사
    $("#email").on("input", function() {
        checkValid("#email", /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/, "#email-msg", "올바른 이메일 형식입니다.", "형식이 올바르지 않습니다.");
    });

    // 6. 핸드폰 실시간 검사
    $("#phone").on("input", function() {
        checkValid("#phone", /^010-\d{3,4}-\d{4}$/, "#phone-msg", "올바른 번호 형식입니다.", "010-0000-0000 형식으로 입력해주세요.");
    });
});
</script>