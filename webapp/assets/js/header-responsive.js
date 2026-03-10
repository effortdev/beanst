

document.addEventListener("DOMContentLoaded", () => {

	const hdr = document.getElementById("hdr");
	const hamburger = document.querySelector(".hamburger");
	const mobileNav = document.querySelector(".mobile-nav");


	let lastScroll = 0;

	window.addEventListener("scroll", () => {
		const current = window.scrollY;


		if (current > 60) {
			hdr.classList.add("scrolled");
		} else {
			hdr.classList.remove("scrolled");
		}


		if (current > lastScroll && current > 120) {
			hdr.classList.add("hide");
			hdr.classList.remove("active");
		} else {
			hdr.classList.remove("hide");
			hdr.classList.add("active");
		}

		lastScroll = current <= 0 ? 0 : current;
	}, { passive: true });


	if (hamburger && mobileNav) {

		hamburger.addEventListener("click", () => {
			const isOpen = hamburger.classList.toggle("open");
			mobileNav.classList.toggle("open", isOpen);


			document.body.style.overflow = isOpen ? "hidden" : "";
		});


		mobileNav.querySelectorAll("a, .text").forEach(link => {
			link.addEventListener("click", () => {
				hamburger.classList.remove("open");
				mobileNav.classList.remove("open");
				document.body.style.overflow = "";
			});
		});


		document.addEventListener("click", (e) => {
			if (!hdr.contains(e.target) && !mobileNav.contains(e.target)) {
				hamburger.classList.remove("open");
				mobileNav.classList.remove("open");
				document.body.style.overflow = "";
			}
		});
	}

});