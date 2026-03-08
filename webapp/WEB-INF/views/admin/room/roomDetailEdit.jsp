<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<div class="admin-room-edit">

	<h2 class="admin-title">객실 상세 수정</h2>

	<form action="${pageContext.request.contextPath}/admin/room/update.do"
		method="post">

		<input type="hidden" name="room_id" value="${room.roomId}">

		<div class="form-group">
			<label>객실 이름</label> <input type="text" value="${room.roomName}"
				readonly>
		</div>

		<div class="form-group">
			<label>기본 인원</label> <input type="number" name="base_capacity"
				value="${room.baseCapacity}">
		</div>

		<div class="form-group">
			<label>최대 인원</label> <input type="number" name="max_capacity"
				value="${room.maxCapacity}">
		</div>

		<div class="form-group">
			<label>기본 요금</label> <input type="text" name="base_price"
				value="${room.basePrice}" id="price">
		</div>

		<div class="form-group">
			<label>추가 요금</label> <input type="text" name="extra_charge"
				id="extraPrice" value="${room.extraCharge}">
		</div>

		<div class="form-buttons">

			<button type="submit" class="btn-save"
				onclick="return confirm('객실 정보를 수정하시겠습니까?')">수정</button>

			<a class="btn-list"
				href="${pageContext.request.contextPath}/admin/room/detailList.do">
				목록 </a>

		</div>

	</form>

</div>

<script>
	document.addEventListener("DOMContentLoaded", function() {

		const priceInput = document.getElementById("price");
		const extraInput = document.getElementById("extraPrice");
		const form = document.querySelector("form");

		function formatPrice(input) {

			input.addEventListener("input", function() {

				let value = this.value.replace(/[^0-9]/g, "");

				if (value === "") {
					this.value = "";
					return;
				}

				this.value = Number(value).toLocaleString();

			});

		}

		// 기본요금
		formatPrice(priceInput);

		// 추가요금
		formatPrice(extraInput);

		// 서버 전송 전 콤마 제거
		form.addEventListener("submit", function() {

			priceInput.value = priceInput.value.replace(/,/g, "");
			extraInput.value = extraInput.value.replace(/,/g, "");

		});

	});
</script>