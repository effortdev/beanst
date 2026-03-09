<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt"%>


<div class="wrap">
	<div class="textBox">
		<div class="top_title fade-up">
			<h2 class="en">자주 묻는 질문</h2>
			<p class="text">궁금한 점을 빠르게 확인하세요</p>
		</div>
	</div>

	<div class="page-content">
		<div class="container">


			<!-- FAQ 카테고리 탭 (전체/예약/시설/기타) -->
			<div class="tab-btns text-center mb-20">
				<a href="${pageContext.request.contextPath}/faqList.do"
					class="${empty category ? 'active' : ''}">전체</a> <a
					href="${pageContext.request.contextPath}/faqList.do?category=예약"
					class="${category == '예약' ? 'active' : ''}">예약</a> <a
					href="${pageContext.request.contextPath}/faqList.do?category=시설"
					class="${category == '시설' ? 'active' : ''}">시설</a> <a
					href="${pageContext.request.contextPath}/faqList.do?category=기타"
					class="${category == '기타' ? 'active' : ''}">기타</a>
			</div>

			<!-- FAQ 목록/없음 처리 -->
			<!-- 조회된 FAQ가 없을 때 -->
			<c:choose>
				<c:when test="${empty faqList}">
					<div class="no-data">
						<p>등록된 FAQ가 없습니다.</p>
					</div>
				</c:when>

				<%-- FAQ 목록 반복 출력 --%>
				<c:otherwise>
					<c:forEach var="f" items="${faqList}">
						<div class="faq-item">
							<%-- 질문 영역: 클릭 시 toggleFaq로 답변 영역 열고 닫기 --%>
							<div class="faq-question" onclick="toggleFaq(this)">
								<span> <span class="category-badge">${f.category}</span>
									${f.question}
								</span> <span class="arrow">&#9660;</span>
							</div>
							<%-- 답변 영역 --%>
							<div class="faq-answer">${f.answer}</div>
						</div>
					</c:forEach>
				</c:otherwise>
			</c:choose>
		</div>
	</div>

</div>
<script>
	function toggleFaq(el) {
		var $q = $(el);
		var $a = $q.next(".faq-answer");
		$q.toggleClass("active");
		$a.toggleClass("show");
	}
	
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