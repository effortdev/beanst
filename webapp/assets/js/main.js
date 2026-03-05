/**
 * main jsp javaScript 입니다
 */

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


// hero섹션 js
 const heroSwiper = new Swiper('.heroSwiper', {
  effect: 'fade',
  fadeEffect: { crossFade: true },
  loop: true,
  speed: 2000,
  autoplay: {
    delay: 800,
    disableOnInteraction: false,
  },
});




// room_about 섹션 js
(function() {
  const style = document.createElement('style');
  style.type = 'text/css';
  style.appendChild(document.createTextNode(`
    .roomListSwiper,
    .roomListSwiper .swiper-wrapper,
    .roomListSwiper .swiper-slide {
      will-change: transform, opacity !important;
    }
    .roomListSwiper .swiper-wrapper {
      visibility: hidden !important;
    }
    .roomListSwiper.swiper-initialized .swiper-wrapper {
      visibility: visible !important;
    }
  `));
  document.head.appendChild(style);
})();

$(function() {
  const swiper = new Swiper(".roomListSwiper", {
    init: false,
    preloadImages: false,

    watchSlidesProgress: true,
    centeredSlides: true,
    speed: 700,

    pagination: {
      el: ".roomListSwiper .swiper-pagination",
      type: "fraction",
      formatFractionCurrent: n => ("0" + n).slice(-2),
      formatFractionTotal: n => ("0" + n).slice(-2),
    },

    navigation: {
      nextEl: ".roomListSwiper .swiper-button-next",
      prevEl: ".roomListSwiper .swiper-button-prev",
    },

    breakpoints: {
      320:  { slidesPerView: 1, spaceBetween: 10, centeredSlides: false },
      640:  { slidesPerView: 1.4, spaceBetween: 20, centeredSlides: true },
      1024: { slidesPerView: 2.2, spaceBetween: 50, centeredSlides: true },
      1440: { slidesPerView: 2.5, spaceBetween: 80, centeredSlides: true },
    },

    on: {
      init() {
        this.el.classList.add("swiper-initialized");
      }
    }
  });

  const doInit = () => swiper.init();
  if ('requestIdleCallback' in window) {
    requestIdleCallback(doInit, { timeout: 200 });
  } else {
    setTimeout(doInit, 100);
  }
});





// about_hotel js
const expSwiper = new Swiper(".experienceSwiper", {
  slidesPerView: "auto",
  spaceBetween: 80,
  freeMode: true,
  freeModeMomentum: false,

  breakpoints: {
    0: {              
      slidesPerView: 1,
      spaceBetween: 20,
      freeMode: false,  
	  centeredSlides: true,
    },
    768: {           
      slidesPerView: "auto",
      spaceBetween: 60,
      freeMode: true,
    },
    1024: {       
      slidesPerView: "auto",
      spaceBetween: 80,
      freeMode: true,
    }
  }
});

const items = document.querySelectorAll("#about_Hotel .experienceSwiper .imgBox");

items.forEach(item => {
  item.addEventListener("click", function () {
    this.classList.toggle("active");
  });
});