document.addEventListener("DOMContentLoaded", function() {
	// 원래 값 저장
	document.querySelectorAll("#email, #phone").forEach(input => input.dataset.original = input.value);

	// 회원정보 수정 submit
	const memberForm = document.getElementById("memberForm");
	if (memberForm) {
		memberForm.addEventListener("submit", function(e) {
			const currentPwInput = document.getElementById("currentPw");
			const confirmPwInput = document.getElementById("confirmPw");
			const editingInputs = Array.from(document.querySelectorAll(".edit-input"))
				.filter(i => i.value !== i.dataset.original);
			const newPw = document.getElementById("newPw").value.trim();
			const newPwCheck = document.getElementById("newPwCheck").value.trim();
			let pwValue = "";

			if (currentPwInput && currentPwInput.value.trim() !== "") pwValue = currentPwInput.value.trim();
			else if (confirmPwInput && confirmPwInput.value.trim() !== "") pwValue = confirmPwInput.value.trim();

			if (!pwValue) { alert("변경된 정보를 저장하려면 현재 비밀번호를 입력해주세요."); e.preventDefault(); return; }

			// 새 비밀번호 유효성
			if (newPw) {
				const pwRegex = /^(?=.*[A-Z])(?=.*\d)(?=.*[\W_]).{8,}$/;
				if (!pwRegex.test(newPw)) { alert("새 비밀번호는 최소 8자 이상, 대문자1개, 숫자1개, 특수문자1개 포함해야 합니다."); e.preventDefault(); return; }
				if (newPw !== newPwCheck) { alert("새 비밀번호와 확인이 일치하지 않습니다."); e.preventDefault(); return; }
			}

			// 이메일/전화번호 유효성
			for (let i of editingInputs) {
				if (i.name === "email") { if (!/^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(i.value)) { alert("유효한 이메일 형식이 아닙니다."); e.preventDefault(); return; } }
				if (i.name === "phone") { if (!/^\d{3}-\d{4}-\d{4}$/.test(i.value)) { alert("전화번호는 000-0000-0000 형식이어야 합니다."); e.preventDefault(); return; } }
			}

			// currentPw hidden input
			let hiddenPw = this.querySelector('input[name="currentPw"]');
			if (!hiddenPw) {
				hiddenPw = document.createElement("input");
				hiddenPw.type = "hidden";
				hiddenPw.name = "currentPw";
				this.appendChild(hiddenPw);
			}
			hiddenPw.value = pwValue;

			// 이메일/전화번호 변경 hidden input
			editingInputs.forEach(i => {
				let hidden = document.createElement("input");
				hidden.type = "hidden"; hidden.name = i.name; hidden.value = i.value;
				this.appendChild(hidden);
				i.setAttribute("readonly", true); i.classList.remove("editing");
				const btn = i.parentElement.querySelector(".edit-btn");
				if (btn) btn.textContent = "수정";
			});

			const confirmRow = document.querySelector(".password-confirm-row");
			if (confirmRow) confirmRow.style.display = "none";
		});
	}
});

// 수정 버튼 토글
function toggleInput(button) {
	const input = button.parentElement.querySelector("input");
	const confirmRow = document.querySelector(".password-confirm-row");
	if (input.hasAttribute("readonly")) {
		input.removeAttribute("readonly"); input.focus();
		button.textContent = "저장"; input.classList.add("editing");
	} else {
		input.setAttribute("readonly", true); button.textContent = "수정"; input.classList.remove("editing");
	}
	let hasChanges = Array.from(document.querySelectorAll(".edit-input"))
		.some(i => i.value !== i.dataset.original || i.classList.contains("editing"));
	confirmRow.style.display = hasChanges ? "flex" : "none";
}

// 비밀번호 변경 영역 토글
function togglePassword() {
	const area = document.getElementById("passwordArea");
	area.style.display = area.style.display === "block" ? "none" : "block";
}

// 나의 예약 바로가기
function goToReservation() {
	window.location.href = CONTEXT_PATH + "/reservation/check.do";
}

// 회원탈퇴 모달 열기/닫기
function withdrawMember() {
	document.getElementById("withdrawModal").style.display = "block";
	document.getElementById("modalOverlay").style.display = "block";
}
function closeModal() {
	document.getElementById("withdrawModal").style.display = "none";
	document.getElementById("modalOverlay").style.display = "none";
	document.getElementById("withdrawPw").value = "";
}
function submitWithdraw() {
	const pwConfirm = document.getElementById("withdrawPw").value.trim();
	if (!pwConfirm) { alert("비밀번호 입력 필수"); return; }

	const form = document.createElement("form");
	form.method = "post";
	form.action = CONTEXT_PATH + "/member/update.do";

	let hiddenPw = document.createElement("input");
	hiddenPw.type = "hidden";
	hiddenPw.name = "currentPw";
	hiddenPw.value = pwConfirm;
	form.appendChild(hiddenPw);

	let statusInput = document.createElement("input");
	statusInput.type = "hidden";
	statusInput.name = "status";
	statusInput.value = "2";
	form.appendChild(statusInput);

	document.body.appendChild(form);
	form.submit();
}