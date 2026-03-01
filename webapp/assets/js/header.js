/**
 * header js 
 */

 
 document.addEventListener("DOMContentLoaded", () => {
  const header = document.querySelector("header");


  let lastScrollY = window.scrollY;
  
  // 스크롤 시 header, 메뉴 숨김 처리
  window.addEventListener("scroll", () => {
    const currentScrollY = window.scrollY;

    if (currentScrollY > lastScrollY && currentScrollY > 10) {
      header.classList.add("hide");
      header.classList.remove("active");

    } else {
      header.classList.remove("hide");
      header.classList.add("active");
    }

    lastScrollY = currentScrollY;
  });
});