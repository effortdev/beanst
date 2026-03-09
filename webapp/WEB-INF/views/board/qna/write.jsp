<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt"%>

<div class="wrap">
	<div class="textBox">
		<div class="top_title fade-up">
			<h2 class="en">1:1 문의 작성</h2>
			<p class="text">예약, 객실, 부대시설 등 어떤 내용이든 편하게 문의해 주세요. 빠르고 친절하게
				답변드리겠습니다.</p>
		</div>
	</div>

	<div class="page-content">
		<div class="container">
			<div class="form-box">
				<c:if test="${not empty errorMsg}">
					<div class="alert alert-danger">${errorMsg}</div>
				</c:if>
				<form action="${pageContext.request.contextPath}/qnaInsert.do"
					method="post">
					<div class="form-group">
						<label>제목</label> <input type="text" name="title"
							class="form-control" required placeholder="제목을 입력하세요">
					</div>
					<div class="form-group">
						<label>내용</label>
						<textarea name="content" class="form-control"
							style="min-height: 250px" required placeholder="문의 내용을 입력하세요"></textarea>
					</div>
					<div class="board-actions">
						<button type="submit" class="btn btn-outline room-select-btn">등록</button>
						<button type="button" class="btn btn-outline"
							onclick="location.href='${pageContext.request.contextPath}/qnaList.do'">취소</button>
					</div>
				</form>
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

</script>