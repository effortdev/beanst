<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>


<footer id="footer">

	<div class="footer-top">

		<div class="footer-left">
			<h2 class="footer-logo en">VINST HOTEL</h2>
			<p class="footer-desc">
				본 사이트는 팀 프로젝트 기반의 교육용 웹사이트입니다.<br> 상업적 목적이 아닌 학습 및 포트폴리오 용도로
				제작되었습니다.
			</p>
		</div>

		<div class="footer-right">

			<nav class="footer-nav en">
			<ul>
					<li class="en"><a href="${pageContext.request.contextPath}/faqList.do">FAQ</a></li>
					<li class="en"><a href="${pageContext.request.contextPath}/qnaList.do">Q&A</a></li>

					<c:if test="${sessionScope.loginMember != null}">
						<li><a
							href="${pageContext.request.contextPath}/member/mypage.do">
								MY PAGE </a></li>
					</c:if>
				</ul>
			</nav>

			<div class="footer-team">
				<h3 class="en">Team Information</h3>
				<ul>
					<li class="en"><strong>팀명 :</strong> Beans't Developers</li>
					<li><strong>팀장 :</strong> 김영훈</li>
					<li><strong>팀원 :</strong> 강태준, 김현우, 송은설, 채미숙</li>
				</ul>

				<c:if
					test="${sessionScope.loginMember == null 
             || sessionScope.loginMember.role != 'ADMIN'}">
					<p class="admin_login">
						<a href="${pageContext.request.contextPath}/admin/login.do">
							관리자 로그인 </a>
					</p>
				</c:if>
			</div>

		</div>

	</div>

	<div class="footer-bottom en">
		<p>© 2026 VINST HOTEL. All Rights Reserved.</p>
	</div>

</footer>