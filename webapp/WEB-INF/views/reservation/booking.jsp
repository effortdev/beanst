<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt"%>

<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/flatpickr/dist/flatpickr.min.css">
<script src="https://cdn.jsdelivr.net/npm/flatpickr"></script>
<script src="https://npmcdn.com/flatpickr/dist/l10n/ko.js"></script>



<c:if test="${not empty errorMsg}">
	<script>
        alert("${errorMsg}");
    </script>
</c:if>
<div class="wrap">
	<div class="textBox">
		<div class="top_title fade-up">
			<h2 class="en">${mode eq 'update' ? '객실 예약 변경' : '객실 예약'}</h2>
			<p class="text">품격 있는 다이닝, 여유로운 라운지, 피트니스와 스파 등 다양한 부대시설을 통해 완성된
				객실을 예약해주세요.</p>
		</div>
	</div>
	<div class="booking-container flex">

		<div class="booking-form w-100">


			<form id="reserveForm"
				action="${pageContext.request.contextPath}/reservation/booking.do"
				method="post">

				<input type="hidden" name="mode" value="${mode}">
				<c:if test="${mode eq 'update'}">
					<input type="hidden" name="reservation_id"
						value="${originRes.reservationId}">
				</c:if>

				<div class="form-group">
					<label>객실 선택</label> <input type="hidden" name="room_id"
						id="selectedRoomId" required> <input type="hidden"
						name="room_name" id="selectedRoomName">

					<div class="room-grid">
						<c:forEach var="room" items="${roomList}">
							<div class="room-card card">

								<div class="room-image">
									<c:choose>
										<c:when test="${not empty room.imagePath}">
											<img src="${room.imagePath}?v=${room.roomId}"
												alt="${room.roomName}"
												onerror="this.src='${pageContext.request.contextPath}/assets/images/default/no_image.png'">
										</c:when>

										<c:otherwise>
											<img
												src="${pageContext.request.contextPath}/assets/images/default/no_image.png"
												alt="no image">
										</c:otherwise>
									</c:choose>
								</div>

								<div>
									<div>
										<h4>${room.roomName}</h4>
										<p>
											기준 ${room.baseCapacity}인 / 최대 ${room.maxCapacity}인<br>
											1박 <strong><fmt:formatNumber
													value="${room.basePrice}" pattern="#,###" /></strong>원
										</p>
									</div>

									<button type="button"
										class="btn btn-outline btn-block room-select-btn"
										data-id="${room.roomId}" data-name="${room.roomName}"
										data-base="${room.baseCapacity}"
										data-max="${room.maxCapacity}" data-price="${room.basePrice}">선택하기</button>
								</div>

							</div>
						</c:forEach>
					</div>
				</div>

				<div class="form-group flex gap-10 mt-30">
					<div class="w-100">
						<label>체크인</label> <input type="text" id="checkIn" name="check_in"
							class="form-control date-picker" required placeholder="날짜 선택">
					</div>
					<div class="w-100">
						<label>체크아웃</label> <input type="text" id="checkOut"
							name="check_out" class="form-control date-picker" required
							placeholder="날짜 선택">
					</div>
				</div>

				<div class="form-group flex gap-10 mt-10">
					<div class="w-100">
						<label>성인</label> <input type="number" id="adultCnt"
							name="adult_count" class="form-control person-calc" value="2"
							min="1" required>
					</div>
					<div class="w-100">
						<label>소아</label> <input type="number" id="childCnt"
							name="child_count" class="form-control person-calc" value="0"
							min="0" required>
					</div>
				</div>

				<input type="hidden" id="totalPriceInput" name="total_price"
					value="0">
				<button type="button" id="submitBtn"
					class="btn btn-primary btn-block mt-30">${mode eq 'update' ? '예약 변경 완료' : '예약하기'}
				</button>
			</form>
		</div>


		<div class="booking-summary">
			<h3>예약 요약</h3>
			<hr>

			<p>
				<strong>객실:</strong> <span id="summaryRoom">선택 안 됨</span>
			</p>
			<p>
				<strong>일정:</strong> <span id="summaryDate">-</span> (<span
					id="summaryNights">0</span>박)
			</p>
			<p>
				<strong>인원:</strong> 성인 <span id="summaryAdult">2</span>명, 소아 <span
					id="summaryChild">0</span>명
			</p>

			<hr>

			<div>
				<span>기본 요금</span> <span><span id="summaryBasePrice">0</span>원</span>
			</div>
			<div>
				<span>인원 추가금 (1인 4만원)</span> <span>+ <span id="summaryExtra">0</span>원
				</span>
			</div>

			<hr>

			<div>
				<p>총 결제 예상 금액</p>
				<h2>
					<span id="summaryTotal">0</span>원
				</h2>
			</div>
		</div>
	</div>
</div>
<script>

