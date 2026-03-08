/* ================================================
   header.js 에 아래 코드를 추가하거나
   별도 header-responsive.js 로 사용하세요
   ================================================ */

document.addEventListener("DOMContentLoaded", () => {

	const hdr = document.getElementById("hdr");
	const hamburger = document.querySelector(".hamburger");
	const mobileNav = document.querySelector(".mobile-nav");

	/* ── 1. 스크롤 시 헤더 배경 전환 ────────────── */
	let lastScroll = 0;

	window.addEventListener("scroll", () => {
		const current = window.scrollY;

		// 일정 높이 내려가면 배경 적용
		if (current > 60) {
			hdr.classList.add("scrolled");
		} else {
			hdr.classList.remove("scrolled");
		}

		// 스크롤 방향에 따라 숨기기/보이기
		if (current > lastScroll && current > 120) {
			hdr.classList.add("hide");
			hdr.classList.remove("active");
		} else {
			hdr.classList.remove("hide");
			hdr.classList.add("active");
		}

		lastScroll = current <= 0 ? 0 : current;
	}, { passive: true });


	/* ── 2. 햄버거 메뉴 토글 ─────────────────────── */
	if (hamburger && mobileNav) {

		hamburger.addEventListener("click", () => {
			const isOpen = hamburger.classList.toggle("open");
			mobileNav.classList.toggle("open", isOpen);

			// 메뉴 열릴 때 body 스크롤 막기
			document.body.style.overflow = isOpen ? "hidden" : "";
		});

		// 메뉴 링크 클릭 시 닫기
		mobileNav.querySelectorAll("a, .text").forEach(link => {
			link.addEventListener("click", () => {
				hamburger.classList.remove("open");
				mobileNav.classList.remove("open");
				document.body.style.overflow = "";
			});
		});

		// 바깥 클릭 시 닫기
		document.addEventListener("click", (e) => {
			if (!hdr.contains(e.target) && !mobileNav.contains(e.target)) {
				hamburger.classList.remove("open");
				mobileNav.classList.remove("open");
				document.body.style.overflow = "";
			}
		});
	}

});