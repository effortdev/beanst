<%@ page contentType="text/html; charset=UTF-8"%>

<aside class="admin-sidebar">
	<ul>
		<li><a
			href="${pageContext.request.contextPath}/admin/dashboard.do">
				대시보드</a></li>

		<li><a
			href="${pageContext.request.contextPath}/admin/roomManage.do"> 객실
				정보 관리</a></li>
		<li><a
			href="${pageContext.request.contextPath}/admin/facility/list.do">
				호텔 정보 관리 </a></li>

		<li><a
			href="${pageContext.request.contextPath}/admin/reservationManage.do">
				예약 관리</a></li>

		<li><a
			href="${pageContext.request.contextPath}/admin/memberManage.do">
				회원 관리</a></li>
		<li><a
			href="${pageContext.request.contextPath}/admin/memberManage.do">
				Q&A 관리</a></li>
	</ul>
</aside>