$(document).ready(function() {
    let currentRoom = { id: '', name: '선택 안 됨', base: 0, max: 0, price: 0 };

    let allBookedDates = ${bookedListJson != null ? bookedListJson : '[]'};
    let currentRoomBookings = []; 
    
    function formatDate(date) {
        let d = new Date(date), month = '' + (d.getMonth() + 1), day = '' + d.getDate(), year = d.getFullYear();
        if (month.length < 2) month = '0' + month;
        if (day.length < 2) day = '0' + day;
        return [year, month, day].join('-');
    }
    
    let checkInPicker = flatpickr("#checkIn", {
        locale: "ko",
        minDate: "today",
        onChange: function(selectedDates) {
            if (selectedDates.length > 0) {
                let inDate = selectedDates[0];
                let nextDay = new Date(inDate);
                nextDay.setDate(nextDay.getDate() + 1);
                checkOutPicker.set('minDate', nextDay);

                let futureBookings = currentRoomBookings
                    .map(b => new Date(b.checkIn))
                    .filter(d => d >= inDate)
                    .sort((a, b) => a - b);
                
                if (futureBookings.length > 0) {
                    checkOutPicker.set('maxDate', formatDate(futureBookings[0]));
                } else {
                    checkOutPicker.set('maxDate', null);
                }
                calculateTotal();
            }
        }
    });

    let checkOutPicker = flatpickr("#checkOut", {
        locale: "ko",
        minDate: new Date().fp_incr(1),
        onChange: function() { calculateTotal(); }
    });

    $('.room-select-btn').on('click', function() {
        $('.room-select-btn').removeClass('btn-secondary').addClass('btn-outline').text('선택하기');
        $('.room-card').css('border-color', '#eee');
        
        $(this).removeClass('btn-outline').addClass('btn-secondary').text('선택됨');
        $(this).closest('.room-card').css('border-color', '#C9A962'); 

        currentRoom.id = $(this).data('id');
        currentRoom.name = $(this).data('name');
        currentRoom.base = parseInt($(this).data('base')) || 0;
        currentRoom.max = parseInt($(this).data('max')) || 0;
        currentRoom.price = parseInt($(this).data('price')) || 0;

        $('#selectedRoomId').val(currentRoom.id);
        $('#selectedRoomName').val(currentRoom.name);
        
        currentRoomBookings = allBookedDates.filter(b => b.roomId == currentRoom.id);

        let checkInDisable = currentRoomBookings.map(b => {
            let outDate = new Date(b.checkOut);
            outDate.setDate(outDate.getDate() - 1); 
            return { from: b.checkIn, to: formatDate(outDate) };
        });

        let checkOutDisable = currentRoomBookings.map(b => {
            let inDate = new Date(b.checkIn);
            inDate.setDate(inDate.getDate() + 1); 
            return { from: formatDate(inDate), to: b.checkOut };
        });

        checkInPicker.clear();
        checkOutPicker.clear();
        checkInPicker.set('disable', checkInDisable);
        checkOutPicker.set('disable', checkOutDisable);
        checkOutPicker.set('maxDate', null);
        
        calculateTotal();
    });

    $('.person-calc').on('change', function() {
        calculateTotal();
    });

    function calculateTotal() {
        if (!currentRoom.id) {
            $('#summaryRoom').text("선택 안 됨");
            return;
        }

        let adult = parseInt($('#adultCnt').val()) || 0;
        let child = parseInt($('#childCnt').val()) || 0;

        if (currentRoom.max > 0 && (adult + child) > currentRoom.max) {
            alert("선택하신 '" + currentRoom.name + "' 객실은 최대 " + currentRoom.max + "명까지만 숙박 가능합니다.");
            
            if (adult > currentRoom.max) {
                adult = currentRoom.max;
                child = 0;
            } else {
                child = currentRoom.max - adult;
            }
            $('#adultCnt').val(adult);
            $('#childCnt').val(child);
        }

        let totalGuests = adult + child;

        $('#summaryRoom').text(currentRoom.name);
        $('#summaryAdult').text(adult);
        $('#summaryChild').text(child);

        let checkIn = $('#checkIn').val();
        let checkOut = $('#checkOut').val();
        let nights = 0;
        
        if (checkIn && checkOut) {
            let inDate = new Date(checkIn);
            let outDate = new Date(checkOut);
            nights = (outDate - inDate) / (1000 * 60 * 60 * 24);
            $('#summaryDate').text(checkIn + " ~ " + checkOut);
            $('#summaryNights').text(nights);
        } else {
            $('#summaryDate').text('-');
        }

        let effectiveNights = nights > 0 ? nights : 1;

        let extraCharge = 0;
        if (totalGuests > currentRoom.base && currentRoom.base > 0) {
            extraCharge = (totalGuests - currentRoom.base) * 40000;
        }

        let totalAmount = (currentRoom.price + extraCharge) * effectiveNights;

        $('#summaryBasePrice').text((currentRoom.price * effectiveNights).toLocaleString());
        $('#summaryExtra').text((extraCharge * effectiveNights).toLocaleString());
        $('#summaryTotal').text(totalAmount.toLocaleString());
        $('#totalPriceInput').val(totalAmount);
    }

    $('#submitBtn').on('click', function() {
        if(!$('#selectedRoomId').val()) { 
            alert("먼저 객실을 선택해주세요."); 
            return; 
        }
        if(!$('#checkIn').val() || !$('#checkOut').val()) { 
            alert("체크인과 체크아웃 날짜를 선택해주세요."); 
            return; 
        }
        $('#reserveForm').submit();
    });


    <c:if test="${mode eq 'update' and not empty originRes}">
        setTimeout(function() {
            const originRoomId = "${originRes.roomId}";
            if (originRoomId) {

                $(".room-select-btn[data-id='" + originRoomId + "']").trigger("click");
                

                $("#adultCnt").val("${originRes.adultCount}");
                $("#childCnt").val("${originRes.childCount}");

                let inDate = "${originRes.checkIn}".substring(0, 10);
                let outDate = "${originRes.checkOut}".substring(0, 10);
                
                checkInPicker.setDate(inDate, true);
                checkOutPicker.setDate(outDate, true);
                
                calculateTotal();
            }
        }, 300); 
    </c:if>

});
</script>

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