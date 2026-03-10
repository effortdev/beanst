
function cancelCheck() {
	const checked = document.querySelectorAll("input[name='reservationIds']:checked");
	if (checked.length === 0) {
		alert("취소할 예약을 선택해주세요.");
		return false;
	}

	let message = "";
	checked.forEach(function(box) {
		const id = box.value;
		const name = document.querySelector("input[name='name_" + id + "']").value;
		const price = document.querySelector("input[name='price_" + id + "']").value;
		const formattedPrice = Number(price).toLocaleString();
		message += "\n예약자: " + name + "\n예약번호: " + id + "\n총 금액: " + formattedPrice + "원" + "\n------------------------";
	});
	message += "\n선택하신 예약을 취소하시겠습니까?";
	return confirm(message);
}


document.addEventListener("DOMContentLoaded", function() {
	const slider = document.querySelector(".reservationContainer");
	if (!slider) return;

	let isDown = false, startX, scrollLeft;

	slider.addEventListener("mousedown", e => {
		isDown = true;
		startX = e.pageX - slider.offsetLeft;
		scrollLeft = slider.scrollLeft;
	});

	slider.addEventListener("mouseleave", () => isDown = false);
	slider.addEventListener("mouseup", () => isDown = false);
	slider.addEventListener("mousemove", e => {
		if (!isDown) return;
		e.preventDefault();
		const x = e.pageX - slider.offsetLeft;
		const walk = (x - startX) * 2; 
		slider.scrollLeft = scrollLeft - walk;
	});


	slider.addEventListener("touchstart", e => {
		startX = e.touches[0].pageX - slider.offsetLeft;
		scrollLeft = slider.scrollLeft;
	});

	slider.addEventListener("touchmove", e => {
		const x = e.touches[0].pageX - slider.offsetLeft;
		slider.scrollLeft = scrollLeft - (x - startX);
	});
});