<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fn" uri="jakarta.tags.functions"%>

<aside class="admin-sidebar">
	<ul>
		<li class="${fn:contains(contentPage, 'dashboard') ? 'active' : ''}">
			<a href="${pageContext.request.contextPath}/admin/dashboard.do">대시보드</a>
		</li>

		<li class="${fn:contains(contentPage, 'roomManage') ? 'active' : ''}">
			<a href="${pageContext.request.contextPath}/admin/roomManage.do">객실
				정보 관리</a>
		</li>

		<li class="${fn:contains(contentPage, 'roomDetail') ? 'active' : ''}">
			<a href="${pageContext.request.contextPath}/admin/room/detailList.do">
				객실 상세 관리 </a>
		</li>

		<li class="${fn:contains(contentPage, 'facility') ? 'active' : ''}">
			<a href="${pageContext.request.contextPath}/admin/facility/list.do">호텔
				시설 관리</a>
		</li>

		<li class="${fn:contains(contentPage, 'reservation') ? 'active' : ''}">
			<a
			href="${pageContext.request.contextPath}/admin/reservation/list.do">예약
				관리</a>
		</li>

		<li class="${fn:contains(contentPage, 'member') ? 'active' : ''}">
			<a href="${pageContext.request.contextPath}/admin/memberManage.do">회원
				관리</a>
		</li>

		<li class="${fn:contains(contentPage, 'qna') ? 'active' : ''}"><a
			href="${pageContext.request.contextPath}/admin/qna/list.do">1:1
				문의 관리</a></li>

		<li class="${fn:contains(contentPage, 'faq') ? 'active' : ''}"><a
			href="${pageContext.request.contextPath}/admin/faq/list.do">FAQ
				관리</a></li>
	</ul>
</aside>